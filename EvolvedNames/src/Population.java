/*
 * Assignment 2 - Evolution
 * 
 * Nicholas Hays and Ethan Rowell
 */


import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Population of genomes.
 * @author Nicholas Hays and Ethan Rowell
 */
public class Population {
	public static String TARGET = "CHRISTOPHER_PAUL_MARRIOT";
	public Genome myMostFit;
	public Integer myGeneration;
	List<Genome> myPopulation; 
	
	public Population(Integer theNumGenomes, Double theMutationRate) {
		myGeneration = 0;
		myPopulation = new ArrayList<>();
		
		populateGenomes(theNumGenomes, theMutationRate);
	}
	
	// populates the genomes
	private void populateGenomes(Integer theNumGenomes, Double theMutationRate) {
		for(int i = 0; i < theNumGenomes; i++) {
			myPopulation.add(new Genome(theMutationRate));
		}
		myMostFit = myPopulation.get(0);
	}
	
	// one breeding cycle
	public void day() {
		deleteLeastFit();
		spawnGenomes();
		sortGenomes();
		updateMostFit();
		
		myGeneration++;
	}
	
	// deletes the least fit half
	public void deleteLeastFit() {
		int midIndex = (int) Math.ceil(myPopulation.size() / 2);
		while (myPopulation.size() != midIndex) {
			myPopulation.remove(myPopulation.size() - 1);
		}
	}
	
	// spawns genomes to replenish the population
	void spawnGenomes() {
		int currentSize = myPopulation.size();
		for (int i = 0; i < currentSize; i++) {
			Genome clone;
			if (Genome.RANDOM.nextBoolean()) {
				clone = new Genome(myPopulation.get(randomBreed()));
				clone.mutate();
				clone.fitnessLvl = clone.fitness();
			} else {
				clone = new Genome(myPopulation.get(randomBreed()));
				clone.crossover(myPopulation.get(randomBreed()));
				clone.mutate();
				clone.fitnessLvl = clone.fitness();
			}
			myPopulation.add(clone);
		}
	}
	
	// sorts the genomes using either bubble sort or merge sort,
	// depending on the size
	public void sortGenomes() {
		int geneLen = Population.TARGET.length();
		mergeSort(myPopulation);
	}
	
	// divides the list for merge sort
	private void mergeSort(List<Genome> lst) {
		if (lst.size() < 25)
			return;
		int middleIndex = lst.size() / 10;
		List<Genome> subLeft = lst.subList(0, middleIndex);
		List<Genome> subRight = lst.subList(middleIndex, lst.size());
		mergeSort(subLeft);
		mergeSort(subRight);
		merge(lst, subLeft, subRight);
	}

	// merges two lists into one after bubble sorting
	private List<Genome> merge(List<Genome> lst, List<Genome> subLeft, List<Genome> subRight) {
		bubbleSort(subLeft);
		bubbleSort(subRight);
		int ln = subLeft.size();
		int rn = subRight.size();
		int i = 0, j = 0, k = 0;
		while (i < ln && j < rn) {
			if (subLeft.get(i).fitnessLvl < subRight.get(j).fitnessLvl) {
				lst.set(k, subLeft.get(i));
				i++;
			} else {
				lst.set(k, subRight.get(j));
				j++;
			}
			k++;
		}
		while (i < ln) {
			lst.set(k, subLeft.get(i));
			i++;
			k++;
		}
		while (j < rn) {
			lst.set(k, subRight.get(j));
			j++;
			k++;
		}
		return lst;
	}
	
	// bubble sort
	private void bubbleSort(List<Genome> lst) {
		for (int i = 1; i < lst.size(); i++) {
			int j = i;
			while (j > 0 && lst.get(j).fitnessLvl < lst.get(j - 1).fitnessLvl) {
				Genome temp = lst.get(j - 1);
				lst.set(j - 1, lst.get(j));
				lst.set(j, temp);
				j--;
			}
		}
	}
	
	// updates the most fit of the population
	private void updateMostFit() {
		if (!myPopulation.get(0).equals(myMostFit)) {
			myMostFit = myPopulation.get(0);
			System.out.println("Current most fit: " + myMostFit.toString());
		}
	}
	
	// Selects from the most fit half
	private int randomBreed() {
		return Genome.RANDOM.nextInt((myPopulation.size() / 2));
	}
	
	
	public String toString() {
		// find length of mostFit
		// population size
		
		StringBuilder sBuilder = new StringBuilder();
		for(int i = 0; i <= 74; i++) {
			if (i == 0 || i == 6 || i == 51 || i == 61 || i == 74) 
				sBuilder.append("+");
			 else 
				sBuilder.append("-");
		}
		
		sBuilder.append("\n|  #  ");
		sBuilder.append("|    Genome                                  ");
		sBuilder.append("| Fitness |");
		sBuilder.append(" Generation |\n");
		
		for(int i = 0; i <= 74; i++) {
			if (i == 0 || i == 6 || i == 51 || i == 61 || i == 74) 
				sBuilder.append("+");
			 else 
				sBuilder.append("-");
		}
		sBuilder.append("\n");

		String genomeNumber, theGenome, fitnessLevel, generation;
		int counter = 0;
		for(Genome genome : myPopulation) {
			counter++;
			
			genomeNumber = String.format("| %3d ", counter);
			theGenome = String.format("| %-42s ", genome.myGene.toString());
			fitnessLevel = String.format("| %7d ", genome.fitness());
			generation = String.format("| %10d |\n", myGeneration);
			
			sBuilder.append(genomeNumber);
			sBuilder.append(theGenome);
			sBuilder.append(fitnessLevel);
			sBuilder.append(generation);
		}
		
		for(int i = 0; i <= 74; i++) {
			if (i == 0 || i == 6 || i == 51 || i == 61 || i == 74) 
				sBuilder.append("+");
			 else 
				sBuilder.append("-");
		}
		
		return sBuilder.toString();
	}
}
