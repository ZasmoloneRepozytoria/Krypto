package pl.lodz.p.edu

data class RsaKeyPair(val publicKey: RsaPublicKey, val privateKey: RsaPrivateKey) {
    companion object {
        fun generateRandom(): RsaKeyPair {
            throw NotImplementedError("RSA key generation is not implemented yet!")
        }
    }
}
