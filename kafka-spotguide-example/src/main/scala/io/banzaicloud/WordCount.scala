package io.banzaicloud

import java.util.Properties
import java.util.concurrent.TimeUnit

import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

object WordCount extends App {

  import org.apache.kafka.streams.scala.Serdes._
  import org.apache.kafka.streams.scala.ImplicitConversions._

  val config: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-scala-application")
    val bootstrapServers = if (args.length > 0) args(0) else "localhost:9092"
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
    p
  }

  val builder = new StreamsBuilder()
  val textLines: KStream[String, String] = builder.stream[String, String]("streams-plaintext-input")
  val wordCounts: KTable[String, Long] = textLines
    .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
    .groupBy((_, word) => word)
    .count()
  wordCounts.toStream.to("streams-wordcount-output")

  val streams: KafkaStreams = new KafkaStreams(builder.build(), config)
  streams.cleanUp()
  streams.start()
  sys.ShutdownHookThread {
    streams.close(10, TimeUnit.SECONDS)
  }

}