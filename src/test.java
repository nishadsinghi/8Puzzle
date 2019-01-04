import java.util.*;
import java.io.*;

public class test{
	public static void main(String args[]) throws IOException{
            String source = "12346875G";
            String goal = "12346857G";
            
            Puzzle o = new Puzzle();
            System.out.println(o.solvable(source, goal));
        }
}

