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
 *
 * 应用例子:
 * 条件: 有10个 日志文件，每个300M, 每个文件中的日志都是按照时间排序。
 * 需求: 将这个10个日志文件合并，合并后的文件日志也是按时间排序
 * 限制: 只有1G内存
 *
 * 方案: 设置一个 10元素的数组，每个元素存储一个日志文件的一条记录，从数组中找出最小时间的日志，
 * 将该日志写入目标文件；再读取之前最小时间日志对应日志下一条记录，加载入数组中，一直重复以上步骤，
 * 直到所有日志写入最终文件。
 * 
 * 优化: 为了避免每次读取/写入磁盘引起的IO低效率，可以为每个文件设立一个读缓存区，再为最终文件设立
 * 一个写缓冲器。当读缓冲器没有数据时，读取文件将其填满，以后每次读文件，先读取缓冲器的；写入文件时，
 * 先写入写缓冲器，当写缓冲区满时，再写入磁盘
 * 
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
