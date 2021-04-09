package sg.edu.iss.movie;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Log4j2
@Configuration
public class MovieStream {

    @Bean
    public Function<Movie, Movie> uppercase() {
        return movie -> {
            movie.setTitle(movie.getTitle().toUpperCase());
            log.info("Processing: {}", movie);
            return movie;
        };
    }

}
