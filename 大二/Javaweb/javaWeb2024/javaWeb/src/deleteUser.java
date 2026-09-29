import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteUser")
public class deleteUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        UserDao userDao = new UserDao();
        int n = userDao.delUserinfo(uid);
        if (n > 0)
            response.sendRedirect("/javaWeb_web/userinfo.jsp");
        else
            response.sendRedirect("/javaWeb_web/error.jsp");

    }
}
