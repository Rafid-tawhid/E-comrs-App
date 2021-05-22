package com.example.homepage.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;
//
//import com.example.homepage.HelperClasses.adapterphone;
//import com.example.homepage.HelperClasses.phonehelper;

import com.example.homepage.Adapter.adapterProduct5;
import com.example.homepage.R;
import com.example.homepage.Adapter.SliderAdapter;
import com.example.homepage.SliderData;
import com.example.homepage.Adapter.adapterProduct2;
import com.example.homepage.Adapter.adapterProduct3;
import com.example.homepage.Adapter.adapterphone;
import com.example.homepage.Upload;
import com.example.homepage.phonehelper;
import com.example.homepage.productHelper;
import com.example.homepage.productHelper2;
import com.example.homepage.productHelper5;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity implements adapterphone.ListItemClickListener {


    //    drawer code
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    public String a;
    Menu menu;
    List<Upload> mUploadS;



    ListView listView2;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayList;
    SearchView searchView;



    String url1 = "https://images.alphacoders.com/696/thumb-1920-696703.jpg";
    String url3 = "https://wallpapercave.com/wp/fwJJS77.jpg";
    String url4 = "https://images.pexels.com/photos/1841841/pexels-photo-1841841.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";
    String url2 = "https://images-na.ssl-images-amazon.com/images/I/718YpWJJPTL._AC_SL1500_.jpg";


    RecyclerView phoneRecycler, recyclerView2, recyclerView3, recyclerView5;
    RecyclerView.Adapter adapter, adapter2, adapter3;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        


        //Slider
        // we are creating array list for storing our image urls.
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));
        sliderDataArrayList.add(new SliderData(url4));


        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();


        //Search bar
        searchView = findViewById(R.id.searchView_main);
        listView2 = findViewById(R.id.listView_main);


        //drawer


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottom_nav_main);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_men) {
                    Intent intent = new Intent(MainActivity.this, ShowMenzImageRv.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_women) {
                    Intent intent = new Intent(MainActivity.this, ShowWomensImageRv.class);
                    startActivity(intent);
                }

                return false;
            }
        });
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.bringToFront();


        //Hooks
        phoneRecycler = findViewById(R.id.my_recycler);
        recyclerView2 = findViewById(R.id.my_recycler2);
        recyclerView3 = findViewById(R.id.my_recycler3);
        recyclerView5 = findViewById(R.id.my_recycler5);
        phoneRecycler();
        productRecycler();
        productRecycler2();
        productrecycler5();


        //Bottom_nav


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bottom_nav_cart: {


                  openFbMessenger();

                        break;

                    }
                    case R.id.bottom_nav_product:
                        Intent intent4 = new Intent(MainActivity.this, ShowDwnloadImageRvActivity.class);
                        startActivity(intent4);

                        break;
                    case R.id.bottom_nav_home:
                        Intent intent3 = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent3);

                        //search trying

                        break;

                }
                return false;
            }
        });


        //slider


    }

    private void openFbMessenger() {

        String YourPageURL = "https://www.facebook.com/RAFI5D/";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));

        startActivity(browserIntent);

    }


    private void productRecycler2() {
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<productHelper2> productlocation3 = new ArrayList<>();
        productlocation3.add(new productHelper2(R.drawable.mobile));
        productlocation3.add(new productHelper2(R.drawable.laptop));
        productlocation3.add(new productHelper2(R.drawable.pants3));
        productlocation3.add(new productHelper2(R.drawable.tshirt));
        productlocation3.add(new productHelper2(R.drawable.guiter));

        adapter3 = new adapterProduct3(productlocation3, this::onphoneListClick);
        recyclerView3.setAdapter(adapter3);

    }


    private void productRecycler() {
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<productHelper> productlocation = new ArrayList<>();
        productlocation.add(new productHelper(R.drawable.wm1));
        productlocation.add(new productHelper(R.drawable.wm2));
        productlocation.add(new productHelper(R.drawable.wm3));
        productlocation.add(new productHelper(R.drawable.wm4));
        productlocation.add(new productHelper(R.drawable.wm5));
        productlocation.add(new productHelper(R.drawable.wm6));
        productlocation.add(new productHelper(R.drawable.wm7));
        productlocation.add(new productHelper(R.drawable.wm8));
        productlocation.add(new productHelper(R.drawable.wm9));
        productlocation.add(new productHelper(R.drawable.wm101));
        adapter2 = new adapterProduct2(productlocation, this::onphoneListClick);
        recyclerView2.setAdapter(adapter2);

    }

    private void phoneRecycler() {


        phoneRecycler.setHasFixedSize(true);
        phoneRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<phonehelper> phonelocations = new ArrayList<>();
        phonelocations.add(new phonehelper(R.drawable.men, "Men"));
        phonelocations.add(new phonehelper(R.drawable.shoe, "Shoe"));
        phonelocations.add(new phonehelper(R.drawable.bag, "Bag"));
        phonelocations.add(new phonehelper(R.drawable.children, "Children"));
        phonelocations.add(new phonehelper(R.drawable.men, "Men"));
        phonelocations.add(new phonehelper(R.drawable.shoe, "Shoe"));
        phonelocations.add(new phonehelper(R.drawable.bag, "Bag"));
        phonelocations.add(new phonehelper(R.drawable.children, "Children"));

        phonelocations.add(new phonehelper(R.drawable.women, "Women"));


        adapter = new adapterphone(phonelocations, this);
        phoneRecycler.setAdapter(adapter);


    }


    private void productrecycler5() {


        recyclerView5.setHasFixedSize(false);
        recyclerView5.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<productHelper5> object5 = new ArrayList<>();
        object5.add(new productHelper5(R.drawable.mn1,"Samsung A20 4GB/64GB Smartphones","16000","Tk 14599",4));
        object5.add(new productHelper5(R.drawable.mn2,"Samsung A20 4GB/64GB Smartphones","16000","Tk 14599",4));
        object5.add(new productHelper5(R.drawable.mn3,"Samsung A20 4GB/64GB Smartphones","16000","Tk 14599",4));
        object5.add(new productHelper5(R.drawable.mn4,"Samsung A20 4GB/64GB Smartphones","16000","Tk 14599",4));
        object5.add(new productHelper5(R.drawable.mn5,"Samsung A20 4GB/64GB Smartphones","16000","Tk 14599",4));
        object5.add(new productHelper5(R.drawable.mn6,"Samsung A20 4GB/64GB Smartphones","16000","Tk 14599",4));
        object5.add(new productHelper5(R.drawable.mn7,"Samsung A20 4GB/64GB Smartphones","16000","Tk 14599",4));
        object5.add(new productHelper5(R.drawable.mn8,"Samsung A20 4GB/64GB Smartphones","16000","Tk 14599",4));
        object5.add(new productHelper5(R.drawable.mn9,"Samsung A20 4GB/64GB Smartphones","16000","Tk 14599",4));
        object5.add(new productHelper5(R.drawable.mn10,"Samsung A20 4GB/64GB Smartphones","16000","Tk 14599",4));



       adapterProduct5 adapter5 = new adapterProduct5(object5,this::onphoneListClick);
        recyclerView5.setAdapter(adapter5);



    }



    @Override
    public void onphoneListClick(int clickedItemIndex) {


            Intent mIntent;
            switch (clickedItemIndex){

//            case 0: //first item in Recycler view
//                mIntent  = new Intent(MainActivity.this, ShowMenzImageRv.class);
//                startActivity(mIntent);
//                break;
//                case 1: //first item in Recycler view
//                    mIntent  = new Intent(MainActivity.this, ShowDwnloadImageRvActivity.class);
//                    startActivity(mIntent);
//                    break;
//                case 2: //first item in Recycler view
//                    mIntent  = new Intent(MainActivity.this, ShowWomensImageRv.class);
//                    startActivity(mIntent);
//                    break;
//                case 3: //first item in Recycler view
//                    mIntent  = new Intent(MainActivity.this, ShowDwnloadImageRvActivity.class);
//                    startActivity(mIntent);
//                    break;



        }




    }
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }


//Menu logon/logout
    public void popUp(View view) {
        PopupMenu popupMenu=new PopupMenu(MainActivity.this,view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.loginOP:
                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                        return true;
                    case R.id.exitOp:
                        Toast.makeText(MainActivity.this, "Good Bye", Toast.LENGTH_SHORT).show();
                        finish();
                        System.exit(0);


                        return true;

                    case R.id.cartOP:
                        Intent intent6=new Intent(MainActivity.this, ShowMultipleProductCart.class);
                        startActivity(intent6);


                        return true;

                }

                return false;
            }
        });
        popupMenu.inflate(R.menu.optionmenu);
        popupMenu.show();

    }


    //search
    public void search(View view) {

        Intent intent=new Intent(MainActivity.this, searchActivity.class);
        startActivity(intent);

    }

    public void catagorys(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), ShowDwnloadImageRvActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    public void gadegts(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), ShowGadggtsActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }
}



