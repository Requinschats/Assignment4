/**
 * Names and IDs:
 * 		Myriam Tayah (40074762)
 * 		Olivier Fradette-Roy (40074024)
 * COMP 249
 * Assignment #4
 * Due Date : Saturday August 11, 2018
 */

//--------------------------------------------------------------------
// Assignment 4
// Watchable interface
// Written by: Olivier Fradette-Roy 40074024 and Myriam Tayah 40074762
//--------------------------------------------------------------------

/**
 * This is an interface named Watchable.
 * It is public and will be used in the TVShow class.
 */
public interface Watchable {
	
	/**
	 * This method will be defined further in the classes that implement this interface.
	 * @param S An object of type TVShow.
	 * @return It returns a String.
	 */
	public String isOnSameTime(TVShow S);
	
}
