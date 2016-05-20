package com.testing1.mysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by USer on 20/5/2016.
 */
public class BooksDBHandler extends SQLiteOpenHelper
{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BOOKSdb.db";
    public static final String DATABASE_TABLE_BOOKS = "books";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BOOKNAME = "book_name";
    public static final String COLUMN_AUTHORNAME = "book_author";
    public static final String CREATE_BOOKS_TABLE = "CREATE TABLE " + DATABASE_TABLE_BOOKS +
            "(" + COLUMN_ID + "INTEGER PRIMARY KEY," + COLUMN_BOOKNAME + "TEXT, " + COLUMN_AUTHORNAME + "TEXT" + ")";

    private static final String TAG = "BooksDBHandlerCLASS";

    public BooksDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_BOOKS_TABLE);
        Log.e(TAG, "onCreate Called");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE_BOOKS);
        onCreate(db);
        Log.e(TAG, "onUpgrade Called");
    }

    public void addBook(Book book)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BOOKNAME, book.getBookName());
        contentValues.put(COLUMN_AUTHORNAME, book.getAuthorName());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DATABASE_TABLE_BOOKS, null, contentValues);

        Log.e("addBook", "Book Added");
        db.close();
    }

    public Book findBookByName(String bookName)
    {
        String queryFindBook = "Select * From" + DATABASE_TABLE_BOOKS + "WHERE" + COLUMN_BOOKNAME + "= \"" + bookName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryFindBook, null);
        Book book = new Book();
        if (cursor.moveToFirst())
        {
            cursor.moveToFirst();
            book.setBookID(Integer.parseInt(cursor.getString(0)));
            book.setBookName(cursor.getString(1));
            book.setAuthorName(cursor.getString(2));
            cursor.close();
        }
        else
        {
            book = null;
        }
        db.close();
        return book;
    }

    public Book findBookByID(int bookID)
    {
        String queryFindBook = "Select * From" + DATABASE_TABLE_BOOKS + "WHERE" + COLUMN_ID + "= \"" + bookID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryFindBook, null);
        Book book = new Book();
        if (cursor.moveToFirst())
        {
            cursor.moveToFirst();
            book.setBookID(Integer.parseInt(cursor.getString(0)));
            book.setBookName(cursor.getString(1));
            book.setAuthorName(cursor.getString(2));
            cursor.close();
        }
        else
        {
            book = null;
        }
        db.close();
        return book;
    }

    public Book findBook(String bookName)
    {
        String queryFindBook = "Select * From" + DATABASE_TABLE_BOOKS + "WHERE" + COLUMN_BOOKNAME + "= \"" + bookName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryFindBook, null);
        Book book = new Book();
        if (cursor.moveToFirst())
        {
            cursor.moveToFirst();
            book.setBookID(Integer.parseInt(cursor.getString(0)));
            book.setBookName(cursor.getString(1));
            book.setAuthorName(cursor.getString(2));
            cursor.close();
        }
        else
        {
            book = null;
        }
        db.close();
        return book;
    }

    public List<Book> getAllBooks()
    {
        List<Book> books = new LinkedList<Book>();
        String queryAllBooks = "Select * From" + DATABASE_TABLE_BOOKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryAllBooks, null);
        Book book = null;
        if (cursor.moveToFirst())
        {
            do
                {
                    book = new Book();
                    book.setBookID(Integer.parseInt(cursor.getString(0)));
                    book.setBookName(cursor.getString(1));
                    book.setAuthorName(cursor.getString(2));
                    books.add(book);
                } while (cursor.moveToNext());
        }
        //cursor.close();
        return books;
    }

    public boolean deleteBook(String bookName) {
        boolean result = false;
        String queryDeleteBook = "Select * From" + DATABASE_TABLE_BOOKS + "WHERE" + COLUMN_BOOKNAME + "=\"" + bookName + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryDeleteBook, null);
        Book book = new Book();
        if (cursor.moveToFirst())
        {
            book.setBookID(Integer.parseInt(cursor.getString(0)));
            db.delete(DATABASE_TABLE_BOOKS, COLUMN_ID + " = ?", new String[]{String.valueOf(book.getBookID())});
            cursor.close();
            result = true;
        }
        return  result;
    }

}
