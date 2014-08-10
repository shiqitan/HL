/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import au.com.bytecode.opencsv.CSVReader;
import com.google.gson.JsonObject;
import dao.ConnectionManager;
import entity.Admin;
import helper.Validation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InsertEntries", urlPatterns = {"/InsertEntries"})
public class InsertEntries extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        LinkedHashMap<String, Object> messageReturn = new LinkedHashMap<String, Object>();
        List<Object> recordLoaded = null;
        ArrayList<Object> errors = new ArrayList<Object>();
        try {
            /* TODO output your page here. You may use following sample code. */

            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            CSVReader reader = null;


            try {
                // Connect to Database
                con = ConnectionManager.getConnection();

                // Wipe Database
                purgeDatabase(con);
                
               // BOOTSTRAP INTO ADMIN TABLE WITH VALIDATION
                int adminInsertCount = bootstrapAdmin(0, con, reader, errors);
                
                // Sort the error messages according to alphabetical order
                ArrayList<Object> errorsUnsorted = errors;
                errors = new ArrayList<Object>();
               
                // Sort for admin.csv
                for (int i=0; i<errorsUnsorted.size(); i++){
                    LinkedHashMap<String, Object> errorObj = (LinkedHashMap<String, Object>) errorsUnsorted.get(i);
                    String filename = (String) errorObj.get("file");
                    
                    if (filename.equals("admin.csv")){
                        errors.add(errorObj);
                    }
                }
                
                recordLoaded = (List) new ArrayList<JsonObject>();

                JsonObject adminCount = new JsonObject();
                adminCount.addProperty("admin.csv", adminInsertCount);

                recordLoaded.add(adminCount);

            } catch (SQLException e) {
                out.println("SQLException" + e);
                messageReturn.put("status", "error");
                messageReturn.put("message", e.getMessage());
            } catch (IOException e) {
                out.println("IOException" + e);
                messageReturn.put("status", "error");
                messageReturn.put("message", e.getMessage());
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                    if (con != null) {
                        con.close();
                        con = null;
                    }
                } catch (SQLException e) {
                    out.println("SQLException " + e);
                    messageReturn.put("status", "error");
                }
            }

            if (errors.isEmpty()) {
                messageReturn.put("status", "success");
                messageReturn.put("num-record-loaded", recordLoaded);
            } else {
                messageReturn.put("status", "error");
                messageReturn.put("num-record-loaded", recordLoaded);
                messageReturn.put("error", errors);
            }
            request.setAttribute("status", messageReturn);
            
            // Dispatch to uploadDone.jsp
            RequestDispatcher rd = request.getRequestDispatcher("uploadDone.jsp");
            rd.forward(request, response);

        } catch (Exception e){
            out.println(e);
        } finally {
            out.close();
        }
    }

    private void purgeDatabase(Connection con) throws SQLException {
        String wipeAdmin = "delete from Admin";
        PreparedStatement wipeAdminStatement = con.prepareStatement(wipeAdmin);
        wipeAdminStatement.execute();
    }

    private int bootstrapAdmin(int adminInsertCount, Connection con,
            CSVReader reader, ArrayList<Object> errors) throws FileNotFoundException,
            IOException, SQLException {
        String relativeWebPath = "data/admin.csv";
        String[] nextLine;
        int totalAdminRow = 1; 
        String absoluteDiskPath = getServletContext().getRealPath("") + File.separator + relativeWebPath;
        reader = new CSVReader(new FileReader(absoluteDiskPath), ',', '"', '\'', 1);
        
        String update_admin = "INSERT INTO admin (adminid, password) VALUES (?,?)";

        PreparedStatement preparedStatement_admin = con.prepareStatement(update_admin);

        while ((nextLine = reader.readNext()) != null) {
            ArrayList<String> AdminErrors = new ArrayList<String>();
            String adminID = nextLine[0].trim();
            String password = nextLine[1].trim();

            if (Validation.checkIfFieldBlank(adminID)) {
                AdminErrors.add("Admin ID is blank");
            }

            if (Validation.checkIfFieldBlank(password)) {
                AdminErrors.add("Password is blank");
            }

            if (AdminErrors.isEmpty()) {
                preparedStatement_admin.setString(1, adminID);
                preparedStatement_admin.setString(2, password);

                preparedStatement_admin.execute();
                adminInsertCount++;
                totalAdminRow++;
            } else {
                LinkedHashMap<String, Object> errorObj = new LinkedHashMap<String, Object>();
                errorObj.put("file", "admin.csv");
                errorObj.put("line", ++totalAdminRow);
                errorObj.put("message", AdminErrors);
                errors.add(errorObj);
            }
        }
        return adminInsertCount;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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