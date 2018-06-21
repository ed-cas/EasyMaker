package com.easymaker.easymaker;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/*
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;*/


public class SplashScreen1 extends AppCompatActivity{

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private ProgressBar progressBar;

    private TextInputLayout textpw, textemail;
    private String name,pw;
    private Button btn_entrar;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        //Pantalla completa
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        callbackManager = CallbackManager.Factory.create();
        loginButton=(LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email"));

        textemail=(TextInputLayout) findViewById(R.id.text_input_layout_email);
        textpw=(TextInputLayout)findViewById(R.id.text_input_layout_pass);
        btn_entrar=(Button)findViewById(R.id.btnLogin);


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Se cancelo el Inicio de Sesion en Facebook",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"Error al Iniciar Secion en Facebook",Toast.LENGTH_SHORT).show();
            }
        });

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!textemail.getEditText().getText().toString().isEmpty() && !textpw.getEditText().getText().toString().isEmpty()){
                    name=textemail.getEditText().getText().toString();
                    pw=textpw.getEditText().getText().toString();
                    firebaseAuth.signInWithEmailAndPassword(name,pw)
                            .addOnCompleteListener(SplashScreen1.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(SplashScreen1.this, "Usuario o contraseña erróneos",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(SplashScreen1.this,"Rellena los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();

        firebaseAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user != null){
                    goMainScreen();
                }
            }
        };
    }

    private void handleFacebookAccessToken(AccessToken accessToken){
        progressBar.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error an la Autenticacion",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
                loginButton.setVisibility(View.VISIBLE);
            }
        });
    }

    private void goMainScreen() {
        Intent intent=new Intent(this, Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    protected void onStop(){
        super.onStop();
        firebaseAuth.removeAuthStateListener(firebaseAuthListener);
    }

    protected void goRegister(View view){
        Intent intent=new Intent(this, RegisterActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void forgot_pw(View view){
        if(!textemail.getEditText().getText().toString().isEmpty()){
            name=textemail.getEditText().getText().toString();
            firebaseAuth.sendPasswordResetEmail(name).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SplashScreen1.this, "Se a enviado un correo a " + name,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SplashScreen1.this, "Correo No registrado previamente",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(SplashScreen1.this, "Ingrese un Correo electronico Valido",Toast.LENGTH_SHORT).show();
        }
    }
}

/**ReciclerView
 * Elementos necesarios
 * adapter //acomodador de los datos dentro del recycler analogo a la persona que acomoda a la gente que entra a un concierto
 * view holder //necesario para optimizar los elementos en los layouts
 * layout manager permite linear layout, grid layou manager, state grid manager
 * dataset // fuente de donde se extraen los datos
 * **/


/*
// Generacion de SHA1 para SDK FB
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.easymaker.easymaker",
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}*/

