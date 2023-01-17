package by.htp.ex.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.htp.ex.bean.Comment;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.ICommentDAO;

public class CommentDAO implements ICommentDAO {
    private final String MSG_ERR = "Error whith comments!";
    private final String SQL_SELECT_ID_USER = "SELECT * FROM comments_post `user_id`, user `id` where `user_id` = `id` and `id_comment` = '%s'";
    private final String SQL_SELECT_COMMENT = "SELECT * FROM comments_post `user_id`, user `id` where `user_id` = `id` and `post_id` = '%s'";
    private final String UPDATE_COMMENT = "UPDATE `vibestretch`.`comments_post` SET `comment_text` = '%s' WHERE (`id_comment` = '%s')";
    private final String DELETE_COMMENT = "DELETE FROM `comments_post` WHERE (`id_comment` = '%s');";

    @Override
    public Comment findCommentById(String commentId) throws DaoException {
        Statement st = null;
        Connection con = null;
        ResultSet rs = null;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {
            Comment comment = null;
            String sqlCommentPost = String.format(SQL_SELECT_ID_USER, commentId);
            st = con.createStatement();
            rs = st.executeQuery(sqlCommentPost);
            while (rs.next()) {
               comment = new Comment(rs.getInt(1),rs.getInt(2), rs.getString(7), rs.getString(4), rs.getString(5));
            }
            return comment;
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        }finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st,rs);
        }
    }

    @Override
    public List<Comment> findAllComment(String postId) throws DaoException {
        Statement st = null;
        Connection con = null;
        ResultSet rs = null;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {
            List<Comment> listComment = new ArrayList<>();

            String sqlCommentPost = String.format(SQL_SELECT_COMMENT, postId);
            st = con.createStatement();
            rs = st.executeQuery(sqlCommentPost);
            while (rs.next()) {
                listComment.add(new Comment(rs.getInt(1),rs.getInt(2), rs.getString(7), rs.getString(4), rs.getString(5)));
            }
            return listComment;
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        }finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st,rs);
        }
    }

    @Override
    public Comment getComment() {
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();

        return null;
    }

    @Override
    public void creatComment(Comment comment) throws DaoException {
        Statement st = null;
        Connection con = null;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {
            String sqlCreateComment = String.format(
                    "INSERT INTO `vibestretch`.`comments_post` (`user_id`, `post_id`, `comment_text`, `comment_date`) VALUES ('%s', '%s', '%s', '%s')",
                    comment.getUserId(), comment.getPostId(), comment.getCommentText(), comment.getCommentDate());
            st = con.prepareStatement(sqlCreateComment);
            st.executeUpdate(sqlCreateComment);
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        }finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st);
        }
    }

    @Override
    public boolean changeComment(Comment comment) throws DaoException  {
        Statement st = null;
        Connection con = null;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {
            String sqlCreateComment = String.format(
                    UPDATE_COMMENT,
                    comment.getCommentText(), comment.getCommentId());
            st = con.prepareStatement(sqlCreateComment);
            st.executeUpdate(sqlCreateComment);
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        }finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st);
        }
        return false;
    }

    @Override
    public boolean deleteComment(String commentId) throws DaoException {
        
        Connection con = null;
        Statement st = null;
        boolean exec = false;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {
            String sqlDel = String.format(DELETE_COMMENT, commentId);
            st = con.prepareStatement(sqlDel);
            if (st.executeUpdate(sqlDel) == 1) {
                exec = true;
            }
            return exec;

        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st);
        }
    }

}
