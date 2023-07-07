/** 
 * Copyright (C) 2022 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.model.util;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Utilities which depend on the maven vs OSGi environment.
 * 
 * @author Vincent Hemery
 */
public class EnvironmentUtil {

    /**
     * Private unused constructor.
     */
    private EnvironmentUtil() {
        // do nothing
    }

    /**
     * Test whether class is loaded in an OSGi context
     * 
     * @return true when loaded in OSGi
     */
    public static boolean isOSGi() {
        // test whether class is instanceof org.osgi.framework.BundleReference interface without loading the interface
        Predicate<Class<?>> isOSGiBundleRef = c -> {
            @SuppressWarnings({ "unchecked", "rawtypes" })
            BiPredicate<BiPredicate, Class<?>> isOSGiBundleRefHelper = (bipredicate, clazz) -> {
                return
                // test whether class is itself the interface
                clazz.getName().equals("org.osgi.framework.BundleReference") ||
                // or it has an interface which matches (recursively)
                        Stream.of(clazz.getInterfaces()).anyMatch(i -> bipredicate.test(bipredicate, i)) ||
                // or its superclass matches (recursively)
                        clazz.getSuperclass() != null && bipredicate.test(bipredicate, clazz.getSuperclass());
            };
            return isOSGiBundleRefHelper.test(isOSGiBundleRefHelper, c);
        };
        Optional<Class<? extends ClassLoader>> classLoaderClass = Optional
                .ofNullable(EnvironmentUtil.class.getClassLoader())
                .map(ClassLoader::getClass);
        return classLoaderClass.filter(isOSGiBundleRef).isPresent();
    }

}
