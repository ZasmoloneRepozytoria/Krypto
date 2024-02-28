package pl.lodz.p.edu

class RsaDecrypter(val privateKey: RsaPrivateKey) : Decrypter {
    override fun decryptData(data: ByteArray): ByteArray {
        throw NotImplementedError("RSA decryption is not implemented yet!")
    }
}
