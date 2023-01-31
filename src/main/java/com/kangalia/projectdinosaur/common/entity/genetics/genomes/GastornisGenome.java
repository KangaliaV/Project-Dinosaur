package com.kangalia.projectdinosaur.common.entity.genetics.genomes;

import com.kangalia.projectdinosaur.core.util.RandomNumGen;

public class GastornisGenome {

    private static RandomNumGen r = new RandomNumGen();

    public String CM = "AMN"; //Colour Morph (A=Albino, M=Melanistic, N=Normal)
    public String FC = "WGCROB"; //Feather Colour (W=White, G=Grey, C=Cream, R=Red, O=Brown, B=Black)
    public String UC = "WGC"; //Underside Colour (W=White, G=Grey, C=Cream)
    public String PC = "WGCROB"; //Pattern Colour (W=White, G=Grey, C=Cream, R=Red, O=Brown, B=Black)
    public String HC = "RGB"; //Highlight Colour (R=Red, G=Green, B=Blue) *Only visible on males*
    public String SC = "CGOB"; //Skin Colour (C=Cream, G=Grey, O=Brown, B=Black)
    public String BC = "CYO"; //Beak Colour (C=Cream, Y=Yellow, O=Orange)
    public String SP = "ABCDE"; //Speed (A=1, B=2, C=3, D=4, E=5)
    public String SZ = "ABCDE"; //Size (A=1, B=2, C=3, D=4, E=5)
    public String AD = "ABCDE"; //Attack Damage (A=1, B=2, C=3, D=4, E=5)
    public String HP = "ABCDE"; //Health (A=1, B=2, C=3, D=4, E=5)

    public String setRandomGenes() {
        String haploid1 = "Z" +
                CM.charAt(r.nextInt(CM.length())) +
                FC.charAt(r.nextInt(FC.length())) +
                UC.charAt(r.nextInt(UC.length())) +
                PC.charAt(r.nextInt(PC.length())) +
                HC.charAt(r.nextInt(HC.length())) +
                SC.charAt(r.nextInt(SC.length())) +
                BC.charAt(r.nextInt(BC.length())) +
                SP.charAt(r.nextInt(SP.length())) +
                SZ.charAt(r.nextInt(SZ.length())) +
                AD.charAt(r.nextInt(AD.length())) +
                HP.charAt(r.nextInt(HP.length()));
        String haploid2 = "Z" +
                CM.charAt(r.nextInt(CM.length())) +
                FC.charAt(r.nextInt(FC.length())) +
                UC.charAt(r.nextInt(UC.length())) +
                PC.charAt(r.nextInt(PC.length())) +
                HC.charAt(r.nextInt(HC.length())) +
                SC.charAt(r.nextInt(SC.length())) +
                BC.charAt(r.nextInt(BC.length())) +
                SP.charAt(r.nextInt(SP.length())) +
                SZ.charAt(r.nextInt(SZ.length())) +
                AD.charAt(r.nextInt(AD.length())) +
                HP.charAt(r.nextInt(HP.length()));
        return haploid1 + haploid2;
    }

    public String setInheritedGenes(String parent1, String parent2) {
        if (parent1 == null || parent2 == null) {
            return setRandomGenes();
        }

        String haploid1 = "";
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < 11; i++) {
            char allele;
            if (shouldMutate()) {
                allele = mutateGenes(i);
            } else {
                String temp = "" + parent1.charAt(i + 1) + parent1.charAt(i + 13);
                r.nextInt(2);
                allele = temp.charAt(r.nextInt(temp.length()));
            }
            System.out.println(allele);
            haploid1 = builder.append(allele).toString();
            System.out.println(haploid1);
        }
        String haploid2 = "";
        StringBuilder builder1 = new StringBuilder();
        for (int i=0; i < 11; i++) {
            String temp1 = "" + parent2.charAt(i+1) + parent2.charAt(i+13);
            r.nextInt(2);
            char allele1 = temp1.charAt(r.nextInt(temp1.length()));
            System.out.println(allele1);
            haploid2 = builder1.append(allele1).toString();
            System.out.println(haploid2);
        }
        return "Z" + haploid1 + "Z" + haploid2;
    }

    public boolean shouldMutate() {
        int chance = r.nextInt(2); //To be 300
        return chance > 0;
    }

    public char mutateGenes(int index) {
        System.out.println("Mutated");
        char allele;
        if (index == 0) {
            allele = CM.charAt(r.nextInt(CM.length()));
        } else if (index == 1) {
            allele = FC.charAt(r.nextInt(FC.length()));
        } else if (index == 2) {
            allele = UC.charAt(r.nextInt(UC.length()));
        } else if (index == 3) {
            allele = PC.charAt(r.nextInt(PC.length()));
        } else if (index == 4) {
            allele = HC.charAt(r.nextInt(HC.length()));
        } else if (index == 5) {
            allele = SC.charAt(r.nextInt(SC.length()));
        } else if (index == 6) {
            allele = BC.charAt(r.nextInt(BC.length()));
        } else if (index == 7) {
            allele = SP.charAt(r.nextInt(SP.length()));
        } else if (index == 8) {
            allele = SZ.charAt(r.nextInt(SZ.length()));
        } else if (index == 9) {
            allele = AD.charAt(r.nextInt(AD.length()));
        } else {
            allele = HP.charAt(r.nextInt(HP.length()));
        }
        return allele;
    }

    public boolean isHomozygous(String genome, int index) {
        String s1a = genome.substring(0, (genome.length()/2));
        String s1b = genome.substring((genome.length()/2));
        char allele1 = s1a.charAt(index);
        char allele2 = s1b.charAt(index);
        return allele1 == allele2;
    }

    public boolean isAlbino(String genome) {
        if (!isHomozygous(genome, 1)) {
            return false;
        }
        String allele = String.valueOf(genome.charAt(1));
        return allele.equals("A");
    }

    public boolean isMelanistic(String genome) {
        if (!isHomozygous(genome, 1)) {
            return false;
        }
        String allele = String.valueOf(genome.charAt(1));
        return allele.equals("M");
    }

}
