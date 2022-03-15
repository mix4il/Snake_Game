package core;

public class Settings {
    public static int scores = 0;
    public Settings() {
    }

    public static void reset(){
        scores = 0;
    }

    public static void addScore(){
        scores++;
    }

    public static boolean isBarrier = true;

}
