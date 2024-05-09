#!/usr/bin/bash

whoami

mkdir -p /home/debian
cd /home/debian
echo in directory $PWD

sudo apt-get update && sudo apt-get upgrade
sudo apt update && sudo apt upgrade

echo "######################## Installing MariaDB ########################"
# sudo yum install mysql -y
sudo apt install curl -y
curl -LsS https://r.mariadb.com/downloads/mariadb_repo_setup | sudo bash -s -- --mariadb-server-version="mariadb-10.11.2"
sudo apt update
sudo apt install mariadb-server -y
sudo systemctl start mariadb
sudo systemctl status mariadb
sudo systemctl enable mariadb

sudo mysql -u root -e "UPDATE mysql.user SET plugin='mysql_native_password' WHERE User='root'";
sudo mysql -u root -e "USE mysql; UPDATE user SET password=PASSWORD('comsc') WHERE User='root' AND Host = 'localhost'; FLUSH PRIVILEGES;"
sudo mysql -u root -e "GRANT ALL PRIVILEGES on *.* TO root@localhost IDENTIFIED BY 'comsc' WITH GRANT OPTION;"
#echo "creating mysql_secure_installation.txt..."
#touch mysql_secure_installation.txt
#cat <<EOF > mysql_secure_installation.txt
#
#n
#Y
#comsc
#comsc
#Y
#Y
#Y
#Y
#Y
#EOF
#
#echo "running mysql_secure_installation..."
#sudo mysql_secure_installation < mysql_secure_installation.txt

cat <<EOF > gitlab_project_keypair.key
-----BEGIN OPENSSH PRIVATE KEY-----
b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAABlwAAAAdzc2gtcn
NhAAAAAwEAAQAAAYEA2ZLmOCk/JnC6P+0MYhTpUYMrId2OAIxgX2VX3nFzP8km388TeO7D
ToJl+lV5kP/NmlMur27Aq6OrI4N77+KPKxXJOyRrB4XSMjrWf5EMVjHhT+We0DcQM6n0s+
RI5IruZN0UULnodvwP2Utvkv2Sz3HhJZrhKVwzlG1/4ar5D0vMYR5F2BxszkzPLgjMYYKq
c4nzMIsUmhs1pFCvOuBonvTlIohUvEeGhAtVzs0qD7EoMytlcsaJRTBCHaT1YKm1jOTfOv
QCaSlQg7PVSAK2YRk21i/PCJPVqSsetQksw8kCwHJbH5Mp/LzJOhokSFiva1XSOe3gFljh
Ke7dcDVaqJKno05laIG0jT5nh4TX05+sJKU3RIAnC95llOd70p6w7uax73+dd1Vrzce7Y6
yZFjOVIjagJhge5tglPSEhnbuLWPnTyDJZJMwizJHdFemvspwqB8CZ3r0LnVl+TgGNO7hc
0dotG++Ob+1dxxliwwtMRH9BT7vdP/DhPfw3oI4fAAAFmKX4W/Wl+Fv1AAAAB3NzaC1yc2
EAAAGBANmS5jgpPyZwuj/tDGIU6VGDKyHdjgCMYF9lV95xcz/JJt/PE3juw06CZfpVeZD/
zZpTLq9uwKujqyODe+/ijysVyTskaweF0jI61n+RDFYx4U/lntA3EDOp9LPkSOSK7mTdFF
C56Hb8D9lLb5L9ks9x4SWa4SlcM5Rtf+Gq+Q9LzGEeRdgcbM5Mzy4IzGGCqnOJ8zCLFJob
NaRQrzrgaJ705SKIVLxHhoQLVc7NKg+xKDMrZXLGiUUwQh2k9WCptYzk3zr0AmkpUIOz1U
gCtmEZNtYvzwiT1akrHrUJLMPJAsByWx+TKfy8yToaJEhYr2tV0jnt4BZY4Snu3XA1WqiS
p6NOZWiBtI0+Z4eE19OfrCSlN0SAJwveZZTne9KesO7mse9/nXdVa83Hu2OsmRYzlSI2oC
YYHubYJT0hIZ27i1j508gyWSTMIsyR3RXpr7KcKgfAmd69C51Zfk4BjTu4XNHaLRvvjm/t
XccZYsMLTER/QU+73T/w4T38N6COHwAAAAMBAAEAAAGBAK5LzRwOhuVvhvTxHGtyXxsKnk
IJ79kmm4mwcPzlG7NsnDTM+cd2VoWNVs59LN7o3beUjaDCc5zMh6vyiBW7BIA6erNjUE59
eM7+bZlSBHUEjn/n7hrVEaihrUNzTKWxWpr4tG6bD8xJzxR/Jwm1E6gVjY0HjvsIPtXYBo
ss4UrMqRTgIImo880soQg6yigPeicGywgHUuDFjWptrILZBVbZzrmkR2VxUyRyxDHgD6L0
g8TCLgoNXChOKZj/7gaazDhj/gAFPO4kFieKIqLtrkm/hz+KRFKw9OmglIwpxtbOBcl9S4
6GC1H1uoATJnR8Oe6N+BNRhjOPvlu5kwLzKHMmqqSxuN0VpmExzy1lFC69iiVaXZhTwDhP
lSbdyaXuog9SWUmhbk3JRxZ7B2txbk/xH5t7UPoMi8Tv6u4/AChcVZCnvd7jJTvcWqv0KE
Vl/64W34iRbVKj/7vjoY2UTbI1JKb3nsxENyQ8in5/aTE9yc2pjN4+hZC/NI8tnh45AQAA
AMALtiq57V/MxAzBf1cWZZ+Q/QEconlb3sRjUPWJf/xUlNFKGl9Eodr96cns3za8IOBYxE
lCT6pU6rwvgADcOoPU0cWpXzPbdmlFjKwCyqK6aAbIx1ayeovH8N94jZ3SAGhq7psSZTLg
sUNqEFR/n0Ks+KZm0itpeO8Ba0MkWjD5bAfN+HZXDKX6l3a1sYoYdg8f5euFqLdKaRRvnG
Aej/tddV2n8xW9UYEBfFpO7LZ3O875vpibFj4/N3Z8Bz1BpbgAAADBAPcc+swD+nuRLLlP
273mskmYjU9WXwfBtp1FKvRhXdoLQ3VLyBYN85nlykPhLj3Tsvbk61mmQXK4q73TYV8qc/
xiY9wey/74z0iPM1B74Ocx9KEvV6LMB+MhCOZxjPoSZ11V9wyJVsXqn40glmWxx95odAJL
22feeAFHyAeLlBgTTmgWJ7cctxYNZuUkcoUiAQ2+3Wfv8o0SK8ELJDy85w2iWiW9ljbf+y
IYStd6RsrYa1dLCG9ZB3lxh1ORHOstgQAAAMEA4WX394kTyPDKax1zbo7VGAj0O61LfH8r
yO1yJGs7g/pLtUJQcziyjk9cNBjVtGAA+J9tL9IULVT7CxpEU0nVvIXcW/GzCX4cvVKwHZ
mcdUwQBSSEINid2/7p/lTZ3YtsogKr9MBbSKMCl8xGxrq7XcfxT8Q+wEGIWHr+KKNz3f/6
mgooF5MTLtsttevVN7x8fi8+dQILSxaKr3kzaKJHU+LinwXc80FOAzIIgEB9dsNItk1il6
+6J+YM092VesufAAAAHElEK2MyMzA5Nzc0NEBEU0ExMEY2MEE4MTVFM0UBAgMEBQY=
-----END OPENSSH PRIVATE KEY-----
EOF

chmod 400 gitlab_project_keypair.key
ssh-agent bash -c 'ssh-add gitlab_project_keypair.key'

touch ~/.ssh/known_hosts
ssh-keyscan git.cardiff.ac.uk >> ~/.ssh/known_hosts
chmod 644 ~/.ssh/known_hosts

sudo apt-get install git -y

ssh-agent bash -c 'ssh-add gitlab_project_keypair.key; git clone -b ore-testing git@git.cardiff.ac.uk:c23097744/team-4-smart-towns.git' -y

sudo mysql -u root -pcomsc < /home/debian/team-4-smart-towns/src/main/resources/schema.sql
sudo mysql -u root -pcomsc < /home/debian/team-4-smart-towns/src/main/resources/data.sql

# sudo mysql -u root -e "USE mysql; UPDATE user SET Password=PASSWORD('comsc') WHERE User='root' AND Host = 'localhost'; FLUSH PRIVILEGES;"
# sudo mysql -u root -e "USE mysql; UPDATE user SET authentication_string=PASSWORD('comsc') WHERE User='root' AND Host='localhost'; FLUSH PRIVILEGES;"
#sudo mysql -u root -e "GRANT ALL PRIVILEGES on *.* TO root@localhost IDENTIFIED BY 'comsc' WITH GRANT OPTION;"


sudo apt-get install unzip -y
sudo apt-get install wget -y

echo "######################## Installing Java 17 ########################"
# Install using .deb
wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.deb
sudo apt install ./jdk-17_linux-x64_bin.deb -y
sudo apt install default-jre -y

#wget https://download.java.net/java/GA/jdk17/0d483333a00540d886896bac774ff48b/35/GPL/openjdk-17_linux-x64_bin.tar.gz
#
#tar zxvf openjdk-17_linux-x64_bin.tar.gz
#sudo mv jdk-17 /usr/local/
#
#export JAVA_HOME=/usr/local/jdk-17
#export PATH=$PATH:$JAVA_HOME/bin

echo "######################## Installing gradle ########################"
wget https://services.gradle.org/distributions/gradle-8.0.2-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-8.0.2-bin.zip
export PATH=$PATH:/opt/gradle/gradle-8.0.2/bin

cd /home/debian/team-4-smart-towns

# gradle build
gradle build -x test
# # gradle test
gradle bootrun
