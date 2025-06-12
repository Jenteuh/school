package be.vdab.school.lessen;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class LesRepository {
    public List<Les> findAll() {
        try (var regels = Files.lines(Path.of("/data/lessen.csv"))) {
            return regels
                    .map(regel -> regel.split(",")) // regel splitsen in zijn onderdelen
                    .map(regelOnderdelen -> // Taal object maken met die onderdelen
                            new Les(regelOnderdelen[0], // code
                                    regelOnderdelen[1])) // naam
                    .toList();
        } catch (IOException | ArrayIndexOutOfBoundsException |
                 NumberFormatException ex) {
            throw new IllegalArgumentException("Lessenbestand bevat verkeerde data", ex);
        }
    }
}
