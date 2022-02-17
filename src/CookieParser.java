import java.io.*;
import java.util.HashMap;

public class CookieParser {

    // name of CSV file to parse for cookies
    String filename;

    // current date we're searching for
    String date;

    public CookieParser(String[] args) {
        // parse args and store them into fields
        parseArgs(args);
    }

    // parse command line arguments and store them into class fields
    private void parseArgs(String[] args) {
        // store filename of CSV file and date into class fields
        this.filename = args[0];
        this.date = args[2];
    }

    // parses the CSV file and prints the most active cookies on
    // the date stored in the class field
    private void displayMostActiveCookies() {
        // Map that stores the frequency of each cookie on the current day
        HashMap<String, Integer> cookieFrequency = new HashMap<>();

        // current line being read
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            // skip first line which represents format of CSV data
            br.readLine();

            // read file line by line
            while ((line = br.readLine()) != null) {
                // split line by "," and store cookie, timestamp
                String[] lineContents = line.split(",");
                String cookie = lineContents[0];
                String timestamp = lineContents[1];

                // extract the date from the timestamp
                String cookieDate = extractDateFromTimeStamp(timestamp);

                // if cookieDate == date, increment the frequency of the date in the map
                if (cookieDate.equals(date)) {
                    // if cookie already exists in map, increment its frequency, else put it in the map
                    // with a frequency of 1
                    cookieFrequency.put(cookie, cookieFrequency.getOrDefault(cookie, 0) + 1);
                }
            }

            // get the integer value of the most frequent cookies
            int frequencyOfMostActiveCookie = getFrequencyOfMostActiveCookie(cookieFrequency);

            // get the most active cookies and display them each on a new line
            printMostActiveCookies(cookieFrequency, frequencyOfMostActiveCookie);
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.printf("File %s not found!\n", filename);
        } catch (IOException e){
            // e.printStackTrace();
        }

    }

    private void printMostActiveCookies(HashMap<String, Integer> cookieFrequency, int frequencyOfMostActiveCookie) {
        // make one pass through map, and if the frequency of the current cookie is equal to the
        // frequency of the most active cookies, print them on a new line
        for (String cookie : cookieFrequency.keySet()) {
            int frequency = cookieFrequency.get(cookie);
            if (frequency == frequencyOfMostActiveCookie) {
                System.out.println(cookie);
            }
        }

        // print a new line for formatting
        // System.out.println();
    }

    // returns the frequency of the most active cookie(s)
    private int getFrequencyOfMostActiveCookie(HashMap<String, Integer> cookieFrequency) {
        int highestFrequency = 0;

        // make one pass through cookieFrequency map to find the highest frequency
        for (String cookie : cookieFrequency.keySet()) {
            int frequency = cookieFrequency.get(cookie);
            if (frequency > highestFrequency) {
                highestFrequency = frequency;
            }
        }

        return highestFrequency;
    }

    // Extracts the date from a timestamp
    private String extractDateFromTimeStamp(String timestamp) {
        // start index is 1 index after the first comma
        int startIndex = timestamp.indexOf(",") + 1;

        // end index is the index of the first "T", starting from startIndex
        int endIndex = timestamp.indexOf("T", startIndex);

        // get the date using substring then return the date
        String date = timestamp.substring(startIndex, endIndex);
        return date;
    }

    // Main method that creates a cookie parser object with the command line arguments,
    // then displays the most active cookies
    public static void main(String[] args) {
        // check if we have all 3 arguments (csv file, -d flag, date)
        // check if the 2nd argument is "-d"
        if (args.length != 3 || !args[1].equals("-d")) {
            System.exit(1); // exit with error
        }

        // create CookieParser object to parse the cookie
        CookieParser cookieParser = new CookieParser(args);
        cookieParser.displayMostActiveCookies();
    }
}
