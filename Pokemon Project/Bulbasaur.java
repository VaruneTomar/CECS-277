package com.company;

import java.util.Random;
/**
 *
 * Class for the Bulbasaur object, with its attributes.
 * @author Randy
 */
public class Bulbasaur extends Pokemon implements Grass{
    /**
     * Constructs a Bulbasaur object.
     */
    public Bulbasaur(){
        super("Bulbasaur");
    }
    /**
     * Returns special menu for Bulbasaur type Grass.
     */
    public String getSpecialMenu() {
        return specialMenu;
    }
    /**
     * Returns special items for Bulbasaur's type Grass, int 3.
     */
    public int getNumSpecialMenuItems(){
        return numSpecialMenuItems;
    }
    /**
     * Takes attack input for Bulbasaur, and returns the move.
     *@param p
     *@param move
     *@return if move = 1, vineWhip
     *@return if move = 2, razorLeaf
     *@return if move = 3, solarBeam
     */
    public String specialAttack(Pokemon p, int move){
        if (move == 1){
            return vineWhip(p);
        }
        else if (move == 2){
            return razorLeaf(p);
        }
        else if (move == 3){
            return solarBeam(p);
        }
        return "error. one of moves not selected";
    }
    /**
     * Executes the attack vineWhip
     *
     * Finds the random damage value for the attack to deal, and takes it from target's hp.
     *
     *@param p - the targeted pokemon
     *@return String describing pokemon's damage taken from attack
     */
    public String vineWhip(Pokemon p){
        Random random = new Random();
        int randValue = random.nextInt(3) + 1;
        double attackMultiplier = battleTable[this.getType()][p.getType()];
        int damage = (int) Math.round(attackMultiplier * randValue);
        p.takeDamage(damage);
        return p.getName() + " is hit by VINE WHIP and takes " + damage + " damage.\n";

    }
    /**
     * Executes the attack razorLeaf
     *
     * Finds the random damage value for the attack to deal, and takes it from target's hp.
     *
     *@param p - the targeted pokemon
     *@return String describing pokemon's damage taken from attack
     */
    public String razorLeaf(Pokemon p){
        Random random = new Random();
        int randValue = random.nextInt(3) + 2;
        double attackMultiplier = battleTable[this.getType()][p.getType()];
        int damage = (int) Math.round(attackMultiplier * randValue);
        p.takeDamage(damage);
        return p.getName() + " is slashed by RAZOR LEAF and takes " + damage + " damage.\n";
    }
    /**
     * Executes the attack solarBeam
     *
     * Finds the random damage value for the attack to deal, and takes it from target's hp.
     *
     *@param p - the targeted pokemon
     *@return String describing pokemon's damage taken from attack
     */
    public String solarBeam(Pokemon p){
        Random random = new Random();
        int randValue = random.nextInt(6);
        double attackMultiplier = battleTable[this.getType()][p.getType()];
        int damage = (int) Math.round(attackMultiplier * randValue);
        p.takeDamage(damage);
        return p.getName() + " is dazzled by SOLAR BEAM and takes " + damage + " damage.\n";
    }
}
