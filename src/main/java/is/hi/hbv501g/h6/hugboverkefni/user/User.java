package is.hi.hbv501g.h6.hugboverkefni.user;

public class User {
    private String user;
    private Integer userID;
    private String avatar;

    private String email;

    public User() {
    }

    // For the purposes of post inclusion
    public User(String user, Integer userID, String avatar) {
        this.user = user;
        this.userID = userID;
        this.avatar = avatar;
    }

    // For the purposes of user creation / user page
    public User(String user, Integer userID, String avatar, String email) {
        this.user = user;
        this.userID = userID;
        this.avatar = avatar;
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", userID=" + userID +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
