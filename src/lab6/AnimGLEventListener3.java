package lab6;

import Textures.AnimListener;
import Textures.TextureReader;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.opengl.*;
import java.util.BitSet;
import javax.media.opengl.glu.GLU;

public class AnimGLEventListener3 extends AnimListener {

    int animationIndex = 0;
    int maxWidth = 100;
    int maxHeight = 100;

    // Download enemy textures from https://craftpix.net/freebies/free-monster-2d-game-items/
    String textureNames[] = {"Back.png", "bullet.png"};

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
        player.x = 50;
        player.y = 50;
        
        ((Player) player).maxAnimCount = 4;
        
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
                texture[i] = TextureReader.readTexture(assetsFolderName + "//man//" + textureNames[i], true);
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

        for (int i = 0; i < anim.length; i++) {
            for (int j = 0; j < anim[i].length; j++) {
                try {
                    animations[i][j] = TextureReader.readTexture(assetsFolderName + "//man//" + anim[i][j], true);
                    gl.glBindTexture(GL.GL_TEXTURE_2D, animint[i][j]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                    new GLU().gluBuild2DMipmaps(
                            GL.GL_TEXTURE_2D,
                            GL.GL_RGBA, // Internal Texel Format,
                            animations[i][j].getWidth(), animations[i][j].getHeight(),
                            GL.GL_RGBA, // External format from image,
                            GL.GL_UNSIGNED_BYTE,
                            animations[i][j].getPixels() // Imagedata
                    );
                } catch (IOException e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        }

    }

    public void display(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();

        DrawBackGround(gl, 0);
        handleKeyPress();
//        DrawGraph(gl);

        for (int i = 0; i < bullets.size(); i++) {
            DrawSprite(gl, bullets.get(i).x, bullets.get(i).y, 1, 0.2f);
            bullets.get(i).move();
        }
        DrawPlayer(gl, player.x, player.y, ((Player) player).currentAnimationSetIndex, 1);

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void DrawPlayer(GL gl, int x, int y, int row, float scale) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, animint[row][((Player) player).currentAnimCount]);	// Turn Blending On

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
        if (isKeyPressed(KeyEvent.VK_SPACE)) {
            int dirX = 0, dirY = 0;
            switch (((Player) player).currentAnimationSetIndex) {
                case 0:
                    dirX = 0;
                    dirY = 1;
                    break;
                case 1:
                    dirX = 0;
                    dirY = -1;
                    break;
                case 2:
                    dirX = 1;
                    dirY = 0;
                    break;
                case 3:
                    dirX = -1;
                    dirY = 0;
                    break;
                case 4:
                    dirX = 1;
                    dirY = 1;
                    break;
                case 5:
                    dirX = -1;
                    dirY = 1;
                    break;
                case 6:
                    dirX = 1;
                    dirY = -1;
                    break;
                case 7:
                    dirX = -1;
                    dirY = -1;
                    break;
                default:
                    throw new AssertionError();
            }
            bullets.add(new ob(player.x, player.y, dirX, dirY, 2, 1));
        }
        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            player.dirX = -1;
            player.moving = 1;
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            player.dirX = 1;
            player.moving = 1;
        }
        if (isKeyPressed(KeyEvent.VK_DOWN)) {
            player.dirY = -1;
            player.moving = 1;
        }
        if (isKeyPressed(KeyEvent.VK_UP)) {
            player.dirY = 1;
            player.moving = 1;
        }
        if (!isKeyPressed(KeyEvent.VK_LEFT) && !isKeyPressed(KeyEvent.VK_RIGHT)) {
            player.dirX = 0;
        }

        if (!isKeyPressed(KeyEvent.VK_DOWN) && !isKeyPressed(KeyEvent.VK_UP)) {
            player.dirY = 0;
        }
        if (player.x < 5) {
            player.x = 5;
        }
        if (player.x > maxWidth - 6) {
            player.x = maxWidth - 6;
        }
        if (player.y < 5) {
            player.y = 5;
        }
        if (player.y > maxHeight - 6) {
            player.y = maxHeight - 6;
        }

        ((Player) player).playerAnimCalulator();
        if ((!isKeyPressed(KeyEvent.VK_LEFT) && !isKeyPressed(KeyEvent.VK_RIGHT))
                && !isKeyPressed(KeyEvent.VK_DOWN) && !isKeyPressed(KeyEvent.VK_UP)) {
            player.moving = 0;
        } else {
            player.move();
            ((Player) player).currentAnimCount = ++((Player) player).currentAnimCount % ((Player) player).maxAnimCount;
        }
    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care 
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }

    public static void main(String[] args) {
        new Anim(new AnimGLEventListener3());
    }
}
