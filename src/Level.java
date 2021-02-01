//Sapozhnikov Arkady
//JumpNBump(Level)
//25.03.18

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Level implements Drawable {
    //    Hitable[] hittables;
    Rabbit[] rabbits;
    Wall[] walls;
    private final Drawable[] drawables;
    String BACKGROUNDIMAGE;

    Level(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        String SOUNDNAME = scanner.nextLine();
        Sound.playSound(SOUNDNAME);
        BACKGROUNDIMAGE = scanner.nextLine();
        String WALLIMAGE = scanner.nextLine();
        int N = scanner.nextInt();
        int M = scanner.nextInt();
//        hittables = new Hitable[N + M];
        walls = new Wall[N];
        for (int i = 0; i < N; i++) {
            int X = scanner.nextInt();
            int Y = scanner.nextInt();
            int WIDTH = scanner.nextInt();
            int HEIGHT = scanner.nextInt();
            walls[i] = new Wall(X, Y, WIDTH, HEIGHT, WALLIMAGE);
//            hittables[i] = new Hitable(X, Y, WIDTH, HEIGHT);
        }
        rabbits = new Rabbit[M];
        drawables = new Drawable[N + M];
        System.arraycopy(walls, 0, drawables, 0, N);
        for (int i = 0; i < M; i++) {
            int X = scanner.nextInt();
            int Y = scanner.nextInt();
            int R = scanner.nextInt();
            int UP = scanner.nextInt();
            int RIGHT = scanner.nextInt();
            int LEFT = scanner.nextInt();
            String IMAGENAME = scanner.nextLine();
            IMAGENAME = IMAGENAME.substring(1);
            rabbits[i] = new Rabbit(X, Y, R, UP, RIGHT, LEFT, IMAGENAME);
            drawables[N + i] = rabbits[i];
//            hittables[i + N] = new Hitable(X, Y, 2 * R, 2 * R);
        }
    }

    void update() {
        for (Rabbit rabbit : rabbits) {
            rabbit.update();
        }
    }

    public void draw(Graphics2D g2d) throws IOException {
        for (Drawable drawable : drawables) {
            drawable.draw(g2d);
        }
    }
}
