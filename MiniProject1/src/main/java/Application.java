import project.CommandLine;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * This is a Java mini-project on base of CRUD-technology
 *
 * @author Artysh Namchyl
 * @version 0.2
 */

public class Application {
    public static void main(String[] args) throws URISyntaxException, IllegalAccessException, FileNotFoundException {
        new CommandLine().exec();
    }
}
