package cs.miscellaneous

/**
 * https://medium.com/system-design-blog/consistent-hashing-b9134c8a9062
 * https://crossoverjie.top/2018/01/08/Consistent-Hash/
 * https://segmentfault.com/a/1190000021199728
 * https://zhuanlan.zhihu.com/p/98030096
 * https://juejin.cn/post/6844903750860013576
 */
import cs.miscellaneous.ConsistentHashing.{getHash, getServer}

import java.util

@main
def main(): Unit ={
    val keys = Array("semlinker", "kakuqo", "fer")
    for (i <- 0 until keys.length) {
        println("[" + keys(i) + "]的hash值为" + getHash(keys(i)) + ", 被路由到结点[" + getServer(keys(i)) + "]")
    }
}

object ConsistentHashing {
    private val servers = Array("192.168.0.1:8888", "192.168.0.2:8888", "192.168.0.3:8888")
    //key表示服务器的hash值，value表示服务器//key表示服务器的hash值，value表示服务器
    private val sortedMap = new util.TreeMap[Integer, String]

    private def init(): Unit ={
        var i = 0
        while (i < servers.length) {
            val hash = getHash(servers(i))
            println("[" + servers(i) + "]加入集合中, 其Hash值为" + hash)
            sortedMap.put(hash, servers(i))
            i += 1
        }
    }
    //得到应当路由到的结点
    private def getServer(key: String) = { //得到该key的hash值
        val hash = getHash(key)
        //得到大于该Hash值的所有Map
        val subMap = sortedMap.tailMap(hash)
        if (subMap.isEmpty) { //如果没有比该key的hash值大的，则从第一个node开始
            val i = sortedMap.firstKey
            //返回对应的服务器
            sortedMap.get(i)
        }
        else { //第一个Key就是顺时针过去离node最近的那个结点
            val i = subMap.firstKey
            subMap.get(i)
        }
    }
    //使用FNV1_32_HASH算法计算服务器的Hash值
    private def getHash(str: String) = {
        val p = 16777619
        var hash = 2166136261L.toInt
        for (i <- 0 until str.length) {
            hash = (hash ^ str.charAt(i)) * p
        }
        hash += hash << 13
        hash ^= hash >> 7
        hash += hash << 3
        hash ^= hash >> 17
        hash += hash << 5
        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) hash = Math.abs(hash)
        hash
    }
}
