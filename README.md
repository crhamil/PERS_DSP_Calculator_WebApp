# DSP Calculator WebApp
*WORK IN PROGRESS*  


Application web **Java (Spring Boot)**. Ce projet est un **refactoring** du projet [ING_POO_DSP_Calculator](https://github.com/adamas-whyowhy/ING_POO_DSP_Calculator.git) entrepris afin d'améliorer la **lisibilité**, la **structure** et la **maintenabilité** du code.  
Il s'agit également d'un projet personnel, qui a pour but de me faire monter en compétences sur le framework web **Spring Boot** et le développement web en Java de manière générale.  

Ce refactoring comprend :
- une meilleure séparation des responsabilités entre les classes par l'implémentation de **patrons de conception** (par exemple, Factory et Singleton) et un héritage et une implémentation mieux maîtrisée
- une organisation plus claire du code source avec l'introduction de **packages**
- une amélioration de la cohérence globale du projet
- une **interface web** pour la visualisation des données


Le projet utilise **Maven** pour la gestion des dépendances et la compilation.

> **Note :** Ce projet est configuré pour **Java 25** et peut être exécuté directement via Maven grâce au plugin `spring-boot-maven-plugin`.

---

## Technologies principales

- **Spring Boot** : framework de développement basé sur Java, utilisé pour la création d'applications web.  
- **Tomcat** : serveur d'applications intégré à Spring Boot, permettant l'exécution de l'application web sans configuration externe.
- **Maven** : outil de gestion de projet et de dépendances.
- **Thymeleaf** : moteur de templates pour la génération dynamique de pages HTML côté serveur.
- **TailwindCSS** : framework CSS utilitaire pour la mise en forme de l'interface.  
  → Dans ce projet, TailwindCSS est compilé à partir de la **version client** du framework.
- **DataTables** : plugin JavaScript pour créer des tableaux interactifs.

---

## Dépendances du projet

- **spring-boot-starter-web** : Composants nécessaires à la création d’applications web RESTful avec Spring MVC et un serveur Tomcat intégré.
- **spring-boot-starter-thymeleaf** : Moteur de template **Thymeleaf** pour la génération dynamique de pages HTML.
- **spring-boot-starter-security** : Fonctionnalités de sécurité, notamment l'authentification et la gestion des accès.
- **spring-boot-devtools** : Outils de développement, tels que le rechargement automatique du code, à enlever lors du passage en production.
- **spring-boot-starter-test** : Bibliothèque de tests unitaires et d’intégration dans un contexte Spring Boot.
