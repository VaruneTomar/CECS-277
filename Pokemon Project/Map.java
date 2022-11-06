package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.awt.*;
/**
 *
 * This is the map class, containing all methods involving the map itself.
 *
 * @author MyName
 *
 */
public class Map {
    /**
     * Constructs a Map object.
     */
    private char[][] map;
    private boolean[][] revealed;

    public Map() {
        map = new char[5][5];
        revealed = new boolean[5][5];

    }
    /**
     *
     * Depending on the area the trainer is on, load that area's map
     * @param mapNum
     * @throws FileNotFoundException
     *
     */
    public void loadMap(int mapNum) throws FileNotFoundException {
        if (mapNum == 1) {
            File areafile = new File("Area1.txt");
            Scanner fileScan = new Scanner(areafile);
            int i = 0;

            while (fileScan.hasNextLine()) {
                String currentline = fileScan.nextLine().replaceAll("\\s", "");
                char[] chararray = currentline.toCharArray();

                for (int y = 0; y < 5; y++) {
                    map[i][y] = chararray[y];

                }
                i += 1;
            }

        } else if (mapNum == 2) {
            map = new char[5][5];
            revealed = new boolean[5][5];

            File areafile = new File("Area2.txt");
            Scanner fileScan = new Scanner(areafile);
            int i = 0;

            while (fileScan.hasNextLine()) {
                String currentline = fileScan.nextLine().replaceAll("\\s", "");
                char[] chararray = currentline.toCharArray();

                for (int y = 0; y < 5; y++) {
                    map[i][y] = chararray[y];

                }
                i += 1;
            }

        } else if (mapNum == 3) {
            map = new char[5][5];
            revealed = new boolean[5][5];

            File areafile = new File("Area3.txt");
            Scanner fileScan = new Scanner(areafile);
            int i = 0;

            while (fileScan.hasNextLine()) {
                String currentline = fileScan.nextLine().replaceAll("\\s", "");
                char[] chararray = currentline.toCharArray();

                for (int y = 0; y < 5; y++) {
                    map[i][y] = chararray[y];

                }
                i += 1;
            }
        }

    }
    /**
     *
     * returns character a point p
     * @param P
     * @return map[x][y]
     *
     */
    public char getCharAtLoc(Point P) {
        int x = (int) P.getX();
        int y = (int) P.getY();

        return map[x][y];

    }
    /**
     *
     * overwrite of toString for map
     * the map string gets return with undiscovered spaces covered with xs, the trainers location marked with *, and revlead spaces marked with the coresponding character
     * @param P
     * @return mapstr
     *
     */
    public String mapToString(Point P) {
        String mapstr = "";
        int xvalue = (int) P.getX();
        int yvalue = (int) P.getY();

        for (int i = 0; i < 5; i++) {
            for (int y = 0; y < 5; y++) {
                if (revealed[i][y] == true ) {
                    if (xvalue == i && yvalue == y) {
                        mapstr += "* ";
                    } else {
                        mapstr += "" + map[i][y] + " ";
                    }

                } else if (i == P.getX() && y == P.getY()) {
                    mapstr += "* ";

                } else {
                    mapstr += "x ";

                }
                if (y == 4) {
                    mapstr += "\n";
                }
            }

        }
        return mapstr;


    }
    /**
     * Finds the start of an area, and returns it.
     * @return nonpoint
     */
    public Point findStart() {
        Point Nonpoint = new Point(0, 0);
        for (int i = 0; i < 5; i++) {
            for (int y = 0; y < 5; y++) {
                if (map[i][y] == 's') {
                    Point startpoint = new Point(i, y);
                    return startpoint;
                }
            }
        }
        return Nonpoint;

    }
    /**
     * Reveals point P, so changes it from an x to its true icon.
     * @param P
     */
    public void reveal(Point P) {
        int x = (int) P.getX();
        int y = (int) P.getY();

        revealed[x][y] = true;

    }
    /**
     * Removes char at point P, and replaces with netscape
     * used when thing has been interacted with
     * ex: item on ground picked up
     * @param P
     */
    public void removeCharAtLoc(Point P) {
        int x = (int) P.getX();
        int y = (int) P.getY();

        if (map[x][y] != 's'){
            map[x][y] = 'n';
        }
    }
}
