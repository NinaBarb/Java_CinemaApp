/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myProject.dal.sql;

import hr.myProject.model.Movie;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import hr.myProject.dal.MovieRepository;
import hr.myProject.model.Person;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class SqlMovieRepository implements MovieRepository {
    
    private static final String ID_MOVIE="IDMovie";
    private static final String HR_TITLE="HRTitle";
    private static final String PUBLISHED_DATE="PublishedDate";
    private static final String DESCRIPTION="Description";
    private static final String ORIG_TITLE="ORIGTitle";
    private static final String PERSON_ID="PersonID";
    private static final String DURATION="Duration";
    private static final String GENRE="Genre";
    private static final String PICTURE_PATH="PicturePath";
    
    private static final String CREATE_MOVIE="{CALL createMovie(?,?,?,?,?,?,?,?,?)}";
    private static final String UPDATE_MOVIE="{CALL updateMovie(?,?,?,?,?,?,?,?,?)}";
    private static final String DELETE_MOVIE="{CALL deleteMovie(?)}";
    private static final String SELECT_MOVIE="{CALL selectMovie(?)}";
    private static final String SELECT_MOVIES="{CALL selectMovies}";
    private static final String DELETE_MOVIES = "{ CALL deleteAllData }";
    
  //////////////////////////////////////////////////////////////////////////////////////////////////  
  //////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String ID_PERSON = "IDPerson";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    
    private static final String CREATE_PERSON = "{ CALL createPerson (?,?,?) }";
    private static final String UPDATE_PERSON = "{ CALL updatePerson (?,?,?) }";
    private static final String DELETE_PERSON = "{ CALL deletePerson (?) }";
    private static final String SELECT_PERSON = "{ CALL selectPerson (?) }";
    private static final String SELECT_PERSONS = "{ CALL selectPersons }";

    private static final String CREATE_MOVIE_ACTOR = "{ CALL createMovieActor (?,?,?) }";
    private static final String SELECT_MOVIE_ACTORS = "{ CALL selectMovieActors (?) }";
    private static final String SELECT_ALL_ACTORS = "{ CALL selectAllActors () }";
    private static final String DELETE_ACTOR_FROM_MOVIE = "{ CALL deleteMovieActors (?,?)}";
    
  //////////////////////////////////////////////////////////////////////////////////////////////////  
  //////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int createMovie(Movie movie) throws Exception {
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt=con.prepareCall(CREATE_MOVIE)){
            stmt.setString(1, movie.getHrTitle());
            stmt.setString(2, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(3, movie.getDescription());
            stmt.setString(4, movie.getEnTitle());
            stmt.setInt(5, movie.getDirector().getId());
            stmt.setInt(6, movie.getDuration());
            stmt.setString(7, movie.getGenres());
            stmt.setString(8, movie.getPicturePath());
            stmt.registerOutParameter(9,Types.INTEGER);
            
            stmt.executeUpdate();
            
            return stmt.getInt(9);
        }
    }

    @Override
    public void createMovies(List<Movie> movies) throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            for (Movie movie : movies) {
                stmt.setString(1, movie.getHrTitle());
                stmt.setString(2, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
                stmt.setString(3, movie.getDescription());
                stmt.setString(4, movie.getEnTitle());
                stmt.setInt(5, movie.getDirector().getId());
                stmt.setInt(6, movie.getDuration());
                stmt.setString(7, movie.getGenres());
                stmt.setString(8, movie.getPicturePath());
                
                stmt.registerOutParameter(9, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updateMovie(int id, Movie data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {

            
            stmt.setString(1, data.getHrTitle());
            stmt.setString(2, data.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(3, data.getDescription());
            stmt.setString(4, data.getEnTitle());
            stmt.setInt(5, data.getDirector().getId());
            stmt.setInt(6, data.getDuration());
            stmt.setString(7, data.getGenres());
            stmt.setString(8, data.getPicturePath());
            stmt.setInt(9, data.getId());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(HR_TITLE),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                            rs.getString(DESCRIPTION),
                            rs.getString(ORIG_TITLE),
                            new Person(rs.getInt(PERSON_ID),
                                    rs.getString(FIRST_NAME),
                                    rs.getString(LAST_NAME)),
                            selectMovieActors(rs.getInt(ID_MOVIE)),
                            rs.getInt(DURATION),
                            rs.getString(GENRE),
                            rs.getString(PICTURE_PATH)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(HR_TITLE),
                        LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                        rs.getString(DESCRIPTION),
                        rs.getString(ORIG_TITLE),
                        new Person(rs.getInt(PERSON_ID),
                                rs.getString(FIRST_NAME),
                                rs.getString(LAST_NAME)),  
                        null,
                        rs.getInt(DURATION),
                        rs.getString(GENRE),
                        rs.getString(PICTURE_PATH));
                movie.setActors(selectMovieActors(movie.getId()));

                movies.add(movie);
            }
        }
        return movies;
    }
    
    @Override
    public void deleteMovies() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIES)) {
            stmt.executeUpdate();
        }
    }
    
   //////////////////////////////////////////////////////////////////////////////////////////////////  
  //////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int createPerson(Person person) throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_PERSON)) {

            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void updatePerson(int id, Person data) throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_PERSON)) {

            stmt.setString(1, data.getFirstName());
            stmt.setString(2, data.getLastName());
            stmt.setInt(3, id);

            stmt.executeUpdate();
        }
    }
    
    @Override
    public void deletePerson(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_PERSON)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Person> selectPerson(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PERSON)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Person(
                            rs.getInt(id)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Person> selectPeople() throws Exception {
        List<Person> persons = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PERSONS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                persons.add(new Person(
                        rs.getInt(ID_PERSON),
                        rs.getString(FIRST_NAME),
                        rs.getString(LAST_NAME)));
            }
        }
        return persons;
    }

   //////////////////////////////////////////////////////////////////////////////////////////////////  
  //////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int createMovieActor(Person person, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_ACTOR)) {

            stmt.setInt(1, movie.getId());
            stmt.setInt(2, person.getId());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public List<Person> selectMovieActors(int id) throws Exception {
        List<Person> persons = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE_ACTORS)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    persons.add(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)));
                }
            }

            return persons;
        }
    }

    @Override
    public List<Person> selectAllActors() throws Exception {
        List<Person> persons = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ALL_ACTORS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Person person = new Person(
                        rs.getInt(ID_PERSON),
                        rs.getString(FIRST_NAME),
                        rs.getString(LAST_NAME));

                persons.add(person);
            }
        }
        return persons;
    }

    @Override
    public void deleteActorFromMovie(int movieId, int actorId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTOR_FROM_MOVIE)) {
            
            stmt.setInt(1, movieId);
            stmt.setInt(2, actorId);

            stmt.executeUpdate();
        }
    }
}
