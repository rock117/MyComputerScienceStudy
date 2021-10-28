package cs.datastructure

/**
 * https://zhuanlan.zhihu.com/p/43263751
 * 布隆过滤器
 * 特点:
 * 1. 可以判断某个元素肯定不存在
 * 2. 可以判断某个元素可能存在
 *
 * 原理:
 * 布隆过滤器使用位图来存储数据，假设位图是从 0 - n
 * 保存的元素经过 hash后，得到一个数字(该数字为 0 - n)
 *
 * 添加元素时，假设该元素的hash值为 k, 则设置 bit[k] = 1
 * 这样如果元素已经添加，则 bit[k] = 1, 否则 bit[k] = 0
 *
 * 查找元素时,
 * 如果 bit[k] = 0, 则可判断 元素不存在
 * 如果 bit[k] = 1, 由于hash冲突的存在(多个元素的hash值相同)，只能推断 元素可能存在
 *
 * 为了尽可能减弱hash冲突，可以使用多个hash函数，这样同时多个hash冲突的可能性大大降低
 * 此时判断元素是否存在，需要判断 m个 hash对应的 bit[x] 是否都为0(元素不存在)，或都为1(元素可能存在)
 *
 *
 *  如何扩容
 *  如何删除
 */
class BloomFilter[T](hashes: List[Hash[T]], bitNum:Int) {
    val cache: Array[Boolean] = Array.fill(bitNum)(false) // 位图

    def add(t: T): Unit = {
        hashes.foreach{ hash =>
            cache(hash(t)) = true
        }
    }

    def notContains(t: T): Boolean = {
        // 所有hash code 对应的下标，至少有一个为 false
        hashes.map(hash => cache(hash(t))).find(e => e == false).isDefined
    }
    def mayContains(t: T): Boolean = {
        // 所有hash code 对应的下标，都为true
        hashes.map(hash => cache(hash(t))).find(e => e == true).size == hashes.size
    }
}
type Hash[T] = T => Int