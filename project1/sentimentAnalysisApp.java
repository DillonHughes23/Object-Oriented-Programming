import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
/**
 * Program parses a text file with positive words, a text file with negative words
 * then parses through and analyzes a directory of movie reviews in simple text files.
 *  
 * @author Dustin Bruce
 * @author Dillon Hughes
 * @
 * 
 * 
 * 
 * 
 * 
*/



public class sentimentApp{


    /**
     * uses regex expression to remove punction
     * Also manually replaces parenthesis with spaces 
     * to avoid losing words in parsing of review text file 
     * @param takes a string
     * @return returns string without punctuation
     * @
     * 
     * 
     * */
    public static String removePunctuations (String source){
        source = source.replaceAll("[(]", " ");
        source = source.replaceAll("[)]", " ");
        return source.replaceAll("\\p{Punct}", "");
    }
    /**
     *Takes a string and replaces all uppercase letters ("E")
     *with lowercase letter ("e")
     * 
     * @param source, string
     * @return returns the given string with all chars
     * lowercase
     */
    public static String lowerCase (String source){
        return source.toLowerCase();
    }


    //ERROR OCCURS IN VOID MAIN BECAUSE OF INCORRECT FILE PATHS. TO GET WORKING YOU HAVE TO CHANGE THE FILE PATHS.

    public static void main(String[] args) throws Exception {
        String posWordPath = null;
        String negWordPath = null;
        String posReviewPath = null;
        String negReviewPath = null;


        //hard coded relative data path, if there are no command line arguments
        if ( args.length < 4){
            posWordPath = "/Users/dillonhughes/Documents/School/OOP/Notes/test/dah249/project1/Data/positive-words.txt";
            negWordPath = "/Users/dillonhughes/Documents/School/OOP/Notes/test/dah249/project1/Data/negative-words.txt";
            posReviewPath = "/Users/dillonhughes/Documents/School/OOP/Notes/test/dah249/project1/Data/Movie-reviews/pos";
            negReviewPath = "/Users/dillonhughes/Documents/School/OOP/Notes/test/dah249/project1/Data/Movie-reviews/neg";
        }
 
        //if command line arguments are provided, updates the paths
        //to the relevant files
        if ( args.length > 0){

            posWordPath = args[0];
            negWordPath = args[1];
            posReviewPath = args[2];
            negReviewPath = args[3];
        }
        // hash sets to easily search neg and pos words
        HashSet <String> neg = new HashSet <String>();
        HashSet <String> pos = new HashSet <String>();

        //arraylists to store object: review, one for pos one for neg
        ArrayList <review> reviewsPos = new ArrayList<review> ();
        ArrayList <review> reviewsNeg = new ArrayList<review> ();
        

        //convert string to file object to get access to .isFile()
        File fileNeg = new File (negWordPath);
        File filePos = new File (posWordPath);
        Scanner sc = new Scanner(fileNeg);
        
        try{
            sc = new Scanner (fileNeg);
        }
        
        catch (Exception e){
            System.out.println(e);
        }
        String temp = "";
        


        //generate negative word hashset, toLower all chars
        //removes punctuation, removes header lines from word files
        //that contain ";"
        while (sc.hasNextLine()){
            temp = sc.nextLine();
            
            if (temp.contains(";")){
                temp = "";
            }
            temp = lowerCase(temp);
            temp = removePunctuations(temp);
            
            if (!temp.isEmpty()){
                neg.add(temp);
            }
               
        }
            sc.close();


        try{
            sc = new Scanner (filePos);
        }
        


        catch (Exception e){
            System.out.println(e);
        }


        //generate negative word hashset, toLower all chars
        //removes punctuation, removes header lines from word files
        //that contain ";"
        while (sc.hasNextLine()){
            temp = sc.nextLine();
            
            if (temp.contains(";")){
                temp = "";
            }
            temp = lowerCase(temp);
            temp = removePunctuations(temp);
            
            if (!temp.isEmpty()){
                pos.add(temp);
            }
               
        }
        sc.close();

        //removing punction from all words creates entry "a" that throws off accuracy
        //manually removes "a" from positive hashset, adds "a+ back in
        pos.remove("a");
        pos.add("a+");

        //iterates through all files in the negative review directory
        // creates an empty review container, pulls entire line, removes punctuation, lowercases
        // then splits with " " delimiter, stores all words in a temp array,
        //then checks to see if any words in the array are contained in either neg hash or pos hash
        // if words are found, updates counter found inside review class for each relevant review object
        //then stores the the temp review container in the designated review array;
        File negReviewsDir = new File (negReviewPath);
        File[] files = negReviewsDir.listFiles();
        BufferedReader fileReader = null;
        for (File file : files){
            
            
            if (file.isFile()){
                
                String line = null;
                try{
                    fileReader = new BufferedReader(new FileReader(file));
                }
                catch (IOException e){
                    System.out.println(e);
                }
                review tempR = new review(file.getName());
                tempR.name = file.getName();
                while((line = fileReader.readLine()) != null){
                    //removes HTML linebreak elements
                    line = line.replaceAll("<br />", " ");
                    line = lowerCase(line);
                    line = removePunctuations(line);
                    String[] tempA = line.split(" ");
                    for (String s: tempA){
                        if (neg.contains(s)){
                            tempR.negWord++;
                        }
                        if (pos.contains(s)){
                            tempR.posWord++;
                        }
                        tempR.words.add(s);
                        
                        
                    }
                    reviewsNeg.add(tempR);
                    
                    
                    
                }
                
            }
        }
       //iterates through all files in the positive review directory
        // creates an empty review container, pulls entire line, removes punctuation, lowercases
        // then splits with " " delimiter, stores all words in a temp array,
        //then checks to see if any words in the array are contained in either neg hash or pos hash
        // if words are found, updates counter found inside review class for each relevant review object
        //then stores the the temp review container in the designated review array;
        File posReviewsDir = new File (posReviewPath);
        File[] filesPos = posReviewsDir.listFiles();

        for (File file : filesPos){
            
            if (file.isFile()){
                
                String line = "";
                try{
                    fileReader = new BufferedReader(new FileReader(file));
                }
                catch (IOException e){
                    System.out.println(e);
                }
                review tempR = new review(file.getName());
                tempR.name = file.getName();
                while((line = fileReader.readLine()) != null){
                    line = line.replaceAll("<br />", " ");
                    line = lowerCase(line);
                    line = removePunctuations(line);
                    
                    String[] tempA = line.split(" ");
                    for (String s: tempA){
                        if(pos.contains(s)){
                            tempR.posWord++;
                        }
                        if (neg.contains(s)){
                            tempR.negWord++;
                        }
                        tempR.words.add(s);
                        
                        
                    }
                    reviewsPos.add(tempR);
                    
                    
                    
                }
                
            }
        }
        
        sc.close();


        ////counters
        //positive reviews found in negative array
        int pNeg = 0;
        //negative reviews found in negative array
        int nNeg = 0;
        //positive reviews found in positive array
        int pPos = 0; 
        //negative reviews found in positive array
        int nPos = 0;
      
        
        //for every review in neg review array, if internal counters
        //of review have counted more neg words than pos words, counts that review
        //as pos/neg
        for (review r : reviewsNeg){
            if (r.negWord >= r.posWord){
                nNeg++;
            }
            if (r.posWord > r.negWord){
                nPos++;
            }
        }
        
        //for every review in pos review array, if internal counters
        //of review have counted more neg words than pos words, counts that review
        //as pos/neg
        for (review r : reviewsPos){
            if (r.negWord >= r.posWord){
                pNeg++;
                
            }
            if (r.posWord > r.negWord){
                pPos++;
            }
        }

        

        //stat printing
        System.out.println("Number of positive reviews: " + reviewsPos.size());
        System.out.println("Correct classified: " + pPos);
        System.out.println("Misclassified: " + pNeg);
        double posReviewCorrect = ((double)pPos/(double)reviewsPos.size());
        System.out.println("Correct classification rate: " + posReviewCorrect*100 + "%\n");


        System.out.println("Number of negative reviews: " + reviewsNeg.size());
        System.out.println("Correct classified: " + nNeg);
        System.out.println("Misclassified: " + nPos);
        double negReviewCorrect = (double)nNeg/(double)reviewsNeg.size();

        System.out.printf("Correct classification rate: %.1f%%", negReviewCorrect*100);
        System.out.println("\n");
        int total = reviewsNeg.size() + reviewsPos.size();
        System.out.println("Number of all reviews: " + total);
        System.out.println("Correct classified: " + (pPos + nNeg));
        int totalIncorrect = nPos + pNeg;
        System.out.println("Misclassified: " + totalIncorrect);
        int correctTotal = (nNeg + pPos);
        double correctPercent = (double)correctTotal/(double)(reviewsNeg.size() + reviewsPos.size()); 
        System.out.printf ("Overall correct classification: %.1f%%", correctPercent*100 );

       
        
        
        
        









    }

}