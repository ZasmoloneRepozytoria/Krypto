package pl.lodz.p.edu

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.text.Text
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.net.URL
import java.util.*
import javax.swing.filechooser.FileSystemView

class AesController: Initializable {
    @FXML
    private var keyField = TextField()
    @FXML
    private var keyLength = ChoiceBox<Int>()
    @FXML
    private var rawText = TextArea()
    @FXML
    private var encryptedText = TextArea()
    @FXML
    private var modeBox = ChoiceBox<String>()
    @FXML
    private var fileName = Text()

    private var buffer = UByteArray(0)

    @FXML
    private fun generateKey() {
        if (keyLength.value != null)
            keyField.text = AesKey.generateRandom(keyLength.value).toHex()
    }

    @FXML
    private fun loadFile() {
        val fileChooser = FileChooser()
        fileChooser.title = "Chose file to encrypt"
        fileChooser.initialDirectory = FileSystemView.getFileSystemView().defaultDirectory
        val stage = rawText.scene.window as Stage
        val file = fileChooser.showOpenDialog(stage) ?: return
        buffer = file.readBytes().toUByteArray()
        rawText.text = file.readBytes().toString(Charsets.UTF_8)
        fileName.text = file.name
    }

    @FXML
    private fun encrypt() {
        var output = UByteArray(0)
        val encrypter = AesEncrypter(AesKey.fromHex(keyField.text))
        var stream = if(modeBox.value=="Text") rawText.text.toUByteArray() else buffer
        if (stream.size % 16 != 0){
            val complement = UByteArray(16 - stream.size % 16){0u}
            stream += complement
        }
        for (i in 0..<stream.size/16){
            output+=encrypter.encryptData(stream.copyOfRange(i*16, (i+1)*16))
        }
        encryptedText.text = output.toBase64String()
    }

    @FXML
    private fun decrypt() {
        var output = UByteArray(0)
        var stream = encryptedText.text.base64ToUByteArray()
        val decrypter = AesEncrypter(AesKey.fromHex(keyField.text))
        for (i in 0..<stream.size/16){
            output+=decrypter.decryptData(stream.copyOfRange(i*16, (i+1)*16))
        }
        rawText.text = String(output.toByteArray(), Charsets.UTF_8)
    }
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        rawText.isWrapText=true
        encryptedText.isWrapText=true
        keyLength.value=128
        keyLength.items.addAll(128, 192, 256)
        modeBox.value="Text"
        modeBox.items.addAll("Text", "File")
    }
}