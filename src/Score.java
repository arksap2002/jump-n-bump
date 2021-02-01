//Sapozhnikov Arkady
//JumpNBump(Score)
//25.03.18

import java.awt.*;

class Score implements Drawable {
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        for (Rabbit rabbit : Main.level.rabbits) {
            g2d.drawString(Integer.toString(rabbit.score), rabbit.x, rabbit.y - 2 * rabbit.r);
        }
    }
}
