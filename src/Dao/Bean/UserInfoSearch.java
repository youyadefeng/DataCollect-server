package Dao.Bean;

public class UserInfoSearch {
    int sex;
    int age;

    public UserInfoSearch(int sex, int age) {
        this.sex = sex;
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
