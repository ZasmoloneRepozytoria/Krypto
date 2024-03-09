import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class GUI : Application() {
    override fun start(stage: Stage) {
        val loader = FXMLLoader(javaClass.getResource("/MainMenu.fxml"))
        loader.setController(MenuControler())
        val root = loader.load<Parent>()
        val menu = Scene(root)
        stage.scene = menu
        stage.show()
    }
}