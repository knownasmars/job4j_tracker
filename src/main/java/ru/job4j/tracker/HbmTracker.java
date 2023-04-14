package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.createQuery("from Item");
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            close();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Item SET name = :name, created = :created"
                                    + " WHERE id = :id")
                    .setParameter("name", item.getName())
                    .setParameter("created", Timestamp.valueOf(item.getCreated()))
                    .setParameter("Id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            close();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE Item WHERE id = :Id")
                    .setParameter("Id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            close();
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        List<Item> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            rsl = session.createQuery("from Item").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            close();
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String name) {
        Session session = sf.openSession();
        List<Item> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            var query = session.createQuery(
                            "from Item where name = :name")
                    .setParameter("name", name);
            rsl = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            close();
        }
        return rsl;
    }

    @Override
    public Item findById(int id) {
        var rsl = new Item();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            rsl = session.get(Item.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            close();
        }
        return rsl;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}