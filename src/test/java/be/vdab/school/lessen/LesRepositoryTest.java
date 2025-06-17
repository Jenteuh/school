package be.vdab.school.lessen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@PropertySource("application.properties")
@Import(LesRepository.class)

class LesRepositoryTest {

    private final LesRepository lesRepository;
    private final String pad;

    LesRepositoryTest(LesRepository lesRepository, @Value("${lessenCsvPad}") String pad) {
        this.lesRepository = lesRepository;
        this.pad = pad;
    }

    @Test
    void erZijnEvenveelLessenAlsHetAantalRegelsInHetBestand() throws IOException {
        try (var regels = Files.lines(Path.of(pad))) {
            assertThat(lesRepository.findAll().size()).isEqualTo(regels.count());
        }
    }

    @Test
    void deEersteLesBevatDeDataUitDeEersteRegel() throws IOException {
        try (var regels = Files.lines(Path.of(pad))) {
            regels.findFirst().ifPresent(eersteRegel -> {
                var eersteLes = lesRepository.findAll().get(0);
                assertThat(eersteLes.getCode() + "," + eersteLes.getNaam()) // land data concateneren tot één string
                        .isEqualTo(eersteRegel); // deze string moet gelijk zijn aan de 1° regel
            });
        }
    }
}