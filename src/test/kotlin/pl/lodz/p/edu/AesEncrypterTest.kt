package pl.lodz.p.edu

import org.junit.jupiter.api.Test

class AesEncrypterTest {

    @Test
    fun `should encrypt and decrypt data`() {
        val encrypter = AesEncrypter(AesKey.generateRandom(128))
        val data = "Hello, World!".toUByteArray()

        val encryptedData = encrypter.encryptData(data)
        val decryptedData = encrypter.decryptData(encryptedData)

        assertArrayEquals(data, decryptedData)
    }

    @Test
    fun `different keys should encrypt and decrypt data differently`() {
        val encrypter1 = AesEncrypter(AesKey.generateRandom(128))
        val encrypter2 = AesEncrypter(AesKey.generateRandom(128))

        val data = "Hello, World!".toUByteArray()

        val encryptedData1 = encrypter1.encryptData(data)
        val encryptedData2 = encrypter2.encryptData(data)

        assertArrayNotEquals(encryptedData1, encryptedData2)
    }

    @Test
    fun `should not decrypt data encrypted with different key`() {
        val encrypter1 = AesEncrypter(AesKey.generateRandom(128))
        val encrypter2 = AesEncrypter(AesKey.generateRandom(128))

        val data = "Hello, World!".toUByteArray()

        val encryptedData = encrypter1.encryptData(data)
        // TODO: Can this throw an exception?
        val decryptedData = encrypter2.decryptData(encryptedData)

        assertArrayNotEquals(data, decryptedData)
    }
}
