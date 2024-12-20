#!/bin/bash

# Récupérer l'ID du dernier pipeline réussi
PIPELINE_ID=$(curl -s -u ${CIRCLECI_TOKEN}: \
  "https://circleci.com/api/v2/project/github/anis-saa77/ceri-m1-techniques-de-test/pipeline?limit=1&filter=successful" | \
  jq -r '.items[0].id')

# Récupérer le numéro du dernier pipeline (ex: 42)
PIPELINE_NUMBER=$(curl -s -u ${CIRCLECI_TOKEN}: \
  "https://circleci.com/api/v2/project/github/anis-saa77/ceri-m1-techniques-de-test/pipeline/${PIPELINE_ID}" | \
  jq -r '.number')

# Générer l'URL des artefacts
ARTIFACTS_URL="https://app.circleci.com/pipelines/github/anis-saa77/ceri-m1-techniques-de-test/${PIPELINE_NUMBER}/artifacts"

# Mettre à jour le README.md
sed -i "s|# Rapport Checkstyle|# Rapport Checkstyle\n\nVoir le rapport Checkstyle des artefacts : $ARTIFACTS_URL|" README.md

# Afficher le lien généré
echo "Lien vers les artefacts : $ARTIFACTS_URL"
