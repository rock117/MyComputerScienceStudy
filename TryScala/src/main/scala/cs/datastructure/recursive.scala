package cs.datastructure

import cats.Eval



// https://github.com/robertberry/Functional-Programming-in-Scala-Exercises/blob/master/src/main/scala/com/github/robertberry/fpis/Chapter5.scala

/**
 * 递归数据结构，避免堆栈溢出
 * @tparam A
 */
sealed trait MList[+A] {
    def toList:List[A]
}
case object MNil extends MList[Nothing] {
    override def toList = List.empty[Nothing]
}
case class Cons[+A](head: A, tail: MList[A]) extends MList[A] {
    override def toList: List[A] = {
        this match {
            case Cons(h, t) => h :: t.toList
        }
    }
}
case class Cons2[+A](h: () => A, t: () => MList[A]) extends MList[A] {
    override def toList: List[A] =  this match {
        case Cons2(h, t) => {
            lazy val  nh = h()
            lazy val nt = t()
            nh :: nt.toList
        }
    }
}

object MList {
    def fromList(list: List[Int]): MList[Int] = {
        list match {
            case Nil => MNil
            case h :: t => Cons(h, fromList(t))
        }
    }
}
object MList2 {
    def fromList2(list: List[Int]): MList[Int] = {
        list match {
            case Nil => MNil
            case h :: t => {
                lazy val nH = () => h
                lazy val nT = () => fromList2(t)
                Cons2(nH, nT)
            }
        }
    }


}

//@main
def run(): Unit = {
    val list = List.fill(9999999)(1)
    //  val l = MList.fromList(list) // overflow
    val l2  = MList2.fromList2(list)
    println(l2)
    println("done")

}

object NotStackSafe1 {
    def factorial(n: BigInt): BigInt =
        if(n == 1) n else n * factorial(n - 1)
}
object NotStackSafe2 {
    def factorial(n: BigInt): Eval[BigInt] =
        if(n == 1) {
            Eval.now(n)
        } else {
            factorial(n - 1).map(_ * n)
        }
}
object StackSafe {
    import cats.data.State
    def factorial(n: BigInt): Eval[BigInt] =
        if(n == 1) {
            Eval.now(n)
        } else {
            Eval.defer(factorial(n - 1).map(_ * n))
        }
}
