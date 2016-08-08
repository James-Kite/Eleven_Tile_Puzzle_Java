/*
 *  Program to solve the eleven tile puzzle for CO528 Intro to intelligent Systems
 */
package eleventile;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author James
 */
public class ElevenTile {
    private static final ArrayList<String> filenames = new ArrayList();
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // fill filenames array list with filenames
        filenames.add("+bb+acb_+ddd+dad2+bb+addd+a_b+dcd.txt");
        filenames.add("+db+a_db+cad+ddb2+db+adbd+a_d+bcd.txt");
        filenames.add("+_d+adbd+cdd+bab2+db+addd+bad+_bc.txt");
        filenames.add("+bc+abdd+_db+dda2+cb+adbd+bdd+_da.txt");
        filenames.add("+bd+ad_d+dbd+cab2+bd+_add+cdd+bba.txt");
        filenames.add("+cd+abd_+bda+dbd2+cd+adad+bd_+bdb.txt");
        filenames.add("+a_+abdc+ddb+dbd2+ba+acd_+dbd+dbd.txt");
        filenames.add("+dd+a_dd+bbd+bca2+bd+adad+d_d+bbc.txt");
        filenames.add("+dc+aa_b+ddd+bbd2+ca+adbd+_dd+dbb.txt");
        filenames.add("+dd+adb_+abd+bcd2+dd+_aad+bdb+cbd.txt");
        filenames.add("+db+adb_+cad+ddb2+bd+ad_b+dcd+dba.txt");
        filenames.add("+bc+addb+bdd+a_d2+db+ac_b+dad+ddb.txt");
        filenames.add("+bd+ad_b+acd+dbd2+bd+_adb+adc+bdd.txt");
        filenames.add("+c_+adda+bdd+dbb2+_a+acdd+bdd+dbb.txt");
        filenames.add("+bc+abdd+dd_+dab2+bc+addb+dbd+d_a.txt");
        filenames.add("+dc+aa_d+bdd+dbb2+cd+ad_b+add+bdb.txt");
        // Loop that runs the algorithm and outputs the route to a text file for each filename
        for (String file : filenames) {
            System.out.println("Solving a new puzzle!");
            //open filewirters to write text files.
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            // Separating the initial and final states from the filename to run iterative deepening algorithm with
            String input = "";
            for (int i = 0; i != 16; i++) {
                input += file.charAt(i);
            }
            String finalState = "";
            for (int j = 17; j != 33; j++) {
                finalState += file.charAt(j);
            }
            // run the iterative deepening algorithm
            LinkedList<String> route = search_TileBoard(input, finalState);
            // create output line variables
            String line_1 = "";
            String line_2 = "";
            String line_3 = "";
            String line_4 = "";
            // concatenate each line by cycling through the route list and writing the board lines separated by spaces, one at a time
            for (String node : route) {
                TileBoard b = new TileBoard(node, finalState);
                line_1 += b.get_packed_state().charAt(0);
                line_1 += b.get_packed_state().charAt(1); 
                line_1 += b.get_packed_state().charAt(2); 
                line_1 += b.get_packed_state().charAt(3);
                line_1 += " ";
                line_2 += b.get_packed_state().charAt(4);
                line_2 += b.get_packed_state().charAt(5); 
                line_2 += b.get_packed_state().charAt(6); 
                line_2 += b.get_packed_state().charAt(7);
                line_2 += " ";
                line_3 += b.get_packed_state().charAt(8);
                line_3 += b.get_packed_state().charAt(9); 
                line_3 += b.get_packed_state().charAt(10); 
                line_3 += b.get_packed_state().charAt(11);
                line_3 += " ";
                line_4 += b.get_packed_state().charAt(12);
                line_4 += b.get_packed_state().charAt(13); 
                line_4 += b.get_packed_state().charAt(14); 
                line_4 += b.get_packed_state().charAt(15);
                line_4 += " ";
            }
            //actually write the lines to the file now
            bw.write(line_1);
            bw.newLine();
            bw.write(line_2);
            bw.newLine();
            bw.write(line_3);
            bw.newLine();
            bw.write(line_4);
            bw.close();
            System.out.println("Puzzle finished!");
        }
    }
    
    // iterative depth search
    public static LinkedList<String> search_TileBoard(String start, String end) {
        for (int depth = 1; true; depth++) {
            // to keep track of how deep the algorithm is currently
            System.out.println("Depth: " + depth);
            // run depth first search on the current node, to the current depth
            LinkedList<String> route = depthFirst(start, end, depth);
            // return the route if solution found
            if (route != null) {
                return route;
            }
        }
    }
    
    //  depth first search
    public static LinkedList<String> depthFirst(String start, String end, int depth) {
        if (depth == 0) {
            return null;
        } else if (start.equals(end)) {
            LinkedList<String> route = new LinkedList();
            route.add(end);
            return route;
        } else {
            TileBoard node = new TileBoard(start, end);
            ArrayList<String> next_nodes = expand_node(node);
            for (String next : next_nodes) {
                LinkedList<String> route = depthFirst(next, end, depth - 1);
                if (route != null) {
                    route.addFirst(start);
                    return route;
                }
            }
            return null;
        }
    }
    
    // next configs method
    public static ArrayList<String> expand_node(TileBoard board) {
        ArrayList<String> output = new ArrayList();
        String up = board.return_up_string();
        String right = board.return_right_string();
        String down = board.return_down_string();
        String left = board.return_left_string();
        if (up != null) {
            output.add(up);
        }
        if (right != null) {
            output.add(right);
        }
        if (down != null) {
            output.add(down);
        }
        if (left != null) {
            output.add(left);
        }
        return output;
    }
}
