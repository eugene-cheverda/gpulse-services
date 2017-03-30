package pulse.services.example

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

package object kafka extends ToProducerOps {
  type EnvelopeProducer = KafkaProducer[Int, Array[Byte]]
  type Envelope = ProducerRecord[Int, Array[Byte]]
}

