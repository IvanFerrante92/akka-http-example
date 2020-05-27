import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import shapeless.Succ
import scala.util.Success
import scala.util.Failure
import scala.concurrent.duration._
import scala.concurrent.Await

object Main extends App {
    
    val host = "0.0.0.0"
    val port = 9000

    implicit val system: ActorSystem = ActorSystem("todoapi") //used to create Actor
    implicit val materializer: ActorMaterializer = ActorMaterializer() //used to create Stream
    import system.dispatcher

    import akka.http.scaladsl.server.Directives._
    // path and get method
    def route = path("hello") {
        get {
            complete("Hello world!")
        }
    }

    //binding of a server
    val binding = Http().bindAndHandle(route, host, port)
    binding.onComplete {
        case Success(_) => ("Success!")
        case Failure(exception) => (s"Failed: ${exception.getMessage()}")
    }
    
Await.result(binding, 3.seconds)
}