package by.htp.ex.bean;

public class Category {
    
    private static final long serialVersionUID = 1L;
    
    private int idCategory;
    private String nameCategory;
    private int countPosts;

    
    public Category(int idCategory, String nameCategory, int countPosts) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.countPosts = countPosts;
    }

    public Category() {
    }
    
    public int getIdCategory() {
        return idCategory;
    }
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    public String getNameCategory() {
        return nameCategory;
    }
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
    public int getCountPosts() {
        return countPosts;
    }
    public void setCountPosts(int countPosts) {
        this.countPosts = countPosts;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idCategory;
        result = prime * result + ((nameCategory == null) ? 0 : nameCategory.hashCode());
        result = prime * result + countPosts;
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
        Category other = (Category) obj;
        if (idCategory != other.idCategory)
            return false;
        if (nameCategory == null) {
            if (other.nameCategory != null)
                return false;
        } else if (!nameCategory.equals(other.nameCategory))
            return false;
        if (countPosts != other.countPosts)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Category [idCategory=" + idCategory + ", nameCategory=" + nameCategory + ", countPosts=" + countPosts
                + "]";
    }
}
