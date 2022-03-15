package Client;

import java.io.Serializable;

public class Request implements Serializable {
    int weight;
    int height;
    int score;

    public Request(int weight, int height, int  score) {
        this.weight = weight;
        this.height = height;
        this.score = score;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getScore() {
        return score;
    }
}
