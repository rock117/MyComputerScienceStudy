package com.rock.datastructure.list

/**
 * 合并2个有序列表
 * <br>
 * https://www.jianshu.com/p/d6db203b271f
 * <br>
 * https://www.huaweicloud.com/articles/a11cc12ffdfcf8d82398fb870a953550.html
 * <br>
 * https://juejin.cn/post/6844903715086794765
 */
object Merge2SortedListsSolution {

  def merge(arr1: Array[Int], arr2: Array[Int]): Array[Int] = {
    val len = arr1.length + arr2.length
    val arr = new Array[Int](len)
    var (i, j, k) = (0, 0, 0)
    while (i < len) { // 从 arr1(j), arr2(k)中取最小值，赋值给 arr(i)。 每次循环 i自增，j/k视情况自增
      val min = if (j >= arr1.length) {
        k += 1
        arr2(k - 1)
      } else if (k >= arr2.length) {
        j += 1
        arr1(j - 1)
      } else if (arr1(j) < arr2(k)) {
        j += 1
        arr1(j - 1)
      } else {
        k += 1
        arr2(k - 1)
      }
      arr(i) = min
      i += 1
    }
    arr
  }

}
