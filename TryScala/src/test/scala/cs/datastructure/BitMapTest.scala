package cs.datastructure

import org.junit.Test
import org.junit.Assert
class BitMapTest {
    @Test
    def testBitMap(): Unit ={
        val bitMap = BitMap()
        bitMap.set(3)
        Assert.assertTrue(bitMap.contains(3))
        Assert.assertFalse(bitMap.contains(4))
    }

    @Test
    def testBitSort(): Unit ={
        val bitMap = BitMap()
        val arr = BitMap.sort(Array(5, 2, 6, 0, 1))
        Assert.assertEquals("0, 1, 2, 5, 6", arr.mkString(", "))
    }
}
