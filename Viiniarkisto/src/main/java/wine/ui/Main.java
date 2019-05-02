/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wine.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author susanna
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws Exception
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        Scanner input = new Scanner(System.in);
        Ui va = new Ui(input);
        va.setup();
    }

}
