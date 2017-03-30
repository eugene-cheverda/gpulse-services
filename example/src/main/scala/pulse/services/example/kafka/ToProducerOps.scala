package pulse.services.example.kafka

trait ToProducerOps {
  implicit def wrap(producer: EnvelopeProducer): ProducerOps = new ProducerOps(producer)
}
