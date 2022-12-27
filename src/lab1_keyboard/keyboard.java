package lab1_keyboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.EventListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author user
 */
public class keyboard extends JFrame implements KeyListener {

    Font typingFont = new Font(Font.MONOSPACED, Font.ITALIC, 30);
    JPanel panel1 = new JPanel();
    JPanel panel7 = new JPanel(new FlowLayout(FlowLayout.CENTER,37,10));
    ArrayList<JButton> buttonsArr = new ArrayList<>();

    keyboard() {
        
        panel7.setBorder(BorderFactory.createCompoundBorder(
                panel1.getBorder(),
                BorderFactory.createEmptyBorder(5, 11, 5, 11)));
        setupTextarea(this);
        
        for (int i = 0; i < keyboardButtons.buttonsNamesArr.length; i++) {
            buttonsArr.add( new JButton(keyboardButtons.buttonsNamesArr[i]));
                panel7.add(buttonsArr.get(i)); 
        }
        //setSize(1000,200);
        add(panel7);
        
        setupJframe(this);
    }

    public static void main(String[] args) {
        keyboard k = new keyboard();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        ArrayList<Integer> index = getIndexOfChar(e.getKeyCode());
        for (int i = 0; i < index.size(); i++) {
            buttonsArr.get(index.get(i)).setBackground(new Color(0,200,200));
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ArrayList<Integer> index = getIndexOfChar(e.getKeyCode());
        for (int i = 0; i < index.size(); i++) {
            buttonsArr.get(index.get(i)).setBackground(new ColorUIResource(new Color(238,238,238)));
        }
    }

    public void setupJframe(keyboard k) {
        k.setLayout(new GridLayout(2,0));
        k.setTitle("Keboard");
        k.setSize(1200, 700);
        k.setLocationRelativeTo(null);
        k.setResizable(false);
        k.setIconImage(new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\ComputerGraphics\\src\\lab1_keyboard\\Keyboard.jpg").getImage());
        k.setDefaultCloseOperation(EXIT_ON_CLOSE);
        k.setVisible(true);
    }

    public static void setupTextarea(keyboard k) {
        JTextArea txtArea = new JTextArea();
        txtArea.setFont(k.typingFont);
        txtArea.setLineWrap(true);
        txtArea.setBorder(BorderFactory.createCompoundBorder(
                k.panel1.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        txtArea.setTabSize(2);
        JScrollPane scroll = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(1150, 350));
        k.panel1.add(scroll);
        k.add(k.panel1);
        txtArea.addKeyListener(k);
    }
    ArrayList<Integer> getIndexOfChar(int button){
        ArrayList<Integer>result = new ArrayList<>();
        for (int i = 0; i < keyboardButtons.buttonsNamesArr.length; i++) {
            if(button==keyboardButtons.buttonsCodeArr[i]){
                result.add(i);
            }
        }
        return result;
    }
}
