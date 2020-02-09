import java.io.*;
import java.util.Scanner;

class PokemonGame
{ 
     public static void main(String[] args)
     {
        int turn = 1;

        Scanner input = new Scanner(System.in);
        Pokemon pikachu = new Pokemon();
        Pokemon dingdong = new Pokemon();

        pikachu.setenemy(dingdong);
        dingdong.setenemy(pikachu);
        pikachu.initial (1,   50,   30,   12,(byte)0B000);
        dingdong.initial(2,10000,10000,10000,(byte)0B011);
        pikachu.displaystat();
        dingdong.displaystat();

        while( ( pikachu.die() || dingdong.die() ) == false ){
           if(turn % 2 == 1)
           pikachu.turn(turn);
           else
           dingdong.turn(turn);
           turn++;
        }
     }
}  // end of class