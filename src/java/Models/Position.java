/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Vector;

/**
 *
 * @author hp
 */
public class Position {
    String title;
//    Vector <User> applier;
    Vector <Exam> exam;
    public Position(){
//        applier=new Vector<User>();
        exam=new Vector<Exam>();
    }
    public Position(String title){
        this.title=title;
    }
    public String getTitle()
    {
        return title;
    }
    public void setUser(User user)
    {
//        applier.add(user);
    }
    public void setExam(Exam Exam)
    {
        exam.add(Exam);
    }
}
