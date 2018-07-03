package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ContActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private FragmentTransaction fragmentTransaction;
    TextView tvEmail;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cont);
        drawerLayout=(DrawerLayout) findViewById(R.id.draw);
        navigationView=(NavigationView) findViewById(R.id.cont_navigation_view);
        tvEmail=(TextView) findViewById(R.id.tv_header_email);
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);


        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.cont_main_container,new HomeFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Contul meu");


        if(auth.getCurrentUser()==null){
           finish();
           startActivity(new Intent(getApplicationContext(),LogInAvocatActivity.class));

        }



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.it_profil:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.cont_main_container,new AdaugaDateProfilFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(R.string.profil);
                        item.setChecked(true);
                        return true;
                    case R.id.it_solicitari:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.cont_main_container,new SolicitariFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(R.string.solicitari);
                        item.setChecked(true);
                        return true;
                    case R.id.it_conversatii:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.cont_main_container,new ConversatiiFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(R.string.conversatii);
                        item.setChecked(true);
                        return true;
                    case R.id.it_calendar:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.cont_main_container,new CalendarFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(R.string.calendar);
                        item.setChecked(true);
                        return true;
                   case R.id.it_clienti:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.cont_main_container,new ClientiFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(R.string.clienti);
                        item.setChecked(true);
                        return true;
                   case R.id.it_cazuri:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.cont_main_container,new CazuriFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(R.string.cazuri);
                        item.setChecked(true);
                        return true;
                    case R.id.it_setari:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.cont_main_container,new SetariFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(R.string.setari);
                        item.setChecked(true);
                        return true;
                    case R.id.it_despre:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.cont_main_container,new DespreFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(R.string.despre);
                        item.setChecked(true);
                        return true;

                    case R.id.it_out:
                        item.setChecked(true);
                        auth.signOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(),LogInAvocatActivity.class));
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });


   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      if(actionBarDrawerToggle.onOptionsItemSelected(item)){
          return true; }
       return super.onOptionsItemSelected(item);
    }
}
