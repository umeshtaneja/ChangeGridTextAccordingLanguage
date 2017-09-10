package com.example.sagoo.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    GridView grid;
    TextView tv1;

    String[] web = {"one","two","three","four","five","six"};
    int[] images = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,
            R.drawable.image5,R.drawable.image6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Field[] fields = R.string.class.getFields();
        String[] allStringNames = new String[fields.length];

        for (int  i =0; i < fields.length; i++) {
            allStringNames[i] = fields[i].getName();
            /*int resId = getResources().getIdentifier(allStringNames[i], "string", getPackageName());
            allStringValues[i] = getString(resId);*/
            }

//allStringNames contains all the stirng names in the string file...

        Collection listOne = new ArrayList(Arrays.asList(web));
        Collection listTwo = new ArrayList(Arrays.asList(allStringNames));
        listOne.retainAll(listTwo );

// listOne contains the common string names in string.xml and web array...

        Object[] obj = listOne.toArray();
        String[] allStringValues = new String[obj.length];
        for(int i=0; i<obj.length;i++) {
            allStringValues[i] = (String)obj[i];
        }

// allStringValues contains an array of all matched values from string.xml and web array in array form...
        Log.d("oncreate: ", Arrays.toString(allStringValues));


        String[] matchedValues = new String[allStringValues.length];
        for (int  i =0; i < allStringValues.length; i++) {
            int resId = getResources().getIdentifier(allStringValues[i], "string", getPackageName());
            matchedValues[i] = getString(resId);
        }

// matchedValues contains the values of string names to be passed in adapter...
        Log.d("oncreateeee: ", Arrays.toString(matchedValues));


        Adapter adapter = new Adapter(MainActivity.this, matchedValues, images);
        grid=(GridView)findViewById(R.id.grid);
        tv1 = (TextView)findViewById(R.id.text);
        grid.setAdapter(adapter);
         }
    }
