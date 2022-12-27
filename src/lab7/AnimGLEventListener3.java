package lab7;

import lab6.*;
import Textures.AnimListener;
import Textures.TextureReader;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.opengl.*;
import java.util.BitSet;
import javax.media.opengl.glu.GLU;

public class AnimGLEventListener3 implements GLEventListener,MouseListener{

    int mouseX = 0 , MouseY = 0;
    int maxWidth = 100;
    int maxHeight = 100;
    int arr[] = {1,1,1};

    // Download enemy textures from https://craftpix.net/freebies/free-monster-2d-game-items/
    String textureNames[] = {"Back.png", "B1.png","bullet.png","Balloon1.png","1.png"};

    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];
    //////////////////////////////////////////////////////
    ArrayList<ob> bullets = new ArrayList<>();
    
    ob player = new Player();
    
    String[][] anim = {
            {"Man1.png", "Man2.png", "Man3.png", "Man4.png"}, //up 0
            {"Man1_down.png", "Man2_down.png", "Man3_down.png", "Man4_down.png"}, // down 1
            {"Man1_right.png", "Man2_right.png", "Man3_right.png", "Man4_right.png"}, // right 2
            {"Man1_left.png", "Man2_left.png", "Man3_left.png", "Man4_left.png"}, // left 3
            {"Man1_topright.png", "Man2_topright.png", "Man3_topright.png", "Man4_topright.png"}, // up right 4
            {"Man1_topleft.png", "Man2_topleft.png", "Man3_topleft.png", "Man4_topleft.png"},// up left 5
            {"Man1_downright.png", "Man2_downright.png", "Man3_downright.png", "Man4_downright.png"},// down right 6
            {"Man1_downleft.png", "Man2_downleft.png", "Man3_downleft.png", "Man4_downleft.png"}};// down left 7

    TextureReader.Texture[][] animations = new TextureReader.Texture[8][4];
    int[][] animint = new int[8][4];

    /*
     5 means gun in array pos
     x and y coordinate for gun 
     */
    public void init(GLAutoDrawable gld) {
        
        
        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);
        for (int i = 0; i < animint.length; i++) {
            gl.glGenTextures(anim[i].length, animint[i], 0);
        }

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture("Assets" + "//man//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }

        

    }

    public void display(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();
        DrawBackGround(gl, 0);
        DrawSprite(gl, 25, 50, arr[0], 2.0f);
        DrawSprite(gl, 50, 50, arr[1], 2.0f);
        DrawSprite(gl, 75, 50, arr[2], 2.0f);
        //handleKeyPress();
//        DrawGraph(gl);

        

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void DrawSprite(GL gl, int x, int y, int index, float scale) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (maxWidth / 2.0) - 1.0, y / (maxHeight / 2.0) - 1.0, 0.8);
        gl.glScaled(0.1 * scale, 0.1 * scale, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    //draw sprite
    public void DrawBackGround(GL gl, int index) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    /*
     * KeyListener
     */
    public void handleKeyPress() {
        
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = (int)(e.getX()*1.0/e.getComponent().getWidth() *100.0);
        MouseY = 100 - (int)(e.getY()*1.0/e.getComponent().getHeight()*100.0);
        double dist1 = Math.sqrt(Math.pow(mouseX - 25, 2) + Math.pow(MouseY - 50, 2));
        double dist2 = Math.sqrt(Math.pow(mouseX - 50, 2) + Math.pow(MouseY - 50, 2));
        double dist3 = Math.sqrt(Math.pow(mouseX - 75, 2) + Math.pow(MouseY - 50, 2));
        
        for (int i = 0; i < 3; i++) {
            arr[i] = 1;
        }
        
        if(dist1 < 9.1){
            arr[0] = 2;
        }else if(dist2 < 9.1){
            arr[1] = 3;
        }else if(dist3 < 9.1){
            arr[2] = 4;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
}
