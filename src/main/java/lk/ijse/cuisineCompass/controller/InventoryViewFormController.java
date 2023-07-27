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
import lk.ijse.cuisineCompass.bo.custom.InventoryBO;
import lk.ijse.cuisineCompass.dto.InventoryDTO;
import lk.ijse.cuisineCompass.view.tdm.InventoryTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryViewFormController implements Initializable {

    @FXML
    private Button btnBackAdInv;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDes;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPar;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnit;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<InventoryTM> tbInvAd;

    InventoryBO inventoryBO = (InventoryBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.INVENTORY);

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAllInv();
    }
    void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("iCode"));
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
            tbInvAd.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    /*private void getAllInv() {
        try {
            ArrayList<InventoryDTO> allInv = inventoryBO.getAllInv();

            for (InventoryDTO inventoryDTO : allInv) {
                tbInvAd.getItems().add(new InventoryTM(
                        inventoryDTO.getIngCode(),
                        inventoryDTO.getDes(),
                        inventoryDTO.getUnit(),
                        inventoryDTO.getPar(),
                        inventoryDTO.getQty(),
                        inventoryDTO.getDate()
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/

    public void btnBackAdInvOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/admin_dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard");
        stage.centerOnScreen();
    }

}
