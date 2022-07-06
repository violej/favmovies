package com.example.myfavoritemovie.model;

        import android.content.Context;
        import android.os.AsyncTask;

        import androidx.annotation.NonNull;
        import androidx.room.Database;
        import androidx.room.Room;
        import androidx.room.RoomDatabase;
        import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Genre.class, Movie.class},version = 1)
public abstract class MoviesDatabase extends RoomDatabase {

    private static MoviesDatabase instance;

    public abstract GenreDao getGenreDao();

    public abstract MovieDao getMovieDao();

    public static synchronized MoviesDatabase getInstance(Context context){

        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MoviesDatabase.class, "moviesDB")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();


        }
        return instance;
    }
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();
        }
    };
    private static class InitialDataAsyncTask extends AsyncTask<Void,Void,Void>{

        private GenreDao genreDao;
        private MovieDao movieDao;

        public InitialDataAsyncTask(MoviesDatabase database){
            genreDao = database.getGenreDao();
            movieDao = database.getMovieDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Genre comedyGenre = new Genre();
            comedyGenre.setGenreName("Comedy");

            Genre romanceGenre = new Genre();
            romanceGenre.setGenreName("Romance");

            Genre dramaGenre = new Genre();
            dramaGenre.setGenreName("Drama");

            genreDao.insert(comedyGenre);
            genreDao.insert(romanceGenre);
            genreDao.insert(dramaGenre);

            Movie movie1 = new Movie();
            movie1.setMovieName("Bad boys for life");
            movie1.setMovieDescription("Miami detectives Mike Lowrey and Marcus Burnett must face off against a mother-and-son pair of drug lords who wreak vengeful havoc on their city. Miami detectives Mike Lowrey and Marcus Burnett must face off against a mother-and-son pair of drug lords who wreak vengeful havoc on their city.");
            movie1.setGenreId(1);

            Movie movie2 = new Movie();
            movie2.setMovieName("Parasite");
            movie2.setMovieDescription("Parasite follows the Kim family struggling to make ends meet until son Ki-woo comes across an unexpected opportunity. With no experience teaching English, he decides to accept a job tutoring Da-hye, a daughter from the wealthy Park family.");
            movie2.setGenreId(1);

            Movie movie3 = new Movie();
            movie3.setMovieName("Dashcam");
            movie3.setMovieDescription("One of the best films of 2020, Host was a low-budget found-footage horror movie which put a demonic twist on all of our pandemic-related fears. The follow-up from the same director, Rob Savage, and his co-writers, Gemma Hurley and Jed Shepherd, sinks its teeth into the same vein. Shot on an iPhone, the film purports to be drawn from a livestream by a Los Angeles-based musician");
            movie3.setGenreId(1);

            Movie movie4 = new Movie();
            movie4.setMovieName("Elvis");
            movie4.setMovieDescription("t's been almost a decade since the release of Baz Luhrmann's The Great Gatsby, but the Australian writer-director of Strictly Ballroom, Romeo + Juliet, and Moulin Rouge! is back at last with a typically glitzy and frenetic biopic of The King of Rock 'n' Roll.");
            movie4.setGenreId(2);

            Movie movie5 = new Movie();
            movie5.setMovieName("Brian And Charles");
            movie5.setMovieDescription("Not a typical science-fiction movie, Brian and Charles is a low-budget, low-key British comedy starring and co-written by David Earl, a regular in Ricky Gervais's sitcoms. Earl plays Brian, a grumpy inventor who lives in a cottage in a remote corner of rural Wales.");
            movie5.setGenreId(2);

            Movie movie6 = new Movie();
            movie6.setMovieName("The Black Phone");
            movie6.setMovieDescription("Adapted from a short story by Joe Hill, The Black Phone is set in suburban Colorado in 1978. Several local children have been abducted by someone the newspapers are calling ");
            movie6.setGenreId(2);

            Movie movie7 = new Movie();
            movie7.setMovieName("Titanic");
            movie7.setMovieDescription("Need we say more? If you haven't watched Titanic yet (please don't admit this out loud), do yourself a favor and stream it on Hulu immediately.");
            movie7.setGenreId(3);

            Movie movie8 = new Movie();
            movie8.setMovieName("Casablanca");
            movie8.setMovieDescription("Set in the early years of World War II in Casablanca, Rick Blaine's (Humphrey Bogart) nightclub is an oasis for refugees despite the warnings he gets from local authorities. But things get rocky when an ex-lover and her boyfriend show up, bringing with them a challenge that Rick has to face");
            movie8.setGenreId(3);

            Movie movie9 = new Movie();
            movie9.setMovieName("Rocky");
            movie9.setMovieDescription("It’s the classic underdog story that made Sylvester Stallone a household name. The movie follows boxer Rocky Balboa on the road to fight heavyweight champion Apollo Creed in a match deemed “a somebody vs. nobody.” The film, written by Stallone, would go on to win Best Picture at the Oscars in 1977.");
            movie9.setGenreId(3);

            movieDao.insert(movie1);
            movieDao.insert(movie2);
            movieDao.insert(movie3);
            movieDao.insert(movie4);
            movieDao.insert(movie5);
            movieDao.insert(movie6);
            movieDao.insert(movie7);
            movieDao.insert(movie8);
            movieDao.insert(movie9);






            return null;
        }
    }
}
