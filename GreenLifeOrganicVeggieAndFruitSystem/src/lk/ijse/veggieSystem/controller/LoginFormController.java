package lk.ijse.veggieSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.veggieSystem.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword1;
    public AnchorPane loginFormContext;
    static String []login1=new String[10];
    public Label lblWarning;
    public Label lblUserName;
    public Label lblPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        login1[0]="maheshi";
        login1[1]="12345";
        if(txtUserName.getText().equals(login1[0])&&txtPassword1.getText().equals(login1[1])){
        Navigation.swatchNavigation("DashboardForm.fxml",actionEvent);
        }else{
            lblWarning.setVisible(true);
        }
    }

    public void txtUserNameKeyReleased(KeyEvent keyEvent) {
        if (txtUserName.getText().equals("")) {
            lblUserName.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[a-zA-Z]{1,}$");
            Matcher matcher = pattern.matcher(txtUserName.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblUserName.setText("Invalid User Name !!!");
            } else {
                lblUserName.setText("");
            }
        }
    }

    public void txtPasswordKeyReleased(KeyEvent keyEvent) {
        if (txtPassword1.getText().equals("")) {
            lblPassword.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[0-9]{5}$");
            Matcher matcher = pattern.matcher(txtPassword1.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblPassword.setText("Invalid Password !!!");
            } else {
                lblPassword.setText("");
            }
        }
    }
}
