package com.royalcareerservices.rcs;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;


public class Contactus4 extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contactus4,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Contact us");
        TextView textView = getView().findViewById(R.id.te2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    //Creating intents for making a call
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:123456789"));
                    getActivity().startActivity(callIntent);

                }else{
                    Toast.makeText(getActivity(), "You don't assign permission.", Toast.LENGTH_SHORT).show();
                }*/
                if(ContextCompat.checkSelfPermission(
                        getActivity(),android.Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) getActivity(), new
                            String[]{android.Manifest.permission.CALL_PHONE}, 0);
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:8931102992")));
                }
        }
    });
}
}
