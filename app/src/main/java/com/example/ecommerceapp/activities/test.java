//package com.example.ecommerceapp.activities;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
//import com.example.ecommerceapp.R;
//import com.google.android.material.navigation.NavigationView;
//
//import kotlin.Unit;
//import kotlin.jvm.functions.Function1;
//
//public class test extends AppCompatActivity {
//   protected final int home =1;
//   protected final int map =2;
//   protected final int cart =3;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.bottom_nav);
//        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomMenu);
//        bottomNavigation.add(new MeowBottomNavigation.Model(home,R.drawable.baseline_home_24));
//        bottomNavigation.add(new MeowBottomNavigation.Model(map,R.drawable.baseline_map_24));
//        bottomNavigation.add(new MeowBottomNavigation.Model(cart,R.drawable.baseline_shopping_cart_24));
//        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                if (model.getId() == 2){
//                    changeActivity(com.example.ecommerceapp.activities.map.class);
//                }
//                return null;
//            }
//        });
//
//        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                String name;
//                switch (model.getId()){
//                    case home:name = "Home";
//                    Toast.makeText(test.this, name,Toast.LENGTH_SHORT).show();
//                    break;
//                    case map:name = "Map";
//                        Toast.makeText(test.this, name,Toast.LENGTH_SHORT).show();
//                    break;
//                    case cart:name = "cart";
//                        Toast.makeText(test.this, name,Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                return null;
//            }
//        });
//
//
//
//    }
//    private void changeActivity(Class activity){
//        Intent intent = new Intent(this, activity);
//        startActivity(intent);
//        finish();
//    }
//
//}