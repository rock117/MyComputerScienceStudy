package cs.datastructure.sort

/**
 * 归并排序
 * 时间复杂度 O(nlogn) 稳定时间复杂度
 * 空间复杂度 O(n + nlogn), n为临时数组大小，logn为递归函数栈空间
 * 稳定排序
 * 
 * 1.将数组分割为2部分(递归分割，直到不能分割)
 * 2.分别对着2个部分排序
 * 3.合并2个排序好的数组
 */
class MergeSort extends Sort {
    def sort(arr: Array[Int]): Unit = {
        val temp = new Array[Int](arr.length) //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr, 0, arr.length - 1, temp)
    }

    private def sort(arr: Array[Int], left: Int, right: Int, temp: Array[Int]): Unit = {
        if (left < right) {
            val mid = (left + right) / 2
            sort(arr, left, mid, temp) //左边归并排序，使得左子序列有序
            sort(arr, mid + 1, right, temp) //右边归并排序，使得右子序列有序
            merge(arr, left, mid, right, temp) //将两个有序子数组合并操作
        }
    }

    private def merge(arr: Array[Int], left: Int, mid: Int, right: Int, temp: Array[Int]): Unit = {
        var i = left; //左序列指针
        var j = mid + 1; //右序列指针
        var t = 0; //临时数组指针
        while (i <= mid && j <= right) {
            if (arr(i) <= arr(j)) {
                t += 1
                i += 1
                temp(t) = arr(i)
            } else {
                t += 1
                j += 1
                temp(t) = arr(j)
            }
        }

        while (i <= mid) { //将左边剩余元素填充进temp中
            t += 1;
            i += 1
            temp(t) = arr(i)
        }
        while (j <= right) { //将右序列剩余元素填充进temp中
            t += 1;
            j += 1
            temp(t) = arr(i)
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        var leftTmp = left
        while (left <= right) {
            leftTmp += 1;
            t += 1
            temp(leftTmp) = temp(t)
        }
    }
}
