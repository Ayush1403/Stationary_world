package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class mycart extends AppCompatActivity {
    LinearLayout llcv;
    ProgressBar pb;
    Button placorder;
    FirebaseDatabase database ;
    DatabaseReference orderRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);
        pb=findViewById(R.id.cartpb);
        llcv=findViewById(R.id.cartview_ll);
        placorder=findViewById(R.id.placeorder);
        database = FirebaseDatabase.getInstance();
        orderRef = database.getReference("ORDERS");
        productfetching();
    }
    private void productfetching(){

//        orderRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                llcv.removeAllViews();
//                for (DataSnapshot detailsSnapshot : dataSnapshot.getChildren()) {
//                    // Extract the email from the snapshot
//
//
//                    Toast.makeText(mycart.this, detailsSnapshot+"", Toast.LENGTH_SHORT).show();
//                    // Get the child nodes under emailSnapshot
//                    DataSnapshot aboutSnapshot = detailsSnapshot.child("about");
//                    DataSnapshot amountSnapshot = detailsSnapshot.child("amount");
//                    DataSnapshot categorySnapshot= detailsSnapshot.child("category");
//                    DataSnapshot nameSnapshot = detailsSnapshot.child("name");
//                    DataSnapshot shopkeeperSnapshot = detailsSnapshot.child("shopkeeper");
//
//
//
//
//                    // Check if both dob and fullname nodes exist
//
//                    // Extract the values from the snapshots
//                    String about = aboutSnapshot.getValue(String.class);
//                    String amount = amountSnapshot.getValue(String.class);
//                    String category = categorySnapshot.getValue(String.class);
//                    String name=nameSnapshot.getValue(String.class);
//                    String shopkeeper=shopkeeperSnapshot.getValue(String.class);
//                    TextView tv_about=new TextView(mycart.this);
//                    TextView tv_amount=new TextView(mycart.this);
//                    TextView tv_category=new TextView(mycart.this);
//                    TextView tv_name=new TextView(mycart.this);
//                    TextView tv_shopkeeper=new TextView(mycart.this);
//
//                    tv_about.setText("about-"+about);
//                    tv_amount.setText("amount -"+amount);
//                    tv_category.setText("category-"+category);
//                    tv_name.setText("name-"+name);
//                    tv_shopkeeper.setText("shopkeeper-"+shopkeeper);
//
//                    tv_about.setTextSize(20);
//                    tv_amount.setTextSize(20);
//                    tv_category.setTextSize(20);
//                    tv_name.setTextSize(20);
//                    tv_shopkeeper.setTextSize(20);
//                    Button b=new Button(mycart.this);
//                    b.setText("REMOVE PRODUCT");
//                    b.setWidth(25);
//                    b.setHeight(10);
//                    b.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            Intent intent=new Intent(mycart.this,productview.class);
////                            intent.putExtra("pid",pid);
//                            intent.putExtra("about",about);
//                            intent.putExtra("amount",amount);
//                            intent.putExtra("category",category);
//                            intent.putExtra("name",name);
//                            intent.putExtra("shopkeeper",shopkeeper);
//                            startActivity(intent);
//
//
//                        }
//                    });//
////
//                    CardView cardView = new CardView(mycart.this);
//                    int cardMargin = getResources().getDimensionPixelSize(R.dimen.card_margin);
//                    int cardElevation = getResources().getDimensionPixelSize(R.dimen.card_elevation);
//                    cardView.setLayoutParams(new ViewGroup.LayoutParams(
//                            ViewGroup.LayoutParams.MATCH_PARENT,
//                            ViewGroup.LayoutParams.WRAP_CONTENT
//
//
//                    ));
////                    Margin to cardView
//
////                    cardView.setCardBackgroundColor(Color.WHITE);
//
//
//
//                    cardView.setCardElevation(cardElevation);
//
//                    cardView.setContentPadding(cardMargin, cardMargin, cardMargin, cardMargin);
//                    LinearLayout innerLayout = new LinearLayout(mycart.this);
//                    innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
//                            LinearLayout.LayoutParams.MATCH_PARENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT
//                    ));
//                    innerLayout.setOrientation(LinearLayout.VERTICAL);
//
//// Add the TextViews to the LinearLayout
//                    innerLayout.addView(tv_about);
//                    innerLayout.addView(tv_amount);
//                    innerLayout.addView(tv_category);
//                    innerLayout.addView(tv_shopkeeper);
//                    innerLayout.addView(tv_name);
//                    innerLayout.addView(b);
//
//// Add the LinearLayout to the CardView
//                    cardView.addView(innerLayout);
////                    llcv.addView(cardView);
//
//
//                }
//
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(mycart.this, "Try  Again Please", Toast.LENGTH_SHORT).show();
//            }
//        });
        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {


//                     Iterate through the child nodes inside each order
                    for (DataSnapshot emailSnapshot : orderSnapshot.getChildren()) {


                        String emailKey = emailSnapshot.getKey();






                        // Access the child nodes inside each email
                        String aboutUs = emailSnapshot.child("about").getValue(String.class);

                        String anotherChild = emailSnapshot.child("another_child").getValue(String.class);
                        TextView tv=new TextView(mycart.this);
                        tv.setText(aboutUs);
                        llcv.addView(tv);
                        // Do something with the child data
                        Toast.makeText(mycart.this, ""+aboutUs, Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(mycart.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}







