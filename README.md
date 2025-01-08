# UCE Génie Logiciel Avancé : Techniques de tests

**Nom et prénom** : Saa Anis  
**Groupe** : Groupe IA Classique

## Badges
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/anis-saa77/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/anis-saa77/ceri-m1-techniques-de-test/tree/master)
[![codecov](https://codecov.io/gh/anis-saa77/ceri-m1-techniques-de-test/graph/badge.svg?token=6THCFKTI18)](https://codecov.io/gh/anis-saa77/ceri-m1-techniques-de-test)
[![Checkstyle](https://img.shields.io/badge/Checkstyle-passing-brightgreen)](https://anis-saa77.github.io/ceri-m1-techniques-de-test/checkstyle/checkstyle.html)
[![Javadoc](https://img.shields.io/badge/Documentation-Javadoc-blue)](https://anis-saa77.github.io/ceri-m1-techniques-de-test/target/site/javadoc/apidocs)

### Choix techniques
- **Maven** : Utilisation de **Maven** pour la gestion des dépendances et la construction du projet Java.
- **CircleCI** : Nous avons choisi **CircleCI** pour automatiser l'intégration continue et l'exécution des tests. Il s'intègre facilement avec GitHub et permet une gestion simple des pipelines.
- **Codecov** : Pour le suivi de la couverture de tests, nous avons utilisé **Codecov**, un service populaire qui offre des badges pour afficher la couverture du code et une analyse détaillée des tests.
- **JaCoCo** : Pour générer les rapports de couverture de tests, nous avons intégré le plugin **JaCoCo** dans notre configuration Maven.
- **Checkstyle** : Utilisation de **Checkstyle** pour la validation des normes de codage et la génération des rapports de style.
- **Javadoc** : Utilisation de **Javadoc** pour le déploiement de la documentation.

### Rapport TP6
#### Défaut non couvert :
L'implémentation de la team rocket RocketPokemonFactory a provoqué une erreur de cast (ClassCastException) due à la classe Pokedex dont les attributs n'était pas abstrait et des tests qui ne tenaient pas compte de cette éventualité.
J'ai donc modifié mon code de manière à corriger le défaut et rajouter un test adéquat.

**Précision :**
Le problème vient plus précisément de la classe Pokedex dont les attributs étaient des classe fille (PokemonMetadataProvider et PokemonFactory), l'instanciation se faisait en castant les paramètres :  :
new Pokedex((PokemonMetadataProvider) metadataProvider, (PokemonFactory) pokemonFactory);
Forcément, l'utilisation de RocketPokemonFactory rentrait en conflit avec le cast de PokemonFactory.
Il a donc suffit d'ajouter de l'abstraction en modifiant le type des attributs par les classes mères (IPokemonMetadataProvider et IPokemonFactory) afin de supporter toute les classes filles.

**Test Ajouté :**
J'ai ajouté un test (dans PokedexTest) pour l'initialisation d'un pokedex à partir de différente classes filles de IPokemonMetadataProvider et IPokemonFactory.

#### Défauts couvert par les test :
L'implémentation ne passe pas les test d'indice invalide, aucune erreur n'est levé à l'utilisation d'un indice hors de l'intervalle.
Aussi, des erreurs sont levées car les statistiques des pokemon ne sont pas celles attendues par mes tests. C'est normal car la team rocket les générent aléatoirement.
