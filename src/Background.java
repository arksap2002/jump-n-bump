//Sapozhnikov Arkady
//JumpNBump(Background)
//25.03.18

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Background implements Drawable {
    @Override
    public void draw(Graphics2D g2d) throws IOException {
        BufferedImage image = ImageIO.read(new File(Main.level.BACKGROUNDIMAGE));
        g2d.drawImage(image, 0, 0, Main.WIDTH, Main.HEIGHT, 0, 0, image.getWidth(), image.getHeight(), null);
    }
}
