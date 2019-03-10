
/**
 *
 * @author Isleam Gabriel-Mihai
 */
interface Cache {

    void add(Subscription[] cache, Subscription to_add, int timestamp_min);

    void remove(Subscription[] cache, Subscription to_remove, int timestamp_min);

}
