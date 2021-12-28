package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tambah extends AppCompatActivity {

    EditText edKode, edNama, edSatuan, edBeli, edJual, edStok, edMin;
    Button simpan;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        edKode = findViewById(R.id.edKode);
        edNama = findViewById(R.id.edNama);
        edSatuan = findViewById(R.id.edSatuan);
        edBeli = findViewById(R.id.edBeli);
        edJual = findViewById(R.id.edJual);
        edStok = findViewById(R.id.edStok);
        edMin = findViewById(R.id.edMin);
        simpan = findViewById(R.id.btn_simpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getKdbrg = edKode.getText().toString();
                String getNmbrg = edNama.getText().toString();
                String getSatuab = edSatuan.getText().toString();
                String getHrgbeli= edBeli.getText().toString();
                String getHrgJual = edJual.getText().toString();
                String getStok = edStok.getText().toString();
                String getStok_min = edMin.getText().toString();

                if (getKdbrg.isEmpty()){
                    edKode.setError("Kode Barang Masih kosong....");
                }else if (getNmbrg.isEmpty()){
                    edNama.setError("Nama Barang Masih Kosong....");
                }else {
                    db.child("Barang").push().setValue(new Barang(getKdbrg, getNmbrg, getSatuab, getHrgbeli, getHrgJual, getStok, getStok_min)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Tambah.this, "Data berhasil disimpan!!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Tambah.this, MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Tambah.this, "Data gagal disimpan!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}