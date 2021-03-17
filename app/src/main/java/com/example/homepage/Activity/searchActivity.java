package com.example.homepage.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.homepage.Adapter.GalleryImageAdapter;
import com.example.homepage.R;

import java.util.ArrayList;

public class searchActivity extends AppCompatActivity {
    ListView listView2;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayList;
    SearchView searchView;
    ImageView imageView,back;
    int[] images = {R.drawable.a1, R.drawable.a4, R.drawable.b, R.drawable.bb, R.drawable.cc,
            R.drawable.guiter, R.drawable.laptop, R.drawable.mobile, R.drawable.laptop,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Search bar
        searchView=findViewById(R.id.searchView_main);
        imageView=findViewById(R.id.searchImageId);
        listView2=findViewById(R.id.listView_main);
        back=findViewById(R.id.back2);
        Gallery gallery = findViewById(R.id.gallery);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        arrayList=new ArrayList<String>();
        arrayList.add("Men");
        arrayList.add("Women");
        arrayList.add("Children");
        arrayList.add("Laptop");
        arrayList.add("Camera");
        arrayList.add("Gadgets");
        arrayList.add("Mobile");
        arrayList.add("Men");
        arrayList.add("Women");
        arrayList.add("Children");
        arrayList.add("Laptop");
        arrayList.add("Camera");
        arrayList.add("Gadgets");
        arrayList.add("Mobile");
        arrayList.add("Men");
        arrayList.add("Women");
        arrayList.add("Children");
        arrayList.add("Laptop");
        arrayList.add("Camera");
        arrayList.add("Gadgets");
        arrayList.add("Mobile");

        arrayAdapter=new ArrayAdapter(searchActivity.this, android.R.layout.simple_list_item_1,arrayList);
        listView2.setAdapter(arrayAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(searchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }



            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        listView2.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String a = (String) parent.getAdapter().getItem(position);
               if (a=="Men")
               {

                   imageView.setImageResource(R.drawable.men);
                   imageView.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent intent=new Intent(searchActivity.this, ShowDwnloadImageRvActivity.class);
                           startActivity(intent);

                       }
                   });


               }
                if (a=="Women")
                {
                    imageView.setImageResource(R.drawable.women);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        }
                    });

                }
                if (a=="Children")
                {
                    imageView.setImageResource(R.drawable.children);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        }
                    });

                }
                if (a=="Laptop")
                {
                    imageView.setImageResource(R.drawable.laptop);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        }
                    });

                }
                if (a=="Mobile")
                {
                    imageView.setImageResource(R.drawable.mobile);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        }
                    });

                }

            }
        });




        //gallery

        GalleryImageAdapter galleryImageAdapter = new GalleryImageAdapter(searchActivity.this); // initialize the adapter
        gallery.setAdapter(galleryImageAdapter); // set the adapter
        gallery.setSpacing(10);

        // perform setOnItemClickListener event on the Gallery
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set the selected image in the ImageView
                imageView.setImageResource(images[position]);

            }
        });


        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // show the selected Image
                imageView.setImageResource(galleryImageAdapter.mImageIds[position]);
            }
        });



    }

}