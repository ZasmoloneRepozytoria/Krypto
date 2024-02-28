package pl.lodz.p.edu

interface Encrypter {
    abstract fun encryptData(data: ByteArray): ByteArray;
}
