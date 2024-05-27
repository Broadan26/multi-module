# Multi-Module Quarkus Project

[![Build](https://github.com/Broadan26/multi-module/actions/workflows/maven-build.yml/badge.svg)](https://github.com/Broadan26/multi-module/actions/workflows/maven-build.yml)
[![Coverage](https://raw.githubusercontent.com/Broadan26/multi-module/825606d2c279c714f155ade786d0166f12989a76/.github/badges/jacoco.svg)](https://github.com/Broadan26/multi-module/actions/workflows/maven-build.yml)
[![Branches](https://raw.githubusercontent.com/Broadan26/multi-module/825606d2c279c714f155ade786d0166f12989a76/.github/badges/branches.svg)](https://github.com/Broadan26/multi-module/actions/workflows/maven-build.yml)

---

## Application Information
* Quarkus 3.8.4
* Java 21 (Built with Amazon Corretto)

## Requirements
* Maven 3.8.2+/3.9/4.0
* Java 21
* Git
* JetBrains IntelliJ (to take advantage of the built-in run profile)

---

# IntelliJ Setup

## 1. Pull repo on to the local machine
* Navigate to the repo via this [link](https://github.com/Broadan26/multi-module)
* Click the green `Code` button and copy the HTTPs link

## 2. Import the Project into IntelliJ
* In IntelliJ click File -> New -> Project From Version Control
  * Ensure the version control is Git and choose a suitable directory for the project
* Then click clone and wait for the source repo to be cloned to your machine

## 3. Setup the Project Settings
* Click on File -> Project Structure
* On the Project tab, ensure that the SDK chosen is SDK21
* On the Modules tab, ensure that the Module SDK chosen is SDK21

## 4. Setup the Environment
* This section has already been configured for developers, but additional custom runs can be configured
  * IntelliJ should recognize the project as a Quarkus + Maven Build with multiple submodules
  * In the .run folder is an xml file containing the run information to get started right away
* To create a custom run, follow these additional steps
  * Click on Run -> Edit Configuration
  * Add a New Configuration and select `Quarkus`
    * Give it any name that makes sense to you
    * Application Module should be `just-for-fun`
    * Underneath the Environment tab select `Configuration`
      * Tick the box for `Resolve Workplace Artifacts`
      * This will ensure that the project looks for modules and dependencies in the project space before searching on Maven Central

## 5. Run the Application
* Click on Run -> Run `Run Application`
  * This should download all the required Quarkus dependencies via Maven
  * It will also build the core, data, and services submodules that the primary application module, api, relies upon
  * You'll know that Quarkus started up correctly if you see their splash mark in the console and a localhost url on port 8080

## 6. Useful Plugins
* Kubernetes
  * Includes a rich YAML editor for modifying yaml files in Kubernetes manifests or Quarkus configuration files
* GitToolBox
  * Shows the latest commits to each line of code in the editor and provides better branch management in the IDE
* SonarLint
  * Allows for better code quality and security by analyzing code as it is written or by scanning after the fact
  * It can also be configured with a 3rd party SonarQube server where project scan configurations are held
* JPA Buddy
  * Allows for better control over existing JPA entities in the Hibernate ORM
  * It can also assist in the creation of new JPA entities
  * Supports Lombok for data class annotations