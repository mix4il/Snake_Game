import Client.Client;
import Controller.GameFieldController;
import Model.Apple;
import Model.Snake;
import Model.Wall;
import View.GameFieldView;
import View.MainView;
import View.MenuView;
import core.Constant;
import core.Position;
import core.Settings;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Main {

    public Main() throws IOException {
        Snake snake = new Snake();
        Apple apple = new Apple(new Position(300,100));
        Client client = new Client();
        Wall wall = new Wall();
        GameFieldController fc = new GameFieldController(snake, apple, wall, client);


        MainView mw = new MainView();
        CardLayout cl = new CardLayout();

        JPanel panelCont = new JPanel();
        GameFieldView gameFieldView = new GameFieldView(fc, cl, panelCont);

        MenuView menuView = new MenuView();
        Font fbMenu = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
        JButton start = new JButton("НАЧАТЬ ИГРУ");
        start.setFont(fbMenu);
        menuView.setLayout(null);
        //start.setBackground(Color.ORANGE);
        int widthStart = 300;
        start.setBounds(Constant.WIDTH_FIELD / 2 - widthStart / 2, Constant.HEIGHT_FIELD / 2 - 200, widthStart, 150);
        menuView.add(start);
        JLabel settings = new JLabel("Параметры игры");
        settings.setFont(fbMenu);
        settings.setBounds(Constant.WIDTH_FIELD / 2 - 75 , Constant.HEIGHT_FIELD / 2  - 20, 200 , 30);
        menuView.add(settings);


        Font fbMenuMode = new Font(Font.SANS_SERIF,Font.PLAIN, 15);

        JLabel gameMode = new JLabel("Режим игры");
        gameMode.setFont(fbMenuMode);
        gameMode.setBounds(Constant.WIDTH_FIELD / 2 - settings.getWidth() / 2 - 100, Constant.HEIGHT_FIELD / 2  + 50, 200 , 20);
        menuView.add(gameMode);

        JRadioButton isWallRadio = new JRadioButton("С орграничениями ");
        isWallRadio.setBounds(Constant.WIDTH_FIELD / 2 - settings.getWidth() / 2 - 100, Constant.HEIGHT_FIELD / 2  + 70, 200 , 50);
        menuView.add(isWallRadio);

        JRadioButton boundless = new JRadioButton("Без ограничений");
        boundless.setBounds(Constant.WIDTH_FIELD / 2 - settings.getWidth() / 2 + 150, Constant.HEIGHT_FIELD / 2  + 70, 150 , 50);
        menuView.add(boundless);

        ButtonGroup gameModeGroup = new ButtonGroup();
        gameModeGroup.add(isWallRadio);
        gameModeGroup.add(boundless);
        isWallRadio.setSelected(true);

        panelCont.setLayout(cl);
        panelCont.add(menuView, "1");
        panelCont.add(gameFieldView, "2");

        cl.show(panelCont, "1");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont,"2");
                fc.startGame();
                Settings.isBarrier = isWallRadio.isSelected();
            }
        });
        mw.add(panelCont);
    }

    public static void main(String[] args)  {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Main();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

