// test cases for the cookie parser
public class CookieParserTester {

    // total number of tests passed and total number of tests ran
    public static int testsPassed = 0;
    public static int totalTests = 0;

    // Main method that creates a cookie parser object with the command line arguments,
    // then runs tests on the cookie parser
    public static void main(String[] args) {
        // run command line parsing tests
        test_1();

        // run cookie parsing tests
        test_2();

        // output the final score
        System.out.println("\n**********************");
        System.out.println("\nTotal test cases passed: " + testsPassed + "/" + totalTests);
    }

    // test for command line parsing
    private static void test_1() {
        System.out.println("\n***** Begin Test 1 - command line argument parsing *****");

        // test 1.1
        System.out.println("\n***** Begin Test 1.1 - too few arguments *****");
        totalTests++;
        String[] arguments = new String[]{"cookie_log.csv", "-d"};
        CookieParser cookieParser = new CookieParser(arguments);

        if (cookieParser.getFilename() == "" && cookieParser.getDate() == "") {
            testsPassed++;
            printMsg(true, "parseArgs");
        } else {
            printMsg(false, "parseArgs");
        }

        // test 1.2
        System.out.println("\n***** Begin Test 1.2 - too many arguments *****");
        totalTests++;
        arguments = new String[]{"cookie_log.csv", "-d", "2018-12-09", "extra arg"};
        cookieParser = new CookieParser(arguments);

        if (cookieParser.getFilename() == "" && cookieParser.getDate() == "") {
            testsPassed++;
            printMsg(true, "parseArgs");
        } else {
            printMsg(false, "parseArgs");
        }

        // test 1.3
        System.out.println("\n***** Begin Test 1.3 - wrong flag *****");
        totalTests++;
        arguments = new String[]{"cookie_log.csv", "-f", "2018-12-09"};
        cookieParser = new CookieParser(arguments);

        if (cookieParser.getFilename() == "" && cookieParser.getDate() == "") {
            testsPassed++;
            printMsg(true, "parseArgs");
        } else {
            printMsg(false, "parseArgs");
        }

        // test 1.4
        System.out.println("\n***** Begin Test 1.2 - correct arguments *****");
        totalTests++;
        arguments = new String[]{"cookie_log.csv", "-d", "2018-12-09"};
        cookieParser = new CookieParser(arguments);

        if (cookieParser.getFilename() == "cookie_log.csv" && cookieParser.getDate() == "2018-12-09") {
            testsPassed++;
            printMsg(true, "parseArgs");
        } else {
            printMsg(false, "parseArgs");
        }
    }

    // tests for cookie parsing
    private static void test_2() {
        System.out.println("\n***** Begin Test 2 - cookie parsing *****");

        // test 2.1
        System.out.println("\n***** Begin Test 2.1 - 2018-12-09 *****");
        totalTests++;
        String[] arguments = new String[]{"cookie_log.csv", "-d", "2018-12-09"};
        CookieParser cookieParser = new CookieParser(arguments);
        String output = cookieParser.getMostActiveCookies();

        if (output.equals("AtY0laUfhglK3lC7")) {
            testsPassed++;
            printMsg(true, "getMostActiveCookies");
        } else {
            printMsg(false, "getMostActiveCookies");
            printExpAct("AtY0laUfhglK3lC7", output);
        }

        // test 2.2
        System.out.println("\n***** Begin Test 2.2 - 2018-12-08 *****");
        totalTests++;
        arguments = new String[]{"cookie_log.csv", "-d", "2018-12-08"};
        cookieParser = new CookieParser(arguments);
        output = cookieParser.getMostActiveCookies();

        if (output.contains("SAZuXPGUrfbcn5UA") &&
        output.contains("4sMM2LxV07bPJzwf")
                && output.contains("fbcn5UAVanZf6UtG")) {
            testsPassed++;
            printMsg(true, "getMostActiveCookies");
        } else {
            printMsg(false, "getMostActiveCookies");
            printExpAct("SAZuXPGUrfbcn5UA\n" +
                    "4sMM2LxV07bPJzwf\n" +
                    "fbcn5UAVanZf6UtG", output);
        }

        // test 2.3
        System.out.println("\n***** Begin Test 2.3 - Date that doesn't exist *****");
        totalTests++;
        arguments = new String[]{"cookie_log.csv", "-d", "2018-01-05"};
        cookieParser = new CookieParser(arguments);
        output = cookieParser.getMostActiveCookies();

        if (output.equals("")) {
            testsPassed++;
            printMsg(true, "getMostActiveCookies");
        } else {
            printMsg(false, "getMostActiveCookies");
            printExpAct("", output);
        }
    }

    // prints whether the test case was failed or passed
    private static void printMsg(boolean passed, String method) {
        if (passed)
            System.out.println(method + " passed");
        else
            System.out.println(method + " failed");
    }

    // prints the actual vs expected output
    private static void printExpAct(String exp, String act) {
        System.out.println("Expected: " + exp);
        System.out.println("Actual: " + act);
    }
}
