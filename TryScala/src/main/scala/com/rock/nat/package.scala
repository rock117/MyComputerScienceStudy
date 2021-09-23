package com.rock

import com.rock.nat.Nat.fromInt

package object nat {
  sealed abstract class Nat {
    def toInt: Int

    override def toString: String = s"${toInt}"

    def flip: Nat = fromInt(toInt * -1).get

    def add(other: Nat): Nat = {
      this match {
        case Z => other
        case S(p) => S(p.add(other))
      }
    }

    def substrat(other: Nat): Nat = add(other.flip)
    def mul(other: Nat):Nat = {
      this match {
        case Z => zero
        case S(p) => other add (p mul other)
      }
    }
  }

  object Nat {
    def fromInt(i: Int): Option[Nat] = {
      i match {
        case x if x < 0 => None
        case 0 => Some(zero)
        case 1 => Some(one)
        case 2 => Some(two)
        case 3 => Some(three)
        case 4 => Some(fourth)
        case 5 => Some(five)
        case 6 => Some(six)
        case 7 => Some(seven)
        case 8 => Some(eight)
        case 9 => Some(nine)
        case _ => fromInt(i - 1).map(prvNat => S(prvNat)) // TODO overflow
      }
    }
  }

  case object Z extends Nat {
    override def toInt: Int = 0
  }

  case class S(n: Nat) extends Nat {
    override def toInt: Int = n.toInt + 1
  }

  val zero = Z
  val one = S(zero)
  val two = S(one)
  val three = S(two)
  val fourth = S(three)
  val five = S(fourth)
  val six = S(five)
  val seven = S(six)
  val eight = S(seven)
  val nine = S(eight)

}
