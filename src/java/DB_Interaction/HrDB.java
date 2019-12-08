/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Candidate;
import Models.Exam;
import Models.Position;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class HrDB {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public Vector<String> getAppliers() {
        Vector<String> list=null;
        Position position=null;
        try {	
		list = new Vector<String>();
		String sql = "SELECT DISTINCT email FROM `apply` WHERE 1";
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
                         list.add(resultSet.getString(1));
                }
            }
        catch(Exception ex)
        {
            
        }
	return (list);
    }
    public Candidate getApplierDetails(String email)
    {
        return new CandidateDB().getDetailedCandidate(email);
    }
    public Vector<Exam> getAllExam()
    {
        Vector<Exam> list=null;
        Exam exam=null;
        try {	
		list = new Vector<Exam>();
		String sql = "SELECT * FROM `exam` WHERE 1";
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
                    exam=new Exam(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                    list.add(exam);
                }
            }
        catch(Exception ex)
        {
            
        }
	return (list);
    }
    public boolean disApprove(String email,String title)
    {
        boolean flag=false;
        try {	
		String sql = "DELETE FROM `apply` WHERE email="
                        +" '"+email+"' "+"And title= '"+title+"'";
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		statement.executeUpdate(sql);
		flag=true;
            }
        catch(Exception ex)
        {
            
        }
	return (flag);
    }

    public void approve(String email, Date deadline,String exam,String job) {
        try {	
//		String sql = "DELETE FROM `apply` WHERE email="
//                        +" '"+email+"' "+"And title= '"+job+"'";
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
//		statement.executeUpdate(sql);
		String sql="insert into `application` VALUES("
                    + "'" + email + "'" + ","
                    +"'" + deadline +"'" + ","+ "'" + exam  + "'"
                    +")";
                statement.executeUpdate(sql);
        }
        catch(Exception ex)
        {
            
        }
    }
}
