git clone https://github.com/alibaba/opensesame.git
cd opensesame
mvn install

git clone https://github.com/alibaba/dubbo.git
cd dubbo
mvn clean install -Dmaven.test.skip
