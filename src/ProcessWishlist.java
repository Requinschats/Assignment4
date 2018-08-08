import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProcessWishlist {

    public static void initializeTVShows(Scanner scanner, TVShow[] TVShows) {
        String line
        int lineCount = 0;
        String showID ="";
        String showName="";
        double startTime=0;
        double endTime=0;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (lineCount % 4 == 0) {
                showID = line.substring(0,line.indexOf(" "));
                showName = line.substring(line.indexOf(" "), line.length());
                lineCount++;
            } else if (lineCount % 4 == 1) {
                startTime = Double.parseDouble(line.substring(0,line.indexOf(" ")));
                lineCount++;
            } else if (lineCount % 4 == 2) {
                endTime =Double.parseDouble(line.substring(0, line.indexOf(" ")));
                lineCount++;
            } else {
                TVShows[(lineCount+1)/4] = new TVShow(showID,showName,startTime,endTime);
                lineCount++;
            }

        }

    }


    public static void main(String[] args) {

        ShowList TVGuide;
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

        initializeTVShows(TVGuideScanner, TVShows);
        for(TVShow tvShow: TVShows) {
            TVGuide = new ShowList(tvShow);
        }

    }


}
