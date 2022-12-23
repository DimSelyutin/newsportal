package by.htp.ex.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.connection.ConnectionPoolException;
import java.sql.ResultSet;

public class NewsDAO implements INewsDAO{

    

    @Override
    public void addNews() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteNews(News news) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public News getNews() {
        return null;
    }

    public List<News> getAllNews() throws ConnectionPoolException, SQLException{
        List<News> listok = new ArrayList<>();
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();

        String sqlAllNews = "SELECT * FROM posts";

        ResultSet rs = con.createStatement().executeQuery(sqlAllNews);
        while(rs.next()){
            listok.add(new News(rs.getInt(1), rs.getString(2), rs.getString(2), rs.getString(5)));
        }
        return listok;
    }

    @Override
    public String createDatePost() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm");
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }
    
}
