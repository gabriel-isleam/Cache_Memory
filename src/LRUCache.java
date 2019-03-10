
/**
 *
 * @author Isleam Gabriel-Mihai
 */
public class LRUCache implements Cache {

    /**
     * add adauga elementul dat in memoria cache
     * 
     * @param cache - vectorul in care este retinuta memoria cache
     * @param to_add - elementul ce trebuie adaugat
     * @param timestamp_min - util pentru remove
     */
    @Override
    public void add(Subscription[] cache, Subscription to_add, int timestamp_min) {

        for (int i = 0; i < cache.length; i++) {
            if (cache[i] == null) {
                cache[i] = to_add;
                return;
            }
        }

        remove(cache, null, timestamp_min);
        cache[cache.length - 1] = to_add;
    }

    /**
     * remove elimina un element dupa algoritmul LRU (in cazul in care to_remove = null) sau un element dat
     *
     * @param cache - vectorul in care este retinuta memoria cache
     * @param to_remove - elementul ce trebuie eliminat
     * @param timestamp_min - timestamp-ul minim initial (cel mai mare posibil - numarul total de add/get)
     */
    @Override
    public void remove(Subscription[] cache, Subscription to_remove, int timestamp_min) {

        int aux = 0;

        if (to_remove != null) {
            for (int i = 0; i < cache.length; i++) {
                if (cache[i].name.equals(to_remove.name)) {
                    aux = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < cache.length; i++) {
                if (cache[i].timestamp < timestamp_min) {
                    timestamp_min = cache[i].timestamp;
                    aux = i;
                }
            }
        }

        for (int i = aux; i < cache.length - 1; i++) {
            cache[i] = cache[i + 1];
        }

        cache[cache.length - 1] = null;
    }
}
