
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
