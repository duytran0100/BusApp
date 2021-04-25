module com.test.bus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires org.apache.commons.codec;
    
    opens com.test.bus to javafx.fxml;
    exports com.test.bus;
    exports com.test.pojo;
    exports com.test.service;
}
