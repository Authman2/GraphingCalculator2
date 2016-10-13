package gc2.main;

import java.util.ArrayList;

public class Polynomial {

	// The polynomial as a string.
	String polynomial;
	
	// The terms of the polynomial in the form "coeff-x^exponent"
	String[] terms;
	
	// All of the +'s and -'s in the polynomial.
	String[] signs;
	
	// The types of operators
	ArrayList<String> operators = new ArrayList<String>();
	
	
	
	
	
	/////////// Constructors ////////////
	
	/** Takes a polynomial as a string and will convert it into a readable expression. 
	 * @param polynomialString -- The polynomial as a string. (ex. "x^2+5x+2")*/
	public Polynomial(String polynomialString) {
		// Add the operators so the program will know later on what to look for.
		this.operators.add("+");
		this.operators.add("-");
		this.polynomial = polynomialString;
		
		
		// Get each term and put it into an array
		terms = getEachTerm();
		signs = getSigns();
		
		// Makes sure the appropriate signs are added to each term.
		assignSignsToTerms();
	}

	
	
	
	
	/////////// Setters ////////////
	
	/** Makes sure that each term has the correct sign. That is, if the equation had a "-2x" it would make
	 * sure that the term "2x" does not appear positive in the terms array. Instead, it will convert it to 
	 * "-2x" because of the sign at the appropraite index of the signs array. */
	private void assignSignsToTerms() {
		// The starting index in the terms array
		int start = 1;
		
		for(int i = 0; i < signs.length; i++) {
			terms[start] = signs[i] + terms[start];
			start++;
		}
	}
	
	
	
	
	
	
	/////////// Getters ////////////

	
	/** Returns an array of the terms in the polynomial.
	 * @return the terms, without their signs, as an array of strings. */
	private String[] getEachTerm() {
		String[] theTerms;
		String poly = polynomial;
		ArrayList<String> ts = new ArrayList<String>();
		
		// Start from the beginning
		for(int i = 0; i < poly.length(); i++) {
			String character = poly.substring(i, i+1);
			String term = "";
			
			// If the current character is not an operator, loop through until you find one.
			if(!operators.contains(character)) {
				
				for(int j = i; j < poly.length(); j++) {
					String character2 = poly.substring(j,j+1);
					
					// If the character you're looking at now is still not an operator, add it to the term.
					if(!operators.contains(character2)) {
						term += character2;
					
					// If you do find an operator, move i to the current position, j.
					} else {
						i = j - 1;
						break;
					}
				}
				
				// Add the term to the list.
				ts.add(term);
			}
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
	
	
	
	/** Returns an array of all of the signs (+ or -) in the polynomial.
	 * @return all the +'s and -'s in the polynomial. */
	private String[] getSigns() {
		String[] theSigns = null;
		int size = 0;
		String poly = polynomial;
		ArrayList<String> signs = new ArrayList<String>();
		
		// Loop through and add all of the operators to the list. Update the size also.
		for(int i = 0; i < poly.length(); i++) {
			if(operators.contains(poly.substring(i, i+1))) {
				size++;
				signs.add(poly.substring(i,i+1));
			}
		}
		
		
		// Add all of the terms to the array
		theSigns = new String[size];
		int k = 0;
		for(String s : signs) {
			theSigns[k] = s;
			k++;
		}
		
		
		return theSigns;
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
		double exponent = 1;
		String newTerm = "";
		
		
		// First, grab the coefficient, which is just the number until the first "x" that is found.
		String coeffString = "";
		for(int i = 0; i < originalTerm.length(); i++) {
			if(!originalTerm.substring(i, i+1).equalsIgnoreCase("x") && !originalTerm.substring(i, i+1).equalsIgnoreCase("^")) {
				coeffString += originalTerm.substring(i,i+1);
			} else {
				break;
			}
		}
		
		// If the coeffString is not the empty string (meaning there is a term), parse that into coefficient
		coefficient = (!coeffString.equals("")) ? Double.parseDouble(coeffString) : 1;
		
		
		// Now get the exponent
		String exponentString = "";
		if(originalTerm.indexOf("^") > -1) {
			exponentString = originalTerm.substring(originalTerm.indexOf("^")+1, originalTerm.length());
		}
		exponent = (!exponentString.equals("")) ? Double.parseDouble(exponentString) : 1;
		
		
		// Now replace the orignal term with the new one.
		if(originalTerm.contains("x") || originalTerm.contains("X")) {
			newTerm = "" + coefficient + " " + x + " " + exponent;
		} else {
			newTerm = "" + coefficient + " " + exponent;
		}
		
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
		double x = (parts.length > 1) ? Double.parseDouble(parts[1]) : 1;
		double exponent = (parts.length > 2) ? Double.parseDouble(parts[2]) : 1;
		
		// Compute the answer
		answer = coeff * (Math.pow(x, exponent));
		
		
		
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
		
		
		// Set up the evalsString list.
		for(String term : terms) {
			evalsString.add( getNumberString(term,x) );
		}
		
		
		// Now, add to the answer the number evaluation of each term.
		// You can say += because all negatives will already be handled by the individual terms.
		for(String stringEval : evalsString) {
			answer += evaluateSingleTerm(stringEval);
		}
		
		
		return answer;
	}
}
