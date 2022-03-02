package cs.datastructure.heap

/**
 * https://zhuanlan.zhihu.com/p/63089552
 *
 * 堆是个完全二叉树，堆中的每个节点都大于等于(小于等于)他的左右节点，使用数组存储堆，0位置不存储数据
 * 以下基于大根堆(堆顶的元素最大)
 *
 * 堆化(插入/删除)时间复杂度 O(logn)
 *
 * 堆排序: 不断的删除堆顶元素
 *
 *
 * Top K 问题
 * 在n个数据中存储 top K, 可以用堆来存储(n > k)
 * steps:
 * 1. 如果堆中数据小于k个，则直接插入堆
 * 2. 如果大于等于k个，则如果当前元素大于堆顶，则直接替换堆顶元素，否则不做任何动作
 *
 * 求中位数
 * 使用2个堆，大根堆，小根堆来分别存储中位数的两边值，
 * 大根堆存储前边数据小的部分，小根堆存储后半部大的数据
 * 则大根堆堆顶就是中位数
 *
 */
class Heap(capacity: Int) {
    private var arr: Array[Int] = Array.fill(capacity + 1)(0)
    private var elementNum: Int = 0

    def data():Array[Int] = arr.takeRight(arr.length - 1)

    def isEmpty():Boolean =  elementNum == 0
    /**
     * 插入(自下而上堆化 heapify)
     * 将节点插入数组末尾，判断元素是否大于父节点，如果是，则与父节点交换位置，直到根节点
     *
     * @param e
     */
    def insert(e: Int): Unit = {
        if (elementNum >= capacity) {
            return
        }
        elementNum += 1
        arr(elementNum) = e
        var i = elementNum
        while (i / 2 > 0 && arr(i) > arr(i / 2)) {
            swap(arr, i, i / 2)
            i = i / 2
        }
    }

    /**
     * 删除堆顶元素(自上而下堆化)
     * 1. 将末尾元素移到堆顶
     * 2. 再用堆顶元素和左右节点的最大节点比较，如果元素小于子节点，则交换
     * 3. 继续与子节点比较，直到到达树叶节点
     */
    def removeMax(): Option[Int] = {
        if (elementNum == 0) {
            None
        } else {
            val max = arr(1)
            arr(1) = arr(elementNum)
            elementNum -= 1
            heapify(arr, elementNum, 1)
            Some(max)
        }
    }

    private def heapify(arr: Array[Int], ii: Int, j: Int): Unit = {
        var i = ii
        while (true) {
            var maxPos = i
            if (i * 2 <= capacity && arr(i) < arr(i * 2)) maxPos = i * 2
            if (i * 2 + 1 <= capacity && arr(maxPos) < arr(i * 2 + 1)) maxPos = i * 2 + 1
            if (maxPos == i) return
            swap(arr, i, maxPos)
            i = maxPos
        }
    }

    /**
     * 删除指定位置的元素
     * 1. 将要删除的元素与数组尾部元素交换
     * 2. 如果要删除的元素比尾元素大，则自上而下堆化
     * 3. 如果要删除的元素比尾元素小，则自下而上堆化
     * 4. 如果要删除的元素等于尾元素，则不操作
     *
     * @param i
     */
    private def remove(i: Int): Int = ???

    private def swap(arr: Array[Int], i: Int, j: Int): Unit = {
        var tmp = arr(i)
        arr(i) = arr(j)
        arr(j) = tmp
    }
}

object Heap extends App {
    var arr = Array(3, 100, 1, -1, 0, 33, 5)
    val heap = buildHeap(arr)

    removeAll(heap)

    def buildHeap(arr: Array[Int]): Heap = {
        val heap = Heap(arr.length)
        arr.foreach(e => heap.insert(e))
        heap
    }

    def removeAll(heap:Heap): Unit = {
        var list = List.empty[Int]
        while (!heap.isEmpty()) {
            list = heap.removeMax().get :: list
        }
        println(list.reverse)
    }

}