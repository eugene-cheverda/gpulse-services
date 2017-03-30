package pulse.services.example.kafka

import com.twitter.util.Future
import pulse.services.example.extensions._

class AppProducer(properties: Map[String, String]) extends Producer {
  override def session(body: EnvelopeProducer => Future[ProducerResponse]) = {
    use(EnvelopeProducer(properties))(body(_))
  }
}

object AppProducer {
  def apply(properties: Map[String, String]) = new AppProducer(properties)
}