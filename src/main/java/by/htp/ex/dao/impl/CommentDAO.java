package by.htp.ex.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.htp.ex.bean.Comment;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.ICommentDAO;


public class CommentDAO implements ICommentDAO{

    



    @Override
    public Comment findCommentById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Comment> findAllComment(String postId) throws SQLException {
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        List<Comment> listComment = new ArrayList<>();

        String sqlCommentPost = "SELECT * FROM vibestretch.comments_post `user_id`, vibestretch.user `id` where `user_id` = `id` and `post_id` = '"+postId+"';";
        ResultSet rs = con.createStatement().executeQuery(sqlCommentPost);
        while(rs.next()){
            listComment.add(new Comment(rs.getInt(2), rs.getString(7),rs.getString(4), rs.getString(5)));
        }
        return listComment;
    }

    @Override
    public Comment getComment() {
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        
        return null;
    }

    @Override
    public void creatComment(Comment comment) throws SQLException {
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        String sqlCreateComment = String.format("INSERT INTO `vibestretch`.`comments_post` (`user_id`, `post_id`, `comment_text`, `comment_date`) VALUES ('%s', '%s', '%s', '%s')",comment.getUserId(),comment.getPostId(),comment.getCommentText(),comment.getCommentDate());
        con.prepareStatement(sqlCreateComment).executeUpdate();
    }

    @Override
    public Comment changeComment(Comment comment) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
