package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DepartmentFormController implements Initializable {
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	@FXML
	private Label labelErrorName;

	@FXML
	public void onButtonSaveAction() {
		System.out.println("onButtonSaveAction");
	}

	@FXML
	public void onButtonCancelAtion() {
		System.out.println("onButtonCancelAtion");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNods();
	}

	private void initializeNods() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);

	}
}
