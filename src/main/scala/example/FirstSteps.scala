package example

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{ Flow, Sink, Source }

object FirstSteps extends App {
  implicit val system       = ActorSystem("FirstSteps")
  implicit val materializer = ActorMaterializer()

  val source = Source(1 to 10)
  val sink   = Sink.foreach(println)
  val graph  = source.to(sink)
  graph.run()
}
