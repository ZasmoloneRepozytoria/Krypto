package pl.lodz.p.edu

import org.junit.jupiter.api.Test

class RsaEncrypterTest {

    @Test
    fun `should encrypt and decrypt data`() {
        val (publicKey, privateKey) = RsaKeyPair.generateRandom()
        val encrypter = RsaEncrypter(publicKey)
        val decrypter = RsaDecrypter(privateKey)
        val data = "Hello, World!".toUByteArray()
        val encryptedData = encrypter.encryptData(data)
        val decryptedData = decrypter.decryptData(encryptedData)

        assertArrayEquals(data, decryptedData)
    }

    @Test
    fun `different keys should encrypt and decrypt data differently`() {
        val publicKey1 = RsaKeyPair.generateRandom().publicKey
        val publicKey2 = RsaKeyPair.generateRandom().publicKey

        val encrypter1 = RsaEncrypter(publicKey1)
        val encrypter2 = RsaEncrypter(publicKey2)

        val data = "Hello, World!".toUByteArray()

        val encryptedData1 = encrypter1.encryptData(data)
        val encryptedData2 = encrypter2.encryptData(data)

        assertArrayNotEquals(encryptedData1, encryptedData2)
    }

    @Test
    fun `should not decrypt data encrypted with different key`() {
        val publicKey = RsaKeyPair.generateRandom().publicKey
        val wrongPrivateKey = RsaKeyPair.generateRandom().privateKey

        val encrypter = RsaEncrypter(publicKey)
        val decrypter = RsaDecrypter(wrongPrivateKey)

        val data = "Hello, World!".toUByteArray()

        val encryptedData = encrypter.encryptData(data)
        // TODO: Can this throw an exception?
        val decryptedData = decrypter.decryptData(encryptedData)

        assertArrayNotEquals(data, decryptedData)
    }
}
