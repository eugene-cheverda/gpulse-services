package pulse.services.example.kafka

import org.apache.kafka.clients.producer.RecordMetadata
import pulse.services.example.extensions.Callback

class ProducerOps(self: EnvelopeProducer) {

  import pulse.services.example.extensions.PromiseOps._

  def sendAsync(record: Envelope) = {
    toTwitter[RecordMetadata](p => {
      self.send(record, Callback(p))
    }).map(metadata => {
      ProducerResponse(metadata.topic(), metadata.offset(), metadata.partition())
    })
  }
}
