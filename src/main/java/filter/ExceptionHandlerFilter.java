package filter;

import exception.DataBaseException;
import exception.NotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.InvalidParameterException;


@WebFilter("/*")
public class ExceptionHandlerFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(req, res);
        }
        catch (InvalidParameterException e ) {
            sendErrorResponse(res,req,HttpServletResponse.SC_BAD_REQUEST,e.getMessage());
        }
        catch (DataBaseException e) {
            sendErrorResponse(res, req, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NotFoundException e )
        { sendErrorResponse(res, req, HttpServletResponse.SC_NOT_FOUND, e.getMessage()); }
    }
    private void sendErrorResponse(HttpServletResponse res,HttpServletRequest req, int statusCode, String message) throws IOException, ServletException {
        res.setStatus(statusCode);
        req.setAttribute("errorMessage", message);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/new-match.jsp");
        dispatcher.forward(req, res);
    }
}
