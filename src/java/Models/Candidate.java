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
public class Candidate extends User {
    String telephoneNumber;
    CV cv;
    Vector<Position> appliedPosition;
    Solution solution[];
    Application application[];
    public Candidate()
    {
        
    }
    public Candidate(String previlige,String username,String telephoneNumber,CV cv)
    {
        this.privilege=previlige;
        this.username=username;
        this.telephoneNumber=telephoneNumber;
        this.cv=cv;
    }
    public void setUsername(String username)
    {
        this.username=username;
    }
    public void setPrevilige(String privilege)
    {
        this.privilege=privilege;
    }
    public void setTelephoneNumber(String telephoneNumber)
    {
        this.telephoneNumber=telephoneNumber;
    }
    public void setCV(String cv_link)
    {
        this.cv.link=cv_link;
    }
    public void setAppliedPosition(Vector<Position> appliedPosition)
    {
        this.appliedPosition=appliedPosition;
    }
    public void setSolution(Solution solution[])
    {
        this.solution=solution;
    }
    public void setappliedPosition(Application application[])
    {
        this.application=application;
    }
    public String get_username()
    {
        return username;
    }
    public String get_cv()
    {
        return cv.getLink();
    }
    public String getPrevilige()
    {
        return privilege;
    }
    public String getTelephone()
    {
        return telephoneNumber;
    }
    public Vector<Position> get_appliedPosition()
    {
        return appliedPosition;
    }
    public Solution[] get_solution()
    {
        return solution;
    }
    public Application[] get_application()
    {
        return application;
    }
    
}
