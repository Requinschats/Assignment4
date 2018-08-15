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
// ShowList class and ShowNode inner class
// Written by: Olivier Fradette-Roy 40074024 and Myriam Tayah 40074762
//--------------------------------------------------------------------

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;



import static java.lang.System.out;

/**
 * This class' purpose is to process different ShowList objects.
 * It holds the main method.
 */
public class ProcessWishlist {

	/**
	 * This method generates an array of TVShow objects.
	 * @param scanner. A scanner object to go through the lists.
	 * @param TVShows. An array of objects TVShow.
	 */
	public static void generateArrayOfTVShows(Scanner scanner, TVShow[] TVShows) {
		String line;
		int lineCount = 0;
		String showID = "";
		String showName = "";
		double startTime = 0;
		double endTime = 0;
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (lineCount % 4 == 0) {
				showID = line.substring(0, line.indexOf(" "));
				showName = line.substring(line.indexOf(" "), line.length());
				lineCount++;
			} else if (lineCount % 4 == 1) {
				startTime = Double.parseDouble(line.substring(line.indexOf(" "), line.length()));
				lineCount++;
			} else if (lineCount % 4 == 2) {
				endTime = Double.parseDouble(line.substring(line.indexOf(" "), line.length()));
				lineCount++;
			} else {
				TVShows[(lineCount + 1) / 4] = new TVShow(showID, showName, startTime, endTime);
				lineCount++;
			}

		}

	}

	/**
	 * This method generates an array list of interestShows.
	 * @param scanner. A scanner object.
	 * @param watchingShows. An ArrayList of shows being watched.
	 * @param interestShows. An ArrayList of shows which bring interest.
	 */
	public static void generateInterestShows(Scanner scanner, ArrayList<TVShow> watchingShows, ArrayList<TVShow> interestShows) {
		String line;
		int lineCount = 1;
		String showID = null;
		String showName = null;
		double startTime = 0;
		double endTime = 0;
		boolean watchingToWhishlist = false;
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.toString().equals("Wishlist")) {
				watchingToWhishlist = true;
			}

			if (!watchingToWhishlist) {

				if (lineCount % 3 == 1) {
					showID = line;
					TVShow temporary = new TVShow(showID, showName, startTime, endTime);
					interestShows.add(temporary);
					lineCount++;
				} else if (lineCount % 3 == 2) {
					showID = line;
					TVShow temporary = new TVShow(showID, showName, startTime, endTime);
					interestShows.add(temporary);
					lineCount++;

				} else {
					showID = line;
					TVShow temporary = new TVShow(showID, showName, startTime, endTime);
					interestShows.add(temporary);
					lineCount++;
				}
			}else{
				if (lineCount % 3 == 1) {
					showID = line;
					TVShow temporary = new TVShow(showID, showName, startTime, endTime);
					watchingShows.add(temporary);
					lineCount++;
				} else if (lineCount % 3 == 2) {
					showID = line;
					TVShow temporary = new TVShow(showID, showName, startTime, endTime);
					watchingShows.add(temporary);
					lineCount++;

				} else {
					showID = line;
					TVShow temporary = new TVShow(showID, showName, startTime, endTime);
					watchingShows.add(temporary);
					lineCount++;
				}
			}

		}
	}


	/**
	 * This method will fill up the ArrayList of interest shows with information held in the TV guide. 
	 * @param interestShows. ArrayList of TVShow objects.
	 * @param TVShowsInGuide. Array of TVShow objects.
	 */
	public static void fullFilTVShowsInformationFromTVGuide(ArrayList<TVShow> interestShows, TVShow[] TVShowsInGuide) {
		for (TVShow tvShow : TVShowsInGuide) {
			if (tvShow != null) {
				if (interestShows.stream().filter(o -> o.getShowID().equals(tvShow.getShowID())).findFirst().isPresent()) {
					int index =0;
					for (TVShow tvShowInInterest: interestShows){
						if(tvShowInInterest.getShowID().equals(tvShow.getShowID())) {
							index = interestShows.indexOf(tvShowInInterest);
						}
					}
					interestShows.set(index, tvShow);
				}
				else{
					out.print("");
				}
			}
		}
	}

	/**
	 * This method will adjust the TV guide to only hold possible TVShows.
	 * @param watchingShows. ArrayList of TVShow objects.
	 * @param TVShowsInGuide. Array of TVShow objects.
	 */
	public static void adjustTVGuideToContainOnlyPossibleTVShows(ArrayList<TVShow> watchingShows, TVShow[] TVShowsInGuide) {

		for (TVShow tvShowWatching : watchingShows) {
			double lowBound = tvShowWatching.getStartTime();
			double highBound = tvShowWatching.getEndTime();

			for (TVShow tvShowInGuide : TVShowsInGuide) {
				if(tvShowInGuide!=null) {
					if ((tvShowInGuide.getStartTime() < highBound && tvShowInGuide.getStartTime() >= lowBound) || (tvShowInGuide.getEndTime() > lowBound && tvShowInGuide.getEndTime() <= highBound)) {
						TVShowsInGuide[Arrays.asList(TVShowsInGuide).indexOf(tvShowInGuide)] = null;
					}
				}
			}

		}
	}

	/**
	 * This method will print whether the user can or can not watch a certain TVShow in which they have interest.
	 * @param wishlistShows. ArrayList of TVShow objects.
	 * @param TVShowsInGuide. Array of TVShow objects.
	 * @param watchinShows. ArrayList of TVShow objects.
	 */
	public static void printResultOnInterest(ArrayList<TVShow> wishlistShows, TVShow[] TVShowsInGuide, ArrayList<TVShow> watchinShows) {
	    out.println();
        List<TVShow> toRemove = new ArrayList<TVShow>();
		for(TVShow wishListShow: wishlistShows) {
            for (int i = 0; i < TVShowsInGuide.length; i++) {
                if (TVShowsInGuide[i] == wishListShow){
                    out.println("The user can watch the show: " + wishListShow.getShowID() + " as he/she is not watching anything else during that time.");
                    toRemove.add(wishListShow);
                }

            }
        }

        wishlistShows.removeAll(toRemove);
        for(TVShow wishListShow: wishlistShows) {
            if(wishListShow.getShowName() !=null){
                out.println("The user can not watch the show: "+ wishListShow.getShowID() + showSuffix(wishListShow, watchinShows));
            }

        }
	}

	/**
	 * This method will print a suffix to the statement indicating whether or not the user can watch a show.
	 * @param wishListShow. 
	 * @param watchingShows
	 * @return A string... the suffix.
	 */
	public static String showSuffix (TVShow wishListShow, ArrayList<TVShow> watchingShows) {
        for (TVShow watchingShow : watchingShows) {
            if (watchingShow != null) {
                if (wishListShow.getStartTime() == watchingShow.getStartTime()) {
                    return (" as he/she will begin watching another show at the same time.");
                } else if (wishListShow.getStartTime() < watchingShow.getEndTime() && wishListShow.getStartTime() >= watchingShow.getStartTime()) {
                    return (" as he/she is not finished with another show he/she is watching.");
                } else if (wishListShow.getEndTime() > watchingShow.getStartTime() && wishListShow.getEndTime() <= watchingShow.getEndTime()) {
                    return (" as he/she is not finished with another show he/she is watching.");
                }

            }
        }
        return (" this show or the creators of this program are lost.");
    }
	
	//TODO
	/**
	 * This method allows us to find a show according to its ID.
	 * @param interest. A ShowList object.
	 * @return A String holding the name of the show and the number of iterations.
	 * @throws NullPointerException
	 */
		public static String findShowbyID(ArrayList<TVShow> interestShows) throws NullPointerException {	// input file = Interest.txt
			out.println();
			Scanner keyIn = new Scanner(System.in);
			out.print("Please enter a TV Show ID for searching:");
			String show = keyIn.next();
			
			int counter = 1;
			for (int i = 0 ; i < interestShows.size(); i++ ) {
				if (interestShows.get(i).getShowID() == show) {
					return ("The TV show you have entered through "+show+" has been found after "+counter+" interations.");
				}
				else 
					counter++;
			}
			keyIn.close();
			return ("Nothing has been found with show ID: "+show+ ".");
		}

		/**
		 * This method prompts the user for information about a show they might want to create.
		 * @return A TVShow object.
		 */
		public static TVShow createShow() {
			Scanner keyboard = new Scanner (System.in);
			out.print("Enter show name (no spaces, use underscore) : ");
			String name = keyboard.next();
			out.print("Enter show ID: ");
			String id = keyboard.next();
			out.print("Enter show start time and end time (separate by space)");
			double start = keyboard.nextDouble();
			double end = keyboard.nextDouble();
			keyboard.close();
			
			return (new TVShow(id, name, start, end));
		}
		
		/**
		 * This method prompt the user for a new show ID to use when copying a TVShow object.
		 * @param obj. A TVShow object.
		 * @return A TVShow object.
		 */
		public static TVShow copyShow(TVShow obj) {
			Scanner keyboard = new Scanner (System.in);
			out.print("Enter new show ID: ");
			String id = keyboard.next();
			keyboard.close();
		
			return(new TVShow(obj, id));
		}
		
		/**
		 * This method allows the comparison of two TVShow objects.
		 * @param a1. ATVShow object.
		 * @param a2. A TVShow object.
		 * @return True if equal, false otherwise.
		 */
		public static String sameShow(TVShow a1, TVShow a2) {
			if (a1.equals(a2)) {
				return (a1.getShowName()+" equals "+a2.getShowName());
			}
			else 
				return (a1.getShowName()+" does not equal "+a2.getShowName());
		}

		/**
		 * This main method contains a String of arguments which will be executed through the program.
		 * @param args
		 */
		public static void main(String[] args) {

			//IV)

			// a)
			ShowList tvGuide = new ShowList();
			ShowList interest = new ShowList();
			TVShow[] tvShowsInGuide = new TVShow[200];

			File interestFile = new File("Interest.txt"); //TODO should paths be a string variable from input?
			File TVGuideFile = new File("TVGuide.txt");

			Scanner tvGuideScanner = null;
			Scanner interestScanner = null;

			try {
				tvGuideScanner = new Scanner(new FileInputStream(TVGuideFile));
				interestScanner = new Scanner(new FileInputStream(interestFile));
			} catch (FileNotFoundException e) {
				System.err.println(e.getStackTrace());
				out.println("files not found");
			}

			//b)
			generateArrayOfTVShows(tvGuideScanner, tvShowsInGuide);
			out.println(tvShowsInGuide[1]);
			out.println(tvShowsInGuide[2]);
			out.println(tvShowsInGuide[1] == tvShowsInGuide[2]);
			HashSet<TVShow> tvShowsSet = new HashSet<>();
			for (TVShow tvShow : tvShowsInGuide) {
				if (!tvShowsSet.contains(tvShow) && tvShow!=null) {
					tvShowsSet.add(tvShow);
					tvGuide.addToStart(tvShow);
				}
			}

			//c)
			ArrayList<TVShow> wishListShows = new ArrayList<>();
			ArrayList<TVShow> watchingShows = new ArrayList<>();

			generateInterestShows(interestScanner,watchingShows, wishListShows);

			fullFilTVShowsInformationFromTVGuide(wishListShows, tvShowsInGuide);
			fullFilTVShowsInformationFromTVGuide(watchingShows, tvShowsInGuide);

			adjustTVGuideToContainOnlyPossibleTVShows(watchingShows, tvShowsInGuide);

			printResultOnInterest(wishListShows, tvShowsInGuide, watchingShows);


			//d)

	        findShowbyID(wishListShows);
			findShowbyID(wishListShows);
			findShowbyID(wishListShows);
			findShowbyID(wishListShows);

			//e) TODO
			
			/*
			 *  TVShow class methods
			 * DONE - create 2 tv show object according to user preferences
			 * DONE - copy show1, ask user for new showID --> show3
			 * DONE - clone show2... TODO --> show4
			 * DONE - toString info for all 4
			 * DONE - compare show1 and show4
			 * DONE - check if show 1 and show 2 are on same time
			 */
			out.println("Creating 1st TV Show");
			TVShow show1 = createShow();
			out.println("Creating 2nd TV Show");
			TVShow show2 = createShow();
			
			out.println("Creating 3rd TV Show by copying the first");
			TVShow show3 = copyShow(show1);
			
			out.println("Creating 4th TV Show by cloning the second");
			TVShow show4 = show3.clone();
			
			show1.toString();
			show2.toString();
			show3.toString();
			show4.toString();
			
			sameShow(show1, show4);
			
			show1.isOnSameTime(show2);
			
			/*
			 * ShowList class methods
			 * DONE - create default ShowList (list1)
			 * DONE - copy list1 (list2)
			 * DONE - addtostart of list1 (using show1)
			 * DONE - addtostart of list2(show3)
			 * DONE - insertatindex(show2, int 1)
			 * DONE - insertatindex(show4, int 2)
			 * DONE - insertatindex(show3, int 3)
			 * DONE - insertatindex(show1, int 4)
			 * DONE - insertatindex(show4, int 5)
			 * DONE - deletefromindex(2)
			 * DONE - deletefromstart
			 * DONE - replaceatindex(show3, 5)
			 * DONE - ask user for show id, find
			 * DONE - ask user for show id, contains
			 * DONE - equals(list1, list2)
			 */
			
			ShowList list1 = new ShowList();
			ShowList list2 = new ShowList(list1);
			list1.addToStart(show1);
			list2.addToStart(show3);
			list1.insertAtIndex(show2, 1);
			list1.insertAtIndex(show4, 2);
			list1.insertAtIndex(show3, 3);
			list1.insertAtIndex(show1, 4);
			list1.insertAtIndex(show4, 5);
			list1.deleteFromIndex(2);
			list1.deleteFromStart();
			list1.replaceAtIndex(show3, 5);
			
			Scanner keyboard = new Scanner(System.in);
			out.print("Enter a showID: ");
			String anID = keyboard.next();
			list1.find(anID).toString();
			out.print("Enter another showID");
			String anotherID = keyboard.next();
			list1.contains(anotherID);
			keyboard.close();
			list1.equals(list2);
			
		}

	}