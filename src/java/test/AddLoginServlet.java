/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 *
 * @author user
 */
@WebServlet(name = "AddLoginServlet", urlPatterns = {"/AddLoginServlet"})
public class AddLoginServlet extends HttpServlet {
    
    // Load driver once
//    static {
//        try {
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AddLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    // Change to NewServletListener.java -> Listener

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
            
        /* TODO output your page here. You may use following sample code. */
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost/sample", "app", "app");
            PrintWriter out = response.getWriter()) {
            Statement stmt = con.createStatement();
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            int ret = stmt.executeUpdate("insert into LOGIN (ID, PASSWORD) values ('"+id+"', '"+password+"')");
            if (ret == 1) {
                out.println("insert success");
            } else {
                out.println("insert fail");
            }
            response.sendRedirect("private/list.jsp");    //最優先顯示，其他print失效
        } catch (SQLException ex) {
            Logger.getLogger(AddLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
