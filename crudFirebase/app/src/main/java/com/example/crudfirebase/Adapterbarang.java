package com.example.crudfirebase;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Adapterbarang extends RecyclerView.Adapter<Adapterbarang.MyViewHolder> {
    private List<Barang> mList;
    private Activity activity;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    public Adapterbarang(List<Barang>mList,Activity activity){
        this.mList = mList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Adapterbarang.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent,false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterbarang.MyViewHolder holder, int position) {
        final Barang data = mList.get(position);
        holder.kode.setText(""+ data.getKdbrg());
        holder.nama.setText("Nama Barang : "+ data.getNmbrg());
        holder.satuan.setText("Satuan : "+ data.getSatuan());
        holder.beli.setText("Harga Beli : "+ data.getHrgbeli());
        holder.jual.setText("Harga Jual : "+ data.getHrgjual());
        holder.stok.setText("Stok : "+ data.getStok());
        holder.stokmin.setText("Stok Minimum : "+ data.getStok_min());

        holder.btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        db.child("Barang").child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity, "Data behasil dihapus",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Data gagal dihapus",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setMessage("Apakah ingin menghapus ?" + data.getNmbrg());
                builder.show();
            }
        });

        holder.card_hasil.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogForm dialog = new DialogForm(
                        data.getKdbrg(),
                        data.getNmbrg(),
                        data.getSatuan(),
                        data.getHrgbeli(),
                        data.getHrgjual(),
                        data.getStok(),
                        data.getStok_min(),
                        data.getKey(),
                        "Ubah"
                );
                dialog.show(manager,"form");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView kode, nama, satuan, beli, jual, stok, stokmin;
        ImageView btnhapus;
        CardView card_hasil;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            kode = itemView.findViewById(R.id.tv_kode);
            nama = itemView.findViewById(R.id.tv_nama);
            satuan = itemView.findViewById(R.id.tv_satuan);
            beli = itemView.findViewById(R.id.tv_hgbeli);
            jual = itemView.findViewById(R.id.tv_hgjual);
            stok = itemView.findViewById(R.id.tv_stok);
            stokmin = itemView.findViewById(R.id.tv_stokmin);
            btnhapus = itemView.findViewById(R.id.hapus);
            card_hasil = itemView.findViewById(R.id.card_hasil);
        }
    }
}
