package View;

import core.Constant;

import javax.swing.*;

public class MainView extends JFrame{
    public MainView() {
        setTitle("Змейка");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constant.WIDTH_FIELD,Constant.HEIGHT_FIELD);
        setLocation(400,400);
        setResizable(false);
        setVisible(true);
    }
}
