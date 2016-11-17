package client.twilio.com.quickstart;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by tconnors on 3/4/16.
 */
public class Dialog {

    public static AlertDialog createRegisterDialog(DialogInterface.OnClickListener updateTokenClickListener, DialogInterface.OnClickListener cancelClickListener,
                                                   ClientActivity.ClientProfile clientProfile, Context context){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setIcon(R.drawable.ic_update_black_24dp);
        alertDialogBuilder.setTitle("Register Client");
        alertDialogBuilder.setPositiveButton("Register", updateTokenClickListener);
        alertDialogBuilder.setNegativeButton("Cancel", cancelClickListener);
        alertDialogBuilder.setCancelable(false);

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.dialog_registration, null);

        EditText clientNameEditText = (EditText) dialogView.findViewById(R.id.client_name_edittext);
        clientNameEditText.setText(clientProfile.getName());
        clientNameEditText.setSelection(clientNameEditText.getText().length());

        CheckBox allowOutgoingCxBx = (CheckBox) dialogView.findViewById(R.id.outgoing_checkbox);
        allowOutgoingCxBx.setChecked(clientProfile.isAllowOutgoing());

        CheckBox allowIncomingCxBx = (CheckBox) dialogView.findViewById(R.id.incoming_checkbox);
        allowIncomingCxBx.setChecked(clientProfile.isAllowIncoming());

        alertDialogBuilder.setView(dialogView);

        return alertDialogBuilder.create();
    }

    public static AlertDialog createCallDialog(DialogInterface.OnClickListener callClickListener, DialogInterface.OnClickListener cancelClickListener, final Context context){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setIcon(R.drawable.ic_call_black_24dp);
        alertDialogBuilder.setTitle("Call");
        alertDialogBuilder.setPositiveButton("Call", callClickListener);
        alertDialogBuilder.setNegativeButton("Cancel", cancelClickListener);
        alertDialogBuilder.setCancelable(false);

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.dialog_call, null);
        Spinner typeSpinner = (Spinner) dialogView.findViewById(R.id.typeSpinner);
        final EditText contact = (EditText) dialogView.findViewById(R.id.contact);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if( position == 0 ){
                    contact.setInputType(InputType.TYPE_CLASS_TEXT);
                    contact.setHint(R.string.client_name);
                } else {
                    contact.setInputType(InputType.TYPE_CLASS_PHONE);
                    contact.setHint(R.string.phone_number);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        alertDialogBuilder.setView(dialogView);

        return alertDialogBuilder.create();

    }

    public static AlertDialog createIncomingCallDialog(DialogInterface.OnClickListener answerCallClickListener, DialogInterface.OnClickListener cancelClickListener, Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setIcon(R.drawable.ic_call_black_24dp);
        alertDialogBuilder.setTitle("Incoming Call");
        alertDialogBuilder.setPositiveButton("Accept", answerCallClickListener);
        alertDialogBuilder.setNegativeButton("Reject", cancelClickListener);
        alertDialogBuilder.setMessage("Incoming call");

        return alertDialogBuilder.create();
    }
}
