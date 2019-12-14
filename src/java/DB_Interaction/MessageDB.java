/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Candidate;
import Models.Message;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;
import jdk.jfr.Timestamp;

/**
 *
 * @author hp
 */
public class MessageDB {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public Vector<Message> get(String email) {
        createMessage();
        Vector<Message> list=null;
        Message message=null;
        try {	
		list = new Vector<Message>();
		String sql = "SELECT * FROM `Message` where `to` ='"+email+"'";
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
                        message = new Message(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5));
			list.add(message);
                }
            }
        catch(SQLException ex)
        {
            
        }
	return (list);
    }
    public boolean createMessage()
    {
        boolean flag=false;
        try{
        
        connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
                String query="Delete from message where 1";
		statement.executeUpdate(query);
                query="Select * from application where 1";
		resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
                    String title=resultSet.getString(1);
                    String applier=resultSet.getString(2);
                    Date deadline=resultSet.getDate(3);
                    String exams=resultSet.getString(4);
//                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
//                    formatter.format(date);
//                    if(deadline.compareTo(date)==1)
//                    {
                        String sql="insert into `message` VALUES("
                        + "'" + "You have applied for "+ title + " so you have to take those exams "+ exams  + "'" + ","
                        + "'" + (int)(Math.random()*date.getTime()*Math.pow(10,100)) + "'" + ","
                        +"'" + "System" +"'" + ","
                        + "'" + applier  + "'" + ","
                        + "'" + 0 + "'"
                        +")";
                statement.executeUpdate(sql);
//                    }
                }
                flag=true;
        }
        catch(SQLException ex)
        {
            
        }
        return flag;
    }
}