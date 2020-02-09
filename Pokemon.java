import java.io.*;
import java.util.Scanner;

class Pokemon
{
      private Scanner input = new Scanner(System.in);
      private int ID,health,mana,attack,lastdamage=0;
      private byte status;

      private final byte ATTACK     = (byte)1;
      private final byte SUICIDE    = (byte)2;
      private final byte UNO        = (byte)3;

      private Pokemon enemy;

      public void setenemy(Pokemon in_enemy){
         try{
            enemy = in_enemy;
         }
         catch(Exception e){
            System.out.println(e);
         }
      }

      private String getname(int id){
         switch(id){
            case 1:return "pikachu";
            case 2:return "dingdong";
            default: return "Who am i? why am I still here? just to suffer?";
         }
      }
     
      public boolean die(){
         return health == 0;
      }

      public int bytedigit(byte num,int digit){
        return (byte)(num >>> digit) & (byte)1;
      }

      public boolean initial(int in_id,int in_health,int in_mana,int in_attack,byte in_status){
        try{
           ID = in_id;
           health = in_health;
           mana = in_mana;
           attack = in_attack;
           status = in_status;
           return true;
        }
        catch(Exception e){
           System.out.println(e);
           return false;
        }
      }

     public boolean displaystat(){
        try{
           System.out.println("Name : " + getname(ID));
           System.out.println("Health is " + Integer.toString(health));
           System.out.println("Mana is " + Integer.toString(mana));
           System.out.println("Attack is " + Integer.toString(attack));
           System.out.print("Status is ");
           if(status == 0)
           System.out.println("none");
           else{
              if(bytedigit(status,0) == 1 )System.out.print("stonk ");
              if(bytedigit(status,1) == 1 )System.out.print("resistance ");
              System.out.println();
           }
           System.out.println("\n==================================\n");
           return true;
        }
        catch(Exception e){
           System.out.println(e);
           return false;
        }
     }

     public void turn(int turn){
         System.out.println("OK Turn "+Integer.toString(turn)+" this is "+getname(ID)+"'s turn");
         switch(ID){
            case 1:
               System.out.println("1 for attack\n2 for suicide\n3 for respect\nanything else for pass");
               switch(input.nextInt()){
               case 1:attack(enemy);break;
               case 2:suicide();break;
               case 3:respect(enemy);break;
               }break;
            case 2:
               System.out.println("1 for attack\n2 for suicide\n3 for respect\n4 for UNO reverse\nanything else for pass");
               switch(input.nextInt()){
               case 1:attack(enemy);break;
               case 2:suicide();break;
               case 3:respect(enemy);break;
               case 4:uno(enemy);break;
               }break;
         }
      }

     private boolean attack(Pokemon Target){
        try{
           System.out.println(getname(ID)+" is attacking "+getname(Target.ID)+"!!");
           Target.damage(ATTACK,this);
           return true;
        }
        catch(Exception e){
           System.out.println(e);
           return false;
        }
     }

     private boolean suicide(){
        try{
           damage(SUICIDE,null);
           return true;
        }
        catch(Exception e){
           System.out.println(e);
           return false;
        }
     }

      private boolean respect(Pokemon Target){
        try{
            System.out.println("\n"+getname(ID)+" is respecting "+getname(Target.ID)+"!!\ntoo bad .... it is not so effective!!!\n");
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
      }

      private boolean uno(Pokemon Target){
         try{
            System.out.println(getname(ID)+" is using UNO reverse to "+getname(Target.ID)+"!!!!!\n");
            Target.damage(UNO,this);
            return true;
         }
         catch(Exception e){
            System.out.println(e);
            return false;
         }
      }

      private void damage(byte reason,Pokemon attacker){
         if(reason == ATTACK)
         {
            int Damage = attacker.attack;

            if(bytedigit(attacker.status,0) == 1 ){
               System.out.println(getname(attacker.ID)+" having strength so the damage is double!!!!!");
               Damage *= 2;
            }
            if(bytedigit(status,1) == 1 ){
               System.out.println("But "+getname(ID)+" having resistance so the damage is halve!!!!!");
               Damage /= 2;
            }

            System.out.println(getname(ID)+" is taking "+Integer.toString(Damage)+" damage!!");
            if(health > Damage){
               health -= Damage;
               System.out.println(getname(ID)+"'s health is left at "+Integer.toString(health)+"!!\n");
            }
            else{
               health = 0;
               System.out.println("\ngoddamn "+ getname(ID) +" is killed by " + getname(attacker.ID) +
               "..\nthis is so sad UmU\n");
            }
            lastdamage = Damage;
         }
         if(reason == UNO)
         {
            int Damage = attacker.lastdamage;
            System.out.println(getname(ID)+" is taking "+Integer.toString(Damage)+" damage!!");
            if(health > Damage){
               health -= Damage;
               System.out.println(getname(ID)+"'s health is left at "+Integer.toString(health)+"!!\n");
            }
            else{
               health = 0;
               System.out.println("\ngoddamn "+ getname(ID) +" is killed by " + getname(attacker.ID) +
               "..\nthis is so sad UmU\n");
            }
         }
         if(reason == SUICIDE)
         {
            health = 0;
            System.out.println("\ngoddamn "+ getname(ID) +" kill itself this is so sad\n");
         }
     }
}  // end of class