/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import ua.kpi.project4.UserAccount;

/**
 *
 * @author Artur Dzidzoiev
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("userid");

            String vLogin = request.getParameter("login");
            String vPassword = request.getParameter("password");
//            out.print("a = " + vLogin + "<br>");
//            out.print("b = " + vPassword);

            if (vLogin != null && vPassword != null) {
                String sql = "SELECT UserAccountId, FullName, Login, Password "
                        + "FROM trucking_company.user_accounts "
                        + "WHERE Login = ?;";
                try {
                    InitialContext context = new InitialContext();
                    DataSource ds = (DataSource) context.lookup("jdbc/TruckingCompany");
                    try (Connection connection = ds.getConnection();
                            PreparedStatement statement = connection.prepareStatement(sql);) {
                        statement.setString(1, vLogin);
                        try (ResultSet result = statement.executeQuery()) {
                            if (result.next()) {
                                int id = result.getInt("UserAccountId");
                                String name = result.getString("FullName");
                                String login = result.getString("Login");
                                String pass = result.getString("Password");

                                UserAccount account = new UserAccount(id, name, login, pass);
                                if (auth(account, vPassword)) {
                                    request.getSession().setAttribute("username", account.getFullname());
                                    request.getSession().setAttribute("userid", account.getUserAccountId());
                                    request.getRequestDispatcher("/index.jsp").forward(request, response);
//                                    request.setAttribute("Name", account.getFullname());
//                                    request.setAttribute("UserAccountId", account.getUserAccountId());
//                                    request.getRequestDispatcher("/welcome_page.jsp").forward(request, response);
                                }
                            } else {
                                throw new IllegalArgumentException("No such user.");
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    private boolean auth(UserAccount user, String password) {
        return user.getPassword().equals(password);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
