//Sapozhnikov Arkady
//JumpNBump(Rabbit)
//25.03.18

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Rabbit extends Hitable implements Drawable {
    int x;
    int y;
    int r;
    private double vx = 0.0;
    private double vy = -5.0;
    private static final double G = 1;
    private static final double DV = 10;
    private static final double A = 1.1;
    private static final int YAppear = 20;
    private final int up;
    private final int right;
    private final int left;
    int score = 0;
    private final BufferedImage image;
    private boolean isRightPushed = false;
    private boolean isLeftPushed = false;
    private boolean isDownPushed = false;
    private final Random random = new Random();

    Rabbit(int newX, int newY, int newR, int newUp, int newRight, int newLeft, String newImageName) throws IOException {
        x = newX;
        y = newY;
        r = newR;
        up = newUp;
        right = newRight;
        left = newLeft;
        image = ImageIO.read(new File(newImageName));
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x - r, y - r, 2 * r + x - r, 2 * r + y - r, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    void update() {
        x += (int) (vx);
        y -= (int) (vy);
        Integer[][] integers = new Integer[4][2];
        Integer[][] integers2 = new Integer[2][2];
        System.arraycopy(hitTest(this), 0, integers, 0, 4);
        System.arraycopy(hitTestRabbit(this), 0, integers2, 0, 2);
        if (integers[1][0] == 1) {
            if (!isDownPushed) {
                vy = 0;
                y = Main.level.walls[integers[1][1]].y + Main.level.walls[integers[1][1]].height + this.r;
            }
            isDownPushed = true;
        } else {
            isDownPushed = false;
        }
        vy -= G;
        if (integers[0][0] == 1) {
            vy = 0;
            y = Main.level.walls[integers[0][1]].y - this.r;
        }
        if (integers[2][0] == 1) {
            vx = 0;
            x = Main.level.walls[integers[2][1]].x - this.r;
            isLeftPushed = true;
        }
        if (integers[3][0] == 1) {
            vx = 0;
            x = Main.level.walls[integers[3][1]].x + Main.level.walls[integers[3][1]].width + this.r;
            isRightPushed = true;
        }
        vx *= A;
        if (integers2[0][0] == 1) {
            vx = 0;
            if (integers2[0][1] == 0) {
                isLeftPushed = true;
            } else {
                isRightPushed = true;
            }
        }
        if (integers2[1][0] == 1) {
            vx = 0;
            score += 1;
            Main.level.rabbits[integers2[1][1]].x = random.nextInt(Main.WIDTH - 2 * r) + 2 * r;
            Main.level.rabbits[integers2[1][1]].y = YAppear;
            Main.level.rabbits[integers2[1][1]].vx = 0;
            Main.level.rabbits[integers2[1][1]].vy = 0;
        }
        if (integers[2][0] != 1 && (integers2[0][0] != 1 || integers2[0][1] != 0)) {
            isLeftPushed = false;
        }
        if (integers[3][0] != 1 && (integers2[0][0] != 1 || integers2[0][1] == 0)) {
            isRightPushed = false;
        }
    }

    void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == up) {
            if (Math.abs(vy) <= 1) {
                vy = DV;
            }
        }
        if (e.getKeyCode() == right) {
            if (!isRightPushed) {
                vx = DV;
            }
        }
        if (e.getKeyCode() == left) {
            if (!isLeftPushed) {
                vx = -DV;
            }
        }
    }

    void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == right) {
            vx = 0;
        }
        if (e.getKeyCode() == left) {
            vx = 0;
        }
    }
}
