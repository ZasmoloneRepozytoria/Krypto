package pl.lodz.p.edu

import java.security.SecureRandom

@JvmInline
value class AesKey(val value: ByteArray) {
    init {
        if (value.size !in listOf(16, 24, 32))
            throw IllegalArgumentException("AES key must be 128, 192 or 256 bits.")
    }

    fun toHex(): String {
        return value.toHexString()
    }

    fun toBase64(): String {
        return value.toBase64String()
    }

    companion object {
        fun fromHex(hex: String): AesKey {
            return AesKey(hex.hexToByteArray())
        }

        fun fromBase64(base64: String): AesKey {
            return AesKey(base64.base64ToByteArray())
        }

        fun generateRandom(bits: Int): AesKey {
            if (bits !in listOf(128, 192, 256))
                throw IllegalArgumentException("AES key must be 128, 192 or 256 bits.")

            val bytes = bits / 8;

            val random = SecureRandom.getInstanceStrong()
            val key = ByteArray(bytes)
            random.nextBytes(key)

            return AesKey(key)
        }
    }
}
