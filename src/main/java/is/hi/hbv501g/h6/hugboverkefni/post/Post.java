package is.hi.hbv501g.h6.hugboverkefni.post;

import is.hi.hbv501g.h6.hugboverkefni.user.User;

public class Post extends Message {
    private String title;

    public Post createPost(String title, Content content, User user) {
        this.title = title;
        this.setUser(user);
        this.setContent(content);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                '}';
    }
}

