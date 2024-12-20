# UCE Génie Logiciel Avancé : Techniques de tests

**Nom et prénom** : Saa Anis  
**Groupe** : Groupe IA Classique

## Badges
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/anis-saa77/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/anis-saa77/ceri-m1-techniques-de-test/tree/master)
[![codecov](https://codecov.io/gh/anis-saa77/ceri-m1-techniques-de-test/graph/badge.svg?token=6THCFKTI18)](https://codecov.io/gh/anis-saa77/ceri-m1-techniques-de-test)

## Rapport Checkstyle
Voir le rapport Checkstyle des artefacts : [![Checkstyle](https://img.shields.io/badge/Checkstyle-passing-brightgreen)](https://app.circleci.com/pipelines/github/anis-saa77/ceri-m1-techniques-de-test/126/workflows/e0359ac0-f85f-4c10-b59d-02af97c6c7e6/jobs/164/artifacts)

### Explication du badge Checkstyle
Le badge checkstyle redirige vers la page des artéfacts CircleCI du workflow courant, où le rapport pourra être retrouvé.
Le fichier  config.yml a été modifié pour que le lien du readme soit bien mis à jour à chaque push.

### Choix techniques

- **CircleCI** : Nous avons choisi **CircleCI** pour automatiser l'intégration continue et l'exécution des tests. Il s'intègre facilement avec GitHub et permet une gestion simple des pipelines.
- **Codecov** : Pour le suivi de la couverture de tests, nous avons utilisé **Codecov**, un service populaire qui offre des badges pour afficher la couverture du code et une analyse détaillée des tests.
- **JaCoCo** : Pour générer les rapports de couverture de tests, nous avons intégré le plugin **JaCoCo** dans notre configuration Maven.
- **Maven** : Utilisation de **Maven** pour la gestion des dépendances et la construction du projet Java.
