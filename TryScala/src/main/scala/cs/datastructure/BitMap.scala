package cs.datastructure

/**
 * 使用位图来存储数据
 * 假设 只存储 8个数(0 - 7)，则只需要 一个字节(8比特)，就可以存储。
 * " =>" 在下面表示 代表， 比如 bit[0] => 0, 表示 "位0 代表数字0",  bit[1] => 1, 表示 "位1 代表数字1"
 * 位图可视为数组，则 bit[0] => 0, bit[1] => 1, .. bit[7] => 7
 * 如果 bit[n] = 1 则表示位图中存在数字n, bit[n] = 0,表示数字n在位图中不存在
 *
 *
 * 如果要存储 0 - 100的数字，则需要 111个比特，13个字节(? 代表未定义)
 * bit[0] => 0, bit[1] => 1, .. bit[100] => 100, bit[101] => ?, .. bit[104] => ?
 * 其中 bit[101..]后不代表任何数字
 *
 * 可以使用 字节数组来代替比特位，比如2个字节 来表示 16位，可用于表示数字 0 - 15
 *
 * 如何使用位图来排序(数字不能重复)?
 * 假设待排序的数字位 0 - n,
 * 1. 则生成 n个比特位的 BitMap与之对应(实际的比特位要大于n, 比如9个数字，就需要 2字节 16位)
 * 2. 将这 n个数字对应的比特位 置为 1
 * 3. 从 位置 0 - n,  判断比特位是否为1，是则输出
 * 4. 输出列表即是排好序的结果
 *
 *
 */
class BitMap {
    val cache: Array[Boolean] = Array.fill(8)(false) // 内部比特位表示
    /**
     * Solution: 将 第 n 位比特 置1
     * @param n
     */
    def set(n: Int): Unit = {
        cache(n) = true
    }

    /**
     *  Solution: 判断第 n 位 比特是否是 1
     * @param n
     * @return
     */
    def contains(n: Int): Boolean = {
        cache(n)
    }

    def filterExistElementIndex():List[Int] = cache.zipWithIndex.filter((e, i) => e).map((e, i) => i).toList

}
object BitMap {
    def sort(arr: Array[Int]): Array[Int] = {
        val newArr = List.empty[Int]
        val bitMap = BitMap()
        arr.foreach(e => bitMap.set(e))
        bitMap.filterExistElementIndex().toArray
    }
}

 