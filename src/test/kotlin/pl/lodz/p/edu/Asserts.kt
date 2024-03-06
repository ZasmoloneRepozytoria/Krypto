package pl.lodz.p.edu

import org.junit.jupiter.api.Assertions.*

fun assertArrayEquals(expected: UByteArray, actual: UByteArray) {
    assertArrayEquals(expected.toByteArray(), actual.toByteArray())
}

fun assertArrayNotEquals(expected: ByteArray, actual: ByteArray) {
    assertFalse(expected.contentEquals(actual))
}

fun assertArrayNotEquals(expected: UByteArray, actual: UByteArray) {
    assertFalse(expected.contentEquals(actual))
}
