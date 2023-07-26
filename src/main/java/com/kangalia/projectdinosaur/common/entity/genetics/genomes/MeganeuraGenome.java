package com.kangalia.projectdinosaur.common.entity.genetics.genomes;

import com.kangalia.projectdinosaur.common.entity.genetics.BaseGenome;

public class MeganeuraGenome extends BaseGenome {

    //Total Genome - This is for spawn eggs and the full list which mutations draw from.
    public String CM = "AMN"; //Colour Morph (A=Albino, M=Melanistic, N=Normal)
    public String BC = "BCGO"; //Base Colour (B=Black, C=Cream, G=Green, O=Blue)
    public String TC = "COG"; //Tail Colour (C=Cream, O=Brown, G=Green)
    public String PC = "BYRG"; //Pattern Colour (B=Blue, Y=Yellow, R=Red, G=Green)
    public String WC = "WBYR"; //Wing Colour (W=White, B=Blue, Y=Yellow, R=Red)
    public String SP = "ABCDE"; //Speed (A=1, B=2, C=3, D=4, E=5)
    public String SZ = "ABCDE"; //Size (A=1, B=2, C=3, D=4, E=5)
    public String AD = "ABCDE"; //Attack Damage (A=1, B=2, C=3, D=4, E=5)
    public String HP = "ABCDE"; //Health (A=1, B=2, C=3, D=4, E=5)

    //Allowed Genome - This is for those first hatching from incubated eggs.
    public String CMA = "N"; //Colour Morph (N=Normal)
    public String BCA = "BO"; //Base Colour (B=Black, O=Brown)
    public String TCA = "C"; //Tail Colour (C=Cream)
    public String PCA = "BG"; //Pattern Colour (B=Blue, G=Green)
    public String WCA = "WB"; //Wing Colour (S=Small, L=Large)
    public String SPA = "BCD"; //Speed (A=1, B=2, C=3, D=4, E=5)
    public String SZA = "BCD"; //Size (A=1, B=2, C=3, D=4, E=5)
    public String ADA = "BCD"; //Attack Damage (B=2, C=3, D=4)
    public String HPA = "BCD"; //Health (A=1, B=2, C=3, D=4, E=5)

    public MeganeuraGenome() {
        haploidLengthMin = 9;
        haploidLengthMax = 11;
    }

    @Override
    public String setRandomGenes() {
        String haploid1 = stopCodon +
                CM.charAt(r.nextInt(CM.length())) +
                BC.charAt(r.nextInt(BC.length())) +
                TC.charAt(r.nextInt(TC.length())) +
                PC.charAt(r.nextInt(PC.length())) +
                WC.charAt(r.nextInt(WC.length())) +
                SP.charAt(r.nextInt(SP.length())) +
                SZ.charAt(r.nextInt(SZ.length())) +
                AD.charAt(r.nextInt(AD.length())) +
                HP.charAt(r.nextInt(HP.length()));
        String haploid2 = stopCodon +
                CM.charAt(r.nextInt(CM.length())) +
                BC.charAt(r.nextInt(BC.length())) +
                TC.charAt(r.nextInt(TC.length())) +
                PC.charAt(r.nextInt(PC.length())) +
                WC.charAt(r.nextInt(WC.length())) +
                SP.charAt(r.nextInt(SP.length())) +
                AD.charAt(r.nextInt(AD.length())) +
                SZ.charAt(r.nextInt(SZ.length())) +
                HP.charAt(r.nextInt(HP.length()));
        return haploid1 + haploid2;
    }

    @Override
    public String setRandomAllowedGenes() {
        String haploid1 = stopCodon +
                CMA.charAt(r.nextInt(CMA.length())) +
                BCA.charAt(r.nextInt(BCA.length())) +
                TCA.charAt(r.nextInt(TCA.length())) +
                PCA.charAt(r.nextInt(PCA.length())) +
                WCA.charAt(r.nextInt(WCA.length())) +
                SPA.charAt(r.nextInt(SPA.length())) +
                SZA.charAt(r.nextInt(SZA.length())) +
                ADA.charAt(r.nextInt(ADA.length())) +
                HPA.charAt(r.nextInt(HPA.length()));
        String haploid2 = stopCodon +
                CMA.charAt(r.nextInt(CMA.length())) +
                BCA.charAt(r.nextInt(BCA.length())) +
                TCA.charAt(r.nextInt(TCA.length())) +
                PCA.charAt(r.nextInt(PCA.length())) +
                WCA.charAt(r.nextInt(WCA.length())) +
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
            allele = TC.charAt(r.nextInt(TC.length()));
        } else if (index == 3) {
            allele = PC.charAt(r.nextInt(PC.length()));
        } else if (index == 4) {
            allele = WC.charAt(r.nextInt(WC.length()));
        } else if (index == 5) {
            allele = SP.charAt(r.nextInt(SP.length()));
        } else if (index == 6) {
            allele = SZ.charAt(r.nextInt(SZ.length()));
        } else if (index == 7) {
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
        } else if (alleles.contains("O")) {
            return "Blue";
        } else if (alleles.contains("C")) {
            return "Cream";
        } else {
            return "Green";
        }
    }

    public String calculateDominanceTC(String alleles) {
        if (alleles.contains("O")) {
            return "Brown";
        } else if (alleles.contains("G")) {
            return "Green";
        } else {
            return "Cream";
        }
    }

    public String calculateDominancePC(String alleles) {
        if (alleles.contains("B")) {
            return "Blue";
        } else if (alleles.contains("Y")) {
            return "Yellow";
        } else if (alleles.contains("R")) {
            return "Red";
        }  else {
            return "Green";
        }
    }

    public String calculateDominanceWC(String alleles) {
        if (alleles.contains("B")) {
            return "Blue";
        } else if (alleles.contains("Y")) {
            return "Yellow";
        } else if (alleles.contains("R")) {
            return "Red";
        } else {
            return "White";
        }
    }
}
