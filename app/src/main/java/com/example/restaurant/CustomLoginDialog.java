package com.example.restaurant;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class CustomLoginDialog extends DialogFragment {
    private EditText emailText;
    private EditText passwordText;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.custom_login_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogCustom);
        builder.setTitle("Autentificare");
        builder.setView(view);

        emailText = view.findViewById(R.id.email);
        passwordText = view.findViewById(R.id.password);

        builder.setNegativeButton("Anulare", null);
        builder.setPositiveButton("Autentificare", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(emailText.getText().toString().contentEquals("admin"))
                {
                    Toast.makeText(getActivity(), "Corect!", Toast.LENGTH_SHORT).show();

                    Intent intent= new Intent(getContext(), Administrator.class);
                    startActivity(intent);
                }
                else
                {Toast.makeText(getActivity(), "Date invalide!", Toast.LENGTH_SHORT).show();
                return;}

            }
        });

        return builder.create();

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Toast.makeText(getActivity(), "Logare anulata!", Toast.LENGTH_SHORT).show();
    }
}
