package Dao.Bean;

public class SleepQualitySearch {
    int sleepEnough;
    int sleepTime;
    int sleepDifficulty;

    public SleepQualitySearch(int sleepEnough, int sleepTime, int sleepDifficulty) {
        this.sleepEnough = sleepEnough;
        this.sleepTime = sleepTime;
        this.sleepDifficulty = sleepDifficulty;
    }

    public int getSleepEnough() {
        return sleepEnough;
    }

    public void setSleepEnough(int sleepEnough) {
        this.sleepEnough = sleepEnough;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getSleepDifficulty() {
        return sleepDifficulty;
    }

    public void setSleepDifficulty(int sleepDifficulty) {
        this.sleepDifficulty = sleepDifficulty;
    }
}
