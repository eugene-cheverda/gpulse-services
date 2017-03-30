package pulse.services.example.kafka

case class ProducerResponse(topic: String, offset: Long, partition: Int)
