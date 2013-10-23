/*********************************
*
* Student: Mark A. Tyler II
*Course: CISC 3150 - Spring 2013
*Object-Oriented Programming
*Assignment 2 - Luhn's Algoritm - MOD10
*********************************/
import java.util.Scanner;
import java.lang.*;
	    
	    public class creditCardChecker {
	    	//Simple method to determine cardType. Built in string method called startWith()
	    	//if the string matches the initial string, return proper string.
	        public static String cardType (String s){
	                if (s.startsWith("4")){
	                    return "Visa";
	                }
	                else if (s.startsWith("5")){
	                    return "MasterCard";
	                }
	                else if (s.startsWith("37")){
	                    return "American Express";
	                }
	                else if (s.startsWith("6")){
	                      return "Discover";
	                }
	                else 
	                    return "Unknown card type";
	         }
	       
	    	//Extract a digit from long
	    	public static int separateDigit(long number, int position) {
	    		//call the toString method to convert the long to a string
	    		String x = Long.toString(number);
	    		//extract digit at a given position.
	    		int temp = x.charAt(position) - '0';
	    		//return the value at the given position passed into te method/function 
	    		return temp;
	    		
	    	}//End of separate digit
	    	
	    	/**
	    	* Function takes the total of everything and checks if it's divisible by ten, 
	    	* If total is divisible by ten return true else false. This function, like it's 
	    	* definition tells us if the credit card is valid on top of many other checks.
	    	**/
	        public static boolean isValid(long total) {
	            if (total % 10 == 0) {
	                return true;
	            }
	            return false;
	        }//End of isValid method
	        
	        //Verify that there is sufficient digits 
	        public static boolean isEnoughDigits(String s){
	        	//Between 13 and 16 digits inclusive.
	        	if(s.length() >=13 && s.length() <=16){
	        		return true;
	        	}else{
	        		//Sanity check
	        		//System.out.println("Insufficient Digits: " + s.length());
	        		return false;
	        	}
	        }//end of isEnoughDigits method
	        
	        /**
	        * getTotal function retrieves the complete total of the sumOfOddPlaces()
	        * and the sumOfEvenPlaces(). With this total we can determine if divisible by 
	        * whether this card is valid or not. 
	        **/
	        //getTotal function is taking a long as it's parameter. It's taking the
	        //card in this particular case.
	    	public static int getTotal(long number){
	    		int total = sumAndDoubleOfEvenPlace(number) + sumOfOddPlaces(number);
	    		return total;
	    	}//end of getTotal function
	        
	        /** 
	        * Return this number if it is a single digit, 
	        * otherwise, return the sum of the two digits 
	        * 
	        *  i.e. if we encounter the number 23,
	        *  23 % 10 = 3
	        *  23 / 10 = 2
	        *  
	        *  3 + 2 = 5
	        * 
	        **/
	    	//get as one digit 
	        public static int getAsOneDigit(int number) {
	            if (number > 9) {
	                return (number % 10 + number / 10);
	            }
	            return number;
	        }//end of get as one digit or get digit function
	      
	        //sum of odd places function
	        public static int sumOfOddPlaces(long number) {
	        	int result = 0;
	    		String s = Long.toString(number);
	    		
	    		for(int i = s.length() - 1; i >= 0; i--){
	    			result = separateDigit(number, i);
	    			//result = (int)Long.parseLong(s.substring(i, i + 1));
	    			result += getAsOneDigit(result);
	    			
	    		}
	    		return result;
	    		
	    		
	        }//end of sum of odd places
	        
	        //sum of even places
	        public static int sumAndDoubleOfEvenPlace(long number) {
	            int sum = 0;
	            int remainder;
	            
	            while (number % 10 != 0 || number / 10 != 0) {
	                remainder = (int) (number % 10);
	                //double remainder
	                remainder *=2;
	                //add up everybody
	                sum += getAsOneDigit(remainder);
	                //extract already combined digit
	                number /= 100;
	            }
	            return sum;
	        }//end of the sum of even places
	        
	        public static void main(String args[]) {
	        	
	        	Scanner input = new Scanner(System.in);	    		
	    		System.out.println("Enter Credit Card Number: ");
	            
	    		//Retrieve each value bit by bit from the string representation.
	    		String s = input.nextLine();
	    		//parse out the string as a long
	    		long number = Long.parseLong(s);
	    		long size = (long)s.length();
	    		
	    		//Create a variable total of type long as a holder.
	    		//Add the even and odd places...
	            //total = sumOfEvenPlaces(number) + sumOfOddPlaces(number);
	            //Not needed because we have a get total function	    	
	
	            if(isValid(number) && isEnoughDigits(s)) {
	            	
	            	System.out.println(number + " is a valid " + cardType(s) + " Card.\n");
	            	
	            	System.out.println("************ Even | Odd | Total *************");
	            	System.out.println("Even Places:  " + sumAndDoubleOfEvenPlace(number));
	            	System.out.println("Odd Places:   " + sumOfOddPlaces(number));
	            	System.out.println("Total :       " + getTotal(number));
	            	System.out.println("\n");
	            	
	            	System.out.println("*************** Card Number ****************");
	            	System.out.println("               " + number + "               ");
	            	System.out.println("\n");
	            	
	            	System.out.println("*************** Card Type ******************");
	            	System.out.println("            " + cardType(s) + "             ");
	            	System.out.println("\n");
	            	
	            
	            } else {
	            	
	            	System.out.println("You entered an invalid Card.");
	            	System.out.println("You entered a(n) "+ size +" digit card.\n");
	            	
	            	System.out.println("************ Even | Odd | Total *************");
	            	System.out.println("Even Places:  " + sumAndDoubleOfEvenPlace(number));
	            	System.out.println("Odd Places:   " + sumOfOddPlaces(number));
	            	System.out.println("Total :       " + getTotal(number));
	            	System.out.println("********************************************\n");
	            	
	            	System.out.println("*************** Card Type ******************");
	            	System.out.println("            " + cardType(s) + "             ");
	            	System.out.println("\n");

	            	System.out.println("********* The last 5 Digits ****************");
	            	System.out.println("Separate Digit: " + separateDigit(number, 1));
	            	System.out.println("Separate Digit: " + separateDigit(number, 2));
	            	System.out.println("Separate Digit: " + separateDigit(number, 3));
	            	System.out.println("Separate Digit: " + separateDigit(number, 4));
	            	System.out.println("Separate Digit: " + separateDigit(number, 5));
	            	System.out.println("********************************************\n");
	            }
	        }
	    }