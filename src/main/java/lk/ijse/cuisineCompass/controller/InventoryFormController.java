package lk.ijse.cuisineCompass.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cuisineCompass.bo.BOFactory;
import lk.ijse.cuisineCompass.bo.custom.InventoryBO;
//import lk.ijse.cuisineCompass.dto.tm.InventoryTM;
import lk.ijse.cuisineCompass.dto.InventoryDTO;
import lk.ijse.cuisineCompass.view.tdm.InventoryTM;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class InventoryFormController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/cuisine_compass";
    private final static Properties props = new Properties();
    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }
    @FXML
    private Button btnBackInv;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDes;

    @FXML
    private TableColumn<?, ?> colInv;

    @FXML
    private TableColumn<?, ?> colPar;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnit;

    @FXML
    private DatePicker datePick;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<InventoryTM> tbInv;

    @FXML
    private TextField txtDes;

    @FXML
    private TextField txtPar;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtInv;

    @FXML
    private JFXComboBox<String> cBoxUnit;

    InventoryBO inventoryBO = (InventoryBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.INVENTORY);

    @SneakyThrows
    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAllInv();
        loadUnits();
    }

    void setCellValueFactory() {
        colInv.setCellValueFactory(new PropertyValueFactory<>("iCode"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("des"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colPar.setCellValueFactory(new PropertyValueFactory<>("par"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    void getAllInv() {
        try {
            ObservableList<InventoryTM> obList = FXCollections.observableArrayList();
            //List<Inventory> invList = InventoryModel.getAll();

            List<InventoryDTO> invList = inventoryBO.getAllInv();

            for(InventoryDTO inventoryDTO : invList) {
                obList.add(new InventoryTM(
                        inventoryDTO.getIngCode(),
                        inventoryDTO.getDes(),
                        inventoryDTO.getUnit(),
                        inventoryDTO.getPar(),
                        inventoryDTO.getQty(),
                        inventoryDTO.getDate()
                ));
            }
            tbInv.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    /*private void loadInv() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> codes = InventoryModel.getInv();

        for (String code : codes) {
            obList.add(code);
        }
        cBoxInv.setItems(obList);
    }*/

    private void loadUnits() {
        List<String> units = new ArrayList<>();

        units.add(0, "g");
        units.add(1, "kg");
        units.add(2, "l");
        units.add(3, "ml");
        units.add(4, "tin");
        units.add(5, "can");
        units.add(6, "jar");
        units.add(7, "packet");

        ObservableList<String> obList = FXCollections.observableArrayList();
        for (String unit : units) {
            obList.add(unit);
        }
        cBoxUnit.setItems(obList);
    }

    public void btnBackInvOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/sup_dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Supervisor Dashboard");
        stage.centerOnScreen();
    }

    public void btnClearOnAction(ActionEvent event) {
        txtInv.setText("");
        txtDes.setText("");
        cBoxUnit.setValue("");
        txtPar.setText("");
        txtQty.setText("");
        datePick.setValue(null);
    }

    public void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete? :)", yes,no).showAndWait();
        if(result.orElse(no)==yes) {

            //boolean isDeleted = InventoryModel.isDeleted(txtInv.getText());

            String iCode = txtInv.getText();

            boolean isDeleted = inventoryBO.delete(iCode);

            if (isDeleted) {
                getAllInv();
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String iCode = txtInv.getText();
        String des = txtDes.getText();
        String unit = cBoxUnit.getValue();

        String sPar = txtPar.getText();
        double par = Double.parseDouble(sPar);

        String sQty = txtQty.getText();
        double qty = Double.parseDouble(sQty);

        Date date = Date.valueOf(datePick.getValue());

        //boolean isUpdated = InventoryModel.isUpdated(iCode, des, unit, par, qty, date);

        boolean isUpdated = inventoryBO.update(new InventoryDTO(iCode, des, unit, par, qty, date));

        if (isUpdated) {
            getAllInv();
            new Alert(Alert.AlertType.CONFIRMATION, "Inventory successfully updated!!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent event) throws SQLException {

        String iCode = txtInv.getText();
        String des = txtDes.getText();
        String unit = cBoxUnit.getValue();

        String sPar = txtPar.getText();
        double par = Double.parseDouble(sPar);

        String sQty = txtQty.getText();
        double qty = Double.parseDouble(sQty);

        Date date = Date.valueOf(datePick.getValue());

        if (txtInv.getText().matches("^I\\d{3}$")) {
            try {
                //boolean isSaved = InventoryModel.isSaved(iCode, des, unit, par, qty, date);

                boolean isSaved = inventoryBO.save(new InventoryDTO(iCode, des, unit, par, qty, date));

                if (isSaved) {
                    getAllInv();
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully added to inventory!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Error! Try Again").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid inventory code! Try Again").show();
        }
    }

    /*public void txtInvOnAction(ActionEvent event) throws SQLException {
        String iCode = txtInv.getText();

        Connection con = DriverManager.getConnection(URL, props);
        String sql = "SELECT * FROM inventory WHERE inventory_code = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, iCode);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String code = resultSet.getString(1);
            String des = resultSet.getString(2);
            String unit = resultSet.getString(3);
            String par = resultSet.getString(4);
            String qty = resultSet.getString(5);
            String date = resultSet.getString(6);
            //LocalDate date = resultSet.getDate(6);
            //Date date = Date.valueOf(datePick.getValue());

            //System.out.println(date);

            txtInv.setText(code);
            txtDes.setText(des);
            cBoxUnit.setValue(unit);
            txtPar.setText(par);
            txtQty.setText(qty);
            datePick.setValue(LocalDate.parse(date));
            //valueOf(datePick.setValue(date));

            txtDes.requestFocus();

        } else {
            txtDes.setText("");
            cBoxUnit.setValue("");
            txtPar.setText("");
            txtQty.setText("");
            datePick.setValue(null);
        }
        txtDes.requestFocus();
    }*/

    public void txtInvOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String iCode = txtInv.getText();

        if(!iCode.isEmpty()){
            InventoryDTO inventoryDTO = inventoryBO.search(iCode);

            if (inventoryDTO != null){
                txtInv.setText(inventoryDTO.getIngCode());
                txtDes.setText(inventoryDTO.getDes());
                cBoxUnit.setValue(inventoryDTO.getUnit());
                txtPar.setText(String.valueOf(inventoryDTO.getPar()));
                txtQty.setText(String.valueOf(inventoryDTO.getQty()));
                datePick.setValue(LocalDate.parse(inventoryDTO.getDate()));

                txtDes.requestFocus();
            }else{
                txtDes.setText("");
                cBoxUnit.setValue("");
                txtPar.setText("");
                txtQty.setText("");
                datePick.setValue(null);
            }
        }
        txtDes.requestFocus();
    }

    /*public void txtInvOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String iCode = txtInv.getText();

        if(existInventory(iCode)){


            txtInv.setText(code);
            txtDes.setText(des);
            cBoxUnit.setValue(unit);
            txtPar.setText(par);
            txtQty.setText(qty);
            datePick.setValue(LocalDate.parse(date));
            //valueOf(datePick.setValue(date));

            txtDes.requestFocus();
        }
    }*/

    boolean existInventory(String id) throws SQLException, ClassNotFoundException {
        return inventoryBO.exist(id);
    }

    public void txtDesOnAction(ActionEvent event) {
        cBoxUnit.requestFocus();
    }

    public void cBoxUnitOnAction(ActionEvent event) {
        txtPar.requestFocus();
    }

    public void txtParOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    public void txtQtyOnAction(ActionEvent event) {
        datePick.requestFocus();
    }

    public void tbInvOnMouseClicked(MouseEvent mouseEvent) {
        tbInv.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            if(newSelection!=null){
                txtInv.setText(newSelection.getIngCode());
                txtDes.setText(newSelection.getDes());
                cBoxUnit.setValue(newSelection.getUnit());
                txtPar.setText(String.valueOf(newSelection.getPar()));
                txtQty.setText(String.valueOf(newSelection.getQty()));
                datePick.setValue(LocalDate.parse(newSelection.getDate()));
            }
        });
    }

    public void datePickOnAction(ActionEvent event) {

    }
}
