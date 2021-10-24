package cs.datastructure.sort

/**
 * 时间复杂度 O(nlogn)
 * 最坏情况: O(n2), 数据已经是有序，且哨兵每次都选最后一个元素时
 * 空间复杂度 O(logn), 最坏情况 O(n)
 * 算法步骤
 * 1. 从数列中挑出一个元素，称为 "基准"（pivot）;
 * 2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 *    在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 3. 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 *
 * https://www.runoob.com/w3cnote/quick-sort-2.html
 */
class QuickSort {

    import java.util

    def sort(sourceArray: Array[Int]): Array[Int] = { // 对 arr 进行拷贝，不改变参数内容
       // val arr = util.Arrays.copyOf(sourceArray, sourceArray.length)
      //  quickSort(arr, 0, arr.length - 1)

        val arr = util.Arrays.copyOf(sourceArray, sourceArray.length)
        quickSort(arr, 0, arr.length - 1)
    }

    private def quickSort(arr: Array[Int], left: Int, right: Int): Array[Int] = {
        if (left < right) {
            val partitionIndex = partition(arr, left, right) //设定基准，并按照基准移动数据到各个分区
            quickSort(arr, left, partitionIndex - 1)
            quickSort(arr, partitionIndex + 1, right)
        }
        arr
    }

    private def partition(arr: Array[Int], left: Int, right: Int) = { // 设定基准值（pivot）
        val pivot = left
        var index = pivot + 1
        for (i <- index to right) {
            if (arr(i) < arr(pivot)) {
                swap(arr, i, index)
                index += 1
            }
        }
        swap(arr, pivot, index - 1)
        index - 1
    }

    private def swap(arr: Array[Int], i: Int, j: Int): Unit = {
        val temp = arr(i)
        arr(i) = arr(j)
        arr(j) = temp
    }
}
