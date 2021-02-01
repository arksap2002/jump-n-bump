//Sapozhnikov Arkady
//JumpNBump(Hitable)
//25.03.18
class Hitable {
    // 1 -> Yes
    // 0 -> No
    Integer[][] hitTest(Rabbit rabbit) {
        // integers[0] - down touch
        // integers[1] - up touch
        // integers[2] - left touch
        // integers[3] - rigth touch
        Integer[][] integers = new Integer[4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                integers[i][j] = 0;
            }
        }
        int i = 0;
        for (Wall wall : Main.level.walls) {
            if ((wall.y <= rabbit.y + rabbit.r) && (wall.y >= rabbit.y - wall.height / 3) && (wall.x <= rabbit.x + 3 * rabbit.r / 4) && (wall.x + wall.width >= rabbit.x - 3 * rabbit.r / 4)) {
                integers[0][0] = 1;
                integers[0][1] = i;
            }
            if ((wall.y + wall.height <= rabbit.y - rabbit.r) && (wall.y + wall.height >= rabbit.y - wall.height / 3) && (wall.x <= rabbit.x + 3 * rabbit.r / 4) && (wall.x + wall.width >= rabbit.x - 3 * rabbit.r / 4)) {
                integers[1][0] = 1;
                integers[1][1] = i;
            }
            if ((wall.y <= rabbit.y) && (wall.y + wall.height >= rabbit.y) && (wall.x >= rabbit.x) && (wall.x <= rabbit.x + rabbit.r)) {
                integers[2][0] = 1;
                integers[2][1] = i;
            }
            if ((wall.y <= rabbit.y) && (wall.y + wall.height >= rabbit.y) && (wall.x + wall.width <= rabbit.x) && (wall.x + wall.width >= rabbit.x - rabbit.r)) {
                integers[3][0] = 1;
                integers[3][1] = i;
            }
            i++;
        }
        return integers;
    }

    Integer[][] hitTestRabbit(Rabbit rabbit) {
        // integers[0] - x touch
        Integer[][] integers = new Integer[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                integers[i][j] = 0;
            }
        }
        int i = 0;
        // 0 - left
        // 1- right
        for (Rabbit rabbit1 : Main.level.rabbits) {
            if (rabbit1 != this) {
                if ((Math.abs(rabbit.x - rabbit1.x) <= rabbit.r + rabbit1.r) && (Math.max(rabbit.r, rabbit1.r) >= Math.abs(rabbit.y - rabbit1.y))) {
                    integers[0][0] = 1;
                    if (rabbit.x > rabbit1.x) {
                        integers[0][1] = 0;
                    } else {
                        integers[0][1] = 1;
                    }
                }
                if ((Math.abs(rabbit.x - rabbit1.x) <= rabbit.r + rabbit1.r) && (Math.max(rabbit.r, rabbit1.r) <= Math.abs(rabbit.y - rabbit1.y)) && (Math.abs(rabbit.y - rabbit1.y) <= rabbit.r + rabbit1.r)) {
                    integers[1][0] = 1;
                    integers[1][1] = i;
                }
            }
            i++;
        }
        return integers;
    }
}
