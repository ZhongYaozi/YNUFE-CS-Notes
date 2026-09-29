import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*") //使用urlPatterns减少配置
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    //IDEA或者Tomcat日志使用的是UTF-8编码,windows系统本地语言是gbk
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//czx add，所有页面统一为UTF-8
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
