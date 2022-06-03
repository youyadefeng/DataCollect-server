package Dao.Bean;

public class EpidemicInfoSearch {
    int inRiskArea;
    int temperature;
    int isFever;
    int isContact;

    public EpidemicInfoSearch(int inRiskArea, int temperature, int isFever, int isContact) {
        this.inRiskArea = inRiskArea;
        this.temperature = temperature;
        this.isFever = isFever;
        this.isContact = isContact;
    }

    public int getInRiskArea() {
        return inRiskArea;
    }

    public void setInRiskArea(int inRiskArea) {
        this.inRiskArea = inRiskArea;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getIsFever() {
        return isFever;
    }

    public void setIsFever(int isFever) {
        this.isFever = isFever;
    }

    public int getIsContact() {
        return isContact;
    }

    public void setIsContact(int isContact) {
        this.isContact = isContact;
    }
}
