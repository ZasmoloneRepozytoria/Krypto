package pl.lodz.p.edu

class AesEncrypter private constructor(val key: ByteArray) : Encrypter() {
    // Tutaj tylko szyfrowanie i deszyfrowanie danych w postaci bajtów.

    override fun encryptData(data: ByteArray): ByteArray {
        throw NotImplementedError("AES encryption is not implemented yet!")
    }

    override fun decryptData(data: ByteArray): ByteArray {
        throw NotImplementedError("AES decryption is not implemented yet!")
    }

    companion object {
        fun withKeyData(key: ByteArray) = AesEncrypter(key)

        fun withHexKey(key: String) = AesEncrypter(key.hexToByteArray())
        fun withBase64Key(key: String) = AesEncrypter(key.base64ToByteArray())
    }
}
