/*
 * Program: Simple Dungeon
 * Programmer: Sean Kelleher
 * Date: 12/7/14
 *
 * Description: This program is a text-based dungeon crawl. The player must survive 
 * random encounters with trolls, goblins and dragons to take the set amount of steps
 * that it takes to get through the dungeon.
 * 
 * Combat is resolved with with virtual four-sided dice rolls (hereafter referred 
 * to as a "d4") and three ability scores:
 *  
 *  * ATK(attack), which determines damage, 
 *  * DEF(defense), which is damage resistance, and 
 *  * SPD(speed), which is used in running away.
 *
 * The player and each monster also have hit points, or HP (the amount of damage they can take before dying).
 *
 * after combat (or after taking a step and not encountering any monsters) the 
 * player can either take another step (and maybe encounter more monsters) or 
 * rest to restore 5-8 hp.
 */
import java.util.Scanner;
import java.util.Random;

public class simpledungeon
{    
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        Random generator = new Random();
                      
        boolean cont = true; //used while loops to decide to continue or break the loop
        boolean dead = false; //used for ending loops once the player is dead
        int dmg = 0; //the variable for damage inflicted.
        int gold = 0; //running total of how much gold the player has collected
        int maxsteps = 10; //the amount of steps the player must take to win
        int steps = 0; //running total of how many steps the player has taken. Once this reaches maxsteps, the player wins.
        int enchance = 0; //number involved in determining random encounters
        int healing = 0; //used while the player is healing
        
        //stats for the player
        int patk = 6; //the player's ATK (attack)
        int pdef = 4; //the player's DEF (defense)
        int pspd = 5; //the player's SPD (speed)
        int php = 30; //the players starting HP (hit points)
        int maxhp = 30; //the player's maximum HP, which is the same as the startng HP
        
        //stats for the trolls
        int tatk = 6; 
        int tdef = 4;
        int tspd = 3;
        int thp = 15;
       
        //stats for the goblins
        int gatk = 4; 
        int gdef = 3;
        int gspd = 4;
        int ghp = 10;
        
        //stats for the dragons
        int datk = 5; 
        int ddef = 5;
        int dspd = 6;
        int dhp = 25;
        
        //containers for what the enemy's stats will be. This will change depending on what the player encounters.
        int enemyatk = 0;
        int enemydef = 0;
        int enemyspd = 0;
        int enemyhp = 0;
        int enemydrop = 0;
                
        System.out.println("Hello, brave hero! Welcome to Victory Questventure 2000.");
        System.out.println("Traverse the perils of the Doomy Doom Dungeon for GOLD and GLORY.");
        //here we deal with the equipment. The player chooses one item, which increases one stat.
        System.out.println("Choose one equipment to aid your quest: A Sword (increases your attack), a shield (increases your defense), or Nikes (increases your speed).");
        System.out.println("Your current stats are " + patk + " Attack, " + pdef + " Defense, and " + pspd + " Speed. Each item will increase the given score by 1.");
        while (cont == true)
        {
            System.out.println("enter \"Sword\", \"Shield\" or \"Nikes\".");
            String input = keyboard.next();        
            if(input.equalsIgnoreCase("shield"))
            {
                pdef++;
                cont = false;
                System.out.println("You get a large metal shield. None of those savage beasts will get through to you now!");              
            }
            if(input.equalsIgnoreCase("sword"))
            {
                patk++;
                cont = false;
                System.out.println("You get an impressive two-handed greatsword. Now you can really pile on that damage!");
            }    
            if(input.equalsIgnoreCase("nikes"))
            {
                pspd++;
                cont = false;
                System.out.println("You get some fresh new kicks. Let's see those monsters catch you now!");           
            }
            else
            {
                if(cont == true)
                {
                    System.out.println("sorry, that's not an option.");
                }                
            }
            if(cont == false)
            {
                System.out.println("Now, let's start the adventure!");
                System.out.println("");
            }
        }              
        //Here, the adventure begins.        
        System.out.println("You approach the wrought iron gate at the entrance of Doomy Doom Dungeon. \nYou have heard that untold horrors and fabulous treasure await those brave souls who venture into the legendary DDD. Brave souls such as yourself!");
        System.out.println("Armed with your equipment, your wits, and a starting HP of " + maxhp + " it's time to take on the world!");
        System.out.println("You open the gate and enter.");
        while(steps < maxsteps) //the game continues until the amount of steps has reached maxsteps
        {    
            if(dead==false)
            {    
                //choosing to continue is another step towards escaping the dungeon.           
                System.out.println("Shall you continue forward (c)? Or quit (q)?");
                if(php < maxhp)//the option to rest and heal is presented if the player's hp is less than maximum.
                {
                    System.out.print("or first take a short rest (r) to restore some health?");
                    String input= keyboard.next();               
                    if(input.contains("r"))                    
                    {
                        System.out.println("It's okay. we all need to take a break sometimes."); //5-8 hp are regained.
                        healing = generator.nextInt(4) +5; //the amount healed
                        php = php + healing;
                        php = Math.min(php, maxhp); //if the player's hit points are healed to greater than the maximum, it gets set to the maximum
                        System.out.println("You rest for a bit, putting yourself back up at " + php + " HP.");
                        System.out.println("Shall you continue forward (c)? Or quit (q)?");
                    }
                }              
                String input= keyboard.next();
                if(input.contains("c"))
                {
                    steps++;
                    System.out.println("You take a step."); 
                    System.out.println("You have taken " + steps + " steps. " + (maxsteps - steps) + " more to go!");
                    if(steps == maxsteps)                        
                    {    
                        //victory message appears upon winning
                        System.out.println("You see a tunnel, with the bright light of day coming through. It's the exit!");
                        System.out.println("Congratulations!!! You made it out of Doomy Doom Dungeon alive, and gathered " + gold + " gold along the way!!");
                        System.out.println("  _______  _______  _______  _______ _________  _________ _______  ______   _  _");
                        System.out.println(" (  ____ \\(  ____ )(  ____ \\(  ___  )\\__   __/  \\__    _/(  ___  )(  ___ \\ ( )( )");
                        System.out.println(" | (    \\/| (    )|| (    \\/| (   ) |   ) (        )  (  | (   ) || (   ) )| || | ");
                        System.out.println(" | (    \\/| (    )|| (    \\/| (   ) |   ) (        )  (  | (   ) || (   ) )| || |");
                        System.out.println(" | | ____ |     __)|  __)   |  ___  |   | |        |  |  | |   | ||  __ (  | || |");
                        System.out.println(" | | \\_  )| (\\ (   | (      | (   ) |   | |        |  |  | |   | || (  \\ \\ (_)(_)");
                        System.out.println(" | (___) || ) \\ \\__| (____/\\| )   ( |   | |     |\\_)  )  | (___) || )___) ) _  _ ");
                        System.out.println(" (_______)|/   \\__/(_______/|/     \\|   )_(     (____/   (_______)|/ \\___/ (_)(_)");
                        break;
                    }         
                }
                if(input.contains("q"))
                {
                    System.out.println("Okay then. Thanks for playing! Bye!");
                    break;
                }
                if(steps < maxsteps)
                {                    
                    enchance = generator.nextInt(17); //if the player is still in the dungeon, there's a chance of an encounter.
                }                
            }
            if(dead==true)//this ends the game once the player dies.
            {
                break;
            }
            //on a result of 10-16, no monster appears.
            if(enchance > 9)
            {
                System.out.println("No monsters yet!");
            }
            //on a result of 0 - 5, a goblin appears.
            if(enchance >= 0 && enchance <= 5) 
            {
                enemyhp = ghp;
                enemyatk = gatk;
                enemydef = gdef;
                enemyspd = gspd;
                enemydrop = generator.nextInt(6) + 1; //goblins drop 1-6 gold.
                System.out.println("A goblin appears! (Attack: " + enemyatk + " Defense: " + enemydef + " Speed: " + enemyspd + " HP: " + enemyhp + ").");
            }
            //on a result of 6-8, a troll appears.
            if(enchance >= 6 && enchance <= 8)
            {
                enemyhp = thp;
                enemyatk = tatk;
                enemydef = tdef;
                enemyspd = tspd;
                enemydrop = generator.nextInt(6) + 10; //trolls drop 10-15 gold.
                System.out.println("Oh no! A big nasty troll appears! (Attack: " + enemyatk + " Defense: " + enemydef + " Speed: " + enemyspd + " HP: " + enemyhp + ").");
            }
            //on a result of 9, a dragon appears.
            if(enchance == 9)
            {
                enemyhp = dhp;
                enemyatk = datk;
                enemydef = ddef;
                enemyspd = dspd;
                enemydrop = generator.nextInt(11) + 20;//dragons drop 20-30 gold.
                System.out.println("Oh my gosh! A mighty red dragon appears! (Attack: " + enemyatk + " Defense: " + enemydef + " Speed: " + enemyspd + " HP: " + enemyhp + "). \nToday is NOT your lucky day.");
            }
            //this part deals with combat.
            if(enchance < 10)
            {
                cont = true; //on a result of 0-9 the loop continues
            }    
            else
            {
                cont = false; //on a result greater than 9, there is no monster and the loop ends
            }
            while(cont == true)
            {                   
                //System.out.println(map(steps, 10));            
                System.out.println("Will you Run (r) or Attack(a)?");
                String input = keyboard.next();
                //this part deals with running away. The player's and monster's speeds (plus the d4) are compared.
                //if the player's speed is lower and fails to escape, the monster gets a free attack.
                //if the monster's attack misses, however, the player escapes.
                if(input.contains("r"))
                {
                    int pd4 = (generator.nextInt(4) + 1); //the d4 that the player will calculate speed with
                    int ed4 = (generator.nextInt(4) + 1);//the d4 that the enemy will calculate speed with
                    if(pspd + pd4 >= enemyspd + ed4)
                    {
                        System.out.println("You were faster than the monster and got away!");
                        System.out.println("However, you get no reward for your cowardice.");
                        cont = false;
                    }
                    if(pspd + pd4 < enemyspd + ed4)
                    {
                        System.out.println("You were too slow, the monster catches up with you! It attacks you!");
                        dmg = (enemyatk + (generator.nextInt(4) + 1)) - (pdef + (generator.nextInt(4) + 1));
                        if(dmg > 0)
                        {    
                            php = php -dmg;
                            System.out.println("it deals " + dmg + " damage! Your hp is now " + php + ". ");
                            if(php <= 0)
                            { 
                                System.out.print("You're dead. Sorry, no gold and glory for you.");
                                cont = false;
                                dead = true;
                            }
                        }
                        if(dmg <= 0)
                        {
                            System.out.println("But it still doesn't strike true enough, and misses.");
                            cont = true;
                        }
                    }
                }
                if(input.contains("a"))
                {
                    dmg = (patk + (generator.nextInt(4) + 1)) - (enemydef + (generator.nextInt(4) + 1));
                    if(dmg <= 0)
                    {
                        System.out.println("You attack, and valiantly miss!");
                    }
                    if(dmg >0)
                    {
                        System.out.println("You hit the monster! it takes " + dmg + " damage, putting it at " + (enemyhp - dmg) + " HP.");
                        enemyhp = enemyhp - dmg;
                        if(enemyhp > 0)
                        {
                            System.out.println("However, the vile thing is still moving!");
                        }
                    }
                    if(enemyhp > 0)
                    {
                        System.out.println("The monster attacks!");
                        dmg = (enemyatk + (generator.nextInt(4) + 1)) - (pdef + (generator.nextInt(4) + 1));
                        if(dmg > 0)
                        {
                            php = php - dmg;
                            php = Math.max(php, 0);
                            System.out.println("it deals " + dmg + " damage! Your hp is now " + php + ". ");
                            if(php <= 0)
                            {
                                System.out.print("You're dead. Sorry, no gold and glory for you.");
                                cont = false;
                                dead = true;
                            }
                        }
                        if(dmg <= 0)
                        {
                            System.out.println("It fails to hit!");
                        }                                
                    }
                    if(enemyhp <= 0)
                    {
                        gold = gold + enemydrop;
                        System.out.println("You have slain the savage beast! You get " + enemydrop + " gold, making your total " + gold + ".\n");
                        cont = false;                
                    }
                }            
            }        
        }       
    }      
}
