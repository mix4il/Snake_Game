package Controller;

import Client.Client;
import Model.Apple;
import Model.Direction;
import Model.Snake;
import Client.Response;
import Model.Wall;
import core.Constant;
import core.Position;
import core.Settings;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Locale;

public class GameFieldController {
    private Snake snake;
    private Apple apple;
    private Client client;
    private Wall wall;
    private boolean isNewRecord = false;
    private int score = 0;
    Response res;
    boolean inGame = false;


    public GameFieldController(Snake snake, Apple apple, Wall wall, Client client) throws IOException {
        this.snake = snake;
        this.apple = apple;
        this.client= client;
        this.wall = wall;
    }

    public void startGame(){
        snake.initSnake();
        score = 0;
        inGame = true;
    }

    public void addScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public void paintGameField(Graphics g) throws IOException, ClassNotFoundException {
        if(inGame){
            if(snake.checkApple(apple.getPositionApple())){
                client.sendRequest(Constant.WIDTH_FIELD, Constant.HEIGHT_FIELD, score);
                res = client.getResponse();
                if (res.getResRecord().equals("Поздравляю! Вы поставили новый рекорд сервера!"))
                    isNewRecord = true;
                addScore();
                snake.addParts();
                apple.createApple(new Position(res.getAppleX(), res.getAppleY()));
            }
            else if (!Settings.isBarrier){
                if(snake.checkCollisionSnake()){
                    snake.moveBarrier();
                }
                else{
                    inGame = false;
                }
            }
            else if(snake.checkCollisionSnake() && snake.checkCollisionWall(wall.getWalls())){
                wall.draw(g);
                snake.move();
            }
            else{
                inGame = false;
            }
            snake.draw(g);
            apple.draw(g);
        }else {
            g.setColor(Color.ORANGE);
            g.fillRect(100, Constant.HEIGHT_FIELD / 2 - 150, Constant.WIDTH_FIELD - 200, 200);
            g.setColor(Color.BLUE);
            g.drawString("ИГРА ЗАКОНЧЕНА! КОЛИЧЕСТВО НАБРАННЫХ ОЧКОВ: " + score,  140 , Constant.HEIGHT_FIELD / 2 - 100);
            if(isNewRecord)
                g.drawString("ПОЗДРАВЛЯЮ! Вы поставили новый рекорд сервера!", 150 , Constant.HEIGHT_FIELD / 2 - 75 );
            else
                g.drawString(res.getResRecord().toUpperCase(Locale.ROOT), Constant.WIDTH_FIELD / 2 - 40 , Constant.HEIGHT_FIELD / 2 - 75 );
            g.setColor(Color.black);
            g.drawString("ДЛЯ ТОГО ЧТОБЫ НАЧАТЬ ИГРУ ЗАНОВО НАЖМИТЕ <ПРОБЕЛ>", 125 , Constant.HEIGHT_FIELD / 2 - 25 );
            g.drawString("ДЛЯ ВЫХОДА К МЕНЮ НАЖМИТЕ <ESC>", 200 , Constant.HEIGHT_FIELD / 2 + 10);
        }
    }

    public FieldKeyListener getKeyAdapter(){
        return new FieldKeyListener();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !(snake.getDirection() == Direction.RIGHT)){
                snake.setDirection(Direction.LEFT);
            }
            if(key == KeyEvent.VK_RIGHT && !(snake.getDirection() == Direction.LEFT)){
                snake.setDirection(Direction.RIGHT);
            }

            if(key == KeyEvent.VK_UP && !(snake.getDirection() == Direction.DOWN)){
                snake.setDirection(Direction.UP);
            }
            if(key == KeyEvent.VK_DOWN && !(snake.getDirection() == Direction.UP)){
                snake.setDirection(Direction.DOWN);
            }
            if(key == KeyEvent.VK_SPACE  && !inGame){
                startGame();
            }
        }
    }
}
