package Dao.Bean;


public class Quest {
    private int questId;
    private String title;
    private String description;
    private int price;
    private int reward;
    private int dataNumber;
    private double longitude = 0;
    private double latitude = 0;
    private int visitedTime = 0;

    public Quest(int questId, String title, String description, int price, int reward, double longtitude, double latitude, int visitedTime) {
        this.questId = questId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.reward = reward;
        this.longitude = longtitude;
        this.latitude = latitude;
        this.visitedTime = visitedTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getVisitedTime() {
        return visitedTime;
    }

    public void setVisitedTime(int visitedTime) {
        this.visitedTime = visitedTime;
    }

    public int getDataNumber() {
        return dataNumber;
    }

    public void setDataNumber(int dataNumber) {
        this.dataNumber = dataNumber;
    }


    public int getQuestId() {
        return questId;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
