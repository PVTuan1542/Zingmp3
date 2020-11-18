package baocaocuoiki.com;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

public class detailNhacMoiActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nhac_moi);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void toolBar(){
        toolbar = findViewById(R.id.atoolBar);
        toolbar.setTitle("Mới phát hành");
        toolbar.setContentDescription("Tat ca");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goto_CaNhan();
            }
        });
    }
    //Đến cá nhân
    private void goto_CaNhan(){
        Intent intent = new Intent (this, CaNhanActivity.class);
        startActivity(intent);
    }
}