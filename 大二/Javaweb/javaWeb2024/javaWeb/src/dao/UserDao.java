package dao;

import model.User;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet results;

    public int addUserinfo(User userinfo) {
        int result = 0;
        con = ConnectionManager.getConnction();
        try {
            String sql = "insert into userinfo(username,password,role,status) values(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userinfo.getUserName());
            pstmt.setString(2, userinfo.getPassword());
            pstmt.setInt(3, userinfo.getRole());
            pstmt.setBoolean(4, userinfo.getStatus());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeResultSet(results);
            ConnectionManager.closeStatement(pstmt);//释放PreparedStatement对象
            ConnectionManager.closeConnection(con);//释放PreparedStatement对象
        }
        return result;
    }

    public int delUserinfo(int id) {
        int result = 0;
        con = ConnectionManager.getConnction();
        try {
            String sql = "delete from userinfo where userid=? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeResultSet(results);
            ConnectionManager.closeStatement(pstmt);//释放PreparedStatement对象
            ConnectionManager.closeConnection(con);//释放Connection对象
        }
        return result;
    }

    public int updateUserinfo(User userinfo) {
        int result = 0;
        con = ConnectionManager.getConnction();
        try {
            String sql = "update userinfo set username=?,password=?,role=?,status=? where userid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userinfo.getUserName());
            pstmt.setString(2, userinfo.getPassword());
            pstmt.setInt(3, userinfo.getRole());
            pstmt.setBoolean(4, userinfo.getStatus());
            pstmt.setInt(5, userinfo.getUserId());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeResultSet(results);
            ConnectionManager.closeStatement(pstmt);//释放PreparedStatement对象
            ConnectionManager.closeConnection(con);//释放Connection对象
        }
        return result;
    }

    public List<User> findByUserName(String userName) {
        String sql = "select * from userinfo where username like '%" + userName + "%' ";
        con = ConnectionManager.getConnction();
        List<User> list = new ArrayList();
        try {
            pstmt = con.prepareStatement(sql);
            results = pstmt.executeQuery();
            while (results.next()) {//if(results.next()) {
                User user = new User();
                user.setUserId(results.getInt("userId"));//czx add 22-6-10
                user.setUserName(results.getString("userName"));
                user.setPassword(results.getString("password"));
                user.setRole(results.getInt("role"));
                user.setStatus(results.getBoolean("status"));
                list.add(user);// 将user对象添加到数组list中.
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }


    public User findByUserId(Integer id) {
        User user = new User();
        String sql = "select * from userinfo where userId=? ";
        con = ConnectionManager.getConnction();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            results = pstmt.executeQuery();
            if (results.next()) {
                user.setUserId(results.getInt("userId"));//czx add 22-6-10
                user.setUserName(results.getString("userName"));
                user.setPassword(results.getString("password"));
                user.setRole(results.getInt("role"));
                user.setStatus(results.getBoolean("status"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }

    public User findByLoginname(String username) {
        User user = new User();
        String sql = "select * from userinfo where username=? ";
        con = ConnectionManager.getConnction();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            results = pstmt.executeQuery();
            if (results.next()) {
                user.setUserId(results.getInt("userId"));//czx add 22-6-10
                user.setUserName(results.getString("userName"));
                user.setPassword(results.getString("password"));
                user.setRole(results.getInt("role"));
                user.setStatus(results.getBoolean("status"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }

    public List<User> listAll() {//得到userinfo表的数据,并存入数组中
        con = ConnectionManager.getConnction();
        List<User> list = new ArrayList();//建立一个数组对象用于存放UserInfo对象
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from userinfo");// 查询字符串
            while (rs.next()) { // 遍历结果集中的所有记录
                User user = new User(); // 创建一个UserInfo对象
                // 将结果集当前记录中的userId属性值赋给对象user中的userId属性
                user.setUserId(rs.getInt("userId"));
                // 将结果集当前记录中的userName属性值赋给对象user中的userName属性
                user.setUserName(rs.getString("userName"));
                // 将结果集当前记录中的password属性值赋给对象user中的password属性
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setStatus(rs.getBoolean("status"));
                list.add(user);// 将user对象添加到数组list中.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return list;
    }
}
