/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myProject.dal;

import hr.myProject.model.Movie;
import hr.myProject.model.Person;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Nina
 */
public interface MovieRepository {
    
    int createMovie(Movie movie) throws Exception;
    void createMovies(List<Movie> movies) throws Exception;
    void updateMovie(int id, Movie data) throws Exception;
    void deleteMovie(int id) throws Exception;
    Optional<Movie> selectMovie(int id) throws Exception;
    List<Movie> selectMovies() throws Exception;
    void deleteMovies() throws Exception;
    
    int createPerson(Person person) throws Exception;
    void updatePerson(int id, Person data) throws Exception;
    void deletePerson(int id) throws Exception;
    Optional<Person> selectPerson(int id) throws Exception;
    List<Person> selectPeople() throws Exception;
    
    int createMovieActor(Person person, Movie movie) throws Exception;
    List<Person> selectMovieActors(int id) throws Exception;
    List<Person> selectAllActors() throws Exception;
    void deleteActorFromMovie(int movieId, int actorId) throws Exception;
}
