package com.example.homepage.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.homepage.Adapter.MenzImageAdapter;
import com.example.homepage.R;

import java.util.ArrayList;

public class ShowGadggtsActivity extends AppCompatActivity {
    TextView back;
    private final String android_version_names[] = {
            "Canon 501",
            "Fuji 101",
            "I-phone watch",
            "MI-band-5",
            "Samsung Note 10",
            "Poco X3",
            "Beats-3",
            "Gamer 320",
            "Mivic-pro",
            "Mavic-2"
    };
    private final String android_version_prices[] = {"150000","70000","20000","6500","33000","37500","3500","1300","250000","150000"
    };

    private final String android_image_urls[] = {
            "https://play-lh.googleusercontent.com/_G2Y9hUj1g09iUt7x2beSOwb3pFUoVcHsMOUFxQd0nSdYPNrm4WcKnEZ8MdqhqyJfQ",
            "https://images.unsplash.com/photo-1552168324-d612d77725e3?ixid=MXwxMjA3fDB8MHxzZWFyY2h8NXx8Y2FtZXJhfGVufDB8fDB8&ixlib=rb-1.2.1&w=1000&q=80",
            "https://img.kentfaith.com/cache/catalog/products/us/GW01.0147/GW01.0147-1-518x518.jpg",
            "https://cf2.s3.souqcdn.com/item/2015/12/10/96/94/27/7/item_XL_9694277_11231094.jpg",
            "https://www.gizbot.com/images/2019-07/vivo-s1_156352984560.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/71ofHxPB7lL._SL1500_.jpg",
            "https://diamu.com.bd/wp-content/uploads/2020/11/Lenovo-HD200-Headphone-1.jpg",
            "http://cdn.shopify.com/s/files/1/1140/4626/products/1MORETripleDriverIn-EarHeadphones_grande.jpg?v=1608884777",
            "https://www.robot-advance.com/EN/ori-drone-pnj-r-traveller-full-hd-2415.jpg",
            "https://149355317.v2.pressablecdn.com/wp-content/uploads/2019/10/DJI-Mavic-2-Pro-Drone-Side-Angle.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gadggts);
        back=findViewById(R.id.back3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initViews();
    }
    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList androidVersions = prepareData();
        MenzImageAdapter adapter = new MenzImageAdapter(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList prepareData(){

        ArrayList android_version = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            com.example.homepage.MenzImageModelClass menzImageModelClass = new com.example.homepage.MenzImageModelClass();
            menzImageModelClass.setAndroid_version_name(android_version_names[i]);
            menzImageModelClass.setAndroid_image_price(android_version_prices[i]);
            menzImageModelClass.setAndroid_image_url(android_image_urls[i]);
            android_version.add(menzImageModelClass);
        }
        return android_version;
    }
}