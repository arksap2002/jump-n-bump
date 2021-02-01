//Sapozhnikov Arkady
//JumpNBump(Wall)
//25.03.18
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Wall extends Hitable implements Drawable{
    int x;
    int y;
    int width;
    int height;
    private BufferedImage image;
    Wall(int newX, int newY, int newWidth, int newHeight, String newImageName) throws IOException {
        x = newX;
        y = newY;
        width = newWidth;
        height = newHeight;
        image = ImageIO.read(new File(newImageName));
    }
    public void draw(Graphics2D g2d){
        g2d.drawImage(image, x, y, x + width, y + height, x, y, x + width, y + height, null);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);
    }
}
