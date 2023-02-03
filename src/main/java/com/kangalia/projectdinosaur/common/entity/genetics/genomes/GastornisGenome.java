package com.kangalia.projectdinosaur.common.entity.genetics.genomes;

import com.kangalia.projectdinosaur.common.entity.genetics.BaseGenome;

public class GastornisGenome extends BaseGenome {

    //Total Genome - This is for spawn eggs and the full list which mutations draw from.
    public String CM = "AMN"; //Colour Morph (A=Albino, M=Melanistic, N=Normal)
    public String FC = "WGCROB"; //Feather Colour (W=White, G=Grey, C=Cream, R=Red, O=Brown, B=Black)
    public String UC = "WGC"; //Underside Colour (W=White, G=Grey, C=Cream)
    public String PC = "WGCROB"; //Pattern Colour (W=White, G=Grey, C=Cream, R=Red, O=Brown, B=Black)
    public String HC = "RGB"; //Highlight Colour (R=Red, G=Green, B=Blue) *Only visible on males*
    public String SC = "CGOB"; //Skin Colour (C=Cream, G=Canvas, O=Brown, B=Black)
    public String BC = "CYO"; //Beak Colour (C=Cream, Y=Yellow, O=Orange)
    public String SP = "ABCDE"; //Speed (A=1, B=2, C=3, D=4, E=5)
    public String SZ = "ABCDE"; //Size (A=1, B=2, C=3, D=4, E=5)
    public String AD = "ABCDE"; //Attack Damage (A=1, B=2, C=3, D=4, E=5)
    public String HP = "ABCDE"; //Health (A=1, B=2, C=3, D=4, E=5)

    //Allowed Genome - This is for those first hatching from incubated eggs.
    public String CMA = "N"; //Colour Morph (N=Normal)
    public String FCA = "GOB"; //Feather Colour (G=Grey, O=Brown, B=Black)
    public String UCA = "GC"; //Underside Colour (G=Grey, C=Cream)
    public String PCA = "GOB"; //Pattern Colour (G=Grey, O=Brown, B=Black)
    public String HCA = "GB"; //Highlight Colour (G=Green, B=Blue) *Only visible on males*
    public String SCA = "GO"; //Skin Colour (G=Canvas, O=Brown)
    public String BCA = "YO"; //Beak Colour (Y=Yellow, O=Orange)
    public String SPA = "BCD"; //Speed (B=2, C=3, D=4)
    public String SZA = "BCD"; //Size (B=2, C=3, D=4)
    public String ADA = "BCD"; //Attack Damage (B=2, C=3, D=4)
    public String HPA = "BCD"; //Health (B=2, C=3, D=4)

    public GastornisGenome() {
        haploidLengthMin = 11;
        haploidLengthMax = 13;
    }

    @Override
    public String setRandomGenes() {
        String haploid1 = stopCodon +
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
        String haploid2 = stopCodon +
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

    @Override
    public String setRandomAllowedGenes() {
        String haploid1 = stopCodon +
                CMA.charAt(r.nextInt(CMA.length())) +
                FCA.charAt(r.nextInt(FCA.length())) +
                UCA.charAt(r.nextInt(UCA.length())) +
                PCA.charAt(r.nextInt(PCA.length())) +
                HCA.charAt(r.nextInt(HCA.length())) +
                SCA.charAt(r.nextInt(SCA.length())) +
                BCA.charAt(r.nextInt(BCA.length())) +
                SPA.charAt(r.nextInt(SPA.length())) +
                SZA.charAt(r.nextInt(SZA.length())) +
                ADA.charAt(r.nextInt(ADA.length())) +
                HPA.charAt(r.nextInt(HPA.length()));
        String haploid2 = stopCodon +
                CMA.charAt(r.nextInt(CMA.length())) +
                FCA.charAt(r.nextInt(FCA.length())) +
                UCA.charAt(r.nextInt(UCA.length())) +
                PCA.charAt(r.nextInt(PCA.length())) +
                HCA.charAt(r.nextInt(HCA.length())) +
                SCA.charAt(r.nextInt(SCA.length())) +
                BCA.charAt(r.nextInt(BCA.length())) +
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

    // ALLELE DOMINANCE

    public int calculateDominanceFC(String alleles) {
        if (alleles.contains("O")) {
            return 0;
        } else if (alleles.contains("B")) {
            return 1;
        } else if (alleles.contains("C")) {
            return 2;
        } else if (alleles.contains("R")) {
            return 3;
        } else if (alleles.contains("G")) {
            return 4;
        } else {
            return 5;
        }
    }

    public int calculateDominanceUC(String alleles) {
        if (alleles.contains("C")) {
            return 0;
        } else if (alleles.contains("G")) {
            return 1;
        } else {
            return 2;
        }
    }

    public int calculateDominancePC(String alleles) {
        if (alleles.contains("B")) {
            return 0;
        } else if (alleles.contains("0")) {
            return 1;
        } else if (alleles.contains("C")) {
            return 2;
        } else if (alleles.contains("R")) {
            return 3;
        } else if (alleles.contains("G")) {
            return 4;
        } else {
            return 5;
        }
    }

    public int calculateDominanceHC(String alleles) {
        if (alleles.contains("B")) {
            return 0;
        } else if (alleles.contains("R")) {
            return 1;
        } else {
            return 2;
        }
    }

    public int calculateDominanceSC(String alleles) {
        if (alleles.contains("C")) {
            return 0;
        } else if (alleles.contains("0")) {
            return 1;
        } else if (alleles.contains("G")) {
            return 2;
        } else {
            return 3;
        }
    }

    public int calculateDominanceBC(String alleles) {
        if (alleles.contains("Y")) {
            return 0;
        } else if (alleles.contains("O")) {
            return 1;
        } else {
            return 2;
        }
    }
}
