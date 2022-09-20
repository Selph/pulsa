package is.hi.hbv501g.h6.hugboverkefni.post;

import is.hi.hbv501g.h6.hugboverkefni.user.User;

public class Reply extends Message {
    public Reply(Content content, User user) {
        this.setContent(content);
        this.setUser(user);
    }


}
