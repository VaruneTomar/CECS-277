package com.company;
import java.util.Random;
/**
 *
 * Class for the Staryu object, with its attributes.
 *
 * @author Varune Tomar
 */
public class Staryu extends Pokemon implements Water{
    /**
     * Constructs a Staryu object.
     */
    public Staryu(){
        super("Staryu");
    }
    /**
     * Returns special menu for Staryu's type Water.
     */
    public String getSpecialMenu(){
        return specialMenu;
    }
    /**
     * Returns special items for Staryu type Water, int 3.
     */
    public int getNumSpecialMenuItems(){
        return numSpecialMenuItems;
    }
    /**
     * Takes attack input for Staryu, and returns the move.
     *@param p
     *@param move
     *@return if move = 1, waterGun
     *@return if move = 2, bubbleBeam
     *@return if move = 3, waterfall
     */
    public String specialAttack(Pokemon p, int move){
        if(move == 1){
            return watergun(p);
        } else if (move == 2){
            return bubbleBeam(p);
        } else if (move == 3){
            return waterfall(p);
        }

        return "error";
    }
    /**
     * Executes the attack waterGun
     *
     * Finds the random damage value for the attack to deal, and takes it from target's hp.
     *
     *@param  p - the targeted pokemon
     *@return String describing pokemon's damage taken from attack
     */
    public String watergun (Pokemon p){
        String wg = "";
        Random rand = new Random();
        int randvalue = rand.nextInt(6 - 2) + 2;
        double attackMultiplier = battleTable[this.getType()][p.getType()];
        int damage = (int) Math.round(attackMultiplier * randvalue);
        p.takeDamage(damage);
        wg = p.getName() + " is sprayed by a watergun and takes " + damage + " damage" + "\n";

        return wg;

    }
    /**
     * Executes the attack bubbleBeam
     *
     * Finds the random damage value for the attack to deal, and takes it from target's hp.
     *
     *@param p - the targeted pokemon
     *@return String describing pokemon's damage taken from attack
     */
    public String bubbleBeam (Pokemon p){
        String bb = "";

        Random rand = new Random();
        int randvalue = rand.nextInt(4 - 1) + 1;
        double attackMultiplier = battleTable[this.getType()][p.getType()];
        int damage = (int) Math.round(attackMultiplier * randvalue);
        p.takeDamage(damage);

        bb = p.getName() + " is hit by a bubblebeam and takes " + damage + " damage " + "\n";

        return bb;

    }
    /**
     * Executes the attack waterfall
     *
     * Finds the random damage value for the attack to deal, and takes it from target's hp.
     *
     *@param p - the targeted pokemon
     *@return String describing pokemon's damage taken from attack
     */
    public String waterfall (Pokemon p){
        String wf = "";
        Random rand = new Random();
        int randvalue = rand.nextInt(5 - 1) + 1;
        double attackMultiplier = battleTable[this.getType()][p.getType()];
        int damage = (int) Math.round(attackMultiplier * randvalue);

        p.takeDamage(damage);
        wf = p.getName() + " is hit by a waterfall and takes " + damage + " damage " + "\n";


        return wf;

    }
}