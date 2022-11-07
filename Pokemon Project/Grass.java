package com.company;

/**
 *
 * This is the interface for all pokemon of the Grass type
 *
 * @author Randy
 *
 */
public interface Grass {
    /**
     * Displays choice of special moves for type Grass.
     */
    public String specialMenu = "1. Vine Whip\n2. Razor Leaf\n3. Solar Beam";

    public int numSpecialMenuItems = 3;

    public String vineWhip(Pokemon p);

    public String razorLeaf(Pokemon p);

    public String solarBeam(Pokemon p);
}
