/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(name = "wsLoginServlet", urlPatterns = {"/ws/logins"})
public class wsLoginServlet extends HttpServlet {
    
    // Load driver once
    static {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
//        resp.setContentType("text/html;charset=UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost/sample", "app", "app"); 
                PrintWriter out = resp.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from login");
            ArrayList list = new ArrayList();
            
//            out.println("{");
            while (rs.next()) {
                HashMap map = new HashMap();
                String id = rs.getString("id");
                map.put("id", id);
                String password = rs.getString("password");
                map.put("password", password);
//                out.println("\"" + id + "\"" + ":\"" + password + "\"");
                list.add(map);
            }
//            out.println("}");
            Gson gson = new Gson();
            out.println(gson.toJson(list));
            con.close();
        } catch (SQLException ex) {
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
        /* TODO output your page here. You may use following sample code. */
       
    }
}
