package pl.lodz.p.edu

import java.security.SecureRandom

class AesEncrypter(val key: AesKey) : Encrypter, Decrypter {
    // Tutaj tylko szyfrowanie i deszyfrowanie danych w postaci bajtów.

    override fun encryptData(data: ByteArray): ByteArray {
        throw NotImplementedError("AES encryption is not implemented yet!")
    }

    override fun decryptData(data: ByteArray): ByteArray {
        throw NotImplementedError("AES decryption is not implemented yet!")
    }
}
