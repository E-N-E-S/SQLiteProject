package com.enesb.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null);

            // String = VARCHAR
            // Integer = INT
            // SQL Kodu yaziyoruz. Bir tablo aciyoruz.
            // String alan name degeri ve age alan int degeri verilmeli diye belirttik
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR, age INT)");


            // INSERT INTO: Databaseye bir deger kaydederken kullanilir
            // VALUES: Degerleri belirtir
            // String degeri tek tirnak icine aliyoruz
            /*
            database.execSQL("INSERT INTO musicians (name, age) VALUES ('James',50)");
            database.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars', 36)");
            database.execSQL("INSERT INTO musicians (name, age) VALUES ('Kirk', 39)");
            */

            // DATABASE UPDATE
            database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'");
            database.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3");

            // DATABASE DELETE
            database.execSQL("DELETE FROM musicians WHERE id = 2");


            // Imlec, tek tek hücrelerin icinde gezip bize bildiriyor
            // Query veritabanindan sorgulama yapmak
            // * = bütün degerleri al
            // selectionArgs = Filtreleme islemi
            // WHERE ile filtreleme yapiyoruz.
            // Cursor curcor = database.rawQuery("SELECT * FROM musicians WHERE name = 'James'", null);
            Cursor curcor = database.rawQuery("SELECT * FROM musicians", null);

            /*
             LIKE = gibi
             %s = sonu s ile biteni getir
             K% = basi K ile baslayanlari getir
             */
            // Cursor curcor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'", null);


            int nameIx = curcor.getColumnIndex("name"); // namenin kacinci indeksde oldugunu verecek
            int ageIx = curcor.getColumnIndex("age");
            int idIx = curcor.getColumnIndex("id");


            while (curcor.moveToNext()) { // curcor ilerleyebildigi kadar gitsin ve while icini yapsin
                System.out.println("Name: " + curcor.getString(nameIx));
                System.out.println("Age: " + curcor.getInt(ageIx));
                System.out.println("Id: " + curcor.getInt(idIx));
            }
            curcor.close(); // isimiz bitince bunu kapatiyoruz


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}