package cs

import cats.Monad
import cats.Id
import cats.syntax.functor.* // for map
import cats.syntax.flatMap.*
import cats.data.State

def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] = {
  for {
    x <- a
    y <- b
  } yield  x*x + y*y
}
type CalcState[A] = State[List[Int], A]
def evalOne(sym: String): CalcState[Int] =
  sym match {
    case "+" => operator(_ + _)
    case "-" => operator(_ - _)
    case "*" => operator(_ * _)
    case "/" => operator(_ / _)
    case num => operand(num.toInt)
  }

def operand(num: Int): CalcState[Int] =
  State[List[Int], Int] { stack =>
    println(s"operand: $stack")
    (num :: stack, num)
  }

def operator(func: (Int, Int) => Int): CalcState[Int] =
  State[List[Int], Int] {
    case b :: a :: tail   =>
      println(s"operator: ${b :: a :: tail}")
      val ans = func(a, b)
      (ans :: tail, ans)
    case _ =>
      sys.error("Fail!")
  }
object Main extends App {
 // println(evalOne("1").runS(List(1,2)).value)
//  println(evalOne("1").runA(List(1,2)).value)
//
//  val program = for {
//    a <- evalOne("1")
//    b <- evalOne("2")
//    ans <- evalOne("+")
//  } yield ans
//  println(program.runA(Nil).value)

  val p2 = evalOne("1") >> evalOne("2") >> evalOne("+")
 // val p2 = evalOne("1").flatMap(_ => evalOne("2").flatMap(_ => evalOne("+")))
  println(p2.runA(Nil).value)
}
