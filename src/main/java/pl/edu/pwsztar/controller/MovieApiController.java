package pl.edu.pwsztar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.domain.dto.CreateMovieDto;
import pl.edu.pwsztar.domain.dto.MovieDto;
import pl.edu.pwsztar.service.MovieService;

import java.util.List;
//http://localhost:63342/tijo_movies/target/classes/frontend/movies.html

@Controller
@RequestMapping(value="/api")
public class MovieApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieApiController.class);

    private final MovieService movieService;

    @Autowired
    public MovieApiController(MovieService movieService) {
        this.movieService = movieService;
    }

    @CrossOrigin
    @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<MovieDto>> getMovies() {
        LOGGER.info("find all movies");

        List<MovieDto> moviesDto = movieService.findAll();
        return new ResponseEntity<>(moviesDto, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createMovie(@RequestBody CreateMovieDto createMovieDto) {
        LOGGER.info("create movie: {}", createMovieDto);

        movieService.save(createMovieDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value="/movies/{id}",method = { RequestMethod.GET, RequestMethod.DELETE})
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        LOGGER.info("delete movie: {}", id);

        movieService.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
