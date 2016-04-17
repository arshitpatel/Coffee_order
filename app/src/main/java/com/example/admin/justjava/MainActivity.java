package com.example.admin.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity = 1;
   int ammount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // for display
    private void display(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_number);
        quantityTextView.setText("" + number);
    }

    //for increment
    public void plus(View view) {
       if (quantity == 20) {
           Toast.makeText(MainActivity.this, "You can not have more then 20 Coffees", Toast.LENGTH_SHORT).show();
      } else {
            quantity = quantity + 1;
            display(quantity);
        }
    }

    // for decrement
    public void minus(View view) {
       if (quantity == 1) {
            Toast.makeText(MainActivity.this, "You can not have less then 1 Coffee", Toast.LENGTH_SHORT).show();
        } else {
            quantity = quantity - 1;
            display(quantity);
        }
    }

   public void submit_oreder(View view) {

        EditText name_field = (EditText) findViewById(R.id.edit_text);
        String name = name_field.getText().toString();

        CheckBox whippedcream = (CheckBox) findViewById(R.id.whipped_cream);
       boolean haswhpped_Cream =  whippedcream.isChecked();

        CheckBox chocolate_cf = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate =  chocolate_cf.isChecked();

        int price = calculate(haswhpped_Cream, hasChocolate);
       String summry = Order_summary(name, haswhpped_Cream, hasChocolate, price);
       Intent intent = new Intent(Intent.ACTION_SENDTO);
       intent.setData(Uri.parse("mailto:")); // only email apps should handle this

      // intent.putExtra(Intent.EXTRA_EMAIL, "arshit.patel16@gmail.com");
       intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee oreder from " + name );
       intent.putExtra(Intent.EXTRA_TEXT, summry);
       if (intent.resolveActivity(getPackageManager()) != null) {
           startActivity(intent);
       }


   }
        public String Order_summary(String name , boolean wapped , boolean chok , int price){
            String extra = "Simple Coffee";
            if(wapped==true && chok==true)
                 extra = "Add both Whipped Cream and Chocalate";
            else if(wapped==true)
                extra = "Add  Whipped Cream";
            else if(chok==true)
                extra= "Add Chocolate";

            String nm;
            nm = " Name : " + name + "\n" + extra  + " \n Quantity : "+ quantity + "\n Total : $" + ammount + "\n Thank You " + name + "!";
            return nm;
        }
    public int calculate( boolean haswhhped , boolean haschocolate ){


        int charge=5;
        if(haswhhped==true)
           charge= charge + 1;
        if(haschocolate==true)
            charge=charge + 2;
        ammount=charge*quantity;

            return ammount;
        //String nm = "Name : " + name + "\n Add Whipped cream ? " + haswhhped + "\n Add Chocolate ? " + haschocolate + " \n Quantity : "+ quantity + "\n Total : $" + ammount + "\n Thank You!";


    }


}
