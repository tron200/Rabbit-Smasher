package lab7;

import lab6.*;
import Textures.AnimListener;
import com.sun.opengl.util.*;
import java.awt.*;
import javax.media.opengl.*;
import javax.swing.*;

public class Anim extends JFrame {

    public static void main(String[] args) {
        new Anim(new AnimGLEventListener3());
    }

    public Anim(AnimGLEventListener3 aListener) {
        GLCanvas glcanvas;
        Animator animator;

        AnimGLEventListener3 listener = aListener;
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addMouseListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(25);
        animator.add(glcanvas);
        animator.start();

        setTitle("Anim Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}
