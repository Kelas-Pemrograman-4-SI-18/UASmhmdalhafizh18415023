package com.mhmdalhafizh.aplikasipenjualanmakanan.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.mhmdalhafizh.aplikasipenjualanmakanan.R;
import com.mhmdalhafizh.aplikasipenjualanmakanan.server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailMakanan extends AppCompatActivity {

    EditText edtkodemakanan, edtnamamakanan, edtjumlah, edthargamakanan;
    ImageView imgGambarMakanan;

    String strkodemakanan, strnamamakanan, strjumlah, strhargamakanan, strGambar, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_makanan);


        edtkodemakanan = (EditText) findViewById(R.id.edtkodemakanan);
        edtnamamakanan = (EditText) findViewById(R.id.edtnamamakanan);
        edtjumlah = (EditText) findViewById(R.id.edtjumlah);
        edthargamakanan = (EditText) findViewById(R.id.edthargamakanan);

        imgGambarMakanan = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strkodemakanan = i.getStringExtra("kodemakanan");
        strnamamakanan = i.getStringExtra("namamakanan");
        strjumlah = i.getStringExtra("jumlah");
        strhargamakanan = i.getStringExtra("hargamakanan");
        strGambar = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");
        edtkodemakanan.setText(strkodemakanan);
        edthargamakanan.setText(strnamamakanan);
        edtjumlah.setText(strjumlah);
        edthargamakanan.setText(strhargamakanan);
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + strGambar)
                .into(imgGambarMakanan);
    }
}