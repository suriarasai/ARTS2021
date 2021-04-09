package sg.edu.iss.movie;

import java.util.function.Supplier;

import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Flux;

@Configuration
public class MovieStream {

	/*
	 * @Bean public Supplier<Flux<Movie>> movie() { return () -> Flux.just( new
	 * Movie("The Matrix","Keanu Reves",1999,"science-fiction"), new
	 * Movie("Flight","BDenzel Washington",2012,"thriller") ); }
	 */
	/*
	 * 
	 * @Bean // Every second will send this movie. public Supplier<Movie> movie() {
	 * return () -> new Movie("The Matrix","Keanu Reves",1999,"science-fiction"); }
	 * 
	 */
    
	
	  @PollableBean 
	  public Supplier<Flux<Movie>> movie() { return () -> Flux.just(
	  new Movie("The Matrix","Keanu Reves",1999,"science-fiction"), new
	  Movie("The Martian","Matt Damon",2015,"science-fiction") ); }
	 
    
}
