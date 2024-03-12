package pl.lodz.p.edu

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AesEncrypterTest {

    @Test
    fun `should encrypt and decrypt data`() {
        val encrypter = AesEncrypter(AesKey.fromHex("2b7e151628aed2a6abf7158809cf4f3c"))
        val data = ubyteArrayOf(0x32u, 0x43u, 0xf6u, 0xa8u, 0x88u, 0x5au, 0x30u, 0x8du, 0x31u, 0x31u, 0x98u, 0xa2u, 0xe0u, 0x37u, 0x07u, 0x34u)

        val encryptedData = encrypter.encryptData(data)
        val decryptedData = encrypter.decryptData(encryptedData)

        assertArrayEquals(data, decryptedData)
    }

    @Test
    fun `different keys should encrypt and decrypt data differently`() {
        val encrypter1 = AesEncrypter(AesKey.generateRandom(128))
        val encrypter2 = AesEncrypter(AesKey.generateRandom(128))

        val data = UByteArray(16){0u}

        val encryptedData1 = encrypter1.encryptData(data)
        val encryptedData2 = encrypter2.encryptData(data)

        assertArrayNotEquals(encryptedData1, encryptedData2)
    }

    @Test
    fun `should not decrypt data encrypted with different key`() {
        val encrypter1 = AesEncrypter(AesKey.generateRandom(128))
        val encrypter2 = AesEncrypter(AesKey.generateRandom(128))

        val data = UByteArray(16){0u}

        val encryptedData = encrypter1.encryptData(data)
        // TODO: Can this throw an exception?
        val decryptedData = encrypter2.decryptData(encryptedData)

        assertArrayNotEquals(data, decryptedData)
    }

    @Nested @DisplayName("genSubKeys should generate Nr+1 keys")
    inner class GenSubKeysTest {
        @Test
        fun `for 128-bit key`() {
            val keys = AesKey.generateRandom(128).genSubKeys()
            assertEquals(11, keys.size / 4)
        }

        @Test
        fun `for 192-bit key`() {
            val keys = AesKey.generateRandom(192).genSubKeys()
            assertEquals(13, keys.size / 4)
        }

        @Test
        fun `for 256-bit key`() {
            val keys = AesKey.generateRandom(256).genSubKeys()
            assertEquals(15, keys.size / 4)
        }
    }
}
