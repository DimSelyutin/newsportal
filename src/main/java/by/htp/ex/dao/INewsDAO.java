package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.News;

public interface INewsDAO {
    void addNews();
    
    void deleteNews(News news);
    News getNews();
    String createDatePost();

    List<News> getAllNews();
}
