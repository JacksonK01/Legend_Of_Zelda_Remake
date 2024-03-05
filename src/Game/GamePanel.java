package Game;

import Entity.Player;
import Input.KeyHandler;
import Item.AbstractItem;
import Item.ItemSetter;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    //This is just to get width and height
    JFrame window;

    final int originalTileSize = 16; //16x16 tile
    public final int scale = 3;

    public final int tileSize = originalTileSize*scale; //48x48 tile
    //4 x 3 ratio
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels
    //FPS
    final int FPS = 60;

    Thread gameThread;

    KeyHandler keyH = new KeyHandler();
    TileManager tileManager = new TileManager(this);
//    Camera camera = new Camera(this);
    public Player player = new Player(this, keyH);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public ArrayList<AbstractItem> itemsList = new ArrayList<>();
    public ItemSetter itemSetter = new ItemSetter(this);


    public GamePanel(JFrame window) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyH);
        this.setFocusable(true);

        this.window = window;
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        long drawInterval = 1000000000/FPS; //Draws the screen every 0.0166666 seconds aka 1/60
        long nextDrawTime = System.nanoTime() + drawInterval; //Calculates the allowed amount of time the thread has until it runs again

        while(this.gameThread != null) {
            long currentTime = System.nanoTime();
            if (System.nanoTime() - currentTime < FPS ) {
                update();
                repaint();


                try {
                    long remaningTime = nextDrawTime - System.nanoTime();
                    remaningTime = remaningTime/1000000; //convert to miliseconds

                    if (remaningTime < 0) {
                        remaningTime = 0;
                    }

                    Thread.sleep(remaningTime);

                    nextDrawTime += drawInterval;
                } catch (InterruptedException ignored) {
                    System.out.println("Thread skipped");
                }
            }
        }
    }
    public void update() {
      player.update();
//      camera.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);

        itemsList.forEach((AbstractItem item) -> {
            if(item != null) {
                item.draw(g2, this);
            }
        });

        player.draw(g2);

        g2.dispose();
    }

    public int getTileSize() {
        return tileSize;
    }
}
