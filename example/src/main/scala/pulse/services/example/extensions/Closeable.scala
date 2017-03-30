package pulse.services.example.extensions

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import org.apache.kafka.clients.producer.KafkaProducer

trait Managed[A] {
  def close(instance: A): Unit
}

object Managed {
  implicit object ManagedByteArrayOutputStream extends Managed[ByteArrayOutputStream] {
    override def close(instance: ByteArrayOutputStream): Unit = instance.close()
  }
  implicit object ManagedByteArrayInputStream extends Managed[ByteArrayInputStream] {
    override def close(instance: ByteArrayInputStream): Unit = instance.close()
  }

  implicit object ManagedKafkaProducer extends Managed[KafkaProducer[Int, Array[Byte]]] {
    override def close(instance: KafkaProducer[Int, Array[Byte]]): Unit = instance.close()
  }
}