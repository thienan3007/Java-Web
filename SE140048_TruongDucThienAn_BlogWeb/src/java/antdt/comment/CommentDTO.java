/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.comment;

import antdt.account.AccountDTO;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author antru
 */
public class CommentDTO implements Serializable{
    private  int id;
    private String content;
    private AccountDTO user;
    private Date commentDate;
    private int statusID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AccountDTO getUser() {
        return user;
    }

    public void setUser(AccountDTO user) {
        this.user = user;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public CommentDTO(int id, String content, AccountDTO user, Date commentDate, int statusID) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.commentDate = commentDate;
        this.statusID = statusID;
    }
    
    public CommentDTO(String content, AccountDTO user, Date commentDate, int statusID) {
        this.content = content;
        this.user = user;
        this.commentDate = commentDate;
        this.statusID = statusID;
    }

    public CommentDTO() {
    }
    

    @Override
    public String toString() {
        return "CommentDTO{" + "id=" + id + ", content=" + content + ", user=" + user + ", commentDate=" + commentDate + ", statusID=" + statusID + '}';
    }
    
    
}
