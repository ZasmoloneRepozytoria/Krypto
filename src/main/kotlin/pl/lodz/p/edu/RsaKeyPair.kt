package pl.lodz.p.edu

import java.math.BigInteger
import java.util.*

data class RsaKeyPair(val publicKey: RsaPublicKey, val privateKey: RsaPrivateKey) {
    fun isValid(): Boolean {
        // We test the keys by encrypting and decrypting a random thing with them.
        val encrypter = RsaEncrypter(publicKey)
        val decrypter = RsaDecrypter(privateKey)
        val data = "May the Force be with you.".toUByteArray()
        val encryptedData = encrypter.encryptData(data)
        val decryptedData = decrypter.decryptData(encryptedData)
        return data contentEquals decryptedData
    }

    companion object {
        fun generateRandom(): RsaKeyPair {
            val rnd = Random()
            val q = BigInteger.probablePrime(1024, rnd)
            val p = BigInteger.probablePrime(1024, rnd)
            val n = q * p
            val phi = (q-BigInteger.ONE) * (p - BigInteger.ONE)
            var e = BigInteger(phi.bitLength(), rnd)
            while (e <= BigInteger.ONE || e >= phi || e.gcd(phi) != BigInteger.ONE) {
                e = BigInteger(phi.bitLength(), rnd)
            }
            val d = e.modInverse(phi)

            val publicKey = "$e,$n"
            val privateKey = "$d,$n"
            //val tmp = publicKey.toUByteArray()
            //val tmp2 = tmp.toHexString()
            return RsaKeyPair(RsaPublicKey(publicKey.toUByteArray()), RsaPrivateKey(privateKey.toUByteArray()))
        }
    }
}

