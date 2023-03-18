package com.kangalia.projectdinosaur.common.entity.genetics.genomes;

import com.kangalia.projectdinosaur.common.entity.genetics.BaseGenome;

public class TrilobiteGenome extends BaseGenome {

    //Total Genome - This is for spawn eggs and the full list which mutations draw from.
    public String CM = "AMN"; //Colour Morph (A=Albino, M=Melanistic, N=Normal)
    public String BC = "BCGO"; //Base Colour (B=Black, C=Cream, G=Green, O=Brown)
    public String UC = "COG"; //Underside Colour (C=Cream, O=Brown, G=Green)
    public String PT = "NTS"; //Pattern Type (N=None, T=Stripes, S=Spine)
    public String PC = "BYRG"; //Pattern Colour (B=Blue, Y=Yellow, R=Red, G=Green)
    public String HT = "SL"; //Horn Type (S=Small, L=Large)
    public String SP = "ABCDE"; //Speed (A=1, B=2, C=3, D=4, E=5)
    public String SZ = "ABCDE"; //Size (A=1, B=2, C=3, D=4, E=5)
    public String HP = "ABCDE"; //Health (A=1, B=2, C=3, D=4, E=5)

    //Allowed Genome - This is for those first hatching from incubated eggs.
    public String CMA = "N"; //Colour Morph (N=Normal)
    public String BCA = "BO"; //Base Colour (B=Black, O=Brown)
    public String UCA = "C"; //Underside Colour (C=Cream)
    public String PTA = "NS"; //Pattern Type (N=None, S=Spine)
    public String PCA = "BG"; //Pattern Colour (B=Blue, G=Green)
    public String HTA = "SL"; //Horn Type (S=Small, L=Large)
    public String SPA = "BCD"; //Speed (A=1, B=2, C=3, D=4, E=5)
    public String SZA = "BCD"; //Size (A=1, B=2, C=3, D=4, E=5)
    public String HPA = "BCD"; //Health (A=1, B=2, C=3, D=4, E=5)

    public TrilobiteGenome() {
        haploidLengthMin = 9;
        haploidLengthMax = 11;
    }

    @Override
    public String setRandomGenes() {
        String haploid1 = stopCodon +
                CM.charAt(r.nextInt(CM.length())) +
                BC.charAt(r.nextInt(BC.length())) +
                UC.charAt(r.nextInt(UC.length())) +
                PT.charAt(r.nextInt(PT.length())) +
                PC.charAt(r.nextInt(PC.length())) +
                HT.charAt(r.nextInt(HT.length())) +
                SP.charAt(r.nextInt(SP.length())) +
                SZ.charAt(r.nextInt(SZ.length())) +
                HP.charAt(r.nextInt(HP.length()));
        String haploid2 = stopCodon +
                CM.charAt(r.nextInt(CM.length())) +
                BC.charAt(r.nextInt(BC.length())) +
                UC.charAt(r.nextInt(UC.length())) +
                PT.charAt(r.nextInt(PT.length())) +
                PC.charAt(r.nextInt(PC.length())) +
                HT.charAt(r.nextInt(HT.length())) +
                SP.charAt(r.nextInt(SP.length())) +
                SZ.charAt(r.nextInt(SZ.length())) +
                HP.charAt(r.nextInt(HP.length()));
        return haploid1 + haploid2;
    }

    @Override
    public String setRandomAllowedGenes() {
        String haploid1 = stopCodon +
                CMA.charAt(r.nextInt(CMA.length())) +
                BCA.charAt(r.nextInt(BCA.length())) +
                UCA.charAt(r.nextInt(UCA.length())) +
                PTA.charAt(r.nextInt(PTA.length())) +
                PCA.charAt(r.nextInt(PCA.length())) +
                HTA.charAt(r.nextInt(HTA.length())) +
                SPA.charAt(r.nextInt(SPA.length())) +
                SZA.charAt(r.nextInt(SZA.length())) +
                HPA.charAt(r.nextInt(HPA.length()));
        String haploid2 = stopCodon +
                CMA.charAt(r.nextInt(CMA.length())) +
                BCA.charAt(r.nextInt(BCA.length())) +
                UCA.charAt(r.nextInt(UCA.length())) +
                PTA.charAt(r.nextInt(PTA.length())) +
                PCA.charAt(r.nextInt(PCA.length())) +
                HTA.charAt(r.nextInt(HTA.length())) +
                SPA.charAt(r.nextInt(SPA.length())) +
                SZA.charAt(r.nextInt(SZA.length())) +
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
            allele = PT.charAt(r.nextInt(PT.length()));
        } else if (index == 4) {
            allele = PC.charAt(r.nextInt(PC.length()));
        } else if (index == 5) {
            allele = HT.charAt(r.nextInt(HT.length()));
        } else if (index == 6) {
            allele = SP.charAt(r.nextInt(SP.length()));
        } else if (index == 7) {
            allele = SZ.charAt(r.nextInt(SZ.length()));
        } else {
            allele = HP.charAt(r.nextInt(HP.length()));
        }
        return allele;
    }

    // ALLELE DOMINANCE

    public String calculateDominanceBC(String alleles) {
        if (alleles.contains("B")) {
            return "Black";
        } else if (alleles.contains("G")) {
            return "Green";
        } else if (alleles.contains("C")) {
            return "Cream";
        } else {
            return "Brown";
        }
    }

    public String calculateDominanceUC(String alleles) {
        if (alleles.contains("O")) {
            return "Brown";
        } else if (alleles.contains("G")) {
            return "Green";
        } else {
            return "Cream";
        }
    }

    public String calculateDominancePT(String alleles) {
        if (alleles.contains("T")) {
            return "Stripes";
        } else if (alleles.contains("S")) {
            return "Spine";
        } else {
            return "None";
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

    public String calculateDominanceHT(String alleles) {
        if (alleles.contains("S")) {
            return "Small";
        } else {
            return "Large";
        }
    }
}
