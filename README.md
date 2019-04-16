# spotguide-kafka

Apache Kafka is a distributed streaming platform used for building real-time data pipelines and streaming apps. It is horizontally scalable, fault-tolerant, wicked fast, and runs in production in thousands of companies.

Use this `spotguide` to deploy Kafka on Kubernetes within minutes.
It uses [Pravega Zookeeper-operator](https://github.com/pravega/zookeeper-operator) to install the required Zookeeper cluster.
Monitoring Kafka is done through Prometheus and Grafana.
Kafka and Zookeeper are separated using Kubernetes namespaces.
All Kafka related entities are deployed to namespace `kafka`, Zookeeper ones to `zookeeper`.

It also contains a WordCount Kafka Stream example. It can be used to try out the spotguide.