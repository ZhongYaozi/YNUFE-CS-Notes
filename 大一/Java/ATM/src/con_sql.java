import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class con_sql {
    Connection con;

    public Connection getConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atm", "root", "zzcsteven1324");
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}

