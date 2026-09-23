import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Change extends Application {
    static Connection con;
    static PreparedStatement checkpassword;
    static PreparedStatement updatepassword;

    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setSpacing(10);
        Label l1 = new Label("请输入你的用户名");
        TextField t1 = new TextField();
        Label l2 = new Label("请输入原密码");
        PasswordField p1 = new PasswordField();
        Label l3 = new Label("请输入新密码");
        PasswordField p2 = new PasswordField();
        Label l4 = new Label("请再次确认新密码");
        PasswordField p3 = new PasswordField();
        Button bOk = new Button("确定");
        bOk.setOnMouseClicked(e -> {
            con_sql cs = new con_sql();
            con = cs.getConnect();
            if (con != null) {
                try {
                    String userId = t1.getText();
                    String oldPw = p1.getText();
                    String newPw = p2.getText();
                    String confirmPw = p3.getText();
                    checkpassword = con.prepareStatement("select Password FROM UserInformation where UserId = ?");
                    checkpassword.setString(1, userId);
                    ResultSet res = checkpassword.executeQuery();
                    if (res.next() && res.getString("Password").equals(oldPw)) {
                        // 验证新密码有效性
                        if (newPw.length() < 6 || newPw.chars().distinct().count() == 1) {
                            Alert ax = new Alert(Alert.AlertType.WARNING);
                            ax.setTitle("新密码无效");
                            ax.setHeaderText(null);
                            ax.setContentText("密码长度不能小于六位且不能全为相同字符");
                            ax.showAndWait();
                        } else if (!newPw.equals(confirmPw)) {
                            Alert ax = new Alert(Alert.AlertType.WARNING);
                            ax.setTitle("密码不匹配");
                            ax.setHeaderText(null);
                            ax.setContentText("新密码和确认密码不匹配");
                            ax.showAndWait();
                        } else {
                            // 更新密码
                            updatepassword = con.prepareStatement("UPDATE UserInformation SET Password = ? WHERE UserId = ?");
                            updatepassword.setString(1, newPw);
                            updatepassword.setString(2, userId);
                            updatepassword.executeUpdate();
                            Alert ax = new Alert(Alert.AlertType.INFORMATION);
                            ax.setTitle("成功");
                            ax.setHeaderText(null);
                            ax.setContentText("密码修改成功,进入登录界面!");
                            ax.showAndWait();
                            primaryStage.close();
                            Load lt = new Load();
                            lt.start(primaryStage);
                        }
                    } else {
                        Alert ax = new Alert(Alert.AlertType.WARNING);
                        ax.setTitle("错误");
                        ax.setHeaderText(null);
                        ax.setContentText("用户名或原密码不正确");
                        ax.showAndWait();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });
        root.getChildren().addAll(l1, t1, l2, p1, l3, p2, l4, p3, bOk);
        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("修改密码");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
