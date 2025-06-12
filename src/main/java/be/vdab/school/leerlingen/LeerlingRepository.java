package be.vdab.school.leerlingen;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class LeerlingRepository {

    public List<Leerling> findAll() {
        try (var regels = Files.lines(Path.of("/data/leerlingen.csv"))) {
            return regels
                    .map(regel -> regel.split(",")) // regel splitsen in zijn onderdelen
                    .map(regelOnderdelen -> // Leerling object maken met die onderdelen
                            new Leerling(Long.parseLong(regelOnderdelen[0]), regelOnderdelen[1], regelOnderdelen[2]))
                    .toList();
        } catch (IOException | ArrayIndexOutOfBoundsException |
                 NumberFormatException ex) {
            throw new IllegalArgumentException("Leerlingenbestand bevat verkeerde data", ex);
        }
    }

}
