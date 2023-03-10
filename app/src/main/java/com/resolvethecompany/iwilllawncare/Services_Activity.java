package com.resolvethecompany.iwilllawncare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;


public class Services_Activity extends AppCompatActivity {

    // creating object of ViewPager
    ViewPager mViewPager;

    Button makePaymentButton;

    // images array
    int[] images = {R.raw.ivan_grass_bush, R.raw.ivan_grass_driveway,
            R.raw.ivan_grass_fullyard, R.raw.ivan_grass_largeyard};

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;
    Button callButton;

    private void initAllVars(){
        callButton = findViewById(R.id.call_Button);
        // Initializing the ViewPager Object
        mViewPager = findViewById(R.id.viewPager);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(Services_Activity.this, images);

        //make payment
        makePaymentButton = findViewById(R.id.makePayment);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy
                        .Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // init all vars
        initAllVars();

        //check for action bar, if present/visible - hide it
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        handleCallButtonOnClick_Method();
        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);

        makePaymentButton.setOnClickListener(view -> {
            Intent toMakePayment =
                    new Intent(Services_Activity.this,
                    TakePaymentActivity.class);
            startActivity(toMakePayment);
        });
    }

    private void handleCallButtonOnClick_Method(){
        callButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:3092517393"));
            startActivity(intent);
        });
    }

    public void handleStripePayment() throws StripeException {
        // Set your secret key. Remember to switch to your live secret key in production.
        // See your keys here: https://dashboard.stripe.com/apikeys
        Stripe.apiKey =
                "sk_test_51LQIaEEPiu5wg8RP4lmMdR" +
                        "qoZ0AWPsZpyxeQaU0SkGLsaVDxkKMPE8f7NnSE2la2" +
                        "8S7XIzXLMXyNKbyvfEUsENT400DwNMtHIR";

        ProductCreateParams params = ProductCreateParams.builder().setName("lawn care").build();

        Product product = Product.create(params);

        // Set your secret key. Remember to switch to your live secret key in production.
        // See your keys here: https://dashboard.stripe.com/apikeys
        Stripe.apiKey = "sk_test_51LQIaEEPiu5wg8RP4lmMdRqoZ0AWPsZpyxeQaU0SkGLsaVDxkKM" +
                "PE8f7NnSE2la28S7XIzXLMXyNKbyvfEUsENT400DwNMtHIR";

        PriceCreateParams productParams =
                PriceCreateParams
                        .builder()
                        .setProduct("prod_MVCOu27Ykqr9k3")
                        .setUnitAmount(2000L)
                        .setCurrency("usd")
                        .build();

        try {
            Price price = Price.create(productParams);
            Log.d("price", " " + price);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}