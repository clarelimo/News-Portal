package models;

import java.util.Objects;

public class News {
    private int id;
    private String post;
    public  News(String post){
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public String getPost() {
        return post;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News that = (News) o;
        return id == that.id &&
                Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post, id);
    }
}
