package baocaocuoiki.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import khampha.com.ZingChart.AdapterZingChart;
import khampha.com.ZingChart.BH_ZingChart;
import zingchart.com.TopMV.AdapterTopMV;
import zingchart.com.TopMV.MV;

public class ZingChartActivity extends AppCompatActivity {

    final String DATABASE_NAME = "anh_zing_mp3.sqlite";
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zing_chart);

        //Controler
        addControler();

        // CTL footer
        bottomNavigationView();
    }

    //footer
    private void bottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bt_navigation);
        bottomNavigationView.setSelectedItemId(R.id.zingchat);

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
    //Đến zingchart
    public void goto_ZingChart(){
        Intent intent = new Intent (this, ZingChartActivity.class);
        startActivity(intent);
    }
    //Đến khám phá
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
        init_zingchat();
        topmv();
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

    //Adapter zing chart
    public  void topmv(){

        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select *from topMV",null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_topMV);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<MV> arrayList =  new ArrayList<>();
        arrayList.clear();


        for(int i = 0; i < cursor.getCount() ; i++){
            cursor.moveToPosition(i);
            String tenBaiHat = cursor.getString(2);
            String tenCaSi = cursor.getString(3);
            byte [] anh = cursor.getBlob(1);
            arrayList.add(new MV(anh,tenBaiHat,tenCaSi));
        }
        AdapterTopMV adapter = new AdapterTopMV(arrayList,getApplicationContext(),R.layout.row_topmv);
        recyclerView.setAdapter(adapter);

    }

}