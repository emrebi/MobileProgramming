package com.example.sqliteemployeeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etFname, etLname, etEmail;
    SQLiteDatabase db;
    ListView lv;
    int selectedId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        etEmail = findViewById(R.id.etEmail);
        lv = findViewById(R.id.listView);

        db = SQLiteDatabase.openOrCreateDatabase(this.getDatabasePath("MyDB.db"), null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Calisanlar(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "LastName VARCHAR NOT NULL, " +
                "FirstName CHAR(50) NOT NULL, " +
                "Email TEXT UNIQUE)");

        Listele();

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Calisanlar selectedCalisan = (Calisanlar) lv.getItemAtPosition(position);
            selectedId = selectedCalisan.getId();
            etFname.setText(selectedCalisan.getfName());
            etLname.setText(selectedCalisan.getlName());
            etEmail.setText(selectedCalisan.getEmail());
        });
    }

    public void Save(View v) {
        try {
            db.execSQL("INSERT INTO Calisanlar (LastName, FirstName, Email) VALUES ('" +
                    etLname.getText().toString() + "', '" +
                    etFname.getText().toString() + "', '" +
                    etEmail.getText().toString() + "')");
            Toast.makeText(this, "KAYIT BAŞARILI", Toast.LENGTH_LONG).show();
            Listele();
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Update(View v) {
        if (selectedId != -1) {
            try {
                db.execSQL("UPDATE Calisanlar SET LastName = '" +
                        etLname.getText().toString() + "', FirstName = '" +
                        etFname.getText().toString() + "', Email = '" +
                        etEmail.getText().toString() + "' WHERE id = " + selectedId);
                Toast.makeText(this, "GÜNCELLEME BAŞARILI", Toast.LENGTH_LONG).show();
                Listele();
            } catch (Exception ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Lütfen güncellemek için bir kayıt seçin", Toast.LENGTH_LONG).show();
        }
    }

    public void Delete(View v) {
        if (selectedId != -1) {
            try {
                db.execSQL("DELETE FROM Calisanlar WHERE id = " + selectedId);
                Toast.makeText(this, "SİLME BAŞARILI", Toast.LENGTH_LONG).show();
                Listele();
            } catch (Exception ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Lütfen silmek için bir kayıt seçin", Toast.LENGTH_LONG).show();
        }
    }

    public void Listele() {
        ArrayList<Calisanlar> calisanlarListesi = new ArrayList<>();
        if (db.isOpen()) {
            Cursor c = db.rawQuery("SELECT * FROM Calisanlar", null);
            if (c != null && c.moveToFirst()) {
                do {
                    Calisanlar cl = new Calisanlar();
                    cl.setId(c.getInt(0));
                    cl.setfName(c.getString(2));
                    cl.setlName(c.getString(1));
                    cl.setEmail(c.getString(3));
                    calisanlarListesi.add(cl);
                } while (c.moveToNext());

                ArrayAdapter<Calisanlar> adap = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, calisanlarListesi);
                lv.setAdapter(adap);
            }
        }
    }
}
