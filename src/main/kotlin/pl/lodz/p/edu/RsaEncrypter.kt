package pl.lodz.p.edu

class RsaEncrypter private constructor(
    val publicKey: ByteArray,
    val privateKey: ByteArray
) : Encrypter() {
    // Tutaj tylko szyfrowanie i deszyfrowanie danych w postaci bajtów.

    override fun encryptData(data: ByteArray): ByteArray {
        throw NotImplementedError("AES encryption is not implemented yet!")
    }

    override fun decryptData(data: ByteArray): ByteArray {
        throw NotImplementedError("AES decryption is not implemented yet!")
    }

    companion object {
        fun withKeyData(publicKey: ByteArray, privateKey: ByteArray) =
            RsaEncrypter(publicKey, privateKey)

        fun withHexKeys(publicKey: String, privateKey: String) =
            RsaEncrypter(publicKey.hexToByteArray(), privateKey.hexToByteArray())
        fun withBase64Keys(publicKey: String, privateKey: String) =
            RsaEncrypter(publicKey.base64ToByteArray(), privateKey.base64ToByteArray())
    }
}
