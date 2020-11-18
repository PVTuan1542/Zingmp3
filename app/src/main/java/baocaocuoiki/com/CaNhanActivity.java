package baocaocuoiki.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CaNhanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_nhan);
    }

    private void bottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bt_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ca_nhan);

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
}