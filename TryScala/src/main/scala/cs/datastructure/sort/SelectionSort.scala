package cs.datastructure.sort

/**
 * 选择排序
 * 时间复杂度 O(n2), 是否已排序都是一样的时间复杂度
 * 稳定性: 不稳定
 * 选择排序是一种简单直观的排序算法，它的工作原理是每一次从待排序的数据元素中选出最小（最大）的一个元素，存放在序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到全部待排序的数据元素排完。
 * 选择排序是不稳定的排序方法。
 * https://www.jianshu.com/p/51100da14cc2
 */
class SelectionSort extends Sort {
  override def sort(arr: Array[Int]): Unit = {
    var i = 0
    var k = 0
    while (i < arr.length) { // 这一层查找后面最小值的下标
      var j = i + 1
      while (j < arr.length) {
        if (arr(k) > arr(j)) { // 这里改为 < 即为从大到小
          k = j
        }
        j += 1
      }
      // 交换值
      if (i != k) {
        val temp = arr(i)
        arr(i) = arr(k)
        arr(k) = temp
      }

      i += 1
      k = i
    }
  }

  def findMinIndex(from: Int, arr:Array[Int]):Int = {
      var i = from
      var min = arr(i)
      while(i < arr.length) {
        if(arr(i + 1) < min) {
          min = arr(i + 1)
        }
      }
      i
  }
  
  
  def findMin(list: List[Int]): Option[Int] = {
     list match {
      case Nil => None
      case h :: tail => findMin(tail).map(e => if(e < h) e else h)
      case _ => Some(1)
    }
  }
}
