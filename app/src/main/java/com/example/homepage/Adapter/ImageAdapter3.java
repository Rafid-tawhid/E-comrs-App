package com.example.homepage.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.Activity.PaymentActivity;
import com.example.homepage.Activity.ShowMultipleProductCart;
import com.example.homepage.DataTransferInterface;
import com.example.homepage.R;
import com.example.homepage.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageAdapter3 extends RecyclerView.Adapter<ImageAdapter3.ImageViewHolder> implements Serializable, Filterable {
    private Context mcontext;
    private List<Upload> mupload;
    private List<Upload> mList2;
    private List<Upload> muploadFull;
    ImageView image;
    public static int t;
    public static int total;
    public static String s;
    public static String OrderedproductsName;

    DataTransferInterface dtInterface;



    public final int controler=0;

//constructor for pass value to activity
public ImageAdapter3(Activity activity, DataTransferInterface dtInterface) {
// TODO Auto-generated constructor stub
    this.mcontext = activity;
    this.dtInterface = dtInterface;
}

    private OnItemClickLisnter mlistner;
   FirebaseStorage mStorage=FirebaseStorage.getInstance();
   DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("cart");

    public ImageAdapter3(Context context, List<Upload> uploadS) {
        this.mcontext = context;
        this.mupload = uploadS;
        muploadFull=new ArrayList<>(uploadS);

    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mcontext).inflate(R.layout.multiplae_product_cart,parent,false);

        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload uploadcurrent=mupload.get(position);


        holder.type.setText(uploadcurrent.getType());
        holder.price.setText(uploadcurrent.getPrice());
        holder.name.setText(uploadcurrent.getName());

        holder.quntity.setText(String.valueOf(uploadcurrent.getQuantity()));

        Picasso.get().load(uploadcurrent.getImgUrl()).fit().centerCrop().into(holder.imageView);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String pls =holder.quntity.getText().toString();
                        int a= Integer.parseInt(pls);
                        a++;
                        String p=holder.price.getText().toString();
                        int b=Integer.parseInt(p);
                        saveQuantity(a,uploadcurrent);







                    }
                });
                holder.minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String pls =holder.quntity.getText().toString();
                        int a = Integer.parseInt(pls);
                        a--;
                        String p=holder.price.getText().toString();
                        Integer b=Integer.parseInt(p);

                        saveQuantity(a,uploadcurrent);





                    }
                });


                //Multiple product calculation
                calculation();



             
            }




        });




        //pass value to activity

        calculation();







    }

    private void saveQuantity(int a,Upload upload) {

        FirebaseDatabase.getInstance().getReference("cart").child(upload.getKey()).child("quantity").setValue(a).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mcontext, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


    @Override
    public int getItemCount() {
        return mupload.size();
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder implements MenuItem.OnMenuItemClickListener
    {

        public TextView price,type,name,quntity,plus,minus;
        public ImageView imageView,deleteBtn;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            price=itemView.findViewById(R.id.ptpe);
            type=itemView.findViewById(R.id.tp);
            name=itemView.findViewById(R.id.nm);
            quntity=itemView.findViewById(R.id.qnt);
            imageView=itemView.findViewById(R.id.img);
            plus=itemView.findViewById(R.id.plus2);
            minus=itemView.findViewById(R.id.minus2);
            deleteBtn=itemView.findViewById(R.id.dlt_icon_id);




            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    /////

                    menu.setHeaderTitle("Select Action");
                    MenuItem doWhateEver=menu.add(Menu.NONE,1,1,"Show Details");
                    MenuItem delete=menu.add(Menu.NONE,2,2,"Delete");

                    doWhateEver.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override

                        public boolean onMenuItemClick(MenuItem item) {

                           dialogeOpen();
                            return false;
                        }
                    });
                    delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            deleteItem(getAdapterPosition());
                            return false;
                        }
                    });




                }
            });


            //delete Button
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialogeOpen();




                }
            });





            ///for admin delete update

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if (mlistner!=null)
                   {
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                        {
                            mlistner.onItemclick(position);
                            mlistner.onWatheverclick(position);
                            mlistner.onDeleteclick(position);
                        }
                    }
                }
            });

        }




        ///Item click delete,
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            return false;
        }


        //dialoge

        public void dialogeOpen()
        {
            final Dialog dialog = new Dialog(mcontext);
//                            Dialog cart code
//                            Include dialog.xml file
            dialog.setContentView(R.layout.multiple_cart_dialog);

            Picasso.get().load(mupload.get(getAdapterPosition()).getImgUrl()).fit().centerCrop().into((ImageView) dialog.findViewById(R.id.img));
            TextView tp=dialog.findViewById(R.id.tp);
            TextView pp=dialog.findViewById(R.id.prc);
            TextView ttl=dialog.findViewById(R.id.nm);
            TextView delete=dialog.findViewById(R.id.dlt);
            tp.setText(mupload.get(getAdapterPosition()).getType());
            pp.setText(mupload.get(getAdapterPosition()).getPrice());

            TextView pluss,minuss,count;
            pluss=dialog.findViewById(R.id.plus);
            minuss=dialog.findViewById(R.id.minus);
            count=dialog.findViewById(R.id.one);
            TextView cancel=dialog.findViewById(R.id.cncl);
            total=0;


            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Upload selectedItem=mupload.get(getAdapterPosition());
                    String selectedKey=selectedItem.getKey();
                    databaseReference.child(selectedKey).removeValue();
                    dialog.dismiss();
                    Toast.makeText(mcontext, "Item Removed", Toast.LENGTH_SHORT).show();

                }
            });

            ///count plus price

            pluss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String pls =count.getText().toString();
                    Integer a = Integer.parseInt(pls)+1;
                    count.setText(String.valueOf(a));
                    quntity.setText(String.valueOf(a));
                    String p=price.getText().toString();
                    Integer b=Integer.parseInt(p);




                }
            });
            minuss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String min =count.getText().toString();
                    Integer a = Integer.parseInt(min)-1;
                    count.setText(String.valueOf(a));
                    quntity.setText(String.valueOf(a));
                    String p=price.getText().toString();
                    Integer b=Integer.parseInt(p);






                }

            });
            calculation();



            dialog.show();

        }
    }


    //final calculation from database

    private void calculation() {




        total=0;

        for (int i=0;i<mupload.size();i++)
        {
           Upload upload=mupload.get(i);

            total+=Integer.parseInt(upload.getPrice())*upload.getQuantity();

             s+=upload.getQuantity()+" "+upload.getName();








        }

        ShowMultipleProductCart.chekOutPrice.setText(String.valueOf(total)+"$");
        ShowMultipleProductCart.charged.setText("50"+"$");
        ShowMultipleProductCart.totalPrice.setText(String.valueOf(total+50)+"$");

    }

    private void deleteItem(int adapterPosition) {

          int a=adapterPosition;


          Upload selectedItem=mupload.get(a);
          String selectedKey=selectedItem.getKey();
          databaseReference.child(selectedKey).removeValue();
        Toast.makeText(mcontext, "Item Removed", Toast.LENGTH_SHORT).show();
        total=0;

    }

    public interface OnItemClickLisnter
    {
        void onItemclick(int position);

        void onWatheverclick(int position);
        void onDeleteclick(int position);




    }
    public void setOnItemClickListener(OnItemClickLisnter listener)
    {

        mlistner=listener;
    }


    //search fulter
    @Override
    public Filter getFilter() {

        return exampleFilter;
    }
    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Upload> filterList=new ArrayList<>();
            if (constraint==null || constraint.length()==0)
            {
                filterList.add((Upload) muploadFull);
            }
            else
            {
                String filterPattern=constraint.toString().toLowerCase().trim();
                for (Upload item:muploadFull)
                {
                    if (item.getName().contains(filterPattern)||item.getType().contains(filterPattern)||item.getDetails().contains(filterPattern))
                    {
                        filterList.add(item);
                    }
                }
            }

            FilterResults results=new FilterResults();
            results.values=filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            mupload.clear();
            mupload.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };




}
