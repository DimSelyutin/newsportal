package by.htp.ex.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import by.htp.ex.bean.Comment;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.ICommentDAO;

public class CommentDAO implements ICommentDAO {
    private final String MSG_ERR = "Error whith comments!";

    @Override
    public Comment findCommentById(int id) throws DaoException {
        return null;
        // try{

        // } catch (SQLException e) {
        // throw new DaoException(MSG_ERR, e);
        // }
    }

    @Override
    public List<Comment> findAllComment(String postId) throws DaoException {
        try {
            Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            List<Comment> listComment = new ArrayList<>();

            String sqlCommentPost = "SELECT * FROM vibestretch.comments_post `user_id`, vibestretch.user `id` where `user_id` = `id` and `post_id` = '"
                    + postId + "';";
            ResultSet rs = con.createStatement().executeQuery(sqlCommentPost);
            while (rs.next()) {
                listComment.add(new Comment(rs.getInt(2), rs.getString(7), rs.getString(4), rs.getString(5)));
            }
            return listComment;
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        }
    }

    @Override
    public Comment getComment() {
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();

        return null;
    }

    @Override
    public void creatComment(Comment comment) throws DaoException {
        try {
            Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            String sqlCreateComment = String.format(
                    "INSERT INTO `vibestretch`.`comments_post` (`user_id`, `post_id`, `comment_text`, `comment_date`) VALUES ('%s', '%s', '%s', '%s')",
                    comment.getUserId(), comment.getPostId(), comment.getCommentText(), comment.getCommentDate());
            con.prepareStatement(sqlCreateComment).executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        }
    }

    @Override
    public Comment changeComment(Comment comment) throws DaoException  {
        return null;
        // try{

        // } catch (SQLException e) {
        // throw new DaoException(MSG_ERR, e);
        // }
    }

}
