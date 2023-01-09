package by.htp.ex.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.w3c.dom.DOMException;

import by.htp.ex.bean.Comment;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.ICommentDAO;
import by.htp.ex.service.ICommentService;



public class CommentServiceImpl implements ICommentService{


    private ICommentDAO commentDao = DaoProvider.getInstance().getCommentDAO();


    @Override
    public List<Comment> findCommentOfPost(String id) throws SQLException {
        List<Comment> comments = null;
        comments = commentDao.findAllComment(id);
        return comments;
    }


    @Override
    public void addComment(Comment comment) throws SQLException {
        
        commentDao.creatComment(comment);

    }
    
}
