package com.company;

import java.util.Random;

/**
 *
 * Class for the charmader object, with its attributes.
 *
 * @author Jack
 */
public class Charmander extends Pokemon implements Fire{
    /**
     * Constructs a Charmander object.
     */
    public Charmander(){
        super("Charmander");
    }
    /**
     * Returns special menu for charmanders type Fire.
     */
    public String getSpecialMenu() {
        return specialMenu;
    }
    /**
     * Returns special items for charmanders type Fire, int 3.
     */
    public int getNumSpecialMenuItems(){
        return numSpecialMenuItems;
    }
    /**
     * Takes attack input for Charmander, and returns the move.
     *@param  p
     *@param move
     *@return if move = 1, ember
     *@return if move = 2, fireBlast
     *@return if move = 3, firePunch
     */
    public String specialAttack(Pokemon p, int move){
        if (move == 1){
            return ember(p);
        }
        else if (move == 2){
            return fireBlast(p);
        }
        else if (move == 3){
            return firePunch(p);
        }
        return "error. one of moves not selected";
    }
    /**
     * Executes the attack ember
     *
     * Finds the random damage value for the attack to deal, and takes it from target's hp.
     *
     *@param p - the targeted pokemon
     *@return String describing pokemon's damage taken from attack
     */
    public String ember(Pokemon p){
        Random random = new Random();
        int randValue = random.nextInt(3);
        double attackMultiplier = battleTable[this.getType()][p.getType()];
        int damage = (int) Math.round(attackMultiplier * randValue);
        p.takeDamage(damage);
        return p.getName() + " is roasted by EMBER and takes " + damage + " damage.\n";

    }
    /**
     * Executes the attack fireBlast
     *
     * Finds the random damage value for the attack to deal, and takes it from target's hp.
     *
     *@param p - the targeted pokemon
     *@return String describing pokemon's damage taken from attack
     */
    public String fireBlast(Pokemon p){
        Random random = new Random();
        int randValue = random.nextInt(3) + 1;
        double attackMultiplier = battleTable[this.getType()][p.getType()];
        int damage = (int) Math.round(attackMultiplier * randValue);
        p.takeDamage(damage);
        return p.getName() + " is scorched by FIRE BLAST and takes " + damage + " damage.\n";
    }
    /**
     * Executes the attack ember
     *
     * Finds the random damage value for the attack to deal, and takes it from target's hp.
     *
     *@param p - the targeted pokemon
     *@return String describing pokemon's damage taken from attack
     */
    public String firePunch(Pokemon p){
        Random random = new Random();
        int randValue = random.nextInt(2) + 1;
        double attackMultiplier = battleTable[this.getType()][p.getType()];
        int damage = (int) Math.round(attackMultiplier * randValue);
        p.takeDamage(damage);
        return p.getName() + " is thrashed by FIRE PUNCH and takes " + damage + " damage.\n";
    }
}
