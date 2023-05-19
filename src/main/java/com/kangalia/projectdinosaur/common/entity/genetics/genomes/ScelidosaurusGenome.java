package com.kangalia.projectdinosaur.common.entity.genetics.genomes;

import com.kangalia.projectdinosaur.common.entity.genetics.BaseGenome;

public class ScelidosaurusGenome extends BaseGenome {

    //Total Genome - This is for spawn eggs and the full list which mutations draw from.
    public String CM = "AMN"; //Colour Morph (A=Albino, M=Melanistic, N=Normal)
    public String BC = "OCBD"; //Base Colour (O=Brown, C=Cream, B=Black, D=Grey)
    public String UC = "CGO"; //Underside Colour (C=Cream, G=Grey, O=Brown)
    public String PC = "GWBRCY"; //Pattern Colour (G=Grey, W=White, B=Black, R=Red, C=Cyan, Y=Yellow)
    public String HC = "RGBY"; //Highlight Colour (R=Red, G=Green, B=Blue, Y=Yellow) *Only visible on males*
    public String SP = "ABCDE"; //Speed (A=1, B=2, C=3, D=4, E=5)
    public String SZ = "ABCDE"; //Size (A=1, B=2, C=3, D=4, E=5)
    public String AD = "ABCDE"; //Attack Damage (A=1, B=2, C=3, D=4, E=5)
    public String HP = "ABCDE"; //Health (A=1, B=2, C=3, D=4, E=5)

    //Allowed Genome - This is for those first hatching from incubated eggs.
    public String CMA = "N"; //Colour Morph (N=Normal)
    public String BCA = "OD"; //Base Colour (O=Brown, D=Grey)
    public String UCA = "CG"; //Underside Colour (C=Cream, G=Grey)
    public String PCA = "GBY"; //Pattern Colour (G=Grey, B=Black, Y=Yellow)
    public String HCA = "BY"; //Highlight Colour (B=Blue, Y=Yellow) *Only visible on males*
    public String SPA = "BCD"; //Speed (B=2, C=3, D=4)
    public String SZA = "BCD"; //Size (B=2, C=3, D=4)
    public String ADA = "BCD"; //Attack Damage (B=2, C=3, D=4)
    public String HPA = "BCD"; //Health (B=2, C=3, D=4)

    public ScelidosaurusGenome() {
        haploidLengthMin = 9;
        haploidLengthMax = 11;
    }

    @Override
    public String setRandomGenes() {
        String haploid1 = stopCodon +
                CM.charAt(r.nextInt(CM.length())) +
                BC.charAt(r.nextInt(BC.length())) +
                UC.charAt(r.nextInt(UC.length())) +
                PC.charAt(r.nextInt(PC.length())) +
                HC.charAt(r.nextInt(HC.length())) +
                SP.charAt(r.nextInt(SP.length())) +
                SZ.charAt(r.nextInt(SZ.length())) +
                AD.charAt(r.nextInt(AD.length())) +
                HP.charAt(r.nextInt(HP.length()));
        String haploid2 = stopCodon +
                CM.charAt(r.nextInt(CM.length())) +
                BC.charAt(r.nextInt(BC.length())) +
                UC.charAt(r.nextInt(UC.length())) +
                PC.charAt(r.nextInt(PC.length())) +
                HC.charAt(r.nextInt(HC.length())) +
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
                HCA.charAt(r.nextInt(HCA.length())) +
                SPA.charAt(r.nextInt(SPA.length())) +
                SZA.charAt(r.nextInt(SZA.length())) +
                ADA.charAt(r.nextInt(ADA.length())) +
                HPA.charAt(r.nextInt(HPA.length()));
        String haploid2 = stopCodon +
                CMA.charAt(r.nextInt(CMA.length())) +
                BCA.charAt(r.nextInt(BCA.length())) +
                UCA.charAt(r.nextInt(UCA.length())) +
                PCA.charAt(r.nextInt(PCA.length())) +
                HCA.charAt(r.nextInt(HCA.length())) +
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
            allele = HC.charAt(r.nextInt(HC.length()));
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
        if (alleles.contains("O")) {
            return "Brown";
        } else if (alleles.contains("D")) {
            return "Grey";
        } else if (alleles.contains("B")) {
            return "Black";
        } else {
            return "Cream";
        }
    }

    public String calculateDominanceUC(String alleles) {
        if (alleles.contains("C")) {
            return "Cream";
        } else if (alleles.contains("G")) {
            return "Grey";
        } else {
            return "Brown";
        }
    }

    public String calculateDominancePC(String alleles) {
        if (alleles.contains("G")) {
            return "Grey";
        } else if (alleles.contains("B")) {
            return "Black";
        } else if (alleles.contains("Y")) {
            return "Yellow";
        } else if (alleles.contains("W")) {
            return "White";
        } else if (alleles.contains("R")) {
            return "Red";
        } else {
            return "Cyan";
        }
    }

    public String calculateDominanceHC(String alleles) {
        if (alleles.contains("B")) {
            return "Blue";
        } else if (alleles.contains("G")) {
            return "Green";
        } else if (alleles.contains("Y")) {
            return "Yellow";
        } else {
            return "Red";
        }
    }
}
