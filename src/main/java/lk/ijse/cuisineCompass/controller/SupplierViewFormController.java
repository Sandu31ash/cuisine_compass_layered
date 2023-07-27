package lk.ijse.cuisineCompass.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cuisineCompass.bo.BOFactory;
import lk.ijse.cuisineCompass.bo.custom.SupplierBO;
//import lk.ijse.cuisineCompass.dto.Supplier;
import lk.ijse.cuisineCompass.dto.SupplierDTO;
//import lk.ijse.cuisineCompass.dto.tm.SupplierTM;
import lk.ijse.cuisineCompass.view.tdm.SupplierTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierViewFormController implements Initializable {
    @FXML
    private Button btnBackAdSupp;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colMail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<SupplierTM> tbSuppAd;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAllSupplier();
    }
    void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private void getAllSupplier() {
        try {
            ObservableList<SupplierTM> obList = FXCollections.observableArrayList();
            //List<Supplier> supList = SupplierModel.getAll();

            List<SupplierDTO> supList = supplierBO.getAllSupplier();

            for(SupplierDTO supplierDTO : supList) {
                obList.add(new SupplierTM(
                        supplierDTO.getId(),
                        supplierDTO.getName(),
                        supplierDTO.getEmail(),
                        supplierDTO.getContact()
                ));
            }
            tbSuppAd.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    /*private void getAllSupplier() {
        try {
            ArrayList<SupplierDTO> allSuppliers = supplierBO.getAllSupplier();

            for (SupplierDTO supplierDTO : allSuppliers) {
                tbSuppAd.getItems().add(new SupplierTM(
                        supplierDTO.getId(),
                        supplierDTO.getName(),
                        supplierDTO.getContact(),
                        supplierDTO.getEmail()
                ));
            }

            //tbEmp.setItems();
            //while (rst.next()) {
              //  tblCustomers.getItems().add(new CustomerTM(rst.getString("id"), rst.getString("name"), rst.getString("address")));
            //}
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/

    public void btnBackAdSuppOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/admin_dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard");
        stage.centerOnScreen();
    }
}
