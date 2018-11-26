package logical;

import javax.swing.*;

import org.jpl7.Query;



public class Main {
    public static void main(String[] args) {
    	Query.hasSolution("use_module(library(jpl))"); 
		String t1 = "consult('idastar2.pl')";
        System.out.println(t1 + " " + (Query.hasSolution(t1) ? "succeeded" : "failed"));


        JFrame window = new JFrame("Slide Puzzle");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new GUI());
        window.pack();
        window.setVisible(true);
        window.setResizable(false);
    }
}