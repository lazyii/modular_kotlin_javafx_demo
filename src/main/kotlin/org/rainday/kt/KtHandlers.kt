package org.rainday.kt

import javafx.beans.property.SimpleStringProperty
import javafx.event.ActionEvent
import javafx.event.EventHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * @author wyd
 * @date 2021-07-08 10:36:03
 * @version 1.0 edit by wyd at 2021-07-08 10:36:03
 */
object KtHandlers {
    public val buttonHandler11 = EventHandler<ActionEvent> {
        GlobalScope.launch {

            println(this)
            println("launch")

            userNameProperty.set("${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))}")
        }
    }

    val userNameProperty = SimpleStringProperty("时间")
}

