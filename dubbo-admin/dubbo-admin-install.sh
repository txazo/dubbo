wget http://mirrors.cnnic.cn/apache/tomcat/tomcat-8/v8.0.36/bin/apache-tomcat-8.0.36.tar.gz
tar -zxvf apache-tomcat-8.0.36.tar.gz
cd apache-tomcat-8.0.36
rm -rf webapps/ROOT

unzip dubbo-admin-2.5.4-SNAPSHOT.war -d webapps/ROOT

vi webapps/ROOT/WEB-INF/dubbo.properties
    dubbo.registry.address=zookeeper://127.0.0.1:2181
    dubbo.admin.root.password=root
    dubbo.admin.guest.password=root

bin/startup.sh

http://127.0.0.1:8081
