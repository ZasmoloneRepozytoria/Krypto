package pl.lodz.p.edu

import org.junit.jupiter.api.RepeatedTest
import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class RsaEncrypterTest {

    @RepeatedTest(10)
    fun `should encrypt and decrypt blocks`() {
        val (publicKey, privateKey) = RsaKeyPair.generateRandom()
        val encrypter = RsaEncrypter(publicKey)
        val decrypter = RsaDecrypter(privateKey)
        val data = "Hello, World!".toUByteArray()
        val encryptedData = encrypter.encryptBlock(data)
        val decryptedData = decrypter.decryptBlock(encryptedData)

        assertEquals(BigInteger(data.toByteArray()), decryptedData)
    }

    @RepeatedTest(100)
    fun `should encrypt and decrypt long data`() {
        val (publicKey, privateKey) = RsaKeyPair.generateRandom()
        val encrypter = RsaEncrypter(publicKey)
        val decrypter = RsaDecrypter(privateKey)
        val data = "ala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kotaala ma kota".toUByteArray()
        val encryptedData = encrypter.encryptData(data)
        val decryptedData = decrypter.decryptData(encryptedData)

        assertArrayEquals(data, decryptedData)
    }

    @RepeatedTest(10)
    fun `generated RSA key pair should be valid`() {
        val keyPair = RsaKeyPair.generateRandom()

        assertTrue(keyPair.isValid())
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

        assertNotEquals(encryptedData1, encryptedData2)
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
