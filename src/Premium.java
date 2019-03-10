
import java.io.PrintWriter;

/**
 *
 * @author Isleam Gabriel-Mihai
 */
public class Premium extends Basic {

    int number_premium;

    /**
     * subtraction scade numarul de subscriptii premium ale unui element;
     * daca nu mai are subscriptii Premium, apeleaza functia de scadere a numarului
     * de subscriptii Basic
     */
    @Override
    void subtraction() {
        if (number_premium > 0) {
            number_premium--;
        } else {
            super.subtraction();
        }
    }

    /**
     * display afiseaza intregul primit ca parametru si "Premium", tipul subscriptiei
     * daca elementul mai are subscriptii premium
     * 
     * @param printW - fisierul in care se scrie
     * @param cache - 0, 1, 2 in functie de aparitia elementului in cache/memoria principala
     */
    @Override
    void display(PrintWriter printW, int cache) {
        if (number_premium > 0) {
            printW.println(cache + " Premium");
        } else {
            super.display(printW, cache);
        }
    }

    Premium(String name, int nr_basic, int nr_premium, int timestamp) {
        super(name, nr_basic, timestamp);
        number_premium = nr_premium;
        this.timestamp = timestamp;
        this.visits_number = 0;
    }
}
