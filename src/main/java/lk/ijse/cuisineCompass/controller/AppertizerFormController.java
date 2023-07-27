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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//import lk.ijse.cuisineCompass.dto.RecipeIngredientDetails;
//import lk.ijse.cuisineCompass.dto.tm.RecipeIngredientDetailsTM;
import lk.ijse.cuisineCompass.bo.BOFactory;
import lk.ijse.cuisineCompass.bo.custom.RecipeBO;
import lk.ijse.cuisineCompass.bo.custom.RecipeIngDetailsBO;
import lk.ijse.cuisineCompass.dto.RecipeDTO;
import lk.ijse.cuisineCompass.dto.RecipeIngredientDetailsDTO;
import lk.ijse.cuisineCompass.view.tdm.RecipeIngredientDetailsTM;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class AppertizerFormController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/cuisine_compass";
    private final static Properties props = new Properties();
    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    @FXML
    private Button btnBackApp;

    @FXML
    private JFXComboBox<String> cBoxRecipe;

    @FXML
    private TableColumn<?, ?> colIng;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnit;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<RecipeIngredientDetailsTM> tbRecipe;

    @FXML
    private TextArea txtMethod;

    @FXML
    private ImageView pic;

    RecipeBO recipeBO = (RecipeBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RECIPE);
    RecipeIngDetailsBO recipeIngDBO = (RecipeIngDetailsBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RECIPE_ING_DETAILS);

    @SneakyThrows
    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        loadRecipes();
    }

    void setCellValueFactory() {
        colIng.setCellValueFactory(new PropertyValueFactory<>("ingCode"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void loadRecipes() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        //List<String> recipes = RecipeModel.getRecipeApp();

        ArrayList<RecipeDTO> recipes = recipeBO.getAllRecipeApp("appertizer");

        for (RecipeDTO recipe : recipes) {
            obList.add(String.valueOf(recipe));
        }
        cBoxRecipe.setItems(obList);
    }

    void getAllRCode() {
        try {
            //String rCode = RecipeModel.getAllRCode(cBoxRecipe.getValue());
            String rCode = recipeBO.getRecipeCode(cBoxRecipe.getValue());
            getAll(rCode);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    void getAll(String rCode) {
        try {
            ObservableList<RecipeIngredientDetailsTM> obList = FXCollections.observableArrayList();
            //List<RecipeIngredientDetails> recipeList = RecipeModel.getAllRecipeIng(String.valueOf(RecipeModel.getAllRCode(String.valueOf(RecipeModel.getRecipe()))));
            //List<RecipeIngredientDetails> recipeList = RecipeModel.getAllRecipeIng(rCode);

            List<RecipeIngredientDetailsDTO> recipeList = recipeIngDBO.getAllIngD(rCode);

            for(RecipeIngredientDetailsDTO recipeIngDDTO : recipeList) {
                obList.add(new RecipeIngredientDetailsTM(
                        recipeIngDDTO.getIngCode(),
                        recipeIngDDTO.getUnit(),
                        recipeIngDDTO.getQty()
                ));
            }
            tbRecipe.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    void getMethod() throws SQLException, ClassNotFoundException {
        //String method = RecipeModel.getMethod(cBoxRecipe.getValue());

        String method = recipeBO.getMethod(cBoxRecipe.getValue());

        txtMethod.setText(method);
    }

    private void getImage(String rCode) throws SQLException, ClassNotFoundException {
        //Connection con = DriverManager.getConnection(URL, props);
        ResultSet resultSet = null;
        //resultSet = RecipeModel.getImage(rCode);
        resultSet = recipeBO.getImage(rCode);
        Image image = null;
        image = new Image(resultSet.getBinaryStream("image"));
        pic.setImage(image);
        pic.setPreserveRatio(false);
    }

    public void btnBackAppOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/recipe_view_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Recipe");
        stage.centerOnScreen();
    }

    @SneakyThrows
    public void cBoxRecipeOnAction(ActionEvent event) {getAllRCode();
        getAllRCode();
        //getAll();
        getMethod();
        setCellValueFactory();
        //getImage(RecipeModel.getAllRCode(cBoxRecipe.getValue()));
        getImage(recipeBO.getRecipeCode(cBoxRecipe.getValue()));
    }
}
