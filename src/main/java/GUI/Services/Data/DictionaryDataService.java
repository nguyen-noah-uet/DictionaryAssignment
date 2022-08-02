package GUI.Services.Data;

import GUI.Entities.Vocabulary;

import javax.persistence.*;
import java.util.List;

public class DictionaryDataService implements IDataService<Vocabulary> {

    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public DictionaryDataService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void getById(int id) {
        Vocabulary vocabulary = entityManager.find(Vocabulary.class, id);
        if (vocabulary == null) {
            throw new EntityNotFoundException("Cannot find vocabulary entity has id: " + id);
        }
    }

    @Override
    public List<Vocabulary> getWords(int from, int to) {
        return entityManager.createQuery("select v from Vocabulary v")
                .setFirstResult(from)
                .setMaxResults(to)
                .getResultList();
    }

    @Override
    public List<Vocabulary> findWord(String word) {
        Query query = entityManager.createQuery("select v from Vocabulary v where v.word like :word");
        query.setParameter("word", word + "%");
        query.setMaxResults(30);
        return query.getResultList();
    }

    @Override
    public Vocabulary findById(int id) {
        Vocabulary vocabulary = entityManager.find(Vocabulary.class, id);
        if (vocabulary == null) {
            throw new EntityNotFoundException("Cannot find Vocabulary entity has id: " + id);
        }
        return vocabulary;
    }

    @Override
    public void add(Vocabulary entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(int id, Vocabulary entity) {
        entityManager.getTransaction().begin();
        Vocabulary vocabulary = findById(id);
        if (vocabulary == null) {
            throw new EntityNotFoundException("Cannot find Airport entity has id: " + entity.getId());
        }
        // update
        vocabulary.setWord(entity.getWord());
        vocabulary.setDetailString(entity.getDetailString());
        entityManager.getTransaction().commit();
    }

    @Override
    public void remove(int id) {
        entityManager.getTransaction().begin();
        Vocabulary vocabulary = findById(id);
        if (vocabulary == null) {
            throw new EntityNotFoundException("Cannot find Vocabulary entity has id: " + id );
        }
        entityManager.remove(vocabulary);
        entityManager.getTransaction().commit();
    }
}
