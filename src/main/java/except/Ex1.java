package except;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Ex1 {

  public static void main(String[] args) {
    try {
      new FileInputStream("xxx.txt");
      if (Math.random() > 0.5) throw new SQLException("DB Broke");
//    } catch (Exception e) { // BAD catches too many exceptions!
//      System.out.println("that failed");
//    }
    } catch (FileNotFoundException | SQLException e) { // New with Java 7 "multi-catch" BETTER

//      Pattern matching (Java 14 feature)
//      if (e instanceof FileNotFoundException fnfe)  {
//        fnfe is initialized as pointer to e, with type FileNotFoundException
//      }
      System.out.println("that failed");
    }

//    } catch (FileNotFoundException a) { // bad, duplicates code
//      System.out.println("that failed");
//    } catch (SQLException b) {
//      System.out.println("that failed");
//    }

//    Throwable t = null;
    BufferedReader in = null;
    try {
      // Pre-Java 9, use Paths.get..
//      BufferedReader in = Files.newBufferedReader(Paths.get("mytext.txt"));
      in = Files.newBufferedReader(Path.of("mytext.txt")); // Java 9 Path.of...

      // maybe throw BusinessLogicException.. WANT this to go to the caller...
    } catch (IOException fnfe) { // no catch for BusinessLogicException...

//    } catch (Throwable t1) {
//      t = t1;
    }
    finally {

      // close all resources..
       if (in != null) {
         try {
           in.close(); // exception here? Will REPLACE a BusinessLogicException BUGGY!!
         } catch (IOException e) {
           e.printStackTrace();
         }
       }
//       if (t != null) throw t;
    }
  }
}
