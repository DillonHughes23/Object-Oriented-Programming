import java.util.ArrayList;
import java.util.HashSet;

/**
 Review class 
  compares each output from the app and chooses if it is a positive review or negative review
  
 */
public class review implements Comparable <review> {
    String name;
    ArrayList <String> words = new ArrayList <String>();
    HashSet <String> negWords = new HashSet <String>();
    HashSet <String> posWords = new HashSet <String>();

    review(String name){
        this.name = name;
    }
    int posWord = 0;
    int negWord = 0;
    public String toString(){
        return "Review: " + this.name + " posWord: " + this.posWord + " negWord " + this.negWord + "\n" + "number of poswords" + posWords.size() + " number of negwords: " + negWords.size() + "\n";
    }
    double negRatio;
    public void calcRatio(){
        negRatio = negWord/posWord;
    }
    @Override
    public int compareTo(review o) {
        if (negRatio > o.negRatio){
            return 1;
        }
        else if (negRatio < o.negRatio){
            return -1;
        }
        else return 0;
        
        
    }
    
}
