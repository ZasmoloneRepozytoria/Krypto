package pl.lodz.p.edu

import kotlin.experimental.xor

class AesEncrypter(val key: AesKey) : Encrypter, Decrypter {
    // Tutaj tylko szyfrowanie i deszyfrowanie danych w postaci bajtów.
    private fun addRoundKey(state: Array<ByteArray>, w: Array<ByteArray>, round: Int): Array<ByteArray> {
        val tmp = arrayOf(ByteArray(4))
        for (i in 0..3){
            for (j in 0..3){
                tmp[j][i] = state[j][i] xor w[4*round+i][j]
            }
        }
        return tmp
    }
    override fun encryptData(data: ByteArray): ByteArray {
        throw NotImplementedError("AES encryption is not implemented yet!")
    }

    override fun decryptData(data: ByteArray): ByteArray {
        throw NotImplementedError("AES decryption is not implemented yet!")
    }
}
