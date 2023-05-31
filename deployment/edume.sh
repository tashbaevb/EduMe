echo 'Copy files...'

scp -i ~/.ssh/edume \
    ./target/*.jar \
    beksdeveloper@34.65.179.105:~/depledume/hosting.jar
