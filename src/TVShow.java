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
// TVShow class
// Written by: Olivier Fradette-Roy 40074024 and Myriam Tayah 40074762
//--------------------------------------------------------------------

import java.util.Scanner;

import static java.lang.System.out;

/**
 * This class allows the creation of objects of type TVShow.
 * The class implements two interfaces: Watchable & Cloneable.
 */
public class TVShow implements Watchable, Cloneable {

	/**
	 * Objects of type TVShow have 4 attributes.
	 * The attributes include 2 variables of type String and 2 variables of type double.
	 */
	private String showID; 			// unique per object
	private String showName; 		// Single word... if multiple they are separated by _
	private double startTime;
	private double endTime;

	// accessors

	/**
	 * This accessor method allows the retrieval of the end time of a TVShow object.
	 * @return endTime. A double variable.
	 */
	public double getEndTime() {
		return endTime;
	}

	/**
	 * This accessor method allows the retrieval of the start time of a TVShow object.
	 * @return startTime. A double variable.
	 */
	public double getStartTime() {
		return startTime;
	}

	/**
	 * This accessor method allows the retrieval of the showID of a TVShow object.
	 * @return showID. A String variable.
	 */
	public String getShowID() {
		return showID;
	}

	/**
	 * This accessor method allows the retrieval of the name of the show of a TVShow object.
	 * @return showName. A String variable.
	 */
	public String getShowName() {
		return showName;
	}

	// mutators

	/**
	 * This mutator method allows the assignment of the end time of a TVShow object.
	 * @param endTime. A double variable.
	 */
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

	/**
	 * This mutator method allows the assignment of showID of a TVShow object.
	 * @param showID. A String variable.
	 */
	public void setShowID(String showID) {
		this.showID = showID;
	}

	/**
	 * This mutator method allows the assignment of the show name of a TVShow object.
	 * @param showName. A String variable.
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}

	/**
	 * This mutator method allows the assignment of the start time of a TVShow object.
	 * @param startTime. A double variable.
	 */
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	// a) parameterized constructor
	/**
	 * This constructor is parameterized and will create a TVShow object with the passing attributes.
	 * @param showID. Show ID, String variable, attribute of TVShow objects.
	 * @param showName. Name of TV Show, String variable, attribute of TVShow objects.
	 * @param startTime. Start time of TV Show, double variable, attribute of TVShow objects.
	 * @param endTime. End time of TV Show, double variable, attribute of TVShow objects.
	 */

	public TVShow(String showID, String showName, double startTime, double endTime) {
		this.endTime = endTime;
		this.showID = showID;
		this.showName = showName;
		this.startTime = startTime;
	}

	// b) Copy Constructor
	/**
	 * This constructor will create an object of type TVShow by copying one that is passed as a parameter.
	 * The only attribute that will defer between the two objects is the showID.
	 * @param s2. Object of type TVShow.
	 * @param newShowID. String variable for new showID.
	 */
	public TVShow(TVShow s2, String newShowID) {
		this.endTime = s2.endTime;
		this.showName = s2.showName;
		this.startTime = s2.startTime;
		this.showID = newShowID;
	}

	// c) clone method
	/**
	 * This clone method will create a clone of the calling TVShow object.
	 * @return TVShowClone. A TVShow object, clone of calling object.
	 */
	public TVShow clone() {
		Scanner scanner = new Scanner(System.in);
		String newShowID;
		out.println("Enter a new show ID: ");
		newShowID = scanner.next();
		TVShow TVShowClone = new TVShow(newShowID, this.showName, this.startTime, this.endTime);
		scanner.close();
		return TVShowClone;
	}

	// d) toString method
	/**
	 * This toString method will return a String containing all the attributes of a TVShow object.
	 * @return String.
	 */
	public String toString() {
		return ("Show ID: "+ showID + "\nShowName: "+ showName +"\nStart time: "+ startTime + "\nEnd time: "+ endTime);
	}

	// d) equals method
	/**
	 * This equals method will return true if the calling object is equal to the one that's passed.
	 * @param obj. A TVShow object, passed object.
	 * @return True if everything is equal (not including showID), false otherwise.
	 */
	public boolean equals(TVShow obj) {
		if (this.endTime == obj.endTime && this.showName == obj.showName && this.startTime == obj.startTime)
			return true;
		else 
			return false;
	}
	

	// e) isOnSameTime method
	/**
	 * This method will compare the start and end times of the calling TVShow object to the passed one.
	 * @return A String.
	 */
	public String isOnSameTime(TVShow S) {
		if (this.getStartTime() == S.getStartTime() && this.getEndTime() == S.getEndTime()) {
			return ("Same time");
		}
		else if ((this.getStartTime() >= S.getEndTime() && this.getEndTime() <= S.getStartTime()) || (this.getEndTime() >= S.getStartTime() && this.getStartTime() <= S.getEndTime())) {
			return ("Some overlap");
		}
		else
			return ("Different time");
	}


}
