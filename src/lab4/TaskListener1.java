/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

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
    int move = 1,rotate = 1;
    double distanceFromOrigin = 51;
    double directionAngel = 0 ,dirX = 1, dirY = 1;
    double currentX = 0,currentY = 0;
    double faceRadius = 100;
    /**
     * Take care of initialization here.
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

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
        
        gl.glPushMatrix();
        gl.glTranslated(currentX, currentY , 0);
        
        gl.glColor3f(Color.yellow.getRed(),Color.yellow.getGreen(),Color.yellow.getBlue());
        poly(gl, faceRadius, 360, GL.GL_POLYGON, 1, 0);
        //draw 3 circles
        
        
        gl.glColor3f(0.0f, 0.0f,0.0f);
        gl.glPushMatrix();
        gl.glTranslated(distanceFromOrigin * Math.cos(Math.toRadians(270)), distanceFromOrigin * Math.sin(Math.toRadians(270)), 0);
        poly(gl, 30, 360,GL.GL_POLYGON,1,0);
        gl.glPopMatrix();
        
        gl.glColor3f(Color.yellow.getRed(),Color.yellow.getGreen(),Color.yellow.getBlue());
        gl.glPushMatrix();
        gl.glTranslated(distanceFromOrigin * Math.cos(Math.toRadians(270)), distanceFromOrigin * Math.sin(Math.toRadians(270)), 0);
        gl.glTranslated(0, 45, 0);
        poly(gl, 65, 4,GL.GL_POLYGON,1,45);
        gl.glPopMatrix();
        
        gl.glColor3f(1.0f, 0f, 0f);
        gl.glPushMatrix();
        gl.glTranslated(distanceFromOrigin * Math.cos(Math.toRadians(20)), distanceFromOrigin * Math.sin(Math.toRadians(20)), 0);
        gl.glRotated(rotate, 0, 0, 1);
        star(gl, 50, 5,GL.GL_POLYGON,2,1);
        gl.glPopMatrix();
        
        gl.glColor3f(1.0f, 0f, 0f);
        gl.glPushMatrix();
        gl.glTranslated(-distanceFromOrigin * Math.cos(Math.toRadians(20)), distanceFromOrigin * Math.sin(Math.toRadians(20)), 0);
        gl.glRotated(-rotate, 0, 0, 1);
        star(gl, 50, 5,GL.GL_POLYGON,2,1);
        gl.glPopMatrix();
        
        
        
        //draw big circle
        
        
        
        
        
        
        
        
        rotate++;
        move+=3;
        
        gl.glPopMatrix();
        if(currentX >= 400 - faceRadius){
            dirX = -1;
            changeAngel();
        }
        if(currentX <= -400 + faceRadius){
            dirX = 1;
            changeAngel();
        }
        if(currentY >= 400 - faceRadius){
            dirY = -1;
            changeAngel();
        }
        if(currentY <= -400 + faceRadius){
            dirY = 1;
            changeAngel();
        }
        currentX += dirX*Math.cos(Math.toRadians(directionAngel))*8;
        currentY += dirY*Math.sin(Math.toRadians(directionAngel))*8;
        System.out.println(dirX+"   "+dirY + " " + currentX+ " " + currentY);
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
    
    public void star(GL gl, double r, int side,int type,int step,double start) {
        gl.glBegin(type);
        int cur = 0;
        side = side * 2;
        for (double i = start; i < start +step * 360; i += (step * 360 / side)) {
            
            if(cur % 2 == 1){
                gl.glVertex2d(r * Math.cos(Math.toRadians(i)), r * Math.sin(Math.toRadians(i)));
            }else{
                gl.glVertex2d(r/2 * Math.cos(Math.toRadians(i)), r/2 * Math.sin(Math.toRadians(i)));
            }
            cur ++;
        }
        gl.glEnd();
    }

    private void changeAngel() {
            directionAngel = Math.random()*98+1;
        
    }

}
