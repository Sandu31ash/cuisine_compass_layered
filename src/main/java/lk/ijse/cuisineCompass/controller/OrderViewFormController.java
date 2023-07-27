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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cuisineCompass.bo.BOFactory;
import lk.ijse.cuisineCompass.bo.custom.PurchaseOrderBO;
import lk.ijse.cuisineCompass.dto.OrderDTO;
import lk.ijse.cuisineCompass.dto.OrderDetailsDTO;
import lk.ijse.cuisineCompass.view.tdm.OrderDetailsTM;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class OrderViewFormController implements Initializable {
    @FXML
    private Button btnBackAdOrder;

    @FXML
    private JFXComboBox<String> cBoxCode;

    @FXML
    private TableColumn<?, ?> colInv;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTot;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<OrderDetailsTM> tbOrderAd;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtOrderedBy;

    @FXML
    private TextField txtSupp;

    PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PURCHASE_ORDER);

    @SneakyThrows
    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        //setCellValueFactory();
        //getAll();
        loadCodes();
    }
    void setCellValueFactory() {
        colInv.setCellValueFactory(new PropertyValueFactory<>("iCode"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTot.setCellValueFactory(new PropertyValueFactory<>("tot"));
    }

    private void loadCodes() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        //List<String> codes = OrderModel.getCodes();

        List<String> codes = purchaseOrderBO.getAllOCodes();

        for (String code : codes) {
            obList.add(code);
        }
        cBoxCode.setItems(obList);
    }

    void getAll(String oCode) {
        //Order order=null;
        //order = OrderModel.getAll(oCode);

        OrderDTO orderDTO = null;
        try {
            orderDTO = purchaseOrderBO.search(oCode);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
        //cBoxCode.setValue(orderDTO.getCode());
        txtDate.setText(orderDTO.getDate());
        txtOrderedBy.setText(orderDTO.getOrderBy());
        txtSupp.setText(orderDTO.getId());
    }

    /*void getAllDetails(String oCode) {
        try {
            ObservableList<OrderTM> obList = FXCollections.observableArrayList();
            List<Order> orderList = OrderModel.getAllDetails(oCode);

            for(Order order : orderList) {
                obList.add(new OrderTM(
                        order.getICode(),
                        order.getPrice(),
                        order.getQty(),
                        order.getTot()
                ));
            }
            tbOrderAd.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error nnnn!").show();
        }
    }*/

    private void getAllDetails(String oCode) {
        try {
            ObservableList<OrderDetailsTM> obList = FXCollections.observableArrayList();
            List<OrderDetailsDTO> orderDList =  purchaseOrderBO.search2(oCode);

            for(OrderDetailsDTO orderDetailsDTO : orderDList) {
                obList.add(new OrderDetailsTM(
                        orderDetailsDTO.getCode(),
                        orderDetailsDTO.getiCode(),
                        orderDetailsDTO.getPrice(),
                        orderDetailsDTO.getQty(),
                        orderDetailsDTO.getTot()
                ));
            }
            tbOrderAd.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    public void btnBackAdOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/admin_dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard");
        stage.centerOnScreen();
    }

    public void cBoxCodeOnAction(ActionEvent event) {
        String oCode = cBoxCode.getValue();
        getAll(oCode);
        getAllDetails(oCode);
        setCellValueFactory();
    }
}
