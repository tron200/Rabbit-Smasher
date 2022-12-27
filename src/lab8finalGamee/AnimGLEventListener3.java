package lab8finalGamee;

import lab7.*;
import lab6.*;
import Textures.AnimListener;
import Textures.TextureReader;
import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.j2d.TextRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.opengl.*;
import java.util.BitSet;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.media.opengl.glu.GLU;
import javax.naming.spi.DirectoryManager;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AnimGLEventListener3 implements GLEventListener, MouseListener, MouseMotionListener, KeyListener {

    // temps
    int tempHight = 0, tempWidth = 0, tx = 0, ty = 0, tfz = 15;
    GL gl;
    int mouseX = 0, mouseY = 0;
    int arr[] = {8, 8, 8, 8, 8, 8, 8, 8, 8};
    int x = 0, y = 0;
    String path;

    int mouseIndex = 0;
    private boolean soundSet;
    MediaPlayer mediaPlayerFactory;
    static  Clip clip;
    static  Clip clip2;

    //Layout enter name variables
    /*
     5 means gun in array pos
     x and y coordinate for gun 
     */
    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        Constants.gl = gl;
        Constants.textureManager = new TextureManager(gl);
        Constants.renderer = new TextRenderer(new Font("Blomberg", Font.BOLD, 90));
        setSound();
        gl.glLoadIdentity();
        gl.glOrtho(-Constants.maxWidth / 2, Constants.maxWidth / 2, -Constants.maxHeight / 2, Constants.maxHeight / 2, -1, 1);
    }

    public void display(GLAutoDrawable gld) {
        gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        //DrawSprite(gl, -1, 1, arr[0], 0.1f,0.1f);
        //back ground
        LayoutManager.run();

        // put holes random
        //Drawing.DrawSprite(x, y, 0, tempWidth, tempHight, TextureManager.holesWithRabitsTexInt);
        GameManager.run();
        Drawing.DrawSprite(x, y, 5, 40, 40, TextureManager.hammerTexInt);
        Drawing.DrawSprite(x + 20, y + 15, mouseIndex, 100, 100, TextureManager.hammerTexInt);
        
        
        //Drawing.DrawSprite(x, y, mouseIndex, tempWidth, tempHight, TextureManager.layoutTexInt);
        
        //Drawing.writeAtPos("30", tx, ty, tfz);
        
        // players
//        setLayout(gl, -200, 120, arr[0], 100, 75);
//        setLayout(gl, 0, 120, arr[1], 100, 75);
//        setLayout(gl, 200, 120, arr[2], 100, 75);
//        setLayout(gl, -200, 0, arr[3], 100, 75);
//        setLayout(gl, 0, 0, arr[4], 100, 75);
//        setLayout(gl, 200, 0, arr[5], 100, 75);
//        setLayout(gl, -200, -120, arr[6], 100, 75);
//        setLayout(gl, 0, -120, arr[7], 100, 75);
//        setLayout(gl, 200, -120, arr[8], 100, 75);
//        
        // setLayout(gl, x, y, 1, 50, 12.5f + 25f);
        //handleKeyPress();
//        DrawGraph(gl);

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    /*
     * KeyListener
     */
    public void handleKeyPress() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();
        mouseX = (int) ((x / width) * Constants.maxWidth) - Constants.maxWidth / 2;
        mouseY = Constants.maxHeight / 2 - ((int) ((y / height) * Constants.maxHeight));

        switch (LayoutManager.current_layout) {
            case Constants.Layout_menu: {
                //start clicked
                if (mouseX > 60 && mouseX < 360 && mouseY < 197.5 && mouseY > 112.5) {
                    //start clicked
                    LayoutManager.current_layout = Constants.Layout_enter_name;
                } else if (mouseX > 60 && mouseX < 360 && mouseY < 92.5 && mouseY > 7.5) {
                    //high score klicked
                    LayoutManager.current_layout = Constants.Layout_high_score;
                } else if (mouseX > 60 && mouseX < 360 && mouseY < -12.5 && mouseY > -97.5) {
                    //How to play klicked
                    LayoutManager.current_layout = Constants.Layout_how_to_play;
                } else if (mouseX > -432.5 && mouseX < -367.5 && mouseY < -177.5 && mouseY > -242.5) {
                    // sound Button clicked 
                    GameManager.switchMuteButton();
                } else if (mouseX > 60 && mouseX < 360 && mouseY < -117.5 && mouseY > -202.5) {
                    // Exit Clicked
                    System.exit(0);
                }
            }
            break;
            case Constants.Layout_difficalties: {
                if (mouseX > -440 && mouseX < -370 && mouseY < 240 && mouseY > 170) {
                    // back Button clicked 
                    LayoutManager.current_layout = Constants.Layout_enter_name;
                } else if (mouseX > -162.5 && mouseX < 142.5 && mouseY < 205 && mouseY > 125) {
                    // easy clicked x = -10 y = 165 width = 305 hight = 80
                    GameManager.changeLevel(0);
                    LayoutManager.current_layout = Constants.Layout_game;
                } else if (mouseX > -162.5 && mouseX < 142.5 && mouseY < 45 && mouseY > -35) {
                    // medium clicked x = -10 y = 5 width = 305 hight = 80
                    GameManager.changeLevel(1);
                    LayoutManager.current_layout = Constants.Layout_game;
                } else if (mouseX > -162.5 && mouseX < 142.5 && mouseY < -115 && mouseY > -195) {
                    // hard clicked x = -10 y = -155 width = 305 hight = 80
                    GameManager.changeLevel(2);
                    LayoutManager.current_layout = Constants.Layout_game;
                }
            }
            break;
            case Constants.Layout_game:

                if (GameManager.win) {
                    //x = 186 y = -192 width = 165 hight = 60       next
                    if (mouseX > 103.5 && mouseX < 268.5 && mouseY < -162 && mouseY > -222) {
                        if(GameManager.gameover){
                            System.out.println("game over");
                            GameManager.hardReset();
                            LayoutManager.current_layout = Constants.Layout_high_score;
                        }else{
                            GameManager.next();
                            
                            
                        }
                    } //x = -179 y = -192 width = 165 hight = 60      menu
                    else if (mouseX > -261.5 && mouseX < -96.5 && mouseY < -162 && mouseY > -222) {
                        LayoutManager.current_layout = Constants.Layout_menu;
                    }
                }else if(GameManager.lose){
                    //x = 186 y = -192 width = 165 hight = 60       next
                    if (mouseX > 103.5 && mouseX < 268.5 && mouseY < -162 && mouseY > -222) {
                        GameManager.hardReset();
                        GameManager.setup();
                        GameManager.resume();
                    } //x = -179 y = -192 width = 165 hight = 60      menu
                    else if (mouseX > -261.5 && mouseX < -96.5 && mouseY < -162 && mouseY > -222) {
                        GameManager.hardReset();
                        GameManager.setup();
                        GameManager.resume();
                        LayoutManager.current_layout = Constants.Layout_menu;
                    }
                }else {
                    if (GameManager.isPause() && !GameManager.win) {
                        GameManager.resume();
                    }

                    if (mouseX > -285 && mouseX < -225 && mouseY < 245 && mouseY > 185) {
                        // back Button clicked 
                        GameManager.reset();
                        LayoutManager.current_layout = Constants.Layout_difficalties;
                    } else if (mouseX > -220 && mouseX < -160 && mouseY < 245 && mouseY > 185) {
                        // sound button clicked
                        GameManager.switchMuteButton();
                    } else if (mouseX > -170.5 && mouseX < -111.5 && mouseY < 248.5 && mouseY > 177.5) {
                        // pause button clicked

                        GameManager.pause();
                    }
                    if (!GameManager.isPause()) {
                        GameManager.Hit(mouseX, mouseY);
                    }

                }
                break;
            case Constants.Layout_high_score:
                if (mouseX > -440 && mouseX < -370 && mouseY < 240 && mouseY > 170) {
                    // back Button clicked 
                    LayoutManager.current_layout = Constants.Layout_menu;
                }
                break;
            case Constants.Layout_how_to_play:
                if (mouseX > -440 && mouseX < -370 && mouseY < 240 && mouseY > 170) {
                    // back Button clicked 
                    LayoutManager.current_layout = Constants.Layout_menu;
                }
                break;
            case Constants.Layout_lose:

                break;
            case Constants.Layout_paused:

                break;
            case Constants.Layout_win:

                break;
            case Constants.Layout_enter_name:
                if (mouseX > -440 && mouseX < -370 && mouseY < 240 && mouseY > 170) {
                    // back Button clicked 
                    LayoutManager.current_layout = Constants.Layout_menu;
                } else if (mouseX > -60 && mouseX < 60 && mouseY < -135 && mouseY > -255) {
                    // next button clicked
                    
                    GameManager.player = new Player(GameManager.EnterdName);
                    LayoutManager.current_layout = Constants.Layout_difficalties;
                }
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        while (mouseIndex != 4) {
            mouseIndex++;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        while (mouseIndex != 0) {
            mouseIndex--;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(70, 70, BufferedImage.TYPE_INT_ARGB);

// Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        e.getComponent().setCursor(blankCursor);
        double x = e.getX();
        double y = e.getY();
        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();
        this.x = (int) ((x / width) * Constants.maxWidth) - Constants.maxWidth / 2;;
        this.y = Constants.maxHeight / 2 - ((int) ((y / height) * Constants.maxHeight));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_U) {
            tfz += 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            tfz -= 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            tempHight += 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            tempHight -= 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            tempWidth += 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            tempWidth -= 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            y += 5;
            ty += 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            y -= 5;
            ty -= 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x += 5;
            tx += 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x -= 5;
            tx -= 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("x = " + x + " y = " + y + " width = " + tempWidth + " hight = " + tempHight);
            GameManager.rh.add(new RabbitHole(x, y, tempWidth, tempHight, 0, 1));
            System.out.println("tx = " + tx + " ty = " + ty + " tfz = " + tfz);
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_I) {
            if (mouseIndex < 4) {
                mouseIndex++;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            if (mouseIndex > 0) {
                mouseIndex--;
            }
        }

        //get tybed name
        if (LayoutManager.current_layout.equals(Constants.Layout_enter_name)) {
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (GameManager.EnterdName.length() != 0) {
                    GameManager.EnterdName = GameManager.EnterdName.substring(0, GameManager.EnterdName.length() - 1);
                }
            } else {
                if (GameManager.EnterdName.length() < 10) {
                    GameManager.EnterdName += e.getKeyChar();
                }

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    static void setSound(){
        try{
        AudioInputStream audiostream = AudioSystem.getAudioInputStream(new File("C://Users//user//Documents//NetBeansProjects//ComputerGraphics//soundtracks//menu.wav"));
        clip =  AudioSystem.getClip();
        clip.open(audiostream);
        clip.loop(-1);
        clip.start();
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
