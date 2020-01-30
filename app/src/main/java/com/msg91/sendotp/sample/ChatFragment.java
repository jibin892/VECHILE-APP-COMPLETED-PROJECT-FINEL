package com.msg91.sendotp.sample;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    String[] vechilee = {"Car", "Bus", "Traveler", "Mini Bus", "jeep"};
    String[] pepole = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "10-15", "10-25", "20-30", "30-50", "50-100"};
    String[] tour = {"Family tour", "Sightseeing Tours", "Shore Excursion Tours", "Adventure or Sporting Tours", "Other Types of Tours"};
    String[] pakeges = {"1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000", "10000-15000", "10000=25000", "20000-30000", "30000-5000", "50000-100000", "above 1 lack"};
    private DatePicker datePicker;
Intent aa;
    private TextView dateView;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    EditText date, phone, name;
    Spinner vechile, noofp, ttour, packege;
    Button exp_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_chat, container, false);

//        LinearLayout constraintLayout = root.findViewById(R.id.cc);
//        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
//        animationDrawable.setEnterFadeDuration(2000);
//        animationDrawable.setExitFadeDuration(2000);
//        animationDrawable.start();
        name = root.findViewById(R.id.nm);
        phone = root.findViewById(R.id.po);
        vechile = root.findViewById(R.id.vechile);
        noofp = root.findViewById(R.id.noofpe);
        ttour = root.findViewById(R.id.ttour);
        packege = root.findViewById(R.id.packeg);
        date = root.findViewById(R.id.date);

        ArrayAdapter aas = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, vechilee);
        aas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        vechile.setAdapter(aas);


        ArrayAdapter aass = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, pepole);
        aass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        noofp.setAdapter(aass);

        ArrayAdapter aasss = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, tour);
        aasss.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        ttour.setAdapter(aasss);


        ArrayAdapter aassss = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, pakeges);
        aassss.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        packege.setAdapter(aassss);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        exp_btn = root.findViewById(R.id.exp_btn);

        exp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


           Intent aa=new Intent(getActivity(),Payment.class);
                aa.putExtra("nm", name.getText().toString());
                aa.putExtra("ph", phone.getText().toString());
                aa.putExtra("tdance", date.getText().toString());
                aa.putExtra("tname",vechile.getSelectedItem().toString().toLowerCase());
                aa.putExtra("tphone",noofp.getSelectedItem().toString().toLowerCase());
                aa.putExtra("temail",ttour.getSelectedItem().toString().toLowerCase());
                aa.putExtra("pa",packege.getSelectedItem().toString().toLowerCase());


           startActivity(aa);



            }
        });


        return root;


    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


//        if (id==R.id.techersde){
//
//            Intent i=new Intent(getActivity(),Therapy.class);
//            startActivity(i);
////            Toast.makeText(getActivity(),"Techers",Toast.LENGTH_LONG).show();
//
//
//        }
//        if (id==R.id.classtime){
//            Intent ii=new Intent(getActivity(),Phycology.class);
//            startActivity(ii);
//
////            Toast.makeText(getActivity(),"class",Toast.LENGTH_LONG).show();
//
//
//        }
        if (id == R.id.onlinesupport) {
            Intent iii = new Intent(getActivity(), Online.class);
            startActivity(iii);

//            Toast.makeText(getActivity(),"class",Toast.LENGTH_LONG).show();


        }
        if (id == R.id.newdance) {


            Intent iiii = new Intent(getActivity(), Newaddmision_st.class);
            startActivity(iiii);


        }

        if (id == R.id.event) {


            Intent iiiii = new Intent(getActivity(), Eventok.class);
            startActivity(iiiii);


        }


        return super.onOptionsItemSelected(item);
    }


}