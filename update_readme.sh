
ARTIFACTS_URL="https://app.circleci.com/pipelines/github/anis-saa77/ceri-m1-techniques-de-test/BLABLABLA2/workflows/${WORKFLOW_ID}/jobs/${JOB_NUMBER}/artifacts"

chmod +w README.md
# Mettre à jour le README.md
sed -i '/## Rapport Checkstyle/{N;s|\(## Rapport Checkstyle\n\).*|\1Voir le rapport Checkstyle des artefacts : [![Checkstyle](https://img.shields.io/badge/Checkstyle-passing-brightgreen)]($ARTIFACTS_URL)|}' README.md
