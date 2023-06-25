1. Start Zipkin server
docker run -d -p 9411:9411 openzipkin/zipkin

2. Start Kafka-zookeeper  docker-compose file


Run these steps :

# docker exec -it kafka bash    - To Login to Kafka bash 
# kafka-console-producer.sh --topic service-station --bootstrap-server localhost:9092  
# kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic service-station --from-beginning


Sequence of Services to start :

Discovery-Service
cloud-Config-server
API_GateWay
Vehicle