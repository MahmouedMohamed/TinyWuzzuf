<%-- 
    Document   : show
    Created on : Dec 14, 2019, 1:19:33 AM
    Author     : hp
--%>

<%@page import="Models.Question"%>
<%@page import="Models.Exam"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<%  if(request.getAttribute("status").toString().equals("show_exam"))
    {
        Vector<Exam> examTypes=new Vector<Exam>();
        if(request.getAttribute("examTypes")!=null)
        {
            examTypes=(Vector<Exam>) request.getAttribute("examTypes");
        }
%><%
    for(int i=0;i<examTypes.size();i++)
        {
%>          <h1>
            <%= examTypes.elementAt(i).getType() %>
            </h1><%
        }
    }
%>
<% if(request.getAttribute("status").toString().equals("show_questions&answers"))
    {
        Vector<Exam> AllExam=new Vector<Exam>();
        if(request.getAttribute("examTypes")!=null)
        {
            AllExam=(Vector<Exam>) request.getAttribute("examTypes");
        }
%>     
<form action="HrController">
    <select name="type">
<%
         for(int i=0;i<AllExam.size();i++)
        {
%>
            <option value="<%= AllExam.elementAt(i).getType() %> ">
            <%= AllExam.elementAt(i).getType() %>
            </option>
<%      } %>
    </select>
    <input type="submit" name="submit" value="showQuestions&Answers">
</form>
<% } %>
<% if(request.getAttribute("status").toString().equals("showQuestions&Answers"))
    {
        Vector<Question> AllQuestion=new Vector<Question>();
        if(request.getAttribute("Q&A")!=null)
        {
            AllQuestion=(Vector<Question>) request.getAttribute("Q&A");
        }
%><%
         for(int i=0;i<AllQuestion.size();i++)
        { %>
            <h2><%= AllQuestion.elementAt(i).getText() %></h2>
            <ul>
                <% for(int j=0;j<AllQuestion.elementAt(i).getAnswer().size();j++)
                {
                %><li><%= AllQuestion.elementAt(i).getAnswer().elementAt(j).getText() %></li><%
                }
%>
            </ul>
    <% }
    }
%>
    </body>
</html>
