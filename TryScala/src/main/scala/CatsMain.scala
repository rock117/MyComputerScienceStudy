import cats.effect.*
import java.io.{File, FileInputStream}
import cats.effect.{IO, IOApp, Resource}
import cats.implicits.*
import cats.effect.*
import cats.effect.std.CountDownLatch
import cats.effect.unsafe.implicits.global
import cats.effect.IO
import cats.effect.kernel.Outcome.Succeeded

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

implicit val MyMain:ExecutionContext = ExecutionContext.global
val BlockingFileIO = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

/**
 * https://timwspence.github.io/blog/posts/2021-01-12-threading-best-practices-cats-effect.html
 */
object CatsMain extends IOApp.Simple {
  override def run: IO[Unit] = IO{println("hello world ooo")}.void
}