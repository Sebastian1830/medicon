package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite_OpenHelper extends SQLiteOpenHelper {
    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table user(" +
                "_ID integer primary key autoincrement," +
                "fullname text," +
                "email text," +
                "password text);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AbrirDB(){
        this.getWritableDatabase();
    }

    public void cerrarDB(){
        this.close();
    }

    public void insertRegUsu(String nom, String email, String pass){
        ContentValues Valores = new ContentValues();
        Valores.put("fullname",nom);
        Valores.put("email",email);
        Valores.put("password",pass);
        this.getWritableDatabase().insert("user",null,Valores);
    }

    public Cursor login(String email, String pass) throws SQLException {
        Cursor mcursor = null;
        mcursor=this.getReadableDatabase().query("user",new String[]{"_ID","fullname","email","password"},
                "email like '"+email+"' and password like '"+pass+"'",
                null,null,null,null);
        return mcursor;
    }

}
