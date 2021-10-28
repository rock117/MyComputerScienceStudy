package cs.datastructure

import org.junit.{Assert, Test}

class BloomFilterTest {
    @Test
    def testBitMap(): Unit ={
        val bf = BloomFilter(List((n:Int) => n % 100), 100)
        Assert.assertTrue(bf.notContains(0))
        bf.add(0)
        Assert.assertFalse(bf.notContains(0))
        Assert.assertTrue(bf.mayContains(0))
    }
}
