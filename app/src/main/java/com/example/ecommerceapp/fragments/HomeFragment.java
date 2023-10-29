package com.example.ecommerceapp.fragments;
import com.example.ecommerceapp.activities.ShowAllActivity;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ecommerceapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.example.ecommerceapp.adapters.CategoryAdapter;
import com.example.ecommerceapp.models.CategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreKtxRegistrar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.ecommerceapp.adapters.NewProductsAdapter;
import com.example.ecommerceapp.models.NewProductsModel;

import com.example.ecommerceapp.adapters.PopularProductsAdapter;
import com.example.ecommerceapp.models.PopularProductsModel;
public class HomeFragment extends Fragment {

    TextView catShowAll,popularShowAll,newProductShowAll;

    //Popular RecyclerView

 PopularProductsAdapter popularProductsAdapter;
 List<PopularProductsModel> popularProductsModelList;
//Category recycleView
    RecyclerView catRecyclerview,newProductRecyclerView,popularProductsRecyclerView;
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    //new Products RecylerView
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;

    FirebaseFirestore db;
    LinearLayout linearLayout;

    ProgressDialog progressDialog;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_home, container, false);
         db=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(getActivity());



        catRecyclerview=root.findViewById(R.id.rec_category);


        newProductRecyclerView=root.findViewById(R.id.new_product_rec);

        popularProductsRecyclerView=root.findViewById(R.id.popular_rec);



        catShowAll=root.findViewById(R.id.category_see_all);
        popularShowAll=root.findViewById(R.id.popular_see_all);
        newProductShowAll=root.findViewById(R.id.newProducts_see_all);


        catShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ShowAllActivity.class);
                startActivity(intent);
            }
        });
        newProductShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ShowAllActivity.class);
                startActivity(intent);
            }
        });
        popularShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ShowAllActivity.class);
                startActivity(intent);
            }
        });



        linearLayout=root.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);

        //image slider

        ImageSlider imageSlider=root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels= new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.img_1,"Discount on gunpla item", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.img_3,"Discount 4% on gunpla item", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.img_4,"Discount 13% on gunpla item", ScaleTypes.CENTER_CROP));


        imageSlider.setImageList(slideModels);
progressDialog.setTitle("Welcome to My Gunplashop");
progressDialog.setMessage("please wait");
progressDialog.setCanceledOnTouchOutside(false);
progressDialog.show();



        //Category
        catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList=new ArrayList<CategoryModel>();
        categoryAdapter=new CategoryAdapter(getActivity(),categoryModelList);
        catRecyclerview.setAdapter(categoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel=document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }
                        } else {
                            Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

//New Products
        newProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsModelList=new ArrayList<>();
        newProductsAdapter=new NewProductsAdapter(getContext(),newProductsModelList);
        newProductRecyclerView.setAdapter(newProductsAdapter);
        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NewProductsModel newProductsModel=document.toObject(NewProductsModel.class);
                                newProductsModelList.add(newProductsModel);
                                newProductsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });



       //PopularProducts
        popularProductsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularProductsModelList=new ArrayList<>();
        popularProductsAdapter=new PopularProductsAdapter(getContext(),popularProductsModelList);
        popularProductsRecyclerView.setAdapter(popularProductsAdapter);

        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProductsModel popularProductsModel=document.toObject(PopularProductsModel.class);
                                popularProductsModelList.add(popularProductsModel);
                                popularProductsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        return root;
    }
}