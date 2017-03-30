package pulse.services.example.api.v1.status

import java.io.File
import java.nio.charset.Charset

import com.twitter.util.Future
import io.finch._
import org.apache.kafka.clients.producer.ProducerRecord
import pulse.services.example.Settings
import pulse.services.example.avro.AvroUtils
import pulse.services.example.kafka._
import io.circe.syntax._
import io.circe.generic.auto._
import scala.util.{Failure, Success}

/**
  * Created by Andrew on 16.03.2017.
  */
object StatusApi {
  def statusApi(settings: Settings) = {
    status :+: updateStatus(settings.statusAvroSchema, settings.kafkaServer)
  }

  def status: Endpoint[Status] =
    get("v1" :: "status") {
      Future(Ok(Status("fine")))
    }

  def updateStatus(statusAvroSchema: File, kafkaServerSettings: Map[String, String]): Endpoint[String] = post("v1" :: "status" :: stringBody) {
    s: String => {
      AvroUtils.jsonToAvroBytes(s, statusAvroSchema)
        .flatMap(
          b => AppProducer(kafkaServerSettings).session(_.sendAsync(new ProducerRecord[Int, Array[Byte]]("statuses-topic", b)))
      ) match {
        case Success(r) => r.map(r => Ok(r.asJson.noSpaces))
        case Failure(e) => Future(InternalServerError(new Exception(e.getMessage)))
      }
    }
  }

}
