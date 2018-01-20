package com.caocao.choseanimal;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageGoc,imageNhan;
    public static ArrayList<String> arrayName;
    int REQUEST_CODE_IMAGE;
    String tenhinhgoc="";
    int Diem=0;
    TextView txtDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addControl() {
        imageGoc = findViewById(R.id.imageViewGoc);
        imageNhan = findViewById(R.id.imageViewNhan);
        txtDiem = findViewById(R.id.textViewDiem);
        String [] mangTen = getResources().getStringArray(R.array.list_name_image);
        arrayName = new ArrayList<>(Arrays.asList(mangTen));
        Collections.shuffle(arrayName);
        int idHinh = getResources().getIdentifier(arrayName.get(5),"drawable",getPackageName());
        tenhinhgoc = arrayName.get(5);
        imageGoc.setImageResource(idHinh);

        imageNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,ImageActivity.class),REQUEST_CODE_IMAGE);
            }
        });
    }

    private void addEvent() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_IMAGE &&resultCode == RESULT_OK &&data!=null){
            String tenhinh = data.getStringExtra("tenhinhchon");
            int idHinhNhan = getResources().getIdentifier(tenhinh,"drawable",getPackageName());
            imageNhan.setImageResource(idHinhNhan);
            //so sánh tên hình
            if(tenhinhgoc.equals(tenhinh)){
                Diem+=10;
                txtDiem.setText("Điểm: "+Diem);
                Toast.makeText(MainActivity.this,"Chính xác",Toast.LENGTH_SHORT).show();

                new CountDownTimer(1200,100){

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        Collections.shuffle(arrayName);
                        int idHinh = getResources().getIdentifier(arrayName.get(5),"drawable",getPackageName());
                        tenhinhgoc = arrayName.get(5);
                        imageGoc.setImageResource(idHinh);

                    }
                }.start();


            }else{
                Diem-=20;
                txtDiem.setText("Điểm: "+Diem);
                Toast.makeText(MainActivity.this,"Sai rồi. Huhu",Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode==REQUEST_CODE_IMAGE && resultCode ==RESULT_CANCELED){
            Diem-=5;
            txtDiem.setText("Điểm: "+Diem);
            Toast.makeText(MainActivity.this,"Bạn chưa chọn hình. \n Bạn bị trừ 5 điểm",Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Toast.makeText(MainActivity.this,"CDpoc",Toast.LENGTH_SHORT).show();
        if(item.getItemId() == R.id.menu_reload){
            Collections.shuffle(arrayName);
            int idHinh = getResources().getIdentifier(arrayName.get(5),"drawable",getPackageName());
            tenhinhgoc = arrayName.get(5);
            imageGoc.setImageResource(idHinh);

        }
        return super.onOptionsItemSelected(item);
    }
}
