package by.htp.ex.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import by.htp.ex.bean.Category;
import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;


import java.sql.ResultSet;

public class NewsDAO implements INewsDAO {
    private Connection con;
    private ResultSet rs;
    private final String MSG_ERR = "Some problems whith database";
    private final String INSERT_NEWS = "INSERT INTO `posts` (`title`, `text`, `image`, `date_post`, `user_id`, `idCategory`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')";
    private final String UPDATE_NEWS = "UPDATE `posts` SET `title` = '%s', `text` = '%s', `image` = '%s', `date_post` = '%s', `user_id` = '%s' WHERE (`id` = '%s')";
    private final String DELETE_NEWS = "DELETE FROM `posts` WHERE (`id` = '%s')";
    private final String SELECT_CATEGORY = "SELECT * FROM category";
    private final String SELECT_POST_ID = "SELECT * FROM posts where `id`= '%s'";
    private final String S_POSTS_CATEGORY = "SELECT * FROM posts, category where `idCategory`= category.id";
    private final String S_POSTS_CATEGORY_ID = "SELECT * FROM posts, category where posts.idCategory=category.id and posts.id = '%s'";
    private final String S_POSTS_CATEGPRY_CNAME = "SELECT * FROM posts, category where `idCategory`= category.id and category_name ='%s'";

    @Override
    public void addNews(News news) throws DaoException {
        try {
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            String sqlQuery = String.format(
                    INSERT_NEWS,
                    news.getTitle(), news.getText(), news.getImageDir(), news.getPostDate(), news.getUserId(),
                    news.getCategory());
            con.prepareStatement(sqlQuery).executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        }
    }

    @Override
    public void update(News news) throws DaoException {
        try {
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            news.setPostDate(news.onCreate());
            String sqlAllNews = String.format(UPDATE_NEWS, news.getTitle(),news.getText(),news.getImageDir(),news.getPostDate(), news.getUserId(),news.getId());
            PreparedStatement prepStatement = con.prepareStatement(sqlAllNews);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            // TODO: handle exception
        }
    }

    @Override
    public void deleteNews(String idNews) throws DaoException {
        try {
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            String sqlDel = String.format(DELETE_NEWS, idNews);
            con.prepareStatement(sqlDel).executeUpdate(sqlDel);
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        }
    }

    @Override
    public List<Category> findAllCategory() throws DaoException {
        try {
            List<Category> categoryList = new ArrayList<>();
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            rs = con.createStatement().executeQuery(SELECT_CATEGORY);
            while (rs.next()) {
                categoryList.add(new Category(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            rs = null;
            return categoryList;
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);

        }
    }

    @Override
    public News getNews(String idPost) throws DaoException {
        try {
            rs = null;
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            String sqlAllNews = String.format(S_POSTS_CATEGORY_ID, idPost);

            rs = con.createStatement().executeQuery(sqlAllNews);
            News news = null;
            while (rs.next()) {
                news = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4),
                        rs.getString(9), rs.getInt(6));
            }
            return news;
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);

        }

    }

    public List<News> findByCategory(String category) throws DaoException {
        con = null;
        try {
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();

            List<News> listok2 = new ArrayList<>();
            String sqlByCategory = String.format(
                S_POSTS_CATEGPRY_CNAME, category);
            rs = con.createStatement().executeQuery(sqlByCategory);
            while (rs.next()) {
                listok2.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4),
                        rs.getString(9), rs.getInt(6)));
            }
            return listok2;
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        }
    }

    public List<News> getAllNews() throws DaoException {
        try {
            con = null;
            rs = null;
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            List<News> listok = new ArrayList<>();
           
            rs = con.createStatement().executeQuery(S_POSTS_CATEGORY);
            while (rs.next()) {
                listok.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4),
                        rs.getString(9), rs.getInt(6)));
            }
            return listok;
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        }
    }

    @Override
    public void writeLike(String idNews) throws DaoException {
        try {
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            String sqlgetLike = String.format(SELECT_POST_ID, idNews);
            rs = con.createStatement().executeQuery(sqlgetLike);
            int likeCount = 0;
            if (rs.next()) {
                likeCount = rs.getInt(8);
            }
            String sqlLike = String.format("UPDATE `posts` SET `likeCount` = '%s' WHERE (`id` = '%s');",
                    ++likeCount, idNews);
        } catch (SQLException e) {
            throw new DaoException(MSG_ERR, e);
        }

    }

}
