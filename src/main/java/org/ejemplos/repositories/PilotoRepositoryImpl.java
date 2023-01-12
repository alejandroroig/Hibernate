package org.ejemplos.repositories;

import org.ejemplos.entities.Piloto;
import org.ejemplos.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PilotoRepositoryImpl implements Repository<Piloto> {
    private SessionFactory sf = HibernateUtil.getSessionFactory();
    private Session s = sf.openSession();

    @Override
    public Piloto create(Piloto piloto) {
        s.getTransaction().begin();
        s.persist(piloto);
        s.getTransaction().commit();
        return piloto;
    }

    @Override
    public Optional<Piloto> read(int id) {
        s.getTransaction().begin();
        Piloto piloto = s.get(Piloto.class, id);
        s.getTransaction().commit();
        return Optional.ofNullable(piloto);
    }

    @Override
    public List<Piloto> readAll() {
        s.getTransaction().begin();
        List<Piloto> pilotos = s.createSelectionQuery("from Piloto ", Piloto.class).list();
        s.getTransaction().commit();
        return pilotos;
    }

    @Override
    public Piloto update(Piloto piloto) {
        s.getTransaction().begin();
        s.merge(piloto);
        s.getTransaction().commit();
        return piloto;
    }

    @Override
    public void delete(Piloto piloto) {
        s.getTransaction().begin();
        s.remove(piloto);
        s.getTransaction().commit();
    }

    // Añado método respecto a la interfaz Repository
    public Optional<Piloto> readByName(String name) {
        s.getTransaction().begin();
        Optional<Piloto> piloto = s.createSelectionQuery("from Piloto where nombre =: nombre", Piloto.class)
                .setParameter("nombre", name).uniqueResultOptional();
        s.getTransaction().commit();
        return piloto;
    }

    // Añado método para cerrar sessionFactory
    public void close() {
        s.close();
        sf.close();
    }
}
