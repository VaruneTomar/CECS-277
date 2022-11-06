package com.company;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;

// Group Member #1 - Jack Pickle
// Group Member #2 - Varune Tomar
// Group Member #3 - Randy Mondragon

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Pokemon starterPok = null;
        // declare scanner

        //ask for trainer name + get name
        System.out.println("Hello trainer, please enter your name!");
        String trainerName = CheckInput.getString();

        System.out.println("Great to meet you " + trainerName);
        //ask for starter choice + store

        boolean PokchoiceBool = true;

        while (PokchoiceBool == true) {
            System.out.println("Choose your pokemon\n1. Charmander\n2. Squirtle\n3. Bulbasaur");

            int starterPokChoice = CheckInput.getIntRange(1, 3);

            if (starterPokChoice == 1 || starterPokChoice == 2 || starterPokChoice == 3) {
                PokchoiceBool = false;
                if (starterPokChoice == 1) {
                    starterPok = new Charmander();
                } else if (starterPokChoice == 2) {
                    starterPok = new Squirtle();
                } else if (starterPokChoice == 3) {
                    starterPok = new Bulbasaur();
                }
            } else {
                System.out.println("You entered incorrectly, try again");
            }
        }

        //declaring map and trainer objects
        Map map = new Map();
        map.loadMap(1);
        int mapnumber = 1;

        //creating a trainer and passing in paramaters
        Trainer trainer = new Trainer(trainerName, starterPok, map);

        Boolean quitprogram = true;

        //creating variables to be used later on
        Point userLoc;
        Point prevLoc = null;
        char userChar = ' ';
        boolean validDirect = true;
        boolean ranaway = true;

        //while loop runs until user quits, reaches finish, or has zero hp
        while (quitprogram) {
            int directionchoice = 1;
            System.out.println(trainer.toString());

            //implementing running away which is called when the user chooses to run away from a wild pokemon they encounter
            if(!ranaway){
                if (trainer.goNorth() != 'w'){
                    trainer.goSouth();
                    directionchoice = 1;
                } else if (trainer.goSouth() != 'w'){
                    trainer.goNorth();
                    directionchoice = 2;
                } else if (trainer.goEast() != 'w'){
                    trainer.goWest();
                    directionchoice = 3;
                } else if (trainer.goWest() != 'w'){
                    trainer.goEast();
                    directionchoice = 4;
                }

                ranaway = true;
            } else {

                directionchoice = mainMenu();
            }


            validDirect = true;

            if (trainer.getHp() < 1){
                quitprogram = false;
                validDirect = false;
                System.out.println("You have zero hp, you can't continue");
            }

            //will go certain direction based off their input
            if (directionchoice == 1) {
                prevLoc = trainer.getLoc();

                Double prevnumX = prevLoc.getX();

                Double prevnumY = prevLoc.getY();

                userChar = trainer.goNorth();
                userLoc = trainer.getLoc();

                Double usernumX = userLoc.getX();

                Double usernumY = userLoc.getY();

                //tells the user they can't go that way depending on their position and direction they choose
                if (prevnumY.equals(usernumY) && usernumX.equals(prevnumX)) {
                    System.out.println("You cannot go that way");

                    validDirect = false;
                }

            } else if (directionchoice == 2) {
                prevLoc = trainer.getLoc();

                Double prevnumX = prevLoc.getX();

                Double prevnumY = prevLoc.getY();

                userChar = trainer.goSouth();
                userLoc = trainer.getLoc();

                Double usernumX = userLoc.getX();

                Double usernumY = userLoc.getY();

                if (prevnumY.equals(usernumY) && usernumX.equals(prevnumX)) {
                    System.out.println("You cannot go that way");
                    validDirect = false;
                }

            } else if (directionchoice == 3) {
                prevLoc = trainer.getLoc();

                Double prevnumX = prevLoc.getX();

                Double prevnumY = prevLoc.getY();

                userChar = trainer.goEast();
                userLoc = trainer.getLoc();

                Double usernumX = userLoc.getX();

                Double usernumY = userLoc.getY();

                if (prevnumY.equals(usernumY) && usernumX.equals(prevnumX)) {
                    System.out.println("You cannot go that way");
                    validDirect = false;
                }
            } else if (directionchoice == 4) {
                prevLoc = trainer.getLoc();

                Double prevnumX = prevLoc.getX();

                Double prevnumY = prevLoc.getY();

                userChar = trainer.goWest();

                userLoc = trainer.getLoc();

                Double usernumX = userLoc.getX();

                Double usernumY = userLoc.getY();

                if (prevnumY.equals(usernumY) && usernumX.equals(prevnumX)) {
//                    ranaway = false;
                    System.out.println("You cannot go that way");

                    validDirect = false;
                } //user chooses to quit and end the program
            } else if (directionchoice == 5) {
                quitprogram = false;
                validDirect = false;
                System.out.println("Sorry to see you go, thanks for playing!");

            } //different instructions based off what the user encounters on the map
            if (validDirect && userChar == 'n') {
                System.out.println("There is nothing here...");

            } if (validDirect && userChar == 's') {
                System.out.println("You are back at the start, nothing here!");

            } if (validDirect && userChar == 'i') {
                Random random = new Random();
                int randItem = random.nextInt(3 - 1) + 1;

                if (randItem == 1){
                    System.out.println("You found a potion");
                    trainer.receivePotion();

                } else {
                    System.out.println("You found a poke ball");
                    trainer.receivePokeball();
                }


            } if (validDirect && userChar == 'w') {

                Pokemon wild = chooseRandomPokemon();
                System.out.println("A wild " + wild.getName() + " has appeared.");

                boolean wildloop = true;
                while (wildloop) {


                    System.out.println(wild);

                    System.out.println(wild.getBasicMenu());

                    int basicMenuChoice = CheckInput.getIntRange(1, 4);

                    if (basicMenuChoice == 1) {
                        //user chooses to fight, thus it calls the trainer attack method
                        trainerAttack(trainer, wild);

                    } else if (basicMenuChoice == 2) {
                        if (trainer.hasPotion()) {
                            System.out.println("Which Pokemon would you like to heal?");
                            System.out.println(trainer.getPokemonList());
                            int potionChoice = CheckInput.getIntRange(1, trainer.getNumPokemon());
                            if (trainer.getPokemon(potionChoice - 1).getHp() > 0) {
                                trainer.usePotion(potionChoice - 1);
                                System.out.println(trainer.getPokemon(potionChoice - 1).getName() + " has been healed!");
                            } else {
                                System.out.println("Your pokemon can't be healed since it fainted");
                            }
                        } else if (!trainer.hasPotion()) {
                            System.out.println("You have ran out of potions! Find/buy more to heal up your pokemon on the go!");

                        }

                    } else if (basicMenuChoice == 3) {
                        if (trainer.hasPokeball()) {
                            boolean catchresult = trainer.catchPokemon(wild);
                            if (catchresult) {
                                wildloop = false;
                                map.removeCharAtLoc(prevLoc);
                            }
                        } else {
                            System.out.println("You have ran out of PokeBalls! Find/buy more to try to catch more pokemon!");
                        }
                    } else if (basicMenuChoice == 4) {
                        ranaway = false;
                        wildloop = false;
                    } else {
                        System.out.println("Invalid Option. Please Try Again.");
                        System.out.println(wild.getBasicMenu());
                    }
                    if (wild.getHp() == 0) {
                        System.out.println("You defeated " + wild.getName());
                        map.removeCharAtLoc(prevLoc);
                        wildloop = false;
                    }
                }
//'''FIX MORE'''

            } if (validDirect && userChar == 'p') {
                Random random = new Random();
                int randinteraction = random.nextInt(8) + 1;

                if (randinteraction == 1) {
                    System.out.println("You found Misty!");
                    System.out.println("Why'd you break my bike!!!");
                    System.out.println("Misty slaps you for 5 damage");
                    trainer.takeDamage(5);
                    // Misty should slap trainer for 5 damage.
                }
                if (randinteraction == 2) {
                    System.out.println("You found Brock! He is holding a frying pan and muttering to himself.");
                    System.out.println("You decide to walk away slowly...");
                }
                if (randinteraction == 3) {
                    if (trainer.spendMoney(5)){
                        System.out.println("Uh oh - You encoutered Team Rocket.");
                        System.out.println("Prepare for trouble...");
                        System.out.println("And make it double!");
                        System.out.println("Team Rocket stole 5 dollars straight from your bag!");
                    } else {
                        System.out.println("You ran into Team Rocket.");
                        System.out.println("They aren't looking for trouble right now,");
                        System.out.println("but next time they see you, expect problems.");
                    }
                }
                if (randinteraction == 4) {
                    System.out.println("You ran into Dustin, who still owed you $10.");
                    System.out.println("He pulls out his wallet and pays you back");
                    trainer.receiveMoney(10);
                }
                if (randinteraction == 5) {
                    System.out.println("You found Justin who gifts you a potion for helping him last week.");
                    trainer.receivePotion();
                }
                if (randinteraction == 6) {
                    System.out.println("Yikes, you bumped into John and he is not happy.");
                    System.out.println("He throws a furious punch at you for 7 damage.");
                    System.out.println("Then he mysteriously walks away...");
                    trainer.takeDamage(7);

                } if (randinteraction == 7) {
                    System.out.println("You approach a complete stranger who looks suspicious.");
                    System.out.println("He threatens you, but doesn't actually do anything.");
                    System.out.println("You just ignore him until he leaves.");
                    trainer.takeDamage(7);
                }
                if (randinteraction == 8) {
                    System.out.println("You meet Lucy, she hasn't seen you in a long time.");
                    System.out.println("She invites you to grab some food and catch up on Saturday.");
                    System.out.println("Lucy: I got to go, call me if your free that day.");
                }

            } if (validDirect && userChar == 'c') {
                System.out.println("You've entered the city.\nWhere would you like to go? \n 1. Store \n 2. Pokemon Hospital ");
                int cityChoice = CheckInput.getIntRange(1,2);
                if (cityChoice == 1){
                    store(trainer);
                }
                else if (cityChoice == 2){
                    System.out.println("Hello! Welcome to the Pokemon Hospital. I'll fix your poor pokemon up in a jiffy.");
                    trainer.healAllPokemon();
                    System.out.println("There you go! See you again soon.");
                }

            } if (validDirect && userChar == 'f') {
                System.out.println("You've found the finish...");
                if (mapnumber < 3) {
                    map.loadMap(mapnumber + 1);
                    mapnumber += 1;
                } else {
                    System.out.println("Game Over!");
                    quitprogram = false;
                }
            }
        }
    }
    /**
     * Prints the main menu of movements
     *@return choice
     */
    public static int mainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Go North");
        System.out.println("2. Go South");
        System.out.println("3. Go East");
        System.out.println("4. Go West");
        System.out.println("5. Quit");
        int choice = CheckInput.getIntRange(1, 5);
        return choice;
    }
    /**
     * chooses a random pokemon
     * creates a list of all 6 pokemon, then returns a random one from that list
     * @return poke.get(randValue)
     */
    public static Pokemon chooseRandomPokemon() {
        Random random = new Random();
        int randValue = random.nextInt(6);

        ArrayList<Pokemon> poke = new ArrayList<Pokemon>();

        Bulbasaur p1 = new Bulbasaur();
        Charmander p2 = new Charmander();
        Squirtle p3 = new Squirtle();
        Oddish p4 = new Oddish();
        Ponyta p5 = new Ponyta();
        Staryu p6 = new Staryu();

        poke.add(p1);
        poke.add(p2);
        poke.add(p3);
        poke.add(p4);
        poke.add(p5);
        poke.add(p6);

        return poke.get(randValue);
    }
    /**
     * contains all battle choices and interacitons with wild pokemon.
     * has random counter attacks for wild pokemon to execute
     */
    public static void trainerAttack(Trainer t, Pokemon wild) {
        System.out.println("Choose a Pokemon:");
        System.out.println(t.getPokemonList());

        int pokeFightChoice = CheckInput.getIntRange(1, t.getNumPokemon()) - 1;
        // ADD POKEMON NAME HERE
        String pokeName = t.getPokemon(pokeFightChoice).getName();
        System.out.println(pokeName + ", I choose you!");
        System.out.println("1. Basic Attack\n2. Special Attack");
        int attackTypeChoice = CheckInput.getIntRange(1, 2);
        int pokemonhealth = t.getPokemon(pokeFightChoice).getHp();

        //will perform basic or special attack on the wild pokemon based off what the user chooses
        if (attackTypeChoice == 1 && pokemonhealth > 0) {
            System.out.println(wild.getAttackMenu());
            int attackChoice = CheckInput.getIntRange(1, 3);
            if (attackChoice == 1) {
                System.out.print(t.getPokemon(pokeFightChoice).slam(wild));
            } else if (attackChoice == 2) {
                System.out.print(t.getPokemon(pokeFightChoice).tackle(wild));
            } else if (attackChoice == 3) {
                System.out.print(t.getPokemon(pokeFightChoice).punch(wild));
            }

        } else if (attackTypeChoice == 2 && pokemonhealth > 0) {
            System.out.println(t.getPokemon(pokeFightChoice).getSpecialMenu());
            int attackChoice = CheckInput.getIntRange(1, 3);
            if (attackChoice == 1) {
                System.out.print(t.getPokemon(pokeFightChoice).specialAttack(wild, 1));
            } else if (attackChoice == 2) {
                System.out.print(t.getPokemon(pokeFightChoice).specialAttack(wild, 2));
            } else if (attackChoice == 3) {
                System.out.print(t.getPokemon(pokeFightChoice).specialAttack(wild, 3));
            }
        } else {
            System.out.println("Your Pokemon fainted, thus it can't fight");
        }

        //generating random counter attack by the wild pokemon
        Random randAttack = new Random();
        int randCounter = randAttack.nextInt(2) + 1;

        if (pokemonhealth < 1){
            randCounter = 1;
        }
        int randtype = randAttack.nextInt(3) + 1;

        if (randCounter == 1) {
            if (randtype == 1) {
                if (t.getPokemon(pokeFightChoice).getHp() == 0) {
                    int trainerdamage = randAttack.nextInt(6);
                    t.takeDamage(trainerdamage);

                    System.out.println(wild.getName() + " slams you for " + trainerdamage + " damage");
                } else if (wild.getHp() > 0){
                    System.out.println(wild.basicAttack(t.getPokemon(pokeFightChoice), 1));
                }
            }
            else if (randtype == 2) {
                if (t.getPokemon(pokeFightChoice).getHp() == 0) {
                    int trainerdamage = randAttack.nextInt(4) + 1;
                    t.takeDamage(trainerdamage);

                    System.out.println(wild.getName() + " hits you with a punch for " + trainerdamage + " damage");
                } else if (wild.getHp() > 0){
                    System.out.println(wild.basicAttack(t.getPokemon(pokeFightChoice), 2));
                }

            }else {
                if (t.getPokemon(pokeFightChoice).getHp() == 0) {
                    int trainerdamage = randAttack.nextInt(2) + 2;
                    t.takeDamage(trainerdamage);

                    System.out.println(wild.getName() + " tackles you for " + trainerdamage + " damage");
                } else if (wild.getHp() > 0) {
                    System.out.println(wild.basicAttack(t.getPokemon(pokeFightChoice), 3));
                }
            }
        } else {
            //special attack counter attack by the wild pokemon
            if (randtype == 1 && wild.getHp() > 0) {
                System.out.println(wild.specialAttack(t.getPokemon(pokeFightChoice), 1));
            }
            if (randtype == 2 && wild.getHp() > 0) {
                System.out.println(wild.specialAttack(t.getPokemon(pokeFightChoice), 2));
            }
            if (randtype == 3 && wild.getHp() > 0) {
                System.out.println(wild.specialAttack(t.getPokemon(pokeFightChoice), 3));

            }
            //pokemon fainted if it has zero hp
        } if (t.getPokemon(pokeFightChoice).getHp() < 1){
            System.out.println(t.getPokemon(pokeFightChoice) + " fainted");
        }
    }
    /**
     * lets you use money in trainer to buy potions and pokeballs.
     * @param t
     */
    public static void store (Trainer t) {
        System.out.println("Hello! What can I help you with?\n1. Buy a Potion - $5\n2. Buy a PokeBall - $3\n3. Exit");

        int storechoice = CheckInput.getIntRange(1, 3);

        while (storechoice != 3) {
            if (storechoice == 1) {
                if (t.spendMoney(5)) {
                    t.receivePotion();
                    System.out.println("Here is your Potion");
                } else{
                    System.out.println(t.getName() + " - Sorry my mistake, I don't have enough money");
                }


            } else if (storechoice == 2) {
                if (t.spendMoney(3)) {
                    t.receivePokeball();
                    System.out.println("Here is your Pokeball");
                } else {
                    System.out.println(t.getName() + " - Sorry my mistake, I don't have enough money");
                }

            }
            System.out.println("Owner - Anything else I can help you with?\n1. Buy a Potion - $5\n2. Buy a PokeBall - $3\n3. Exit");
//
            storechoice = CheckInput.getIntRange(1, 3);
        } System.out.println("Owner - Thank you, come again soon!");
    }

}




