package com.kangalia.projectdinosaur.common.entity.genetics.genomes;

import com.kangalia.projectdinosaur.common.entity.genetics.BaseGenome;

public class AustralovenatorGenome extends BaseGenome {

    //Total Genome - This is for spawn eggs and the full list which mutations draw from.
    public String CM = "AMN"; //Colour Morph (A=Albino, M=Melanistic, N=Normal)
    public String BC = "OCGD"; //Base Colour (O=Orange, C=Cream, G=Green, D=Grey)
    public String UC = "CGOB"; //Underside Colour (C=Cream, G=Grey, O=Brown, B=Black)
    public String PT = "CSPFR"; //Pattern Type (C=Circles, S=Stripes, P=Spots, F=Clownfish, R=Rings)
    public String PC = "GWBRCY"; //Pattern Colour (G=Grey, W=White, B=Black, R=Red, C=Cyan, Y=Yellow)
    public String HC = "RGBY"; //Highlight Colour (R=Red, G=Green, B=Blue, Y=Yellow) *Only visible on males*
    public String SP = "ABCDE"; //Speed (A=1, B=2, C=3, D=4, E=5)
    public String SZ = "ABCDE"; //Size (A=1, B=2, C=3, D=4, E=5)
    public String AD = "ABCDE"; //Attack Damage (A=1, B=2, C=3, D=4, E=5)
    public String HP = "ABCDE"; //Health (A=1, B=2, C=3, D=4, E=5)

    //Allowed Genome - This is for those first hatching from incubated eggs.
    public String CMA = "N"; //Colour Morph (N=Normal)
    public String BCA = "OD"; //Base Colour (O=Orange, D=Grey)
    public String UCA = "CG"; //Underside Colour (C=Cream, G=Grey)
    public String PTA = "CS"; //Pattern Type (C=Circles, S=Stripes)
    public String PCA = "GBY"; //Pattern Colour (G=Grey, B=Black, Y=Yellow)
    public String HCA = "BY"; //Highlight Colour (B=Blue, Y=Yellow) *Only visible on males*
    public String SPA = "BCD"; //Speed (B=2, C=3, D=4)
    public String SZA = "BCD"; //Size (B=2, C=3, D=4)
    public String ADA = "BCD"; //Attack Damage (B=2, C=3, D=4)
    public String HPA = "BCD"; //Health (B=2, C=3, D=4)

    public AustralovenatorGenome() {
        haploidLengthMin = 10;
        haploidLengthMax = 12;
    }

    @Override
    public String setRandomGenes() {
        String haploid1 = stopCodon +
                CM.charAt(r.nextInt(CM.length())) +
                BC.charAt(r.nextInt(BC.length())) +
                UC.charAt(r.nextInt(UC.length())) +
                PT.charAt(r.nextInt(PC.length())) +
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
                PT.charAt(r.nextInt(PC.length())) +
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
                PTA.charAt(r.nextInt(PCA.length())) +
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
                PTA.charAt(r.nextInt(PCA.length())) +
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
            allele = PT.charAt(r.nextInt(PT.length()));
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

    public int calculateDominanceBC(String alleles) {
        if (alleles.contains("O")) {
            return 0;
        } else if (alleles.contains("D")) {
            return 1;
        } else if (alleles.contains("G")) {
            return 2;
        } else {
            return 3;
        }
    }

    public int calculateDominanceUC(String alleles) {
        if (alleles.contains("C")) {
            return 0;
        } else if (alleles.contains("G")) {
            return 1;
        } else if (alleles.contains("O")) {
            return 2;
        } else {
            return 3;
        }
    }

    public int calculateDominancePT(String alleles) {
        if (alleles.contains("C")) {
            return 0;
        } else if (alleles.contains("S")) {
            return 1;
        } else if (alleles.contains("R")) {
            return 2;
        } else if (alleles.contains("P")) {
            return 3;
        } else {
            return 4;
        }
    }

    public int calculateDominancePC(String alleles) {
        if (alleles.contains("G")) {
            return 0;
        } else if (alleles.contains("B")) {
            return 1;
        } else if (alleles.contains("Y")) {
            return 2;
        } else if (alleles.contains("W")) {
            return 3;
        } else if (alleles.contains("R")) {
            return 4;
        } else {
            return 5;
        }
    }

    public int calculateDominanceHC(String alleles) {
        if (alleles.contains("B")) {
            return 0;
        } else if (alleles.contains("G")) {
            return 1;
        } else if (alleles.contains("Y")) {
            return 2;
        } else {
            return 3;
        }
    }
}
