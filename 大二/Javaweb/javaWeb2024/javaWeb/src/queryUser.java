import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "queryUser", urlPatterns = "/queryUser")
public class queryUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String uname = request.getParameter("userName");
        UserDao dao = new UserDao();
        List<User> list = dao.findByUserName(uname);
        //request.setAttribute("bean",list);
        //request.getRequestDispatcher("queryuser.jsp").forward(request,response);

        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>查询结果</TITLE></HEAD>");
        out.print(" 查询" + uname + "结果如下：");
        out.print(" <table border='1' bgcolor='#e0ffff'>");
        out.print(" <tr>");
        out.print(" <th>编号</th>");
        out.print(" <th>用户名</th>");
        out.print(" <th>密码</th>");
        out.print(" <th>角色</th>");
        out.print(" <th>状态</th>");
        out.print(" </tr>");
        for (User user : list) {
            out.print("<tr>");
            out.print("<td>" + user.getUserId() + "</td>");
            out.print("<td>" + user.getUserName() + "</td>");
            out.print("<td>" + user.getPassword() + "</td>");
            out.print("<td>" + user.getRole() + "</td>");
            out.print("<td>" + user.getStatus() + "</td>");
            out.print("</tr>");
        }
        out.print("</table>");
        out.print("<br>");
        out.print("<input type='button' name='btnBack' value='返回' onclick='javascript:window.history.back();'/>");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();

    }
}
