package com.rock

import com.rock.nat.Nat


object Main extends App {
//  val (n1:Nat, n2:Nat) = (Nat.fromInt(12).get, Nat.fromInt(10).get)
//  val nat:Nat =  Nat.fromInt(100).get.add(Nat.fromInt(200).get)
//  println(nat)
//  println(n1 mul n2)
  var rng:RNG = SimpleRNG(0)
  var i = 0
  while(i < 100) {
    val (next, nextRng) = rng.nextInt
    println(s"$i ${(next, nextRng)}")
    rng = nextRng
    i+=1
  }
}
trait RNG {
  def nextInt: (Int, RNG)
}
case class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0XBL) & 0XFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }
}
