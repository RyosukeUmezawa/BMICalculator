package com.example.admin.bmicalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 2015/06/23.
 */
public class HistoryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView listView = (ListView) findViewById(R.id.list);
        ArrayList<History> list = new ArrayList<>();
        ListViewAdapter adapter = new ListViewAdapter(HistoryActivity.this);
        adapter.setListView(list);
        listView.setAdapter(adapter);

        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM history", null);
        c.moveToFirst();

        for (int i = 0; i<c.getCount(); i++) {
            double Height = c.getDouble(c.getColumnIndex("height"));
            double Weight = c.getDouble(c.getColumnIndex("weight"));
            double BMI = c.getDouble(c.getColumnIndex("bmi"));
            String strDate = c.getString(c.getColumnIndex("date"));


            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");
            String fdate = "";

            try {
                Date date = dateformat.parse(strDate);
                fdate = format2.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            History history = new History();
            history.setHeight(Height);
            history.setWeight(Weight);
            history.setBMI(BMI);
            history.setStrDate(fdate);
            list.add(history);
            adapter.notifyDataSetChanged();

            c.moveToNext();
        }

        c.close();
        db.close();

        //リスト項目をクリック時に呼び出されるコールバックを登録
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //リスト,クリック時の処理
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(HistoryActivity.this, "Click:" + String.valueOf(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HistoryActivity.this, ResultHistoryActivity.class);
                intent.putExtra("Position", String.valueOf(position + 1));
                startActivity(intent);
            }

        });
        //とりあえず残しておく
//        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this);
//        SQLiteDatabase db = helper.getReadableDatabase();
//
//        Cursor c = db.query("history", new String[]{"rowid as _id", "height",
//                "weight", "bmi", "date"}, null, null, null, null, null);
//
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_history, c,
//                new String[]{"height", "weight", "bmi", "date"},
//                new int[]{R.id.height, R.id.weight, R.id.bmi, R.id.date}, 0);
//
//        //リストビュー
//        ListView list = (ListView) findViewById(R.id.list);
//        list.setAdapter(adapter);
//
//        //リスト項目をクリック時に呼び出されるコールバックを登録
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            //リスト,クリック時の処理
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
////                Toast.makeText(HistoryActivity.this, "Click:" + String.valueOf(position), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(HistoryActivity.this, ResultHistoryActivity.class);
//                intent.putExtra("Position", String.valueOf(position + 1));
//                startActivity(intent);
//            }
//        });
    }

    public void onClickBack(View v) {
        //ResultActivityを終了
        finish();
    }

    public class History {
        long Id;
        double Height;
        double Weight;
        double BMI;
        String strDate;

        public long getId() { return Id; }

        public void setId(int id) { this.Id = id; }

        public double getHeight() { return Height; }

        public void setHeight(double height) { this.Height = height; }

        public double getWeight() { return Weight; }

        public void setWeight(double weight) { this.Weight = weight; }

        public double getBMI() { return BMI; }

        public void setBMI(double bmi) { this.BMI = bmi; }

        public String getDate() { return strDate; }

        public void setStrDate(String date) { this.strDate = date; }
    }

}
