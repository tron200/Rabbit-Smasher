/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.awt.Polygon;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 * For our purposes only two of the GLEventListeners matter. Those would be
 * init() and display().
 */
class SimpleGLEventListener implements GLEventListener {

    double minCircleScale = 10, maxCircleScale = 160, circleScale = 10,circleScaleDirection = 1;
    double starRotation = 6;
    double maxYvalue = 73, minYvalue = -90, yValue = 0,yDirection = 1;
    
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
        gl.glLineWidth(5.0f);
        
        //draw scaled circle
        gl.glColor3f(1.0f, 0.0f, 0.5f);
        gl.glPushMatrix();
        gl.glTranslated(-200, 0, 0);
        gl.glScaled(circleScale, circleScale, 0);
        poly(gl, 0.5, 360,GL.GL_LINE_LOOP,1,0);
        gl.glPopMatrix();
        
        //draw center rotating star
        gl.glColor3f(1.0f, 0.0f, 0.5f);
        gl.glPushMatrix();
        gl.glTranslated(0, 0, 0);
        gl.glScaled(150, 150, 0);
        gl.glRotated(starRotation, 0, 0, 1);
        poly(gl, 0.5, 5,GL.GL_LINE_LOOP,2,90);
        gl.glPopMatrix();
        
        // draw 5th virtical bounced shape
        gl.glColor3f(0, 0.7f, 1);
        gl.glColor3f(1.0f, 0.0f, 0.5f);
        gl.glPushMatrix();
        gl.glTranslated(200, yValue, 0);
        gl.glScaled(150, 150, 0);
        poly(gl, 0.5, 5,GL.GL_LINE_LOOP,1,90);
        gl.glPopMatrix();
        
        changeValues();

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

    private void changeValues() {
        
        //circle
        if(circleScale >= maxCircleScale){
            circleScaleDirection = -1;
        }else if(circleScale <= minCircleScale){
            circleScaleDirection = 1;
        }
        circleScale += circleScaleDirection;
        
        //star
        starRotation = (starRotation + 1)%360;
        
        //5th shape
        if(yValue >= maxYvalue){
            yDirection = -1;
        }else if(yValue <= minYvalue){
            yDirection = 1;
        }
        yValue += yDirection;
    }
}
