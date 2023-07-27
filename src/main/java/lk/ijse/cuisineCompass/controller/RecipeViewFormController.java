package lk.ijse.cuisineCompass.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cuisineCompass.bo.BOFactory;
import lk.ijse.cuisineCompass.bo.custom.RecipeBO;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class RecipeViewFormController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/cuisine_compass";
    private final static Properties props = new Properties();
    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView pic;

    @FXML
    private Label lblLatest;

    RecipeBO recipeBO = (RecipeBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RECIPE);

    @SneakyThrows
    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        //getLatest();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/sup_dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Supervisor Dashboard");
        stage.centerOnScreen();
    }

    public void btnAppOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/appertizer_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Appertizer Recipe");
        stage.centerOnScreen();
    }

    public void btnSoupOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/soup_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Soup/Salad Recipe");
        stage.centerOnScreen();
    }

    public void btnMainOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/main_course_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Main Course Recipe");
        stage.centerOnScreen();
    }

    public void btnDessertOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/dessert_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dessert Recipe");
        stage.centerOnScreen();
    }

    public void btnBevOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/beverage_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Beverage Recipe");
        stage.centerOnScreen();
    }

   public void getLatest() throws SQLException, ClassNotFoundException {
       //String rCode = RecipeModel.getLatest();

       String rCode = recipeBO.getLatest();

       //System.out.println(id);
       getLatestR(rCode);
   }

    public void getLatestR(String rCode) throws SQLException, ClassNotFoundException {
        //String des = RecipeModel.getLatestR(rCode);

        String des = recipeBO.getLatestR(rCode);

        lblLatest.setText(des);
        getImage(rCode);
        lblLatest.setText(des);
    }

    /*private void getLatestImage(String id) throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);
        ResultSet resultSet;
        resultSet = RecipeModel.getLatestImage(id);
        Image image = null;
        image = new Image(resultSet.getBinaryStream("image"));
        pic.setImage(image);
        pic.setPreserveRatio(false);
    }*/

    private void getImage(String rCode) throws SQLException, ClassNotFoundException {
        //Connection con = DriverManager.getConnection(URL, props);
        ResultSet resultSet = null;
        //resultSet = RecipeModel.getImage(rCode);
        resultSet = recipeBO.getImage(rCode);
        Image image = null;
        //image = new Image(resultSet.getBinaryStream("image"));
        if(resultSet == null){
            pic.setImage(null);
        }else{
            image = new Image(resultSet.getBinaryStream("image"));
            pic.setImage(image);
            pic.setPreserveRatio(false);
        }
    }

}
