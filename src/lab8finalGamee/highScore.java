/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8finalGamee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class highScore {
    static int best = 0;
    static File highs;
    static File names;
    static FileReader reader;
    static FileWriter writer;
    static InputStreamReader InputReader;
    static OutputStreamWriter OutputReader;

    
 
    
    public highScore() {
        read();
    }
    
    static String[] read(){
        String result = "";
        //name--*--15
        try {
        highs = new File("highs");
        highs.createNewFile();
        reader = new FileReader(highs);
        InputReader = new InputStreamReader(new FileInputStream(highs), "UTF-8");
        int character;
            while ((character = reader.read()) != -1) {
                result+= (char)character;
            }
            reader.close();
            String [] data = result.split("--*--");
            
            best = Integer.parseInt(data[1]);
            
        } catch (Exception e) {
            
        }
        return result.replace("--*--","    ").split("\n");
    }
    
    static void write(String text){
        try{
        highs = new File("highs");
        
        highs.createNewFile();
            String temp = "";
            for (int i = 0; i < read().length; i++) {
                temp += read()[i]+"\n";
            }
                    temp = temp.replace("    ", "--*--");
            
        writer = new FileWriter(highs);
        OutputReader = new OutputStreamWriter(new FileOutputStream(highs), "UTF-8");
       
            writer.write(text+ "\n"+temp);
            
            writer.close();
        }catch(Exception e){
            
        }
    }

    
    
    static boolean isNewHighScore(String playerName,int score){
        boolean x = false;
        if(best < score){
            x = true;
            best = score;
            write(playerName+"--*--"+score);
        }
        return x;
    }
    
    
    
}
