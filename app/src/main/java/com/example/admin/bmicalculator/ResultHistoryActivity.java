package com.example.admin.bmicalculator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Created by admin on 2015/06/25.
 */
public class ResultHistoryActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_history);

        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Intent intent = this.getIntent();
        String position = intent.getStringExtra("Position");

        Cursor c = db.rawQuery("SELECT height, weight, bmi, date FROM history where _id = " + position, null);
        c.moveToFirst();

        double Height = c.getDouble(c.getColumnIndex("height"));
        double Weight = c.getDouble(c.getColumnIndex("weight"));
        double BMI = c.getDouble(c.getColumnIndex("bmi"));
        String strDate = c.getString(c.getColumnIndex("date"));

        c.close();
        db.close();


        bmiCalculator myCondition = new bmiCalculator();
        //set
        myCondition.setHeight(Height);
        myCondition.setWeight(Weight);

        //bmiCalculator.class
        double StandardWeight = myCondition.StandardWeight();
        String Judge = myCondition.bmiJudg(BMI);

        //テキストビュー
        TextView txtHeight = (TextView) findViewById(R.id.height);
        txtHeight.setText(String.valueOf(Height));

        TextView txtWeight = (TextView) findViewById(R.id.weight);
        txtWeight.setText(String.valueOf(Weight));

        TextView txtBMI = (TextView) findViewById(R.id.bmi);
        txtBMI.setText(String.valueOf(BMI));

        //BMIの判定をする
        TextView txtJudge = (TextView) findViewById(R.id.bmiJudg);
        txtJudge.setText(Judge);

        //標準体重を表示
        TextView txtStanderdWeight = (TextView) findViewById(R.id.standardWeight);
        String strStandardWeight = String.valueOf(StandardWeight);
        txtStanderdWeight.setText(strStandardWeight);

        //日付を表示
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");
        String fdate = "";

        try {
            Date date = dateformat.parse(strDate);
            fdate = format2.format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        TextView txtDate = (TextView) findViewById(R.id.date);
//        txtDate.setText(String.valueOf(strDate));
        txtDate.setText(String.valueOf(fdate));
    }

    public void onClickBack(View v) {
        //ResultActivityを終了
        finish();
    }
}
