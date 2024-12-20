
ARTIFACTS_URL="https://app.circleci.com/pipelines/github/anis-saa77/ceri-m1-techniques-de-test/BLABLABLA/workflows/${WORKFLOW_ID}/jobs/${JOB_NUMBER}/artifacts"

echo "Avant modification :"
cat README.md
echo "Permissions avant modification :"
ls -l README.md
chmod +w README.md
# Mettre à jour le README.md
sed -i "s|## Rapport Checkstyle|## Rapport Checkstyle\n\nVoir le rapport Checkstyle des artefacts : [![Checkstyle](https://img.shields.io/badge/Checkstyle-passing-brightgreen)]($ARTIFACTS_URL)|" README.md
echo "Après modification :"
cat README.md
echo "Permissions après modification :"
ls -l README.md