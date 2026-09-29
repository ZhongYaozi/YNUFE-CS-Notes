import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/myServlet")  //添加/  web.xml
public class myServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=gbk");
        request.setCharacterEncoding("gbk");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");//得到JSP页面输入的用户名
        //name=new String(name.getBytes("ISO-8859-1"),"gbk"); //字符编码转换
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print(" 你好！欢迎" + name + "使用servlet");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();

    }
    //测试：http://localhost:8090/javaWeb_web/ab
    //http://localhost:8090/javaWeb_web/ab?name=abc


}
