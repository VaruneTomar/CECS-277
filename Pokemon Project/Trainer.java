package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * This is the trainer class, containing the trainer object's atributes and methods
 *
 * @author Jack Pickle
 *
 */
public class Trainer extends Entity {

    private int money = 25;
    private int potions = 1;
    private int pokeballs = 5;
    private Point loc;
    private Map map;
    private ArrayList<Pokemon> pokemon = new ArrayList<>();

    public Trainer(String n, Pokemon p, Map m) {
        super(n, 25);
        map = m;
        pokemon.add(p);
        loc = map.findStart();
    }
    /**
     *@return money
     */
    public int getMoney() {

        return money;
    }

    /**
     *
     * @param amt
     * @return true or false
     */
    public boolean spendMoney(int amt) {
        if (money >= amt) {
            money -= amt;
            return true;
        } else {
            return false;
        }
    }

    public void receiveMoney(int amt) {
        money += amt;
    }
    /**
     *@return true if your trainer has potions
     *@return false if your trainer has no potions
     */
    public boolean hasPotion() {
        if (potions > 0) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * add a potion
     */
    public void receivePotion() {
        potions ++;
    }
    /**
     * heal one of the trainers pokemon by using a potion.
     * can't use potions on fainted pokemon
     */
    public void usePotion(int pokeindex) {
        if (pokemon.get(pokeindex).getHp() < 1 ){
            System.out.println("You can't use a potion on a fainted pokemon");
        } else {
            potions--;
            pokemon.get(pokeindex).heal();
        }

    }
    /**
     *@return true if your trainer has pokeball
     *@return false if your trainer has no pokeball
     */
    public boolean hasPokeball() {
        if (pokeballs > 0) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * add a potion
     */
    public void receivePokeball() {
        pokeballs ++;
    }
    /**
     * check if you caught the pokemon!
     * use a pokeball no matter what
     * catch odds is determined by current hp, and creating a random value
     * if random vaule is greater than the pokemons hp, you caught it, and it gets added to the party
     * @return true
     * if you fail to catch
     * @return false
     */
    public boolean catchPokemon(Pokemon p){
        Random random = new Random();
        int randValue = random.nextInt(p.getMaxHp());
        --pokeballs;
        if (randValue > p.getHp()) {
            System.out.println("Shake... Shake... Shake...");
            System.out.println("You caught the " + p + "!");
            pokemon.add(p);
            return true;
        } else {
            System.out.println("Shake... Unlucky! It broke free!");
            return false;
        }
    }
    /**
     * @return loc
     */
    public Point getLoc() {
        return loc;
    }
    /**
     *
     * if player is trying to go off map, dont translate.
     *
     * else, reveal the character that the player went to, and translate loc north
     *
     * @return map character
     */
    public char goNorth() {
        if (loc.getX() == 0) {
            return map.getCharAtLoc(loc);
        } else {
            map.reveal(loc);
            if (map.getCharAtLoc(loc) == 'w'){
                loc.translate(-1,0);
                return map.getCharAtLoc(loc);
            }
            map.removeCharAtLoc(loc);
            loc.translate(-1, 0);
            return map.getCharAtLoc(loc);
        }
    }
    /**
     *
     * if player is trying to go off map, dont translate.
     *
     * else, reveal the character that the player went to, and translate loc south
     *
     * @return map character
     */
    public char goSouth() {
        if (loc.getX() == 4) {
            return map.getCharAtLoc(loc);
        } else {
            map.reveal(loc);
            if (map.getCharAtLoc(loc) == 'w'){
                loc.translate(1,0);
                return map.getCharAtLoc(loc);
            }
            map.removeCharAtLoc(loc);
            loc.translate(1, 0);
            return map.getCharAtLoc(loc);
        }
    }
    /**
     *
     * if player is trying to go off map, dont translate.
     *
     * else, reveal the character that the player went to, and translate loc east
     *
     * @return map character
     */
    public char goEast() {
        if (loc.getY() == 4) {
            return map.getCharAtLoc(loc);
        } else {
            map.reveal(loc);
            if (map.getCharAtLoc(loc) == 'w'){
                loc.translate(0,1);
                return map.getCharAtLoc(loc);
            }
            map.removeCharAtLoc(loc);
            loc.translate(0, 1);
            return map.getCharAtLoc(loc);
        }
    }
    /**
     *
     * if player is trying to go off map, dont translate.
     *
     * else, reveal the character that the player went to, and translate loc west
     *
     * @return map character
     */
    public char goWest() {
        if (loc.getY() == 0) {
            return map.getCharAtLoc(loc);
        } else {
            map.reveal(loc);
            if (map.getCharAtLoc(loc) == 'w'){
                loc.translate(0,-1);
                return map.getCharAtLoc(loc);
            }
            map.removeCharAtLoc(loc);
            loc.translate(0, -1);
            return map.getCharAtLoc(loc);
        }
    }
    /**
     * @return size of pokemon list
     */
    public int getNumPokemon() {
        return pokemon.size();

    }
    /**
     * heal all pokemon in trainer's pokemon list.
     *
     * for use at pokecenters
     */
    public void healAllPokemon() {
        for (int i = 0; i < pokemon.size(); i++)
            if (pokemon.get(i).getHp() > 0) {
                pokemon.get(i).heal();
            }
    }
    /**
     * @return pokemon at a specific index
     */
    public Pokemon getPokemon(int index) {

        return pokemon.get(index);
    }
    /**
     * @return trainers list of pokemon as a string
     */
    public String getPokemonList() {
        String str = "";
        for (int i = 0; i < pokemon.size(); i++) {
            str += i + 1 + ". " + String.valueOf(getPokemon(i)) + "\n";
        }
        return str;
    }
    /**
     * @return trainer as a string with name, hp, maxhp, money, potions, pokeballs, pokemon, and location
     */
    public String toString() {
        String str = "";
        str = "\n" + getName() + " HP: " + getHp() + "/" + getMaxHp() + "\n";
        str += "Money: " + getMoney() + "\n";
        str += "Potions: " + potions + "\n";
        str += "Poke Balls: " + pokeballs + "\n";
        str += "Pokemon" + "\n" + "--------" +"\n";
        str += getPokemonList() + "\n";
        str += "Map: " + "\n";
        str += map.mapToString(loc);

        return str;
    }
}