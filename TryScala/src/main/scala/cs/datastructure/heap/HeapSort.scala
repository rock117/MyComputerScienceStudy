package cs.datastructure.heap

/**
 * https://www.javatpoint.com/heap-sort
 * https://www.cnblogs.com/chengxiao/p/6129630.html
 *
 * 堆排序
 * 原地排序
 * 时间复杂度 O(nlogn)
 * 空间复杂度 O(1)
 * 不稳定排序
 *
 *
 * 分2个步骤，建堆，和排序
 * 1. 建堆
 * 2. 排序 -> 删除根节点 -> 堆化
 * Heap sort basically recursively performs two main operations -
 * Build a heap H, using the elements of array.
 * Repeatedly delete the root element of the heap formed in 1st phase.
 *
 */
object HeapSort {

    /**
     * function to heapify a subtree.
     *  Here 'i' is the index of root node in array a[], and 'n' is the size of heap.
     * @param a
     * @param n
     * @param i
     */
    def heapify(a: Array[Int], n: Int, i: Int): Unit = {
        var largest = i // Initialize largest as root
        val left = 2 * i + 1 // left child
        val right = 2 * i + 2 // right child
        // If left child is larger than root
        if (left < n && a(left) > a(largest)) largest = left
        // If right child is larger than root
        if (right < n && a(right) > a(largest)) largest = right
        // If root is not largest
        if (largest != i) { // swap a[i] with a[largest]
            val temp = a(i)
            a(i) = a(largest)
            a(largest) = temp
            heapify(a, n, largest)
        }
    }

    /*Function to implement the heap sort*/
    def heapSort(a: Array[Int], n: Int): Unit = {
        for (i <- n / 2 - 1 to 0 by -1) {
            heapify(a, n, i)
        }
        // One by one extract an element from heap
        for (i <- n - 1 to 0 by -1) {
            /* Move current root element to end*/
            // swap a[0] with a[i]
            val temp = a(0)
            a(0) = a(i)
            a(i) = temp
            heapify(a, i, 0)
        }
    }

    /* function to print the array elements */
    def printArr(a: Array[Int], n: Int): Unit = {
        for (i <- 0 until n) {
            System.out.print(a(i) + " ")
        }
    }
}
@main
def main(): Unit ={
    val a = Array(45, 7, 20, 40, 25, 23, -2)
    val n = a.length
    System.out.print("Before sorting array elements are - \n")
    HeapSort.printArr(a, n)
    HeapSort.heapSort(a, n)
    System.out.print("\nAfter sorting array elements are - \n")
    HeapSort.printArr(a, n)
}