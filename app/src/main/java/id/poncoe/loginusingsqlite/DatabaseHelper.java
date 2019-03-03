package id.poncoe.loginusingsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
    // Disebut ketika tidak ada database di disk dan kelas pembantu perlu
    // untuk membuat yang baru.
    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        _db.execSQL(LoginDatabaseAdapter.DATABASE_CREATE);

    }
    // Disebut ketika ada versi database yang tidak cocok artinya versi
    // database pada disk perlu ditingkatkan ke versi saat ini.
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Catat pemutakhiran versi.
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");


        // Perbarui database yang ada agar sesuai dengan versi baru. Berganda
        // versi sebelumnya dapat ditangani dengan membandingkan _oldVersion dan _newVersion
        // nilai.
        // Kasus paling sederhana adalah dengan menjatuhkan tabel lama dan membuat yang baru.
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Buat yang baru.
        onCreate(_db);
    }


}
