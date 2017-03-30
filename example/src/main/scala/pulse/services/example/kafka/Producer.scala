package pulse.services.example.kafka

import com.twitter.util.Future
import scala.util.Try

trait Producer {
  def session(body: EnvelopeProducer => Future[ProducerResponse]) : Try[Future[ProducerResponse]]
}






