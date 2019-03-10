
/**
 *
 * @author Isleam Gabriel-Mihai
 */
public class LFUCache implements Cache {

    /**
     * add adauga elementul dat in memoria cache
     * 
     * @param cache - vectorul in care este retinuta memoria cache
     * @param to_add - elementul ce trebuie adaugat
     * @param aux_min - util pentru remove
     */
    @Override
    public void add(Subscription[] cache, Subscription to_add, int aux_min) {

        for (int i = 0; i < cache.length; i++) {
            if (cache[i] == null) {
                cache[i] = to_add;
                return;
            }
        }

        remove(cache, null, aux_min);
        cache[cache.length - 1] = to_add;
    }

   /**
     * remove elimina un element dupa algoritmul LFU (in cazul in care to_remove = null) sau un element dat
     * - elementul ce trebuie eliminat va fi comparat doar dupa numarul de vizite deoarece elementele sunt pozitionate 
     * in vector in ordinea adaugarii in vector (inserarea se face mereu la final)
     * 
     * 
     * @param cache - vectorul in care este retinuta memoria cache
     * @param to_remove - elementul ce trebuie eliminat
     * @param aux_min - numarul minim de vizite initial (cel mai mare posibil - numarul total de add/get)
     */
    @Override
    public void remove(Subscription[] cache, Subscription to_remove, int aux_min) {

        int aux = cache.length, visits_min = aux_min;
        if (to_remove != null) {
            for (int i = 0; i < cache.length; i++) {
                if (cache[i].name.equals(to_remove.name)) {
                    aux = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < cache.length; i++) {
                if (cache[i].visits_number < visits_min) {
                    visits_min = cache[i].visits_number;
                    aux = i;
                } 
            }
        }

        cache[aux].visits_number = 0;                                   //numarul de vizite se reseteaza deoarece 
                                                                        //elementul este scos din cache
        
        for (int i = aux; i < cache.length - 1; i++) {
            cache[i] = cache[i + 1];
        }

        cache[cache.length - 1] = null;
    }
}