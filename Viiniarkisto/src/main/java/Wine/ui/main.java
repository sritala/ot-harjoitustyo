package Wine.ui;

import java.util.Scanner;

/**
 * main luokka
 */
public class main {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Ui va = new Ui(input);
        va.setup();

    }

}
