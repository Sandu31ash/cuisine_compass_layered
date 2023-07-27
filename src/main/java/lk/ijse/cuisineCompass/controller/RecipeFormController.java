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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.cuisineCompass.bo.BOFactory;
import lk.ijse.cuisineCompass.bo.custom.CategoryBO;
import lk.ijse.cuisineCompass.bo.custom.IngredientBO;
import lk.ijse.cuisineCompass.bo.custom.RecipeBO;
import lk.ijse.cuisineCompass.bo.custom.RecipeIngDetailsBO;
import lk.ijse.cuisineCompass.db.DBConnection;
/*
import lk.ijse.cuisineCompass.dto.Category;
import lk.ijse.cuisineCompass.dto.Ingredient;
import lk.ijse.cuisineCompass.dto.Recipe;
import lk.ijse.cuisineCompass.dto.RecipeIngredientDetails;
import lk.ijse.cuisineCompass.dto.tm.CategoryTM;
import lk.ijse.cuisineCompass.dto.tm.IngredientTM;
import lk.ijse.cuisineCompass.dto.tm.RecipeIngredientDetailsTM;
import lk.ijse.cuisineCompass.dto.tm.RecipeTM;
*/
import lk.ijse.cuisineCompass.dto.*;
import lk.ijse.cuisineCompass.view.tdm.*;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class RecipeFormController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/cuisine_compass";
    private final static Properties props = new Properties();
    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    @FXML
    private Button btnBackAd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtDes;

    @FXML
    private JFXComboBox<String> cBoxIng;

    @FXML
    private TextArea txtMethod;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtRecipeCode;

    @FXML
    private JFXComboBox<String> cBoxUnit;

    @FXML
    private JFXComboBox<String> cBoxCourse;

    @FXML
    private JFXComboBox<String> cBoxCate;

    @FXML
    private TableView<CategoryTM> tbCategory;

    @FXML
    private TableColumn<?, ?> colAddedby;

    @FXML
    private TableColumn<?, ?> colCateCode;

    @FXML
    private TableColumn<?, ?> colCateCodeRecipe;

    @FXML
    private TableColumn<?, ?> colRecipeCode;

    @FXML
    private TableColumn<?, ?> colCourse;

    @FXML
    private TableColumn<?, ?> colDesCate;

    @FXML
    private TableColumn<?, ?> colDesIng;

    @FXML
    private TableColumn<?, ?> colIngCode;

    @FXML
    private TableColumn<?, ?> colMethodRecipe;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> coleDesRecipe;

    @FXML
    private TableView<RecipeTM> tbRecipe;

    @FXML
    private TableView<IngredientTM> tbIng;

    @FXML
    private TextField txtAddedby;

    @FXML
    private TableView<RecipeIngredientDetailsTM> tbRecipeIngD;

    @FXML
    private TableColumn<?, ?> colUnit;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRCode;

    @FXML
    private TableColumn<?, ?> colICode;

    @FXML
    private Label lbl;

    @FXML
    private ImageView pic;

    CategoryBO categoryBO = (CategoryBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.CATEGORY);
    RecipeBO recipeBO = (RecipeBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RECIPE);
    IngredientBO ingredientBO = (IngredientBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.INGREDIENT);
    RecipeIngDetailsBO recipeIngDetailsBO = (RecipeIngDetailsBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RECIPE_ING_DETAILS);

    @SneakyThrows
    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        txtAddedby.setText(LoginFormController.user);
        setCellValueFactory();
        setCellValueFactoryRecipe();
        setCellValueFactoryIng();
        setCellValueFactoryRecipeIngD();
        getAllCate();
        getAllRecipe();
        getAllIng();
        getAllRecipeIngD();
        loadUnits();
        loadCourses();
        loadIng();
        loadCate();

    }

    void setCellValueFactory() {
        colCateCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDesCate.setCellValueFactory(new PropertyValueFactory<>("des"));
    }

    /*void getAll() {
        try {
            ObservableList<CategoryTM> obList = FXCollections.observableArrayList();
            //List<Category> catList = CategoryModel.getAll();

            List<Category> catList = categoryBO.getAll();

            for(Category category : catList) {
                obList.add(new CategoryTM(
                        category.getCode(),
                        category.getType(),
                        category.getDes()
                ));
            }
            tbCategory.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }*/

    /*private void getAllCate() {
        try {
            ArrayList<CategoryDTO> allCate = categoryBO.getAllCate();

            for (CategoryDTO categoryDTO : allCate) {
                tbCategory.getItems().add(new CategoryTM(
                        categoryDTO.getCode(),
                        categoryDTO.getType(),
                        categoryDTO.getDes()
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

    void getAllCate() {
        try {
            ObservableList<CategoryTM> obList = FXCollections.observableArrayList();
            List<CategoryDTO> catList = categoryBO.getAllCate();

            for(CategoryDTO categoryDTO : catList) {
                obList.add(new CategoryTM(
                        categoryDTO.getCode(),
                        categoryDTO.getType(),
                        categoryDTO.getDes()
                ));
            }
            tbCategory.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    void setCellValueFactoryRecipe() {
        colRecipeCode.setCellValueFactory(new PropertyValueFactory<>("recipeCode"));
        coleDesRecipe.setCellValueFactory(new PropertyValueFactory<>("des"));
        colCateCodeRecipe.setCellValueFactory(new PropertyValueFactory<>("cateCode"));
        colMethodRecipe.setCellValueFactory(new PropertyValueFactory<>("method"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        colAddedby.setCellValueFactory(new PropertyValueFactory<>("addedBy"));
    }

    /*void getAllRecipe() {
        try {
            ObservableList<RecipeTM> obList = FXCollections.observableArrayList();
            List<Recipe> recipeList = RecipeModel.getAll();

            for(Recipe recipe : recipeList) {
                obList.add(new RecipeTM(
                        recipe.getRecipeCode(),
                        recipe.getDes(),
                        recipe.getCateCode(),
                        recipe.getMethod(),
                        recipe.getCourse(),
                        recipe.getAddedBy()
                ));
            }
            tbRecipe.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }*/

    /*private void getAllRecipe() {
        try {
            ArrayList<RecipeDTO> allRecipe = recipeBO.getAllRecipe();

            for (RecipeDTO recipeDTO : allRecipe) {
                tbRecipe.getItems().add(new RecipeTM(
                        recipeDTO.getRecipeCode(),
                        recipeDTO.getDes(),
                        recipeDTO.getCateCode(),
                        recipeDTO.getMethod(),
                        recipeDTO.getCourse(),
                        recipeDTO.getAddedBy()
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

    private void getAllRecipe() {
        try {
            ObservableList<RecipeTM> obList = FXCollections.observableArrayList();
            List<RecipeDTO> recipeList = recipeBO.getAllRecipe();

            for(RecipeDTO recipeDTO : recipeList) {
                obList.add(new RecipeTM(
                        recipeDTO.getRecipeCode(),
                        recipeDTO.getDes(),
                        recipeDTO.getCateCode(),
                        recipeDTO.getMethod(),
                        recipeDTO.getCourse(),
                        recipeDTO.getAddedBy()
                ));
            }
            tbRecipe.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    void setCellValueFactoryIng() {
        colIngCode.setCellValueFactory(new PropertyValueFactory<>("ingCode"));
        colDesIng.setCellValueFactory(new PropertyValueFactory<>("des"));
    }

    /*void getAllIng() {
        try {
            ObservableList<IngredientTM> obList = FXCollections.observableArrayList();
            List<Ingredient> ingList = IngredientModel.getAll();

            for(Ingredient ingredient : ingList) {
                obList.add(new IngredientTM(
                        ingredient.getIngCode(),
                        ingredient.getDes()
                ));
            }
            tbIng.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }*/

    /*private void getAllIng() {
        try {
            ArrayList<Ingredient> allIng = ingredientDAO.getAllIng();

            for (Ingredient ingredient : allIng) {
                tbIng.getItems().add(new IngredientTM(
                        ingredient.getIngCode(),
                        ingredient.getDes()
                ));
            }

            //tbIng.setItems();
            //while (rst.next()) {
              //  tblCustomers.getItems().add(new CustomerTM(rst.getString("id"), rst.getString("name"), rst.getString("address")));
            //}
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/

    private void getAllIng() {
        try {
            ObservableList<IngredientTM> obList = FXCollections.observableArrayList();
            List<IngredientDTO> ingList = ingredientBO.getAllIng();

            for(IngredientDTO ingredientDTO : ingList) {
                obList.add(new IngredientTM(
                        ingredientDTO.getIngCode(),
                        ingredientDTO.getDes()
                ));
            }
            tbIng.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    void setCellValueFactoryRecipeIngD() {
        colRCode.setCellValueFactory(new PropertyValueFactory<>("recipeCode"));
        colICode.setCellValueFactory(new PropertyValueFactory<>("ingCode"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    void getAllRecipeIngD() {
        try {
            ObservableList<RecipeIngredientDetailsTM> obList = FXCollections.observableArrayList();
            List<RecipeIngredientDetailsDTO> rIngList = recipeIngDetailsBO.getAllIng();

            for(RecipeIngredientDetailsDTO recipeIngDDTO : rIngList) {
                obList.add(new RecipeIngredientDetailsTM(
                        recipeIngDDTO.getRecipeCode(),
                        recipeIngDDTO.getIngCode(),
                        recipeIngDDTO.getUnit(),
                        recipeIngDDTO.getQty()
                ));
            }
            tbRecipeIngD.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    /*private void getAllRecipeIngD() {
        try {
            ArrayList<RecipeIngredientDetailsDTO> allRecipe = recipeIngDetailsBO.getAllIng();

            for (RecipeIngredientDetailsDTO recipeIngDDTO : allRecipe) {
                tbRecipeIngD.getItems().add(new RecipeIngredientDetailsTM(
                        recipeIngDDTO.getRecipeCode(),
                        recipeIngDDTO.getIngCode(),
                        recipeIngDDTO.getUnit(),
                        recipeIngDDTO.getQty()
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

    private void loadCourses() {
        List<String> courses = new ArrayList<>();

        courses.add(0, "Appertizer");
        courses.add(1, "Soup/Salad");
        courses.add(2, "Main Course");
        courses.add(3, "Dessert");
        courses.add(4, "Beverage");

        ObservableList<String> obList = FXCollections.observableArrayList();
        for (String course : courses) {
            obList.add(course);
        }
        cBoxCourse.setItems(obList);
    }

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

    private void loadIng() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        //List<String> codes = IngredientModel.getIng();

        List<String> codes = ingredientBO.getIngCode();

        for (String code : codes) {
            obList.add(code);
        }
        cBoxIng.setItems(obList);
    }

    private void loadCate() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        //List<String> codes = CategoryModel.getCate();

        List<String> codes = categoryBO.getAllCateCode();

        for (String code : codes) {
            obList.add(code);
        }
        cBoxCate.setItems(obList);
    }

    public void btnBackAdOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/admin_dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard");
        stage.centerOnScreen();
    }

    public void btnSaveOnAction(ActionEvent event) {

        String rCode = txtRecipeCode.getText();
        String iCode = cBoxIng.getValue();
        String unit = cBoxUnit.getValue();
        String qty = txtQty.getText();
        double qty1 = Double.parseDouble(qty);

        String des = txtDes.getText();
        String cCode = cBoxCate.getValue();
        String method = txtMethod.getText();
        String course = cBoxCourse.getValue();
        String addedBy = txtAddedby.getText();

        if (txtRecipeCode.getText().matches("^R\\d{3}$")) {
                    try {
                        //boolean isSaved = RecipeModel.isSaved(rCode, des, cCode, method, course, addedBy);
                        //boolean isSaved1 = RecipeModel.isSaved1(rCode, iCode, unit, qty1);

                        boolean isSaved = recipeBO.save(new RecipeDTO(rCode, des, cCode, method, course, addedBy));

                        boolean isSaved1 = recipeIngDetailsBO.save(new RecipeIngredientDetailsDTO(rCode, iCode, unit, qty1));

                        if (isSaved || isSaved1) {
                            getAllRecipe();
                            getAllRecipeIngD();
                            new Alert(Alert.AlertType.CONFIRMATION, "Recipe added!").show();
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        new Alert(Alert.AlertType.ERROR, "Error! Try Again").show();
                    }

        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Username!\nTry Again").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent event) {
        String rCode = txtRecipeCode.getText();
        String iCode = cBoxIng.getValue();
        String unit = cBoxUnit.getValue();
        String qty = txtQty.getText();
        double qty1 = Double.parseDouble(qty);

        String des = txtDes.getText();
        String cCode = cBoxCate.getValue();
        String method = txtMethod.getText();
        String course = cBoxCourse.getValue();
        String addedBy = txtAddedby.getText();

        try {
            //boolean isUpdated = RecipeModel.isUpdated(rCode, des, cCode, method, course, addedBy);
            //boolean isUpdated1 = RecipeModel.isUpdated1(rCode, iCode, unit, qty1);

            boolean isUpdated = recipeBO.update(new RecipeDTO(rCode, des, cCode, method, course, addedBy));

            boolean isUpdated1 = recipeIngDetailsBO.update(new RecipeIngredientDetailsDTO(rCode, iCode, unit, qty1));

            if (isUpdated && isUpdated1) {
                getAllRecipe();
                getAllRecipeIngD();
                new Alert(Alert.AlertType.CONFIRMATION, "Recipe details successfully updated!!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Error! Try Again").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete? :)", yes,no).showAndWait();
        if(result.orElse(no)==yes) {

            //boolean isDeleted = RecipeModel.isDeleted(txtRecipeCode.getText());
            //boolean isDeleted1 = RecipeModel.isDeleted1(txtRecipeCode.getText());

            String rCode = txtRecipeCode.getText();

            boolean isDeleted = recipeBO.delete(rCode);

            boolean isDeleted1 = recipeIngDetailsBO.delete(rCode);

            if (isDeleted && isDeleted1) {
                getAllRecipe();
                getAllRecipeIngD();
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            }
        }
    }

    public void btnClearOnAction(ActionEvent event) {
        txtRecipeCode.setText("");
        cBoxIng.setValue("");
        cBoxUnit.setValue("");
        txtQty.setText("");
        cBoxCate.setValue("");
        cBoxCourse.setValue("");
        txtDes.setText("");
        txtMethod.setText("");
        txtAddedby.setText("");
        txtRecipeCode.requestFocus();
        pic.setImage(null);
    }

    @SneakyThrows
    public void txtRecipeCodeOnAction(ActionEvent event) throws SQLException {
        String rCode = txtRecipeCode.getText();
        getRecipeDetails(rCode);
        getRecipeIngDetails(rCode);
        getImage(rCode);
        txtRecipeCode.requestFocus();
    }

    private void getImage(String rCode) throws SQLException, ClassNotFoundException {
        //Connection con = DriverManager.getConnection(URL, props);
        ResultSet resultSet = null;
        //resultSet = RecipeModel.getImage(rCode);
        resultSet = recipeBO.getImage(rCode);
        Image image = null;
        image = new Image(resultSet.getBinaryStream("image"));
        if(resultSet==null){
            pic.setImage(null);
        }else {
            pic.setImage(image);
            pic.setPreserveRatio(false);
        }
    }

    /*public void getRecipeDetails(String rCode) throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);
        String sql = "SELECT * FROM recipe WHERE recipe_code = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, rCode);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String recipeCode = resultSet.getString(1);
            String des = resultSet.getString(2);
            String cCode = resultSet.getString(3);
            String method = resultSet.getString(4);
            String course = resultSet.getString(5);
            String addedBy = resultSet.getString(6);

            txtRecipeCode.setText(recipeCode);
            txtDes.setText(des);
            cBoxCate.setValue(cCode);
            txtMethod.setText(method);
            cBoxCourse.setValue(course);
            txtAddedby.setText(addedBy);

            cBoxIng.requestFocus();

        } else {

            cBoxIng.setValue("");
            cBoxUnit.setValue("");
            txtQty.setText("");
            cBoxCate.setValue("");
            cBoxCourse.setValue("");
            txtDes.setText("");
            txtMethod.setText("");
            //txtAddedby.setText("");
            pic.setImage(null);
        }
        //txtIngCode.requestFocus();
    }*/

    public void getRecipeDetails(String rCode){
        if(!rCode.isEmpty()){
            RecipeDTO recipeDTO = null;
            try{
                recipeDTO = recipeBO.search(rCode);
            }catch (SQLException | ClassNotFoundException e){
                new Alert(Alert.AlertType.ERROR, "Something went wrong :"+e).show();
            }
            if (recipeDTO != null){
                txtRecipeCode.setText(recipeDTO.getRecipeCode());
                txtDes.setText(recipeDTO.getDes());
                cBoxCate.setValue(recipeDTO.getCateCode());
                txtMethod.setText(recipeDTO.getMethod());
                cBoxCourse.setValue(recipeDTO.getCourse());
                txtAddedby.setText(recipeDTO.getAddedBy());

                cBoxIng.requestFocus();
            }else{
                cBoxIng.setValue("");
                cBoxUnit.setValue("");
                txtQty.setText("");
                cBoxCate.setValue("");
                cBoxCourse.setValue("");
                txtDes.setText("");
                txtMethod.setText("");
                //txtAddedby.setText("");
                pic.setImage(null);
            }
        }
    }

    //public void getRecipeIngDetails(String rCode) throws SQLException {
      /*  Connection con = DriverManager.getConnection(URL, props);
        String sql = "SELECT * FROM recipe_ingredient_details WHERE recipe_code = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, rCode);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String recipeCode = resultSet.getString(1);
            String iCode = resultSet.getString(2);
            String unit = resultSet.getString(3);
            String qty = resultSet.getString(4);

            txtRecipeCode.setText(recipeCode);
            cBoxIng.setValue(iCode);
            cBoxUnit.setValue(unit);
            txtQty.setText(qty);

            cBoxIng.requestFocus();

        } else {
            //txtRecipeCode.setText("");
            cBoxIng.setValue("");
            cBoxUnit.setValue("");
            txtQty.setText("");
            cBoxCate.setValue("");
            cBoxCourse.setValue("");
            txtDes.setText("");
            txtMethod.setText("");
            //txtAddedby.setText("");
            pic.setImage(null);
        }
        cBoxIng.requestFocus();
    }*/

    public void getRecipeIngDetails(String rCode){
        if(!rCode.isEmpty()){
            RecipeIngredientDetailsDTO recipeIngDTO = null;
            try{
                recipeIngDTO = recipeIngDetailsBO.search(rCode);
            }catch (SQLException | ClassNotFoundException e){
                new Alert(Alert.AlertType.ERROR, "Something went wrong :"+e).show();
            }
            if (recipeIngDTO != null){
                txtRecipeCode.setText(recipeIngDTO.getRecipeCode());
                cBoxIng.setValue(recipeIngDTO.getIngCode());
                cBoxUnit.setValue(recipeIngDTO.getUnit());
                txtQty.setText(String.valueOf(recipeIngDTO.getQty()));

                cBoxIng.requestFocus();
            }else{
                cBoxIng.setValue("");
                cBoxUnit.setValue("");
                txtQty.setText("");
                cBoxCate.setValue("");
                cBoxCourse.setValue("");
                txtDes.setText("");
                txtMethod.setText("");
                //txtAddedby.setText("");
                pic.setImage(null);
            }
        }
    }

    public void cBoxIngOnAction(ActionEvent event) {
        cBoxUnit.requestFocus();
    }

    public void txtQtyOnAction(ActionEvent event) { cBoxCate.requestFocus(); }

    public void cBoxCateOnAction(ActionEvent event) {
        cBoxCourse.requestFocus();
    }

    public void cBoxCourseOnAction(ActionEvent event) {
        txtAddedby.requestFocus();
    }

    public void cBoxUnitOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    public void txtAddedbyOnAction(ActionEvent event) { txtDes.requestFocus(); }


    public void tbRecipeOnMouseClicked(MouseEvent mouseEvent) {
        tbRecipe.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            if(newSelection!=null){
                txtRecipeCode.setText(newSelection.getRecipeCode());
                txtDes.setText(newSelection.getDes());
                cBoxCate.setValue(newSelection.getCateCode());
                txtMethod.setText(newSelection.getMethod());
                cBoxCourse.setValue(newSelection.getCourse());
                txtAddedby.setText(newSelection.getAddedBy());
                /*String rCode = txtRecipeCode.getText();
                getImage(rCode);*/
            }
        });
    }

    /*private void getImage(String rCode) throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);
        ResultSet resultSet = null;
        resultSet = RecipeModel.getImage(rCode);
        Image image = null;
        image = new Image(resultSet.getBinaryStream("image"));
        pic.setImage(image);
        pic.setPreserveRatio(false);
    }*/

    public void tbRecipeIngDOnMouseClicked(MouseEvent mouseEvent) {
        tbRecipeIngD.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            if(newSelection!=null){
                //txtRecipeCode.setText(newSelection.getRecipeCode());
                cBoxIng.setValue(newSelection.getIngCode());
                cBoxUnit.setValue(newSelection.getUnit());
                txtQty.setText(String.valueOf(newSelection.getQty()));
            }
        });
    }

    public void btnAddOnAction(ActionEvent event) throws SQLException {
        /*cBoxIng.setValue("");
        cBoxUnit.setValue("");
        txtQty.setText("");
        cBoxIng.requestFocus();*/
        String rCode = txtRecipeCode.getText();
        String iCode= cBoxIng.getValue();
        String unit = cBoxUnit.getValue();
        double qty = Double.parseDouble(txtQty.getText());

        if (txtRecipeCode.getText().matches("^R\\d{3}$")) {
            try {
                //boolean isSet = RecipeModel.isSetIng(rCode, iCode, unit, qty);

                boolean isSet = recipeIngDetailsBO.save(new RecipeIngredientDetailsDTO(rCode, iCode, unit, qty));

                if (isSet) {
                    getAllRecipeIngD();
                    new Alert(Alert.AlertType.CONFIRMATION, "Ingredient added!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Error! Try Again").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid recipe code ! Try Again").show();
        }
    }

    public void btnAddOnMouseEntered(MouseEvent mouseEvent) {
        lbl.setVisible(true);
    }

    public void btnAddOnMouseExited(MouseEvent mouseEvent) {
        lbl.setVisible(false);
    }

    public void btnPicOnAction(ActionEvent event) {
        String id = txtRecipeCode.getText();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                FileInputStream fis = new FileInputStream(file);

                String sql = "INSERT INTO image_recipe (image, id) VALUES (?, ?)";
                PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
                pstm.setBinaryStream(1, fis, fis.available());
                pstm.setString(2, id);
                int x = pstm.executeUpdate();

                if (x > 0) {
                    //ResultSet resultSet = RecipeModel.getImage(id);
                    ResultSet resultSet = recipeBO.getImage(id);
                    if (resultSet.next()) {
                        Image image = new Image(resultSet.getBinaryStream("image"));
                        pic.setImage(image);
                    }
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
