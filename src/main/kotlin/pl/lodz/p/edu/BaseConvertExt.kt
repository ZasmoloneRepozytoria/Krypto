package pl.lodz.p.edu

import java.util.*

fun String.base64ToByteArray(): ByteArray {
    return Base64.getDecoder().decode(this)
}
