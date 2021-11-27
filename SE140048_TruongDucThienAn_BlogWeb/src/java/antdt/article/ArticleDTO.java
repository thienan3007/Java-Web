package antdt.article;


import antdt.account.AccountDTO;
import antdt.comment.CommentDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author antru
 */
public class ArticleDTO implements Serializable{
    private int id;
    private String title;
    private String content;
    private Date postingDate;
    private String description;
    private AccountDTO author;
    private int statusID;
    private List<CommentDTO> comments;

    public ArticleDTO(int id, String title, String content, Date postingDate, String description, AccountDTO author, int statusID, List<CommentDTO> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postingDate = postingDate;
        this.description = description;
        this.author = author;
        this.statusID = statusID;
        this.comments = comments;
    }
    
    public ArticleDTO(int id, String title, String content, Date postingDate, String description, int statusID, List<CommentDTO> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postingDate = postingDate;
        this.description = description;
        this.statusID = statusID;
        this.comments = comments;
    }
    
    public ArticleDTO(String title, String content, Date postingDate, String description, AccountDTO author, int statusID) {
        this.title = title;
        this.content = content;
        this.postingDate = postingDate;
        this.description = description;
        this.author = author;
        this.statusID = statusID;
    }
    
    public ArticleDTO(String title, String content, Date postingDate, String description, int statusID) {
        this.title = title;
        this.content = content;
        this.postingDate = postingDate;
        this.description = description;
        this.statusID = statusID;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountDTO getAuthor() {
        return author;
    }

    public void setAuthor(AccountDTO author) {
        this.author = author;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" + "id=" + id + ", title=" + title + ", content=" + content + ", postingDate=" + postingDate + ", description=" + description + ", author=" + author + ", statusID=" + statusID + '}';
    }
    
    
}
