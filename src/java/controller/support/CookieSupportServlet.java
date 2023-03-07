package controller.support;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This Servlet will handle some function with Cookie
 */
@WebServlet(name = "CookieSupportServlet", urlPatterns = {"/CookieSupportServlet", "/cookieSupport"})
public class CookieSupportServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * This method will process the Cookie with given CookieEnum, and return the value belong to that cookie
     * <br>doRemove option will take action on this Cookie
     * @param request
     * @param cookieEnum
     * @param doRemove If <span style="color:red">true</span>, then remove this Cookie
     * If <span style="color:red">true</span>, no removed action
     * @return 
     */
    public static String processCookie(HttpServletRequest request, HttpServletResponse response, CookieEnum cookieEnum, boolean doRemove)
    {
        String cookieValue = "";
        boolean isFound = false;
        Cookie[] cookies = request.getCookies();

        for (int i = 0; i < cookies.length && !isFound; i++) {
            Cookie existCookie = cookies[i];
            
            //If exist cookie with name = this cookieEnum.name, then get its value
            if (existCookie.getName().equals(cookieEnum.getName())) 
            {
                cookieValue = existCookie.getValue();
                isFound = true;
                if(doRemove) 
                {
                    existCookie.setMaxAge(0);
                    response.addCookie(existCookie);
                }
            }
        }
        
        return cookieValue;
    }
    
    public static void addCookie(HttpServletResponse response, CookieEnum cookieEnum, String cookieValue)
    {
        TimeEnum timeEnum = TimeEnum.MAX_COOKIE_TIME;
        Cookie cookie = new Cookie(cookieEnum.getName(), cookieValue);
        cookie.setMaxAge(timeEnum.getValue());
        response.addCookie(cookie);
    }
}
