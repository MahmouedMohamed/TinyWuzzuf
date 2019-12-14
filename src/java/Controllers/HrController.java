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
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
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
    public void reDirect(HttpServletRequest request, HttpServletResponse response,String route) throws IOException, ServletException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher(route);
        dispatcher.forward(request, response);
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
                reDirect(request,response,"Apply.jsp");
        }
        else if(request.getParameter("submit").equals("submitApplier"))
        {
            step=2;
            Candidate candidate=hrDB.getApplierDetails(request.getParameter("candidateName"));
            request.setAttribute("candidate",candidate);
            request.setAttribute("step",step);
            reDirect(request,response,"Apply.jsp");
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
                            reDirect(request,response,"Apply.jsp");
        }
                else if(request.getParameter("submit").equals("DisApprove"))
        {
            step=0;
            hrDB.disApprove(request.getParameter("candidateName"), request.getParameter("candidateJob"));
                            reDirect(request,response,"Hr_Home.jsp");
        }
        else if(request.getParameter("submit").equals("Approve"))
        {
            Map<String, String[]> parameters = request.getParameterMap();
            String[] values ;
            String exam="";
            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                if (entry.getKey().startsWith("chosen")) {
                    values = entry.getValue();
                    for(int i=0;i<values.length;i++)
                    {
                        exam+=values[i].trim(); //delete all white spaces and /n
                        if(i+1<values.length)
                        {
                            exam+=" ";
                        }
                    }
                }
            }
            String email=request.getParameter("candidateName");
            String job=request.getParameter("candidateJob");
            Date deadline=Date.valueOf(request.getParameter("deadline"));
            hrDB.approve(email,deadline,exam,job);
            reDirect(request,response,"Hr_Home.jsp");
        }
        else if(request.getParameter("submit").equals("show_exam"))
        {
            request.setAttribute("examTypes",hrDB.getAllExam());
            request.setAttribute("status","show_exam");
            reDirect(request,response,"show.jsp");
        }
        else if(request.getParameter("submit").equals("add_exam"))
        {
            request.setAttribute("AllPosition",hrDB.getAllPosition());
            request.setAttribute("status","add_exam");
            reDirect(request,response,"add.jsp");
        }
        else if(request.getParameter("submit").equals("addExam"))
        {
            hrDB.addExam(request.getParameter("type"),request.getParameter("title"));
            reDirect(request,response,"Hr_Home.jsp");
        }
        else if(request.getParameter("submit").equals("update_exam"))
        {
            request.setAttribute("status","update_exam");
            request.setAttribute("examTypes",hrDB.getAllExam());
            reDirect(request,response,"update.jsp");
        }
        else if(request.getParameter("submit").equals("updateExam"))
        {
            hrDB.updateExam(request.getParameter("oldTitle"),request.getParameter("newTitle"));
            reDirect(request,response,"Hr_Home.jsp");
        }
        else if(request.getParameter("submit").equals("delete_exam"))
        {
            request.setAttribute("status","delete_exam");
            request.setAttribute("examTypes",hrDB.getAllExam());
            reDirect(request,response,"delete.jsp");
        }
        else if(request.getParameter("submit").equals("deleteExam"))
        {
            hrDB.deleteExam(request.getParameter("title"));
            reDirect(request,response,"Hr_Home.jsp");
        }
        else if(request.getParameter("submit").equals("show_questions&answers"))
        {
            request.setAttribute("status","show_questions&answers");
            request.setAttribute("examTypes",hrDB.getAllExam());
            reDirect(request,response,"show.jsp");
        }
        else if(request.getParameter("submit").equals("showQuestions&Answers"))
        {
            request.setAttribute("status","showQuestions&Answers");
            request.setAttribute("Q&A",hrDB.getAllQuestionsAndAnswers(request.getParameter("type")));
            reDirect(request,response,"show.jsp");
        }
        else if(request.getParameter("submit").equals("add_questions&answers"))
        {
            request.setAttribute("status","add_questions&answers");
            reDirect(request,response,"add.jsp");
        }
        else if(request.getParameter("submit").equals("add_Question"))
        {
            request.setAttribute("status","add_Question");
            request.setAttribute("examTypes",hrDB.getAllExam());
            reDirect(request,response,"add.jsp");
        }
        else if(request.getParameter("submit").equals("addQuestion"))
        {
            hrDB.addQuestion(request.getParameter("text"),request.getParameter("type"));
            reDirect(request,response,"Hr_Home.jsp");
        }
        else if(request.getParameter("submit").equals("add_Answer"))
        {
            request.setAttribute("status","add_Answer");
            request.setAttribute("Q&A",hrDB.getAllQuestionsAndAnswers());
            reDirect(request,response,"add.jsp");
        }
        else if(request.getParameter("submit").equals("addAnswer"))
        {
            hrDB.addAnswer(
                    request.getParameter("text"),
                    Integer.parseInt(request.getParameter("status")),
                    request.getParameter("QID")
            );
            reDirect(request,response,"Hr_Home.jsp");
        }
        else if(request.getParameter("submit").equals("update_questions&answers"))
        {
            request.setAttribute("status","update_questions&answers");
            request.setAttribute("examTypes",hrDB.getAllExam());
            reDirect(request,response,"update.jsp");
        }
        else if(request.getParameter("submit").equals("update_Question"))
        {
            request.setAttribute("status","update_Question");
            request.setAttribute("Q&A",hrDB.getAllQuestionsAndAnswers());
            reDirect(request,response,"update.jsp");
        }
        else if(request.getParameter("submit").equals("updateQuestion"))
        {
            hrDB.updateQuestion(request.getParameter("QID"),request.getParameter("text"));
            reDirect(request,response,"Hr_Home.jsp");
        }
        else if(request.getParameter("submit").equals("update_Answer"))
        {
            request.setAttribute("status","update_Answer");
            request.setAttribute("Q&A",hrDB.getAllQuestionsAndAnswers());
            reDirect(request,response,"update.jsp");
        }
        else if(request.getParameter("submit").equals("updateAnswer"))
        {
            hrDB.updateAnswer(
                    request.getParameter("AID"),
                    request.getParameter("QID"),
                    request.getParameter("text"),
                    Integer.parseInt(request.getParameter("status"))
            );
            reDirect(request,response,"Hr_Home.jsp");
        }
        else if(request.getParameter("submit").equals("delete_questions&answers"))
        {
            request.setAttribute("status","delete_questions&answers");
            request.setAttribute("examTypes",hrDB.getAllExam());
            reDirect(request,response,"delete.jsp");
        }
        else if(request.getParameter("submit").equals("delete_Question"))
        {
            request.setAttribute("status","delete_Question");
            request.setAttribute("Q&A",hrDB.getAllQuestionsAndAnswers());
            reDirect(request,response,"delete.jsp");
        }
        else if(request.getParameter("submit").equals("deleteQuestion"))
        {
            hrDB.deleteQuestion(request.getParameter("QID"));
            reDirect(request,response,"Hr_Home.jsp");
        }
        else if(request.getParameter("submit").equals("delete_Answer"))
        {
            request.setAttribute("status","delete_Answer");
            request.setAttribute("Q&A",hrDB.getAllQuestionsAndAnswers());
            reDirect(request,response,"delete.jsp");
        }
        else if(request.getParameter("submit").equals("deleteAnswer"))
        {
            hrDB.deleteAnswer(request.getParameter("AID"));
            reDirect(request,response,"Hr_Home.jsp");
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
