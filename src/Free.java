
import java.io.PrintWriter;

/**
 *
 * @author Isleam Gabriel-Mihai
 */
public class Free extends Subscription {

    /**
     * display afiseaza intregul primit ca parametru si "Free", tipul subscriptiei
     * 
     * @param printW - fisierul in care se scrie
     * @param cache - 0, 1, 2 in functie de aparitia elementului in cache/memoria principala
     */
    @Override
    void display(PrintWriter printW, int cache) {

        printW.println(cache + " Free");
    }

    Free(String name, int timestamp) {
        this.name = name;
        this.timestamp = timestamp;
        this.visits_number = 0;
    }

    /**
     * subtraction nu face nimic deoarece numarul de subscriptii free este nelimitat
     * si nu va fi nevoie de vreo scadere ca in cazul basic/premium
     */
    @Override
    void subtraction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
