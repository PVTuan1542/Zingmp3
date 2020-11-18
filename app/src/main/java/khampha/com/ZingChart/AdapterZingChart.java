package khampha.com.ZingChart;

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

public class AdapterZingChart extends RecyclerView.Adapter<AdapterZingChart.ViewHolder>{
    ArrayList<BH_ZingChart> baiHats;
    Context context;
    int layout;

    public AdapterZingChart(ArrayList<BH_ZingChart> baiHats, Context context,int layout) {
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
        holder.txtTop.setText(String.valueOf(baiHats.get(position).getTop()));

    }

    @Override
    public int getItemCount() {
        return baiHats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTen,txtCaSi,txtTop ;
        ImageView imgHinh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = (TextView) itemView.findViewById(R.id.tenBaiHat);
            txtCaSi = (TextView) itemView.findViewById(R.id.tenCaSi);
            txtTop = (TextView) itemView.findViewById(R.id.top);
            imgHinh = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
