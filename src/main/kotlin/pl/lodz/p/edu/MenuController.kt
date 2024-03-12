package pl.lodz.p.edu
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Stage
import java.net.URL
import java.util.*

class MenuController:Initializable {
    @FXML
    private var aesButton = Button()

    @FXML
    private fun onAesButtonPushed() {
        val stage = aesButton.scene.window as Stage
        val loader = FXMLLoader(javaClass.getResource("/Aes.fxml"))
        loader.setController(AesController())
        val root = loader.load<Parent>()
        val aesScene = Scene(root)
        stage.scene = aesScene
    }
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
    }
}
