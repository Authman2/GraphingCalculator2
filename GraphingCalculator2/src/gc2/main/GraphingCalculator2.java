package gc2.main;

public class GraphingCalculator2 {

	public static void main(String[] args) {
		//Polynomial p1 = new Polynomial("9x^3+5x^2-4x-8+9");
		Polynomial p2 = new Polynomial("x+5");
		
		System.out.println();
		System.out.println();
		//System.out.println("Answer:" + p1.evaluate(1));
		System.out.println("Answer:" + p2.evaluate(5));
	}

}
