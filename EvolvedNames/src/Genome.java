import java.util.Random;

public class Genome {
	private static Random RANDOM = new Random();
	double myMutationRate;
	StringBuilder myGene;
	int fitnessLvl;
	char[] alpha = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', '_', '-', '\'' };

	// constructor
	public Genome(double theMutationRate) {
		this.myMutationRate = theMutationRate;
		this.myGene = new StringBuilder(alpha[0]);
	}

	// copy constructor
	public Genome(Genome theGene) {
		this.myGene = theGene.myGene;
		this.myMutationRate = theGene.myMutationRate;
	}

	// the function mutates the string in this Genome
	public void mutate() {

		if (randChance() == true) {
			int theIndex = randIndex(myGene.length() + 1); 
			if (theIndex == myGene.length()) {
				myGene.append(alpha[randIndex(alpha.length)]);
			} else {
				myGene.insert(theIndex, alpha[randIndex(alpha.length)]);
			}
		}

		if (randChance() == true) {
			if (myGene.length() > 2) {
				myGene.deleteCharAt(randIndex(myGene.length()));
			}
		}

		for (int i = 0; i < myGene.length() - 1; i++) {
			if (randChance() == true) {
				myGene.setCharAt(i, alpha[randIndex(alpha.length)]);
			}
		}

	}

	// this function will update the current Genome by crossing it over with
	// other
	public void crossover(Genome theOther) {
		int smallestGeneLen = Math.min(myGene.length(), theOther.myGene.length());
		
		for (int i = 0; i < smallestGeneLen; i++) {
			if(RANDOM.nextInt(2) == 1) {
				myGene.setCharAt(i, theOther.myGene.charAt(i));
			} 
		}
		
	}
	public void testMethod() {
		//some more
		//some data	
	}
	
	// returns the fitness of the Genome
	public Integer fitness() {
		return null;
	}

	private int randIndex(int theLength) {
		return RANDOM.nextInt(theLength);
	}

	private boolean randChance() {
		boolean hitOrMiss;
		Random random = new Random();
		int rand = random.nextInt(100);
		if (rand < 5) {
			hitOrMiss = true;
		} else {
			hitOrMiss = false;
		}
		System.out.println(rand);
		return hitOrMiss;
	}

}
