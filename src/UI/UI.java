package UI;

import Game.GamePanel;

import java.awt.*;

public class UI {
    GamePanel gp;
    Font font = new Font("Arial", Font.PLAIN, 20);

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(Color.cyan);
        int spacing = 23;
        String[] playerInvetory = gp.player.InventoryToString();
        for(int i = 0; i < playerInvetory.length; i++) {
            g2.drawString(playerInvetory[i], 50, (i + 1) * spacing);
        }
        g2.drawString("Rupee: " + gp.player.wallet, 50, (playerInvetory.length + 2) * spacing);
    }
}
