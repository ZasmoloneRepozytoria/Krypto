package pl.lodz.p.edu

import java.math.BigInteger

class RsaDecrypter(val privateKey: RsaPrivateKey){

    fun decryptData(data: String): UByteArray {
        var output = UByteArray(0)
        val tmp = data.split(",")
        for (segment in tmp){
            if(segment.isNotEmpty())
                output += decryptBlock(BigInteger(segment)).toUByteArray()
        }
        return output
    }
    fun decryptBlock(data: BigInteger): BigInteger {
        val tmp = String(privateKey.value.toByteArray()).split(',')
        val d = BigInteger(tmp[0])
        val n = BigInteger(tmp[1])
        val c = BigInteger(data.toByteArray())
        return c.modPow(d, n)
    }
}
