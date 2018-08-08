import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.Set;

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

    public static void generateArrayListOfWatchinjTVShows(Scanner scanner,ArrayList<TVShow> watchingShows, ArrayList<TVShow> interestShows) {
        String line;
        int lineCount = 0;
        String showID = "";
        String showName = "";
        double startTime = 0;
        double endTime = 0;
        boolean watchingToWhishlist = false;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.startsWith("Wishlist")) {
                watchingToWhishlist = true;
            }

            if (!watchingToWhishlist) {

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
                    TVShow temporary = new TVShow(showID, showName, startTime, endTime);
                    interestShows.add(temporary);
                }

            }else{
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
                    TVShow temporary = new TVShow(showID, showName, startTime, endTime);
                    watchingShows.add(temporary);
                }
            }
        }

    }

    //replaced under b)
    public List<TVShow> duplicates(TVShow[] tvShowsArray) {
        Set<TVShow> tvShowsSet = new HashSet<>();
        List<TVShow> duplicates = new LinkedList<>();
        for (TVShow tvShow : tvShowsArray) {
            if (tvShowsSet.add(tvShow) == false) {
                duplicates.add(tvShow);
            }
        }
        return duplicates;
    }


    public static void main(String[] args) {

        //IV)

        ShowList TVGuide = new ShowList();
        ShowList interest = new ShowList();
        TVShow[] TVShows = new TVShow[100];

        File interestFile = new File("Interest.txt");
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
        generateArrayOfTVShows(TVGuideScanner, TVShows);
        Set<TVShow> tvShowsSet = new HashSet<>();
        for (TVShow tvShow : TVShows) {
            if (tvShowsSet.add(tvShow) == true) {
                tvShowsSet.add(tvShow);
                TVGuide.addToStart(tvShow);
            }
        }

        //c)
        ArrayList <TVShow> interestShows = new ArrayList<>();
        ArrayList <TVShow> watchingShows = new ArrayList<>();
        generateArrayListOfWatchinjTVShows(interestScanner, watchingShows, interestShows);
    }

}
