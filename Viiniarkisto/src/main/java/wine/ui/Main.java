package wine.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Setup class for the application
 *
 * @author susanna
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        Scanner input = new Scanner(System.in);
        Ui va = new Ui(input);
        va.setup();
    }

}
