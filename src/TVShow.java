import java.util.Scanner;

import static java.lang.System.out;

public class TVShow implements Watchable, Cloneable {

	private String showID; 			// unique
	private String showName; 		// Single word... if multiple they are separated by _
	private double startTime;
	private double endTime;

	// accessors

	public double getEndTime() {
		return endTime;
	}

	public double getStartTime() {
		return startTime;
	}

	public String getShowID() {
		return showID;
	}

	public String getShowName() {
		return showName;
	}

	// mutators
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

	public void setShowID(String showID) {
		this.showID = showID;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	// a) parameterized constructor
	public TVShow(String showID, String showName, double startTime, double endTime) {
		this.endTime = endTime;
		this.showID = showID;
		this.showName = showName;
		this.startTime = startTime;
	}

	// b) Copy Constructor
	public TVShow(TVShow s2, String newShowID) {
		this.endTime = s2.endTime;
		this.showName = s2.showName;
		this.startTime = s2.startTime;
		this.showID = newShowID;
	}

	//TODO c) clone method --> NOT CORRECT
	public TVShow clone() {
		Scanner scanner = new Scanner(System.in);
		String newShowID;
		out.println("Enter a new show ID: ");
		newShowID = scanner.next();
		TVShow TVShowClone = new TVShow(newShowID, showName, startTime, endTime);
		scanner.close();
		return TVShowClone;
	}

	// d) toString method
	public String toString() {
		return ("Show ID: "+ showID + "\nShowName: "+ showName +"\nStart time: "+ startTime + "\nEnd time: "+ endTime);
	}

	// d) equals method
	public boolean equals(TVShow obj) {
		return (obj.getEndTime() == endTime && obj.getStartTime() == startTime && obj.getShowName() == showName);
	}

	// e) isOnSameTime method
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
