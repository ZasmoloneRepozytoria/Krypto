package pl.lodz.p.edu

interface Decrypter {
    fun decryptData(data: ByteArray): ByteArray
}
