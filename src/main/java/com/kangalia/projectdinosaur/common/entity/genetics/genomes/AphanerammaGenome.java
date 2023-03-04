package com.kangalia.projectdinosaur.common.entity.genetics.genomes;

import com.kangalia.projectdinosaur.common.entity.genetics.BaseGenome;

public class AphanerammaGenome extends BaseGenome {

    //Total Genome - This is for spawn eggs and the full list which mutations draw from.
    public String CM = "AMN"; //Colour Morph (A=Albino, M=Melanistic, N=Normal)
    public String BC = "LBGD"; //Base Colour (L=Lime, B=Black, G=Green, D=Grey)
    public String UC = "CLG"; //Underside Colour (C=Cream, L=Lime, G=Green)
    public String PC = "BYRG"; //Pattern Colour (B=Blue, Y=Yellow, R=Red, G=Green)
    public String SP = "ABCDE"; //Speed (A=1, B=2, C=3, D=4, E=5)
    public String SZ = "ABCDE"; //Size (A=1, B=2, C=3, D=4, E=5)
    public String AD = "ABCDE"; //Attack Damage (A=1, B=2, C=3, D=4, E=5)
    public String HP = "ABCDE"; //Health (A=1, B=2, C=3, D=4, E=5)

    //Allowed Genome - This is for those first hatching from incubated eggs.
    public String CMA = "N"; //Colour Morph (N=Normal)
    public String BCA = "BGD"; //Base Colour (B=Black, G=Green, D=Grey)
    public String UCA = "LG"; //Underside Colour (L=Lime, G=Green)
    public String PCA = "BY"; //Pattern Colour (B=Blue, Y=Yellow)
    public String SPA = "BCD"; //Speed (B=2, C=3, D=4)
    public String SZA = "BCD"; //Size (B=2, C=3, D=4)
    public String ADA = "BCD"; //Attack Damage (B=2, C=3, D=4)
    public String HPA = "BCD"; //Health (B=2, C=3, D=4)

    public AphanerammaGenome() {
        haploidLengthMin = 8;
        haploidLengthMax = 10;
    }

    @Override
    public String setRandomGenes() {
        String haploid1 = stopCodon +
                CM.charAt(r.nextInt(CM.length())) +
                BC.charAt(r.nextInt(BC.length())) +
                UC.charAt(r.nextInt(UC.length())) +
                PC.charAt(r.nextInt(PC.length())) +
                SP.charAt(r.nextInt(SP.length())) +
                SZ.charAt(r.nextInt(SZ.length())) +
                AD.charAt(r.nextInt(AD.length())) +
                HP.charAt(r.nextInt(HP.length()));
        String haploid2 = stopCodon +
                CM.charAt(r.nextInt(CM.length())) +
                BC.charAt(r.nextInt(BC.length())) +
                UC.charAt(r.nextInt(UC.length())) +
                PC.charAt(r.nextInt(PC.length())) +
                SP.charAt(r.nextInt(SP.length())) +
                SZ.charAt(r.nextInt(SZ.length())) +
                AD.charAt(r.nextInt(AD.length())) +
                HP.charAt(r.nextInt(HP.length()));
        return haploid1 + haploid2;
    }

    @Override
    public String setRandomAllowedGenes() {
        String haploid1 = stopCodon +
                CMA.charAt(r.nextInt(CMA.length())) +
                BCA.charAt(r.nextInt(BCA.length())) +
                UCA.charAt(r.nextInt(UCA.length())) +
                PCA.charAt(r.nextInt(PCA.length())) +
                SPA.charAt(r.nextInt(SPA.length())) +
                SZA.charAt(r.nextInt(SZA.length())) +
                ADA.charAt(r.nextInt(ADA.length())) +
                HPA.charAt(r.nextInt(HPA.length()));
        String haploid2 = stopCodon +
                CMA.charAt(r.nextInt(CMA.length())) +
                BCA.charAt(r.nextInt(BCA.length())) +
                UCA.charAt(r.nextInt(UCA.length())) +
                PCA.charAt(r.nextInt(PCA.length())) +
                SPA.charAt(r.nextInt(SPA.length())) +
                SZA.charAt(r.nextInt(SZA.length())) +
                ADA.charAt(r.nextInt(ADA.length())) +
                HPA.charAt(r.nextInt(HPA.length()));
        return haploid1 + haploid2;
    }

    @Override
    public char mutateGenes(int index) {
        char allele;
        if (index == 0) {
            allele = CM.charAt(r.nextInt(CM.length()));
        } else if (index == 1) {
            allele = BC.charAt(r.nextInt(BC.length()));
        } else if (index == 2) {
            allele = UC.charAt(r.nextInt(UC.length()));
        } else if (index == 3) {
            allele = PC.charAt(r.nextInt(PC.length()));
        } else if (index == 4) {
            allele = SP.charAt(r.nextInt(SP.length()));
        } else if (index == 5) {
            allele = SZ.charAt(r.nextInt(SZ.length()));
        } else if (index == 6) {
            allele = AD.charAt(r.nextInt(AD.length()));
        } else {
            allele = HP.charAt(r.nextInt(HP.length()));
        }
        return allele;
    }

    // ALLELE DOMINANCE

    public String calculateDominanceBC(String alleles) {
        if (alleles.contains("B")) {
            return "Black";
        } else if (alleles.contains("D")) {
            return "Grey";
        } else if (alleles.contains("L")) {
            return "Lime";
        } else {
            return "Green";
        }
    }

    public String calculateDominanceUC(String alleles) {
        if (alleles.contains("L")) {
            return "Lime";
        } else if (alleles.contains("G")) {
            return "Green";
        } else {
            return "Cream";
        }
    }

    public String calculateDominancePC(String alleles) {
        if (alleles.contains("Y")) {
            return "Yellow";
        } else if (alleles.contains("B")) {
            return "Blue";
        } else if (alleles.contains("R")) {
            return "Red";
        }  else {
            return "Green";
        }
    }
}
