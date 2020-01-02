/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author user
 */
public class ServletResponseWrapper extends HttpServletResponseWrapper {
    private StringWriter stringWriter = new StringWriter();
    private PrintWriter writer = new PrintWriter(stringWriter);
    
    public ServletResponseWrapper (HttpServletResponse response) {
        super (response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }
    
    public String getBuffString() {
        writer.flush();
        return stringWriter.toString();
    }
    
}
