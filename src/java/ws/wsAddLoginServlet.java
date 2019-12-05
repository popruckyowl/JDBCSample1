/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.LoginServlet;
import test.LoginlistServlet;

/**
 *
 * @author user
 */
@WebServlet(name = "wsAddLoginServlet", urlPatterns = {"/ws/addlogins"})
public class wsAddLoginServlet extends HttpServlet {

    // Load driver once
    static {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
        resp.setContentType("application/json");
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost/sample", "app", "app"); 
                PrintWriter out = resp.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String id = req.getParameter("id");
            String password = req.getParameter("password");
            PreparedStatement pstmt = con.prepareStatement("insert into LOGIN (id, password) values (?, ?)");
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            int ret = pstmt.executeUpdate();
            out.println(ret);
        } catch (Exception ex) {
            Logger.getLogger(LoginlistServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
        resp.setContentType("application/json");
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost/sample", "app", "app"); 
                PrintWriter out = resp.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String id = req.getParameter("id");
            String password = req.getParameter("password");
            PreparedStatement pstmt = con.prepareStatement("update LOGIN set password=? where id=?");
            pstmt.setString(2, id);
            pstmt.setString(1, password);
            int ret = pstmt.executeUpdate();
            out.println(ret);
        } catch (Exception ex) {
            Logger.getLogger(LoginlistServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
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
        
        }
    }
