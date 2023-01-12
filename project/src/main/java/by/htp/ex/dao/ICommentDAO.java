package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.Comment;

public interface ICommentDAO {

    Comment getComment()  throws DaoException;
    void creatComment(Comment comemnt) throws DaoException;
    Comment changeComment(Comment comment) throws DaoException;
    Comment findCommentById(int id) throws DaoException;
    List<Comment> findAllComment(String postId) throws DaoException;
    
}
