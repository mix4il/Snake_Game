package Client;

import java.io.Serializable;

public class Response implements Serializable {

    int appleX;
    int appleY;
    String resRecord;

    public Response(int appleX, int appleY, String resRecord) {
        this.appleX = appleX;
        this.appleY = appleY;
        this.resRecord = resRecord;
    }

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public String getResRecord() {
        return resRecord;
    }
}
