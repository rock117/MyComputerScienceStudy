package cs.datastructure.sort.linearsort
import cs.datastructure.sort.Sort

/**
 * 
 * O(nlog(n/m), n个数据，m个桶，当m接近于n是， log(n/m)=1, 进而 O(nlog(n/m)) = O(n)
 * 适用条件:
 * 首先，待排序数据容易划分为 m 个桶，且桶之间有着天然的大小顺序。这样每个桶数据排序完成后，
 * 桶与桶之间的数据就不需要再排序
 *
 * 其次，桶之间的数据分布比较均匀，极端情况下所有的数据都位于一个桶，则排序退化为 O(nlogn)
 *
 * 桶排序适合用于外部排序
 * 1. 假设有10G的订单文件，想根据订单金额排序，订单金额大小为 1 - 100000，则可将其划分为1000个桶，
 * 2. 桶区间为(1, 1000), (1001, 2000), ..
 * 3. 将每个区间载入内存排序，排好序后，按照写入文件(文件按桶编号命名)
 * 4. 按照编号文件读取文件，再合并到最终文件
 */
class BucketSort extends LinearSort{
    override def sort(arr: Array[Int]): Unit = ???
}
