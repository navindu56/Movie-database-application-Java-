package com.example.mobilecw2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    public static final String TABLE_NAME = "movies" ;
    private static final String DATABASE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 1;
    public static final String COLUMN_MOVIE_KEY = "MOVIE_KEY";
    public static final String COLUMN_MOVIE_TITLE = "MOVIE_TITLE";
    public static final String COLUMN_MOVIE_YEAR = "MOVIE_YEAR";
    public static final String COLUMN_MOVIE_DIRECTOR = "MOVIE_DIRECTOR";
    public static final String COLUMN_MOVIE_ACTRESSES = "MOVIE_ACTRESSES";
    public static final String COLUMN_MOVIE_RATINGS = "MOVIE_RATINGS";
    public static final String COLUMN_MOVIE_DESCRIPTION = "MOVIE_DESCRIPTION";
    public static final String COLUMN_MOVIE_IS_FAVOURITE = "MOVIE_FAVOURITES";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_MOVIE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MOVIE_TITLE + " Text NOT NULL,"
                + COLUMN_MOVIE_YEAR + " INTEGER NOT NULL,"
                + COLUMN_MOVIE_DIRECTOR + " TEXT NOT NULL,"
                + COLUMN_MOVIE_ACTRESSES + " TEXT NOT NULL,"
                + COLUMN_MOVIE_RATINGS + " INTEGER NOT NULL,"
                + COLUMN_MOVIE_DESCRIPTION + " TEXT NOT NULL,"
                + COLUMN_MOVIE_IS_FAVOURITE + " INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    public void addMovie(Movies movieData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_MOVIE_KEY, movieData.getMovieId());
        contentValues.put(COLUMN_MOVIE_TITLE, movieData.getMovieTitle());
        contentValues.put(COLUMN_MOVIE_YEAR, movieData.getYear());
        contentValues.put(COLUMN_MOVIE_DIRECTOR, movieData.getDirector());
        contentValues.put(COLUMN_MOVIE_ACTRESSES, movieData.getCast());
        contentValues.put(COLUMN_MOVIE_RATINGS, movieData.getRating());
        contentValues.put(COLUMN_MOVIE_DESCRIPTION, movieData.getDescription());
        contentValues.put(COLUMN_MOVIE_IS_FAVOURITE, movieData.getIsFavourite());

        db.insertOrThrow(TABLE_NAME, null, contentValues);
        db.close();
    }

//    code to get single movie to edit movie fragment
    public Movies getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_MOVIE_KEY,
                        COLUMN_MOVIE_TITLE, COLUMN_MOVIE_YEAR,COLUMN_MOVIE_DIRECTOR,COLUMN_MOVIE_ACTRESSES,COLUMN_MOVIE_DESCRIPTION,COLUMN_MOVIE_RATINGS,COLUMN_MOVIE_IS_FAVOURITE }, COLUMN_MOVIE_KEY + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Movies movie = new Movies(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5),(Integer.parseInt(cursor.getString(6))),(Integer.parseInt(cursor.getString(7))));
        // return movie
        return movie;
    }

//    code to get all movies to display fragment
    public List<Movies> getAllMovies() {
        List<Movies> movieList = new ArrayList<Movies>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_MOVIE_TITLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Movies movies = new Movies();
                movies.setMovieId(Integer.parseInt(cursor.getString(0)));
                movies.setMovieTitle(cursor.getString(1));
                movies.setYear(Integer.parseInt(cursor.getString(2)));
                movies.setDirector(cursor.getString(3));
                movies.setCast(cursor.getString(4));
                movies.setRating(Integer.parseInt(cursor.getString(5)));
                movies.setDescription(cursor.getString(6));
                movies.setIsFavourite(Integer.parseInt(cursor.getString(7)));
                // Adding movie to list
                movieList.add(movies);
            } while (cursor.moveToNext());
        }

        // return movie list
        return movieList;
    }

//    code to get favourite movies to favouriteMovies fragment
    public List<Movies> getFavMovies() {
        List<Movies> movieList = new ArrayList<Movies>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + COLUMN_MOVIE_IS_FAVOURITE + " =1 ORDER BY " + COLUMN_MOVIE_TITLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Movies movies = new Movies();
                movies.setMovieId(Integer.parseInt(cursor.getString(0)));
                movies.setMovieTitle(cursor.getString(1));
                movies.setYear(Integer.parseInt(cursor.getString(2)));
                movies.setDirector(cursor.getString(3));
                movies.setCast(cursor.getString(4));
                movies.setRating(Integer.parseInt(cursor.getString(5)));
                movies.setDescription(cursor.getString(6));
                movies.setIsFavourite(Integer.parseInt(cursor.getString(7)));
                // Adding movie to list
                movieList.add(movies);
            } while (cursor.moveToNext());
        }

        // return movie list
        return movieList;
    }

//    code to update movies
    public int updateMovies(Movies movies) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVIE_TITLE, movies.getMovieTitle());
        values.put(COLUMN_MOVIE_YEAR, movies.getYear());
        values.put(COLUMN_MOVIE_DIRECTOR, movies.getDirector());
        values.put(COLUMN_MOVIE_ACTRESSES, movies.getCast());
        values.put(COLUMN_MOVIE_RATINGS, movies.getRating());
        values.put(COLUMN_MOVIE_DESCRIPTION, movies.getDescription());
        values.put(COLUMN_MOVIE_IS_FAVOURITE, movies.getIsFavourite());

        // updating row
        return db.update(TABLE_NAME, values, COLUMN_MOVIE_KEY + " = ?",
                new String[] { String.valueOf(movies.getMovieId()) });
    }

//    code to search movies
    public List<String> getSearchMovies(String searchString) {
        List<String> movieList = new ArrayList<String>();
        // Select All Query

        String selectQuery = "SELECT "  +COLUMN_MOVIE_TITLE+ " FROM " + TABLE_NAME + " WHERE " + COLUMN_MOVIE_TITLE + " like \"%" + searchString + "%\" UNION " + "SELECT "  +COLUMN_MOVIE_TITLE+ " FROM " + TABLE_NAME + " WHERE " + COLUMN_MOVIE_DIRECTOR + " like \"%" + searchString + "%\" UNION " + "SELECT "  +COLUMN_MOVIE_TITLE+ " FROM " + TABLE_NAME + " WHERE " + COLUMN_MOVIE_ACTRESSES + " like \"%" + searchString + "%\"";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String name;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding movie to list
                name = cursor.getString(0);
                movieList.add(name);
            } while (cursor.moveToNext());
        }

        // return movie list
        return movieList;
    }


}
