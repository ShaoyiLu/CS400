// --== CS400 Project One File Header ==--
// Name: Yingjie Dai
// CSL Username: ydai
// Email: ydai69@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import java.util.Scanner;

/**
 * The class test for all the method in Book map frontend
 */
public class FrontendTester {

    /**
     * The method check if method exit properly
     *
     * @return true if test passed, false otherwise
     */
    public static boolean test1(){
        //simulate input
        TextUITester tester = new TextUITester("4\n");

        //generate backend placeholder
        BookMapperFrontend frontend =
                new BookMapperFrontend(new Scanner(System.in),null,null);
        //run the program
        frontend.runCommandLoop();

        //get the output
        String output = tester.checkOutput();

        //get the exit output check if it is correct
        String exit = output.split("\n")[9];

        //check if it is equal
        if (exit.equals("Goodbye!")){
            return true;
        }else {
            return false;
        }

    }

    /**
     * The method check if method will search by ISBN properly
     *
     * @return true if test passed, false otherwise
     */
    public static boolean test2(){
        //simulate input
        TextUITester tester = new TextUITester("1\nvalidISBN\n4\n");

        //generate backend placeholder
        BookMapperBackend backend = new BookMapperBackend();
        ISBNValidator validator = new ISBNValidator();
        BookMapperFrontend frontend =
                new BookMapperFrontend(new Scanner(System.in),backend,validator);
        //run the program
        frontend.runCommandLoop();

        //get the output
        String output = tester.checkOutput();

        //print output check if it is correct
        String resultBook = output.split("\n")[10];

        //check books correctness
        if (resultBook.equals("1. \"The Hitchhiker's Guide to the Galaxy " +
                "(Hitchhiker's Guide to the Galaxy  #1)\" " +
                "by Douglas Adams, ISBN: 9780330491198")){
            return true;
        }
        else {
            return false;//test failed
        }

    }

    /**
     * The method check if set title name properly
     *
     * @return true if test passed, false otherwise
     */
    public static boolean test3(){
        //simulate input
        TextUITester tester = new TextUITester("2\ncorrectTitle\n4\n");

        //generate backend placeholder
        BookMapperBackend backend = new BookMapperBackend();
        ISBNValidator validator = new ISBNValidator();
        BookMapperFrontend frontend =
                new BookMapperFrontend(new Scanner(System.in),backend,validator);
        //run the program

        frontend.runCommandLoop();

        //get the output
        String output = tester.checkOutput();

        //System.out.println(output);

        //get output book to check if it is correct
        String resultBook = output.split("\n")[11];

        //check books correctness
        if (resultBook.equals("1. \"The Ultimate Hitchhiker's Guide " +
                "to the Galaxy (Hitchhiker's Guide to the Galaxy  #1-5)\"" +
                " by Douglas Adams, ISBN: 9780345453747")){
            return true;//test pass
        }
        else {
            return false;//test failed
        }
    }


    /**
     * The method check if method will search by title word properly
     *
     * @return true if test passed, false otherwise
     */
    public static boolean test4() {
        //simulate input
        TextUITester tester = new TextUITester("3\nsomeName\n4\n");

        //generate backend placeholder
        BookMapperBackend backend = new BookMapperBackend();
        ISBNValidator validator = new ISBNValidator();
        BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), backend, validator);
        //run the program
        frontend.runCommandLoop();

        //get the output
        String output = tester.checkOutput();

        //print output check if it is correct
        //System.out.println(output);

        //check if filter name is correct
        if (backend.getAuthorFilter().equals("someName")){
            return true;
        }
        else {
            return false;//test failed
        }
    }

    /**
     * The method check if frontend could display window
     *
     * @return true if test passed, false otherwise
     */
    public static boolean test5(){

        //setup tester
        TextUITester tester = new TextUITester("1\n\n");

        //generate placeholder
        BookMapperBackend backend = new BookMapperBackend();
        ISBNValidator validator = new ISBNValidator();
        BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),
                backend, validator);
        //run the program
        frontend.displayMainMenu();

        //get the output
        String output = tester.checkOutput();

        //check if filter name is correct
        if(output.equals("You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n"))
            return true;//test passed

        return false;//failed test

    }

    /**
     * The main method to check if all the test has been passed
     */
    public static void main(String[] args) {
        //run all test
        System.out.println("FrontendEngineering Individual Test1: " +test1());
        System.out.println("FrontendEngineering Individual Test2: " +test2());
        System.out.println("FrontendEngineering Individual Test3: " +test3());
        System.out.println("FrontendEngineering Individual Test4: " +test4());
        System.out.println("FrontendEngineering Individual Test5: " +test5());
    }
}