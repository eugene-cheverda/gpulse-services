package pulse.services.example

import java.io.File

import com.typesafe.config.Config


/**
  * Created by Andrew on 19.03.2017.
  */

trait Settings {

  val threadPoolMaxCount: Int

  val useTaskApi: Boolean

  val statusAvroSchema: File

  val kafkaServer: Map[String, String]
}



object Settings {
  def apply(config: Config, cliSettings: CliParameters) = new Settings {

    val threadPoolMaxCount: Int = config.getInt("threadpool.maxCount")

    val useTaskApi = cliSettings.useTaskApi

    val statusAvroSchema = cliSettings.statusAvroSchema

    val kafkaServer = Map(
      "bootstrap.servers" -> config.getString("kafka.bootstrap.servers"),
      "acks" -> config.getString("kafka.acks"),
      "retries" -> config.getString("kafka.retries"),
      "batch.size" -> config.getString("kafka.batch.size"),
      "linger.ms" -> config.getString("kafka.linger.ms"),
      "buffer.memory" -> config.getString("kafka.buffer.memory"),
      "key.serializer" -> config.getString("kafka.key.serializer"),
      "value.serializer" -> config.getString("kafka.value.serializer")
    )
  }
}
