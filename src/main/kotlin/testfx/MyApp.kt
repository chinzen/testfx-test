package testfx

import javafx.collections.FXCollections
import tornadofx.*
import java.time.LocalDate

class MyApp : App(MyView::class)

class MyView : View() {
    private val persons = FXCollections.observableArrayList(
        Person(name = "Samantha Stuart", birthday = LocalDate.of(1981,12,4)),
        Person(name = "Tom Marks", birthday = LocalDate.of(2001,1,23))
    )
    
    override val root = tableview(persons) {
        column("name", Person::name).makeEditable()
        column("birthday", Person::birthday)
    }
}

data class Person(val name: String, val birthday: LocalDate)
