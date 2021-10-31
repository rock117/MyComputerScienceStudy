package cs.datastructure.tree

case class Node[T](var data: Int, var left: Node[T] = null, var right: Node[T] = null)
object Node {
    /**
     * 前序遍历
     * @param node
     * @param visit
     * @tparam T
     */
    def preOrder[T](node: Node[T], visit: Node[T] => Unit): Unit ={
        if(node == null){
            return 
        }
        visit(node)
        preOrder(node.left, visit)
        preOrder(node.right, visit)
    }

    /**
     * 中序遍历
     * @param node
     * @param visit
     * @tparam T
     */
    def inOrder[T](node: Node[T], visit: Node[T] => Unit): Unit ={
        if(node == null){
            return
        }
        inOrder(node.left, visit)
        visit(node)
        inOrder(node.right, visit)
    }

    /**
     * 后序遍历
     * @param node
     * @param visit
     * @tparam T
     */
    def postOrder[T](node: Node[T], visit: Node[T] => Unit): Unit ={
        if(node == null){
            return
        }
        postOrder(node.left, visit)
        postOrder(node.right, visit)
        visit(node)
    }
}