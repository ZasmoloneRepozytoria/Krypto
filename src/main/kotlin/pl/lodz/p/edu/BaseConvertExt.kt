package pl.lodz.p.edu

import java.math.BigInteger
import java.util.*

fun String.base64ToByteArray(): ByteArray {
    return Base64.getDecoder().decode(this)
}

fun String.base64ToUByteArray(): UByteArray {
    return this.base64ToByteArray().toUByteArray()
}

fun ByteArray.toBase64String(): String {
    return Base64.getEncoder().encodeToString(this)
}

fun UByteArray.toBase64String(): String {
    return this.toByteArray().toBase64String()
}

fun String.toUByteArray(): UByteArray {
    return this.toByteArray().toUByteArray()
}

fun BigInteger.toUByteArray(): UByteArray {
    return this.toByteArray().toUByteArray()
}

fun splitUByteArray(input: UByteArray, chunkSize: Int): List<UByteArray> {
    val chunks = mutableListOf<UByteArray>()
    var index = 0
    while (index < input.size) {
        val chunk = input.sliceArray(index until (index + chunkSize).coerceAtMost(input.size))
        chunks.add(chunk)
        index += chunk.size
    }
    return chunks
}
