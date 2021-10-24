package temp
@main
def main2(): Unit ={
    println(Math.pow(9, 1/2))
}
type Point = List[Double]

def distance(p1: Point, p2: Point): Unit ={
    val len = p1.size
    Math.pow(p1.zipWithIndex.map(f => Math.pow((f._1 - p2(f._2)), 2)).sum, 1/2)
}

