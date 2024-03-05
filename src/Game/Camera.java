package Game;

import Entity.Player;

public class Camera {
    int cameraX, cameraY;
    int cameraFovWidth, cameraFovHeight;
    boolean followPlayerModeXAxis, followPlayerModeYAxis;

    GamePanel gp;
    Player player;

    public Camera(GamePanel gp) {
        this.cameraX = gp.player.screenX;
        this.cameraY = gp.player.screenY;

        this.cameraFovWidth = gp.screenWidth;
        this.cameraFovHeight = gp.screenHeight;

        this.gp = gp;
        this.player = gp.player;
    }

    public void checkFollowPlayerOnXAxis() {
        if(player.getCurrentMap().getMapLength() * gp.tileSize - player.worldX > 7 * 48 || player.worldX > 0) {
            this.followPlayerModeXAxis = false;
        }
    }

    public void checkFollowPlayerOnYAxis() {

    }

    public void update() {
        checkFollowPlayerOnXAxis();
        checkFollowPlayerOnYAxis();
    }
}
