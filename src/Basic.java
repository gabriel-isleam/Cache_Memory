import java.io.PrintWriter;

/**
 *
 * @author Isleam Gabriel-Mihai
 */
public class Basic extends Free {

    int number_basic;

   /**
     * subtraction scade numarul de subscriptii basic ale unui element
     */
    @Override
    void subtraction() {
        if (number_basic > 0) {
            number_basic--;
        }
    }

    
    /**
     * display afiseaza intregul primit ca parametru si "Basic", tipul subscriptiei
     * daca elementul mai are subscriptii basic
     * 
     * @param printW - fisierul in care se scrie
     * @param cache - 0, 1, 2 in functie de aparitia elementului in cache/memoria principala
     */
    @Override
    void display(PrintWriter printW, int cache) {
        if (number_basic > 0) {
            printW.println(cache + " Basic");
        } else {
            super.display(printW, cache);
        }
    }

    Basic(String name, int nr, int timestamp) {
        super(name, timestamp);
        number_basic = nr;
        this.timestamp = timestamp;
        this.visits_number = 0;
    }
}
