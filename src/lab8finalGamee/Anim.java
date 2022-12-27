package lab8finalGamee;
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
        glcanvas.addMouseMotionListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(60);
        animator.add(glcanvas);
        JTextField score = new JTextField();
        score.setMaximumSize(new Dimension(5,5));
        animator.start();
        
        setSize(900, 500);
        setTitle("Rabbit Smasher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}
