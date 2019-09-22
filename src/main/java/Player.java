import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;

public class Player {

    public static final int MAX_V = 50;
    public static final int MAX_TOP = 20;
    public static final int MAX_BOTTOM = 500;

    Image img_c = new ImageIcon(getClass().getClassLoader().getResource("car.png")).getImage();
    Image img_left = new ImageIcon(getClass().getClassLoader().getResource("car-up.png")).getImage();
    Image img_right = new ImageIcon(getClass().getClassLoader().getResource("car-down.png")).getImage();
    Image img = img_c;


    public Rectangle getRect(){
        return new Rectangle(x,y,100,50);
    }

    int v = 0;
    int dv = 0;
    int s = 0;
    int x= 10;
    int y = 300;
    int dy = 0;

    int layer1 = 0;
    int layer2 = 1200;



    public void move(){
        s += v;
        v += dv;
        if(v <=0){
            v = 0;
        }
        if(v>=MAX_V){
            v = MAX_V;
        }
        if(y <= MAX_TOP){
            y = MAX_TOP;
        }
        if(y>=MAX_BOTTOM){
            y = MAX_BOTTOM;
        }
        y -= dy;
        if(layer2 - v <= 0){
            layer1 = 0;
            layer2= 1200;
        }else{
            layer1-= v;
            layer2-= v;
        }


    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key ==KeyEvent.VK_RIGHT){
            dv = 5;
        }
        if (key ==KeyEvent.VK_LEFT){
            dv = -5;
        }
        if(key == KeyEvent.VK_UP){
         dy = 5;
         img = img_left;
        }
        if(key == KeyEvent.VK_DOWN){
            dy = -5;
            img = img_right;
        }

    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT ||key == KeyEvent.VK_LEFT){
            dv = 0;
        }
        if(key == KeyEvent.VK_UP ||key == KeyEvent.VK_DOWN){
            dy = 0;
            img = img_c;
        }

    }
}
