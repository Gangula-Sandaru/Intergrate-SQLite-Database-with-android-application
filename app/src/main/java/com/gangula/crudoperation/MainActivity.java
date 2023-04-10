package com.gangula.crudoperation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.internal.TouchObserverFrameLayout;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText editName, editCourse, editMarks, editUpdateMarks;
    Button addData, viewData, deleteData, updateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        addData = findViewById(R.id.insertData);
        viewData = findViewById(R.id.viewData);
        updateData = findViewById(R.id.updateData);
        deleteData = findViewById(R.id.deleteData);

        // calling for the addData function.
        addData();
        viewData();
        updateData();
        deleteData();
    }

    // set on click listener to the insert data
    public void addData(){
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editName = findViewById(R.id.studentName);
                editCourse = findViewById(R.id.studentCourse);
                editMarks = findViewById(R.id.studentMark);
                boolean inserted = myDb.insertData(editName.getText().toString(), editCourse.getText().toString(), editMarks.getText().toString());

                // get some output to the screen
                Log.i("DATA", editName.getText().toString() + " " + editCourse.getText().toString() + " " + editMarks.getText().toString());

                if (inserted){
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // view all data
    public void viewData(){
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = myDb.getAllData();
                if(result.getCount() == 0){
                    showMessage("Error message", "No data found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append("ID: " + result.getString(0) + "\n");
                    buffer.append("Name: " + result.getString(1) + "\n");
                    buffer.append("Course: " + result.getString(2) + "\n");
                    buffer.append("Mark: " + result.getString(3) + "\n");
                }

                showMessage("list of Data", buffer.toString());
            }
        });
    }

    private void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }

    public void updateData(){
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editName = findViewById(R.id.studentName);
                editCourse = findViewById(R.id.studentCourse);
                editMarks = findViewById(R.id.studentMark);
                editUpdateMarks = findViewById(R.id.markFUpdate);

                Log.i("DATA", editUpdateMarks.getText().toString());
                boolean isUpdate = myDb.updateData(editUpdateMarks.getText().toString(), editName.getText().toString(), editCourse.getText().toString(), editMarks.getText().toString());
                if(isUpdate){
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void deleteData(){
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteData = myDb.deleteData(editUpdateMarks.getText().toString());
                if(deleteData > 0){
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}