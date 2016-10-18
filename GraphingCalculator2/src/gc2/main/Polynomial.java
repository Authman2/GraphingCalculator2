package gc2.main;

import java.util.ArrayList;

public class Polynomial {

	// The polynomial as a string.
	String polynomial;
	
	// The terms of the polynomial in the form "coefficient-x^exponent"
	String[] terms;
	
	// An array of just the coefficients
	double[] coefficients;
	
	// An array of just the exponents
	double[] exponents;
	
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
		
		// Add "1" if there is just an "x" term with no other coefficient.
		addOnes();
		
		// Fill the coefficients and exponents arrays.
		coefficients = getCoefficients();
		exponents = getExponents();
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
	
	/** Returns the degree of the polynomial. 
	 * @return the greatest exponent in the polynomial.*/
	public double getDegree() {
		double deg = 0;
		ArrayList<String> exponents = new ArrayList<String>();
		
		for(String term : terms) {
			if(term.indexOf("^") > -1) {
				exponents.add(term.substring(term.indexOf("^")+1 ));
			}
		}
		
		// Find highest exponent
		for(String exp : exponents) {
			if(Double.parseDouble(exp) > deg) {
				deg = Double.parseDouble(exp);
			}
		}
		
		
		return deg;
	}

	
	
	/** Finds and returns the index of the next operator.
	 * @return the index of the next operator in the polynomial. */
	private int findNextOperator(String poly) {
		for(int i = 1; i < poly.length(); i++) {
			
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
		String[] theTerms;	// The final array of terms.
		String poly = polynomial;	// Get a copy of the polynomial.
		ArrayList<String> ts = new ArrayList<String>();
		
		
		// Start from the beginning
		while(poly.length() > 0) {
			// Find the next operator.
			int stop = findNextOperator(poly);
			
			// The term that you are currently looking at is from 0 to stop.
			String term = poly.substring(0, stop);
			// Shorten the poly string so you can continue to look for the next operator starting from 0.
			poly = poly.substring(stop);
			
			// Add the term.
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
	
	
	
	/** Fills the coefficients array with the coefficient of each term. This is called AFTER the getEachTerm
	 * method because it assumes that the term array has already been filled. */
	private double[] getCoefficients() {
		double[] coeffs;
		String coeffsString = "";
		
		// Loop through and add to the coeffs string.
		for(String s : terms) {
			if(s.indexOf("x") > -1) {
				coeffsString += s.substring(0, s.indexOf("x")) + " ";
			} 
			else if(s.indexOf("^") > -1) {
				coeffsString += s.substring(0, s.indexOf("^")) + " ";
			} 
			else {
				coeffsString += s + " ";
			}
		}
		
		// Split into an array of strings.
		String[] temp = coeffsString.split(" ");
		coeffs = new double[temp.length];
		
		int k = 0;
		for(String t : temp) {
			coeffs[k] = Double.parseDouble(t);
			k++;
		}
		
		return coeffs;
	}

	
	
	/** Returns an array of all of the exponents in the polynomial. Must be called after the getEachTerm method. */
	private double[] getExponents() {
		double[] exponents;
		
		String temp = "";
		
		// Find the exponents
		for(String s : terms) {
			if(s.indexOf("^") > -1) {
				temp += s.substring(s.indexOf("^")+1) + " ";
			} else if(s.indexOf("x") > -1) {
				temp += "1 ";
			} else {
				temp += "0 ";
			}
		}
		
		// Fill the array of exponents
		String[] temp2 = temp.split(" ");
		exponents = new double[temp2.length];
		int k = 0;
		for(String s : temp2) {
			exponents[k] = Double.parseDouble(s);
			k++;
		}
		
		return exponents;
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
			if(originalTerm.indexOf("^") > -1) {
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
		// A list for the evaluations of each individual term.
		ArrayList<Double> evaluations = new ArrayList<Double>();
		
		
		// Set up the evalsString list.
		for(String term : terms) {
			evalsString.add( getNumberString(term,x) );
		}
		
		
		// Now, add to the answer the number evaluation of each term.
		// You can say += because all negatives will already be handled by the individual terms.
		for(String stringEval : evalsString) {
			evaluations.add( evaluateSingleTerm(stringEval) );
		}
		
		
		for(Double d : evaluations) {
			answer += d;
			//System.out.println("Answer Evaluation: " + answer);
		}
		
		
		return answer;
	}
	
	
	
	/** Returns the lower bound of a particular polynomial's roots. */
    public double getLowerBound() {
        return -getUpperBound();
    }
    
    
    
    /** Returns the upper bound of a particular polynomial's roots. */
    public double getUpperBound() {
        //Coefficient of largest magnitude
        double aMax = 0;
        //Highest degree in the polynomial
        int highestDeg = 0;
        //Coefficient with the highest degree
        double aN = 0;
        
        //Loop through the coefficients to find the one of greatest magnitude
        for(int i = 0; i < coefficients.length; i++) {
            if(Math.abs(coefficients[i]) > aMax) {
                aMax = coefficients[i];
            }
        }
        
        //Loop through to find the coefficient of largest degree
        for(int i = 0; i < exponents.length; i++) {
            if(exponents[i] > highestDeg) {
                highestDeg = i;
            }
        }
        
        aN = coefficients[highestDeg];
        
        double bound = 0;        
        bound = 1 + Math.abs(aMax/aN);
        
        
        return bound;
    }
	
    
	
	/** Returns an array of the roots of the polynomial.
	 * @param xmin -- The min value to look for roots at.
	 * @param xmax -- The max value to look for roots at.
	 * @return an array of doubles, which are the roots of the polynomial. */
	public double[] getRoots(int xmin, int xmax) {
		 //Create a really small step to use for moving along the x-axis.
        double step = Math.abs((getUpperBound()-getLowerBound())/10000);
        //Array of roots
        ArrayList<Double> rootsTemp = new ArrayList<Double>();
        
        
        // Check for zero
        if(evaluate(0) == 0.0) {
        	rootsTemp.add(new Double(0));
        }
        
        /* Loop through to see if the sign changes between two points when they are evaluated. */
        for(double i = xmin; i < xmax; i += step) {
            if((evaluate(i) < 0 && evaluate(i+step) > 0) || (evaluate(i) > 0 && evaluate(i+step) < 0)) {
                double root = (i + (i+step))/2;
                rootsTemp.add(root);
            }
        }
        
        //Create an array of all of the real roots.
        double[] realRoots = new double[rootsTemp.size()];
        
        int k = 0;
        for(Double d : rootsTemp) {
        	realRoots[k] = d;
        	k++;
        }
        
        
        
        return realRoots;
    }
	
	
	
	/** Returns a Polynomial object that is the derivative of this polynomial. */
	public Polynomial getDerivative() {
		Polynomial derivative = null;
		ArrayList<String> newTerms = new ArrayList<String>();
		
		// Loop through the terms, coefficients, and exponents array and use the power rule.
		for(int i = 0; i < coefficients.length; i++) {
			if(terms[i].indexOf("x") > -1) {
				// The new term.
				String nTerm = "";
				
				// Using the power rule, take the original coefficient times the exponent
				// Then subtract one from the exponent.
				double nCoeff = coefficients[i] * exponents[i];
				double nExp = exponents[i] - 1;
			
				if(nExp == 0) {
					// An exponent of zero will just be a constant
					nTerm = "" + coefficients[i];
					newTerms.add(nTerm);
				} else if(nExp == 1) {
					// Don't show the exponent
					nTerm = "" + nCoeff + "x";
					newTerms.add(nTerm);
				} else {
					nTerm = "" + nCoeff + "x^" + nExp;
					newTerms.add(nTerm);
				}
			} else {
				// Nothing, because if it was a constant term it will go away.
			}
		}
		
		// Construct the polynomial string
		String poly = "";
		for(String s : newTerms) {
			poly += s;
		}
		
		derivative = new Polynomial(poly);
		
		return derivative;
	}



	@Override
	public String toString() {
		String poly = "";
		for(String term : terms) {
			poly += term;
		}
		if(poly.startsWith("+")) {
			poly = poly.substring(1);
		}
		return poly;
	}
}
