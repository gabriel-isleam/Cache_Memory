
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Isleam Gabriel-Mihai
 */
public class Main {

    /**
     * search cauta si intoarce elementul cu numele dat din memoria principala
     * 
     * @param memory - vectorul in care este retinuta memoria principala
     * @param name - numele ce va fi cautat in memorie
     * 
     * @throws NullPointerException - in cazul in care memoria este goala
     */
    public static Subscription search(Subscription[] memory, String name) throws NullPointerException {
        if (memory == null) {
            throw new NullPointerException();
        }
        for (Subscription iterator : memory) {
            if (name.equals(iterator.name)) {
                return iterator;
            }
        }
        return null;
    }

    /**
     * memory_overwrite suprascrie elementul cu acelasi nume din memoria principala
     * 
     * @param memory - vectorul in care este retinuta memoria principala
     * @param to_overwrite - elementul nou creat ce va suprascrie vechiul element cu acelasi nume
     */
    public static void memory_overwrite(Subscription[] memory, Subscription to_overwrite) {
        for (int i = 0; i < memory.length; i++) {
            if (memory[i].name.equals(to_overwrite.name)) {
                memory[i] = to_overwrite;
                return;
            }
        }
    }

    /**
     *
     * @param args - argumentele primite in linia de comanda
     */
    public static void main(String[] args) {

        Scanner scanner = null;
        try {
            File file_in = new File(args[0]);
            scanner = new Scanner(file_in);
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found!");
            return;
        }

        PrintWriter printW = null;
        try {
            File file_out = new File(args[1]);
            printW = new PrintWriter(file_out);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        String cache_type = scanner.nextLine();
        Cache cache_method;

        if (cache_type.equals("FIFO")) {
            cache_method = new FIFOCache();
        } else if (cache_type.equals("LRU")) {
            cache_method = new LRUCache();
        } else {
            cache_method = new LFUCache();
        }

        int max_cache = Integer.parseInt(scanner.nextLine());
        Subscription[] cache = new Subscription[max_cache];
        int operations_total = Integer.parseInt(scanner.nextLine());
        int timestamp = 0;

        String line;
        String[] words;
        Subscription place, cache_place;
        Subscription[] memory = new Subscription[operations_total];       //cazul extrem, imposibil in care toate operatiile 
        int index = 0;                                                    //sunt ADD (de elemente diferite), astfel toate elementele

        for (int i = 0; i < operations_total; i++) {
            place = null;
            cache_place = null;
            line = scanner.nextLine();
            words = line.split(" ");

            try {
                place = search(memory, words[1]);
            } catch (NullPointerException e) {
                place = null;
            }

            try {
                cache_place = search(cache, words[1]);
            } catch (NullPointerException e) {
                cache_place = null;
            }

            if (words[0].equals("ADD")) {
                if (words.length == 4) {
                    if (place == null) {
                        memory[index] = new Premium(words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]), timestamp);
                        timestamp++;
                        index++;
                    } else {
                        place = new Premium(words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]), timestamp);
                        memory_overwrite(memory, place);
                        timestamp++;
                        if (cache_place != null) {
                            cache_method.remove(cache, place, operations_total);
                        }
                    }
                } else if (words.length == 3) {
                    if (place == null) {
                        memory[index] = new Basic(words[1], Integer.parseInt(words[2]), timestamp);
                        timestamp++;
                        index++;
                    } else {
                        place = new Basic(words[1], Integer.parseInt(words[2]), timestamp);
                        memory_overwrite(memory, place);
                        timestamp++;
                        if (cache_place != null) {
                            cache_method.remove(cache, place, operations_total);
                        }
                    }
                }

            } else if (words[0].equals("GET")) {
                if (place == null) {
                    printW.println("2");
                } else {
                    place.timestamp = timestamp;
                    place.visits_number++;
                    timestamp++;
                    if (cache_place != null) {
                        place.display(printW, 0);
                    } else {
                        place.display(printW, 1);
                        cache_method.add(cache, place, operations_total);
                    }
                    place.subtraction();
                }
            }
        }

        printW.close();
    }
}
