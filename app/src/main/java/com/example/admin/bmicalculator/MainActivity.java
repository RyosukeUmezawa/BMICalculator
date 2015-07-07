package com.example.admin.bmicalculator;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DB作成
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.close();
    }

    //
    public void onClick(View v){
        //SubActivity
        Intent intent = new Intent(this, com.example.admin.bmicalculator.ResultActivity.class);

        //hight
        EditText editHeight = (EditText)this.findViewById(R.id.editHeight);
        intent.putExtra("Height", editHeight.getText().toString());
        //weight
        EditText editWeight = (EditText)this.findViewById(R.id.editWeight);
        intent.putExtra("Weight", editWeight.getText().toString());

        //
        startActivity(intent);
    }

    public void onClickHistory (View v){
        //履歴
        Intent intent = new Intent(this, com.example.admin.bmicalculator.HistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
