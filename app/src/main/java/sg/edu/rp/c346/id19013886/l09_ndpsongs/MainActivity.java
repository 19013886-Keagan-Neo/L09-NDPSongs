package sg.edu.rp.c346.id19013886.l09_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    Button btnInsert, btnShowList;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int stars = 0;

                if (rb1.isChecked())
                {
                    stars = 1;
                }
                else if (rb2.isChecked())
                {
                    stars = 2;
                }
                else if (rb3.isChecked())
                {
                    stars = 3;
                }
                else if (rb4.isChecked())
                {
                    stars = 4;
                }
                else if (rb5.isChecked())
                {
                    stars = 5;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(etTitle.getText().toString(),
                        etSingers.getText().toString(), Integer.parseInt(etYear.getText().toString()),
                        Integer.parseInt(String.valueOf(stars)));

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();

                    etTitle.setText("");
                    etSingers.setText("");
                    etYear.setText("");

                    int checkRB = rg.getCheckedRadioButtonId();
                    if (checkRB == R.id.rb1) {
                        rb1.setChecked(false);
                    } else if (checkRB == R.id.rb2) {
                        rb2.setChecked(false);
                    } else if (checkRB == R.id.rb3) {
                        rb3.setChecked(false);
                    } else if (checkRB == R.id.rb4) {
                        rb4.setChecked(false);
                    } else {
                        rb5.setChecked(false);
                    }

                }
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowListActivity.class);
                startActivity(i);
            }
        });

    }
}