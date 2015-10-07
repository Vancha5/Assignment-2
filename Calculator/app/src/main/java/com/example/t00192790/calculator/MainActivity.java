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


public class MainActivity extends AppCompatActivity {

    TextView tvmain;
   //button num_1;
    double value = 0;
    double result = 0;
    String op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // num_1 = (Button)findViewById(R.id.button19);
        //num_1.setOnClickListener(num_handler);
        tvmain = (TextView) findViewById(R.id.textViewmain);

    }

        public void onClick(View v) {
            Toast.makeText(getBaseContext(), "Number Handler", Toast.LENGTH_SHORT).show();
            Button b1 = (Button) v;
            tvmain.append(b1.getText());
        }

        public void opOnClick(View v) {
            Button b2 = (Button) v;
            op = b2.getText().toString();
            if(value == 0) {
                value = Double.parseDouble(tvmain.getText().toString());
                tvmain.setText("");
            }
            else {
                value = calc();

            }



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
            String str = tvmain.getText().toString();
            if (str.length() > 0) {
                str = str.substring(0, str.length()-1);
            }
            tvmain.setText(str);
        }

        public void sign(View v)
        {
            if(Double.parseDouble(tvmain.getText().toString()) > 0)
                tvmain.setText("-" + tvmain.getText());

        }
        public void calcButton(){
            double test = calc();
        }


        public double calc(){

            if(op.equals("+"))
            {
                result = value +  Double.parseDouble(tvmain.getText().toString());
            }
            else if(op.equals("-"))
            {
                result = value - Double.parseDouble(tvmain.getText().toString());
            }
            else if(op.equals("*"))
            {
                result = value * Double.parseDouble(tvmain.getText().toString());
            }
            else if(op.equals("/"))
            {
                result = value / Double.parseDouble(tvmain.getText().toString());
            }

            tvmain.setText(String.valueOf(result));
            return result;


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
