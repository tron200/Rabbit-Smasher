package lab8finalGamee;

public class Level {
    //array of holes with rabbits

    int levelTime;
    double rabbitAppearTime;
    //rabbit appear method (optional)
    int winTarget;
    int difficulty;           //0: easy   1:medium   2:hard
    int minHolesNumber;
    int currentHolesNumber;
    int maxHolesNumber;
    int rabbitCost;

    Level(int diff) {   //0: easy   1:medium   2:hard
        switch (diff) {
            case 0 -> {//easy
                diff = 0;
                //array of holes
                levelTime = 40;
                rabbitAppearTime = 1.5;
                winTarget = 10;
                minHolesNumber = 3;
                maxHolesNumber = 6;
                rabbitCost = 10;
            }
            case 1 -> {//medium
                diff = 1;
                //array of holes
                levelTime = 60;
                rabbitAppearTime = 0.75;
                winTarget = 20;
                minHolesNumber = 6;
                maxHolesNumber = 9;
                rabbitCost = 20;
            }
            case 2 -> {//Hard
                diff = 2;
                //array of holes
                levelTime = 80;
                rabbitAppearTime = 0.50;
                winTarget = 30;
                minHolesNumber = 9;
                maxHolesNumber = 12;
                rabbitCost = 30;
            }
            

        }
        currentHolesNumber = minHolesNumber;
            }

}
