package com.example.admin.bmicalculator;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import java.lang.*;


/**
 * Created by admin on 2015/06/19.
 */
public class ResultActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = this.getIntent();

        //身長を表示
        String txtheight = intent.getStringExtra("Height");
        TextView height = (TextView) findViewById(R.id.height);
        height.setText(txtheight);

        //体重を表示
        String txtweight = intent.getStringExtra("Weight");
        TextView weight = (TextView) findViewById(R.id.weight);
        weight.setText(txtweight);

        //入力を数値化
        double numHeight = Double.parseDouble(txtheight);
        double numWeight = Double.parseDouble(txtweight);

        //BMI計算
        bmiCalculator myCondition = new bmiCalculator();
        myCondition.setHeight(numHeight);
        myCondition.setWeight(numWeight);

        //bmiCalculator.class
        double bmi = myCondition.Caluclator();
        double StandardWeight = myCondition.StandardWeight();
        String Judge = myCondition.bmiJudg(bmi);

        //db挿入
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        MySQLiteOpenHelper.insert(db, numHeight, numWeight, bmi);

        db.close();

        //計算結果を表示
        TextView txtbmi = (TextView) findViewById(R.id.bmi);
        String strBmi = String.valueOf(bmi);
        txtbmi.setText(strBmi);

        //BMIの判定をする
        TextView txtJudge = (TextView) findViewById(R.id.bmiJudg);
        txtJudge.setText(Judge);

        //標準体重を表示
        TextView txtStanderdWeight = (TextView) findViewById(R.id.standardWeight);
        String strStandardWeight = String.valueOf(StandardWeight);
        txtStanderdWeight.setText(strStandardWeight);
    }

    public void onClickBack(View v) {
        //ResultActivityを終了
        finish();
    }

    public void onClickHistory (View v){
        //履歴
        Intent intent = new Intent(this, com.example.admin.bmicalculator.HistoryActivity.class);
        startActivity(intent);
    }
}