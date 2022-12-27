/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab6;

import Textures.TextureReader;

/**
 *
 * @author user
 */
public class Player extends ob{
    TextureReader.Texture [][] anim;
    int currentAnimCount = 0, maxAnimCount = 0;
    int currentAnimationSetIndex = 0;
    
    void playerAnimCalulator(){
        if(dirX == 0 && dirY == 1){
            currentAnimationSetIndex = 0;   // UP texture while moving 
        }else if(dirX == 0 && dirY == -1){
            currentAnimationSetIndex = 1;   // DOWN texture while moving
        }
        else if(dirX == 1 && dirY ==0 ){
            currentAnimationSetIndex = 2;  // RIGHT 
        }
        else if(dirX == -1 && dirY == 0){
            currentAnimationSetIndex = 3;  // LEFT
        }
        else if(dirX == 1 && dirY == 1){
            currentAnimationSetIndex = 4; // UP RIGHT
        }
        else if(dirX == -1 && dirY == 1){
            currentAnimationSetIndex = 5;  // UP LEFT
        }
        else if(dirX == 1 && dirY == -1){
            currentAnimationSetIndex = 6;  // DOWN RIGHT
        }
        else if(dirX == -1 && dirY == -1){
            currentAnimationSetIndex = 7; // DOWN LEFT
        }
    }
    
}
