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
import by.htp.ex.dao.connectionPool.ConnectionPoolException;

import java.sql.ResultSet;

public class NewsDAO implements INewsDAO {
    private Connection con;
    private ResultSet rs;

    @Override
    public void addNews(News news) throws SQLException {
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        String sqlQuery = String.format("INSERT INTO `posts` (`title`, `text`, `image`, `date_post`, `user_id`, `idCategory`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",news.getTitle(),news.getText(), news.getImageDir(), news.getPostDate(), news.getUserId(), news.getCategory());
        con.prepareStatement(sqlQuery).executeUpdate();
    }

    @Override
    public void update(News news) throws SQLException, ConnectionPoolException {
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        news.setPostDate(news.onCreate());
        String sqlAllNews = "UPDATE `posts` SET `title` = '"+ news.getTitle() + "', `text` = '"+ news.getText()+ "', `image` = '"+ news.getImageDir()+ "', `date_post` = '" 
        + news.getPostDate() + "', `user_id` = '" 
        + news.getUserId()+ "' WHERE (`id` = '" 
        + news.getId() + "')";
        PreparedStatement prepStatement = con.prepareStatement(sqlAllNews);
        prepStatement.executeUpdate();

    }

    @Override
    public void deleteNews(News news) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Category> findAllCategory() throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        String sqlCategory = "SELECT * FROM category";
        
        rs = con.createStatement().executeQuery(sqlCategory);
        while(rs.next()){
            categoryList.add(new Category(rs.getInt(1),rs.getString(2), rs.getInt(3)));
        }
        rs = null;
        return categoryList;
    }

    @Override
    public News getNews(String idPost) throws ConnectionPoolException, SQLException, DaoException {
        rs = null;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        String sqlAllNews = "SELECT * FROM posts where `id`='" + idPost + "'";
        String strCategory = getCategoryForNews(idPost);

        rs = con.createStatement().executeQuery(sqlAllNews);
        News news = null;
        while (rs.next()) {
            news = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4),
                    strCategory, rs.getInt(6));
        }
        return news;
    }

    public List<News> getAllNews() throws ConnectionPoolException, SQLException, DaoException {
        List<Category> listCategory = findAllCategory();
        con = null;
        rs = null;
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        List<News> listok = new ArrayList<>();
        String sqlAllNews = "SELECT * FROM posts";
        rs = con.createStatement().executeQuery(sqlAllNews);
        while (rs.next()) {
            listok.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4),
            getCategoryF(listCategory, rs.getInt(7)), rs.getInt(6)));
        }
        return listok;
    }

    public String getCategoryF(List<Category> category, int idCategory){
        for (int i = 0; i < category.size(); i++) {
            if (category.get(i).getIdCategory() == idCategory) {
                return category.get(i).getNameCategory();
            }
        }
        return null;
    }
    public String getCategoryForNews(String idCategory) throws SQLException, ConnectionPoolException, DaoException {
        String category = null;
        String sqlPostCategory = "SELECT * FROM category where `id`='" + idCategory + "'";
        con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        rs = con.createStatement().executeQuery(sqlPostCategory);

        if (rs.next()) {
            category = rs.getString(2);
        }
        return category;
    }

}
