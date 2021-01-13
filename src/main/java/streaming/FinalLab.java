package streaming;

public class FinalLab {
  public static void main(String[] args) {
    /*
    Goal: Create a "concordance" -- table of word frequencies
    200 most frequent words, in descending order

    1) Read a file to create a stream
       - look in java.nio.file.Files
       - use forEach to print the entire book, off that stream
    2) Convert from Stream of lines to a Stream of words
       - Pattern (regular expression) "\\W+"
       - ONE line -> MANY words... which method must we use
       - what's the best way to get Pattern to help?

    3) This pattern occasionally produces empty strings
       - we do NOT want them to be counted..

    4) "The", "the" are "the same word"...
       - ensure they will be counted as such

    5) Build a map of key = some word - value = count of how many times it showed up
       - this is a collect, but which combination?

    6) take the map, extract a stream of Map.Entry

    7) Sort the stream of  Map.Entry in DESCENDING order of Map.Entry "Value"

    8) Cut the stream off after 200 items

    9) pretty format the result

    10) print is all.

     */
  }
}
