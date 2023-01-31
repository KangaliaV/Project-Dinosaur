package com.kangalia.projectdinosaur.common.entity.genetics.alleles;

public class GastornisAlleles {

    //COLOUR ALLELES

    //Colour Morph (CM)
    public static final int ALBINO = 0; //All white
    public static final int MELANISTIC = 1; //All black/dark
    public static final int NORMAL = 2; //Affected by the genes below

    //Feather Colour (FC)
    public static final int F_WHITE = 0;
    public static final int F_GREY = 1;
    public static final int F_CREAM = 2;
    public static final int F_RED = 3;
    public static final int F_BROWN = 4;
    public static final int F_BLACK = 5;

    //Underside Colour (UC)
    public static final int U_WHITE = 0;
    public static final int U_GREY = 1;
    public static final int U_CREAM = 2;

    //Pattern Colour (PC)
    public static final int P_WHITE = 0;
    public static final int P_GREY = 1;
    public static final int P_CREAM = 2;
    public static final int P_RED = 3;
    public static final int P_BROWN = 4;
    public static final int P_BLACK = 5;

    //Highlight Colour (HC)
    public static final int H_RED = 0;
    public static final int H_GREEN = 1;
    public static final int H_BLUE = 2;

    //Skin Colour (SC)
    public static final int S_CREAM = 0;
    public static final int S_GREY = 1;
    public static final int S_BROWN = 2;
    public static final int S_BLACK = 3;

    //Beak Colour (BC)
    public static final int B_CREAM = 0;
    public static final int B_YELLOW = 1;
    public static final int B_ORANGE = 2;


    //CHARACTERISTIC ALLELES

    //Speed
    public static final int SPEED1 = 0; //Fastest
    public static final int SPEED2 = 0; //Slowest
    public static final int SPEED3 = 1;
    public static final int SPEED4 = 1;
    public static final int SPEED5 = 2; //Middle

    //Size
    public static final int SIZE1 = 0; //Smallest
    public static final int SIZE2 = 0; //Largest
    public static final int SIZE3 = 1; //Second Smallest
    public static final int SIZE4 = 1; //Second Largest
    public static final int SIZE5 = 2; //Middle

    //Attack Damage
    public static final int ATTACK1 = 0; //Lowest
    public static final int ATTACK2 = 0; //Highest
    public static final int ATTACK3 = 1; //Second Lowest
    public static final int ATTACK4 = 1; //Second Highest
    public static final int ATTACK5 = 2; //Middle

    //Health
    public static final int HEALTH1 = 10; //Lowest
    public static final int HEALTH2 = 40; //Highest
    public static final int HEALTH3 = 20; //Second Lowest
    public static final int HEALTH4 = 20; //Second Highest
    public static final int HEALTH5 = 30; //Middle
}
