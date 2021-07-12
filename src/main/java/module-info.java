/**
 * @author wyd
 * @date 2021-07-09 10:35:47
 * @version 1.0 edit by wyd at 2021-07-09 10:35:47
 */
module kt.desktop.coroutines {
    requires kotlin.stdlib;
    requires kotlinx.coroutines.core.jvm;
    requires kotlinx.coroutines.javafx;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;

    exports org.rainday.kt;
    exports org.rainday.avaj.mvvm;

    opens org.rainday.avaj.mvvm to javafx.fxml;
}
