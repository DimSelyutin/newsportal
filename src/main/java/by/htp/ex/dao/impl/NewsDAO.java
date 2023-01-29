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
    private final String INSERT_NEWS_TRANSLATE = "INSERT INTO `posts_%s` (`posts_id`,`title`, `text`) VALUES ('%s','%s', '%s')";
    private final String DROP_TRIGER = "DROP TRIGGER IF EXISTS `vibestretch`.`posts_AFTER_INSERT`";
    private final String CREATE_TRIGER = "CREATE DEFINER = CURRENT_USER TRIGGER `vibestretch`.`posts_AFTER_INSERT` AFTER INSERT ON `posts` FOR EACH ROW BEGIN INSERT INTO posts_%s (posts_id, title, text) VALUES (NEW.id, NEW.title, NEW.text); END";
    private final String UPDATE_NEWS = "UPDATE `posts_%s` SET `title` = '%s', `text` = '%s' WHERE (`posts_id` = '%s')";
    private final String DELETE_NEWS = "DELETE FROM `posts` WHERE (`id` = '%s')";
    private final String SELECT_CATEGORY = "SELECT *,(SELECT COUNT(idCategory) FROM posts where category.id = posts.idCategory) FROM category";
    private final String S_POSTS_CATEGORYS = "SELECT posts_%s.*, posts.date_post,  posts.image, category.category_name, posts.user_id, (SELECT COUNT(post) FROM vibestretch.user_post_likes where user_post_likes.post = posts_%s.posts_id) FROM posts_%s LEFT JOIN posts ON posts.id = posts_%s.posts_id LEFT JOIN category ON category.id = posts.idCategory";
    private final String S_POSTS_CATEGPRY_CNAME = "SELECT * FROM posts, category where `idCategory`= category.id and category_name ='%s'";
    private final String INSERT_LIKE = "INSERT INTO `vibestretch`.`user_post_likes` (`user`, `post`) VALUES ('%s', '%s')";
    private final String GET_LIKE_NEWS = "SELECT COUNT(post) FROM vibestretch.user_post_likes where post = '%s';";
    private final String GET_LIKED_NEWS = "SELECT * FROM vibestretch.user_post_likes where user = '%s'";
    private final String UPDATE_IMG_CATEGORY = "UPDATE `vibestretch`.`posts` SET `image` = '%s', `idCategory` = '%s' WHERE (`id` = '%s');";

    @Override
    public boolean addNews(News news) throws DaoException {
        boolean exec = false;
        Statement st = null;

        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        String sqlQueryLocal = String.format(CREATE_TRIGER, news.getLocal());
        try {
            con.createStatement().execute(DROP_TRIGER);

            con.createStatement().execute(sqlQueryLocal);

            String sqlQuery = String.format(INSERT_NEWS, news.getTitle(),
                    news.getText(),
                    news.getImageDir(),
                    news.getPostDate(),
                    news.getUserId(),
                    news.getCategory());

            st = con.prepareStatement(sqlQuery);

            if (st.executeUpdate(sqlQuery) == 1) {
                con.createStatement().execute(DROP_TRIGER);
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
    public boolean saveTranslate(News news) throws DaoException {

        Statement st = null;

        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {

            String sqlQuery = String.format(INSERT_NEWS_TRANSLATE, news.getLocal(),
                    news.getId(),
                    news.getTitle(),
                    news.getText());
            st = con.prepareStatement(sqlQuery);
            return !st.execute(sqlQuery);
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
            String sqlAllNews = String.format(UPDATE_NEWS, news.getLocal(), news.getTitle(), news.getText(), news.getId());
            String sqlUpdImgCAteg = String.format(UPDATE_IMG_CATEGORY,news.getImageDir(),news.getCategory(), news.getId());
            st = con.prepareStatement(sqlAllNews);
            if (st.executeUpdate(sqlAllNews) == 1 && con.createStatement().executeUpdate(sqlUpdImgCAteg) == 1) {
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
            st = con.prepareStatement(SELECT_CATEGORY);
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
            String sqlAllNews = String.format(S_POSTS_CATEGORYS + " WHERE posts_%s.posts_id = '%s'", local, local,
                    local,
                    local, local, idPost);
            st = con.createStatement();
            rs = st.executeQuery(sqlAllNews);
            News news = null;

            while (rs.next()) {
                news = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getString(8));
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
            String getAllNewsLocal = String.format(S_POSTS_CATEGORYS, local, local, local, local);

            st = con.createStatement();
            rs = st.executeQuery(getAllNewsLocal);
            while (rs.next()) {
                listok.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getString(8)));
            }
            return listok;
        } catch (SQLException e) {
            throw new DaoException("Error to get all news!");
        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st, rs);
        }
    }

    @Override
    public boolean writeLike(String idUser, String idNews) throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();

        try {

            String sqlCheck = String.format("SELECT * FROM user_post_likes where user = '%s' and post = '%s'", idUser,
                    idNews);
            String sql = String.format("DELETE FROM `vibestretch`.`user_post_likes` WHERE (`user`,`post`) = ('%s','%s');", idUser, idNews);
            st = con.createStatement();
            rs = st.executeQuery(sqlCheck);
            if (rs.next()) {
                st = con.createStatement();
                st.executeUpdate(sql);
                return true;
            } else {
                String sqlgetLike = String.format(INSERT_LIKE, idUser, idNews);
                st = con.createStatement();
                st.executeUpdate(sqlgetLike);
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException("Error to add like for news!", e);
        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st, rs);
        }

    }


    @Override
    public int getCountLike(String idNews) throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        int likeCount = 0;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {
            String sqlgetLike = String.format(GET_LIKE_NEWS, idNews);
            st = con.createStatement();
            rs = st.executeQuery(sqlgetLike);
            if (rs.next()) {
                likeCount = rs.getInt(1);
            }
            return likeCount;
        } catch (SQLException e) {
            throw new DaoException("Error to add like for news!", e);
        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st, rs);
        }
    }

    @Override
    public List<String> getLikedNews(String idUser) throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        List<String> likedNews = new ArrayList<>();
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        try {
            String sql = String.format(GET_LIKED_NEWS, idUser);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                likedNews.add(rs.getString(3));
            }
            return likedNews;
        } catch (SQLException e) {
            throw new DaoException("Error to add like for news!", e);
        } finally {
            DaoProvider.getInstance().getConnectionDAO().closeConnection(con, st, rs);
        }
    }

}
