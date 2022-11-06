package com.company;

/**
 *
 * This is the interface for all pokemon of the Water type
 *
 * @author Varune Tomar
 *
 */
public interface Water {
    /**
     * Displays choice of special moves for type Water.
     */
    public String specialMenu = "1. Water Gun\n2. Bubble Beam\n3. Waterfall";
    public int numSpecialMenuItems = 3;

    public String watergun (Pokemon p);

    public String bubbleBeam (Pokemon p);

    public String waterfall (Pokemon p);

}
