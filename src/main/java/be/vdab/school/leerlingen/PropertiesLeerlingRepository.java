package be.vdab.school.leerlingen;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
@Primary
public class PropertiesLeerlingRepository implements LeerlingRepository {

    private final String pad;

    public PropertiesLeerlingRepository(@Value("${leerlingenPropertiesPad}") String pad) {
        this.pad = pad;
    }

    @Override
    public List<Leerling> findAll() {
        try (var regels = Files.lines(Path.of(pad))) {
            return regels
                    .map(regel -> regel.split("[:,]")) // regel splitsen in zijn onderdelen
                    .map(regelOnderdelen -> // Leerling object maken met die onderdelen
                            new Leerling(Long.parseLong(regelOnderdelen[0]), regelOnderdelen[1], regelOnderdelen[2]))
                    .toList();
        } catch (IOException | ArrayIndexOutOfBoundsException |
                 NumberFormatException ex) {
            throw new IllegalArgumentException("Leerlingenbestand bevat verkeerde data", ex);
        }
    }

}
