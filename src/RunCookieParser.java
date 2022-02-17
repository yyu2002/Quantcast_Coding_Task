public class RunCookieParser {
    public static void main(String[] args) {
        // run the cookie parser on the command line argument
        CookieParser cookieParser = new CookieParser(args);
        System.out.println(cookieParser.getMostActiveCookies());
    }
}
