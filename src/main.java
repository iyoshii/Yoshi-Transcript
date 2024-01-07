import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "invalidInput.txt";
        String username = welcomeUsername(fileName);
        loop(username);

    }

    public static String welcomeUsername(String fileName) {
        while(true){
            //welcome banner
            System.out.println(".--------------------------------.");
            System.out.println("| Welcome to Yoshi's Transcript! |");
            System.out.println("'--------------------------------'\n");

            //username
            System.out.print("Please enter your username to proceed: ");
            String username = scanner.nextLine();

            //validate username
            if (validateInput(fileName, username)) {
                errorMessage();
            } else {
                clearScreen();
                return username;
            }
        }

    }

    public static void mainMenu(String username) throws FileNotFoundException {
        boolean validMainMenuInput = false;

        while(!validMainMenuInput){
        //main menu
            System.out.println(".-------------------------.");
            System.out.println("|      1: Encryption      |");
            System.out.println("|      2: Decryption      | ");
            System.out.println("'-------------------------'\n");

        //selection
            System.out.println(username + ", you may select [1] for Encryption or [2] for Decryption.");
            System.out.print("Please enter your choice: ");
            String mainMenuChoice = scanner.nextLine();

            if(mainMenuChoice.equals("1")){
                validMainMenuInput = true;
                clearScreen();
                encryptionMenu(username);
           }
           else if(mainMenuChoice.equals("2")){
                validMainMenuInput = true;
                clearScreen();
                decryptionMenu(username);
            }
           else{
               errorMessage();
          }
        }
    }

    public static void endOfProgram(String username) throws FileNotFoundException {
        boolean validEndOfProgramInput = false;
        clearScreen();

        while(!validEndOfProgramInput){
            System.out.println(username + ", do you want to quit the program?");
            System.out.print("Please enter [Y] for yes or [N] for no: ");
            String endOfProgramChoice = scanner.nextLine();

            if(endOfProgramChoice.equals("y")||endOfProgramChoice.equals("Y")){
                validEndOfProgramInput = true;
                clearScreen();
                System.out.println("End of program. Thanks for using Yoshi's Transcript!");
            }
            else if(endOfProgramChoice.equals("n")||endOfProgramChoice.equals("N")){
                validEndOfProgramInput = true;
                clearScreen();
                loop(username);
            }
            else{
                errorMessage();
            }
        }
    }

    public static void encryptionMenu(String username) throws FileNotFoundException {
        boolean validEncryptionMenuInput = false;

        while(!validEncryptionMenuInput){
           //main menu
           System.out.println(".----------------------------------.");
           System.out.println("|        1: File Encryption        |");
           System.out.println("|      2: Keyboard Encryption      | ");
           System.out.println("'----------------------------------'\n");

          //selection
          System.out.println(username + ", you may select [1] for File Encryption or [2] for Keyboard Encryption.");
          System.out.print("Please enter your choice: ");
          String encryptionMenuChoice = scanner.nextLine();

            if(encryptionMenuChoice.equals("1")){
                validEncryptionMenuInput = true;
                clearScreen();
                fileEncryption(username);
            }
            else if(encryptionMenuChoice.equals("2")){
                validEncryptionMenuInput = true;
                clearScreen();
                keyboardEncryption(username);
            }
            else{
               errorMessage();
            }
        }
    }

    public static void decryptionMenu(String username) throws FileNotFoundException {
        boolean validDecryptionMenuInput = false;

        while(!validDecryptionMenuInput){
            //main menu
            System.out.println(".----------------------------------.");
            System.out.println("|        1: File Decryption        |");
            System.out.println("|      2: Keyboard Decryption      | ");
            System.out.println("'----------------------------------'\n");

            //selection
            System.out.println(username + ", you may select [1] for File Decryption or [2] for Keyboard Decryption.");
            System.out.print("Please enter your choice: ");
            String encryptionMenuChoice = scanner.nextLine();

            if(encryptionMenuChoice.equals("1")){
                validDecryptionMenuInput = true;
                clearScreen();
                fileDecryption(username);
            }
            else if(encryptionMenuChoice.equals("2")){
                validDecryptionMenuInput = true;
                clearScreen();
                keyboardDecryption(username);
            }
            else{
                errorMessage();
            }
        }
    }

    public static void fileEncryption(String username) throws FileNotFoundException {
        boolean validFileEncryptionName = false;

        while(!validFileEncryptionName){
            System.out.println(username + ", please enter a file name below for encryption:");
            String inputFileName = scanner.nextLine();

            File file = new File(inputFileName);
            if (file.exists()) {
                validFileEncryptionName = true;

                String[] encryptFileMessage = readFile(inputFileName);
                String[] encryptFileMessageKeywords = separateIntoKeywords(encryptFileMessage);

                String output = "";
                for (String keyword : encryptFileMessageKeywords) {
                    output += "yoshi" + keyword + " ";
                }

                clearScreen();
                System.out.println("Here is the encrypted message in Yoshi's Transcript:\n" + output.trim());
                scanner.nextLine();

            } else {
                errorMessage();
            }
        }
    }

    public static void keyboardEncryption(String username){
        System.out.println(username + ", please enter a source message below for encryption:");
        String keyboardEncryptionMessage = scanner.nextLine();

        String[] sourceMessageLines = keyboardEncryptionMessage.split("\n");
        String[] encryptFileMessageKeywords = separateIntoKeywords(sourceMessageLines);

        String output = "";
        for (String keyword : encryptFileMessageKeywords) {
            output += "yoshi" + keyword + " ";
        }

        clearScreen();
        System.out.println("Here is the encrypted message in Yoshi's Transcript:\n" + output.trim());
        scanner.nextLine();
    }

    public static void fileDecryption(String username) throws FileNotFoundException {
        boolean validFileDecryptionName = false;

        while(!validFileDecryptionName){
            System.out.println(username + ", please enter a file name below for decryption:");
            String inputFileName = scanner.nextLine();

            File file = new File(inputFileName);
            if (file.exists()) {
                validFileDecryptionName = true;

                String[] decryptFileMessage = readFile(inputFileName);
                String[] decryptFileMessageKeywords = separateIntoKeywords2(decryptFileMessage);

                String output = "";
                for (String keyword : decryptFileMessageKeywords) {
                    output += keyword + " ";
                }

                clearScreen();
                System.out.println("Here is the decrypted message from Yoshi's Transcript:\n" + output.trim());
                scanner.nextLine();

            } else {
                errorMessage();
            }
        }
    }

    public static void keyboardDecryption(String username){
        System.out.println(username + ", please enter a source message below for decryption:");
        String keyboardDecryptionMessage = scanner.nextLine();

        String[] decryptSourceMessage = keyboardDecryptionMessage.split("\n");
        String[] decryptFileMessageKeywords = separateIntoKeywords2(decryptSourceMessage);

        String output = "";
        for (String keyword : decryptFileMessageKeywords) {
            output += keyword + " ";
        }

        clearScreen();
        System.out.println("Here is the decrypted message from Yoshi's Transcript:\n" + output.trim());
        scanner.nextLine();
    }

    private static String[] readFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);

        int numberOfLinesInFile = countLinesInFile(inputFilename);
        String[] data = new String[numberOfLinesInFile];
        int index = 0;
        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextLine();
        }
        scanner.close();
        return data;
    }

    private static int countLinesInFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        return lineCount;
    }

    private static String[] separateIntoKeywords(String[] encryptFileMessage) {
        List<String> keywords = new ArrayList<>();
        for (String line : encryptFileMessage) {
            String[] words = line.split(" ");
            for (String word : words) {
                    String encryptedWord = getEncryptedWord(word);
                    keywords.add(encryptedWord);
            }
        }
        return keywords.toArray(new String[0]);
    }

    private static String getEncryptedWord(String word) {
        if (word.length() <= 3) {
            return word;
        }

        String shiftedWord = word.charAt(word.length() - 1) + word.substring(0, word.length() - 1);
        char firstChar = shiftedWord.charAt(0);
        char lastChar = shiftedWord.charAt(shiftedWord.length() - 1);
        return ((int) firstChar) + shiftedWord.substring(1, shiftedWord.length() - 1) + ((int) lastChar);
    }

    private static String[] separateIntoKeywords2(String[] encryptedFileMessage) {
        List<String> decryptedKeywords = new ArrayList<>();
        for (String keyword : encryptedFileMessage) {
            if (keyword.startsWith("yoshi")) {
                String encryptedWord = keyword.substring(5);
                String[] words = encryptedWord.split("yoshi");

                for (String word : words) {
                    String decryptedWord = getDecryptedWord(word);
                    decryptedKeywords.add(decryptedWord);
                }
            }
            else {
                decryptedKeywords.add(keyword); // Keyword is not encrypted, add as is
            }
        }
        return decryptedKeywords.toArray(new String[0]);
    }

    private static String getDecryptedWord(String encryptedWord) {
        encryptedWord = encryptedWord.trim();

        if (encryptedWord.length() <= 3) {
            return encryptedWord.trim();
        }

        int wordLength = encryptedWord.length();
        int firstCharValue;
        int lastCharValue;
        String shiftedWord;

        try {
            String firstCharString = encryptedWord.substring(0, 3);
            String lastCharString = encryptedWord.substring(wordLength - 3);
            shiftedWord = encryptedWord.substring(3, wordLength - 3);

            if(!firstCharString.matches("\\d+")){
                firstCharString = encryptedWord.substring(0, 2);
            }
            if(!lastCharString.matches("\\d+")){
                lastCharString = encryptedWord.substring(wordLength - 2);
            }

            if (firstCharString.length() == 2) {
                firstCharString = "0" + firstCharString;
                shiftedWord = encryptedWord.substring(2, wordLength - 3);
            }
            if (lastCharString.length() == 2) {
                lastCharString = "0" + lastCharString;
                shiftedWord = encryptedWord.substring(3, wordLength - 2);
            }

            firstCharValue = Integer.parseInt(firstCharString);
            lastCharValue = Integer.parseInt(lastCharString);

        }
        catch (NumberFormatException e) {
            return encryptedWord;
        }

        char firstChar = (char) firstCharValue;
        char lastChar = (char) lastCharValue;

        return shiftedWord.replaceAll("\\s+", "") + lastChar + firstChar;
    }

    public static boolean validateInput(String fileName, String username) {
        File file = new File(fileName);
        Scanner fileScanner;

        try {
            fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if (line.equalsIgnoreCase(username)) {
                    fileScanner.close();
                    return true;
                }
            }

            fileScanner.close();
            return false;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void errorMessage(){
        clearScreen();

        System.out.println("Invalid input. Please try again.");
        System.out.println("Press [Enter] to continue.");
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }

        scanner.nextLine();
    }

    public static void clearScreen(){
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static void loop(String username) throws FileNotFoundException {
        mainMenu(username);
        endOfProgram(username);
    }
}