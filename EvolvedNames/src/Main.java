
public class Main {

	static Population population;
	
	public static void main(String[] args) {
		population = new Population(100, 0.05);
		testPopulation();
		double startTime = System.currentTimeMillis();
		
		population.day();
		while(population.myMostFit.fitness() > 0) {
			population.day();
			testPopulation();
		}
		
		System.out.println(String.format("The most fit: %s in %d generations.", population.myMostFit.toString(), population.myGeneration));
		
		double endTime = System.currentTimeMillis();
		double totalTime = (endTime - startTime) / 1000;
		System.out.println("Total time: " + totalTime);
	}
	
	private void testGenome() {
		Genome genome = new Genome(0.05);
		
		System.out.println(genome.toString());
		System.out.println(genome.myGene.toString());
		
	}
	
	private static void testPopulation() {
		System.out.println(population.toString());
	}
}
