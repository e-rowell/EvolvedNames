import java.util.List;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Ethan Rowell
 *
 */
public class Population {
	public static final String TARGET = "CHRISTOPHER_PAUL_MARRIOT";
	public Genome myMostFit;
	public Integer myGeneration;
	List<Genome> myPopulation; 
	
	/**
	 * 
	 * @param theNumGenomes
	 * @param theMutationRate
	 */
	public Population(Integer theNumGenomes, Double theMutationRate) {
		myGeneration = 0;
		myPopulation = new ArrayList<>();
		
		populateGenomes(theNumGenomes, theMutationRate);
	}
	
	/**
	 * 
	 */
	private void populateGenomes(Integer theNumGenomes, Double theMutationRate) {
		for(int i = 0; i < theNumGenomes; i++) {
			myPopulation.add(new Genome(theMutationRate));
		}
	}
	
	/**
	 * 
	 */
	public void day() {
		for (Genome genome : myPopulation) {
			genome.fitness();
		}
		
		sortGenomes(myPopulation);
		// mergeSort(myPopulation, 0, myPopulation.size() - 1);

		if (!myPopulation.get(0).equals(myMostFit)) {
			myMostFit = myPopulation.get(0);
			System.out.println("Current most fit: " + myMostFit.toString());
		}
		
		// spawns genomes to replace the least fit half
		for (int i = myPopulation.size() / 2; i < myPopulation.size(); i++) {
			myPopulation.set(i, spawnGenome());
		}
		
		myGeneration++;
	}
	
	private void sortGenomes(List<Genome> list) {
		boolean swapped = true;
		int j = 0;
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < list.size() - j; i++) {
				if (list.get(i).fitnessLvl > list.get(i + 1).fitnessLvl) {
					swapGenomes(i, i + 1);
					swapped = true;
				}
			}
		}
	}
	
	private void swapGenomes(Integer theSmallIndex, Integer theLargeIndex) {
		Genome tempGenome = myPopulation.get(theSmallIndex);
		myPopulation.set(theSmallIndex, myPopulation.get(theLargeIndex));
		myPopulation.set(theLargeIndex, tempGenome);
	}
	
	/*private List<Genome> mergeSort(List<Genome> list, int left, int right) {
		int middle = list.size() / 2;
		if (middle == 1) return list;
		mergeSort(list.subList(middle, list.size()), left, middle);
		mergeSort(list.subList(middle, list.size()), middle, right);
		sortGenomes(list);
		return list;
	}*/
	
	private Genome spawnGenome() {
		Genome clone;
		
		if (Genome.RANDOM.nextBoolean()) {
			clone = new Genome(myPopulation.get(randomBreed()));
			clone.mutate();
		} else {
			clone = new Genome(myPopulation.get(randomBreed()));
			clone.crossover(myPopulation.get(randomBreed()));
			clone.mutate();
		}
		return clone;
	}
	
	private int randomBreed() {
		return Genome.RANDOM.nextInt((myPopulation.size() / 2));
	}
	
	public String toString() {
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
			theGenome = String.format("| %-42s ", genome.toString());
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
