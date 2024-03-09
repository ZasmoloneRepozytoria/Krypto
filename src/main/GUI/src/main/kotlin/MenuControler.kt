import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import java.net.URL
import java.util.*

class MenuControler:Initializable {
    @FXML
    private var aesButton = Button()

    @FXML
    private fun onAesButtonPushed() {
        println("bonk")
    }
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
    }
}