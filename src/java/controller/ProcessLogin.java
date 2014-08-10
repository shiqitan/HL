/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.AdminDAO;
import dao.StudentDAO;
import entity.Admin;
import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author weiyi.ngow.2012
 */
@WebServlet(name = "ProcessLogin", urlPatterns = {"/ProcessLogin"})
public class ProcessLogin extends HttpServlet {
 
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
        try {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } finally {
            out.close();
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
        
        HttpSession session = request.getSession();
        
        String userid = null;
        String userType = request.getParameter("userType");
        userid = request.getParameter("userid");
        String password = request.getParameter("password");
        
        // If userid and password is blank
        if (userid == null || password == null) {
            request.setAttribute("error", "Invalid userid/password");
            RequestDispatcher rd = request.getRequestDispatcher("mainLogin.jsp");
            rd.forward(request, response);
        }
        
        if (userType.equals("staff")) {
            AdminDAO adminDAO = new AdminDAO();
            userid = request.getParameter("userid");
            Admin admin = adminDAO.retrieve(userid);
            
            // If such userid does not exist in DB
            if (admin == null) {
                request.setAttribute("error", "Invalid userid/password");
                RequestDispatcher rd = request.getRequestDispatcher("mainLogin.jsp");
                rd.forward(request, response);
            } else {
                String correctPassword = admin.getAdminPassword();
                
                // If password matches the one in DB
                if (correctPassword.equals(password)) {
                    session.setAttribute("User", userid);
                    response.sendRedirect("adminHomePage.jsp");
                } else {
                    request.setAttribute("error", "Invalid userid/password");
                    RequestDispatcher rd = request.getRequestDispatcher("mainLogin.jsp");
                    rd.forward(request, response);
                }
            }
        } else { 
            StudentDAO studentDAO = new StudentDAO();
            userid = request.getParameter("userid");
            
            // If userid or password is blank 
            // Or student userid is not 10 characters long (i.e. S10012345A)
            if (userid == null || password == null || userid.length() != 10) {
                request.setAttribute("error", "Invalid userid/password");
                RequestDispatcher rd = request.getRequestDispatcher("mainLogin.jsp");
                rd.forward(request, response);
            }
            Student student = studentDAO.retrieve(userid);
            
            // If no such student exist in DB
            if (student == null) {
                request.setAttribute("error", "Invalid userid/password");
                RequestDispatcher rd = request.getRequestDispatcher("mainLogin.jsp");
                rd.forward(request, response);
            } else { 
                String correctPassword = student.getPassword();

                if (correctPassword.equals(password)) {
                    session.setAttribute("User", userid);
                    response.sendRedirect("studentHomePage.jsp");
                } else {
                    request.setAttribute("error", "Invalid userid/password");
                    RequestDispatcher rd = request.getRequestDispatcher("mainLogin.jsp");
                    rd.forward(request, response);
                }
            }
        }
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
