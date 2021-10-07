package cs.compiler

@main
def main(): Unit = {
  val s = DFA.test
  println(s"s is $s")
}


case class DFA()

object DFA {
  def transfer[C](current: State, c: C): State = ???

  def test = {
    val s = State2.S1
    val res = s match {
      case State2.S1 => "s1"
      case State2.S2 => "s2"
      case _ => "none"
    }
    res
  }
}

class State

enum State2 {
  case S1
  case S2
  case S3
}

enum Color(val rgb: Int) {
  case Red extends Color(0xFF0000)
  case Green extends Color(0x00FF00)
  case Blue extends Color(0x0000FF)
}