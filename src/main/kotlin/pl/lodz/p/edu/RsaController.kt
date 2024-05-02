package pl.lodz.p.edu

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.text.Text
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.*
import javax.swing.filechooser.FileSystemView

class RsaController: Initializable {
    @FXML
    private var publicKeyField = TextField()
    @FXML
    private var privateKeyField = TextField()
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
        val keys = RsaKeyPair.generateRandom()
        publicKeyField.text = keys.publicKey.toBase64()
        privateKeyField.text = keys.privateKey.toBase64()
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
        fileChooser.initialFileName = if (rawFileName.text != "") rawFileName.text + ".rsa"
        else if (encryptedFileName.text != "") encryptedFileName.text + ".rsa"
        else "file.rsa"
        val stage = rawText.scene.window as Stage
        val file = fileChooser.showSaveDialog(stage) ?: return
        FileOutputStream(file).use { outputStream ->
            outputStream.write(encryptedText.text.base64ToByteArray())
        }
    }
    @FXML
    private fun encrypt() {
        try {
            val encrypter = RsaEncrypter(RsaPublicKey.fromBase64(publicKeyField.text))
            val stream = if (modeBox.value == "Text") rawText.text.toUByteArray() else rawText.text.base64ToUByteArray()

            encryptedText.text = encrypter.encryptData(stream).toBase64String()
        }
        catch (e: KeyFormatException){
            val a = Alert(Alert.AlertType.ERROR)
            a.contentText = "Key error: " + e.message
            e.printStackTrace()
            a.show()
        }
        catch (e: IllegalArgumentException){
            val a = Alert(Alert.AlertType.ERROR)
            a.contentText = "Encrypt input is not in Base64 format"
            e.printStackTrace()
            a.show()
        }
    }

    @FXML
    private fun decrypt() {
        try {
            val stream = encryptedText.text.base64ToUByteArray()
            val decrypter = RsaDecrypter(RsaPrivateKey.fromBase64(privateKeyField.text))
            val output = decrypter.decryptData(stream)
            if (modeBox.value == "Text") {
                rawText.text = String(output.toByteArray(), Charsets.UTF_8)
            } else {
                rawText.text = output.toBase64String()
            }
        }
        catch (e: KeyFormatException){
            val a = Alert(Alert.AlertType.ERROR)
            a.contentText = "Key error: " + e.message
            e.printStackTrace()
            a.show()
        }
        catch (e: IllegalArgumentException){
            val a = Alert(Alert.AlertType.ERROR)
            a.contentText = "Decrypt input is not in Base64 format"
            e.printStackTrace()
            a.show()
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