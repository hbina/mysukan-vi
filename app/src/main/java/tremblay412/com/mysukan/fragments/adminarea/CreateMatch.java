package tremblay412.com.mysukan.fragments.adminarea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import tremblay412.com.mysukan.helper.NameManager;
import tremblay412.com.mysukan.R;
import tremblay412.com.mysukan.fragments.BaseFragment;

public class CreateMatch extends BaseFragment {

    private Spinner teamOne, teamTwo, scoreOne, scoreTwo, scoreThree, scoreFour, scoreFive, scoreSix;
    ArrayAdapter<CharSequence> teamAdapter, scoreAdapter;
    private Button submitButton;
    private TextView text;
    public String sport_name;
    private Bundle args;
    private DatabaseReference databaseSport;
    private List<String> checker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView;

        //get argument from previous fragment
        args = getArguments();
        sport_name = args.getString("sport_name");


        //array for checker
        checker = Arrays.asList("badminton_men_doubles", "badminton_women_doubles", "badminton_mixed_doubles", "squash_men_singles", "squash_women_singles");

        if (!checker.contains(NameManager.UserToDatabase(sport_name))) {
            rootView = inflater.inflate(R.layout.submit_score_norm, container, false);

            teamOne = (Spinner) rootView.findViewById(R.id.teamOne);
            teamTwo = (Spinner) rootView.findViewById(R.id.teamTwo);
            scoreOne = (Spinner) rootView.findViewById(R.id.scoreOne);
            scoreTwo = (Spinner) rootView.findViewById(R.id.scoreTwo);

            teamAdapter = ArrayAdapter.createFromResource(getContext(), R.array.team_list, android.R.layout.simple_spinner_item);
            teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            teamOne.setAdapter(teamAdapter);
            teamTwo.setAdapter(teamAdapter);

            scoreAdapter = ArrayAdapter.createFromResource(getContext(), R.array.number, android.R.layout.simple_spinner_item);
            scoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            scoreOne.setAdapter(scoreAdapter);
            scoreTwo.setAdapter(scoreAdapter);
        } else {
            rootView = inflater.inflate(R.layout.submit_score_set, container, false);

            teamOne = (Spinner) rootView.findViewById(R.id.teamOne);
            teamTwo = (Spinner) rootView.findViewById(R.id.teamTwo);
            scoreOne = (Spinner) rootView.findViewById(R.id.scoreOne);
            scoreTwo = (Spinner) rootView.findViewById(R.id.scoreTwo);
            scoreThree = (Spinner) rootView.findViewById(R.id.scoreThree);
            scoreFour = (Spinner) rootView.findViewById(R.id.scoreFour);
            scoreFive = (Spinner) rootView.findViewById(R.id.scoreFive);
            scoreSix = (Spinner) rootView.findViewById(R.id.scoreSix);

            teamAdapter = ArrayAdapter.createFromResource(getContext(), R.array.team_list, android.R.layout.simple_spinner_item);
            teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            teamOne.setAdapter(teamAdapter);
            teamTwo.setAdapter(teamAdapter);

            scoreAdapter = ArrayAdapter.createFromResource(getContext(), R.array.number, android.R.layout.simple_spinner_item);
            scoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            scoreOne.setAdapter(scoreAdapter);
            scoreTwo.setAdapter(scoreAdapter);
            scoreThree.setAdapter(scoreAdapter);
            scoreFour.setAdapter(scoreAdapter);
            scoreFive.setAdapter(scoreAdapter);
            scoreSix.setAdapter(scoreAdapter);
        }

        //set textHeader
        text = (TextView) rootView.findViewById(R.id.textView10);
        text.setText(sport_name);

        //Change string to sync with database string

        sport_name = NameManager.UserToDatabase(sport_name);

        //Database
        databaseSport = FirebaseDatabase.getInstance().getReference("games");

        submitButton = (Button) rootView.findViewById(R.id.BTN_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseSport.push().getKey();

                if (sport_name == "soccer" || sport_name == "frisbee") {
                    SportNorm sport = new SportNorm(id, teamOne.getSelectedItem().toString(), teamTwo.getSelectedItem().toString(), Integer.parseInt(scoreOne.getSelectedItem().toString()), Integer.parseInt(scoreTwo.getSelectedItem().toString()));
                    databaseSport.child(sport_name).child(id).setValue(sport);
                    Toast.makeText(getContext(), "Sport added", Toast.LENGTH_LONG).show();
                } else {
                    SportSet sport = new SportSet(id, teamOne.getSelectedItem().toString(), teamTwo.getSelectedItem().toString(), Integer.parseInt(scoreOne.getSelectedItem().toString()), Integer.parseInt(scoreTwo.getSelectedItem().toString())
                            , Integer.parseInt(scoreThree.getSelectedItem().toString()), Integer.parseInt(scoreFour.getSelectedItem().toString())
                            , Integer.parseInt(scoreFive.getSelectedItem().toString()), Integer.parseInt(scoreSix.getSelectedItem().toString()));
                    databaseSport.child(sport_name).child(id).setValue(sport);
                    Toast.makeText(getContext(), "Sport added", Toast.LENGTH_LONG).show();
                }


            }
        });

        return rootView;
    }
}