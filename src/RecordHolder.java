class RecordHolder {
    private double theta;
    private double[] weight;
    private int lifeTime;

    RecordHolder(double theta, double[] weight, int lifeTime){
        this.theta = theta;
        this.weight = weight;
        this.lifeTime = lifeTime;
    }

    void setTheta(double theta) {
        this.theta = theta;
    }

    double getTheta() {
        return theta;
    }

    void setWeight(double[] weight) {
        this.weight = weight;
    }

    double[] getWeight() {
        return weight;
    }

    void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    int getLifeTime() {
        return lifeTime;
    }
}
