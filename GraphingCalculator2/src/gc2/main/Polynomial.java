package gc2.main;

import java.util.ArrayList;

public class Polynomial {

	// The polynomial as a string.
	String polynomial;
	
	// The terms of the polynomial in the form "coefficient-x^exponent"
	String[] terms;
	
	// The types of operators as a string pattern.
	ArrayList<String> operators = new ArrayList<String>();
	
	
	
	
	
	/////////// Constructors ////////////
	
	/** Takes a polynomial as a string and will convert it into a readable expression. 
	 * @param polynomialString -- The polynomial as a string. (ex. "x^2+5x+2")*/
	public Polynomial(String polynomialString) {
		// Add the operators so the program will know later on what to look for.
		this.polynomial = polynomialString;
		operators.add("+");
		operators.add("-");
		
		// Add a plus or minus at the beginning
		if(this.polynomial.substring(0,1).equals("-")) {
			// don't do anything
		} else {
			this.polynomial = "+" + this.polynomial;
		}

		
		// Get each term and put it into an array
		terms = getEachTerm();
		
		
		// Add ones if there is just an "x" term with no other coefficient.
		addOnes();
	}

	
	
	
	
	/////////// Setters ////////////
	
	/** Puts a 1 in front of all x terms if there is no other coefficient. */
	private void addOnes() {
		for(int i = 0; i < terms.length; i++) {
			// If it just an x term, put a 1 in front of it.
			if(terms[i].matches("[-+][xX].*")) {
				terms[i] = terms[i].substring(0, 1) + "1x" + terms[i].substring(2);
			}
		}
	}
	
	
	
	
	
	/////////// Getters ////////////

	/** Finds and returns the index of the next operator.
	 * @return the index of the next operator in the polynomial. */
	private int findNextOperator(String poly, int start) {
		for(int i = start+1; i < poly.length(); i++) {
			
			if(operators.contains(poly.substring(i, i+1))) {
				return i;
			}
			
		}
		
		// Default is to return the length of the polynomial, which means you've reached the end of the polynomial
		return poly.length();
	}
	
	
	
	/** Returns an array of the terms in the polynomial.
	 * @return the terms, without their signs, as an array of strings. */
	private String[] getEachTerm() {
		String[] theTerms;
		String poly = polynomial;
		ArrayList<String> ts = new ArrayList<String>();
		
		
		// Start from the beginning
		while(poly.length() > 0) {
			int stop = findNextOperator(poly, 0);
			
			String term = poly.substring(0, stop);
			poly = poly.substring(stop);
			
			ts.add(term);
		}
		
		
		// Add each term to the array from the array list
		theTerms = new String[ts.size()];
		int k = 0;
		for(String s : ts) {
			theTerms[k] = s;
			k++;
		}
		
		return theTerms;
	}
	
	
	
	/** Returns a single term, but with the "x^exponent" term replaced by the number when the value of 
	 * the parameter "x" to the power specified in the original term. The coefficient, the x term, and the
	 * exponent are all separated by a single space.
	 * @param originalTerm -- The term from the original string that the user enters.
	 * @param x -- The x value to evaluate the polynomial at.
	 * @return the term, but with each value evaluated and coefficients, x's, and exponents separated by
	 * a single space. */
	private String getNumberString(String originalTerm, double x) {
		double coefficient = 1;
		double nX = 1;
		double exponent = 1;
		String newTerm = "";
		
		
		// Get the coefficient
		String coeffString = "";
		if(originalTerm.indexOf("x") > -1) {
			coeffString = originalTerm.substring(0, originalTerm.indexOf("x"));
		} else {
			if(originalTerm.indexOf("^") > 0) {
				coeffString = originalTerm.substring(0, originalTerm.indexOf("^"));
			} else {
				coeffString = originalTerm.substring(0);
			}
		}
		
		
		// Get the x term
		String xString = "";
		if(originalTerm.indexOf("x") > -1) {
			xString = "" + x;
		} else {
			xString = "1";
		}
		
		
		// Get the exponent
		String exponentString = "";
		if(originalTerm.indexOf("^") > -1) {
			exponentString = originalTerm.substring(originalTerm.indexOf("^")+1);
		} else {
			exponentString = "1";
		}
		
		
		// Create the new term
		coefficient = Double.parseDouble(coeffString);
		nX = Double.parseDouble(xString);
		exponent = Double.parseDouble(exponentString);
		
		newTerm = "" + coefficient + " " + nX + " " + exponent;
		//System.out.println(newTerm);
		
		return newTerm;
	}
	
	
	
	/** This method actually does the computation for evaluating a term. By the time the program gets here,
	 * the string, term, will already be in a nice format for computing.
	 * @param term -- The nicely formatted string of a polynomial term.
	 * @return the answer to "term" when evaluated. */
	private double evaluateSingleTerm(String term) {
		double answer = 0;
		
		// Get the coefficient part, the x part, and the exponent part.
		String[] parts = term.split(" ");
		
		// Set the appropriate variables.
		double coeff = Double.parseDouble(parts[0]);
		double x = Double.parseDouble(parts[1]);
		double exponent = Double.parseDouble(parts[2]);
		
		// Compute the answer
		answer = coeff * (Math.pow(x, exponent));
		
		//System.out.println("Single Term Eval: " + answer);
		
		// Return the answer
		return answer;
	}
	
	
	
	/** Evaluates the polynomial at the given value "x."
	 * @param x -- The input value for the polynomial.
	 * @return the value of the entire polynomial evaluated at "x" */
	public double evaluate(double x) {
		double answer = 0;
		
		// A list for the evaluations as strings.
		ArrayList<String> evalsString = new ArrayList<String>();
		ArrayList<Double> evaluations = new ArrayList<Double>();
		
		
		// Set up the evalsString list.
		for(String term : terms) {
			evalsString.add( getNumberString(term,x) );
		}
		
		
		// Now, add to the answer the number evaluation of each term.
		// You can say += because all negatives will already be handled by the individual terms.
		for(String stringEval : evalsString) {
			evaluations.add(evaluateSingleTerm(stringEval));
		}
		
		
		for(Double d : evaluations) {
			answer += d;
			//System.out.println("Answer Evaluation: " + answer);
		}
		
		
		return answer;
	}
}
