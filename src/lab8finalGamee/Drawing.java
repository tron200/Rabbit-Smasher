package lab8finalGamee;

import com.sun.opengl.util.j2d.TextRenderer;
import java.awt.Font;
import javax.media.opengl.GL;
import static lab8finalGamee.Constants.renderer;


public class Drawing {
    
    static void DrawSprite(int x, int y, int index, float width, float height, int[] arrayOfTextures) {
        Constants.gl.glEnable(GL.GL_BLEND);

        Constants.gl.glBindTexture(GL.GL_TEXTURE_2D, arrayOfTextures[index]);	// Turn Blending On

        Constants.gl.glPushMatrix();
        Constants.gl.glTranslated(x, y, 0);
        Constants.gl.glScaled(width / 2, height / 2, 1);
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
    static void writeAtPos(String text, int x, int y, int fontSize) {

        try {
            Constants.renderer = new TextRenderer(new Font("Blomberg", Font.BOLD, fontSize));
            renderer.beginRendering(Constants.maxWidth, Constants.maxHeight);
            // optionally set the color
            renderer.draw(text, x, y);
            renderer.endRendering();
        } catch (Exception e) {

        }
    }
}
