package by.htp.ex.dao;

import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.Comment;

public interface ICommentDAO {

    Comment getComment();
    void creatComment(Comment comemnt) throws SQLException;
    Comment changeComment(Comment comment);
    Comment findCommentById(int id);
    List<Comment> findAllComment(String postId) throws SQLException;
    
}
