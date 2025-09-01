package filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ExceptionHandlerFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(req, res);
        }
        catch (Exception e) {
            sendErrorResponse(res, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    private void sendErrorResponse(HttpServletResponse res, int statusCode, String message) throws IOException {
     //   sendResponse(res, statusCode, errorToJson(message));
    }

}
