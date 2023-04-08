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

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText editName, editCourse, editMarks, editUpdateMarks;
    Button addData, viewData, deleteDta, updateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);


        editName = findViewById(R.id.studentName);
        editCourse = findViewById(R.id.studentCourse);

        Log.i("Data", "Test data: " +  editCourse.getText().toString());


        editMarks = findViewById(R.id.studentMark);
        editUpdateMarks= findViewById(R.id.markFUpdate);

        addData = findViewById(R.id.insertData);
        viewData = findViewById(R.id.viewData);
        updateData = findViewById(R.id.updateData);
        deleteDta = findViewById(R.id.deleteData);

        // calling for the addData function.
//        addData();
        viewData();



    }


    // set on click lister to the insert data
    public void addData(){
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean inserted = myDb.insertData(editName.getText().toString(), editCourse.toString(), editMarks.toString());

                // get some output to the screen
//                Log.i("Data",editName.getText().toString() +""+ editCourse.toString()+""+ editMarks.toString());

                if (inserted){
                    Toast.makeText(MainActivity.this, "Data Inserted",Toast.LENGTH_SHORT).show();
                }else{
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

                    showMessage("list of Data", buffer.toString());
                }
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
}