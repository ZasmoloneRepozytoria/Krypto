package pl.lodz.p.edu

import java.math.BigInteger

class RsaDecrypter(val privateKey: RsaPrivateKey) : Decrypter {

    override fun decryptData(data: UByteArray): UByteArray {
        var output = UByteArray(0)
        val tmp = splitUByteArrayToBigIntegers(data, 256)
        for (segment in tmp){
            output += decryptBlock(segment)
        }
        return output
    }
    fun decryptBlock(data: UByteArray): UByteArray {
        val tmp = String(privateKey.value.toByteArray()).split(',')
        val d = BigInteger(tmp[0])
        val n = BigInteger(tmp[1])
        val c = BigInteger(data.toByteArray())
        return c.modPow(d, n).toUByteArray()
    }
}
