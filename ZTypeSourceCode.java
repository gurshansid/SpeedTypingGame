import javalib.worldimages.*;   // images, like RectangleImage or OverlayImages
import javalib.funworld.*;      // the abstract World class and the big-bang library
import java.awt.Color;          // general colors (as triples of red,green,blue values)
                                // and predefined colors (Red, Green, Yellow, Blue, Black, White)
import java.util.Random;

//represents a word in the ZType game
interface IWord {

  // constant for screen height 
  int SCREEN_HEIGHT = 0;

  // constant for font size of words 
  int FONT_SIZE = 13;

  // helper for filterOutEmpties
  boolean filterHelper();

  // helper for checkAndReduce
  IWord checkHelper(String str);

  // compares two words to see which one comes first alphabetically 
  int extractCompare(IWord w);

  // helper for extractCompare that returns an integer 
  int compare(String str);

  // helper for draw
  WorldScene draw(WorldScene w);

  // moves this word on the y-axis 
  IWord move();

  // checks if this words y coordinate if off of the screen
  boolean isOff();

  //checks if this word starts with a given key
  boolean keyMatchesWord(String key);

  // turns this word into an active word with the same fields
  IWord makeActive();

  //checks if a word is active or inactive
  boolean isActive();
}

//represents a list of words
interface ILoWord {

  // takes in a String of length 1 and produces 
  // an ILoWord where any active words in this ILoWord 
  // are reduced by removing the first letter only if 
  // the given string matches the first letter.
  ILoWord checkAndReduce(String s);

  // takes in an IWord and produces an ILoWord that is like this 
  // ILoWord but with the given IWord added at the end
  ILoWord addToEnd(IWord w);

  // produces an ILoWord with any IWords that have an empty string are filtered out.
  ILoWord filterOutEmpties();

  // takes in a WorldScene and draws all of the words in this ILoWord onto the given WorldScene.
  WorldScene draw(WorldScene w); 

  //moves this list of words
  ILoWord move();

  //checks if any word in this list has a y coordinate off the screen
  boolean anyOff();

  // checks if any of the words in this list start with a given letter
  boolean keyMatchesList(String key);

  //turns the first word in a list of words that starts with a given string to an active word
  ILoWord turnActive(String key);

  // checks if any words in this list are active
  boolean anyActive();
}

//represents an empty list of words
class MtLoWord implements ILoWord {

  // takes in a String of length 1 and produces 
  // an ILoWord where any active words in this ILoWord 
  // are reduced by removing the first letter only if 
  // the given string matches the first letter.
  public ILoWord checkAndReduce(String s) {
    return this;
  }

  // takes in an IWord and produces an ILoWord that is like this 
  // ILoWord but with the given IWord added at the end
  public ILoWord addToEnd(IWord w) {
    return new ConsLoWord(w, this);
  }

  // produces an ILoWord with any IWords that have an empty string are filtered out.
  public ILoWord filterOutEmpties() {
    return this;
  }

  // takes in a WorldScene and draws all of the words in this ILoWord onto the given WorldScene.
  public WorldScene draw(WorldScene w) {
    return w;
  }

  //moves the words in this empty list
  public ILoWord move() {
    return this;
  }

  //checks if any word in this empty list has a y coordinate off the screen
  public boolean anyOff() {
    return false; 
  }

  //checks if any of the words in this list start with a given letter
  public boolean keyMatchesList(String key) {
    return false;
  }

  //turns the first word in a list of words that starts with a given string to an active word
  public ILoWord turnActive(String key) {
    return this;
  }

  // checks if any word in this empty list is active 
  public boolean anyActive() {
    return false;
  }
}

class ConsLoWord implements ILoWord {
  IWord first;
  ILoWord rest;

  ConsLoWord(IWord first, ILoWord rest) {
    this.first = first;
    this.rest = rest;
  }

  // takes in a String of length 1 and produces 
  // an ILoWord where any active words in this ILoWord 
  // are reduced by removing the first letter only if 
  // the given string matches the first letter.
  public ILoWord checkAndReduce(String s) {
    return new ConsLoWord(this.first.checkHelper(s), this.rest.checkAndReduce(s));
  }

  // takes in an IWord and produces an ILoWord that is like this 
  // ILoWord but with the given IWord added at the end
  public ILoWord addToEnd(IWord w) {
    return new ConsLoWord(this.first, this.rest.addToEnd(w));
  } 

  // produces an ILoWord with any IWords that have an empty string are filtered out.
  public ILoWord filterOutEmpties() {
    if (this.first.filterHelper()) {
      return this.rest.filterOutEmpties();
    }
    return new ConsLoWord(this.first, this.rest.filterOutEmpties());
  }

  // takes in a WorldScene and draws all of the words in this 
  // ILoWord onto the given WorldScene.
  public WorldScene draw(WorldScene w) {
    return this.rest.draw(this.first.draw(w));
  }

  //moves the words in this non-empty list
  public ILoWord move() {
    return new ConsLoWord(this.first.move(), this.rest.move());
  }

  //checks if any word in this non-empty list has a y coordinate off the screen
  public boolean anyOff() {
    return this.first.isOff() || this.rest.anyOff(); 
  } 

  //checks if any of the words in this list start with a given letter
  public boolean keyMatchesList(String key) {
    return this.first.keyMatchesWord(key) || this.rest.keyMatchesList(key);
  }

  //turns the first word in a list of words that starts with a given string to an active word
  public ILoWord turnActive(String key) {
    if (this.first.keyMatchesWord(key) && !this.rest.anyActive()) {
      return new ConsLoWord(this.first.makeActive(), this.rest);
    }
    else if (!this.first.keyMatchesWord(key) && !this.anyActive()) {
      return new ConsLoWord(this.first, this.rest.turnActive(key));
    }
    else {
      return this;
    }
  }

  // checks if any word in this list is active
  public boolean anyActive() {
    return this.first.isActive() || this.rest.anyActive();
  }
}

// abstract class for words
abstract class AWord implements IWord {
  String word;
  int x;
  int y;

  // constructor for AWord
  AWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
  }

  //constructor for AWord that defaults y coordinate to height of screen
  AWord(String word, int x) {
    this.word = word;
    this.x = x;
    this.y = SCREEN_HEIGHT;
  }

  //helper for filterOutEmpties
  public boolean filterHelper() {
    return this.word.equals("");
  } 

  //helper for checkHelper
  public abstract IWord checkHelper(String str);

  //extracts the words of two IWords and calls compare on them
  public int extractCompare(IWord w) {
    return w.compare(this.word);
  } 

  // returns an integer when comparing two strings
  public int compare(String str) {
    return str.toLowerCase().compareTo(this.word.toLowerCase());
  }

  // draws a word on a string
  public abstract WorldScene draw(WorldScene w);

  //create a new word that is like this word but is shifted on the x-axis
  public abstract IWord move();

  //checks if this words y coordinate is 0
  public boolean isOff() {
    return this.y > 600;
  }
  
  //checks if this words y coordinate is 0
  public IWord makeActive() {
    return new ActiveWord(this.word, this.x, this.y);
  }

  //checks if this word starts with a given letter
  public boolean keyMatchesWord(String key) {
    return this.word.startsWith(key);
  }

  //checks if a word is active or inactive
  public abstract boolean isActive();
}

//represents an active word in the ZType game
class ActiveWord extends AWord {

  ActiveWord(String word, int x, int y) {
    super(word, x, y);
  }

  // defaults an active words y-coordinate to the height of the screen 
  ActiveWord(String word, int x) {
    super(word, x);
  }

  //helper for checkHelper
  public IWord checkHelper(String str) {
    if (this.word.startsWith(str)) {
      return new ActiveWord(this.word.substring(1), this.x, this.y);
    }
    return this;
  }

  // draws this word onto a given WorldScene
  public WorldScene draw(WorldScene w) { 
    return w.placeImageXY(new TextImage(this.word, FONT_SIZE, FontStyle.REGULAR, Color.RED),
        this.x, this.y);
  }

  //create a new active word that is like this word but is shifted on the x-axis
  public IWord move() {
    return new ActiveWord(this.word, this.x, this.y + 3);
  }

  //checks if a word is active or inactive
  public boolean isActive() {
    return true;
  }

}

//represents an inactive word in the ZType game
class InactiveWord extends AWord {


  InactiveWord(String word, int x, int y) {
    super(word, x, y);
  }

  // defaults an inactive words y-coordinate to the height of the screen 
  InactiveWord(String word, int x) {
    super(word, x);
  }

  

  // helper for checkAndReduce
  public IWord checkHelper(String str) {
    return this;
  }

  // draws this word onto a given WorldScene
  public WorldScene draw(WorldScene w) { 
    return w.placeImageXY(new TextImage(this.word, FONT_SIZE, FontStyle.REGULAR, Color.BLACK),
        this.x, this.y);
  }

  //create a new inactive word that is like this word but is shifted on the x-axis
  public IWord move() {
    return new InactiveWord(this.word, this.x, this.y + 3);
  }

  //checks if a word is active or inactive
  public boolean isActive() {
    return false;
  }
}

//represents a world class to animate a list of Words on a scene
class ZTypeWorld extends World {
  ILoWord words;
  Random rand;
  int ticks;

  ///The constructor for use in "real" games
  ZTypeWorld(ILoWord words, Random rand, int ticks) { 
    this.words = words;
    this.rand = new Random(); 
    this.ticks = ticks;
  }

  ZTypeWorld(ILoWord words, int ticks) {
    this(words, new Random(), ticks);
  }

  //draws the words onto the background
  public WorldScene makeScene() {
    return this.words.draw(new WorldScene(300, 600));
  }

  //move the words on the scene. Adds a new word at a random location at every tick of the clock
  public World onTick() {
    ILoWord add = this.words.addToEnd(new InactiveWord(new Utils().produceString(), 
        (this.rand.nextInt(200)) + 50));
    if (this.words.anyOff()) {
      return endOfWorld("YOU LOST!");
    }
    else {
      if (this.ticks % 30 == 0) {
        return new ZTypeWorld(add.move(), ticks + 1);
      }
      else {
        return new ZTypeWorld(this.words.move(), ticks + 1);
      }
    }
  }

  // displays a given message when the world ends 
  public WorldScene lastScene(String msg) {
    return new WorldScene(300, 600).placeImageXY(new TextImage(msg, 18, Color.BLACK), 150, 300);
  }

  // add a key event to remove a letter on an existing word in the world
  public ZTypeWorld onKeyEvent(String key) {
    if (this.words.keyMatchesList(key)) {
      return new ZTypeWorld(this.words.turnActive(key).checkAndReduce(key).filterOutEmpties(), 
          this.ticks);
    }
    else {
      return this;
    }
  }
}

// Utils class to hold methods
class Utils {
  
  Random random; // random object

  //The constructor for use in "real" games
  Utils() { 
    this(new Random()); 
  }

  //The constructor for use in testing, with a specified Random object
  Utils(Random random) {
    this.random = random;
  }

  // takes in an integer and returns the the letter of the alphabet that
  // corresponds to that integer 
  String letterNumber(int i) {
    return "abcdefghijklmnopqrstuvwxyz".substring(i, i + 1);
  }

  // creates a string of 6 random letters and keeps track of the length of string
  String randomString(String str, int stringLengthSoFar) {
    if (stringLengthSoFar >= 0 &&  stringLengthSoFar < 6) {
      return str + randomString(letterNumber(
          this.random.nextInt(26)), stringLengthSoFar + 1);
    }
    else {
      return str; 
    }
  }

  // creates a string of 6 random letters starting with an empty string 
  String produceString() {
    return randomString("", 0);
  }
}
