package pulse.services.example.kafka

import scala.collection.JavaConversions._

object EnvelopeProducer {
  def apply(properties: Map[String, String]): EnvelopeProducer = new EnvelopeProducer(properties)
}
