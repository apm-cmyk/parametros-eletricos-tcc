module ParametrosEletricosLTFinal {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires org.apache.commons.math4.legacy;
	requires javafx.base;
	
	opens gui to javafx.graphics, javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
}
