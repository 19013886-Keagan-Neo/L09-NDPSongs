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

public class ModifyActivity extends AppCompatActivity {

    EditText etID, etTitle, etSingers, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.rgStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText(String.valueOf(data.get_id()));
        etTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));

        if (data.getStars() == 1)
        {
            rb1.setChecked(true);
        }
        else if (data.getStars() == 2)
        {
            rb2.setChecked(true);
        }
        else if (data.getStars() == 3)
        {
            rb3.setChecked(true);
        }
        else if (data.getStars() == 4)
        {
            rb4.setChecked(true);
        }
        else if (data.getStars() == 5)
        {
            rb5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.set_id(Integer.parseInt(etID.getText().toString()));
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSingers.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));

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

                data.setStars(stars);
                dbh.updateSong(data);
                dbh.close();
                Intent i = new Intent(ModifyActivity.this, ShowListActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteSong(data.get_id());
                Intent i = new Intent(ModifyActivity.this, ShowListActivity.class);
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}