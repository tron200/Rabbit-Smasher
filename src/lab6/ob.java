/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab6;

/**
 *
 * @author user
 */
public class ob {
    
    int x = 0;
    int y = 0;
    int [] anim = {};
    int dirX = 0; // -1, 1
    int dirY = 0; // -1, 1
    int moving = 0;
    int speed = 1;
    
    ob(){
        
    }
    ob(int x, int y, int dirX, int dirY, int speed, int moving){
        this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.speed = speed;
        this.moving = moving;
    }
    
    void move(){
        x += moving * dirX * speed;
        y += moving * dirY * speed;
    }
}
