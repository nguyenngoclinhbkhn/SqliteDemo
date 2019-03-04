package com.example.nguyenngoclinh.sqlitedatabase2demomanagerstudent;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.nguyenngoclinh.sqlitedatabase2demomanagerstudent.database.DatabaseManager;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private DatabaseManager databaseManager;
    private SimpleCursorAdapter cursorAdapter; // de ket noi tung hang voi item
    private Cursor cursor; // du lieu cua bang

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listViewStudent);
        databaseManager = new DatabaseManager(this);
        databaseManager.insertStudent("Linh", "0123412423");
        databaseManager.insertStudent("Long", "0975676456");
        databaseManager.insertStudent("Loan", "0678234234");
        databaseManager.insertStudent("Lieu", "016234565");
        databaseManager.insertStudent("Lien", "0456456345");
        databaseManager.insertStudent("Ly", "0198575656");

        //lay danh sach sinh vien

        cursor = databaseManager.getInforStudent();

        cursorAdapter = new SimpleCursorAdapter(this, R.layout.item_student, cursor,
                new String[]{"_id", "Name", "Phone"},
                new int[]{R.id.textViewId, R.id.textViewName, R.id.textViewPhone});

        listView.setAdapter(cursorAdapter);

        //bat su kien click vao item de xoa

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                int _id = cursor.getInt(0);
                databaseManager.deleteStudent(_id);
                getStudent();
            }
        });


    }
    private void getStudent(){
        cursor = databaseManager.getInforStudent();

        cursorAdapter = new SimpleCursorAdapter(this, R.layout.item_student, cursor,
                new String[]{"_id", "Name", "Phone"},
                new int[]{R.id.textViewId, R.id.textViewName, R.id.textViewPhone});

        listView.setAdapter(cursorAdapter);
    }
}
