package controller.admin;

import controller.support.TimeEnum;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.admin.Admin;
import model.admin.AdminDAO;
import webpage_tools.*;

/**
 * Process Admin 
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet", "/admin"})
public class AdminServlet extends HttpServlet {
    private static final AdminDAO AdminDAO;    
    static {
        AdminDAO = new AdminDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object admin = session.getAttribute("admin");
        boolean adminNotNull = Objects.nonNull(admin);
        if (adminNotNull)
        {
            response.sendRedirect(WebPageEnum.ADMIN_PAGE.getURL());
        }
        else response.sendRedirect(WebPageEnum.ADMIN_LOGIN.getURL());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        System.out.println("Served at [" + getServletName() + "]");
        HttpSession session = request.getSession();
        
        String command = request.getParameter("command");
        CommandEnum commandEnum = CommandEnum.getCommandEnum(command);
        
        switch (commandEnum) {
            case LOGIN:
                processLogin(request, response);
                break;
            case LOGOUT:
                processLogout(request, response);
                break;
            case SETTING:
                
                break;
            default:
                session.removeAttribute(CommandEnum.SETTING.name());
                response.sendRedirect(WebPageEnum.ADMIN_LOGIN.getURL());
        }
    }
    
    protected void processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(TimeEnum.A_15_MINUTES.getValue());
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<Admin> admin = AdminDAO.Authenticate(username, password);
        
        if(admin.isPresent()) {
            session.setAttribute("admin", admin.get());
            response.sendRedirect(WebPageEnum.ADMIN_PAGE.getURL());
        }
        else {
            session.setAttribute(MessageEnum.LOGIN_ERROR.getName(), MessageEnum.LOGIN_ERROR.getMessage());
            response.sendRedirect(WebPageEnum.ADMIN_LOGIN.getURL());
        }
    }
    
    protected void processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        session.invalidate();
        session = request.getSession(true);
        response.sendRedirect(WebPageEnum.ADMIN_LOGIN.getURL());
    }
    
    protected void processSetting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.sendRedirect(WebPageEnum.ADMIN_SETTING_PAGE.getURL());
    }
}
