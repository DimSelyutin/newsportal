package by.htp.ex.bean;


public class News {
    

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String text;
    private String postDate;
    private int userId;



    public News(int id, String title, String text, String postDate, int userId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.postDate = postDate;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((postDate == null) ? 0 : postDate.hashCode());
        result = prime * result + userId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        News other = (News) obj;
        if (id != other.id)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (postDate == null) {
            if (other.postDate != null)
                return false;
        } else if (!postDate.equals(other.postDate))
            return false;
        if (userId != other.userId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "News [id=" + id + ", title=" + title + ", text=" + text + ", postDate=" + postDate + ", userId="
                + userId + "]";
    }
}
    
