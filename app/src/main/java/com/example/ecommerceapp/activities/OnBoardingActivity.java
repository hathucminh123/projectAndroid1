package com.example.ecommerceapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapters.SliderAdapter;

public class OnBoardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button btn;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);
        //hide toolbar
        //getSupportActionBar().hide();


        viewPager=findViewById(R.id.slider);

        dotsLayout=findViewById(R.id.dots);
        btn=findViewById(R.id.get_started_btn);
addDots(0);
viewPager.addOnPageChangeListener(changeListener);

        //call Adapter
        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingActivity.this, RegistrationActivity.class));

            }
        });
    }
    private void addDots(int position){

        dots=new TextView[3];
        dotsLayout.removeAllViews();
        for(int i=0;i< dots.length;i++){
            dots[i] =new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);

        }
if(dots.length>0){
    dots[position].setTextColor(getResources().getColor(R.color.pink));
}


    }
    ViewPager.OnPageChangeListener changeListener =new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            if(position==0){
                btn.setVisibility(View.INVISIBLE);

            } else if (position ==1) {
                btn.setVisibility(View.INVISIBLE);
                
            }else {
                animation= AnimationUtils.loadAnimation(OnBoardingActivity.this,R.anim.slider_animation);
                btn.setAnimation(animation);
                btn.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}