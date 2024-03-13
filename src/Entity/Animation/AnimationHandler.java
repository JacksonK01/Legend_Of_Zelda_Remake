package Entity.Animation;

import java.awt.*;

public interface AnimationHandler {
    void playAnimation(Graphics2D g2);

    //Might be useful to have this apart of the interface
    void setPosAndPlayAnimation(Graphics2D g2, int x, int y);
}
