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
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import khampha.com.CaSi.AdapterCasi;
import khampha.com.CaSi.Casi;
import khampha.com.HoatDong.AdapterHoatDong;
import khampha.com.HoatDong.HoatDong;
import khampha.com.ZingChart.AdapterZingChart;
import khampha.com.ZingChart.BH_ZingChart;
import khampha.com.muonnghe.AdapterMuonNghe;
import khampha.com.muonnghe.BaiHat;

public class MainActivity extends AppCompatActivity {

    final String DATABASE_NAME = "anh_zing_mp3.sqlite";
    SQLiteDatabase database;

    //EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        searchView = (androidx.appcompat.widget.SearchView) findViewById(R.id.search);
        searchView.setQueryHint("Heloo");*/
        //footer
        bottomNavigationView();

        //search(); hàm xử lý seach

        //Layout kham pha
        addControler_Kham_Pha();
        //Chi tiết của các thành phần trong layout
        details();

    }
    //Details
    private void details(){
        detail_nhacMoi();
    }
    //details nhạc mới
    private void detail_nhacMoi(){
        TextView textView = (TextView) findViewById(R.id.tv_nhacMoi);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goto_nhacMoi();
            }
        });
    }

    //Controler Footer
    private void bottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bt_navigation);
        bottomNavigationView.setSelectedItemId(R.id.kham_pha);

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

    //Đén nhac mới
    private void goto_nhacMoi(){
        Intent intent = new Intent (this, detailNhacMoiActivity.class);
        startActivity(intent);
    }
    //Đén khám phá
    private void goto_kham_pha(){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
    //Đến zingchart
    public void goto_ZingChart(){
        Intent intent = new Intent (this, ZingChartActivity.class);
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

    private void addControler_Kham_Pha(){
        init_muon_nghe();
        init_playlist();
        init_radio();
        init_casi();
        init_hoatdong();
        init_zingchat();
        init_top100();
    }

    //xu ly search
    /*public void search(){
        editText = (EditText) findViewById(R.id.search);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == editText.getId())
                {
                    editText.setCursorVisible(true);
                }
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent event) {
                editText.setCursorVisible(false);
                if (event != null&& (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(editText.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });
    }*/

    //Adapter Muốn nghe
    public  void init_muon_nghe(){
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select *from muonnghe ",null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_muon_nghe);
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
        AdapterMuonNghe adapter = new AdapterMuonNghe(arrayList_nghe,getApplicationContext(),R.layout.row_layout4);
        recyclerView.setAdapter(adapter);
    }

    //Adapter Muốn nghe
    public  void init_radio(){
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * from radio ",null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_radio);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<BaiHat> arrayList_radio =  new ArrayList<>();

        for(int i=0 ; i < cursor.getCount() ; i++){
            cursor.moveToPosition(i);
            String ten =cursor.getString(1);
            byte [] anh = cursor.getBlob(2);

            arrayList_radio.add(new BaiHat(anh,ten));
        }

        AdapterMuonNghe adapter = new AdapterMuonNghe(arrayList_radio,getApplicationContext(),R.layout.row_layout4);
        recyclerView.setAdapter(adapter);
    }

    //Adapter Muốn nghe
    public  void init_playlist(){
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * from playlist ",null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_playlist);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<BaiHat> arrayList =  new ArrayList<>();
        for(int i=0 ; i < cursor.getCount() ; i++){
            cursor.moveToPosition(i);
            String ten =cursor.getString(1);
            byte [] anh = cursor.getBlob(2);

            arrayList.add(new BaiHat(anh,ten));
        }

        AdapterMuonNghe adapter = new AdapterMuonNghe(arrayList,getApplicationContext(),R.layout.row_layout4);
        recyclerView.setAdapter(adapter);

    }

    //Adapter Ca sĩ
    public  void init_casi(){
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * from casi ",null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_casi);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<Casi> arrayList_casi =  new ArrayList<>();
        arrayList_casi.clear();
        AdapterCasi adapter = new AdapterCasi(arrayList_casi,getApplicationContext());
        recyclerView.setAdapter(adapter);

        for(int i=0 ; i < cursor.getCount() ; i++){
            cursor.moveToPosition(i);
           int id = cursor.getInt(0);
            String ten =cursor.getString(1);
            byte [] anh = cursor.getBlob(2);

            arrayList_casi.add(new Casi(ten,anh));
        }
    }

    //Adapter Hoạt động
    public  void init_hoatdong(){
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select *from hoat_dong",null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_hoatdong);
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

        AdapterHoatDong adapter = new AdapterHoatDong(arrayList,getApplicationContext(),R.layout.row_hoat_dong);
        recyclerView.setAdapter(adapter);

    }

    //Adapter zing chart
    public  void init_zingchat(){

        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select *from zing_chat",null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_zingchat);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<BH_ZingChart> arrayList =  new ArrayList<>();
        arrayList.clear();

        for(int i = 0; i < cursor.getCount() ; i++){
            cursor.moveToPosition(i);
            int top = cursor.getInt(0);
            String tenBaiHat = cursor.getString(1);
            String tenCaSi = cursor.getString(2);
            byte [] anh = cursor.getBlob(3);
            arrayList.add(new BH_ZingChart(top,tenBaiHat,tenCaSi,anh));
            }
        AdapterZingChart adapter = new AdapterZingChart(arrayList,getApplicationContext(),R.layout.row_zingchat);
        recyclerView.setAdapter(adapter);

    }

    //Adapter Muốn nghe
    public  void init_top100(){

        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * from Top_100 ",null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Top100);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //Khởi tạo arrayList
        ArrayList<BaiHat> arrayList =  new ArrayList<>();
        arrayList.clear();

        for(int i = 0 ; i < cursor.getCount() ; i++){
            cursor.moveToPosition(i);
            byte [] anh = cursor.getBlob(1);
            String mo_ta = cursor.getString(2);

            arrayList.add(new BaiHat(anh,mo_ta));
        }

        AdapterMuonNghe adapter = new AdapterMuonNghe(arrayList,getApplicationContext(),R.layout.row_layout4);
        recyclerView.setAdapter(adapter);





    }


}