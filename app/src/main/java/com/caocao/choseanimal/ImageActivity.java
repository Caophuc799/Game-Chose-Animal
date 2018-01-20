package com.caocao.choseanimal;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Collections;

public class ImageActivity extends Activity {

    TableLayout mytable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mytable = findViewById(R.id.tableLayoutImage);
        int dong = 6;
        int cot =3;
        //create row and column
        Collections.shuffle(MainActivity.arrayName);
        for(int i=0;i<dong;i++){
            TableRow tableRow = new TableRow(this);
            //create image view(cột)
            for(int j=0;j<cot;j++){
                ImageView imageView = new ImageView(this);
                Resources r = getResources();
                int  px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(px,px);
                imageView.setLayoutParams(layoutParams);
                final int vitri = cot*i+j;
//                MainActivity.arrayName
                int idHinh = getResources().getIdentifier(MainActivity.arrayName.get(vitri),"drawable",getPackageName());
                imageView.setImageResource(idHinh);
                tableRow.addView(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra("tenhinhchon",MainActivity.arrayName.get(vitri));
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
            }
            mytable.addView(tableRow);
        }

    }
}
