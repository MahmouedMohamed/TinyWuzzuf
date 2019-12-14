/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author hp
 */
public class Message {
    String body;
    String MessageID;
    String from;
    String to;
    boolean seen;
    public Message()
    {
        
    }
    public Message(String body,String MessageID,String from,String to,boolean seen)
    {
        this.body=body;
        this.MessageID=MessageID;
        this.from=from;
        this.to=to;
        this.seen=seen;
    }
}
