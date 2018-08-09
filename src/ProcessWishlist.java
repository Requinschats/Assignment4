import java.io.File;
import java.io.FileInputStream;
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
                startTime = Double.parseDouble(line.substring(0, line.indexOf(" ")));
                lineCount++;
            } else if (lineCount % 4 == 2) {
                endTime = Double.parseDouble(line.substring(0, line.indexOf(" ")));
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
            if (line.startsWith("Wishlist")) {
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
            }

        }
    }


    public static void fullFilTVShowsInformationFromTVGuide(ArrayList<TVShow> interestShows, TVShow[] TVShowsInGuide) {
        for (TVShow tvShow : TVShowsInGuide) {
            if (interestShows.contains(tvShow.getShowID())) {
                interestShows.set(interestShows.indexOf(interestShows.contains(tvShow.getShowID())), tvShow);
            }
        }
    }


    public static void adjustTVGuideToContainOnlyPossibleTVShows(ArrayList<TVShow> watchingShows, TVShow[] TVShowsInGuide) {

        for (TVShow tvShowWatching : watchingShows) {
            double lowBound = tvShowWatching.getStartTime();
            double highBound = tvShowWatching.getEndTime();

            for (TVShow tvShowInGuide : TVShowsInGuide) {
                if ((tvShowInGuide.getStartTime() < highBound && tvShowInGuide.getStartTime() >= lowBound) || (tvShowInGuide.getEndTime() > lowBound && tvShowInGuide.getEndTime() <= highBound)) {
                    tvShowInGuide = null;
                }

            }
        }
        int newLength = 0;
        for (TVShow tvShowInGuide : TVShowsInGuide) {
            if (tvShowInGuide != null) {
                newLength++;
            }
        }
        TVShow[] tempArray = new TVShow[newLength];
        for (int i = 0; i < TVShowsInGuide.length; i++) {
            if (TVShowsInGuide[i] != null) {
                tempArray[i] = TVShowsInGuide[i];
            }
        }
        TVShowsInGuide = Arrays.copyOf(tempArray, newLength);
    }

    public static void printResultOnInterest(ArrayList<TVShow> interestShows, TVShow[] TVShowsInGuide) {

        for (TVShow tvShow : TVShowsInGuide) {
            if (interestShows.contains(tvShow)) {
                out.println(" The user can watch show: " + tvShow.getShowID());
            } else {
                out.println(" The user can not watch show: " + tvShow.getShowID());
            }
        }
    }


    public static void main(String[] args) {

        //IV)

        // a)
        ShowList TVGuide = new ShowList();
        ShowList interest = new ShowList();
        TVShow[] TVShowsInGuide = new TVShow[100];

        File interestFile = new File("Interest.txt"); //TODO should paths be a string variable from input?
        File TVGuideFile = new File("TVGuide.txt");

        Scanner TVGuideScanner = null;
        Scanner interestScanner = null;

        try {
            TVGuideScanner = new Scanner(new FileInputStream(TVGuideFile));
            interestScanner = new Scanner(new FileInputStream(interestFile));
        } catch (FileNotFoundException e) {
            System.err.println(e.getStackTrace());
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
        ArrayList<TVShow> interestShows = new ArrayList<>();
        ArrayList<TVShow> watchingShows = new ArrayList<>();

        generateInterestShows(interestScanner, watchingShows, interestShows);

        fullFilTVShowsInformationFromTVGuide(interestShows, TVShowsInGuide);
        fullFilTVShowsInformationFromTVGuide(watchingShows, TVShowsInGuide);

        adjustTVGuideToContainOnlyPossibleTVShows(watchingShows, TVShowsInGuide);

        printResultOnInterest(interestShows, TVShowsInGuide);


        //d)

        //e)

    }

}
