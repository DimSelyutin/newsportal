package by.htp.ex.service;


import java.util.List;

import by.htp.ex.bean.Comment;

public interface ICommentService {
    List<Comment> findCommentOfPost(String id) throws ServiceException;

    void addComment(Comment comment) throws ServiceException;
    void chageComment(Comment comment, String userId) throws ServiceException;
    boolean deleteComment(String commentId) throws ServiceException;
    Comment findCommentById(String commentId) throws ServiceException;

}
