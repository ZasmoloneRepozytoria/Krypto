package pl.lodz.p.edu

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import java.net.URL
import java.util.*

class AesController: Initializable {
    @FXML
    private var keyField = TextField()
    @FXML
    private val keyButton = Button()
    @FXML
    private var keyLength = ChoiceBox<Int>()
    @FXML
    private var rawText = TextArea()
    @FXML
    private var encryptedText = TextArea()
    @FXML
    private val encryptButton = Button()

    @FXML
    private fun generateKey() {
        if (keyLength.value != null)
            keyField.text = AesKey.generateRandom(keyLength.value).toHex()
    }

    @FXML
    private fun encrypt() {
        println("bonk")
        var output = UByteArray(0)
        var stream = rawText.text.toUByteArray()
        val encrypter = AesEncrypter(AesKey.fromHex(keyField.text))
        if (stream.size % 16 != 0){
            val complement = UByteArray(stream.size % 16){0u}
            stream += complement
        }
        for (i in 0..<stream.size/16){
            output+=encrypter.encryptData(stream.copyOfRange(i, 16*(i+1)))
        }
        encryptedText.text = output.toHexString()
    }
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        keyLength.items.addAll(128, 192, 256)
    }
}