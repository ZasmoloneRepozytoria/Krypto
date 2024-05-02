package pl.lodz.p.edu

import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class ArraySplittingTest {
    @Test
    fun `concated sub arrays should be same as original`() {
        val array = ubyteArrayOf(0u, 1u, 2u, 3u, 4u, 5u, 6u, 7u, 8u, 9u)
        val splittedArray = splitUByteArray(array, 3)
        var concatedArray = UByteArray(0)
        for (subArray in splittedArray) {
            concatedArray += subArray
        }
        assertArrayEquals(array, concatedArray)
    }

    @Test
    fun `sub arrays should have correct size`() {
        val array = ubyteArrayOf(0u, 1u, 2u, 3u, 4u, 5u, 6u, 7u, 8u, 9u)
        val splittedArray = splitUByteArray(array, 3)
        for(i in splittedArray.indices) {
            if (i != splittedArray.size-1)
                assertEquals(3, splittedArray[i].size)
            else
                assertEquals(array.size.mod(3), splittedArray[i].size)
        }
    }

    @Test
    fun `BigInt from array test`() {
        val array = BigInteger("512").toUByteArray() + BigInteger("512").toUByteArray()
        val splittedArray = splitUByteArrayToBigIntegers(array, 2)
        for(i in splittedArray.indices) {
            assertEquals(BigInteger("512"), BigInteger(splittedArray[i].toByteArray()))
        }
    }
}