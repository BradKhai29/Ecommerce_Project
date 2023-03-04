/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import controller.SupportEnum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;
import webpage_tools.WebPageEnum;

/**
 *
 * @author This PC
 */
@WebServlet(name = "LoginWithCookie", urlPatterns = {"/LoginWithCookie", "/loginWithCookie"})
public class LoginWithCookieServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletInfo() + "]");
        HttpSession session = request.getSession(true);
        session.setAttribute(SupportEnum.LOGIN_CHECKPOINT_COOKIE.getName(), "checkpoint");
        
        //Get the Cookie
        Optional<Customer> rememeberUser = processRemberUserCookie(request, response);

        if (rememeberUser.isPresent()) {
            session.setAttribute(SupportEnum.CUSTOMER.getName(), rememeberUser.get());
        } 
        
        response.sendRedirect(WebPageEnum.HOME.getURL());
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


    @Override
    public String getServletInfo() {
        return getServletName();
    }

    private Optional<Customer> processRemberUserCookie(HttpServletRequest request, HttpServletResponse response) {
        //Init empty Optional
        System.out.println("processing RemberUserCookie ...");
        Optional<Customer> rememberUser = Optional.empty();

        Cookie cookies[] = request.getCookies();
        for (int i = 0; i < cookies.length; i++) 
        {
            Cookie existCookie = cookies[i];

            //If exist rememberUserCookie, read its value to check
            if (existCookie.getName().equals(SupportEnum.REMEMBER_USER_COOKIE.getName())) 
            {
                String cookieValue = existCookie.getValue();
                rememberUser = RememberUserManager.get(cookieValue);

                //Check if the remember cookie is valid or not
                if(rememberUser.isEmpty()) 
                {
                    existCookie.setMaxAge(0);
                    response.addCookie(existCookie);
                }
            }
        }
        System.out.println("Finishing processed RemberUserCookie ...");
        return rememberUser;
    }
}
