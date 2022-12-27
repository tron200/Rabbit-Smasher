/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package keko;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javafx.scene.input.KeyCode;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 *
 * @author user
 */
public class ballMoving implements GLEventListener, KeyListener {

    double dirX = 0, dirY = 0, currX = 0, currY = 0, newDirX = 0, newDirY = 0;
    boolean ret = false;

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(0, 100, 0, 100);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-300, 300, -300, 300, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glPointSize(13.0f);
        gl.glLineWidth(5.0f);
        gl.glColor3f(1.0f, 0.0f, 0.5f);

        gl.glPushMatrix();
        gl.glTranslated(currX, currY, 0);
        poly(gl, 20, 360, GL.GL_POLYGON, 1, 0);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(0, -20, 0);
        poly(gl, 600, 2, GL.GL_LINE_LOOP, 1, 0);
        gl.glPopMatrix();

        changeVariables();
    }

    public void poly(GL gl, double r, int side, int type, int step, double start) {
        gl.glBegin(type);
        for (double i = start; i < start + step * 360; i += (step * 360 / side)) {
            gl.glVertex2d(r * Math.cos(Math.toRadians(i)), r * Math.sin(Math.toRadians(i)));
        }
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (Math.abs(currX) < 4 && Math.abs(currY) < 4) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                dirX = 1;
                newDirX = 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                dirX = -1;
                newDirX = -1;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                dirY = 1;
                newDirY = 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                dirY = -1;
                newDirY = -1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            newDirX = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            newDirX = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            newDirY = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            newDirY = 0;
        }
    }

    private void changeVariables() {
        isMaxBound();
        currX += dirX;
        currY += dirY;

        if (ret && Math.abs(currX) < 4 && Math.abs(currY) < 4) {
            ret = false;
            dirX = newDirX;
            dirY = newDirY;
            currX = 0;
            currY = 0;
        }
    }

    private void isMaxBound() {
        if (currX >= 120) {
            dirX = -1;
            ret = true;
        }
        if (currX <= -120) {
            dirX = 1;
            ret = true;
        }
        if (currY >= 120) {
            dirY = -1;
            ret = true;
        }
        if (currY <= -120) {
            dirY = 1;
            ret = true;
        }
    }

}
