package by.htp.ex.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private static final long serialVersionUID = 1L;
    
    private int postId;
    private int userId;
    private String userName;
    private String commentText;
    private String commentDate;


    public Comment(){

    }

    public Comment(int userId, String userName, String commentText, String commentDate) {
        this.userId = userId;
        this.userName = userName;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    public Comment(int postId, int userId, String commentText) {
        this.postId = postId;
        this.userId = userId;
        this.commentText = commentText;
        this.commentDate = onCreate();
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

   

   
    

    public String onCreate() {
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }

    
}
