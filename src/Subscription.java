
import java.io.PrintWriter;

/**
 *
 * @author Isleam Gabriel-Mihai
 */
public abstract class Subscription {

    int visits_number;                      // numarul de accesari
    String name;
    int timestamp;

    abstract void subtraction();

    abstract void display(PrintWriter printW, int cache);

}
