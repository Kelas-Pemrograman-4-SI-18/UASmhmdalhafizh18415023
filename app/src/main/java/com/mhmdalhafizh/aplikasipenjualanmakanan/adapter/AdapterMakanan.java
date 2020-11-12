package com.mhmdalhafizh.aplikasipenjualanmakanan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhmdalhafizh.aplikasipenjualanmakanan.R;
import com.mhmdalhafizh.aplikasipenjualanmakanan.model.ModelMakanan;
import com.mhmdalhafizh.aplikasipenjualanmakanan.server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMakanan extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelMakanan> item;

    public AdapterMakanan(Activity activity, List<ModelMakanan> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_makanan, null);


        TextView kodemakanan        = (TextView) convertView.findViewById(R.id.txtkodemakanan);
        TextView namamakanan        = (TextView) convertView.findViewById(R.id.txtnamamakanan);
        TextView jumlahmakanan      = (TextView) convertView.findViewById(R.id.txtjumlahmakanan);
        TextView hargamakanan       = (TextView) convertView.findViewById(R.id.txtharga);
        ImageView gambarmakanan            = (ImageView) convertView.findViewById(R.id.gambarmakanan);

        kodemakanan.setText(item.get(position).getKodemakanan());
        namamakanan.setText(item.get(position).getNamamakanan());
        jumlahmakanan.setText(item.get(position).getJumlahmakanan());
        hargamakanan.setText("Rp." + item.get(position).getHargamakanan());
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambarmakanan);
        return convertView;
    }
}
