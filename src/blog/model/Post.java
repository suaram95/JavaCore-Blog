package blog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

    private User user;
    private String title;
    private String text;
    private Category category;
    private Date createdDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy hh:mm:ss");

    public Post(User user, String title, String text, Category category, Date createdDate, SimpleDateFormat sdf) {
        this.user = user;
        this.title = title;
        this.text = text;
        this.category = category;
        this.createdDate = createdDate;
        this.sdf = sdf;
    }

    public Post() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (user != null ? !user.equals(post.user) : post.user != null) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (text != null ? !text.equals(post.text) : post.text != null) return false;
        if (category != post.category) return false;
        if (createdDate != null ? !createdDate.equals(post.createdDate) : post.createdDate != null) return false;
        return sdf != null ? sdf.equals(post.sdf) : post.sdf == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (sdf != null ? sdf.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String result;
        result = "      POST " + '\n';
        result += "  About User " +'\n'+" Name: "+user.getName()+'\n'+" Surname: "+user.getSurname()+ '\n'
                   +" Email: "+user.getEmail() + '\n';
        result += "   Title: " + title + '\n';
        result += "   Text: " + text + '\n';
        result += "   Category: " + category + '\n';
        result += "   Date: " + sdf.format(createdDate)+'\n';
        return result;
    }
}
