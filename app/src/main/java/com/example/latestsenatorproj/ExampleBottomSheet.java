package com.example.latestsenatorproj;



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExampleBottomSheet extends BottomSheetDialogFragment {

    public static final String ID = "senator_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PHONE= "senator_id";
    public static  final String STATE = "state";

    public ExampleBottomSheet() {
        // Required empty public constructor
    }


   public static ExampleBottomSheet newInstance(String name, String email, String phone, String State){

        Bundle args = new Bundle();
        args.putString(NAME,name);
        args.putString(EMAIL,email);
        args.putString(PHONE,phone);
        args.putString(STATE,State);

        ExampleBottomSheet sheet = new ExampleBottomSheet();
        sheet.setArguments(args);
        return sheet;
    }


    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog,container,false);
            TextView nameOfSenator = view.findViewById(R.id.nameOfSenator);
            TextView emailOfSenator = view.findViewById(R.id.emailAddress);
            TextView stateOrDistrict = view.findViewById(R.id.StateAndDistrict);
            TextView senatorsNumber = view.findViewById(R.id.phoneNumber);

            // We are going to be getting the values we need from the onbindViewHolder with the getArguments method;
               String name = getArguments().getString(NAME);
               String email = getArguments().getString(EMAIL);
               String phone = getArguments().getString(PHONE);
               String State = getArguments().getString(STATE);

            nameOfSenator.setText(name);
            emailOfSenator.setText(email);
            stateOrDistrict.setText(State);
            senatorsNumber.setText(phone);

        return view;
    }

}



