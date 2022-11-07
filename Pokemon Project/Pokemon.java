package com.company;

import java.util.Random;
/**
 *
 * This is the pokemon class, containing the pokemon object's atributes and methods
 *
 * @author Randy
 *
 */
public abstract class Pokemon extends Entity{
    public static final double [][] battleTable =
            {{1,.5,2}, {2,1,.5}, {.5,2,1}};

    Random random = new Random();
    /**
     * constructs a pokemon
     */
    public Pokemon(String n){
        super(n, 24);
    }
    /**
     * in individual Pokemon classes and using various type interfaces
     */
    public abstract String getSpecialMenu();


    /**
     * in individual Pokemon classes and using various type interfaces
     */
    public abstract int getNumSpecialMenuItems();


    /**
     *special attack for pokemon
     *@param p
     *@param move
     */
    public abstract String specialAttack(Pokemon p, int move);

    /**
     *@return string of basic menu options
     */
    public String getBasicMenu(){
        return "What do you want to do?\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away";
    }

    /**
     *@return 4
     */
    public int getNumBasicMenuItems(){
        return 4;
    }

    /**
     *depending on move, do a move.
     *@param p
     *@param move
     *@return slam if move 1
     *@return slam if move 2
     *@return slam if move 3
     */
    public String basicAttack(Pokemon p, int move){
        if (move == 1){
            return slam(p);
        }
        else if (move == 2){
            return tackle(p);
        }
        else if (move == 3){
            return punch(p);
        }
        return "error. one of moves not selected";
    }

    /**
     *@return string of basic attack options
     */
    public String getAttackMenu(){
        return "1. Slam\n2. Tackle\n3. Punch";
    }

    /**
     *@return 3
     */
    public int getNumAttackMenuItems(){
        return 3;
    }

    /**
     *deals slam damage to p
     *@param p
     *@return string of p taking damage
     */
    public String slam(Pokemon p){
        Random random = new Random();
        int randValue = random.nextInt(6);
        p.takeDamage(randValue);
        return p.getName() + " is SLAMMED and takes " + randValue + " damage.\n";
    }

    /**
     *deals tackle damage to p
     *@param p
     *@return string of p taking damage
     */
    public String tackle(Pokemon p){
        Random random = new Random();
        int randValue = random.nextInt(2) + 2;
        p.takeDamage(randValue);
        return p.getName() + " is TACKLED and takes " + randValue + " damage.\n";
    }

    /**
     *deals punch damage to p
     *@param p
     *@return string of p taking damage
     */
    public String punch(Pokemon p){
        Random random = new Random();
        int randValue = random.nextInt(4) + 1;
        p.takeDamage(randValue);
        return p.getName() + " is PUNCHED and takes " + randValue + " damage.\n";
    }

    /*
     * @return pokemon type based on pokemon name
     */
    public int getType(){
        if (this.getName().equals("Charmander") || this.getName().equals("Ponyta")){
            return 0;
        }
        else if (this.getName().equals("Squirtle") || this.getName().equals("Staryu")){
            return 1;
        }
        else if (this.getName().equals("Bulbasaur") || this.getName().equals("Oddish")){
            return 2;
        }
        return 3;
    }

}
