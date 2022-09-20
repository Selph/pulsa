package is.hi.hbv501g.h6.hugboverkefni.post;

import is.hi.hbv501g.h6.hugboverkefni.user.User;

import java.util.Arrays;

public abstract class Message {

    private Content content;
    private User user;
    private Reply[] replies;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Reply[] getReplies() {
        return replies;
    }

    public void setReplies(Reply[] replies) {
        this.replies = replies;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content=" + content +
                ", user=" + user +
                ", replies=" + Arrays.toString(replies) +
                '}';
    }
}
