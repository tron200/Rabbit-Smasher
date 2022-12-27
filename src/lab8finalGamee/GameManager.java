package lab8finalGamee;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import static lab8finalGamee.AnimGLEventListener3.clip;
import static lab8finalGamee.AnimGLEventListener3.clip2;

public class GameManager {

    //variables
    static int Button_sound = 2;
    static boolean pause = false;
    static String EnterdName = "";
    static boolean ready = false;
    static ArrayList<RabbitHole> DefaultRh = new ArrayList<>();
    static ArrayList<RabbitHole> rh = new ArrayList<>();
    static ArrayList<Integer> tempArr = new ArrayList<>();
    static ArrayList<Integer> ActiveRbbits = new ArrayList<>();
    static Player player;
    static Level level;
    static boolean scoreSaved = false;
    static int Timer;
    static int timerTemp = 0;

    static int destroyTimerTemp = 100;
    static boolean win;
    static boolean lose;
    static boolean gameover = false;

    static void run() {
        if (LayoutManager.current_layout.equals(Constants.Layout_game)) {
            if (level != null && !isReady()) {
                setup();
            } else {
                for (int i = 0; i < rh.size(); i++) {
                    Drawing.DrawSprite(GameManager.rh.get(i).x, GameManager.rh.get(i).y, GameManager.rh.get(i).currentAnimationIndex, GameManager.rh.get(i).width, GameManager.rh.get(i).hight, Constants.textureManager.holesWithRabitsTexInt);
                    //respawn rabbits and countDownNumbers
                }
                if (pause) {
                    if (!win && !lose) {
                        showPause();
                    } else if (lose) {
                        Drawing.DrawSprite(0, 0, 5, Constants.maxWidth, Constants.maxHeight, TextureManager.layoutTexInt);
                        Drawing.writeAtPos(player.score + "", 360, 200, 150);
                    }

                } else {

                    setTimer();
                    RespawnRandomRabbit();
                    moveRabbits();
                    destroyTimerTemp--;
                    if (destroyTimerTemp <= 0) {
                        for (int i = 0; i < rh.size(); i++) {
                            if (!ActiveRbbits.contains(i)) {
                                rh.get(i).currentAnimationIndex = 0;
                            }
                        }
                        destroyTimerTemp = 100;
                    }
                }

            }
            checkLose();
            checkWin();
            checkGameOver();
        }
    }

    static void setup() {
        setDegfaultHoles();
        Timer = level.levelTime;
        timerTemp = Timer * 1000;
        //add number of holes in random pos
        for (int i = 0; i < level.currentHolesNumber; i++) {
            int temp = (int) (Math.random() * 12);
            while (tempArr.contains(temp)) {
                temp = (int) (Math.random() * 12);
            }
            tempArr.add(temp);
            rh.add(DefaultRh.get(temp));

        }
        resume();
        ready = true;
    }

    private static void setDegfaultHoles() {
        DefaultRh.add(new RabbitHole(-235, 65, 140, 95, 0, level.rabbitAppearTime));
        //        x = -235 y = 65 width = 140 hight = 95
        //
        DefaultRh.add(new RabbitHole(15, 65, 140, 95, 0, level.rabbitAppearTime));
        //x = 15 y = 65 width = 140 hight = 95
        DefaultRh.add(new RabbitHole(270, 65, 140, 95, 0, level.rabbitAppearTime));

        //x = 270 y = 65 width = 140 hight = 95
        DefaultRh.add(new RabbitHole(155, -15, 140, 95, 0, level.rabbitAppearTime));
        //x = 155 y = -15 width = 140 hight = 95

        DefaultRh.add(new RabbitHole(-120, -15, 140, 95, 0, level.rabbitAppearTime));
        //x = -120 y = -15 width = 140 hight = 95

        DefaultRh.add(new RabbitHole(-365, -10, 140, 95, 0, level.rabbitAppearTime));
        //x = -365 y = -10 width = 140 hight = 95

        DefaultRh.add(new RabbitHole(-235, -95, 140, 95, 0, level.rabbitAppearTime));
        //x = -235 y = -95 width = 140 hight = 95

        DefaultRh.add(new RabbitHole(30, -95, 140, 95, 0, level.rabbitAppearTime));
        //x = 30 y = -95 width = 140 hight = 95

        DefaultRh.add(new RabbitHole(310, -95, 140, 95, 0, level.rabbitAppearTime));
        //x = 310 y = -95 width = 140 hight = 95

        DefaultRh.add(new RabbitHole(185, -170, 140, 95, 0, level.rabbitAppearTime));
        //x = 185 y = -170 width = 140 hight = 95

        DefaultRh.add(new RabbitHole(-115, -175, 140, 95, 0, level.rabbitAppearTime));
        //x = -115 y = -175 width = 140 hight = 95

        DefaultRh.add(new RabbitHole(-370, -185, 140, 95, 0, level.rabbitAppearTime));
        //x = -370 y = -185 width = 140 hight = 95
    }

    private static void setTimer() {
        timerTemp -= 20;
        if (timerTemp % 1000 == 0 && timerTemp >= 0) {
            Timer = timerTemp / 1000;
        }

    }

    static void RespawnRandomRabbit() {
        int temp = (int) (Math.random() * 2);
        if (ActiveRbbits.size() + rh.size() - temp < rh.size()) {
            int rand = (int) (Math.random() * rh.size());
            while (ActiveRbbits.contains(rand)) {
                rand = (int) (Math.random() * rh.size());
            }
            ActiveRbbits.add(rand);
            rh.get(rand).currentAnimationIndex = 0;
        }
    }

    static void moveRabbits() {
        for (int i = 0; i < ActiveRbbits.size(); i++) {
            if (rh.get(ActiveRbbits.get(i)).timeOfshow > 0.1) {
                rh.get(ActiveRbbits.get(i)).show();
            } else if (rh.get(ActiveRbbits.get(i)).timeOfshow <= 0) {
                rh.get(ActiveRbbits.get(i)).timeOfshow = level.rabbitAppearTime;
                ActiveRbbits.remove(i);
            } else {

                rh.get(ActiveRbbits.get(i)).disappear();

            }
        }
    }

    static void win() {
        pause = true;
        win = true;
        if (level.currentHolesNumber < level.maxHolesNumber) {
            Drawing.DrawSprite(0, 0, 7, Constants.maxWidth, Constants.maxHeight, TextureManager.layoutTexInt);
        } else {
            gameover = true;
            // win or high score layout
            Drawing.DrawSprite(0, 0, 9, Constants.maxWidth, Constants.maxHeight, TextureManager.layoutTexInt);
        }
        Drawing.writeAtPos(player.score + "", 360, 200, 150);
    }

    static void next() {
        if (gameover != true) {
            level.currentHolesNumber++;
            player.target = 0;
            win = false;
            reset();
            setup();
            resume();
        }

    }

    static void lose() {
        pause = true;
        win = false;
        lose = true;

        gameover = true;
        // win or high score layout

    }

    static void pause() {
        pause = true;

    }

    static void resume() {
        pause = false;
    }

    void mute() {

    }

    static void reset() {
        rh = new ArrayList<>();
        DefaultRh = new ArrayList<>();
        tempArr = new ArrayList<>();
        ActiveRbbits = new ArrayList<>();
        ready = false;
        Timer = level.levelTime;
        timerTemp = Timer * 1000;

    }

    static void switchMuteButton() {
        if (Button_sound == 1) {
            Button_sound = 2;
        } else {
            Button_sound = 1;
        }
    }

    static void changeLevel(int diff) {
        level = new Level(diff);
        reset();
        setup();
    }

    static void Hit(int x, int y) {
        // 140, 95
        boolean hit = false;
        for (int i = 0; i < ActiveRbbits.size(); i++) {
            int rabbitX = rh.get(ActiveRbbits.get(i)).x;
            int rabbitY = rh.get(ActiveRbbits.get(i)).y;
            if (x < rabbitX + 70 && x > rabbitX - 70
                    && y < rabbitY + 47.5 && y > rabbitY - 47.5) {
                //hit
                rh.get(ActiveRbbits.get(i)).destroyRabbit(level.rabbitAppearTime);
                player.score += level.rabbitCost;
                player.target++;
                ActiveRbbits.remove(i);
                hit = true;
                //play sound hit 
            }

        }
        try {
            if (hit) {
                AudioInputStream audiostream = AudioSystem.getAudioInputStream(new File("C://Users//user//Documents//NetBeansProjects//ComputerGraphics//soundtracks//bonk.wav"));
                clip2 = AudioSystem.getClip();
                clip2.open(audiostream);
                clip2.start();
            } else {
                AudioInputStream audiostream = AudioSystem.getAudioInputStream(new File("C://Users//user//Documents//NetBeansProjects//ComputerGraphics//soundtracks//miss.wav"));
                clip2 = AudioSystem.getClip();
                clip2.open(audiostream);
                clip2.start();
            }
        } catch (Exception eF) {

        }
    }

    //getters
    static boolean isPause() {
        return pause;
    }

    static boolean isReady() {
        return ready;
    }

    static void checkWin() {
        if (player.target == level.winTarget && Timer > 0) {
            win();
        }
    }

    static void checkLose() {
        if (Timer <= 0) {
            lose();
        }
    }

    static void showPause() {
        Drawing.DrawSprite(0, 0, 6, Constants.maxWidth, Constants.maxHeight, TextureManager.layoutTexInt);
    }

    static void checkGameOver() {
        if (gameover == true) {
            //if highScore then go to highScore after saved it
            if (!scoreSaved) {
                scoreSaved = true;
                highScore.isNewHighScore(player.name, player.score);
            }
            if (lose) {

            }
        }
    }

    static void hardReset() {
        reset();
        EnterdName = "";
        player.score = 0;
        player.target = 0;
        level.currentHolesNumber = level.minHolesNumber;
        scoreSaved = false;
        destroyTimerTemp = 100;
        win = false;
        gameover = false;
        lose = false;

    }
}
