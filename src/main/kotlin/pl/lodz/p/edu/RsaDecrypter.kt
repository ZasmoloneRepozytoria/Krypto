package pl.lodz.p.edu

class RsaDecrypter(val privateKey: RsaPrivateKey) : Decrypter {
    override fun decryptData(data: UByteArray): UByteArray {
        throw NotImplementedError("RSA decryption is not implemented yet!")
    }
}
