package com.example.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.ecommerceapp.models.AddressModel;

import com.example.ecommerceapp.models.MyCartModel;
public class PlacedOrderActivity2 extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order2);


        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();


        List<AddressModel> list=(ArrayList<AddressModel>)getIntent().getSerializableExtra("itemList");

        if(list !=null && list.size()>0){
            for(AddressModel model :list){
                final HashMap<String,Object> cartMap=new HashMap<>();
                cartMap.put("isSelected",model.isSelected());
                cartMap.put("userAddress",model.getUserAddress());
                cartMap.put("productName",model.getProductName());
                cartMap.put("productPrice",model.getProductPrice());
                cartMap.put("currentTime",model.getCurrentTime());
                cartMap.put("currentDate",model.getCurrentDate());
                cartMap.put("totalQuantity",model.getTotalQuantity());
                cartMap.put("totalPrice",model.getTotalPrice());


                firestore.collection("User").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(PlacedOrderActivity2.this,"Your order success ",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });


            }
        }
    }
}