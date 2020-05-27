import akka.actor.ActorSystem
import scala.concurrent.ExecutionContext
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.Http
import scala.concurrent.Future



class Server(router: Router, host: String, port: Int)(implicit system: ActorSystem, ex: ExecutionContext, mat: ActorMaterializer) {

    def bind(): Future[ServerBinding] = Http().bindAndHandle(router.route, host, port)


}