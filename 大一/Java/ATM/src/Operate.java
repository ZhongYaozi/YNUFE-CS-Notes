import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Operate extends Application {
    private BankAccount account = new BankAccount();

    public void start(Stage primaryStage) {
        VBox root = new VBox();
        Button checkBalanceButton = new Button("查询余额");
        Button depositButton = new Button("存款");
        Button withdrawButton = new Button("取款");
        Button transferButton = new Button("转账");
        Button changePasswordButton = new Button("修改密码");

        checkBalanceButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("查询余额");
            alert.setHeaderText(null);
            alert.setContentText("当前余额: " + account.getBalance());
            alert.showAndWait();
        });

        depositButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("存款");
            dialog.setHeaderText(null);
            dialog.setContentText("请输入存款金额:");
            dialog.showAndWait().ifPresent(amount -> {
                try {
                    int value = Integer.parseInt(amount);
                    if (value > 0) {
                        account.deposit(value);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("存款成功");
                        alert.setHeaderText(null);
                        alert.setContentText("存款成功，当前余额: " + account.getBalance());
                        alert.showAndWait();
                    } else {
                        showError("存款金额必须为正数");
                    }
                } catch (NumberFormatException ex) {
                    showError("请输入有效的金额");
                }
            });
        });

        withdrawButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("取款");
            dialog.setHeaderText(null);
            dialog.setContentText("请输入取款金额:");
            dialog.showAndWait().ifPresent(amount -> {
                try {
                    int value = Integer.parseInt(amount);
                    if (value > 0 && value % 100 == 0 && value <= 5000 && account.getBalance() >= value) {
                        account.drawMoney(value);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("取款成功");
                        alert.setHeaderText(null);
                        alert.setContentText("取款成功，当前余额: " + account.getBalance());
                        alert.showAndWait();
                    } else {
                        showError("取款金额必须为100的倍数，且不超过5000元");
                    }
                } catch (NumberFormatException ex) {
                    showError("请输入有效的金额");
                }
            });
        });

        transferButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("转账");
            dialog.setHeaderText(null);
            dialog.setContentText("请输入转账金额:");
            dialog.showAndWait().ifPresent(amount -> {
                try {
                    int value = Integer.parseInt(amount);
                    if (value > 0 && value <= 2000 && account.getBalance() >= value) {
                        BankAccount targetAccount = new BankAccount(); // 模拟目标账户
                        account.transfer(targetAccount, value);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("转账成功");
                        alert.setHeaderText(null);
                        alert.setContentText("转账成功，当前余额: " + account.getBalance());
                        alert.showAndWait();
                    } else {
                        showError("转账金额必须大于0且不超过2000元");
                    }
                } catch (NumberFormatException ex) {
                    showError("请输入有效的金额");
                }
            });
        });

        changePasswordButton.setOnAction(e -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("修改密码");

            ButtonType changeButtonType = new ButtonType("修改", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(changeButtonType, ButtonType.CANCEL);

            PasswordField oldPassword = new PasswordField();
            oldPassword.setPromptText("旧密码");
            PasswordField newPassword = new PasswordField();
            newPassword.setPromptText("新密码");

            GridPane grid = new GridPane();
            grid.add(new Label("旧密码:"), 0, 0);
            grid.add(oldPassword, 1, 0);
            grid.add(new Label("新密码:"), 0, 1);
            grid.add(newPassword, 1, 1);
            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == changeButtonType) {
                    return new Pair<>(oldPassword.getText(), newPassword.getText());
                }
                return null;
            });

            dialog.showAndWait().ifPresent(passwords -> {
                if (passwords.getKey().equals("123456") && passwords.getValue().length() >= 6 && !passwords.getValue().matches("(.)\\1{5,}")) {
                    // 修改密码逻辑
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("修改密码成功");
                    alert.setHeaderText(null);
                    alert.setContentText("密码修改成功");
                    alert.showAndWait();
                } else {
                    showError("密码修改失败，请检查输入");
                }
            });
        });

        root.getChildren().addAll(checkBalanceButton, depositButton, withdrawButton, transferButton, changePasswordButton);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("ATM操作");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
