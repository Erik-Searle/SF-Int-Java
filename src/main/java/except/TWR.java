package except;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;

public class TWR {
  public static void main(String[] args) throws IOException {
    // must be final or "effectively final" (i.e. never changed!)
    /*final */BufferedReader br = Files.newBufferedReader(Path.of("other.txt"));
    try (
        // "resources" must "implements AutoCloseable"
        // Java 7, 8, must be initialized declarations.
        br; // Java 9+ can be a simple final variable ref
        BufferedReader in = Files.newBufferedReader(Path.of("mytext.txt")); // Java 9 Path.of...
        BufferedWriter out = Files.newBufferedWriter(Path.of("out.txt"))/* optional trailing semi ;*/
    ) {
      // maybe throw BusinessLogicException.. WANT this to go to the caller...
//    } catch (IOException fnfe) { // no catch for BusinessLogicException...

    }
    // compiler generates a finall implicitly, handles the closure of *all* resources
    // including any close exceptions
    // in our example, might throw BusinessLogicException carry "suppressed" exceptions
    // which are any close problems that arose
  }
}
