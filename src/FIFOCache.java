
/**
 *
 * @author Isleam Gabriel-Mihai
 */
public class FIFOCache implements Cache {

    /**
     * add adauga elementul dat in memoria cache
     * 
     * @param cache - reprezinta vectorul memoriei cache
     * @param to_add - elementul ce trebuie adaugat
     * @param timestamp_min - util pentru LRU/LFU
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
     * remove elimina un element dupa algoritmul FIFO (in cazul in care to_remove = null) sau un element dat
     *
     * @param cache - vectorul in care este retinuta memoria cache
     * @param to_remove - elementul ce trebuie eliminat
     * @param timestamp_min - util pentru LRU
     */
    @Override
    public void remove(Subscription[] cache, Subscription to_remove, int timestamp_min) {

        int aux = 0;
        if (to_remove == null) {
            aux = 0;
        } else {
            for (int i = 0; i < cache.length; i++) {
                if (cache[i].name.equals(to_remove.name)) {
                    aux = i;
                    break;
                }
            }
        }

        for (int i = aux; i < cache.length - 1; i++) {
            cache[i] = cache[i + 1];
        }

        cache[cache.length - 1] = null;
    }
}
