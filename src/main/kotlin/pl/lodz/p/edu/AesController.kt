package pl.lodz.p.edu

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.text.Text
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.io.FileOutputStream
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
    private var rawFileName = Text()
    @FXML
    private var encryptedFileName = Text()
    @FXML
    private var loadRawButton = Button()
    @FXML
    private var loadEncryptedButton = Button()
    @FXML
    private var saveRawButton = Button()
    @FXML
    private var saveEncryptedButton = Button()

    @FXML
    private fun generateKey() {
        if (keyLength.value != null)
            keyField.text = AesKey.generateRandom(keyLength.value).toHex()
    }

    @FXML
    private fun loadRaw() {
        val file = loadFile() ?: return
        rawText.text = file.readBytes().toBase64String()
        rawFileName.text = file.name
    }
    @FXML
    private fun loadEncrypted() {
        val file = loadFile() ?: return
        encryptedText.text = file.readBytes().toBase64String()
        encryptedFileName.text = file.name
    }

    @FXML
    private fun saveRaw() {
        val fileChooser = FileChooser()
        fileChooser.title = "Save file"
        fileChooser.initialDirectory = FileSystemView.getFileSystemView().defaultDirectory
        fileChooser.initialFileName = if (rawFileName.text != "") rawFileName.text
        else if (encryptedFileName.text != "") encryptedFileName.text
        else "file"
        val stage = rawText.scene.window as Stage
        val file = fileChooser.showSaveDialog(stage) ?: return
        FileOutputStream(file).use { outputStream ->
            outputStream.write(rawText.text.base64ToByteArray())
        }
    }

    @FXML
    private fun saveEncrypted() {
        val fileChooser = FileChooser()
        fileChooser.title = "Save file"
        fileChooser.initialDirectory = FileSystemView.getFileSystemView().defaultDirectory
        fileChooser.initialFileName = if (rawFileName.text != "") rawFileName.text + ".aes"
        else if (encryptedFileName.text != "") encryptedFileName.text + ".aes"
        else "file.aes"
        val stage = rawText.scene.window as Stage
        val file = fileChooser.showSaveDialog(stage) ?: return
        FileOutputStream(file).use { outputStream ->
            outputStream.write(encryptedText.text.base64ToByteArray())
        }
    }
    @FXML
    private fun encrypt() {
        var output = UByteArray(0)
        val encrypter = AesEncrypter(AesKey.fromHex(keyField.text))
        var stream = if(modeBox.value == "Text") rawText.text.toUByteArray() else rawText.text.base64ToUByteArray()
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
        val stream = encryptedText.text.base64ToUByteArray()
        val decrypter = AesEncrypter(AesKey.fromHex(keyField.text))
        for (i in 0..<stream.size/16){
            output+=decrypter.decryptData(stream.copyOfRange(i*16, (i+1)*16))
        }
        if (modeBox.value=="Text") {
            rawText.text = String(output.toByteArray(), Charsets.UTF_8)
        } else {
            rawText.text = output.toBase64String()
        }
    }

    private fun loadFile(): File? {
        val fileChooser = FileChooser()
        fileChooser.title = "Load File"
        fileChooser.initialDirectory = FileSystemView.getFileSystemView().defaultDirectory
        val stage = rawText.scene.window as Stage
        return fileChooser.showOpenDialog(stage)
    }

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        rawText.isWrapText=true
        encryptedText.isWrapText=true
        keyLength.value=128
        keyLength.items.addAll(128, 192, 256)
        modeBox.value="Text"
        modeBox.items.addAll("Text", "File")
        modeBox.onAction = EventHandler {
            if (modeBox.value=="Text") {
                loadRawButton.isDisable = true
                loadEncryptedButton.isDisable = true
                saveRawButton.isDisable = true
                saveEncryptedButton.isDisable = true
        } else {
                loadRawButton.isDisable = false
                loadEncryptedButton.isDisable = false
                saveRawButton.isDisable = false
                saveEncryptedButton.isDisable = false
            }
        }
    }
}