import java.util.*;

class Main {
  public static void main(String[] args) {
    ArrayList<String> animals = new ArrayList<String>();
    animals.add("Animal");
    animals.add("Whales");
    animals.add("Monkey");
    animals.add("Tigers");
    animals.add("Rooster");
    animals.add("Turkey");
    ArrayList<String> school = new ArrayList<String>();
    school.add("Pencil");
    school.add("Eraser");
    school.add("Health");
    school.add("Recess");
    school.add("Lesson");
    school.add("Crayon")
    ArrayList<String> sports = new ArrayList<String>();
    sports.add("Tennis");
    sports.add("Sports");
    sports.add("Karate");
    sports.add("Soccer");
    sports.add("Runner");
    sports.add("Boxing");
    ArrayList<String> geography = new ArrayList<String>();
    geography.add("Canada");
    geography.add("Norway");
    geography.add("Amazon");
    geography.add("Sweden");
    geography.add("Ganges");
    geography.add("Turkey")
    ArrayList<String> fruits = new ArrayList<String>();
    fruits.add("Banana");
    fruits.add("Apples");
    fruits.add("Orange");
    fruits.add("Grapes");
    fruits.add("Papaya");
    fruits.add("Cherry");
    ArrayList<ArrayList> words = new ArrayList<ArrayList>();
    words.add(sports);
    words.add(animals);
    words.add(school);
    words.add(geography);
    words.add(fruits);
    Scanner input = new Scanner(System.in);
    ArrayList<Integer> guesses = new ArrayList<Integer>();
    do { 
      guesses.add(oneGame(words));
      System.out.print("\nDo you want to play again? ");
    }
    while(input.next().toLowerCase().charAt(0) == 'y');
    int games = guesses.size();
    double average = 0;
    int min = guesses.get(0);
    int wins;
    int losses = 0;
    for(int val: guesses) {
      average += val;
    }
    average /= (double)(games);
    for(int i = 0; i < guesses.size(); i++) {
      min = Math.min(min, guesses.get(i));
      if(guesses.get(i) == 5)
        losses++;
    }
    wins = games - losses;
    System.out.println("You played " + games + " games");
    System.out.println("You won " + wins + " of them and lost " + losses + " of them");
    System.out.println("You had an average of " + (average - average % 0.01) + " guesses with a minimum of " + min + " guesses");
  }

  public static int oneGame(ArrayList<ArrayList> words) {
    System.out.print("\nWhat category do you want? (0 = sports, 1 = animals, 2 = school, 3 = geography, 4 = fruits): ");
    Scanner input = new Scanner(System.in);
    int row = input.nextInt();
    int randomWord = (int)(Math.random() * 6);
    ArrayList<String> category = words.get(row);
    String selected = category.get(randomWord);
    int numGuesses = 0;
    boolean correct = false;
    String guess;
    while(numGuesses < 4 && !correct) {
      numGuesses++;
      System.out.print("\nGuess a sequence of 6 letters: ");
      guess = input.next();
      if(guess.length() != 6) {
        while(guess.length() != 6) {
          
          System.out.print("\nTry again. Guess a sequence of 6 letters: ");
          guess = input.next();
        }
      }
      correct = hints(guess, selected);
      if(correct==true) {
        System.out.print("\nCongratulations, you guessed the word!");
        return numGuesses;
      }
      else if(numGuesses >= 4) {
        System.out.println("\nSorry, you are out of guesses. The correct word was "+selected);
          return 5;
      }
    }
    return numGuesses;
  }
  
  public static boolean hints(String guess, String selected) {
    int k = 0;
    guess = guess.toUpperCase();
    selected = selected.toUpperCase();
    if(guess.equals(selected))
      return true;
    ArrayList<String> arrGuess = stringToArr(guess);
    ArrayList<String> arrSelected = stringToArr(selected);
    String[] output = new String[selected.length()];
    for(int i = 0; i < arrSelected.size(); i+=0) {
      if(arrGuess.get(i).equals(arrSelected.get(i))) {        
        output[k] = "G";
        arrGuess.remove(i);
        arrSelected.remove(i);
      }
      else
        i++;
      k++;
    }
    k = 0; 
    for(int i = 0; i < arrSelected.size(); i++) {
      while(output[k] == "G")
        k++;
      if(arrSelected.contains(arrGuess.get(i))){
        output[k]="Y";
        k++;
      }
      else {
        output[k]="B";
        k++;
      } 
    }
    
    
  
    ArrayList<String> arrGuess1 = stringToArr(guess.toUpperCase());
    for(String val: arrGuess1)
      System.out.print(val + " ");
    System.out.println();
    for(String val: output)
      System.out.print(val + " ");
      
    return false;
  }

  public static ArrayList<String> stringToArr(String word) {
    ArrayList<String> arr = new ArrayList<String>();
    for(int i = 0; i < word.length(); i++) {
      arr.add(Character.toString(word.charAt(i)));
    }
    return arr;
  }
  
}
