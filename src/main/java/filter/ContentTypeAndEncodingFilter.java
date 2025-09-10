package filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class ContentTypeAndEncodingFilter extends HttpFilter {
        @Override
        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
            req.setCharacterEncoding("UTF-8"); // Устанавливаем кодировку запросов
            res.setCharacterEncoding("UTF-8"); // Устанавливаем кодировку ответов
            chain.doFilter(req, res);
        }

}