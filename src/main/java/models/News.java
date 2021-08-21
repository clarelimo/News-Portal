package models;

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
}
