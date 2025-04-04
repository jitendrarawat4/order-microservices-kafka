# order-microservices-kafka

Order Processing using kafka

docker build -t order-creation-service:latest .
docker build -t order-execution-service:latest .

docker-compose down
docker-compose up

docker-compose up -d --build

docker exec -it kafka kafka-topics --create --topic order-topic --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092

docker exec -it kafka kafka-topics --create --topic order-topic --partitions 1 --replication-factor 1 --bootstrap-server kafka:9092

kafka-topics.sh --describe --topic order-topic --bootstrap-server localhost:9092
kafka-topics.sh --alter --topic order-topic --partitions 5 --bootstrap-server localhost:9092

Zipkin logging
docker run -d -p 9411:9411 openzipkin/zipkin

http://localhost:9411
