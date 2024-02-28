package pl.lodz.p.edu

class RsaEncrypter(val publicKey: RsaPublicKey) : Encrypter {
    // Tutaj tylko szyfrowanie i deszyfrowanie danych w postaci bajtów.

    override fun encryptData(data: ByteArray): ByteArray {
        throw NotImplementedError("AES encryption is not implemented yet!")
    }
}
