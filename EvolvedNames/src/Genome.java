import java.util.Random;

/**
 * Defines a genome object.
 * @author nicholas hays and ethan rowell
 */
public class Genome {
	public static final char[] ALPHA = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_', '-', '\'' };
	public static final Random RANDOM = new Random();
	public double myMutationRate;
	public StringBuilder myGene;
	public int fitnessLvl;

	// constructor
	public Genome(double theMutationRate) {
		myMutationRate = theMutationRate;
		myGene = new StringBuilder();
		myGene.append(ALPHA[0]);
	}

	// copy constructor
	public Genome(Genome theGene) {
		myGene = new StringBuilder();
		myGene.append(theGene.myGene.toString());
		myMutationRate = theGene.myMutationRate;
	}

	// the function mutates the string in this Genome
	public void mutate() {
		if (mutationChance()) {
			int theIndex = randIndex(myGene.length() + 1);
			if (theIndex == myGene.length()) {
				myGene.append(ALPHA[randIndex(ALPHA.length)]);
			} else {
				myGene.insert(theIndex, ALPHA[randIndex(ALPHA.length)]);
			}
		}

		if (mutationChance()) {
			if (myGene.length() >= 2) {
				myGene.deleteCharAt(randIndex(myGene.length()));
			}
		}

		for (int i = 0; i < myGene.length(); i++) {
			if (mutationChance()) {
				myGene.setCharAt(i, ALPHA[randIndex(ALPHA.length)]);
			}
		}
		// fitness();
	}

	// this function will update the current Genome by crossing it over with
	// other
	public void crossover(Genome theOther) {
		/*int smallestGeneLen = Math.min(myGene.length(), theOther.myGene.length());
		for (int i = 0; i < smallestGeneLen; i++) {
			if (RANDOM.nextBoolean()) {
				myGene.setCharAt(i, theOther.myGene.charAt(i));
			}
		}*/
		
		int smallestGeneLen = Math.min(myGene.length(), theOther.myGene.length());
		String tempGene = myGene.toString();
		myGene.setLength(0);
		
		for (int i = 0; i < smallestGeneLen; i++) {
			if (RANDOM.nextBoolean()) {
				myGene.append(theOther.myGene.charAt(i));
			} else {
				myGene.append(tempGene.charAt(i));
			}
		}
	}

	// returns the fitness of the Genome
	public Integer fitness() {
		int n = myGene.length();		
		int m = Population.TARGET.length();
		int l = Integer.max(n, m);
		int f = Math.abs(m - n);
		for (int i = 1; i <= l; i++) {
			try {
				if (myGene.charAt(i - 1) != Population.TARGET.charAt(i - 1)) {
					f++;
				}
			} catch (IndexOutOfBoundsException e) {
				f++;
			}
		}
		return fitnessLvl = f;
		// return f;
	}

	private int randIndex(int theLength) {
		return RANDOM.nextInt(theLength);
	}

	private boolean mutationChance() {
		int rand = RANDOM.nextInt(100);
		if (rand <= myMutationRate * 100) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return myGene.toString();
	}
}