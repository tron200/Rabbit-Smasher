package lab8finalGamee;

import Textures.TextureReader;
import java.io.IOException;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;


public class TextureManager {
    static String layouts[] = {"//rabbits smasher//layouts//menu.png","//rabbits smasher//layouts//difficalties.png",
        "//rabbits smasher//layouts//game.png","//rabbits smasher//layouts//HighScore.png",
        "//rabbits smasher//layouts//HowToPlay.png","//rabbits smasher//layouts//lose.png",
        "//rabbits smasher//layouts//paused.png","//rabbits smasher//layouts//win.png",
        "//rabbits smasher//layouts//enterName.png","//rabbits smasher//layouts//gameOverWin.png"};
    
    TextureReader.Texture layoutsTex[] = new TextureReader.Texture[layouts.length];
    static int[] layoutTexInt = new int[layouts.length];
    
    String buttons[] = {"//rabbits smasher//buttons//back.png","//rabbits smasher//buttons//mute.png","//rabbits smasher//buttons//sound.png","//rabbits smasher//buttons//pauseButton.png"};
    TextureReader.Texture buttonsTex[] = new TextureReader.Texture[buttons.length];
    int buttonsTexInt[] = new int[buttons.length];
    
    static String holesWithRabits[] = {"//rabbits smasher//holesOfRabbits//1.png","//rabbits smasher//holesOfRabbits//2.png"
    ,"//rabbits smasher//holesOfRabbits//3.png","//rabbits smasher//holesOfRabbits//4.png","//rabbits smasher//holesOfRabbits//5.png"};
    TextureReader.Texture holesWithRabitsTex[] = new TextureReader.Texture[holesWithRabits.length];
    
    static int holesWithRabitsTexInt[] = new int[holesWithRabits.length];
    
    static String hammer[] = {"//rabbits smasher//hammer//1.png","//rabbits smasher//hammer//2.png","//rabbits smasher//hammer//3.png","//rabbits smasher//hammer//4.png"
    ,"//rabbits smasher//hammer//5.png","//rabbits smasher//hammer//dot.png"};
    TextureReader.Texture hammerTex[] = new TextureReader.Texture[hammer.length];
    
    static int hammerTexInt[] = new int[hammer.length];

    public TextureManager(GL gl) {
        setUpTextures(gl, layouts, layoutsTex, layoutTexInt);
        setUpTextures(gl, buttons, buttonsTex, buttonsTexInt);
        setUpTextures(gl, holesWithRabits, holesWithRabitsTex, holesWithRabitsTexInt);
        setUpTextures(gl, hammer, hammerTex, hammerTexInt);
    }
    
    
    
    static void setUpTextures(GL gl,String [] layouts, TextureReader.Texture [] layoutsTex, int [] layoutTexInt){
        
        //gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(layouts.length, layoutTexInt, 0);

        for (int i = 0; i < layouts.length; i++) {
            try {
                layoutsTex[i] = TextureReader.readTexture("Assets" + layouts[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, layoutTexInt[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        layoutsTex[i].getWidth(), layoutsTex[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        layoutsTex[i].getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }
    
    
}
