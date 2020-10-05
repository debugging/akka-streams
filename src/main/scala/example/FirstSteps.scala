package example

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{ Flow, Sink, Source }

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object FirstSteps extends App {
  implicit val system       = ActorSystem("FirstSteps")
  implicit val materializer = ActorMaterializer()

  val source = Source(1 to 10)
  val sink   = Sink.foreach(println)
  val graph  = source.to(sink)
  //graph.run()

  // sources
  val finiteSource   = Source.single(123)
  val finiteSource2  = Source(List(1, 2, 3))
  val emptySource    = Source.empty[Int]
  val infiniteSource = Source(Stream.from(100))
  val futureSource   = Source.fromFuture(Future(500))

  // flows
  val mapFlow  = Flow[Int].map(_ * 2)
  val takeFlow = Flow[Int].take(5)
  // drop, filter

  // sinks
  val ignoreSink  = Sink.ignore
  val foreachSink = Sink.foreach(println)
  val headSink    = Sink.head[Int]
  val foldSink    = Sink.fold[Int, Int](0)((a, b) => a + b)

  // graph
  val doubleFlowGraph = source.via(mapFlow).via(takeFlow).to(sink) // runs on same actor
  //doubleFlowGraph.run()

  // one liner :)
  Source(List("Raptors", "Lakers", "Clippers", "Houston", "Nets"))
    .via(Flow[String].filter(_.length > 5))
    .via(Flow[String].take(3))
    .to(Sink.foreach(println))
    .run()

  println("app end...")
}
