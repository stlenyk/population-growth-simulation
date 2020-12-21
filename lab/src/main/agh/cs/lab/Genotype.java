package agh.cs.lab;


import java.util.*;
import java.util.stream.Stream;

public class Genotype {
	private int[] genes = new int[32];

	public Genotype() {
		for(int i=0; i<8; i++) genes[i] = i;
		Random rand = new Random();
		for(int i=8; i< genes.length; i++)
			genes[i] = rand.nextInt(8);
		Arrays.sort(genes);
	}

	public Genotype(Animal parent1, Animal parent2) {

		Random rand = new Random();
		int split1 = rand.nextInt(30) + 1; // <1, 30>
		int split2 = rand.nextInt(30) + 1;
		if(split1 > split2) {
			int temp = split1;
			split1 = split2;
			split2 = split1;
		}
		int[] par1genes = parent1.getGenotype().genes;
		int[] par2genes = parent2.getGenotype().genes;
		genes = Stream.of(
			Arrays.stream(par1genes, 0, split1),
			Arrays.stream(par2genes, split1, split2+1),
			Arrays.stream(par1genes, split2+1, 32)
		).flatMapToInt(s -> s).toArray();
		Arrays.sort(genes);

		// check for missing alleles and add them to genotype
		ArrayList<Integer> missingGeneTypes = new ArrayList<>();
		int[] numOfGenes = new int[8];
		for(int i=0, gene=0; i<genes.length; i++) {
			numOfGenes[genes[i]]++;
			if (genes[i] > gene) {
				missingGeneTypes.add(gene);
				gene++;
			}
			else if (genes[i] == gene)
				gene++;
		}
		for (Integer i: missingGeneTypes) {
			int index;
			do {
				index = rand.nextInt(32);
			} while(numOfGenes[genes[index]] < 2);
			genes[index] = i;
		}
		Arrays.sort(genes);

	}

	public int[] getGenes() {
		return genes;
	}

}
