import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Random;

public class Road extends JPanel implements ActionListener ,Runnable{
    Timer mainTimer = new Timer(20,this);

//Image img = new ImageIcon("C:\\Users\\User\\IdeaProjects\\koxanf1\\src\\main\\resources\\road.jpg").getImage();
Image img = new ImageIcon(getClass().getClassLoader().getResource("road.jpg")).getImage();
Player p = new Player();
Thread enemiesFactory = new Thread(this);

List<Enemy> enemies = new ArrayList<Enemy>();

public Road(){
    mainTimer.start();
    enemiesFactory.start();
    addKeyListener(new MyKeyAdapter());
    setFocusable(true);
}

    public void run() {
        while(true){
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1200,rand.nextInt(600),rand.nextInt(50),this));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyKeyAdapter extends KeyAdapter{
    public void keyPressed(KeyEvent e){
        p.keyPressed(e);
    }
    public void keyReleased(KeyEvent e){
        p.keyReleased(e);
    }
}

public void paint(Graphics g){
    g = (Graphics2D) g;
    g.drawImage(img,p.layer1,0,null);
    g.drawImage(img,p.layer2,0,null);
    g.drawImage(p.img,p.x,p.y,null);

    double v = (200/Player.MAX_V)*p.v;
    g.setColor(Color.WHITE);
    Font font = new Font("Arial",Font.BOLD,20);
    g.setFont(font);
    g.drawString("Speed: "+ v + " км/ч",100,30);

    Iterator<Enemy> i = enemies.iterator();
    while(i.hasNext()){
        Enemy e = i.next();
        if(e.x >=2400 || e.x <= -0){
            i.remove();
        }else {
            e.move();
            g.drawImage(e.img,e.x,e.y,null);
        }

    }
}

    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        testColissionWithEnemies();
        testWin();

    }

    private void testWin(){
    if(p.s > 200000){
        JOptionPane.showMessageDialog(null,"Вы выиграли! Канграчюлечис");
        System.exit(0);
    }
    }

    private void testColissionWithEnemies(){
    Iterator <Enemy> i = enemies.iterator();
    while (i.hasNext()){
        Enemy e = i.next();
        if(p.getRect().intersects(e.getRect())){
            JOptionPane.showMessageDialog(null,"exit");
            System.exit(1);
        }
    }
    }
}
