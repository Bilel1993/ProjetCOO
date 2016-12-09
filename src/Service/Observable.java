package Service;


public interface Observable {
    void add(Observateur o);
    void notifier();
}
