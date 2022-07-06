package com.example.myfavoritemovie.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class AppRepository {

    private GenreDao genreDao;
    private MovieDao movieDao;

    private LiveData<List<Genre>> genres;
    private LiveData<List<Movie>> movies;

    public AppRepository(Application application){

        MoviesDatabase database = MoviesDatabase.getInstance(application);
        genreDao = database.getGenreDao();
        movieDao = database.getMovieDao();
    }

    public LiveData<List<Genre>> getGenres(){
        return genreDao.getAllGenres();
    }
    public LiveData<List<Movie>> getGenreMovies(int genreId){
        return movieDao.getGenreMovies(genreId);
    }
//создание жанра асинхронно
    public void insertGenre(Genre genre){

        new InsertGenreAsyncTask(genreDao).execute(genre);
    }

    public void insertMovie(Movie movie){
        new InsertMovieAsynkTask(movieDao).execute(movie);
    }

    private static class InsertGenreAsyncTask extends AsyncTask<Genre,Void,Void>{

        private GenreDao genreDao;

        public InsertGenreAsyncTask(GenreDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Genre... genres) {

            genreDao.insert(genres[0]);
            return null;
        }
    }

    private static class InsertMovieAsynkTask extends AsyncTask<Movie,Void,Void>{

        private MovieDao movieDao;

        public InsertMovieAsynkTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {

            movieDao.insert(movies[0]);
            return null;
        }
    }
//создание жанра асинхронно


//обновление жанра асинхронно
    public void updateGenre(Genre genre){

        new UpdateGenreAsyncTask(genreDao).execute(genre);
    }

    public void updateMovie(Movie movie){
        new UpdateMovieAsynkTask(movieDao).execute(movie);
    }

    private static class UpdateGenreAsyncTask extends AsyncTask<Genre,Void,Void>{

        private GenreDao genreDao;

        public UpdateGenreAsyncTask(GenreDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Genre... genres) {

            genreDao.update(genres[0]);
            return null;
        }
    }

    private static class UpdateMovieAsynkTask extends AsyncTask<Movie,Void,Void>{

        private MovieDao movieDao;

        public UpdateMovieAsynkTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {

            movieDao.update(movies[0]);
            return null;
        }
    }
//обновление жанра асинхронно

//удаление жанра асинхронно

    public void deleteGenre(Genre genre){

        new DeleteGenreAsyncTask(genreDao).execute(genre);
    }

    public void deleteMovie(Movie movie){
        new DeleteMovieAsynkTask(movieDao).execute(movie);
    }

    private static class DeleteGenreAsyncTask extends AsyncTask<Genre,Void,Void>{

        private GenreDao genreDao;

        public DeleteGenreAsyncTask(GenreDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Genre... genres) {

            genreDao.delete(genres[0]);
            return null;
        }
    }

    private static class DeleteMovieAsynkTask extends AsyncTask<Movie,Void,Void>{

        private MovieDao movieDao;

        public DeleteMovieAsynkTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {

            movieDao.delete(movies[0]);
            return null;
        }
    }
//удаление жанра асинхронно


}
