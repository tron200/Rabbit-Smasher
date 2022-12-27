/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package keko;

import lab5.*;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 * For our purposes only two of the GLEventListeners matter. Those would be
 * init() and display().
 */
class SimpleGLEventListener implements GLEventListener,MouseListener {

    ArrayList<Pair> points = new ArrayList<>();
    /**
     * Take care of initialization here.
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        //gl.glViewport(0, 600, 0, 600);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 100, 0, 100, -1.0, 1.0);

    }

    /**
     * Take care of drawing here.
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        //draw scaled circle
        for (int i = 0; i < points.size(); i++) {
            drawPoint(gl, GL.GL_POINTS, points.get(i));
            if(i % 2 == 1){
                drawLine(gl,GL.GL_LINE_STRIP , points.get(i-1), points.get(i));
            }
        }
        

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

    public void drawLine(GL gl,int type,Pair point1,Pair point2) {
        gl.glColor3f(1f, 0.0f, 0.0f);
        gl.glLineWidth(5.0f);
        gl.glBegin(type);
        gl.glVertex2i((int)point1.getKey(), (int)point1.getValue());
        gl.glVertex2i((int)point2.getKey(), (int)point2.getValue());
        gl.glEnd();
    }
    
    public void drawPoint(GL gl,int type,Pair point1) {
        gl.glColor3f(0f, 0.5f, 1.0f);
        gl.glPointSize(8.0f);
        gl.glBegin(type);
        gl.glVertex2i((int)point1.getKey(), (int)point1.getValue());
        gl.glEnd();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = (int)(1+e.getX()*1.0/e.getComponent().getWidth() * 100);
        int y = 100 - (int)(e.getY()*1.0/e.getComponent().getHeight() * 100);
        points.add(new Pair(x,y));
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
