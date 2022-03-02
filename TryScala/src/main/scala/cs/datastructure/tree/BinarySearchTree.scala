package cs.datastructure.tree

/**
 * 二叉查找树
 * 假设节点A，有左右节点，则
 * Left(A).value < A.value < Right(A).value
 * 应用:
 * 快速查找最多值，最小值，排序(O(n))
 * 以下代码假设树中没有重复元素
 *
 * 如果支持重复数据，则2种存储方式
 * 1. 节点中的值存储的是链表(而不是单一值)，相同元素的值存储在链表中
 * 2. 每个节点还是只存储单个值，如果有重复数据，则将作为右节点
 * 2.1 查找时，要把所有相等的值找出来
 * 2.2 删除时，要把相同的值删掉
 */
class BinarySearchTree() {
    private var root: Node[Int] = null

    /**
     * 判断查找的元素是不是小于 当前节点值，如果是则在左节点查找
     * 判断查找的元素是不是大于 当前节点值，如果是则在右节点查找
     * 判断查找的元素是不是等于于 当前节点值，如果则表示已经找到，直接返回
     *
     * @param data
     * @return
     */
    def find(data: Int): Node[Int] = {
        var node = root
        while (node != null) {
            if (data < node.data) {
                node = node.left
            } else if (data > node.data) {
                node = node.right
            } else {
                return node
            }
        }
        return null
    }

    /**
     * 树的插入
     * 步骤
     * 1. 如果树为空，直接构建节点，插入结束
     * 2. 如果数据大于当前节点的值，则表明数据应该插入右子树，将当前节点设置为右节点，转向 #1
     * 3. 如果数据小于当前节点的值，则表明数据应该插入左子树，将当前节点设置为左节点，转向 #1
     *
     * @param data
     */
    def insert(data: Int): Unit = {
        if (root == null) {
            root = Node(data)
            return
        }
        var p = root
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = Node(data)
                    return
                }
                p = p.right
            }
            else { // data < p.data
                if (p.left == null) {
                    p.left = Node(data)
                    return
                }
                p = p.left
            }
        }
    }

    /**
     * 树的删除
     * https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
     * https://www.algolist.net/Data_structures/Binary_search_tree/Removal
     * https://www.techiedelight.com/deletion-from-bst/
     *
     * case 1: 待删除的节点没有子节点
     * 将父节点中指向该节点的指针置为null即可
     *
     * case 2: 待删除的节点只有一个子节点
     * 将父节点中指向该节点的指针，调整指向为该节点的子节点
     *
     * case 3: 待删除的节点有2个子节点
     * Find inorder successor of the node. Copy contents of the inorder successor to
     * the node and delete the inorder successor. Note that inorder predecessor can also be used.
     *
     * The important thing to note is, inorder successor is needed only when the right child is not empty.
     * In this particular case, inorder successor can be obtained by finding the minimum value in the right
     * child of the node.
     *
     * Note:
     *  a node’s inorder successor is its right subtree’s leftmost child,
     *  a node’s inorder predecessor is the left subtree’s rightmost child.
     *
     */
    def deleteKey(key: Int): Unit = {
        root = deleteRec(root, key)
    }
    def deleteRec(root: Node[Int], key: Int): Node[Int] = {
        /* Base Case: If the tree is empty */
        if (root == null)
            return root;

        /* Otherwise, recur down the tree */
        if (key < root.data)
            root.left = deleteRec(root.left, key);
        else if (key > root.data)
            root.right = deleteRec(root.right, key);

        // if key is same as root's
        // key, then This is the
        // node to be deleted
        else {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    def minValue(r: Node[Int]): Int = {
        var root = r
        var minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

}

object BinarySearchTree {
    def sort(arr: Array[Int]): List[Int] = {
        val tree = BinarySearchTree()
        arr.foreach(e => tree.insert(e))
        val list = scala.collection.mutable.ListBuffer.empty[Int]
        Node.inOrder(tree.root, node => {
            if (node != null) {
                list.addOne(node.data)
            }
        })
        list.toList
    }
}

@main
def main(): Unit = {
    val list = BinarySearchTree.sort(Array(3, 1, 100, 4, 33))
    println(list)
}