package com.company;

/**
 * Functions used to manipulate names and hp of objects.
 *
 * Use:	For use with the pokemon project
 *	Since this class is abstract, call object.method
 *
 * Example:  int num = pokemon.getHp();
 * @author Varune Tomar 2021
 */
public abstract class Entity{
    /**
     * An entitiy object has a name, maxHp, and hp
     */
    private String name;
    private int hp;
    private int maxHp;

    /**
     *
     * @param n name of the entity
     * @param mHp max hp of the tntiy
     */
    public Entity(String n, int mHp) {

        this.name = n;
        this.hp = mHp;
        this.maxHp = mHp;
    }

        /**
         * @return the current hp as this.hp
         */
     public int getHp() {
         if (this.hp < 1) {
             this.hp = 0;
         }
         return this.hp;
     }

        /**
         * Gets the objects maxHp and returns it, accessor
         * @return this.maxHp.
         */
     public int getMaxHp() {
         return this.maxHp;
     }

        /**
         * Subtracts the damage of an attack from the targets current hp
         * void method, doesnt return anything
         */
     public void takeDamage(int d) {
         this.hp -= d;
     }

        /**
         * Sets hp equal to maxHp, healing the object
         * void method, doesnt return anything
         */
     public void heal() {
         this.hp = this.maxHp;
     }

        /**
         * Returns objects name, accessor
         *@return this.name
         */
     public String getName() {
         return this.name;
     }

        /**
         * Overwrite toString to print objects atributes in the context of Entity
         *@return String name, hp, maxhp
         */
     public String toString(){
        String s = getName() + " HP: " + getHp() + "/" + getMaxHp();
        return s;
    }
}
