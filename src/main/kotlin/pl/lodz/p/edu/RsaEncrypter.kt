package pl.lodz.p.edu

import java.math.BigInteger

class RsaEncrypter(val publicKey: RsaPublicKey){
    // Tutaj tylko szyfrowanie i deszyfrowanie danych w postaci bajtów.

    fun encryptData(data: UByteArray): String {
        var output = ""
        val tmp = splitUByteArray(data, 64)
        for (segment in tmp){
            val encryptedBlock = encryptBlock(segment).toString()

            output += "$encryptedBlock,"
        }
        return output
    }
     fun encryptBlock(data: UByteArray): BigInteger {
        val tmp = String(publicKey.value.toByteArray()).split(',')
        val e = BigInteger(tmp[0])
        val n = BigInteger(tmp[1])
        val m = BigInteger(data.toByteArray())
        return m.modPow(e, n)
    }
}
