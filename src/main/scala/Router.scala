import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives
import scala.util.Success
import scala.util.Failure
import akka.http.scaladsl.model.StatusCodes

trait Router {
    def route: Route
}

class TodoRouter(todoRepository: TodoRepository) extends Router with Directives{

    import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
    import io.circe.generic.auto._

    // REST Endpoint that recall the Todo object function
    override def route: Route = pathPrefix(pm = "todos") {
        pathEndOrSingleSlash{
            get{
                onComplete(todoRepository.all()) {
                    case Success(todos) => complete(todos)
                    case Failure(error) => 
                        println(error.getMessage)
                        complete(StatusCodes.InternalServerError) 
                }
          }
      } ~ path(pm = "done") {
          get{
              complete(todoRepository.done())
          }
      } ~ path(pm = "pending") {
          get {
              complete(todoRepository.pending())
          }
      }
  }


}