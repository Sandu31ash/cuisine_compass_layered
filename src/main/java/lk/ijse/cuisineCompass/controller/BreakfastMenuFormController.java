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
import lk.ijse.cuisineCompass.bo.custom.MenuBO;
import lk.ijse.cuisineCompass.dto.MenuDTO;
import lk.ijse.cuisineCompass.view.tdm.MenuTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class BreakfastMenuFormController implements Initializable {
    @FXML
    private Button btnBackBreakfast;

    @FXML
    private TableColumn<?, ?> colDishApp;

    @FXML
    private TableColumn<?, ?> colDishBev;

    @FXML
    private TableColumn<?, ?> colDishDess;

    @FXML
    private TableColumn<?, ?> colDishMain;

    @FXML
    private TableColumn<?, ?> colDishSoup;

    @FXML
    private TableColumn<?, ?> colPriceApp;

    @FXML
    private TableColumn<?, ?> colPriceBev;

    @FXML
    private TableColumn<?, ?> colPriceDess;

    @FXML
    private TableColumn<?, ?> colPriceMain;

    @FXML
    private TableColumn<?, ?> colPriceSoup;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<MenuTM> tbApp;

    @FXML
    private TableView<MenuTM> tbBev;

    @FXML
    private TableView<MenuTM> tbDessert;

    @FXML
    private TableView<MenuTM> tbMain;

    @FXML
    private TableView<MenuTM> tbSoup;

    MenuBO menuBO = (MenuBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.MENU);

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAllApp();
        getAllSoup();
        getAllMain();
        getAllDessert();
        getAllBev();
        //loadJobRoles();
    }

    void setCellValueFactory() {
        colDishApp.setCellValueFactory(new PropertyValueFactory<>("dish"));
        colPriceApp.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDishSoup.setCellValueFactory(new PropertyValueFactory<>("dish"));
        colPriceSoup.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDishMain.setCellValueFactory(new PropertyValueFactory<>("dish"));
        colPriceMain.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDishDess.setCellValueFactory(new PropertyValueFactory<>("dish"));
        colPriceDess.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDishBev.setCellValueFactory(new PropertyValueFactory<>("dish"));
        colPriceBev.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void getAllApp() {
        try {
            ObservableList<MenuTM> obList = FXCollections.observableArrayList();
            //List<Menu> menuList = MenuModel.getAllApp("breakfast");

            List<MenuDTO> menuList = menuBO.getAllApp("Appertizer", "breakfast");

            for(MenuDTO menuDTO : menuList) {
                obList.add(new MenuTM(
                        menuDTO.getDish(),
                        menuDTO.getPrice()
                ));
            }
            tbApp.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    /*void getAllApp() {
        try {
            ObservableList<MenuTM> obList = FXCollections.observableArrayList();
            List<Menu> menuList = MenuModel.getAllApp("breakfast");

            for(Menu menu : menuList) {
                obList.add(new MenuTM(
                        menu.getDish(),
                        menu.getPrice()
                ));
            }
            tbApp.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }*/

    /*void getAllSoup() {
        try {
            ObservableList<MenuTM> obList = FXCollections.observableArrayList();
            List<Menu> menuList = MenuModel.getAllSoup("breakfast");

            for(Menu menu : menuList) {
                obList.add(new MenuTM(
                        menu.getDish(),
                        menu.getPrice()
                ));
            }
            tbSoup.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }*/

    private void getAllSoup() {
        try {
            ObservableList<MenuTM> obList = FXCollections.observableArrayList();
            //List<Menu> menuList = MenuModel.getAllApp("breakfast");

            List<MenuDTO> menuList = menuBO.getAllApp("Soup/Salad", "breakfast");

            for(MenuDTO menuDTO : menuList) {
                obList.add(new MenuTM(
                        menuDTO.getDish(),
                        menuDTO.getPrice()
                ));
            }
            tbSoup.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    /*void getAllMain() {
        try {
            ObservableList<MenuTM> obList = FXCollections.observableArrayList();
            List<Menu> menuList = MenuModel.getAllMain("breakfast");

            for(Menu menu : menuList) {
                obList.add(new MenuTM(
                        menu.getDish(),
                        menu.getPrice()
                ));
            }
            tbMain.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }*/

    private void getAllMain() {
        try {
            ObservableList<MenuTM> obList = FXCollections.observableArrayList();
            //List<Menu> menuList = MenuModel.getAllApp("breakfast");

            List<MenuDTO> menuList = menuBO.getAllApp("Main Course", "breakfast");

            for(MenuDTO menuDTO : menuList) {
                obList.add(new MenuTM(
                        menuDTO.getDish(),
                        menuDTO.getPrice()
                ));
            }
            tbMain.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    /*void getAllDessert() {
        try {
            ObservableList<MenuTM> obList = FXCollections.observableArrayList();
            List<Menu> menuList = MenuModel.getAllDessert("breakfast");

            for(Menu menu : menuList) {
                obList.add(new MenuTM(
                        menu.getDish(),
                        menu.getPrice()
                ));
            }
            tbDessert.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }*/

    private void getAllDessert() {
        try {
            ObservableList<MenuTM> obList = FXCollections.observableArrayList();
            //List<Menu> menuList = MenuModel.getAllApp("breakfast");

            List<MenuDTO> menuList = menuBO.getAllApp("Dessert", "breakfast");

            for(MenuDTO menuDTO : menuList) {
                obList.add(new MenuTM(
                        menuDTO.getDish(),
                        menuDTO.getPrice()
                ));
            }
            tbDessert.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    /*void getAllBev() {
        try {
            ObservableList<MenuTM> obList = FXCollections.observableArrayList();
            List<Menu> menuList = MenuModel.getAllBev("breakfast");

            for(Menu menu : menuList) {
                obList.add(new MenuTM(
                        menu.getDish(),
                        menu.getPrice()
                ));
            }
            tbBev.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }*/

    private void getAllBev() {
        try {
            ObservableList<MenuTM> obList = FXCollections.observableArrayList();
            //List<Menu> menuList = MenuModel.getAllApp("breakfast");

            List<MenuDTO> menuList = menuBO.getAllApp("Beverage", "breakfast");

            for(MenuDTO menuDTO : menuList) {
                obList.add(new MenuTM(
                        menuDTO.getDish(),
                        menuDTO.getPrice()
                ));
            }
            tbBev.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    public void btnBackBreakfastOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/menu_view_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.centerOnScreen();
    }
}
