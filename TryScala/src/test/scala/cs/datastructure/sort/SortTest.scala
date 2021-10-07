package cs.datastructure.sort
import cs.datastructure.list.Merge2SortedListsSolution
import cs.datastructure.list.Merge2SortedListsSpecification.property
import cs.datastructure.sort.SortSpecification.property
import org.junit.Test
import org.junit.Assert.*
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties
class SortTest {

    @Test
    def testBubbleSort(): Unit ={
        val arr = Array(1,2,3,4,1)
        val sort = BubbleSort()
        sort.sort(arr)
        println(arr.mkString(","))
    }
    @Test
    def testInsertSort(): Unit ={
        val arr = Array(4,3,2,1,0)
        val sort = InsertSort()
        sort.sort(arr)
        println(arr.mkString(","))
    }
}


object SortSpecification extends Properties("Sort") {
//    property("Bubble Sort") = checkSort(BubbleSort())
//
//    private def checkSort(sort:Sort) = {
//        forAll { (arr: Array[Int]) =>
//            sort.sort(arr)
//            val res = forAll { (i: Int, j: Int) =>
//                if (i >= 0 && i < arr.length && j >= 0 && j < arr.length) {
//                    if (i < j) arr(i) <= arr(j) else arr(i) >= arr(j)
//                } else {
//                    false
//                }
//            }
//        }}
//
//        }
//    }
}



