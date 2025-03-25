/**
 * Copyright (C) 2021 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MavenUtil {

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules()
            .enable(SerializationFeature.INDENT_OUTPUT).setSerializationInclusion(Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private MavenUtil() {
        // private constructor
    }

    public static Map<String, Object> loadReport(Path reportFile)
            throws IOException {
        return mapper.readValue(reportFile.toFile(), Map.class);
    }

    public static String getMvnExecutable() {
        String os = System.getProperty("os.name");
        return os.startsWith("Windows") ? "mvn.cmd" : "mvn";
    }

    public static void execute(File pomFile, String mvnExecutable, List<String> goals, Map<String, String> properties,
            List<String> activeProfiles) throws InterruptedException, IOException {
        MavenCommandBuilder builder = new MavenCommandBuilder(mvnExecutable).directory(pomFile.getParentFile());
        goals.forEach(builder::addGoal);
        properties.forEach(builder::addProperty);
        if (!activeProfiles.isEmpty()) {
            builder.activeProfiles(activeProfiles.stream().collect(Collectors.joining(",")));
        }
        activeProfiles.forEach(builder::addGoal);
        builder.start().waitFor();
    }

    public static Path analyze(Path projectRoot, String mvnExecutable) throws InterruptedException, IOException {
        var appModule = projectRoot.resolve("app");
        File pomFile = appModule.resolve("pom.xml").toFile();
        if (!pomFile.isFile()) {
            throw new IllegalArgumentException("Not a maven project !");
        }
        /*
         * Extensions must be built and installed first. If not, we may encounter this error:
         * [ERROR] Failed to execute goal org.bonitasoft.maven:bonita-project-maven-plugin:1.0.3:analyze (default-cli) on project my-project:
         * Execution default-cli of goal org.bonitasoft.maven:bonita-project-maven-plugin:1.0.3:analyze failed:
         * Failed to analyze artifact com.company:resourceNameRestAPI:zip:0.0.1:compile: com.company:resourceNameRestAPI:zip:0.0.1 was not found
         */
        var extensionsModule = projectRoot.resolve("extensions");
        File extensionsPomFile = extensionsModule.resolve("pom.xml").toFile();
        if (extensionsPomFile.isFile()) {
            new MavenCommandBuilder(mvnExecutable).directory(extensionsModule.toFile()).addGoal("install").start()
                    .waitFor();
        }

        new MavenCommandBuilder(mvnExecutable).directory(appModule.toFile()).addGoal("bonita-project:analyze").start()
                .waitFor();
        return appModule.resolve("target").resolve("bonita-dependencies.json");
    }

    public static List<String> buildClasspath(Path projectRoot, String mvnExecutable)
            throws InterruptedException, IOException {
        var appModule = projectRoot.resolve("app");
        File pomFile = appModule.resolve("pom.xml").toFile();
        if (!pomFile.isFile()) {
            throw new IllegalArgumentException("Not a maven project !");
        }

        var classpathFile = Files.createTempFile("build_classpath", ".txt");
        new MavenCommandBuilder(mvnExecutable).directory(appModule.toFile()).addGoal("dependency:build-classpath")
                .addProperty("mdep.outputFile", classpathFile.toAbsolutePath().toString())
                .addProperty("mdep.pathSeparator", ";").start().waitFor();
        return Stream.of(Files.readString(classpathFile).split(";")).collect(Collectors.toList());
    }

    static class MavenCommandBuilder {

        private ProcessBuilder builder;
        private boolean quiet = false;
        private boolean clean = false;
        private boolean install = false;
        private List<String> commands;
        private String activeProfiles;
        private List<String> goals = new ArrayList<>();
        private Map<String, String> properties = new HashMap<>();

        public MavenCommandBuilder(String mvnExecutable) {
            builder = new ProcessBuilder(mvnExecutable);
            // make sure correct Java version is used
            builder.environment().put("JAVA_HOME", System.getProperty("java.home"));
            commands = builder.command();
        }

        public MavenCommandBuilder addGoal(String goal) {
            this.goals.add(goal);
            return this;
        }

        public MavenCommandBuilder activeProfiles(String activeProfiles) {
            this.activeProfiles = activeProfiles;
            return this;
        }

        public MavenCommandBuilder directory(File workingDirectory) {
            this.builder.directory(workingDirectory);
            return this;
        }

        public MavenCommandBuilder addProperty(String key, String value) {
            properties.put(key, value);
            return this;
        }

        public Process start() throws IOException {
            commands.add("-ntp");
            if (clean) {
                commands.add("clean");
            }
            if (install) {
                commands.add("install");
            }
            goals.stream().forEach(commands::add);
            if (activeProfiles != null) {
                commands.add(String.format("-P%s", activeProfiles));
            }
            properties.forEach((key, value) -> commands.add(String.format("-D%s=%s", key, value)));
            if (quiet) {
                commands.add("-q");
            }
            commands.add("-B");
            if (Objects.equals(System.getProperty("style.color"), "never")) {
                commands.add("-Dstyle.color=never");
            }
            String bonitaVersion = System.getProperty("bonita.version");
            if (bonitaVersion != null && !bonitaVersion.isBlank() && !"null".equals(bonitaVersion)) {
                commands.add("-Dbonita.version=" + bonitaVersion);
            }
            Process process = builder.start();
            new Thread(new ConsoleConsumer(process)).start();
            return process;
        }

        class ConsoleConsumer implements Runnable {

            private Process process;
            private boolean quiet;

            public ConsoleConsumer(Process process, boolean quiet) {
                this.process = process;
                this.quiet = quiet;
            }

            public ConsoleConsumer(Process process) {
                this(process, false);
            }

            @Override
            public void run() {
                final InputStream stream = process.getInputStream();

                try {
                    byte[] buf = new byte[32];
                    int num;
                    // Do not try reading a line cos it considers '\r' end of line
                    while ((num = stream.read(buf)) != -1) {
                        if (!quiet) {
                            System.out.write(buf, 0, num);
                        }
                    }
                } catch (IOException e) {
                    //ignore exception
                }
            }

        }

    }

}
