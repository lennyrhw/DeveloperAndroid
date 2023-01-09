package poliban.ac.id.e020320041.shoppingchallengemaps;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import poliban.ac.id.e020320041.shoppingchallenge.R;


public class MainActivityLenny extends AppCompatActivity {
    public static final int ITEM_REQUEST = 1;
    private final TextView[] item = new TextView[9];
    private ArrayList<String> itemsList = new ArrayList<>(9);
    private EditText mStoreEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Shopping Challenge - LennyR");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        item[0] = findViewById(R.id.text1);
        item[1] = findViewById(R.id.text2);
        item[2] = findViewById(R.id.text3);
        item[3] = findViewById(R.id.text4);
        item[4] = findViewById(R.id.text5);
        item[5] = findViewById(R.id.text6);
        item[6] = findViewById(R.id.text7);
        item[7] = findViewById(R.id.text8);
        item[8] = findViewById(R.id.text9);
        mStoreEditText = findViewById(R.id.map_editText);

        if (savedInstanceState != null) {
            itemsList = savedInstanceState.getStringArrayList("ItemsListStringArray");
            int i;
            if (itemsList != null && itemsList.size() > 0) {
                for (i = 0; i < itemsList.size(); i++) {
                    if (itemsList.size() > 9) {
                        Toast.makeText(MainActivityLenny.this, "Cannot add more items to the list",
                                Toast.LENGTH_LONG).show();
                        break;
                    }
                    item[i].setVisibility(View.VISIBLE);
                    item[i].setText(itemsList.get(i));
                }
            }
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (itemsList.size() != 0) {
            outState.putStringArrayList("ItemsListStringArray", itemsList);
        }
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, SecondLenny.class);
        startActivityForResult(intent, ITEM_REQUEST);
    }

    // method for when SecondActivity returns intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                String itemString = data.getStringExtra(SecondLenny.EXTRA_ITEMS);
                itemsList.add(itemString);
                int i;
                for (i = 0; i < itemsList.size(); i++) {
                    if (itemsList.size() > 9) {
                        Toast.makeText(MainActivityLenny.this,
                                "Cannot add more items to the list",
                                Toast.LENGTH_LONG).show();
                        break;
                    }
                    item[i].setVisibility(View.VISIBLE);
                    item[i].setText(itemsList.get(i));
                }

            }
        }
    }

    public void searchStores(View view) {
        String location = mStoreEditText.getText().toString();
        String url = null;
        try {
            url = URLEncoder.encode(location, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Uri address = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + url);
        Intent intent = new Intent(Intent.ACTION_VIEW, address);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(MainActivityLenny.this,
                    "There was a problem with the store search.", Toast.LENGTH_LONG).show();
        }
    }
}