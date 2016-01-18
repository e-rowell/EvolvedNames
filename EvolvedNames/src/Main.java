
public class Main {

	public static void main(String[] args) {
		// should instantiate a population and call day()
		// until the target string is part of the pop
		// target string has fitness zero so loop should repeat
		// until the most fit genome has fitness zero.
		Genome childGene = new Genome(.05);
		Genome parentGene = new Genome(.05);
		
		for (int i = 0; i < 1000; i++) {
			childGene.mutate();
			parentGene.mutate();
			childGene.crossover(parentGene);
			System.out.println("child gene: " + childGene.toString());
			System.out.println("parent gene: " + parentGene.toString());
			System.out.println();
		}
	}

	// this method tests the Genome class.
	public static void testGenome() {

	}

	// this method test the Population class.
	public static void testPopulation() {

	}

}
