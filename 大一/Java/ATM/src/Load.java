import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Load extends Application {
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        Label l1 = new Label("用户名");
        TextField tName = new TextField();
        Label l2 = new Label("密码");
        TextField tpw = new TextField();
        Button bOK = new Button("登录");
        Button bChange = new Button("更改密码");
        Button bReg = new Button("注册");
        Button bCancel = new Button("退出");
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.add(l1, 0, 0);
        root.add(tName, 1, 0);
        root.add(l2, 0, 1);
        root.add(tpw, 1, 1);
        root.add(bOK, 1, 2);
        root.add(bChange, 2, 2);
        root.add(bReg, 1, 3);
        root.add(bCancel, 2, 3);
        bCancel.setOnMouseClicked(e -> {
            System.exit(0);
        });
        bReg.setOnMouseClicked(e -> {
            new Register().start(primaryStage);
        });
        bChange.setOnMouseClicked(e -> {
            new Change().start(primaryStage);
        });
        bOK.setOnMouseClicked(e -> {
            new Operate().start(primaryStage);
        });
        Scene scene = new Scene(root, 400, 180);
        primaryStage.setTitle("登录");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
