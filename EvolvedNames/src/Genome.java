import java.util.Random;

import javax.xml.bind.annotation.W3CDomHandler;

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

	// the function mutates the string in this Genonme
	public void mutate() {

		if (randChance() == true) {
			int theIndex = randIndex(myGene.length());
			if (theIndex == myGene.length()) {
				myGene.append(alpha[randIndex(alpha.length - 1)]);
			} else {
				myGene.insert(theIndex, alpha[randIndex(alpha.length - 1)]);
			}
		}

		if (randChance() == true) {
			if (myGene.length() > 2) {
				myGene.deleteCharAt(randIndex(myGene.length() - 1));
			}
		}

		for (int i = 0; i < myGene.length() - 1; i++) {
			if (randChance() == true) {
				myGene.setCharAt(i, alpha[randIndex(alpha.length - 1)]);
			}
		}

	}

	// this function will update the current Genome by crossing it over with
	// other
	public void crossover(Genome other) {
		int 
	}

	// returns the finess of the Genome
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
