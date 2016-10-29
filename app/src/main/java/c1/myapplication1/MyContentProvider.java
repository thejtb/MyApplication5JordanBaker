package c1.myapplication1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public MyContentProvider() {
    }

    public final static String DBNAME = "UserDatabase";



    public final static String TABLE_NAMESTABLE = "usertable";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_EID = "employeeid";
    public final static String COLUMN_GENDER = "gender";
    public final static String COLUMN_EMAIL = "email";
    public final static String COLUMN_PASS = "passcode";
    public final static String COLUMN_DEPARTMENT = "department";
    public final static String COLUMN_ACCESS = "access";
    private MainDatabaseHelper mOpenHelper;

    private static final String SQL_CREATE_MAIN =
            "CREATE TABLE " + TABLE_NAMESTABLE +
                    "( _ID INTEGER PRIMARY KEY, " +
                    COLUMN_EID + " TEXT, " +
                    COLUMN_NAME+ " TEXT, "+
                    COLUMN_GENDER + " TEXT," +
                    COLUMN_EMAIL + " TEXT,"+
                    COLUMN_PASS + " TEXT," +
                    COLUMN_DEPARTMENT + " TEXT,"+
                    COLUMN_ACCESS + " TEXT" +
                    " )";

    public static final String AUTHORITY = "c1.myapplication1";
    public static final Uri CONTENT_URI = Uri.parse(
            "content://c1.myapplication1.contentprovider/" + TABLE_NAMESTABLE);
    @Override
    public boolean onCreate(){

        mOpenHelper = new MainDatabaseHelper(getContext());

        return true;

    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_NAMESTABLE, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {



        long id = mOpenHelper.getWritableDatabase().insert(TABLE_NAMESTABLE, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);

    }



    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_NAMESTABLE, projection, selection, selectionArgs,
               null, null, sortOrder);


    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean exists(String eD)
    {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select 1 from usertable where eID=%s"
                ,new String[] {eD});
        boolean exist = (cursor.getCount() >0);
        cursor.close();
        return  exist;


    }


    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }






    }


}
