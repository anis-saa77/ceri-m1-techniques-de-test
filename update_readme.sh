#!/bin/bash

# Récupérer l'ID du dernier pipeline réussi
RESPONSE=$(curl -s -u ${CIRCLECI_TOKEN}: \
  "https://circleci.com/api/v2/project/github/anis-saa77/ceri-m1-techniques-de-test/pipeline?limit=1&filter=successful")

# Afficher la réponse JSON brute pour déboguer
echo "Réponse de l'API : $RESPONSE"

# Vérifier si la réponse contient des erreurs
if echo "$RESPONSE" | jq -e .items > /dev/null; then
    PIPELINE_ID=$(echo "$RESPONSE" | jq -r '.items[0].id')
    PIPELINE_NUMBER=$(echo "$RESPONSE" | jq -r '.items[0].number')

    # Générer l'URL des artefacts
    ARTIFACTS_URL="https://app.circleci.com/pipelines/github/anis-saa77/ceri-m1-techniques-de-test/${PIPELINE_NUMBER}/artifacts"

    # Mettre à jour le README.md
    sed -i "s|# Rapport Checkstyle|# Rapport Checkstyle\n\nVoir le rapport Checkstyle des artefacts : $ARTIFACTS_URL|" README.md

    echo "Lien vers les artefacts : $ARTIFACTS_URL"
else
    echo "Erreur : La réponse de l'API ne contient pas de pipeline valide."
fi
