package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class CommandLine {
    public void exec() throws IOException {
        Database db = new Database(DbInit.init());
        Scanner scanner = new Scanner(System.in);
        System.out.println("CRUD application v0.4");
        while (true) {
            System.out.print("# ");
            String cmd = scanner.next();
            switch (cmd.toLowerCase().charAt(0)) {
                case 'c':
                    db.create();
                    break;
                case 'r':
                    db.read();
                    break;
                case 'u':
                    db.update();
                    break;
                case 'd':
                    db.delete();
                    break;
                case 'f':
                    db.find();
                    break;
                case 'g':
                    db.group();
                    break;
                case 's':
                    db.sort();
                    break;
                case 'v':
                    db.saveToFile();
                    break;
                case 'x':
                    System.out.println("Exit of command line");
                    return;
                default:
                    System.out.println("List of command: [c]reate, [r]ead, [u]pdate, [d]elete, [f]ind, [g]roup, [s]ort,  sa[v]e to file, e[x]it]");
            }
        }
    }
}
