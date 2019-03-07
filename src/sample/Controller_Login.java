package sample;

import Connectvy.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Controller_Login {

    ConnectionClass connectionClass = new ConnectionClass();

    Connection connection = connectionClass.getConnection();

    @FXML
    private JFXButton Btn_Login_LOGIN;
    @FXML
    private JFXButton Btn_Cancle_Login;
    @FXML
    private JFXTextField Txfiled_UserID_Login;
    @FXML
    private JFXPasswordField Txfiled_Password_Login;
    @FXML
    private JFXButton Btn_AR_Login;
    @FXML
    private JFXButton Btn_EN_Login;
    @FXML
    private ImageView icon_UserID_Login;
    @FXML
    private ImageView icon_Password_Login;
    @FXML
    private AnchorPane LoginWindow;

    int count = 0;
    //0 equal EN    And 1 == AR

    @FXML
    private void M_Btn_AR_Login(ActionEvent event) {
        count = 1;
        Btn_AR_Login.setText("العربية");
        Btn_AR_Login.setLayoutX(350);
        //Btn_AR_Login.setLayoutY(15);

        Btn_EN_Login.setText("الإنجليزية");
        Btn_EN_Login.setLayoutX(280);
        // Btn_EN_Login.setLayoutY(15);

        icon_UserID_Login.setLayoutX(378);
        //icon_UserID_Login.setLayoutY(15);

        icon_Password_Login.setLayoutX(378);

        Txfiled_UserID_Login.setPromptText("رقم المستخدم");
        Txfiled_UserID_Login.setAlignment(Pos.CENTER_RIGHT);

        Txfiled_Password_Login.setPromptText("كلمة المرور");
        Txfiled_Password_Login.setAlignment(Pos.CENTER_RIGHT);

        Btn_Login_LOGIN.setText("دخـول");
        Btn_Cancle_Login.setText("الغاء");

    }

    @FXML
    private void M_Btn_EN_Login(ActionEvent event) {
        count = 0;
        Btn_AR_Login.setText("Arabic");
        Btn_AR_Login.setLayoutX(14);
        //Btn_AR_Login.setLayoutY(15);

        Btn_EN_Login.setText("English");
        Btn_EN_Login.setLayoutX(82);
        // Btn_EN_Login.setLayoutY(15);

        icon_UserID_Login.setLayoutX(175);
        //icon_UserID_Login.setLayoutY(15);

        icon_Password_Login.setLayoutX(175);

        Txfiled_UserID_Login.setPromptText("User ID");
        Txfiled_UserID_Login.setAlignment(Pos.CENTER_LEFT);

        Txfiled_Password_Login.setPromptText("Password");
        Txfiled_Password_Login.setAlignment(Pos.CENTER_LEFT);

        Btn_Login_LOGIN.setText("Log in");
        Btn_Cancle_Login.setText("Cancel");

    }

    @FXML
    private void M_Btn_Login_LOGIN(ActionEvent event) throws SQLException {
        
        
        if (Txfiled_UserID_Login.getText().isEmpty()==true) {
            //JOptionPane.showMessageDialog(null ,"Welcomjjjjjjjjj" , "subject" , JOptionPane.ERROR_MESSAGE);  
            JOptionPane.showMessageDialog(null, "Wrong !!!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {

        Statement st3 = connection.createStatement();
        String Query = "SELECT * FROM `employee` WHERE EMPLOYEE_ID=" + Txfiled_UserID_Login.getText();
        System.out.println(Query);
        st3.executeQuery(Query);
        ResultSet rs3 = st3.getResultSet();
        //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
        if (rs3.first()) {
            System.out.println(" Txfiled_Password_Login = " + Txfiled_Password_Login.getText());
            System.out.println(" getString = " + rs3.getString("PASSWORD"));

            if (rs3.getString("EMPLOYEE_ID").equalsIgnoreCase(Txfiled_UserID_Login.getText()) && rs3.getString("PASSWORD").equalsIgnoreCase(Txfiled_Password_Login.getText())) {

                Stage stage2 = (Stage) LoginWindow.getScene().getWindow();
                stage2.close();

                FXMLLoader loader = new FXMLLoader();
                if (count == 0) {
                    loader.setLocation(getClass().getResource("/sample/sample_EN.fxml"));
                } else if (count == 1) {
                    loader.setLocation(getClass().getResource("/sample/sample_AR.fxml"));
                }

                try {
                    loader.load();

                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                Controller controller = loader.getController();
                //edit here 
   

                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.showAndWait();

            } else {
                //JOptionPane.showMessageDialog(null,"Hello, Welcome to Javatpoint.");  
               // JOptionPane.showMessageDialog(null, "Successfully Updated.", "Alert", JOptionPane.WARNING_MESSAGE);
           JOptionPane.showMessageDialog(null, "Wrong !!! .", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }
    }}

    @FXML
    private void M_Btn_Cancle_Login(ActionEvent event) {

        Stage stage2 = (Stage) LoginWindow.getScene().getWindow();
        stage2.close();

    }

}
