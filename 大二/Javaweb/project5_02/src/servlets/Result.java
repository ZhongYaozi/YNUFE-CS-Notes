package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String sex = (String) session.getAttribute("sex");
        String degree = (String) session.getAttribute("degree");
        String[] adept = (String[]) session.getAttribute("adept");
        String adeptStr = "";
        if (adept != null) {
            for (String a : adept) {
                adeptStr += a + "&nbsp;&nbsp;&nbsp;";
            }
        }

        PrintWriter out = response.getWriter();
        out.println("<table border><tr><th>姓名</th><th>性别</th><th>学历</th><th>擅长技术</th></tr>");
        out.println("<tr><td>" + username + "</td>");
        out.println("<td>" + sex + "</td>");
        out.println("<td>" + degree + "</td>");
        out.println("<td>" + adeptStr + "</td></tr>");
        out.println("</table>");
        out.flush();
        out.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
