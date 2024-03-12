package pl.lodz.p.edu

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
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
    private fun generateKey() {
        if (keyLength.value != null)
            keyField.text = AesKey.generateRandom(keyLength.value).toHex()
    }
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        keyLength.items.addAll(128, 192, 256)
    }
}