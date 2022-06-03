package Dao.Bean;

public class User {
    int userid;
    String userName;
    String password;
    int score;

    public User(int userid, String userName, String password, int score) {
        this.userid = userid;
        this.userName = userName;
        this.password = password;
        this.score = score;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
