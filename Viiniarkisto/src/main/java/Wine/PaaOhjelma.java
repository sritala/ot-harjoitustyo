/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wine;

import java.util.Scanner;

/**
 *
 * @author susanna
 */
public class PaaOhjelma {
    
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        WineArchive va = new WineArchive(input);
        va.start();
    }
    
}
