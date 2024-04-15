package pl.lodz.p.edu

import java.math.BigInteger

class RsaEncrypter(val publicKey: RsaPublicKey) : Encrypter {
    // Tutaj tylko szyfrowanie i deszyfrowanie danych w postaci bajtów.

    override fun encryptData(data: UByteArray): UByteArray {
        val tmp = String(publicKey.value.toByteArray()).split(',')
        val e = BigInteger(tmp[0])
        val n = BigInteger(tmp[1])
        val m = BigInteger(data.toByteArray())
        return m.modPow(e, n).toUByteArray()
    }
}
