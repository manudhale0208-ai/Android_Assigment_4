package com.example.assigment4;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddContactDialog extends DialogFragment {

    private EditText etName, etPhone;
    private OnContactAddedListener listener;

    // Interface Callback
    public interface OnContactAddedListener {
        void onContactAdded(String name, String phone);
    }

    public AddContactDialog(OnContactAddedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_layout, null);

        etName = view.findViewById(R.id.edtViewName);
        etPhone = view.findViewById(R.id.edtViewPhoneno);

        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnCancel = view.findViewById(R.id.btnCancel);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(requireActivity());

        builder.setView(view);

        AlertDialog dialog = builder.create();

        btnSave.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(getActivity(),
                        "Enter Name",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getActivity(),
                    "Name = " + name,
                    Toast.LENGTH_SHORT).show();


            if (!name.matches("^[A-Za-z ]+$")) {
                Toast.makeText(getActivity(),
                        "Enter Only Letters",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if(phone.length() != 10){
                Toast.makeText(getActivity(),
                        "Enter valid phone number",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            listener.onContactAdded(name, phone);

            dismiss();
        });

        btnCancel.setOnClickListener(v -> dismiss());

        return dialog;
    }
}
