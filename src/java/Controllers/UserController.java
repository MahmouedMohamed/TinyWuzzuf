/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB_Interaction.CandidateDB;
import DB_Interaction.DatabaseConnection;
import DB_Interaction.MessageDB;
import Models.CV;
import Models.Candidate;
import Models.Message;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {
    CandidateDB candidateDB=null;
    MessageDB messageDB=null;
    public UserController() {
		candidateDB = new CandidateDB();
                messageDB= new MessageDB();
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
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameter("submit").equals("register"))
        {
            Candidate candidate=new Candidate(
                    "user",
                    request.getParameter("username"),
                    request.getParameter("telephone"),
                    new CV(request.getParameter(("cv")))
            );
            candidateDB.save(candidate);
            HttpSession session= request.getSession(true);
            session.setAttribute("username",request.getParameter("username"));
            request.setAttribute("candidate",candidate);
            request.setAttribute("message", messageDB.get(candidate.get_username()));
            RequestDispatcher dispatcher = request.getRequestDispatcher("PositionController");
            dispatcher.forward(request, response);
        }
        
        else if(request.getParameter("submit").equals("login"))
        {
            Candidate candidate=candidateDB.get(request.getParameter("username"));
            if(candidate.get_username()==null)
            {
                try (PrintWriter out = response.getWriter()) {
                        out.print("Sorry username isn't valid");  
                        RequestDispatcher dispatcher=request.getRequestDispatcher("Login.jsp");  
                        dispatcher.include(request,response);  
                    }
            }
            else
            {
                if(candidate.getPrevilige().equals("user"))
                {
                    HttpSession session= request.getSession(true);
                    session.setAttribute("username",request.getParameter("username"));
                    request.setAttribute("candidate",candidate);
                    request.setAttribute("message", messageDB.get(candidate.get_username()));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("PositionController");
                    dispatcher.forward(request, response);
                }
                else
                {
                    HttpSession session= request.getSession(true);
                    session.setAttribute("username",request.getParameter("username"));
                    request.setAttribute("message", messageDB.get(session.getAttribute("username").toString()));
                    response.sendRedirect("Hr_Home.jsp");
                }
            }
        }
        else if(request.getParameter("submit").equals("Apply"))
        {
            HttpSession session=request.getSession(true);
            Candidate candidate=candidateDB.get(request.getParameter(session.getAttribute("username").toString()));
            request.setAttribute("candidate",candidate);
            request.setAttribute("message", messageDB.get(candidate.get_username()));
            RequestDispatcher dispatcher=request.getRequestDispatcher("ApplyController");  
            dispatcher.include(request,response);  
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
