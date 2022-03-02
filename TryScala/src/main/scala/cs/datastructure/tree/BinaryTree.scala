package cs.datastructure.tree

/**
 * 二叉树的链式结构
 * @param data
 * @param left
 * @param right
 * @tparam T
 */
case class LinkedNode[T](data: T, left: Node[T], right: Node[T])

/**
 * 二叉树的数组存储表达形式，对于完全二叉树，效率高，非完全二叉树会浪费空间
 * 节点X存储的位置为 i, 则
 * 左子节点在 2i
 * 右子节点在 2i + 1
 * 父节点在  i/2
 *
 * 为了方便父子节点下标计算，根节点存储在下标 1 而不是 0
 * @param nodes
 * @tparam T
 */
case class BTree[T](nodes: Array[T]) {
//    def toBtree():String = {
//        nodes match {
//            case Array() | Array(_) => "()"
//            case Array(_, e0) => s"($e0)"
//            case Array(_, e0, e1) => s"($e0 ($e1))"
//            case Array(_, e0, e1, e2) => s"($e0 ($e1 $e2))"
//            case Array(_, e0, e1, e2, _*) => s"($e0)"
//            case _ => "default"
//        }
//    }
}
// (a (b c))