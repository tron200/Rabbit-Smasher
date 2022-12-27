package lab8finalGamee;

import com.sun.opengl.util.j2d.TextRenderer;
import javax.media.opengl.GL;

public class Constants {
    final static int maxWidth = 900;
    final static int maxHeight = 500;
    
    static GL gl;
    static TextureManager textureManager;
    static TextRenderer renderer;
    
    //layouts
    final static String Layout_menu = "menuLayout";
    final static String Layout_difficalties = "difficalties";
    final static String Layout_game = "game";
    final static String Layout_high_score = "HighScore";
    final static String Layout_how_to_play = "HowToPlay";
    final static String Layout_lose = "lose";
    final static String Layout_paused = "paused";
    final static String Layout_win = "win";
    final static String Layout_enter_name = "enterName";
    final static String Layout_gameOverWin = "gameOverWin";
    //buttons
    final static int Button_back = 0;
    final static int Button_pause = 3;
}
