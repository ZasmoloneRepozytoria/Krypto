package pl.lodz.p.edu

@JvmInline
value class RsaPrivateKey(val value: ByteArray) {
    fun toHex(): String {
        return value.toHexString()
    }

    fun toBase64(): String {
        return value.toBase64String()
    }

    companion object {
        fun fromHex(hex: String): RsaPublicKey {
            return RsaPublicKey(hex.hexToByteArray())
        }

        fun fromBase64(base64: String): RsaPublicKey {
            return RsaPublicKey(base64.base64ToByteArray())
        }
    }
}
