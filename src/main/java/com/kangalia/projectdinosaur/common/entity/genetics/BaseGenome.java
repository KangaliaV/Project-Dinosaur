package com.kangalia.projectdinosaur.common.entity.genetics;

import com.kangalia.projectdinosaur.core.util.RandomNumGen;

public class BaseGenome {

    public static RandomNumGen r = new RandomNumGen();

    public int haploidLengthMin;
    public int haploidLengthMax;
    public String stopCodon = "Z";

    public String getAlleles(String genome, int index) {
        String alleles;
        StringBuilder builder = new StringBuilder();
        alleles = builder.append(genome.charAt(index)).append(genome.charAt(index + haploidLengthMax)).toString();
        return alleles;
    }

    public String setRandomGenes() {
        return stopCodon+stopCodon;
    }

    public String setRandomAllowedGenes() {
        return stopCodon+stopCodon;
    }

    public String setInheritedGenes(String parent1, String parent2) {
        if (parent1 == null || parent2 == null) {
            return setRandomGenes();
        }

        String haploid1 = "";
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < haploidLengthMin; i++) {
            char allele;
            if (shouldMutate()) {
                allele = mutateGenes(i);
            } else {
                String temp = "" + parent1.charAt(i + 1) + parent1.charAt(i + haploidLengthMax);
                r.nextInt(2);
                allele = temp.charAt(r.nextInt(temp.length()));
            }
            System.out.println(allele);
            haploid1 = builder.append(allele).toString();
            System.out.println(haploid1);
        }
        String haploid2 = "";
        StringBuilder builder1 = new StringBuilder();
        for (int i=0; i < haploidLengthMin; i++) {
            char allele1;
            if (shouldMutate()) {
                allele1 = mutateGenes(i);
            } else {
                String temp1 = "" + parent2.charAt(i + 1) + parent2.charAt(i + haploidLengthMax);
                r.nextInt(2);
                allele1 = temp1.charAt(r.nextInt(temp1.length()));
            }
            System.out.println(allele1);
            haploid2 = builder1.append(allele1).toString();
            System.out.println(haploid2);
        }
        return stopCodon + haploid1 + stopCodon + haploid2;
    }

    public boolean shouldMutate() {
        int chance = r.nextInt(300);
        return chance == 0;
    }

    public char mutateGenes(int index) {
        return stopCodon.charAt(0);
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

    public float calculateCoefficient(String alleles) {
        String allele0 = alleles.substring(0);
        String allele1 = alleles.substring(1);
        float coefficient0 = 1f;
        float coefficient1 = 1f;
        for (int i=0; i < 5; i++) {
            coefficient0 = switch (allele0) {
                case "B" -> 0.8f;
                case "A" -> 0.9f;
                case "D" -> 1.1f;
                case "E" -> 1.2f;
                default -> 1f;
            };
        }
        for (int i=0; i < 5; i++) {
            coefficient1 = switch (allele1) {
                case "B" -> 0.8f;
                case "A" -> 0.9f;
                case "D" -> 1.1f;
                case "E" -> 1.2f;
                default -> 1f;
            };
        }
        return (coefficient0+coefficient1)/2;
    }
}
