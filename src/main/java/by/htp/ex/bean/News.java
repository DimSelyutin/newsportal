package by.htp.ex.bean;


public class News {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String text;
    private String postDate;

    public News(int id,String title, String text, String postDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.postDate = postDate;
    }

    public News() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
    
