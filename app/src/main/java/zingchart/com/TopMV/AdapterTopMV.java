package zingchart.com.TopMV;

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
import khampha.com.ZingChart.AdapterZingChart;
import khampha.com.ZingChart.BH_ZingChart;

public class AdapterTopMV extends RecyclerView.Adapter<AdapterTopMV.ViewHolder>{
    ArrayList<MV> baiHats;
    Context context;
    int layout;

    public AdapterTopMV(ArrayList<MV> baiHats, Context context,int layout) {
        this.baiHats = baiHats;
        this.context = context;
        this.layout = layout;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(layout,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTen.setText(baiHats.get(position).getTen());

        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(baiHats.get(position).getAnh(),
                0,baiHats.get(position).getAnh().length);
        holder.imgHinh.setImageBitmap(bmHinhDaiDien);

        holder.txtCaSi.setText(baiHats.get(position).getCasi());

    }

    @Override
    public int getItemCount() {
        return baiHats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTen,txtCaSi ;
        ImageView imgHinh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = (TextView) itemView.findViewById(R.id.tv_TenBaiHat);
            txtCaSi = (TextView) itemView.findViewById(R.id.tv_TenCaSi);
            imgHinh = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
