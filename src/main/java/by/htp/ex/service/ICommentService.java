package by.htp.ex.service;

import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.Comment;

public interface ICommentService {
    List<Comment> findCommentOfPost(String id) throws SQLException;

    void addComment(Comment comment) throws SQLException;

}
