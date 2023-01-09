package org.ejemplos;

import org.ejemplos.entities.Equipo;
import org.ejemplos.entities.Piloto;
import org.ejemplos.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;

public class App
{
    public static void main( String[] args )
    {
        Equipo e1 = new Equipo("Aston Martin", "Mercedes");

        Piloto p1 = new Piloto("Fernando Alonso", LocalDate.of(1981, 7, 29), e1);
        Piloto p2 = new Piloto("Lance Stroll", LocalDate.of(1998, 10, 29), e1);
        Piloto p3 = new Piloto("Felipe Drugovich", LocalDate.of(1000, 5, 23), e1);

        Session sesion = HibernateUtil.getSessionFactory().openSession();

        // Insertar (save --> persist)
        // La gestión del id le corresponde a SGBD
        System.out.println("\nAntes de insertar ------ ");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        Transaction tx = sesion.beginTransaction();
        sesion.persist(p1);
        sesion.persist(p2);
        sesion.persist(p3);
        tx.commit();

        // ¿Qué id tienen una vez ha sido insertado?
        System.out.println("\nTras insertar ------ ");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        // Leer (se pueden utilizar get o find)
        Piloto p3copia = sesion.get(Piloto.class,p3.getId());
        System.out.println("\nLeer id 3 ------ ");
        System.out.println(p3copia);

        // Actualizar (update --> merge)
        p3.setFechaNacimiento(LocalDate.of(2000, 5, 23));

        tx = sesion.beginTransaction();
        sesion.merge(p3);
        tx.commit();

        System.out.println("\nTras actualizar ------ ");
        System.out.println(p3);

        // Borrar (delete --> remove)
        tx = sesion.beginTransaction();
        sesion.remove(p3copia);
        tx.commit();

        // Ver todos (createQuery --> createSelectionQuery
        System.out.println("\nVer todos ------ ");
        sesion.createSelectionQuery("from Piloto ").getResultList().forEach(System.out::println);

        // Ver según parámetro
        System.out.println("\nVer según parámetro ------ ");
        sesion.createSelectionQuery("from Piloto where nombre =: nombre")
                .setParameter("nombre", "Lance Stroll")
                .getResultList().forEach(System.out::println);

        System.out.println("\nVer según parámetro (esto no imprimirá nada) ------ ");
        sesion.createSelectionQuery("from Piloto where nombre =: nombre")
                .setParameter("nombre", "Felipe Drugovich")
                .getResultList().forEach(System.out::println);

        sesion.close();
        HibernateUtil.getSessionFactory().close();
    }
}
