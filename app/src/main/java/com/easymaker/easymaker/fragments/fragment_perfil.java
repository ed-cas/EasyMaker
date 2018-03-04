package com.easymaker.easymaker.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.easymaker.easymaker.R;
import com.easymaker.easymaker.SplashScreen1;
import com.easymaker.easymaker.Objetos.Usuario;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;


public class fragment_perfil extends Fragment {
     View view;
     private TextView name;
     private TextView email;
     private TextView idu;
     private CircleImageView circleImageView;
     private Usuario u;

    public fragment_perfil() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            u= (Usuario)getArguments().getSerializable("key_user");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_perfil, container, false);

        name=(TextView)view.findViewById(R.id.textView2_user);
        email=(TextView)view.findViewById(R.id.textView_email);
        idu=(TextView)view.findViewById(R.id.textView4_uid);
        circleImageView=(CircleImageView)view.findViewById(R.id.profile_image);
        Button button=(Button)view.findViewById(R.id.btn_logout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogOut(view);
            }
        });

        name.setText(u.getName());
        email.setText(u.getEmail());
        idu.setText(u.getId());
        Uri url=u.getPerfil_photo();
        Picasso.with(getContext()).load(url).into(circleImageView);
        return view;
    }


    public void LogOut(View view){
        LoginManager.getInstance().logOut();
        FirebaseAuth.getInstance().signOut();
        goLoginScreen();
    }

    private void goLoginScreen() {
        Intent intent= new Intent(getContext(), SplashScreen1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
