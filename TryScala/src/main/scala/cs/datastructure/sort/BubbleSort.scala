package cs.datastructure.sort

import scala.util.control.Breaks

/**
 * 冒泡排序
 * 稳定性: 稳定
 * 时间复杂度 O(n2),如果是已排好序，则是O(n)
 */
class BubbleSort extends Sort{
    def sort(arr: Array[Int]):Unit = {
        var i = 0
        var go = true
        while (i < arr.length && go) { // 当某次遍历没有元素交互时，表示数组已有序，提前结束
            var hasSiwtchElement = false
            for(j <- 0 until arr.length - i - 1) {
                 if(arr(j) > arr(j + 1)){
                     val tmp = arr(j + 1)
                     arr(j + 1) = arr(j)
                     arr(j) = tmp
                     hasSiwtchElement = true
                 }
            }
            go = hasSiwtchElement
            i += 1
        }
    }
}
