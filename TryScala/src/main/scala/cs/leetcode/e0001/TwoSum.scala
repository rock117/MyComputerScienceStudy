package cs.leetcode.e0001

/**
 * https://books.halfrost.com/leetcode/ChapterFour/0001~0099/0001.Two-Sum/
 * 返回2数在数组中的index, 该2数的和为target
 */
object TwoSum extends App {
    def twoSum(num: Array[Int], target: Int): Option[(Int, Int)] = {
        val map = scala.collection.mutable.Map.empty[Int, Int]
        for (i <- 0 to num.length) {
            val another = target - num(i)
            if (map.contains(another)) {
                return Some((map(another), i))
            }
            else {
                map.put(num(i), i)
            }
        }
        Some((0, 1))
    }

    println(twoSum(Array(1, 2, 5), 7))


}
