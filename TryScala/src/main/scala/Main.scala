@main
def main(): Unit ={
  val list = List(1,2)
   println(s"min is ${getMin(Nil)}")
}
def getMin(list: List[Int]): Int ={
  list match {
    case Nil => println("nil"); -1
    case h :: Nil => println("single element"); h
    case h :: tail => println("normal list"); Math.min(h, getMin(tail))
  }
}
class Eval[T](run: => T){
  def eval:T = run
  def flatMap[F](f: T => Eval[F]):Eval[F] = {
    f(eval)
  }
}

