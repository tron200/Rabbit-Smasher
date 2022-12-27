/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

import java.awt.Polygon;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 * For our purposes only two of the GLEventListeners matter. Those would be
 * init() and display().
 */
class SimpleGLEventListener implements GLEventListener {

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
        gl.glOrtho(-300, 300.0, -150, 150, -1.0, 1.0);

    }

    /**
     * Take care of drawing here.
     */
    @Override
    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glPointSize(13.0f);

        gl.glColor3f(1.0f, 0.0f, 0.5f);
        
        poly(gl, 40, 5,-200,0,GL.GL_LINE_LOOP);
        gl.glColor3f(0.3f, 1, 0.8f);
        poly(gl, 40, 50,0,0,GL.GL_LINE_LOOP);
        gl.glLineWidth(3.0f);
        gl.glColor3f(1,0 , 1);
        star(gl, 40, 5,0,0,GL.GL_LINE_LOOP);
        gl.glColor3f(0, 0.7f, 1);
        poly(gl, 40, 8,200,0,GL.GL_LINE_LOOP);
        gl.glColor3f(0, 0.4f, 1);
        poly(gl, 40, 50,0,100,GL.GL_LINE_LOOP);
        gl.glColor3f(1, 0.2f, 0);
        Spacwstar(gl, 40, 5,0,100,GL.GL_LINE_STRIP);

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

    public void poly(GL gl, int r, int side, double x_offset, double y_offset,int type) {
        gl.glBegin(type);
        for (int i = 0; i < 360; i += (360 / side)) {
            gl.glVertex2d(x_offset+r * Math.cos(Math.toRadians(i)-55), y_offset+r * Math.sin(Math.toRadians(i)-55));
        }
        gl.glEnd();
    }
    public void star(GL gl, int r, int side, double x_offset, double y_offset,int type) {
        gl.glBegin(type);
        for (int i = 0; i < 2*360; i += 2*(360 / side)) {
            gl.glVertex2d(x_offset+r * Math.cos(Math.toRadians(i)-55), y_offset+r * Math.sin(Math.toRadians(i)-55));
        }
        gl.glEnd();
    }
    
    public void Spacwstar(GL gl, int r, int side, double x_offset, double y_offset,int type) {
        gl.glBegin(type);
        for (int i = 0; i < 360+360/side; i += (360 / side)) {
            gl.glVertex2d(x_offset+r * Math.cos(Math.toRadians(i)), y_offset+r * Math.sin(Math.toRadians(i)));
            gl.glColor3f(1, 0, 0);
            gl.glVertex2d(x_offset+r/4 * Math.cos(Math.toRadians(i)-150), y_offset+r/4 * Math.sin(Math.toRadians(i)-150));
        }
        gl.glEnd();
    }
}
