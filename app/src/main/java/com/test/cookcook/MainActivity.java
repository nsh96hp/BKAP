package com.test.cookcook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.test.cookcook.data.ReadJSon;
import com.test.cookcook.data.entity.Cooked;
import com.test.cookcook.ui.defaultCooked.CookedFragment;
import com.test.cookcook.ui.defaultCooked.addCooked.AddCookedFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    FragmentManager fragmentManager;
    MaterialSearchView searchView;
    ArrayList<Cooked> lstCooked=new ArrayList<>();
    DatabaseReference mData;
    private static String keyID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //////
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction tran=fragmentManager.beginTransaction();
        //////
        mData = FirebaseDatabase.getInstance().getReference();

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                String key = mData.push().getKey();
                Cooked cooked=new Cooked(1,"Name","intro",1,1,1,1,"image");
                mData.child("Cooked").child(key).setValue(cooked);

                keyID=key;
                /////
                FragmentTransaction tran=fragmentManager.beginTransaction();
                Fragment fragment= new AddCookedFragment(MainActivity.this);
                tran.replace(R.id.frame_layout,fragment);
                tran.commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //////////////////// fragment

        Fragment fragment= new CookedFragment(MainActivity.this);
        tran.add(R.id.frame_layout,fragment);
        tran.commit();
        //Search view
        searchView=findViewById(R.id.search_view);
        //Danh sách gợi ý. Lấy danh sách từ list Cooked trên fb+ csdl
        lstCooked= ReadJSon.ReadCookedJsonFile(this);
        String sugg[]=new String[lstCooked.size()];
        for (int i=0;i<lstCooked.size();i++){
            sugg[i]=lstCooked.get(i).getName();
        }
        searchView.setSuggestions(sugg);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_home) {
//            // Handle the camera action
//        } else if (id == R.id.nav_favorites) {
//
//        } else if (id == R.id.nav_plan) {
//
//        } else if (id == R.id.nav_shopping) {
//
//        } else if (id == R.id.nav_download) {
//
//        } else if (id == R.id.nav_setting) {
//
//        }
        switch (id) {
            case (R.id.nav_home):
                //Trang chu cac mon
                fragmentManager=getSupportFragmentManager();
                FragmentTransaction tran=fragmentManager.beginTransaction();
                Fragment fragment= new CookedFragment(MainActivity.this);
                tran.replace(R.id.frame_layout,fragment);
                tran.commit();
                break;
            case (R.id.nav_suggestions):
                //Cac mon goi y
                break;
            case (R.id.nav_favorites):
                //Cac mon uu thich
                break;
            case (R.id.nav_mycooked):
                //Cac mon minh nau dang nen
                break;
            case (R.id.nav_plan):
                //Cac mon du dinh nau
                break;
            case (R.id.nav_shopping):
                //Cac nguyen lieu can de lau mon du dinh
                break;
            case (R.id.nav_friend):
                //danh sach ban, them ban, tim ban, chat voi ban
                break;
            case (R.id.nav_group):
                //chat group, them ng group
                break;
            case (R.id.nav_download):
                //danh sach mon da tai ve
                break;
            case (R.id.nav_setting):
                break;
            case (R.id.nav_help):
                break;
            case (R.id.nav_logout):
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getMyData() {
        return keyID;
    }
}
