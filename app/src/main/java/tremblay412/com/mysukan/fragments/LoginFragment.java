package tremblay412.com.mysukan.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import tremblay412.com.mysukan.R;

public class LoginFragment extends BaseFragment {

    private static final String TAG = "LoginFragment";

    private EditText ET_email, ET_password;
    private Button LoginButton;
    private FirebaseAuth firebaseAuth;

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.activity_login, container, false);

        //Initialize XML hookss
        ET_email = (EditText) rootView.findViewById(R.id.ET_email);
        ET_password = (EditText) rootView.findViewById(R.id.ET_password);
        LoginButton = (Button) rootView.findViewById(R.id.LoginButton);

        // Initialize Firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogin();
            }
        });
        return rootView;
    }

    private void UserLogin() {
        String email = ET_email.getText().toString();
        String password = ET_password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgressDialog("Loading");

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            hideProgressDialog();
                            LoginButton.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(), "You are now logged in", Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            Fragment fr = new AdminFragment();
                            fragmentTransaction.replace(R.id.login_fragment, fr, fr.toString());
                            fragmentTransaction.commit();


                        } else {
                            hideProgressDialog();
                            Toast.makeText(getActivity(), "Login failed. Please check your info", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}