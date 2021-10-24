package cs.datastructure.sort

/**
 * 插入排序
 * 稳定性: 稳定
 * 时间复杂度 O(n2), 如果是已排好序，则是O(n)
 * https://www.runoob.com/w3cnote/insertion-sort.html
 * http://c.biancheng.net/algorithm/insertion-sort.html
 * 从左到右，依次抽取一个元素，插入到已排序好的数组中。起始时，待排序的数组是第一个元素
 * 待插入的元素从右往左与排了序的元素比较
 * 
 * 
 */
class InsertSort extends Sort {
    def sort(arr: Array[Int]):Unit = {
        println("Before sort:"+arr.mkString(","))
        for(i <- 1 until arr.length) {
            val insertElem = arr(i)
            //记录目标元素所在的位置
            var position = i
            // 由右到左移动元素，直到当前位置的值不大于插入值
            while(position > 0 && arr(position-1) > insertElem){
                arr(position) = arr(position-1)
                position = position - 1
                println(arr.mkString(","))
            }
            if(position != i) {
                arr(position) = insertElem
            }
            println(arr.mkString(","))
        }
    }
}
