import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "editUser", urlPatterns = "/editUser")//无须配置web.xml
public class editUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=gbk");
        Integer uid = Integer.valueOf(request.getParameter("uid"));//userId
        String uname = request.getParameter("userName");
        String upwd = request.getParameter("userPwd");
        Integer urole = Integer.valueOf(request.getParameter("userRole"));
        boolean ustatus = Boolean.valueOf(request.getParameter("userStatus"));

        User user = new User();
        user.setUserId(uid);
        user.setUserName(uname);
        user.setPassword(upwd);
        user.setRole(urole);
        user.setStatus(ustatus);
        response.getWriter().println(user);

        UserDao dao = new UserDao();
        int n = dao.updateUserinfo(user);
        if (n > 0)
            response.sendRedirect("././userinfo.jsp");//请注意javaWeb加了_web ,也可以用相对路径，回到上一级
        else
            response.sendRedirect("/javaWeb_web/error.jsp");
        /*
        PrintWriter writer = response.getWriter();
        Map<String, String[]> params = request.getParameterMap();
        String queryString = "";
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }
        writer.println(queryString);
        */
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doPost(request,response);//在同一个Servlet上需要同时支持GET 和 POST方法
        String str = request.getQueryString();
    }
}
