package pl.lodz.p.edu

import java.security.SecureRandom

@JvmInline
value class AesKey(val value: UByteArray) {
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

    fun genSubKeys(): Array<UByteArray> {
        val sBox = AesEncrypter.generateSBox()
        val Nk = value.size / 4
        val Nr = Nk + 6
        val Rcon = ubyteArrayOf(0x1u, 0x2u, 0x4u, 0x8u, 0x10u, 0x20u, 0x40u, 0x80u, 0x1Bu, 0x36u)
        var w = arrayOf<UByteArray>()
        var temp: UByteArray

        fun subWord(word: UByteArray): UByteArray {
            return UByteArray(4) { sBox[word[it].toInt()] }
        }

        fun rotWord(word: UByteArray): UByteArray {
            return ubyteArrayOf(word[1], word[2], word[3], word[0])
        }

        var i = 0
        while (i < Nk) {
            w += ubyteArrayOf(value[4 * i], value[4 * i + 1], value[4 * i + 2], value[4 * i + 3])
            i++
        }
        while (i <= 4 * Nr + 3) {
            temp = w[i - 1]
            if (i.mod(Nk) == 0) {
                val firstByte = subWord(rotWord(temp))[0] xor Rcon[i]
                temp = subWord(rotWord(temp))
                temp[0] = firstByte
            } else if (Nk > 6 && i.mod(Nk) == 4) {
                temp = subWord(temp)
            }
            for (j in 0..3) {
                w[i][j] = w[i - Nk][j] xor temp[j]
            }
            i++
        }
        return w
    }

    companion object {
        fun fromHex(hex: String): AesKey {
            return AesKey(hex.hexToUByteArray())
        }

        fun fromBase64(base64: String): AesKey {
            return AesKey(base64.base64ToUByteArray())
        }

        fun generateRandom(bits: Int): AesKey {
            if (bits !in listOf(128, 192, 256))
                throw IllegalArgumentException("AES key must be 128, 192 or 256 bits.")

            val bytes = bits / 8

            val random = SecureRandom.getInstanceStrong()
            val key = ByteArray(bytes)
            random.nextBytes(key)

            return AesKey(key.toUByteArray())
        }
    }
}
