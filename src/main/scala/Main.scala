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

    // Instance of todos object, server and router
    val todoRepository = new InMemoryTodoRepository(Seq(
        Todo("1", "Buy coffee", "Remember to buy coffee", false),
        Todo("2", "Pay recepits", "Remember to pay the telephone bill", false),
        Todo("3", "Go to market", "We need more food!", true)
    ))
    val router = new TodoRouter(todoRepository)
    val server = new Server(router, host, port)


    //binding of a server
    val binding = server.bind()
    binding.onComplete {
        case Success(_) => ("Success!")
        case Failure(exception) => (s"Failed: ${exception.getMessage()}")
    }
    
Await.result(binding, 3.seconds)
}