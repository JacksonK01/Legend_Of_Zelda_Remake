package Entity.Animation;

import Entity.Player;

import java.awt.image.BufferedImage;

public class PlayerAnimations {
    private BufferedImage[] walkCycleAnimation;
    Player player;

    public PlayerAnimations(Player player) {
        this.player = player;
    }
}
