package com.example.latestsenatorproj;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SenatorFragment extends Fragment {
    public Senator mSenator;
    private SenatorHolderAdapter mAdapter;
    private RecyclerView recyclerView;
    private SenatorSingleton singleton;



    public  static Fragment newInstance(){
        return  new SenatorFragment();
    }


    public SenatorFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setHasOptionsMenu(true);
        mSenator = new Senator();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_senator, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        update();
        return view ;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu,menuInflater);
        menuInflater.inflate(R.menu.menu_main,menu);

        singleton = SenatorSingleton.getInstance(getActivity());

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                Log.d("the query is :",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                Log.d("the query is :",newText);
                return true;
            }
        });


    }


    private void searchContact(String keyword) {

        List<Senator> senatorSearched = singleton.getSenator(keyword);
        mAdapter = new SenatorHolderAdapter(senatorSearched);
        if(senatorSearched != null){
            recyclerView.setAdapter(mAdapter);
        }
    }
    private void update(){
        singleton = SenatorSingleton.getInstance(getActivity());
        List<Senator>  senators = singleton.allSenators();
        mAdapter= new SenatorHolderAdapter(senators);
        mAdapter.setCrimes(senators);
        recyclerView.setAdapter(mAdapter);
    }




    // View holder class for the individual objects we have in our app

    public class SenateHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView mail ;
        private TextView phone;
        private TextView State;
        private ImageView Imemail;
        private ImageView Imphone;

        public SenateHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
             Imemail = itemView.findViewById(R.id.imageView);
            Imphone = itemView.findViewById(R.id.imageView3);
        }


        public void bind(final Senator senator){

            name.setText(senator.getName());

        }



    }


// This adapter class holds the views from the view holders

    public  class SenatorHolderAdapter extends RecyclerView.Adapter<SenateHolder>{
// this contains the list we are going to be making use of in our main fragment
        List<Senator> mode;

        public static final String EMAIL = "email_address";

        public SenatorHolderAdapter( List<Senator> mode ){

            this.mode = mode;
        }


        @NonNull
        @Override
        public SenateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewdesign,parent,false);
            final SenateHolder hold = new SenateHolder(view);
            //final Senator senator = new Senator();
            return hold;
        }

        @Override
        public void onBindViewHolder(@NonNull SenateHolder holder, int position) {
           final Senator model = mode.get(position);
            holder.bind(model);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ExampleBottomSheet sheet = ExampleBottomSheet.newInstance(model.getName(),model.getEmail(),model.getPhone(),model.getState());
                    FragmentManager fragmentManager = getFragmentManager();
                    sheet.show(fragmentManager,"Show");
                }
            });

            holder.Imemail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    String messageText = model.getEmail();
                    intent.putExtra(Intent.EXTRA_TEXT, messageText);
                    intent.setType("text/plain");
                    startActivity(intent);
                }
            });

            holder.Imphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    String phone = model.getPhone();
                    intent.putExtra(Intent.EXTRA_PHONE_NUMBER,phone);
                    startActivity(intent);

                }
            });


        }

        @Override
        public int getItemCount() {

            return mode.size();
        }

        public void setCrimes(List<Senator> senators) {
            mode = senators;
        }


    }





}
