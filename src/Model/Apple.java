package Model;

import core.Position;

import javax.swing.*;
import java.awt.*;

public class Apple {
    private Position positionApple;
    int SIZE_DOT = 25;
    Image appleImg;

    public Apple(Position positionApple) {
        this.positionApple = positionApple;
        loadImage();
    }

    public void loadImage(){
        ImageIcon appleIcon = new ImageIcon("/Users/mihailbaskakov/IdeaProjects/Snake_Game/src/assets/apple.png");
        appleImg = appleIcon.getImage();
    }


    public void draw(Graphics g){
        g.drawImage(appleImg, positionApple.getX(), positionApple.getY(), null);
    }

    public void createApple(Position newPosition){
        this.positionApple = newPosition;
    }

    public Position getPositionApple() {
        return positionApple;
    }
}
