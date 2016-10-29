package c1.myapplication1;

import android.content.ContentValues;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {


    Button submitButton;
    Button resetButton;
    EditText eID;
    EditText nameField;
    RadioButton maleButton;
    RadioButton femaleButton;
    EditText emailField;
    EditText passField1;
    EditText passField2;

    Spinner spinner;
    CheckBox checkBox;

    ColorStateList oldcolors;

    //textViews
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    String [] idArray;
    Button rButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative);

        submitButton = (Button) findViewById(R.id.submitButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        eID= (EditText) findViewById(R.id.eID);
        spinner = (Spinner) findViewById(R.id.spinner);
        nameField = (EditText) findViewById(R.id.nameField);
        maleButton = (RadioButton) findViewById(R.id.maleButton);
        femaleButton = (RadioButton) findViewById(R.id.femaleButton);
        emailField = (EditText) findViewById(R.id.emailField);
        passField1 = (EditText) findViewById(R.id.passField1);
        passField2 = (EditText) findViewById(R.id.passField2);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        rButton = (Button) findViewById( R.id.rButton);

         idArray = getResources().getStringArray(R.array.empids);
         textView1 = (TextView) findViewById(R.id.textView);
         textView2 = (TextView) findViewById(R.id.textView2);
         textView3 = (TextView) findViewById(R.id.textView3);
         textView4 = (TextView) findViewById(R.id.textView4);
         textView5 = (TextView) findViewById(R.id.textView5);
         textView6 = (TextView) findViewById(R.id.textView6);
         textView7 = (TextView) findViewById(R.id.textView7);
         textView8 = (TextView) findViewById(R.id.textView8);

        oldcolors = textView1.getTextColors();
        nameField.setText("");

    }

    public void myEventHandler(View v)
    {

        if(v == submitButton)
        {

            StringBuilder errorMessage = new StringBuilder(100);
            errorMessage.append("ERRORS DETECTED!!!\n");
            int eFlag = 0;

            //Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI, null, MyContentProvider.COLUMN_EID + " = "
              //      + DatabaseUtils.sqlEscapeString(eID.toString()), null, null);

            Cursor mCursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null, MyContentProvider.COLUMN_EID + " = ?",  new String[] {eID.getText().toString()}, null);






            if( eID.getText().toString().trim().equals("") || mCursor.getCount() == 0)
            {
                errorMessage.append("Missing eID!\n");
                eFlag = 1;
                textView1.setTextColor(Color.parseColor("#ff0000"));

            }

            if (nameField.getText().toString().trim().equals(""))
            {

                errorMessage.append("Missing Name!\n");
                eFlag = 1;
                textView2.setTextColor(Color.parseColor("#ff0000"));
            }
            if (!((maleButton.isChecked())|| (femaleButton.isChecked()) ))
            {
                errorMessage.append("Missing Gender Selection!\n");
                eFlag = 1;
                textView3.setTextColor(Color.parseColor("#ff0000"));
            }
            if (emailField.getText().toString().trim().equals("") )
            {
                errorMessage.append("Missing Email Field!\n");
                eFlag = 1;
                textView4.setTextColor(Color.parseColor("#ff0000"));
            }

            Cursor Cursor1 = getContentResolver().query(MyContentProvider.CONTENT_URI, null, MyContentProvider.COLUMN_PASS + " = ?",  new String[] {passField1.getText().toString()}, null);

            String str1 = "";
            if(Cursor1.getCount() > 0)
            if( Cursor1.moveToFirst()){
                str1 = Cursor1.getString(Cursor1.getColumnIndex("passcode"));

            }
            if(str1.equals(""))
            {
                errorMessage.append("Password not in the database!\n");
                textView5.setTextColor(Color.parseColor("#ff0000"));
            }


            if (passField1.getText().toString().trim().equals("") )
            {
                errorMessage.append("Missing Password entry!\n");
                eFlag = 1;
                textView5.setTextColor(Color.parseColor("#ff0000"));
            }
            if (passField2.getText().toString().trim().equals("") )
            {
                errorMessage.append("Please confirm password!\n");
                eFlag = 1;
                textView6.setTextColor(Color.parseColor("#ff0000"));
            }
            if(!(passField1.getText().toString().equals(passField2.getText().toString())))
            {
                errorMessage.append("Passcodes are not the same!\n");
                eFlag = 1;
                textView6.setTextColor(Color.parseColor("#ff0000"));
                textView5.setTextColor(Color.parseColor("#ff0000"));

            }

            if (spinner.getSelectedItem().toString().equals ("Default"))
            {
                errorMessage.append("Please select something other than Default!\n");
                eFlag = 1;
                textView7.setTextColor(Color.parseColor("#ff0000"));
            }

            //All Fields are filled in
            if(eFlag == 0) {

                textView1.setTextColor(oldcolors);
                textView2.setTextColor(oldcolors);
                textView3.setTextColor(oldcolors);
                textView4.setTextColor(oldcolors);
                textView5.setTextColor(oldcolors);
                textView6.setTextColor(oldcolors);
                textView7.setTextColor(oldcolors);



                int sFlag = 1;

                if (sFlag == 1) {
                    Toast.makeText(this, "Submitted!", Toast.LENGTH_LONG).show();





                } else {

                    Toast.makeText(this, "User not found!", Toast.LENGTH_LONG).show();
                    textView1.setTextColor(Color.parseColor("#ff0000"));
                }


            }
            else
            {
                Toast.makeText(this, errorMessage.toString(), Toast.LENGTH_LONG).show();

            }


        }
        else if(v == resetButton)
        {
            nameField.setText("");
            eID.setText("");
            emailField.setText("");
            maleButton.setChecked(false);
            femaleButton.setChecked(false);
            passField1.setText("");
            passField2.setText("");
            spinner.setSelection(0);
            checkBox.setChecked(false);
            Toast.makeText(this, "Reset Fields!", Toast.LENGTH_LONG).show();


        }
        else if(v == rButton)
        {
            StringBuilder errorMessage = new StringBuilder(100);
            errorMessage.append("ERRORS DETECTED!!!\n");
            int eFlag = 0;




            Cursor mCursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null, MyContentProvider.COLUMN_EID + " = ?",  new String[] {eID.getText().toString()}, null);


            if( eID.getText().toString().trim().equals("") || mCursor.getCount() >= 1)
            {
                errorMessage.append("Missing eID/User already added!\n");
                eFlag = 1;
                textView1.setTextColor(Color.parseColor("#ff0000"));

            }

            if (nameField.getText().toString().trim().equals(""))
            {

                errorMessage.append("Missing Name!\n");
                eFlag = 1;
                textView2.setTextColor(Color.parseColor("#ff0000"));
            }
            if (!((maleButton.isChecked())|| (femaleButton.isChecked()) ))
            {
                errorMessage.append("Missing Gender Selection!\n");
                eFlag = 1;
                textView3.setTextColor(Color.parseColor("#ff0000"));
            }
            if (emailField.getText().toString().trim().equals("") )
            {
                errorMessage.append("Missing Email Field!\n");
                eFlag = 1;
                textView4.setTextColor(Color.parseColor("#ff0000"));
            }
            if (passField1.getText().toString().trim().equals("") )
            {
                errorMessage.append("Missing Password entry!\n");
                eFlag = 1;
                textView5.setTextColor(Color.parseColor("#ff0000"));
            }
            if (passField2.getText().toString().trim().equals("") )
            {
                errorMessage.append("Please confirm password!\n");
                eFlag = 1;
                textView6.setTextColor(Color.parseColor("#ff0000"));
            }
            if(!(passField1.getText().toString().equals(passField2.getText().toString())))
            {
                errorMessage.append("Passcodes are not the same!\n");
                eFlag = 1;
                textView6.setTextColor(Color.parseColor("#ff0000"));
                textView5.setTextColor(Color.parseColor("#ff0000"));

            }

            if (spinner.getSelectedItem().toString().equals ("Default"))
            {
                errorMessage.append("Please select something other than Default!\n");
                eFlag = 1;
                textView7.setTextColor(Color.parseColor("#ff0000"));
            }

            //All Fields are filled in
            if(eFlag == 0) {

                textView1.setTextColor(oldcolors);
                textView2.setTextColor(oldcolors);
                textView3.setTextColor(oldcolors);
                textView4.setTextColor(oldcolors);
                textView5.setTextColor(oldcolors);
                textView6.setTextColor(oldcolors);
                textView7.setTextColor(oldcolors);



                int sFlag = 1;

                if (sFlag == 1) {
                    Toast.makeText(this, "Submitted!", Toast.LENGTH_LONG).show();
                    Uri mNewUri;

                    ContentValues mNewValues = new ContentValues();


                    mNewValues.put(MyContentProvider.COLUMN_EID, eID.getText().toString().trim());
                    mNewValues.put(MyContentProvider.COLUMN_NAME, nameField.getText().toString().trim());
                    if(maleButton.isChecked())
                        mNewValues.put(MyContentProvider.COLUMN_GENDER, "male");
                    else
                        mNewValues.put(MyContentProvider.COLUMN_GENDER, "female");
                    mNewValues.put(MyContentProvider.COLUMN_EMAIL,emailField.getText().toString().trim());
                    mNewValues.put(MyContentProvider.COLUMN_PASS,passField1.getText().toString().trim());
                    mNewValues.put(MyContentProvider.COLUMN_DEPARTMENT,spinner.getSelectedItem().toString());

                    if(checkBox.isChecked())
                    mNewValues.put(MyContentProvider.COLUMN_ACCESS,"yes");
                    else
                        mNewValues.put(MyContentProvider.COLUMN_ACCESS,"no");
                    mNewUri = getContentResolver().insert(
                            MyContentProvider.CONTENT_URI, mNewValues);





                } else {

                    Toast.makeText(this, "User not found!", Toast.LENGTH_LONG).show();
                    textView1.setTextColor(Color.parseColor("#ff0000"));
                }


            }
            else
            {
                Toast.makeText(this, errorMessage.toString(), Toast.LENGTH_LONG).show();

            }


        }


    }

}

