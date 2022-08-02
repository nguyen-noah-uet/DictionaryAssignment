package GUI.Services.Data;

import java.util.List;

public interface IDataService<T> {
    void getById(int id);

    List<T> getWords(int from, int to);

    List<T> findWord(String word);

    T findById(int id);

    void add(T entity);

    void update(int id, T entity);

    void remove(int id);
}
