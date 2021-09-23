package com.rock.datastructure.list

//import org.scalatest.FunSuite
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

//class Merge2SortedListsTest extends FunSuite {
//  test("An empty List should have size 0") {
//    assert(List.empty.size == 0)
//  }
//}

//object StringSpecification extends Properties("String") {
//  property("startsWith") = forAll { (a: String, b: String) =>
//    (a+b).startsWith(a)
//  }
//}



object Merge2SortedListsSpecification extends Properties("Merge2SortedLists") {

  /**
   * 如何将 Merge2SortedLists.merge的返回值类型 编码为某种类型，以便使用其为参数做断言
   * 或者 指定某个值，其为Merge2SortedLists.merge所生成
   */
//  property("Merge2SortedLists") = forAll { (arr1: Array[Int], arr2: Array[Int]) =>
//    Merge2SortedLists.merge(arr1, arr2).length == arr1.length + arr2.length
//  }

  property("Merge2SortedLists") = forAll { (arr1: Array[Int], arr2: Array[Int]) =>
    val arr3 = Merge2SortedListsSolution.merge(arr1, arr2)
    forAll { (i:Int, j:Int) =>
        if(i >= 0 && i < arr3.length && j>=0 && j < arr3.length) {
          if(i < j) arr3(i) <= arr3(j) else arr3(i) >= arr3(j)
        } else {
          false
        }

    }
  }


}
