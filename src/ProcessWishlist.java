import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.Set;



import static java.lang.System.out;

public class ProcessWishlist {

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
		/*        int newLength = 1;
        for (TVShow tvShowInGuide : TVShowsInGuide) {
            if (tvShowInGuide != null) {
                newLength++;
            }
        }
        TVShow[] tempArray = new TVShow[newLength];
        for (int i = 1; i < TVShowsInGuide.length; i++) {
            if (TVShowsInGuide[i] != null) {
                tempArray[i] = TVShowsInGuide[i];
            }
        }
        TVShowsInGuide = Arrays.copyOf(tempArray, newLength);*/
	}

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

	public static String showSuffix (TVShow wishListShow, ArrayList<TVShow> watchingShows) {

        double lowBound = wishListShow.getStartTime();
        double highBound = wishListShow.getEndTime();

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
	public static String findShowbyID(ShowList interest) throws NullPointerException {	// input file = Interest.txt
		out.println();
		Scanner keyIn = new Scanner(System.in);
		int count = 0;
		out.print("Please enter a TV Show ID for searching: ");
		String show = keyIn.next();
		keyIn.close();
		if (interest.find(show) != null) {
			count = interest.getNumIterations();
			return ("The TV show you have entered through "+show+" has been found after "+count+" interations.");
		}
		else 
			return ("Nothing has been found with "+show+".");
	}

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
	
	public static TVShow copyShow(TVShow obj) {
		Scanner keyboard = new Scanner (System.in);
		out.print("Enter new show ID: ");
		String id = keyboard.next();
		keyboard.close();
	
		return(new TVShow(obj, id));
	}
	
	public static String sameShow(TVShow a1, TVShow a2) {
		if (a1.equals(a2)) {
			return (a1.getShowName()+" equals "+a2.getShowName());
		}
		else 
			return (a1.getShowName()+" does not equal "+a2.getShowName());
	}

	public static void main(String[] args) {

		//IV)

		// a)
		ShowList TVGuide = new ShowList();
		ShowList interest = new ShowList();
		TVShow[] TVShowsInGuide = new TVShow[20];

		File interestFile = new File("Interest.txt"); //TODO should paths be a string variable from input?
		File TVGuideFile = new File("TVGuide.txt");

		Scanner TVGuideScanner = null;
		Scanner interestScanner = null;

		try {
			TVGuideScanner = new Scanner(TVGuideFile);
			interestScanner = new Scanner(interestFile);
		} catch (FileNotFoundException e) {
			System.err.println(e.getStackTrace());
			out.println("files not found");
		}

		//b)
		generateArrayOfTVShows(TVGuideScanner, TVShowsInGuide);
		Set<TVShow> tvShowsSet = new HashSet<>();
		for (TVShow tvShow : TVShowsInGuide) {
			if (tvShowsSet.add(tvShow) == true) {
				tvShowsSet.add(tvShow);
				TVGuide.addToStart(tvShow);
			}
		}

		//c)
		ArrayList<TVShow> wishListShows = new ArrayList<>();
		ArrayList<TVShow> watchingShows = new ArrayList<>();

		generateInterestShows(interestScanner, wishListShows, watchingShows);

		fullFilTVShowsInformationFromTVGuide(wishListShows, TVShowsInGuide);
		fullFilTVShowsInformationFromTVGuide(watchingShows, TVShowsInGuide);

		adjustTVGuideToContainOnlyPossibleTVShows(watchingShows, TVShowsInGuide);

		printResultOnInterest(wishListShows, TVShowsInGuide, watchingShows);


		//d)

            findShowbyID(interest);

		findShowbyID(interest);
		findShowbyID(interest);
		findShowbyID(interest);

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
		
		
		
		
		/*
		 * ShowNode inner class methods 											
		 * - create 1 showNode default (node1)									// DONT KNOW IF NECESSARY CUS METHOD ALREADY INCLUDED WITHIN SHOWLIST CLASS METHODS
		 * - create 2 showNode parameterized ( node2 (show2) node3(show3) )		// DONT KNOW IF NECESSARY CUS METHOD ALREADY INCLUDED WITHIN SHOWLIST CLASS METHODS
		 * - copy node1 (node4)													// DONT KNOW IF NECESSARY CUS METHOD ALREADY INCLUDED WITHIN SHOWLIST CLASS METHODS
		 * - clone node3 (node5)													// DONT KNOW IF NECESSARY CUS METHOD ALREADY INCLUDED WITHIN SHOWLIST CLASS METHODS
		 * 
		 * 
		 */
		

	}

}