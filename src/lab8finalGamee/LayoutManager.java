package lab8finalGamee;

import javax.media.opengl.GL;
import static lab8finalGamee.Constants.Layout_difficalties;
import static lab8finalGamee.Constants.Layout_enter_name;
import static lab8finalGamee.Constants.Layout_game;
import static lab8finalGamee.Constants.Layout_high_score;
import static lab8finalGamee.Constants.Layout_how_to_play;
import static lab8finalGamee.Constants.Layout_lose;
import static lab8finalGamee.Constants.Layout_menu;
import static lab8finalGamee.Constants.Layout_paused;
import static lab8finalGamee.Constants.Layout_win;

public class LayoutManager {
  //layout textures variables
    static String current_layout = Layout_menu;
    
    
    static void run(){
        setDinamicLayoutElements(current_layout);
    }
    
    static void setDinamicLayoutElements(String layout) {
        current_layout = layout;
        switch (layout) {
            case Layout_menu: {
                setLayout(0);//static
                
                //set sound button
                Drawing.DrawSprite(-400, -210, GameManager.Button_sound, -65, 65, Constants.textureManager.buttonsTexInt);

            }
            break;
            case Layout_difficalties: {
                setLayout(1);
                Drawing.DrawSprite(-405, 205, Constants.Button_back, 70, 70, Constants.textureManager.buttonsTexInt);
            }
            break;
            case Layout_game: {
                setLayout(2);
                Drawing.DrawSprite(-255, 215, Constants.Button_back, 60, 60, Constants.textureManager.buttonsTexInt);
                Drawing.DrawSprite(-190, 215, GameManager.Button_sound, 60, 60, Constants.textureManager.buttonsTexInt);
                Drawing.DrawSprite(-123, 213, Constants.Button_pause, 59, 71, Constants.textureManager.buttonsTexInt);
                Drawing.writeAtPos(GameManager.player.target + "/" + GameManager.level.winTarget, 50, 455, 30);
                Drawing.writeAtPos(GameManager.Timer+"", 400, 410, 64);
                Drawing.writeAtPos(GameManager.player.score+"", 825, 460, 31);
                
            }
            break;
            case Layout_high_score:
                setLayout(3);
                Drawing.DrawSprite(-405, 205, Constants.Button_back, 70, 70, Constants.textureManager.buttonsTexInt);
                Drawing.writeAtPos(highScore.read()[0], 305, 250, 45);
                Drawing.writeAtPos(highScore.read()[1], 305, 205, 45);
                Drawing.writeAtPos(highScore.read()[2], 305, 160, 45);
                Drawing.writeAtPos(highScore.read()[3], 305, 115, 45);
                Drawing.writeAtPos(highScore.read()[4], 305, 70, 45);
                break;
            case Layout_how_to_play:
                setLayout(4);
                Drawing.DrawSprite(-405, 205, Constants.Button_back, 70, 70, Constants.textureManager.buttonsTexInt);
                break;
            case Layout_lose:
                setLayout(5);
                break;
            case Layout_paused:
                setLayout(6);
                break;
            case Layout_win:
                setLayout(7);
                break;
            case Layout_enter_name:
                setLayout(8);
                Drawing.DrawSprite(-405, 205, Constants.Button_back, 70, 70, Constants.textureManager.buttonsTexInt);
                Drawing.writeAtPos(GameManager.EnterdName, 180, 180, 90);
                //x = -245 y = -45 width = 75 hight = 190
                break;
        }
    }
    static void setLayout(int index) {

        Constants.gl.glBindTexture(GL.GL_TEXTURE_2D, Constants.textureManager.layoutTexInt[index]);	// Turn Blending On

        Constants.gl.glPushMatrix();
        Constants.gl.glScaled(Constants.maxWidth / 2, Constants.maxHeight / 2, 1);
        //System.out.println(x +" " + y);
        Constants.gl.glBegin(GL.GL_QUADS);
        // Front Face
        Constants.gl.glTexCoord2f(0.0f, 0.0f);
        Constants.gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        Constants.gl.glTexCoord2f(1.0f, 0.0f);
        Constants.gl.glVertex3f(1.0f, -1.0f, -1.0f);
        Constants.gl.glTexCoord2f(1.0f, 1.0f);
        Constants.gl.glVertex3f(1.0f, 1.0f, -1.0f);
        Constants.gl.glTexCoord2f(0.0f, 1.0f);
        Constants.gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        Constants.gl.glEnd();
        Constants.gl.glPopMatrix();

        Constants.gl.glDisable(GL.GL_BLEND);
    }
}