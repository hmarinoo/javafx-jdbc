package gui;

import java.net.URL;
import java.net.http.WebSocket.Listener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.sound.sampled.LineListener;

import com.mysql.fabric.xmlrpc.base.Array;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

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

	private DepartmentService depService;

	private Department department;
	
	List<DataChangeListener> dataChengeListeners = new ArrayList<>();
	
	public void subscribeDataChengeListaner(DataChangeListener listener) {
		dataChengeListeners.add(listener);
		
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setDepartmentService(DepartmentService depService) {
		this.depService = depService;
	}

	@FXML
	public void onButtonSaveAction(ActionEvent event) {
		if (department == null) {
			throw new IllegalStateException("Department was null");
		}
		if (depService == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			department = getFormData();
			depService.saveOrUpdate(department);
			notifyDataChengeListeners();
			Utils.currentStage(event).close(); 
		} catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChengeListeners() {
		dataChengeListeners.forEach(DataChangeListener::onDataChange);	
	}

	private Department getFormData() {
		Department department = new Department();
		department.setId(Utils.tryParseInt(txtId.getText()));
		department.setName(txtName.getText());
		return department;
	}

	@FXML
	public void onButtonCancelAtion(ActionEvent event) {
		Utils.currentStage(event).close(); 
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNods();
	}

	private void initializeNods() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);

	}

	public void updateFormData() {
		if (department == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(department.getId()));
		txtName.setText(department.getName());
	}
}
