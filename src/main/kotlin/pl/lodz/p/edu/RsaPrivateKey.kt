package pl.lodz.p.edu

@JvmInline
value class RsaPrivateKey(val value: UByteArray) {
    fun toHex(): String {
        return value.toHexString()
    }

    fun toBase64(): String {
        return value.toBase64String()
    }

    companion object {
        fun fromHex(hex: String): RsaPrivateKey {
            return RsaPrivateKey(hex.hexToUByteArray())
        }

        fun fromBase64(base64: String): RsaPrivateKey {
            return RsaPrivateKey(base64.base64ToUByteArray())
        }
    }
}
