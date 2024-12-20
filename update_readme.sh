RESPONSE=$(curl -s -u ${CIRCLECI_TOKEN}: \
  "https://circleci.com/api/v2/project/github/anis-saa77/ceri-m1-techniques-de-test/pipeline?limit=1&filter=successful")

# Afficher la réponse JSON brute pour déboguer
#echo "Réponse de l'API : $RESPONSE"

# Vérifier si la réponse contient des erreurs
if echo "$RESPONSE" | jq -e .items > /dev/null; then
  PIPELINE_ID=$(echo "$RESPONSE" | jq -r '.items[0].id')
  PIPELINE_NUMBER=$(echo "$RESPONSE" | jq -r '.items[0].number')

  WORKFLOW_RESPONSE=$(curl -s -H "Circle-Token: ${CIRCLECI_TOKEN}" \
      "https://circleci.com/api/v2/pipeline/${PIPELINE_ID}/workflow")
  #echo "Réponse workflow : $WORKFLOW_RESPONSE"

  WORKFLOW_ID=$(echo "$WORKFLOW_RESPONSE" | jq -r '.items[0].id')

  # Récupérer le job number du job spécifique dans ce workflow
  #JOB_NUMBER=$(echo "$WORKFLOW_RESPONSE" | jq -r '.items[0].jobs[0].number')
  JOBS_RESPONSE=$(curl -s -H "Circle-Token: ${CIRCLECI_TOKEN}" \
      "https://circleci.com/api/v2/workflow/${WORKFLOW_ID}/job")

  # Afficher les jobs pour débogage
  #echo "Réponse Jobs : $JOBS_RESPONSE"

  # Extraire le job number (en supposant qu'il y a au moins un job dans le workflow)
  JOB_NUMBER=$(echo "$JOBS_RESPONSE" | jq -r '.items[0].job_number')

  # Vérifier si JOB_NUMBER est vide
  if [ -z "$JOB_NUMBER" ]; then
    echo "Erreur : Aucun job trouvé dans ce workflow."
  else
    # Générer l'URL des artefacts du job spécifique
    ARTIFACTS_URL="https://app.circleci.com/pipelines/github/anis-saa77/ceri-m1-techniques-de-test/${PIPELINE_NUMBER}/workflows/${WORKFLOW_ID}/jobs/${JOB_NUMBER}/artifacts"

    # Mettre à jour le README.md
    chmod +w README.md
    sed -i "/## Rapport Checkstyle/{N;s|\(## Rapport Checkstyle\n\).*|\1Voir le rapport Checkstyle des artefacts : [![Checkstyle](https://img.shields.io/badge/Checkstyle-passing-brightgreen)]($ARTIFACTS_URL)|}" README.md

    echo "Lien vers les artefacts : $ARTIFACTS_URL"
  fi
else
  echo "Erreur : La réponse de l'API ne contient pas de pipeline valide."
fi