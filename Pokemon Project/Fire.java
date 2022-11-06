package com.company;

/**
 *
 * This is the interface for all pokemon of the fire type
 *
 * @author Jack Pickle
 *
 */
public interface Fire {
    /**
     * Displays choice of special moves
     */
    public String specialMenu = "1. Ember\n2. Fire Blast\n3. Fire Punch";
    public int numSpecialMenuItems = 3;

    public String ember (Pokemon p);

    public String fireBlast (Pokemon p);

    public String firePunch (Pokemon p);

}