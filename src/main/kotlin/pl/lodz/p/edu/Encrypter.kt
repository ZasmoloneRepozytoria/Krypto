package pl.lodz.p.edu

abstract class Encrypter {
    abstract fun encryptData(data: ByteArray): ByteArray;
    abstract fun decryptData(data: ByteArray): ByteArray;

    // Wszystkie metody do szyfrowania i deszyfrowania *tekstu* implementować
    // *tutaj*, korzystając z metod `encryptData` i `decryptData` implementowanych
    // w klasach dziedziczących.
}
