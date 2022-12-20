package by.htp.ex.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;

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

    public List<News> getAllNews(){
        List<News> listok = new ArrayList<>();
        News news = new News("Lorem ipsum", 
                                "WARNING [http-nio-8080-exec-106] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalsForLeaks When running on Java 9 or later you need to add  to the JVM command line arguments to enable",
                               createDatePost());
        for (int i = 0; i < 10; i++) {
            listok.add(news);
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
