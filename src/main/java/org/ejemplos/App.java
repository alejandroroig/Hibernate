package org.ejemplos;

import org.ejemplos.entities.Equipo;
import org.ejemplos.entities.Piloto;
import org.ejemplos.repositories.PilotoRepositoryImpl;
import java.time.LocalDate;
import java.util.Optional;

public class App
{
    public static void main( String[] args )
    {
        Equipo e1 = new Equipo("Aston Martin", "Mercedes");

        Piloto p1 = new Piloto("Fernando Alonso", LocalDate.of(1981, 7, 29), e1);
        Piloto p2 = new Piloto("Lance Stroll", LocalDate.of(1998, 10, 29), e1);
        Piloto p3 = new Piloto("Felipe Drugovich", LocalDate.of(1000, 5, 23), e1);

        PilotoRepositoryImpl pilotos = new PilotoRepositoryImpl();

        System.out.println("\nInserción y lectura ------ ");
        pilotos.create(p1);
        pilotos.create(p2);
        pilotos.create(p3);
        pilotos.readAll().forEach(System.out::println);

        System.out.println("\nLeer por id ------ ");
        Optional<Piloto> p3copia = pilotos.read(p3.getId());
        if (p3copia.isPresent())
            System.out.println(p3copia);
        else
            System.out.println("El id del piloto no existe");

        System.out.println("\nLeer por id que no existe------ ");
        Optional<Piloto> pilotoNoExiste = pilotos.read(4);
        if (pilotoNoExiste.isPresent())
            System.out.println(pilotoNoExiste);
        else
            System.out.println("El id del piloto no existe");


        System.out.println("\nTras actualizar, lectura ------ ");
        p3.setFechaNacimiento(LocalDate.of(2000, 5, 23));
        pilotos.update(p3);
        System.out.println(p3);

        // Borrado del piloto p3
        pilotos.delete(p3);

        // Ver todos
        System.out.println("\nBorrado y lectura ------ ");
        pilotos.readAll().forEach(System.out::println);

        // Leer según parámetro
        System.out.println("\nLeer por nombre ------ ");
        Optional<Piloto> copiaFernando = pilotos.readByName("Fernando Alonso");
        if (copiaFernando.isPresent())
            System.out.println(copiaFernando);
        else
            System.out.println("El id del piloto no existe");

        // Leer según parámetro
        System.out.println("\nLeer por nombre que no existe------ ");
        pilotoNoExiste = pilotos.readByName("Paco El-Manco");
        if (pilotoNoExiste.isPresent())
            System.out.println(pilotoNoExiste);
        else
            System.out.println("El id del piloto no existe");

        pilotos.close();
    }
}
