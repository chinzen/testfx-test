import javafx.scene.Node
import javafx.scene.input.KeyCode
import javafx.stage.Stage
import org.junit.Test
import org.testfx.api.FxRobot
import org.testfx.framework.junit.ApplicationTest
import testfx.MyApp
import kotlin.test.assertTrue

class MyAppTest : ApplicationTest() {
    private lateinit var app: MyApp
    
    override fun start(stage: Stage) {
        app = MyApp()
        app.start(stage)
    }

    override fun stop() {
        app.stop()
    }
    
@Test
fun `FxRobot hangs on an activation of the edit mode`() {
    val cellContent = "Samantha Stuart"
    
    // For me `doubleClickOn` breaks it and was able to make it work
    // only by calling `clickOn` twice instead.
    clickOn(cellContent)
    clickOn(cellContent)
    press(KeyCode.BACK_SPACE)
    val nameNew = "name new that is quite long"
    write(nameNew)
    press(KeyCode.ENTER)
    
    assertTrue(lookup(nameNew).tryQuery<Node>().isPresent)
}

@Test
fun `FxRobot works if the keys were manually released`() {
    val cellContent = "Samantha Stuart"
    
    clickOn(cellContent)
    clickOn(cellContent)
    releaseAllKeys()
    press(KeyCode.BACK_SPACE)
    releaseAllKeys()
    val nameNew = "name new that is quite long"
    write(nameNew)
    press(KeyCode.ENTER)
    releaseAllKeys()
    
    assertTrue(lookup(nameNew).tryQuery<Node>().isPresent)
}
    
    fun FxRobot.releaseAllKeys() = release(*arrayOfNulls<KeyCode>(0))
}
