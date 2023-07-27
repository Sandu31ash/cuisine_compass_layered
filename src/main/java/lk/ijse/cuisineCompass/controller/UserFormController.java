package lk.ijse.cuisineCompass.controller;

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
import lk.ijse.cuisineCompass.bo.custom.EmployeeBO;
import lk.ijse.cuisineCompass.bo.custom.UserBO;
import lk.ijse.cuisineCompass.db.DBConnection;
//import lk.ijse.cuisineCompass.dto.User;
//import lk.ijse.cuisineCompass.dto.tm.UserTM;
import lk.ijse.cuisineCompass.dto.EmployeeDTO;
import lk.ijse.cuisineCompass.dto.UserDTO;
import lk.ijse.cuisineCompass.view.tdm.UserTM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class UserFormController implements Initializable{
    private static final String URL = "jdbc:mysql://localhost:3306/cuisine_compass";
    private final static Properties props = new Properties();
    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    @FXML
    private AnchorPane pane;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtJob;
    @FXML
    private PasswordField txtPw;
    @FXML
    private TextField txtUserName;
    @FXML
    private TableView<UserTM> tbUser;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colJob;
    @FXML
    private TableColumn<?, ?> colPw;
    @FXML
    private TableColumn<?, ?> colUsername;
    @FXML
    private Label lblPw;
    @FXML
    private Label lblUser;
    @FXML
    private ImageView pic;
    @FXML
    private Button btnUpload;

    UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAllUser();
        //loadJobRoles();
    }

    void setCellValueFactory() {
        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPw.setCellValueFactory(new PropertyValueFactory<>("password"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJob.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
    }

    /*private void loadJobRoles() {
        List<String> jobs = new ArrayList<>();

        jobs.add(0, "Head Chef");
        jobs.add(1, "Sous Chef");
        jobs.add(2, "Chef de Partie");

        ObservableList<String> obList = FXCollections.observableArrayList();
        for (String code : jobs) {
            obList.add(code);
        }
        cBoxJ.setItems(obList);
    }*/

    public void btnBackUserOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/cuisineCompass/view/admin_dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard");
        stage.centerOnScreen();
    }

    public void btnClearOnAction(ActionEvent event) {
        txtId.setText("");
        txtJob.setText("");
        txtUserName.setText("");
        txtPw.setText("");

        lblUser.setText("");
        lblPw.setText("");
    }

    public void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete? :)", yes,no).showAndWait();
        if(result.orElse(no)==yes) {

            //boolean isDeleted = UserModel.isDeleted(txtId.getText());

            String uId = txtId.getText();

            boolean isDeleted = userBO.delete(uId);

            if (isDeleted) {
                getAllUser();
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String jobRole = txtJob.getText();
        String userName = txtUserName.getText();
        String pW = txtPw.getText();

        if (txtUserName.getText() == null || txtUserName.getText().isEmpty()) {
            lblUser.setText("Username or Password cannot be empty  !");
            //new Alert(Alert.AlertType.ERROR, "Username or Password cannot be empty!").show();
            txtUserName.requestFocus();
        }else if (txtPw.getText() == null || txtPw.getText().isEmpty()) {
            lblPw.setText("Username or Password cannot be empty  !");
            //new Alert(Alert.AlertType.ERROR, "Username or Password cannot be empty!").show();
        } else if (txtPw.getText().matches("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W).{8,}$")) {
            try {
                //boolean isUpdated = UserModel.isUpdated(id, jobRole, userName, pW);

                boolean isUpdated = userBO.update(new UserDTO(userName, pW, id, jobRole));

                if (isUpdated) {
                    getAllUser();
                    new Alert(Alert.AlertType.CONFIRMATION, "User account successfully updated!!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Error! Try Again").show();
            }
        }else {
            lblPw.setText("Invalid Password!\nTry Again");
        }

    }

    public void btnCreateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        lblUser.setText("");
        lblPw.setText("");

        String id = txtId.getText();
        String jobRole = txtJob.getText();
        String userName = txtUserName.getText();
        String pW = txtPw.getText();

        if (txtUserName.getText() == null || txtUserName.getText().isEmpty()) {
            lblUser.setText("Username or Password cannot be empty  !");
            //new Alert(Alert.AlertType.ERROR, "Username or Password cannot be empty!").show();
            txtUserName.requestFocus();
        }else if (txtPw.getText() == null || txtPw.getText().isEmpty()) {
            lblPw.setText("Username or Password cannot be empty  !");
            //new Alert(Alert.AlertType.ERROR, "Username or Password cannot be empty!").show();
        } else if (txtPw.getText().matches("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W).{8,}$")) {
            //boolean isCreated = UserModel.isCreated(userName, pW, id, jobRole);

            boolean isCreated = userBO.save(new UserDTO(userName, pW, id, jobRole));

            if (isCreated) {
                getAllUser();
                new Alert(Alert.AlertType.CONFIRMATION, "Account successfully created!").show();
            }
            //new Alert(Alert.AlertType.ERROR, "Invalid Password!\nTry Again").show();
        }else {
            lblPw.setText("Invalid Password!\nTry Again");
        }
    }

    private void getAllUser() {
        try {
            ObservableList<UserTM> obList = FXCollections.observableArrayList();
            //List<User> userList = UserModel.getAll();

            List<UserDTO> userList = userBO.getAllUser();

            for(UserDTO userDTO : userList) {
                obList.add(new UserTM(
                        userDTO.getId(),
                        userDTO.getJobRole(),
                        userDTO.getUserName(),
                        userDTO.getPassword()
                ));
            }
            tbUser.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    /*private void getAllUser() {
        try {
            ArrayList<UserDTO> allUsers = userBO.getAllUser();

            for (UserDTO userDTO : allUsers) {
                tbUser.getItems().add(new UserTM(
                        userDTO.getUserName(),
                        userDTO.getPassword(),
                        userDTO.getId(),
                        userDTO.getJobRole()
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

    private void getImage(String userName) throws SQLException, ClassNotFoundException {
        //Connection con = DriverManager.getConnection(URL, props);
        ResultSet resultSet = null;
        //resultSet = UserModel.getImage(userName);
        resultSet = userBO.getImage(userName);
        Image image = null;
        //image = new Image(resultSet.getBinaryStream("image"));
        //if(resultSet == null){
        //pic.setImage(null);
        //}else{
        image = new Image(resultSet.getBinaryStream("image"));
        pic.setImage(image);
        pic.setPreserveRatio(false);
        //}
    }

    //public void txtIdOnAction(ActionEvent event) throws SQLException {
      /*  String id = txtId.getText();

        Connection con = DriverManager.getConnection(URL, props);
        String sql = "SELECT * FROM user WHERE emp_id = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String job_role = resultSet.getString(4);
            String userName = resultSet.getString(1);
            String pW = resultSet.getString(2);

            txtJob.setText(job_role);
            txtUserName.setText(userName);
            getImage(txtUserName.getText());
            txtPw.setText(pW);

        } else {
            Connection con1 = DriverManager.getConnection(URL, props);
            String sql1 = "SELECT * FROM employee WHERE emp_id = ?";

            PreparedStatement pstm1 = con1.prepareStatement(sql1);
            pstm1.setString(1, id);

            ResultSet resultSet1 = pstm1.executeQuery();

            if (resultSet1.next()) {
                String job_role = resultSet1.getString(3);

                txtJob.setText(job_role);
                txtUserName.setText("");
                txtPw.setText("");
                pic.setImage(null);

                if((job_role).equalsIgnoreCase("Head Chef") || (job_role).equalsIgnoreCase("Sous Chef") || (job_role).equalsIgnoreCase("Chef de Partie")){
                    txtUserName.requestFocus();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Cannot create account for service level employees!").show();
                }
            }else{
                txtJob.setText("");
                new Alert(Alert.AlertType.ERROR, "Employee doesn't exist!").show();
            }
        }
        txtJob.requestFocus();
    }*/

    public void txtIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        if(!id.isEmpty()){
            UserDTO userDTO = null;
            try{
                userDTO = userBO.search(id);
            }catch (SQLException | ClassNotFoundException e){
                new Alert(Alert.AlertType.ERROR, "Something went wrong :"+e).show();
            }
            if (userDTO != null){

                txtJob.setText(userDTO.getJobRole());
                txtUserName.setText(userDTO.getUserName());
                getImage(userDTO.getUserName());
                txtPw.setText(userDTO.getPassword());

            }else{
                //new Alert(Alert.AlertType.INFORMATION, "This employee id isn't exist").show();
                //txtId.requestFocus();

                EmployeeDTO employeeDTO = employeeBO.search(id);

                if (employeeDTO != null){
                    txtJob.setText(employeeDTO.getJobRole());
                    txtUserName.setText("");
                    txtPw.setText("");
                    pic.setImage(null);

                    if((employeeDTO.getJobRole()).equalsIgnoreCase("Head Chef") || (employeeDTO.getJobRole()).equalsIgnoreCase("Sous Chef") || (employeeDTO.getJobRole()).equalsIgnoreCase("Chef de Partie")){
                        txtUserName.requestFocus();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Cannot create account for service level employees!").show();
                    }
                }else{
                    txtJob.setText("");
                    new Alert(Alert.AlertType.ERROR, "Employee doesn't exist!").show();
                }
            }
        }
    }

    public void txtJobOnAction(ActionEvent event) { txtUserName.requestFocus(); }

    public void txtUserNameOnAction(ActionEvent event) {
        txtPw.requestFocus();
    }

    public void txtPwOnAction(){    }

    public void tbUserOnMouseClicked(MouseEvent mouseEvent) {
        tbUser.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            if(newSelection!=null){
                txtId.setText(newSelection.getId());
                txtJob.setText(newSelection.getJobRole());
                txtUserName.setText(newSelection.getUserName());
                txtPw.setText(newSelection.getPassword());
                //txtPw.setText();
            }
        });
    }

    public void btnUploadOnAction(ActionEvent event) {
        String userName = txtUserName.getText();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                FileInputStream fis = new FileInputStream(file);

                String sql = "INSERT INTO image_user (image, user_name) VALUES (?, ?)";
                PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
                pstm.setBinaryStream(1, fis, fis.available());
                pstm.setString(2, userName);
                int x = pstm.executeUpdate();

                if (x > 0) {
                    //ResultSet resultSet = UserModel.getImage(userName);
                    ResultSet resultSet = userBO.getImage(userName);
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
