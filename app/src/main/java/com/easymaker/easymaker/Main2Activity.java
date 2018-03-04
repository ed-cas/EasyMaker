package com.easymaker.easymaker;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.easymaker.easymaker.Objetos.Herramienta;
import com.easymaker.easymaker.Objetos.Usuario;
import com.easymaker.easymaker.fragments.fragment_details_tools;
import com.easymaker.easymaker.fragments.fragment_knowledge;
import com.easymaker.easymaker.fragments.fragment_perfil;
import com.easymaker.easymaker.fragments.fragment_proyects;
import com.easymaker.easymaker.adapters.fragment_swipe_adapter;
import com.easymaker.easymaker.fragments.fragment_tools;
import com.easymaker.easymaker.interfaces.Comunica_fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements fragment_tools.OnFragmentInteractionListener, fragment_details_tools.OnFragmentInteractionListener, Comunica_fragment{

    private ViewPager viewPager;
    private Usuario obj_user;
    fragment_details_tools fragment_details_herramientas;
    fragment_tools fragment_herramientas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Pantalla completa
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        final BottomNavigationView navigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_view);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);                  /// Configuracion de Barra inferior de Navegacion

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();  //Cuentas de Usuario

        viewPager=(ViewPager)findViewById(R.id.view_pager_fragments); ///viewpager swipe fragments

        if(firebaseUser != null){
            obj_user = new Usuario();
            obj_user.setEmail(firebaseUser.getEmail());
            obj_user.setName(firebaseUser.getDisplayName());
            obj_user.setId(firebaseUser.getUid());
            obj_user.setPerfil_photo(firebaseUser.getPhotoUrl());
        }else{
            goLoginScreen();  // si no esta log, va a SplashScreen
        }
        setUpFragments_viewPager();  /// PreCarga los Fragments e incializa ViewPAger
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                navigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void goLoginScreen() {
        Intent intent= new Intent(this, SplashScreen1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(item.getItemId() == R.id.item_proyects){
                viewPager.setCurrentItem(0);
                return true;
            }else if(item.getItemId() == R.id.item_knowledge){
                viewPager.setCurrentItem(1);
                return true;
            }else if(item.getItemId() == R.id.item_tools){
                viewPager.setCurrentItem(2);
                return true;
            }else if(item.getItemId() == R.id.item_perfil){
                viewPager.setCurrentItem(3);
                return true;
            }
            return false;
        }
    };

    private void setUpFragments_viewPager(){
        viewPager.setAdapter(new fragment_swipe_adapter(getSupportFragmentManager(),addFragment()));
    }

    private ArrayList<Fragment> addFragment(){
        Bundle bundle = new Bundle();
        bundle.putSerializable("key_user",obj_user);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragment_perfil fp = new fragment_perfil();
        fp.setArguments(bundle);

        fragments.add(new fragment_proyects());
        fragments.add(new fragment_knowledge());
        fragments.add(new fragment_tools());
        fragments.add(fp);
        return fragments;
    }


    public void onFragmentInteraction(Uri uri){

    }

    @Override
    public void enviar_herramienta(Herramienta herramienta) {
        //fragment_details_herramientas = (fragment_details_tools) this.getSupportFragmentManager().findFragmentById(R.id.des_tool_id);
        /*if(findViewById(R.id.recyclerf )== null){
            fragment_details_herramientas.asignarInformacion(herramienta);
        }else {*/
            fragment_details_herramientas=new fragment_details_tools();
            Bundle bundleEnvio=new Bundle();
            bundleEnvio.putSerializable("objeto",herramienta);
            fragment_details_herramientas.setArguments(bundleEnvio);

            //cargamos el fragment en el Activity
            getSupportFragmentManager().beginTransaction().replace(R.id.vista_principal,fragment_details_herramientas).addToBackStack(null).commit();
        //}
    }


}


/**
 * RecyclerView  compuesto por 3 elementos, usado para listar conjunto de datos de un DataSet
 * LayoutManager puede tomar 3 formas de acomdar los elementos linear, stage, Grid  ----Archivo XML con distribucion de elementos de cada CARDVIEW
 * Adapter , usado para acomodar los datos en los layoutmanager                     ----Clase JAVA Acomoda en ADAPTER
 * ViewHolder elemento que optimiza el rendimiento de los elementos en las vistas   ---
 * Item Decoration e ItemAnimator  usados para configuracion de transiciones.
 * **/