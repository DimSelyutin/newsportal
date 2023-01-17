package by.htp.ex.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int commentId;

    private int postId;
    private int userId;
    private String userName;
    private String commentText;
    private String commentDate;

    public Comment() {

    }

    // edit
    public Comment(int commentId, int postId, int userId, String commentText, String commentDate) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }
    //create
    public Comment(int commentId,int userId, String userName, String commentText, String commentDate) {
        this.commentId = commentId;
        this.userId = userId;
        this.userName = userName;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }
    //screate
    public Comment(int postId, int userId, String commentText) {
        this.postId = postId;
        this.userId = userId;
        this.commentText = commentText;
        this.commentDate = onCreate();
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + postId;
        result = prime * result + userId;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((commentText == null) ? 0 : commentText.hashCode());
        result = prime * result + ((commentDate == null) ? 0 : commentDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comment other = (Comment) obj;
        if (postId != other.postId)
            return false;
        if (userId != other.userId)
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (commentText == null) {
            if (other.commentText != null)
                return false;
        } else if (!commentText.equals(other.commentText))
            return false;
        if (commentDate == null) {
            if (other.commentDate != null)
                return false;
        } else if (!commentDate.equals(other.commentDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Comment [postId=" + postId + ", userId=" + userId + ", userName=" + userName + ", commentText="
                + commentText + ", commentDate=" + commentDate + "]";
    }

}
