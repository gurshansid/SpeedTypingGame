import tester.*;                // The tester library

//all examples and tests for ILoWord
class ExamplesWordLists {
  ExamplesWordLists() {}

  // examples of active words
  IWord apple = new ActiveWord("apple", 4, 4);
  IWord apple2 = new ActiveWord("Apple", 4, 4);
  IWord orange = new ActiveWord("orange", 5, 5);
  IWord banana = new ActiveWord("banana", 3, 3);
  IWord pear = new ActiveWord("pear", 2, 1);
  IWord tangerine = new ActiveWord("tangerine", 1, 2);
  IWord letterA = new ActiveWord("a", 1, 2);
  IWord mtWordA = new ActiveWord("", 2 ,5);
  IWord walk = new ActiveWord("walk", 2, 601);


  // examples of inactive words
  IWord avocado = new InactiveWord("avocado", 1, 4);
  IWord inactiveApple = new InactiveWord("apple", 4, 4);
  IWord grape = new InactiveWord("grape", 1, 5);
  IWord strawberry = new InactiveWord("strawberry", 5, 1);
  IWord apricot = new InactiveWord("apricot", 6, 1);
  IWord blueberry = new InactiveWord("blueberry", 1, 6);
  IWord mtWordI = new InactiveWord("", 2, 6);

  // ACTIVE LISTS
  // example of an empty list
  ILoWord mt = new MtLoWord();

  // a list with one active word
  ILoWord list1 = new ConsLoWord(this.apple, this.mt);

  // a list with 2 active words
  ILoWord list2 = new ConsLoWord(this.banana, this.list1);

  // a list with 3 active words
  ILoWord list3 = new ConsLoWord(this.pear, this.list2);

  // an alphabetically sorted list with 3 active words
  ILoWord sortedList = new ConsLoWord(this.apple, 
      new ConsLoWord(this.banana, new ConsLoWord(this.pear, this.mt)));

  // a list of active words with two empty strings
  ILoWord listWithEmpty = new ConsLoWord(this.mtWordA, 
      new ConsLoWord(this.banana, new ConsLoWord(this.mtWordA, this.list3)));

  // a list with where two active words are identical
  ILoWord listWithTwoApples = new ConsLoWord(this.apple, this.list2);

  // a list where two active words are identical but one is upper case and one is not
  ILoWord listWithTwoDifferentApples = new ConsLoWord(new 
      ActiveWord("Apple", 3, 2), this.list3);

  // INACTIVE LISTS
  // a list with one inactive word
  ILoWord list4 = new ConsLoWord(this.avocado, this.mt);

  // a list with 2 inactive words
  ILoWord list5 = new ConsLoWord(this.strawberry, this.list4);

  // a list with 3 inactive words
  ILoWord list6 = new ConsLoWord(this.grape, this.list5);

  // an alphabetically sorted list with 3 inactive words
  ILoWord sortedList2 = new ConsLoWord(this.avocado, 
      new ConsLoWord(this.grape, new ConsLoWord(this.strawberry, this.mt)));

  // a list of inactive words with an empty string
  ILoWord listWithEmpty2 = new ConsLoWord(this.mtWordI, this.list6);

  // a list with where two inactive words are identical
  ILoWord listWithTwoApples2 = new ConsLoWord(this.strawberry, this.list6);

  // a list where two inactive words are identical but one is upper case and one is not
  ILoWord listWithTwoDifferentApples2 = new ConsLoWord(new 
      InactiveWord("Strawberry", 3, 2), this.list2);

  // a list with inactive and active words
  ILoWord list7 = new ConsLoWord(this.grape, this.list2);
  ILoWord list10 = new ConsLoWord(this.avocado, this.list2);
  ILoWord list12 = new ConsLoWord(this.avocado, new ConsLoWord(this.inactiveApple, this.mt));

  // a list with inactive and active words sorted
  ILoWord list8 = new ConsLoWord(this.banana, new ConsLoWord(new 
      InactiveWord("grape", 1, 5), this.mt));

  //a list with all empty strings
  ILoWord list9 = new ConsLoWord(this.mtWordA, new ConsLoWord(this.mtWordI, this.mt));
  ILoWord list11 = new ConsLoWord(this.mtWordA, new ConsLoWord(this.mtWordI, this.mt));
  ILoWord list20 = new ConsLoWord(this.mtWordA, new ConsLoWord(this.mtWordA, this.mt));

  ILoWord list21 = new ConsLoWord(this.letterA, this.mt);
  ILoWord list22 = new ConsLoWord(this.walk, this.mt);



  // a list with 3 inactive words, 3 active words, 2 empty strings, 
  // a duplicate string, and an upper case word
  ILoWord testList = new ConsLoWord(this.mtWordA, new ConsLoWord(this.apple, 
      new ConsLoWord(new ActiveWord("Apple", 3, 2), new ConsLoWord(this.strawberry, 
          new ConsLoWord(new InactiveWord("apple", 3, 2), 
              new ConsLoWord(this.mtWordI, this.mt))))));

  ILoWord testListSorted = new ConsLoWord(this.mtWordI, new ConsLoWord(this.mtWordA, 
      new ConsLoWord(new InactiveWord("apple", 3, 2), new ConsLoWord(new ActiveWord("Apple", 3, 2),
          new ConsLoWord(this.apple, 
              new ConsLoWord(this.strawberry, this.mt))))));


  // examples of WorldImage
  WorldImage imageEmpty =  new EmptyImage();
  WorldImage image1 =  new TextImage("apple", Color.RED);
  WorldImage image1b =  new TextImage("apple", Color.BLACK);
  WorldImage image2 =  new TextImage("banana", Color.RED);
  WorldImage image3 =  new TextImage("avocado", Color.BLACK);

  // examples of WorldScene 
  WorldScene w1 = new WorldScene(100, 100);
  WorldScene w2 = w1.placeImageXY(image1, 4, 4);
  WorldScene w3 = w1.placeImageXY(image1, 4, 4).placeImageXY(image2, 3, 3); 
  WorldScene w4 = w1.placeImageXY(imageEmpty, 1, 4);
  WorldScene w5 = w1.placeImageXY(image3, 1, 4);
  WorldScene w6 = w1.placeImageXY(image3, 1, 4).placeImageXY(image1b, 4, 4);
  WorldScene wFinal = new WorldScene(300, 600);

  // examples of ZTypeWorlds
  ZTypeWorld worldEmpty = new ZTypeWorld(this.mt, 10);
  ZTypeWorld worldWithAWord = new ZTypeWorld(this.list1, 100);
  ZTypeWorld worldWithTwoWords = new ZTypeWorld(this.list12, 100);
  ZTypeWorld worldWithAnInactiveWord = new ZTypeWorld(this.list4, 100);
  ZTypeWorld worldWithBoth = new ZTypeWorld(this.list21, 20);

  //tests for the method checkAndReduce 
  boolean testCheckAndReduce(Tester t) {
    return
        // checkAndReduce for an empty list
        t.checkExpect(this.mt.checkAndReduce("a"), this.mt)
        // checkAndReduce for a regular list of active words
        && t.checkExpect(this.sortedList.checkAndReduce("a"), 
            new ConsLoWord(new ActiveWord("pple",4, 4), 
                new ConsLoWord(this.banana, new ConsLoWord(this.pear, this.mt))))
        // given letter is now applicable to any word
        && t.checkExpect(this.sortedList.checkAndReduce("x"), this.sortedList)
        // check if a string matches in inputed letter
        && t.checkExpect(new ConsLoWord(new ActiveWord("a", 4, 4), 
            this.list5).checkAndReduce("a"),
            new ConsLoWord(new ActiveWord("", 4, 4), this.list5))
        // test for edge cases (also checks case sensitivity)
        && t.checkExpect(this.testList.checkAndReduce("a"), new ConsLoWord(this.mtWordA, 
            new ConsLoWord(new ActiveWord("pple", 4, 4), 
                new ConsLoWord(new ActiveWord("Apple", 3, 2), new ConsLoWord(this.strawberry, 
                    new ConsLoWord(new InactiveWord("apple", 3, 2), 
                        new ConsLoWord(this.mtWordI, this.mt)))))));
  }

  //tests for the method addToEnd 
  boolean testAddToEnd(Tester t) {
    return
        // addToEnd for an empty list
        t.checkExpect(this.mt.addToEnd(this.apple), this.list1)
        // addToEnd for a regular list of active words
        && t.checkExpect(this.sortedList.addToEnd(this.apple), 
            new ConsLoWord(this.apple, 
                new ConsLoWord(this.banana, new ConsLoWord(this.pear, 
                    new ConsLoWord(this.apple,
                        this.mt)))))
        // check to add empty string
        && t.checkExpect(this.sortedList.addToEnd(this.mtWordA), 
            new ConsLoWord(this.apple, 
                new ConsLoWord(this.banana, new ConsLoWord(this.pear, 
                    new ConsLoWord(this.mtWordA,
                        this.mt)))))
        // test for edge cases
        && t.checkExpect(this.testList.addToEnd(this.apple), 
            new ConsLoWord(this.mtWordA, new ConsLoWord(this.apple, 
                new ConsLoWord(new ActiveWord("Apple", 3, 2), 
                    new ConsLoWord(this.strawberry, 
                        new ConsLoWord(new InactiveWord("apple", 3, 2), 
                            new ConsLoWord(this.mtWordI, 
                                new ConsLoWord(this.apple, this.mt))))))));
  }

  //tests for the method filterOutEmpties 
  boolean testFilterOutEmpties(Tester t) {
    return
        // filterOutEmpties for an empty list
        t.checkExpect(this.mt.filterOutEmpties(), this.mt)
        // filterOutEmpties for a regular list of active words with no empties
        && t.checkExpect(this.sortedList.filterOutEmpties(), this.sortedList)
        // filterOutEmpties on a list of all empties
        && t.checkExpect(this.list9.filterOutEmpties(), this.mt)
        // filterOutEmpties on edge case list (list with words and empties)
        && t.checkExpect(this.testListSorted.filterOutEmpties(), 
            new ConsLoWord(new InactiveWord("apple", 3, 2), 
                new ConsLoWord(new ActiveWord("Apple", 3, 2),
                    new ConsLoWord(this.apple, 
                        new ConsLoWord(this.strawberry, this.mt)))));
  }

  // tests for the method draw 
  boolean testDraw(Tester t) {
    return 
        // test for a list of one active word
        t.checkExpect(this.list1.draw(w1), w2)
        // test for a list of two active words
        && t.checkExpect(this.list2.draw(w1), w3)
        // test for a empty list
        && t.checkExpect(this.mt.draw(w1), w4)
        // test for a list of all inactive words
        && t.checkExpect(this.list4.draw(w1), w5)
        // test for a list of active and inactive words
        && t.checkExpect(this.list12.draw(w1), w6);
  }

  // tests for filterHelper
  boolean testFilterHelper(Tester t) {
    return 
        // test for an empty string
        t.checkExpect(this.mtWordA.filterHelper(), true)
        // test for a string that has characters
        && t.checkExpect(this.apple.filterHelper(), false); 
  }


  // tests for checkHelper
  boolean testCheckHelper(Tester t) {
    return 
        // test for an active word that starts with the given string
        t.checkExpect(this.apple.checkHelper("a"), new ActiveWord("pple", 4, 4))
        // test for an active word that does not start with the given string
        && t.checkExpect(this.apple.checkHelper("b"), new ActiveWord("apple", 4, 4))
        // test for an inactive word
        && t.checkExpect(this.grape.checkHelper("g"), new InactiveWord("grape", 1, 5)); 
  }

  // tests for compare
  boolean testCompare(Tester t) {
    return 
        // test an IWord compared to its own name
        t.checkExpect(this.apple.compare("apple"), 0)
        // test an IWord compared to its own name but capitalized to 
        // case insensitivity 
        && t.checkExpect(this.apple.compare("Apple"), 0)
        // test an IWord compared to a word it comes before
        && t.checkExpect(this.apple.compare("banana"), 1)
        // test an IWord compared to a word it comes after
        && t.checkExpect(this.banana.compare("apple"), -1); 
  }

  // tests for extractCompare
  boolean testExtractCompare(Tester t) {
    return 
        // test an IWord compared to its own name
        t.checkExpect(this.apple.extractCompare(this.apple), 0)
        // test an IWord compared to its own name but capitalized to 
        // case insensitivity 
        && t.checkExpect(this.apple.extractCompare(this.apple2), 0)
        // test an IWord compared to a word it comes before
        && t.checkExpect(this.apple.extractCompare(this.banana), -1)
        // test an IWord compared to a word it comes after
        && t.checkExpect(this.banana.extractCompare(this.apple), 1); 
  }

  // tests for makeScene
  boolean testMakeScene(Tester t) {
    return 
        // test for makeScene on a world with an empty list of words 
        t.checkExpect(this.worldEmpty.makeScene(), this.mt.draw(wFinal))
        // test for makeScene on a world with a list of one active word
        && t.checkExpect(this.worldWithAWord.makeScene(), this.list1.draw(wFinal))
        // test for makeScene on a world with a list of one inactive word
        && t.checkExpect(this.worldWithAnInactiveWord.makeScene(), this.list4.draw(wFinal));

  }

  //test for isOff on IWords
  boolean testIsOff(Tester t) {
    // test on a word that does have 600+ as a y coordinate
    return t.checkExpect(this.apple.isOff(), false)
        // test on a word that does have 600+ as a y coordinate
        && t.checkExpect(new ActiveWord("apple", 100, 606).isOff(), true);
  }

  //test for makeActive on IWords
  boolean testMakeActive(Tester t) {
    // test on a word that is inactive
    return t.checkExpect(this.inactiveApple.makeActive(), this.apple)
        // test on a word that is active
        && t.checkExpect(this.apple.makeActive(), this.apple);
  }

  //test for turnActive on IWords
  boolean testTurnActive(Tester t) {
    //test on a list that has an active word
    return t.checkExpect(this.list1.turnActive("a"), this.list1)
        // test on a list that has an inactive first
        &&  t.checkExpect(this.list6.turnActive("g"), new ConsLoWord(new ActiveWord("grape", 1, 5), 
            this.list5))
        //test on a list that makes the second word in the list the active word
        &&  t.checkExpect(this.list6.turnActive("s"), new ConsLoWord(this.grape, 
            new ConsLoWord(new ActiveWord("strawberry", 5, 1), this.list4)))
        // test on a list using an unseen starting letter
        && t.checkExpect(this.list1.turnActive("z"), this.list1);

  }

  //test for anyOff on ILoWord
  boolean testAnyOff(Tester t) {
    // test on an empty list
    return t.checkExpect(new MtLoWord().anyOff(), false)
        // test on a non-empty empty list
        && t.checkExpect(this.list22.anyOff(), true);
  }

  boolean testOnKey(Tester t) {
    return 
        // test for OnKey on a world with a single of word 
        t.checkExpect(this.worldWithAWord.onKeyEvent("a"), 
            new ZTypeWorld(new ConsLoWord(new ActiveWord("pple", 4, 4), this.mt), 100))
        && t.checkExpect(this.worldWithBoth.onKeyEvent("a"), 
            new ZTypeWorld(this.mt, 20))
        && t.checkExpect(this.worldWithAWord.onKeyEvent("p"), 
            new ZTypeWorld(new ConsLoWord(new ActiveWord("apple", 4, 4), this.mt), 100))
        && t.checkExpect(this.worldWithTwoWords.onKeyEvent("a"), 
            new ZTypeWorld(new ConsLoWord(new ActiveWord("vocado", 1, 4), 
                new ConsLoWord(this.inactiveApple, this.mt)), 100))
        && t.checkExpect(this.worldWithAnInactiveWord.onKeyEvent("a"), 
            new ZTypeWorld(new ConsLoWord(new ActiveWord("vocado", 1, 4), this.mt), 100));
  }

  // tests for keyMatchesWord 
  boolean testKeyMatchesWord(Tester t) {
    // test where key matches the the word
    return t.checkExpect(this.apple.keyMatchesWord("a"), true)
        // test where key does not match the the word
        && t.checkExpect(this.apple.keyMatchesWord("b"), false);
  }

  //tests for keyMatchesList
  boolean testKeyMatchesList(Tester t) {
    // test where key matches a word in the list 
    return t.checkExpect(this.list1.keyMatchesList("a"), true)
        // test where key does not match the the word
        && t.checkExpect(this.list1.keyMatchesList("b"), false)
        // test on empty list
        && t.checkExpect(this.mt.keyMatchesList("b"), false);
  }

  // test for onTick
  boolean testOnTick(Tester t) {
    return 
        // test for onTick 
        t.checkExpect(this.worldWithAWord.onTick(), 
            new ZTypeWorld(new ConsLoWord(new ActiveWord("apple", 4, 7), this.mt), 101))
        && t.checkExpect(this.worldWithAWord.onTick(), 
            new ZTypeWorld(new ConsLoWord(new ActiveWord("apple", 4, 7), this.mt), 101));
  }

  //test for lastScene
  boolean testLastScene(Tester t) {
    return 
        // test for lastScene
        t.checkExpect(this.worldWithAWord.lastScene("asd"), 
            new WorldScene(300, 600).placeImageXY(new TextImage("asd", 18, Color.BLACK), 150, 300));
  }

  // test fro Isactive
  boolean testIsActive(Tester t) {
    return 
        //testing an active word
        t.checkExpect(this.apple.isActive(), true)
        &&
        //testing an inactive word
        t.checkExpect(this.avocado.isActive(), false);
  }

  // test for anyActive
  boolean testAnyActive(Tester t) {
    return 
        //checking a list with an active word
        t.checkExpect(this.list1.anyActive(), true)
        &&
        //checking a list with no active words
        t.checkExpect(this.list4.anyActive(), false)
        &&
        //checking an empty list
        t.checkExpect(this.mt.anyActive(), false);
  }
  
  //renders image of world
  boolean testBigBang(Tester t) {
    ZTypeWorld world = new ZTypeWorld(new MtLoWord(), 0);
    int worldWidth = 300;
    int worldHeight = 600;
    double tickRate = .05;
    return world.bigBang(worldWidth, worldHeight, tickRate);
  }
}
