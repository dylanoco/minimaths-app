package com.company;
//Dylan O'Connor, c0014965, MiniApps Assignment
public abstract class MiniApps {
    static java.util.Scanner in = new java.util.Scanner(System.in);
    public static void main(String[] args) {
        Menu();
    }
    //Made a function instead, so I can call it when an application is completed.
    public static void Menu(){
        // String Variables so that it's not clogging the Print Line command
        String strCounting = "1) Keep Counting Game";
        String strSquareRoot = "2) Square Root Calculator";
        String strCheckDigitGen = "3) Check-Digit Generator";
        String strCheckDigitCheck = "4) Check-Digit Checker";
        // Start of the program; displays the user the options
        System.out.println("P4CS Mini Applications");
        System.out.println("----------------------");
        int menuChoice;
        boolean menuBoolean = false;
        do {
            //New line to prevent using system.out.println over and over
            System.out.println("Please select an option by typing the number associated with the Application:");
            System.out.println(strCounting + "\n" + strSquareRoot + "\n" +
                    strCheckDigitGen + "\n" + strCheckDigitCheck + "\n" + "9) Quit");
            //Once the menuBoolean has been set to true, the while loop stops and proceeds with one of the apps.
            menuChoice = in.nextInt();
            if (menuChoice == 1) {
                CountingGame();
                menuBoolean = true;
            } else if (menuChoice == 2) {
                SquareRootCalc();
                menuBoolean = true;
            } else if (menuChoice == 3) {
                CheckDigitGen();
                menuBoolean = true;
            } else if (menuChoice == 4) {
                CheckDigitChecker();
                menuBoolean = true;
            } else if (menuChoice == 9) {
                break;
            }
        } while (!menuBoolean);

    }
    private static void CountingGame() {
        java.text.DecimalFormat sec = new java.text.DecimalFormat("00");
        //Variables
        int Counter = 0;
        int qAnswer = (int) (Math.random() * 11);
        int verifyAnswer = 0;
        //Introduction
        System.out.println("""
                Keep Counting
                -------------
                You will be presented with 10 addition questions.
                After the first question, the left-hand operand is the result of the previous addition.
               
                """);
        //To begin the timer
        long start = System.nanoTime();
        /*While loop which continues as long as the counter doesn't reach 10. The reason why I used a while loop instead
        of a for loop was because I was using Counter inside and outside the loop. So I figured it would make sense to only have
        one variable than to have two variables being used in similar fashion.
        */
        while (Counter != 10) {
            //Random integer variables within the while loop so that they are constantly random
            verifyAnswer = 0;
            int qNumber = (int) (Math.random() * 11);
            int randInt = (int) (Math.random() * 2);
            // Adds 1 onto the counter constantly until it reaches 10.
            Counter ++;
            //Randomly chooses between addition or subtraction, and creates the question.
            if (randInt == 1) {
                verifyAnswer = (qAnswer - qNumber);
                System.out.println("Question " + Counter + ": " + qAnswer + "-" + qNumber + " =");
            } else if (randInt == 0) {
                verifyAnswer = (qAnswer + qNumber);
                System.out.println("Question " + Counter + ": " + qAnswer + "+" + qNumber + " =");
            }
            //Allows for the input from the user, compares it with the answer and either continues with the loop or prints the actual answer if entered wrong.
            qAnswer = in.nextInt();
            if (qAnswer != verifyAnswer) {
                System.out.println("Answer: " + verifyAnswer);
                //Breaks the loop as the answer was wrong.
                break;

            }
        }
        //Concludes the game once the Counter has reached 10 and the last answer is correct!
        if (Counter == 10 && qAnswer == verifyAnswer) {
            //Stops the timer, and converts it into seconds by dividing it by a billion and formatting it into seconds
            long end = System.nanoTime();
            long totalSeconds = (end - start) / 1000000000;
            System.out.println("Questions complete in " + sec.format(totalSeconds) + " seconds");
        }
        Menu();
    }
    // ----------------------- ----------------------- ----------------------- ----------------------- -----------------------
    private static void SquareRootCalc () {
        //java.util.Scanner in=new java.util.Scanner(System.in);
        //This is used later on to get the decimal places; utilising arrays makes this easier.
        String[] dPlaces = {"0","0.0","0.00","0.000","0.0000","0.00000","0.000000"};
        System.out.println("""
                Square Root Calculator
                ----------------------
                Please enter a positive number:""");
        int dNum;
        int num = in.nextInt();
        //Double type has to be used, so it can find the exact number. Through trial and error I have noticed that Float
        do{
            System.out.println("Please enter the amount of Decimal Places between 0-7: ");
            dNum = in.nextInt();
            //Keeps check the range of numbers when doing the decimal place
        }while(dNum > 7 || dNum < 0);
        //I ask the user how many decimal places, and depending on their answer, the decimal format adjusts to the correct decimal place.
        java.text.DecimalFormat sqrtPlaces = new java.text.DecimalFormat(dPlaces[dNum]);
        //The next two variables create the range at which it will find the square root of the number entered
        //Upper is the highest range, and lower being the lowest range.

        /* The range between the upper and lower bound is very large making it
        inefficient, as its technically more taxing on the computer, and takes more iterations; longer to get the result
        The answer it retrieves at the end is very close but not the exact for some numbers (off by 0.000000###).
        If the condition in the while loop wasn't a float, it would be an infinite loop which I didn't know how to fix.
         I am aware of this, but couldn't figure out a way of fixing this without implementing
        numerous lines of code. I had the option to use Math.pow() but it would only take 1 iteration to get to the
        final answer. So I held off from using that method for the upper/lower bounds as it felt that the bounds wasn't necessarily working
        towards the answer, but more so that it would get it in one go.
        I know it's counter-intuitive to say that my code is wrong and inefficient, but I'd rather be honest and aware of my faults
        when doing this part of the assignment haha.
         */
        double upper = num;
        double lower = 1;
        double avg = 0;
        double avgSquared = 0;
        //A while loop is used here so that it goes through numerous amounts of iterations until it finds the right number
        while((float)avgSquared != num){
            avg = (upper + lower) / 2;
            avgSquared = avg * avg;
            //as displayed in the assignment, it checks if the squared number the for loop calculated is bigger than the number
            //entered, and if so, it assigns upper to the average, making it go through the loop again. Same vice versa if the squared number is lower
            //than the number entered
            if (avgSquared > num){
                upper = avg;
            }
            else{
                lower = avg;
            }
        }
        //Decided to do to print outputs, one displaying the answer with the specified decimal places, and the other
        //which displays the true answer.
        System.out.println("The square root of " + num + " to " + dNum + " decimal places is " + sqrtPlaces.format(avg));
        System.out.println("The answer without the decimal places is: " + avg);
        Menu();
    }
    // ----------------------- ----------------------- ----------------------- ----------------------- -----------------------
    /*inputs    expected outputs   actual outputs,
      12365          123653             123653     |
      (((1+3+5)*7) + ((2+6)*3))%10 = 7
      10 - 7 = 3
      12365 + 3 = 123653 ✔️

      56734          567341              567341
      (((5+7+4)*7) + ((6+3)*3))%10 = 9
      10 - 9=1
      56734 + 1 = 567341 ✔️

      99937           999379             999379
      (((9+9+7)*7) + ((9+3)*3))%10 = 1
      10-1=9
      99934 + 9 = 999349 ✔️
     */
    private static void CheckDigitGen() {
        //java.util.Scanner in=new java.util.Scanner(System.in);
        System.out.println("""
                Check-Digit Calculator
                ----------------------
                This calculator will take a 5-digit number and generate a trailing 6th check digit\s
                Please enter 5-digit number to generate final code:""");
        boolean lenCheck = false;
        int number;
        int n1=0,n2=0,n3=0,n4=0,n5 = 0;
        while(!lenCheck) {
            number = in.nextInt();
            /*
            This isn't converting the number to a string and reverting it back; this is only to confirm the amount of numbers.
            strNum is a variable that will be used only for this purpose and not for separating&combining the integers.
            */
            String strNum = Integer.toString(number);
            //First checks if it's the appropriate length;
            if (strNum.length() == 5){
                //Grabs each individual number
                n1 = number / 10000;
                n2 = (number - (n1 * 10000)) / 1000;
                n3 = (number - (n1 * 10000) - (n2 * 1000)) / 100;
                n4 = (number - (n1 * 10000) - (n2 * 1000) - (n3 * 100)) / 10;
                n5 = (number - (n1 * 10000) - (n2 * 1000) - (n3 * 100) - (n4 * 10));
                //Then confirms if whether any of the numbers are equal to 0;
                if(n1!=0 && n2!=0 && n3!=0 && n4!=0 && n5!=0){
                    lenCheck = true;
                }
                else{
                    System.out.println("Error! Digits are out of range.");
                    System.out.println("Please enter a 5 Digit Number, with each digit within the range 1-9:");
                }
            }
            else{
                System.out.println("Error! Amount of digits not equal to 5.");
                System.out.println("Please enter a 5 Digit Number, with each digit within the range 1-9:");
            }
        }

        int totalGroup = ((n1 + n3 + n5) * 7) + ((n2 + n4) * 3);
        int totalRemainder = totalGroup % 10;
        //remains 0 if its 0, if not then subtracts from 10.
        if (totalRemainder != 0) {
            totalRemainder = 10 - totalRemainder;
        }
        System.out.println("The 6-digit final number is: " + n1 + "" + n2 + "" + n3 + "" + n4 + "" + n5 + "" + totalRemainder);
        Menu();
    }
    // ----------------------- ----------------------- ----------------------- ----------------------- -----------------------
    private static void CheckDigitChecker () {
        System.out.println("""
                           Check-Digit Checker
                           -------------------
                           Please enter your 6 digit code
                            """);
        int checkDigit=0;
        int n1=0,n2=0,n3=0,n4=0,n5=0;
        boolean lenCheck = false;
        while(!lenCheck) {
            int number = in.nextInt();
            checkDigit = number - ((number / 10) * 10);
            /*
            This isn't converting the number to a string and reverting it back; this is only to confirm the amount of numbers.
            strNum is a variable that will be used only for this purpose and not for separating & combining the integers.
            */
            String strNum = Integer.toString(number);
            number/=10;
            if (strNum.length() == 6){
                n1 = number / 10000;
                n2 = (number - (n1 * 10000)) / 1000;
                n3 = (number - (n1 * 10000) - (n2 * 1000)) / 100;
                n4 = (number - (n1 * 10000) - (n2 * 1000) - (n3 * 100)) / 10;
                n5 = (number - (n1 * 10000) - (n2 * 1000) - (n3 * 100) - (n4 * 10));
                if(n1!=0 && n2!=0 && n3!=0 && n4!=0 && n5!=0){
                    lenCheck = true;
                }
                else{
                    System.out.println("Error! Digits are out of range.");
                    System.out.println("Please enter a 6 Digit Number, with each digit within the range 1-9:");
                }
            }
            else{
                System.out.println("Error! Amount of digits not equal to 6.");
                System.out.println("Please enter a 6 Digit Number, with each digit within the range 1-9:");
            }
        }
        int totalGroup = ((n1 + n3 + n5) * 7) + ((n2 + n4) * 3);
        int totalRemainder = totalGroup % 10;
        if (totalRemainder != 0) {
            totalRemainder = 10 - totalRemainder;
        }
        //Only difference between this and the previous app is this if statement.
        //I use the check digit that I found from the 6th digit and compared it with TotalRemainder; the check digit that was calculated.
        if (checkDigit == totalRemainder) {
            System.out.println("The number is valid");
        }
        else {
            System.out.println("The number is invalid");
        }


        Menu();
    }
}
