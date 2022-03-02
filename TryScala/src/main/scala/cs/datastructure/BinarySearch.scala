package cs.datastructure

/**
 * 二分查找
 * 时间复杂度 O(logn)
 * 适用于在已排好序的数组中查找元素
 *
 * 适用条件: 已排好序的数组，不能是链表，因链表随机访问的时间是 O(n)
 * 数据量太小不适合二分查找(与顺序查找差别不大)，如果数据比较是耗时操作，则可以
 * 数据量太大也不适合，因为用数组存储数据，内存必须是连续的，而大的连续内存是个珍贵的资源
 *
 * 例子:
 * 如何快速查找 1000万的整数，要求内存尽量省
 * 将数据存储于数组中，排序后，使用二分查找，如果用哈希表或者二叉查找树，则会额外的占用很多内存
 *
 * 例子2:
 * 如何求解一个数 x 的平方根
 * 解法:
 * 先求解整数部分，使用二分查找，在 0-x中查找平方值小于等于 x的数，
 * 接着同样的方法一位一位的求解小数部分
 */
class BinarySearch {
    def search(arr: Array[Int], value: Int): Int = {
        var low = 0
        var high = arr.length
        while (low <= high) {
            val mid = (low + high) / 2
            if (arr(mid) == value) {
                return mid
            } else if (arr(mid) < value) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }
        -1
    }
}
