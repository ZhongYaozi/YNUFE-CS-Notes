package util;

import java.sql.*;


public class ConnectionManager {

    private static String DbDriver = "com.mysql.jdbc.Driver";

    private static String DbUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    //private static String DbUrl ="jdbc:mysql://47.108.204.98:3306/test?useUnicode=true&characterEncoding=UTF-8";

    private static String DbUserName = "root";

    private static String DbPassword = "123";//本机

    //private static String DbPassword = "root";
    // 返回连接
    public static Connection getConnction() {
        Connection dbConnection = null;
        try {
            Class.forName(DbDriver);
            dbConnection = DriverManager.getConnection(DbUrl,
                    DbUserName, DbPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dbConnection;
    }


    // 返回连接
    public static void closeConnection(Connection dbConnection) {
        try {
            if (dbConnection != null && (!dbConnection.isClosed())) {
                dbConnection.close();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }

    public static void closeCon(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }


    // 关闭结果集
    public static void closeResultSet(ResultSet res) {
        try {
            if (res != null) {
                res.close();
                res = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭语句
     *
     * @param pStatement PreparedStatement
     */

    public static void closePreparedStatement(PreparedStatement pStatement) {
        try {
            if (pStatement != null) {
                pStatement.close();
                pStatement = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement Statement) {
        try {
            if (Statement != null) {
                Statement.close();
                Statement = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
