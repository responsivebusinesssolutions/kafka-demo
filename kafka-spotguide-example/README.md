# kafka-spotguide-example

This is a simple Kafka Streams WordCount application written in Scala.

It consumes messages from topic: `streams-plaintext-input`

It produces the output to topic: `streams-wordcount-output`

## Usage:
Build:
```
mvn clean package
```

Run:
```
java -jar target/kafka-spotguide-example-1.0-SNAPSHOT.jar <your-bootstrap-server-address-with-port>
```

Example:
```
java -jar target/kafka-spotguide-example-1.0-SNAPSHOT.jar kafka-spotguide-broker-1:19090
```