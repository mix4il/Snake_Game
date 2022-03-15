package Model;
import java.awt.*;
import javax.swing.*;

import core.Constant;
import core.Position;

import java.util.ArrayList;

public class Snake {
    private ArrayList<Position> snakeParts = new ArrayList<>();
    int SIZE_DOT = 25;
    Image snakeRight;
    Image snakeUp;
    Image snakeLeft;
    Image snakeDown;
    Image snakeBody;
    Direction direction;

    public Snake() {
        initSnake();
        loadImage();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Direction getDirection() {
        return direction;
    }

    public void loadImage(){
        ImageIcon snakeImgRight = new ImageIcon("/Users/mihailbaskakov/IdeaProjects/Snake_Game/src/assets//snake_right.png");
        snakeRight = snakeImgRight.getImage();
        ImageIcon snakeImgDown = new ImageIcon("/Users/mihailbaskakov/IdeaProjects/Snake_Game/src/assets//snake_down.png");
        snakeDown = snakeImgDown.getImage();
        ImageIcon snakeImgUp = new ImageIcon("/Users/mihailbaskakov/IdeaProjects/Snake_Game/src/assets//snake_up.png");
        snakeUp = snakeImgUp.getImage();
        ImageIcon snakeImgLeft = new ImageIcon("/Users/mihailbaskakov/IdeaProjects/Snake_Game/src/assets//snake_left.png");
        snakeLeft = snakeImgLeft.getImage();
        ImageIcon snakeBodyImg = new ImageIcon("/Users/mihailbaskakov/IdeaProjects/Snake_Game/src/assets//body.png");
        snakeBody = snakeBodyImg.getImage();
    }


    public void initSnake(){
        direction = Direction.RIGHT;
        snakeParts.clear();
        for(int i = 4; i < 7; i++){
            snakeParts.add(new Position(i * SIZE_DOT, SIZE_DOT * 4));
        }
    }

    public void move(){
        Position head = snakeParts.get(snakeParts.size() - 1);
        snakeParts.remove(0);
        switch (direction){
            case RIGHT:
                snakeParts.add(new Position(head.getX() + SIZE_DOT, head.getY()));
                break;
            case LEFT:
                snakeParts.add(new Position(head.getX() - SIZE_DOT, head.getY()));
                break;
            case DOWN:
                snakeParts.add(new Position(head.getX(), head.getY() + SIZE_DOT));
                break;
            case UP:
                snakeParts.add(new Position(head.getX(), head.getY() - SIZE_DOT));
                break;
        }
    }

    public void moveBarrier(){
        Position head = snakeParts.get(snakeParts.size() - 1);
        snakeParts.remove(0);
        switch (direction){
            case RIGHT:
                if(head.getX() > Constant.WIDTH_FIELD - Constant.DOT_SIZE)
                    snakeParts.add(new Position(0, head.getY()));
                else
                    snakeParts.add(new Position(head.getX() + Constant.DOT_SIZE, head.getY()));
                break;
            case LEFT:
                if(head.getX() < 5)
                    snakeParts.add(new Position(Constant.WIDTH_FIELD - Constant.DOT_SIZE, head.getY()));
                else
                    snakeParts.add(new Position(head.getX() - Constant.DOT_SIZE, head.getY()));
                break;
            case DOWN:
                if(head.getY() > Constant.HEIGHT_FIELD - 2 * Constant.DOT_SIZE)
                    snakeParts.add(new Position(head.getX(), Constant.DOT_SIZE));
                else
                    snakeParts.add(new Position(head.getX(), head.getY() + Constant.DOT_SIZE));
                break;
            case UP:
                if(head.getY() < Constant.DOT_SIZE)
                    snakeParts.add(new Position(head.getX(),  Constant.HEIGHT_FIELD - 2 * Constant.DOT_SIZE));
                else
                    snakeParts.add(new Position(head.getX(), head.getY() - Constant.DOT_SIZE));
                break;
        }
    }

    public boolean checkCollisionSnake(){
        Position head = snakeParts.get(snakeParts.size() - 1);
        for(int i = 0; i < snakeParts.size() - 1; i++){
            if(head.equals(snakeParts.get(i))){
                return false;
            }
        }
        return true;
    }

    public boolean checkCollisionWall(ArrayList<Position> walls){
        Position head = snakeParts.get(snakeParts.size() - 1);
        for(int i = 0; i < walls.size() - 1; i++){
            if(head.equals(walls.get(i))){
                return false;
            }
        }
        return true;
    }

    public boolean checkApple(Position positionApple){
        Position head = snakeParts.get(snakeParts.size() - 1);
        return head.equals(positionApple);
    }

    public void addParts(){
        Position head = snakeParts.get(snakeParts.size() - 1);
        switch (direction){
            case RIGHT:
                snakeParts.add(new Position(head.getX() + SIZE_DOT, head.getY()));
                break;
            case LEFT:
                snakeParts.add(new Position(head.getX() - SIZE_DOT, head.getY()));
                break;
            case DOWN:
                snakeParts.add(new Position(head.getX(), head.getY() + SIZE_DOT));
                break;
            case UP:
                snakeParts.add(new Position(head.getX(), head.getY() - SIZE_DOT));
                break;
        }
    }

    public void draw(Graphics g){
        for(int i = 0; i < snakeParts.size();i++){
            Position pos = snakeParts.get(i);
            if(i == snakeParts.size() - 1){
                switch (direction){
                    case RIGHT:
                        g.drawImage(snakeRight,pos.getX(), pos.getY(), null);
                        break;
                    case LEFT:
                        g.drawImage(snakeLeft,pos.getX(), pos.getY(), null);
                        break;
                    case DOWN:
                        g.drawImage(snakeDown,pos.getX(), pos.getY(), null);
                        break;
                    case UP:
                        g.drawImage(snakeUp,pos.getX(), pos.getY(), null);
                        break;
                }
            }
            else{
                    g.drawImage(snakeBody,pos.getX(), pos.getY(), null);
            }
        }
    }

}
