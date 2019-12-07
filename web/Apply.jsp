<%-- 
    Document   : Apply
    Created on : Dec 7, 2019, 3:58:05 PM
    Author     : hp
--%>

<%@page import="java.util.Vector"%>
<%@page import="Models.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <h1>Hello <%=session.getAttribute("username")%></h1>     
    <form action="HrController">
<%
    if(Integer.parseInt(request.getAttribute("step").toString())==1)
    {
        Vector<String> candidateEmails=new Vector<String>();
        if(request.getAttribute("candidateEmails")!=null)
        {
            candidateEmails=(Vector<String>) request.getAttribute("candidateEmails");
        }
%>
        <h1> Select The applier </h1>
        <select name="candidateName">
            <%
        for(int i=0;i<candidateEmails.size();i++)
        {
            %>
            <option value="<%= candidateEmails.elementAt(i) %>">
            <%= candidateEmails.elementAt(i)%>
            </option><%
        }
            %>
            <input type="submit" name="submit" value="submitApplier"/>
            <%
    }

    else if (Integer.parseInt(request.getAttribute("step").toString())==2)
    {
        Candidate candidate=new Candidate();
        if(request.getAttribute("candidate")!=null)
            {
                candidate=(Candidate)request.getAttribute("candidate");
            }
%>
        <h1> Select The job for <%=candidate.get_username()%></h1>
        <input type="text" name="candidateName" value="<%=candidate.get_username()%>" hidden></select>
        <select name="candidateJob">
            <%
        for(int i=0;i<candidate.get_appliedPosition().size();i++)
        {
            %>
            <option value="<%= candidate.get_appliedPosition().elementAt(i).getTitle() %>">
            <%= candidate.get_appliedPosition().elementAt(i).getTitle() %>
            </option><%
        }
            %>
            <input type="submit" name="submit" value="submitJob"/>
            <%
    }

else if (Integer.parseInt(request.getAttribute("step").toString())==3)
    {
        Candidate candidate=new Candidate();
Vector<String> exam=new Vector<String>();
        if(request.getAttribute("candidate")!=null)
            {
                candidate=(Candidate)request.getAttribute("candidate");
            }
if(request.getAttribute("exam")!=null)
            {
                exam=(Vector<String>)request.getAttribute("exam");
            }
%>
        <h1> Select The sequence of Exams for <%=candidate.get_username()%> to join <%=request.getAttribute("job")%></h1>
        <input type="text" name="candidateName" value="<%=candidate.get_username()%>" hidden></select>
        <input type="text" name="candidateJob" value="<%=request.getAttribute("job")%>" hidden></select>
        <select name="candidateJob">
            <%
        for(int i=0;i<candidate.get_appliedPosition().size();i++)
        {
            %>
            <option value="<%= candidate.get_appliedPosition().elementAt(i).getTitle() %>">
            <%= candidate.get_appliedPosition().elementAt(i).getTitle() %>
            </option><%
        }
            %>
            <input type="submit" name="submit" value="submitJob"/>
            <%
    }
%>
</form>
    </body>
</html>
