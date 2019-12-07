/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Timestamp;

/**
 *
 * @author hp
 */
public class Exam {
    String exam_name;
    String Type;
    Question question[];
    String relatedTo;
    public Exam(){}
    public Exam(String exam_name,String Type,String relatedTo)
    {
        this.exam_name=exam_name;
        this.relatedTo=relatedTo;
        this.Type=Type;
    }
}
