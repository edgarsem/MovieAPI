package lt.viko.eif.groupproject.movieapi.controller;

import lt.viko.eif.groupproject.movieapi.model.Movie;
import lt.viko.eif.groupproject.movieapi.repository.MovieRepo;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class MovieController {

    @GetMapping("/movies")
    String mainMethod() throws IOException {
        return "WebService for movies.";
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) throws IOException {
        Movie movie = MovieRepo.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movies/search/{title}")
    public ResponseEntity<Map<String, String>> searchMovies(@PathVariable String title) throws IOException {
        Map<String, String> result = MovieRepo.searchMovieByTitle(title);
        if (result != null && !result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
