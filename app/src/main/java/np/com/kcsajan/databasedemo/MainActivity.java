package np.com.kcsajan.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etId, etName, etAddress;
    Button btnInsert, btnSelect, btnUpdate, btnDelete;
    TextView tvData;

    MyDbHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating object of database helper
        myDbHelper = new MyDbHelper(this);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);

        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        tvData = findViewById(R.id.tvData);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(etId.getText().toString());
                String name = etName.getText().toString();
                String address = etAddress.getText().toString();

                myDbHelper.insertData(id, name, address);
                Toast.makeText(MainActivity.this, "Data Insert Successfully !", Toast.LENGTH_SHORT).show();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 0;
                String name = "";
                String address = "";

                Cursor cursor = myDbHelper.selectData();
                while (cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    name = cursor.getString(1);
                    address = cursor.getString(2);
                }
                // displaying data
                tvData.setText("Id= " + id + "\nName= " + name + "\nAddress= " + address);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                String address = etAddress.getText().toString();

                myDbHelper.updateData(id, name, address);
                Toast.makeText(MainActivity.this, "Data Update Successfully !", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etId.getText().toString();
                myDbHelper.deleteData(id);
                Toast.makeText(MainActivity.this, "Data Deleted Successfully !", Toast.LENGTH_SHORT).show();
            }
        });

    }
}