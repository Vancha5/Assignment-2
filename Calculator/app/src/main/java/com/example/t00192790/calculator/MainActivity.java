package com.example.t00192790.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOError;
import java.io.IOException;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    TextView tvmain;
    TextView tvhistory;
   //button num_1;
    double value;
    double result;
    String op;
    String str;
    double memvalue;
    String[] history;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // num_1 = (Button)findViewById(R.id.button19);
        //num_1.setOnClickListener(num_handler);
        tvmain = (TextView) findViewById(R.id.textViewmain);
        tvhistory = (TextView) findViewById(R.id.tvhistory);
        tvmain.setText("");
        value = 0;
        result = 0;
        op = "";
        str = tvmain.getText().toString();
        memvalue = 0;
        history = new String[100];
        count = 0;


    }

        public void onClick(View v) {
            Button b1 = (Button) v;
            str = tvmain.getText().toString();
            if (str.contains(".")) { //checks if the string contains a decimal, if it does 0's are ok
               // if (Double.parseDouble(tvmain.getText().toString()) == 0)
                if(!b1.getText().toString().equals("."))
                    tvmain.append(b1.getText());
            }
            else if(str != null && !str.isEmpty() && !str.equals("-")) {
                if (Double.parseDouble(str) == 0) //checks if the value is equal to zero, if it is I need an if to not allow zeros
                {
                    if (b1.getText().toString().equals("0"))
                        return;
                    else
                        tvmain.append(b1.getText());
                }
                else
                    tvmain.append(b1.getText());
            }
            else //if no decimal and value is not zero then just append, can also be empty
                tvmain.append(b1.getText());
        }

        public void memadd(View v){
            str = tvmain.getText().toString();
            if(str != null && !str.isEmpty()) {
                memvalue = memvalue + Double.parseDouble(str);
                //Toast.makeText(getApplicationContext(), String.valueOf(memvalue), Toast.LENGTH_SHORT).show();
            }
        }

        public void memsub(View v){
            str = tvmain.getText().toString();
            if(str != null && !str.isEmpty())
                memvalue =  memvalue - Double.parseDouble(str);
        }

        public void memclear(View v){
            memvalue = 0;
        }

        public void memrecall(View v){
            Toast.makeText(getApplicationContext(), String.valueOf(memvalue), Toast.LENGTH_SHORT).show();
            tvmain.setText(String.valueOf(memvalue));
        }




        public void opOnClick(View v) {
            Button b2 = (Button) v;
            str = tvmain.getText().toString();
            history[count] = str;
            count++;
            if (!str.isEmpty() && !str.equals(null)) {

                op = b2.getText().toString();
                history[count] = op;
                count++;
                if (value == 0) { //if the string empty then get the value and set the text to empty string
                    value = Double.parseDouble(str);
                    result = value;
                    tvmain.setText("");
                    Toast.makeText(getApplicationContext(), "set value", Toast.LENGTH_SHORT).show();
                } //else if (value == Double.parseDouble(str)) { //if the value is not zero then call the calc method, calc sets the text to the result of calc
                    //tvmain.setText("");
                //}
                 else {
                    result = calc();
                    if (result != 0) {
                        //tvmain.setText(String.valueOf(result));
                        tvmain.setText("");
                        Toast.makeText(getApplicationContext(), "result = calc()", Toast.LENGTH_SHORT).show();
                    } else {
                        tvmain.setText("");
                        Toast.makeText(getApplicationContext(), "opOnClick value not empty op set to empty string", Toast.LENGTH_SHORT).show();

                    }


                }
            }
            tvhistory.setText(Arrays.toString(history));
        }





        public void clear(View v){
            tvmain.setText("");
            value=0;
            op="";
        }
        public void partClear(View v){
            tvmain.setText("");
        }
        public void del(View v)
        {
             String tempString = tvmain.getText().toString();

            if(tempString.equals(".") || tempString.equals("-")) {
                tempString = "";

            }

            else if (tempString.length() > 0) {

                tempString = tempString.substring(0, tempString.length()-1);
                history[count] = "";
                count--;

               // Toast.makeText(getApplicationContext(), "str else if", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "ignored if statements", Toast.LENGTH_SHORT).show();

            }
            tvmain.setText(tempString);
            tvhistory.setText(Arrays.toString(history));

        }

        public void sign(View v)
        {
            str = tvmain.getText().toString();
            String tempString = "";
            if(!str.isEmpty() && str != null && !(str.contains("-"))) {
                if (Double.parseDouble(str) > 0 && (str.length() > 0))
                    tvmain.setText("-" + str);
            }
            else if(!str.isEmpty() && str != null && str.contains("-"))
            {
                tempString = str.substring(1);
                tvmain.setText(tempString);
            }
            else if(str.equals("-")){
                tempString = "";
                tvmain.setText(tempString);
            }
            else{
                tempString = "-";
                tvmain.setText(tempString);

            }
        }
        public void calcButton(View v){
             result = calc();
            if(result != 0)
             tvmain.setText(String.valueOf(result));
            else
                tvmain.setText("");
        }


        public double calc(){
            str = tvmain.getText().toString();
            if(!str.isEmpty() && !str.equals(null) && !op.equals("")){
                Toast.makeText(getApplicationContext(), "value is" + value + ".", Toast.LENGTH_SHORT).show();
            if (op.equals("+")) {
                result = result + value;
            } else if (op.equals("-")) {
                result = result - value;
            } else if (op.equals("*")) {
                result = result * value;
            } else if (op.equals("/")) {
                result = result / value;
            }
              /*  if (op.equals("+")) {
                    result = value + Double.parseDouble(str);
                } else if (op.equals("-")) {
                    result = value - Double.parseDouble(str);
                } else if (op.equals("*")) {
                    result = value * Double.parseDouble(str);
                } else if (op.equals("/")) {
                    result = value / Double.parseDouble(str);
                }    */


          //  tvmain.setText(String.valueOf(result));
                history[count] = op;
                count++;
                history[count] = String.valueOf(value);
                count++;
                tvhistory.setText(history.toString());
                return result;

        }
            else if(!str.isEmpty() && !str.equals(null)) {

            result = Double.parseDouble(str);
            return result;
        }
            else
                return 0;
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
