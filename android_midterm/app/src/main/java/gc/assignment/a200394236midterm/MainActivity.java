package gc.assignment.a200394236midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText editTextFirstName, editTextLastName;
    TextView textViewAndroidChoice;
    Button buttonPlay, buttonReset;
    Spinner spinner;

    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseArtists = FirebaseDatabase.getInstance().getReference("Game");

        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        textViewAndroidChoice = (TextView) findViewById(R.id.textViewAndroidChoice);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        spinner = (Spinner) findViewById(R.id.spinner);



        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Play Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Reset Clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
