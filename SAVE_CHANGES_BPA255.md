# BPA-255 - Sauvegarde des Modifications (bonita-process-model)

Date: 2026-02-02
Branch: support/9.0.x

## 🎯 Objectif

Nettoyer les logs de debug dans ProcessPomGenerator.java tout en gardant la logique métier qui permet de respecter les fragments décochés.

## 📝 État des Modifications

### Fichiers Modifiés Actuellement (git status)

```
M bundles/org.bonitasoft.bonita2bar/src/org/bonitasoft/bonita2bar/BarBuilder.java
M bundles/org.bonitasoft.bonita2bar/src/org/bonitasoft/bonita2bar/process/pomgen/ProcessPomGenerator.java
```

---

## 📦 Modifications Détaillées

### 1. BarBuilder.java ✅ (À GARDER tel quel)

**Ligne modifiée** : 181

**Changement** :
```java
// AVANT
return processPomGenerator.withGeneratedPom(process, pomAccess -> {

// APRÈS
return processPomGenerator.withGeneratedPom(process, configuration, pomAccess -> {
```

**Raison** : Cette modification est **NÉCESSAIRE** pour passer la configuration à ProcessPomGenerator afin qu'il puisse filtrer les dépendances selon les fragments décochés.

**Action** : ✅ **GARDER** cette modification

---

### 2. ProcessPomGenerator.java 🔄 (À NETTOYER)

#### Ajouts à garder ✅

**Imports nouveaux (lignes 24-26, 34-35)** :
```java
import java.util.Set;
import java.util.stream.Collectors;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.Fragment;
```
✅ **GARDER** : Nécessaires pour la nouvelle logique

**Nouvelle méthode `withGeneratedPom` surchargée (lignes 94-110)** :
```java
public <R, E extends Exception> R withGeneratedPom(Pool process, Configuration configuration,
        ProcessPomConsumer<R, E> consumer)
        throws IOException, E {
    try (var pomAccess = generatePom(process, configuration)) {
        return consumer.consume(pomAccess);
    }
}
```
✅ **GARDER** : Permet de passer la configuration

**Modification de la signature de `generatePom` (ligne 124)** :
```java
// AVANT
private ProcessPom generatePom(Pool process) throws IOException {

// APRÈS
private ProcessPom generatePom(Pool process, Configuration configuration) throws IOException {
```
✅ **GARDER** : Nécessaire pour recevoir la configuration

**Appel à `filterDependenciesFromConfiguration` (lignes 152-154)** :
```java
// filter dependencies based on configuration.processDependencies
if (configuration != null) {
    filterDependenciesFromConfiguration(model, configuration);
}
```
✅ **GARDER** : C'est le cœur de notre solution

**Méthode `filterDependenciesFromConfiguration` (lignes 197-265)** :
✅ **GARDER LA LOGIQUE** mais ❌ **SUPPRIMER LES LOGS DE DEBUG**

---

#### Logs de debug à supprimer ❌

**Lignes 205-207** : Banner de version
```java
System.out.println("========================================");
System.out.println("[ProcessPomGenerator] VERSION: 2025-01-26 with exported attribute fix");
System.out.println("========================================");
```
❌ **SUPPRIMER**

**Ligne 211** :
```java
System.out.println("[ProcessPomGenerator] No processDependencies containers, keeping all dependencies");
```
❌ **SUPPRIMER**

**Lignes 227-230** :
```java
System.out.println("[ProcessPomGenerator] === Filtering dependencies ===");
System.out.println("[ProcessPomGenerator] Total fragments (checked + unchecked): " + totalFragments);
System.out.println("[ProcessPomGenerator] Selected JARs (exported=true): " + selectedJars.size());
selectedJars.forEach(jar -> System.out.println("[ProcessPomGenerator]   ✓ " + jar));
```
❌ **SUPPRIMER**

**Lignes 234-235** :
```java
System.out.println(
        "[ProcessPomGenerator] No fragments found in .conf, keeping all dependencies (backward compatibility)");
```
❌ **SUPPRIMER**

**Lignes 241-245** :
```java
System.out.println(
        "[ProcessPomGenerator] All dependencies unchecked by user, removing all JAR dependencies from POM");
int beforeCount = model.getDependencies().size();
model.getDependencies().clear();
System.out.println("[ProcessPomGenerator] Removed " + beforeCount + " dependencies");
```
❌ **SUPPRIMER les System.out** (garder le code)

**Ligne 250** :
```java
System.out.println("[ProcessPomGenerator] Dependencies before filtering: " + beforeCount);
```
❌ **SUPPRIMER**

**Ligne 257** :
```java
if (shouldRemove) {
    System.out.println("[ProcessPomGenerator]   ✗ Removing: " + jarName);
}
```
❌ **SUPPRIMER le System.out** (garder le if pour la logique)

**Lignes 262-264** :
```java
int afterCount = model.getDependencies().size();
System.out.println("[ProcessPomGenerator] Dependencies after filtering: " + afterCount);
System.out.println("[ProcessPomGenerator] Removed " + (beforeCount - afterCount) + " dependencies");
```
❌ **SUPPRIMER les System.out** (garder la logique si nécessaire)

---

## 🎯 Code Final Attendu

La méthode `filterDependenciesFromConfiguration` devrait ressembler à :

```java
private void filterDependenciesFromConfiguration(Model model, Configuration configuration) {
    var processDepsContainers = configuration.getProcessDependencies();
    if (processDepsContainers.isEmpty()) {
        return; // No filter, keep all dependencies
    }

    // Count total fragments (checked or not) to detect old .conf files
    long totalFragments = processDepsContainers.stream()
            .flatMap(container -> container.getFragments().stream())
            .count();

    // Build set of selected JARs (exported=true) from ALL containers
    Set<String> selectedJars = processDepsContainers.stream()
            .flatMap(container -> container.getFragments().stream())
            .filter(Fragment::isExported)
            .map(Fragment::getValue)
            .collect(Collectors.toSet());

    // If no fragments at all (old .conf before our changes), keep all dependencies (backward compatibility)
    if (totalFragments == 0) {
        return;
    }

    // If fragments exist but none selected (user unchecked all), remove all JAR dependencies
    if (selectedJars.isEmpty()) {
        model.getDependencies().clear();
        return;
    }

    // Remove dependencies not in the selected set
    model.getDependencies().removeIf(dep -> {
        String jarName = dep.getArtifactId() + "-" + dep.getVersion() + ".jar";
        return !selectedJars.contains(jarName);
    });
}
```

**Clean, sans logs, uniquement la logique métier** ✅

---

## 🔙 Pour Revenir en Arrière

Si quelque chose ne va pas :
```bash
cd /Users/imad/Documents/workspace/eclipse/bonita-studio-sp-dev2/git/bonita-process-model

# Voir les changements actuels
git diff

# Revenir en arrière fichier par fichier
git checkout HEAD -- bundles/org.bonitasoft.bonita2bar/src/org/bonitasoft/bonita2bar/process/pomgen/ProcessPomGenerator.java

# Ou tout annuler
git reset --hard HEAD
```

---

## 📌 Patch Sauvegardé

Le patch complet des changements actuels est sauvegardé dans:
```
/tmp/bonita-process-model_changes.patch
```

Pour appliquer ce patch plus tard:
```bash
cd /Users/imad/Documents/workspace/eclipse/bonita-studio-sp-dev2/git/bonita-process-model
git apply /tmp/bonita-process-model_changes.patch
```