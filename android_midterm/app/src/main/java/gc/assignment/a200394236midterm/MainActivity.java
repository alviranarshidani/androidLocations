package gc.assignment.a200394236midterm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editTextFirstName, editTextLastName;
    TextView textViewAndroidChoice;
    Button buttonPlay, buttonReset;
    Spinner spinner;

    DatabaseReference DbMidtermRef;
    private static final Random rand = new Random();
    String WinAns;
    List<Game> GameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbMidtermRef = FirebaseDatabase.getInstance().getReference("Game");

        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        textViewAndroidChoice = (TextView) findViewById(R.id.textViewAndroidChoice);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        spinner = (Spinner) findViewById(R.id.spinner);




        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Play Clicked ", Toast.LENGTH_SHORT).show();

                generateRandomNumber();

                String UserChoice = spinner.getSelectedItem().toString();
                //Toast.makeText(MainActivity.this, UserChoice, Toast.LENGTH_SHORT).show();
                String AndroidChoice = textViewAndroidChoice.getText().toString();
                //Toast.makeText(MainActivity.this, AndroidChoice, Toast.LENGTH_SHORT).show();


                whoWins(UserChoice, AndroidChoice);
                addGame();

            }

            private void whoWins(String UserChoice, String AndroidChoice) {

                if(UserChoice == AndroidChoice)
                    WinAns = "Tie";
                else if(UserChoice == "Scissors" && AndroidChoice == "Paper")
                    WinAns = "User";
                else if(UserChoice == "Paper" && AndroidChoice == "Rock")
                    WinAns = "User";
                else if(UserChoice == "Rock" && AndroidChoice == "Scissors")
                    WinAns = "User";
                else
                    WinAns = "Android";

             Toast.makeText(MainActivity.this, WinAns, Toast.LENGTH_SHORT).show();

            }

            private void generateRandomNumber() {
                int randInt = rand.nextInt(100)+1;
               //Toast.makeText(MainActivity.this, "Generated no.: " + randInt, Toast.LENGTH_SHORT).show();

                if (randInt<34)
                    textViewAndroidChoice.setText("Rock");
                else if(randInt>33 && randInt<67)
                    textViewAndroidChoice.setText("Paper");
                else if(randInt>66)
                    textViewAndroidChoice.setText("Scissors");
            }

             private void addGame(){
                String FirstName = editTextFirstName.getText().toString();
                String LastName = editTextLastName.getText().toString();
                String Choice = spinner.getSelectedItem().toString();
                String Winner = WinAns;


                if(!TextUtils.isEmpty(FirstName)){
                    String GameId = DbMidtermRef.push().getKey();

                    Game game = new Game(GameId, FirstName, LastName, Choice, Winner);

                    DbMidtermRef.child(GameId).setValue(game);

                    //Toast.makeText(this, "Game added", Toast.LENGTH_SHORT).show();

                }else{
                    //Toast.makeText(this, "You should enter a name", Toast.LENGTH_SHORT).show();
                }
            }



        });


        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Reset Clicked", Toast.LENGTH_SHORT).show();

                editTextFirstName.setText("");
                editTextLastName.setText("");
                textViewAndroidChoice.setText("");

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        DbMidtermRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot gameSnapshot : dataSnapshot.getChildren()){
                    Game game = gameSnapshot.getValue(Game.class);
                    GameList.add(game);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
