package cs.datastructure.sort

/**
 * https://www.runoob.com/w3cnote/insertion-sort.html
 * http://c.biancheng.net/algorithm/insertion-sort.html
 * 从左到右，依次抽取一个元素，插入到已排序好的数组中。起始时，待排序的数组是第一个元素
 * 待插入的元素从右往左与排了序的元素比较
 */
class InsertSort extends Sort {
    def sort(arr: Array[Int]):Unit = {
        println("Before sort:"+arr.mkString(","))
        for(i <- 1 until arr.length) {
            val insertElem = arr(i)
            //记录目标元素所在的位置
            var position = i
            //循环有2个目的
            // 找到一个比目标元素小的元素，目标元素插入到该元素之后的位置
            // 记下此元素的下标
            while(position > 0 && arr(position-1) > insertElem){
                arr(position) = arr(position-1)
                position = position - 1
            }
            if(position != i) {
                arr(position) = insertElem
            }
            println(arr.mkString(","))
        }
    }
}
