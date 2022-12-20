package by.htp.ex.bean;


public class News {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String text;
    private String postDate;

    public News(String title, String text, String postDate) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((postDate == null) ? 0 : postDate.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        return true;
    }

    @Override
    public String toString() {
        return "News [id=" + id + ", title=" + title + ", text=" + text + ", postDate=" + postDate + "]";
    }
}
