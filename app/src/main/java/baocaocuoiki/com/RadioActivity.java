package baocaocuoiki.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import khampha.com.HoatDong.AdapterHoatDong;
import khampha.com.HoatDong.HoatDong;
import khampha.com.muonnghe.AdapterMuonNghe;
import khampha.com.muonnghe.BaiHat;

public class RadioActivity extends AppCompatActivity {

    final String DATABASE_NAME = "anh_zing_mp3.sqlite";
    SQLiteDatabase database;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        imageView = (ImageView) findViewById(R.id.img_tk);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goto_CaNhan();
            }
        });

        //footer
        bottomNavigationView();

        //add controler
        addControler();


    }

    private void bottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bt_navigation);
        bottomNavigationView.setSelectedItemId(R.id.radio);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ca_nhan:
                        goto_CaNhan();
                        return true;
                    case R.id.kham_pha:
                        goto_kham_pha();
                        return true;
                    case R.id.zingchat:
                        goto_ZingChart();
                        return true;
                    case R.id.radio:
                        goto_radio();
                        return true;
                    case R.id.theodoi:
                        return true;
                }
                return false;
            }
        });
    }
    //Dến zingchart
    public void goto_ZingChart(){
        Intent intent = new Intent (this, ZingChartActivity.class);
        startActivity(intent);
    }
    //Đến Khám phá
    private void goto_kham_pha(){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
    //Đến radio
    private void goto_radio(){
        Intent intent = new Intent (this, RadioActivity.class);
        startActivity(intent);
    }
    //Đến cá nhân
    private void goto_CaNhan(){
        Intent intent = new Intent (this, CaNhanActivity.class);
        startActivity(intent);
    }

    //Controler
    private void addControler(){
        //Kenh Nổi bật
        init_layout(R.id.recyclerView_KenhNoiBat,"KenhNoiBat");
        //The loại
        init_theloai_casi(R.id.recyclerView_TheLoai,"TheLoai");
        //Chủ đề
        init_layout(R.id.recyclerView_ChuDe,"ChuDe");
        //Ca sĩ
        init_theloai_casi(R.id.recyclerView_NgheSi,"casi");
    }

    //Adapter Hoạt động
    public  void init_layout(int rcv,String table){
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select *from "+table+" ",null);

        RecyclerView recyclerView = (RecyclerView) findViewById(rcv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<HoatDong> arrayList =  new ArrayList<>();
        arrayList.clear();

        for(int i=0 ; i < cursor.getCount() ; i++){
            cursor.moveToPosition(i);
            byte[] anh = cursor.getBlob(2);
            String ten = cursor.getString(1);

            arrayList.add(new HoatDong(anh,ten));
        }

        AdapterHoatDong adapter = new AdapterHoatDong(arrayList,getApplicationContext(),R.layout.row_radio);
        recyclerView.setAdapter(adapter);
    }

    //Dùng Adapter MuonNGhe cho thể loại và ca sĩ
    public  void init_theloai_casi(int rcv, String table){
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select *from "+table+" ",null);

        RecyclerView recyclerView = (RecyclerView) findViewById(rcv);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,
                GridLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        ArrayList<BaiHat> arrayList_nghe =  new ArrayList<>();

        for(int i = 0 ; i < cursor.getCount() ; i++){
            cursor.moveToPosition(i);
            String ten =cursor.getString(1);
            byte [] anh = cursor.getBlob(2);

            arrayList_nghe.add(new BaiHat(anh,ten));
        }
        AdapterMuonNghe adapter = new AdapterMuonNghe(arrayList_nghe,getApplicationContext(),R.layout.row_radio);
        recyclerView.setAdapter(adapter);
    }
}