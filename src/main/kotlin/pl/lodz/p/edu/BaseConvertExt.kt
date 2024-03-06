package pl.lodz.p.edu

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
