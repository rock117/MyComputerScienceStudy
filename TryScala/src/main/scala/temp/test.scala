package temp

import cats.data.State
import cats.effect.IO

import scala.util.Random
import cats.syntax.flatMap

import java.util.UUID
type Stack = List[Int]


@main
def main(): Unit = {
    val list = generate(
      List(randomId(), randomId(), randomId()),
      List(randomId(), randomId(), randomId(), randomId(), randomId())
    )
  list.foreach(println(_))
}
def randomId():String = UUID.randomUUID().toString()
def generate(users: List[String], secIds: List[String]):List[(String, String, Int)] = {
  for {
    user <- users
    secId <- secIds
  } yield (user, secId, random(0, 100))
}
def randomE[T](list: List[T]): T = list(random(0, list.length - 1))
def random(from:Int, to:Int):Int = from + (Math.abs(Random.nextInt()) % to.+(1))
def randomStream(from:Int, to:Int):Stream[Int] = random(from, to) #:: randomStream(from, to)
def randomN(from:Int, to:Int, n:Int) = randomStream(from, to).take(n).toList

def randomWithWeight(weight: Double, from:Int, to:Int, n:Int):Int = {

  1

}



case class Animal(n: Int) {
  def +:(a: Int) = Animal(this.n + a)
}
