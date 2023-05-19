package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class productview extends AppCompatActivity {
    TextView name_pv,shopkeeper_pv,about_pv,amount_pv;
    String pid,shopkeeper,userid,amount,category,name,about,id;
    Button addtocart;
    FirebaseDatabase database ;
    DatabaseReference orderRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productview);

        name_pv=findViewById(R.id.name_pv_pv);
        shopkeeper_pv=findViewById(R.id.shopkeeper_pv);
        about_pv=findViewById(R.id.about_pv);
        amount_pv=findViewById(R.id.amount_pv);
        addtocart=findViewById(R.id.addtocart);
        database = FirebaseDatabase.getInstance();
        orderRef = database.getReference("ORDERS");







        pid = getIntent().getStringExtra("pid");
        amount=getIntent().getStringExtra("amount");
        about = getIntent().getStringExtra("about");
        category = getIntent().getStringExtra("category");
        name = getIntent().getStringExtra("name");
        shopkeeper = getIntent().getStringExtra("shopkeeper");


        name_pv.setText(amount);
        about_pv.setText(about);
        amount_pv.setText(amount);
        shopkeeper_pv.setText(shopkeeper);



        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("myuser",MODE_PRIVATE);
                userid = sp.getString("username", "na");
                id=userid.replace(".",",");
                addtocart();



            }
        });


    }
    private void addtocart(){
        Map<String, Object> product = new HashMap<>();
        product.put("pid",pid);
        product.put("amount", amount);
        product.put("shopkeeper",shopkeeper);
        product.put("about",about);
        product.put("category",category);



        orderRef.child(id).push().setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(productview.this, "Data Added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(productview.this, "error"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}