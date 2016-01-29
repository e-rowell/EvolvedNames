
public class Main {

	public static void main(String[] args) {
		Population population = new Population(100, 0.05);
		
		double startTime = System.currentTimeMillis();
		
		population.day();
		while(population.myMostFit.fitness() > 0) {
			population.day();
		}
		
		System.out.println(String.format("The most fit: %s in %d generations.", population.myMostFit.toString(), population.myGeneration));
		
		double endTime = System.currentTimeMillis();
		double totalTime = (endTime - startTime) / 1000;
		System.out.println("Total time: " + totalTime);
	}
}
