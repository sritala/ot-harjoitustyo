package Wine.ui;

import java.util.Scanner;

/**
 * main luokka
 */
public class PaaOhjelma {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        WineArchive va = new WineArchive(input);
        va.setup();

    }

}
