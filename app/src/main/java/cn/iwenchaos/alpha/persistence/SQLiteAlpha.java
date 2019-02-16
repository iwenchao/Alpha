package cn.iwenchaos.alpha.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by chaos
 * on 2019/2/14. 16:11
 * 文件描述：
 */
public class SQLiteAlpha extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySQLiteHelper sqLiteHelper = new MySQLiteHelper(this, "userinfo", null, 1);
        SQLiteDatabase sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("username", "Omooo");
        values.put("pwd", "Test");
        sqLiteDatabase.insert("userinfo", null, values);
        sqLiteDatabase.endTransaction();
        sqLiteDatabase.close();

    }




    static class MySQLiteHelper extends SQLiteOpenHelper {


        private static final int VERSION_2 = 2;

        public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "create table userinfo(id integer primary key autoincrement,username varchar(64),pwd varchar(64))";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            switch (newVersion) {
                case VERSION_2:
                    db.execSQL("alter table userinfo rename to user");
                    db.execSQL("alter table userinfo add column sex varchar");
                    break;
                default:
                    break;
            }
        }

    }


}
