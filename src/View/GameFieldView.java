package View;

import Controller.GameFieldController;
import core.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GameFieldView extends JPanel implements ActionListener{
    Timer timer = new Timer(100, this);
    GameFieldController fc;
    CardLayout cl;
    JPanel panelCont;

    public GameFieldView(GameFieldController fc, CardLayout cl, JPanel panelCont){
        this.addComponentListener( new ComponentAdapter() {
            @Override
            public void componentShown( ComponentEvent e ) {
                GameFieldView.this.requestFocusInWindow();
            }
        });
        this.fc = fc;
        setLocation(500, 500);
        init();
        setFocusable(true);
        System.out.println("Init");
        timer.start();
        this.cl = cl;
        this.panelCont = panelCont;
    }

    public void init(){
        addKeyListener(fc.getKeyAdapter());
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_ESCAPE){
                    cl.first(panelCont);
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        try {
            fc.paintGameField(g);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, getWidth(), 25);
        g.setColor(Color.blue);
        g.drawString("Количество очков "+ fc.getScore(), getWidth() / 2 - 70, 15);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
