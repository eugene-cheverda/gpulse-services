package pulse.services.example.extensions

import com.twitter.util.Promise
import org.apache.kafka.clients.producer.{Callback => KCallback, RecordMetadata}
import pulse.services.example.extensions.PromiseOps._

object Callback {
  def apply(p: Promise[RecordMetadata]): KCallback = new KCallback {
    override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
      onComplete(p, metadata, exception)
    }
  }
}
