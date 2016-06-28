tar zxvf dubbo-monitor-simple-2.5.4-SNAPSHOT-assembly.tar.gz
cd dubbo-monitor-simple-2.5.4-SNAPSHOT

vi conf/dubbo.properties
    dubbo.registry.address=zookeeper://127.0.0.1:2181

bin/start.sh

http://127.0.0.1:8080
