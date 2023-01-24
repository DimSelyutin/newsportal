package by.htp.ex.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import by.htp.ex.bean.Category;
import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;

import java.sql.ResultSet;

public class NewsDAO implements INewsDAO {

    private final String INSERT_NEWS = "INSERT INTO `posts` (`title`, `text`, `image`, `date_post`, `user_id`, `idCategory`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')";

    private final String INSERT_LOCAL = "INSERT INTO `vibestretch`.`posts_%s` (`title`, `text`) VALUES ('%s', '%s')";

    private final String UPDATE_NEWS = "UPDATE `posts_%s` SET `title` = '%s', `text` = '%s', `image` = '%s', `user_id` = '%s' WHERE (`id` = '%s')";
    private final String DELETE_NEWS = "DELETE FROM `posts` WHERE (`id` = '%s')";
    private final String SELECT_CATEGORY = "SELECT * FROM category";
    private final String SELECT_POST_ID = "SELECT * FROM posts where `id`= '%s'";
    private final String S_POSTS_CATEGORY = "SELECT * FROM posts_%s, category where `idCategory`= category.id";
    private final String S_POSTS_CATEGORYS = "SELECT posts_%s.*, posts.date_post,  posts.image, category.category_name, posts.user_id FROM posts_%s LEFT JOIN posts ON posts.id = posts_%s.posts_id LEFT JOIN category ON category.id = posts.idCategory";
    private final String S_POSTS_CATEGORY_ID = "SELECT * FROM posts, category where posts.idCategory=category.id and posts.id = '%s'";
    private final String S_POSTS_CATEGPRY_CNAME = "SELECT * FROM posts, category where `idCategory`= category.id and category_name ='%s'";

    @Override
    public boolean addNews(News news) throws DaoException {
        Connection con = null;
        boolean exec = false;
        Statement st = null;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {
            int st2;
                String sqlQueryLocal = String.format("CREATE DEFINER = CURRENT_USER TRIGGER `vibestretch`.`posts_AFTER_INSERT` AFTER INSERT ON `posts` FOR EACH ROW BEGIN INSERT INTO posts_%s (posts_id, title, text) VALUES (NEW.id, NEW.title, NEW.text); END", news.getLocal());
        
                st2 = con.prepareStatement(sqlQueryLocal).executeUpdate();

            String sqlQuery = String.format(
                    INSERT_NEWS,
                    news.getTitle(), news.getText(), news.getImageDir(), news.getPostDate(), news.getUserId(),
                    news.getCategory());
            st = con.prepareStatement(sqlQuery);
            if (st.executeUpdate(sqlQuery) == 1) {
                String sq = "DROP TRIGGER IF EXISTS `vibestretch`.`posts_AFTER_INSERT`";
                con.prepareStatement(sq).executeUpdate();
                exec = true;
            }

            return exec;
        } catch (SQLException e) {
            throw new DaoException("Error to add news!", e);
        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st);
        }
    }

    @Override
    public boolean update(News news) throws DaoException {
        Connection con = null;
        Statement st = null;
        boolean exec = false;

        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {
            String sqlAllNews = String.format(UPDATE_NEWS,news.getLocal(),  news.getTitle(), news.getText(), news.getImageDir(),
                    news.getUserId(), news.getId());
            st = con.prepareStatement(sqlAllNews);
            if (st.executeUpdate(sqlAllNews) == 1) {
                exec = true;
            }

            return exec;
        } catch (SQLException e) {
            throw new DaoException("Error to update news!", e);
        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st);
        }
    }

    @Override
    public boolean deleteNews(String idNews) throws DaoException {
        Connection con = null;
        Statement st = null;
        boolean exec = false;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {
            String sqlDel = String.format(DELETE_NEWS, idNews);
            st = con.prepareStatement(sqlDel);
            if (st.executeUpdate(sqlDel) == 1) {
                exec = true;
            }
            return exec;

        } catch (SQLException e) {
            throw new DaoException("Error to delete news!", e);
        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st);
        }
    }

    @Override
    public List<Category> findAllCategory() throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            List<Category> categoryList = new ArrayList<>();
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_CATEGORY);
            while (rs.next()) {
                categoryList.add(new Category(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            return categoryList;
        } catch (SQLException e) {
            throw new DaoException("Error to find category news!", e);

        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st, rs);

        }
    }

    @Override
    public News getNews(String local, String idPost) throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            String sqlAllNews = String.format(S_POSTS_CATEGORYS+" WHERE posts_%s.posts_id = '%s'", local,local, local,local, idPost);
            System.out.println(sqlAllNews);
            st = con.createStatement();
            rs = st.executeQuery(sqlAllNews);
            News news = null;
            while (rs.next()) {
                news = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7));
            }
            return news;
        } catch (SQLException e) {
            throw new DaoException("Error to find news!", e);

        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st, rs);
        }

    }

    public List<News> findByCategory(String category) throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();

            List<News> listok2 = new ArrayList<>();
            String sqlByCategory = String.format(S_POSTS_CATEGPRY_CNAME, category);
            st = con.createStatement();
            rs = st.executeQuery(sqlByCategory);
            while (rs.next()) {
                listok2.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4),
                        rs.getString(10), rs.getInt(6)));
            }
            return listok2;
        } catch (SQLException e) {
            throw new DaoException("Error to find by category!", e);
        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st, rs);
        }
    }

    public List<News> getAllNews(String local) throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;

        try {
            con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            List<News> listok = new ArrayList<>();
            String getAllNewsLocal = String.format(S_POSTS_CATEGORYS, local,local,local);

            st = con.createStatement();
            rs = st.executeQuery(getAllNewsLocal);
            while (rs.next()) {
                listok.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7)));
            }
            return listok;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error to get all news!", e);
        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st, rs);
        }
    }

    @Override
    public void writeLike(String idUser, String idNews) throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {
            String sqlgetLike = String.format(
                    "INSERT INTO `vibestretch`.`user_post_likes` (`user`, `post`) VALUES ('%s', '%s')", idUser, idNews);
            rs = con.createStatement().executeQuery(sqlgetLike);

        } catch (SQLException e) {
            throw new DaoException("Error to add like for news!", e);
        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st, rs);
        }

    }

}
