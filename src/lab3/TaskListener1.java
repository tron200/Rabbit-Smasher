/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3;

import java.awt.Color;
import java.awt.Polygon;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 * For our purposes only two of the GLEventListeners matter. Those would be
 * init() and display().
 */
class TaskListener1 implements GLEventListener {
    int move = 1;
    double distanceFromOrigin = 160;
    /**
     * Take care of initialization here.
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glViewport(0, 0, 600, 300);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-400, 400.0, -400, 400, -1.0, 1.0);

    }

    /**
     * Take care of drawing here.
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPointSize(13.0f);
        gl.glLineWidth(5.0f);
        
        //draw big circle
        gl.glColor3f(Color.yellow.getRed(),Color.yellow.getGreen(),Color.yellow.getBlue());
        poly(gl, 300, 360, GL.GL_POLYGON, 1, 0);
        //draw 3 circles
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glPushMatrix();
        gl.glTranslated(distanceFromOrigin * Math.cos(Math.toRadians(20)), distanceFromOrigin * Math.sin(Math.toRadians(20)), 0);
        poly(gl, 100, 360,GL.GL_POLYGON,1,0);
        gl.glPopMatrix();
        
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glPushMatrix();
        gl.glTranslated(-distanceFromOrigin * Math.cos(Math.toRadians(20)), distanceFromOrigin * Math.sin(Math.toRadians(20)), 0);
        poly(gl, 100, 360,GL.GL_POLYGON,1,0);
        gl.glPopMatrix();
        
        gl.glColor3f(0.0f, 0.0f,0.0f);
        gl.glPushMatrix();
        gl.glTranslated(distanceFromOrigin * Math.cos(Math.toRadians(270)), distanceFromOrigin * Math.sin(Math.toRadians(270)), 0);
        poly(gl, 100, 360,GL.GL_POLYGON,1,0);
        gl.glPopMatrix();
        
        gl.glColor3f(0.0f, 0.0f,0.0f);
        gl.glPushMatrix();
        gl.glTranslated(distanceFromOrigin * Math.cos(Math.toRadians(20)), distanceFromOrigin * Math.sin(Math.toRadians(20)), 0);
        gl.glRotated(move*20, 0, 0, 1);
        gl.glTranslated(-50, 0, 0);
        poly(gl, 50, 360,GL.GL_POLYGON,1,0);
        gl.glPopMatrix();
        
        gl.glColor3f(0.0f, 0.0f,0.0f);
        gl.glPushMatrix();
        gl.glTranslated(-distanceFromOrigin * Math.cos(Math.toRadians(20)) , distanceFromOrigin * Math.sin(Math.toRadians(20)), 0);
        gl.glRotated(-move*20, 0, 0, 1);
        gl.glTranslated(50, 0, 0);
        poly(gl, 50, 360,GL.GL_POLYGON,1,0);
        gl.glPopMatrix();
        move++;
        
        

    }

    /**
     * Called when the GLDrawable (GLCanvas or GLJPanel) has changed in size. We
     * won't need this, but you may eventually need it -- just not yet.
     */
    @Override
    public void reshape(
            GLAutoDrawable drawable,
            int x,
            int y,
            int width,
            int height
    ) {
    }

    /**
     * If the display depth is changed while the program is running this method
     * is called. Nowadays this doesn't happen much, unless a programmer has his
     * program do it.
     */
    @Override
    public void displayChanged(
            GLAutoDrawable drawable,
            boolean modeChanged,
            boolean deviceChanged
    ) {
    }

    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub

    }

    public void poly(GL gl, double r, int side,int type,int step,double start) {
        gl.glBegin(type);
        for (double i = start; i < start +step * 360; i += (step * 360 / side)) {
            gl.glVertex2d(r * Math.cos(Math.toRadians(i)), r * Math.sin(Math.toRadians(i)));
        }
        gl.glEnd();
    }

}
