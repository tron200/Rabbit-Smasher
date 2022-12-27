/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8finalGamee;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class RabbitHole {
    int x;
    int y;
    int width;
    int hight;
    int currentAnimationIndex = 0;
    double timeOfshow ;

    public RabbitHole(int x, int y, int width, int hight,int currentAnimationIndex , double timeOfshow) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.hight = hight;
        this.timeOfshow = timeOfshow;
        this.currentAnimationIndex = currentAnimationIndex;
    }
    
    void show(){
        if(currentAnimationIndex <3){
            currentAnimationIndex++;
        }
        timeOfshow-=0.01;
    }
    
    void disappear(){
        if(currentAnimationIndex >0){
            currentAnimationIndex--;
        }
        timeOfshow-=0.01;
    }
    
    void destroyRabbit(double time){
        timeOfshow = time;
        currentAnimationIndex = 4;
        
    }
    
    
    
}
