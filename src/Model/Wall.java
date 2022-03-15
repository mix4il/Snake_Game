package Model;

import core.Constant;
import core.Position;

import java.awt.*;
import java.util.ArrayList;

public class Wall {
    private ArrayList<Position> walls = new ArrayList<>();
    int weight_cell = Constant.WIDTH_FIELD  / Constant.DOT_SIZE;
    int height_cell = Constant.HEIGHT_FIELD / Constant.DOT_SIZE;

    public Wall(){
        for(int j = 0; j < weight_cell; j++){
            walls.add(new Position(j * Constant.DOT_SIZE, Constant.DOT_SIZE));
            walls.add(new Position(0, (j + 1) * Constant.DOT_SIZE));
            walls.add(new Position(j * Constant.DOT_SIZE, (height_cell - 2) * Constant.DOT_SIZE));
            walls.add(new Position(j * Constant.DOT_SIZE, (height_cell - 1) * Constant.DOT_SIZE));
            walls.add(new Position((weight_cell - 1) * Constant.DOT_SIZE, (j + 1) * Constant.DOT_SIZE));
        }
    }

    public void draw(Graphics g){
        for(Position pos : walls){
            g.fillRect(pos.getX(), pos.getY(),Constant.DOT_SIZE, Constant.DOT_SIZE );
        }
    }

    public ArrayList<Position> getWalls() {
        return walls;
    }
}
