package cs.fnlearning
import cats.instances.future.*
import cats.instances.list.*
import cats.syntax.traverse.*
import cats.syntax.functor.*
import cats.Applicative
import cats.Id
import cats.Monad
import cats.Functor
import cats.catsInstancesForId

import scala.concurrent.duration.*
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class UptimeService[F[_]: Applicative](client: UptimeClient[F]) {
    def getTotalUptime(hostnames: List[String]): F[Int] =
        hostnames.traverse(client.getUptime).map(_.sum)
}
trait UptimeClient[F[_]] {
    def getUptime(hostname: String): F[Int]
}
//trait RealUptimeClient extends UptimeClient[Future] {
//    def getUptime(hostname: String): Future[Int]
//}
//trait TestUptimeClient extends UptimeClient[Id] {
//    def getUptime(hostname: String): Id[Int]
//}
class TestUptimeClient(hosts: Map[String, Int])
  extends UptimeClient[Id] {
    def getUptime(hostname: String): Int =
        hosts.getOrElse(hostname, 0)
}

class RealUptimeClient(hosts: Map[String, Int])
  extends UptimeClient[Future] {
    def getUptime(hostname: String): Future[Int] =
        Future(hosts.getOrElse(hostname, 0))
}

def testTotalUptime() = {
    val hosts = Map("host1" -> 10, "host2" -> 6)
    val client = new TestUptimeClient(hosts)
    val service = new UptimeService(client)
    val actual = service.getTotalUptime(hosts.keys.toList)
    val expected = hosts.values.sum
    assert(actual == expected)
}

def testTotalUptimeReal() = {
    val hosts = Map("host1" -> 10, "host2" -> 6)
    val client = new RealUptimeClient(hosts)
    val service = new UptimeService(client)
    val actual:Future[Int] = service.getTotalUptime(hosts.keys.toList)
    val expected = hosts.values.sum
    assert(Await.result(actual, 10.seconds) == expected)
}
@main
def main(): Unit ={
    testTotalUptime()
    testTotalUptimeReal()
}