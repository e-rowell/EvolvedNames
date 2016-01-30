/*
 * Assignment 2 - Evolution
 * 
 * Nicholas Hays and Ethan Rowell
 */


/**
 * 
 * Main entry point.
 * @author Nicholas Hays and Ethan Rowell
 */
public class Main {

	static Population population;
	
	public static void main(String[] args) {
		evolution();
		
		// testPopulation();
		// testGenome();
	}
	
	private static void evolution() {
		population = new Population(100, 0.05);
		double startTime = System.currentTimeMillis();
		
		while(population.myMostFit.fitness() > 0) {
			population.day();
		}
		
		System.out.println(String.format("The most fit: %s in %d generations.",
				population.myMostFit.toString(), population.myGeneration));
		
		double endTime = System.currentTimeMillis();
		double totalTime = (endTime - startTime) / 1000;
		System.out.println("Total time: " + totalTime);
	}
	
	
	
	
	private static void testGenome() {
		Population.TARGET = "TESTING_GENOMES";
		
		Genome genome = new Genome(0.1);
		Genome testGenome = new Genome(0.05);
		
		// testing genome strings
		System.out.println("Genome toString: " + genome.toString());
		System.out.println("Default genome: " + genome.myGene.toString());
		
		// testing genome mutation rate and crossover
		for (int i = 0; i < 100; i++) {
			
			genome.mutate();
			genome.fitnessLvl = genome.fitness();
			// testing copy constructor
			if (i == 25) {
				testGenome = new Genome(genome);
				System.out.println("Original genome: " + genome.toString());
				System.out.println("Copied genome: " + testGenome.toString());
			}
			
			// testing crossover
			if (i == 50) {
				genome.crossover(testGenome);
				genome.mutate();
				
				System.out.println("Crossover genome: " + genome.toString());
			}
			
			System.out.println(genome.toString());
		}
	}
	
	private static void testPopulation() {
		Population.TARGET = "CHRISTOPHER_BALLER_MARRIOT";
		
		// testPopLarge();
		testPopMethods();
		// displayPopulation();
	}
	
	private static void testPopLarge() {
		// we don't mess around.
		population = new Population(10000, 0.05);
		double startTime = System.currentTimeMillis();
		
		population.day();
		while(population.myMostFit.fitness() > 10) {
			population.day();
		}
		
		System.out.println(String.format("The most fit: %s in %d generations.",
				population.myMostFit.toString(), population.myGeneration));
		
		double endTime = System.currentTimeMillis();
		double totalTime = (endTime - startTime) / 1000;
		
		System.out.println("Total time: " + totalTime);
	}
	
	private static void testPopMethods() {
		population = new Population(10, 0.5);
		
		
		for (int i = 0; i < 100; i++) {
			population.day();
		}
		System.out.println(population.toString());
		
		// testing deletion
		population.deleteLeastFit();
		System.out.println(population.toString());
		
		// testing spawning
		population.spawnGenomes();
		System.out.println(population.toString());
		
		// testing sort
		population.sortGenomes();
		System.out.println(population.toString());
	}
	
	private static void displayPopulation() {
		Population.TARGET = "OK";
		
		population = new Population(100, 0.25);
		
		// Generation 0
		System.out.println(population.toString());
		
		while(population.myMostFit.fitness() > 0) {
			population.day();
			System.out.println(population.toString());
		}
	}
}
