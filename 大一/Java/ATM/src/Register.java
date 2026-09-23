import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class Register extends Application {
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        Label lId = new Label("请输入用户ID:");
        TextField tId = new TextField();
        Label lPw = new Label("请输入密码:");
        PasswordField tPw = new PasswordField();
        Button bOk = new Button("注册");

        bOk.setOnAction(e -> {
            con_sql cs = new con_sql();
            Connection con = cs.getConnect();
            try {
                Statement sql = con.createStatement();
                String tid = tId.getText();
                String tpw = tPw.getText();
                ResultSet res = sql.executeQuery("SELECT * FROM UserInformation WHERE UserId='" + tid + "'");
                if (res.next()) {
                    Alert ax = new Alert(Alert.AlertType.INFORMATION);
                    ax.setTitle("提示");
                    ax.setHeaderText(null);
                    ax.setContentText("该用户已经注册，请重新输入!");
                    ax.showAndWait();
                    tId.setText("");
                    tPw.setText("");
                } else {
                    sql.executeUpdate("INSERT INTO UserInformation (UserId, Password) VALUES ('" + tid + "', '" + tpw + "')");
                    Alert ax = new Alert(Alert.AlertType.INFORMATION);
                    ax.setTitle("提示");
                    ax.setHeaderText(null);
                    ax.setContentText("注册成功，进入登录界面!");
                    ax.showAndWait();
                    primaryStage.close();
                    Load load = new Load();
                    load.start(new Stage());
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        root.getChildren().addAll(lId, tId, lPw, tPw, bOk);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("注册");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
