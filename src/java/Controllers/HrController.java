/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB_Interaction.DatabaseConnection;
import DB_Interaction.HrDB;
import DB_Interaction.PositionDB;
import Models.Candidate;
import Models.Exam;
import Models.Position;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
@WebServlet(name = "HrController", urlPatterns = {"/HrController"})
public class HrController extends HttpServlet {
    Vector<String> candidateEmail;
    Vector<Position> position;
    int step=0;
    HrDB hrDB=null;
    public HrController() {
            candidateEmail=new Vector<String>();
            hrDB = new HrDB();
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
        if(request.getParameter("submit").equals("approve"))
        {
            step=1;
            candidateEmail=hrDB.getAppliers();
                request.setAttribute("candidateEmails",candidateEmail);    
                request.setAttribute("step",step);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Apply.jsp");
                dispatcher.forward(request, response);
        }
        else if(request.getParameter("submit").equals("submitApplier"))
        {
            step=2;
            Candidate candidate=hrDB.getApplierDetails(request.getParameter("candidateName"));
            request.setAttribute("candidate",candidate);
            request.setAttribute("step",step);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Apply.jsp");
            dispatcher.forward(request, response);
        }
        else if(request.getParameter("submit").equals("submitJob"))
        {
            step=3;
            Vector<Exam> exam=hrDB.getAllExam();
            String job=request.getParameter("candidateJob");
            Candidate candidate=hrDB.getApplierDetails(request.getParameter("candidateName"));
            request.setAttribute("job", job);
            request.setAttribute("candidate",candidate);
            request.setAttribute("exam",exam);
            request.setAttribute("step",step);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Apply.jsp");
            dispatcher.forward(request, response);
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
