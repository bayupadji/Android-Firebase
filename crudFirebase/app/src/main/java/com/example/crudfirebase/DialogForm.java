package com.example.crudfirebase;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {
    String Kdbrg, Nmbrg, Satuan, Hrgbeli, Hrgjual, Stok, Stok_min, key, pilih;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    public DialogForm(String kdbrg, String nmbrg, String satuan, String hrgbeli, String hrgjual, String stok, String stok_min, String key, String pilih) {
        Kdbrg = kdbrg;
        Nmbrg = nmbrg;
        Satuan = satuan;
        Hrgbeli = hrgbeli;
        Hrgjual = hrgjual;
        Stok = stok;
        Stok_min = stok_min;
        this.key = key;
        this.pilih = pilih;
    }

    TextView tkode, tnama, tsatuan, tbeli, tjual, tstok, tstokmin;
    Button btn_simpan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_tambah, container, false);

        tkode = v.findViewById(R.id.edKode);
        tnama = v.findViewById(R.id.edNama);
        tsatuan = v.findViewById(R.id.edSatuan);
        tbeli = v.findViewById(R.id.edBeli);
        tjual = v.findViewById(R.id.edJual);
        tstok = v.findViewById(R.id.edStok);
        tstokmin = v.findViewById(R.id.edMin);
        btn_simpan = v.findViewById(R.id.btn_simpan);

        tkode.setText(Kdbrg);
        tnama.setText(Nmbrg);
        tsatuan.setText(Satuan);
        tbeli.setText(Hrgbeli);
        tjual.setText(Hrgjual);
        tstok.setText(Stok);
        tstokmin.setText(Stok_min);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kode = tkode.getText().toString();
                String nama = tnama.getText().toString();
                String satuan = tsatuan.getText().toString();
                String beli = tbeli.getText().toString();
                String jual = tjual.getText().toString();
                String stok = tstok.getText().toString();
                String stokmin = tstokmin.getText().toString();

                if (pilih.equals("Ubah")){
                    db.child("Barang").child(key).setValue(new Barang(kode, nama, satuan, beli, jual, stok, stokmin)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(v.getContext(),"Data Berhasil diubah",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(v.getContext(),"Data Gagal diubah",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
