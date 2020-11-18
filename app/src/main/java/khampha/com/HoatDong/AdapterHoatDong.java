package khampha.com.HoatDong;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import baocaocuoiki.com.R;

public class AdapterHoatDong extends  RecyclerView.Adapter<AdapterHoatDong.ViewHoder>{

    ArrayList<HoatDong> hoatDongs;
    Context context;
    int layout_row;

    public AdapterHoatDong(ArrayList<HoatDong> hoatDongs, Context context,int layout_row) {
        this.hoatDongs = hoatDongs;
        this.context = context;
        this.layout_row = layout_row;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(layout_row,parent,false);
        return new ViewHoder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.txtTen.setText(hoatDongs.get(position).getTen());

        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(hoatDongs.get(position).getAnh(),
                0,hoatDongs.get(position).getAnh().length);
        holder.imgHinh.setImageBitmap(bmHinhDaiDien);
    }

    @Override
    public int getItemCount() {
        return hoatDongs.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{

        TextView txtTen;
        ImageView imgHinh;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);

            txtTen = (TextView) itemView.findViewById(R.id.tv_ten);
            imgHinh = (ImageView) itemView.findViewById(R.id.img);

        }
    }
}
