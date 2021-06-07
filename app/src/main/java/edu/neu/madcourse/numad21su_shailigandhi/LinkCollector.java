package edu.neu.madcourse.numad21su_shailigandhi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity{

    private RviewAdapter rAdapter;
    private RecyclerView rView;
    private RecyclerView.LayoutManager recyclerLayoutManager;
    private FloatingActionButton addBtn;
    private ArrayList<ItemCard> itemCardArrayList = new ArrayList<>();
    private EditText name;
    private EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        createRecyclerView();

        addBtn = findViewById(R.id.addButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertDialogBox(v, itemCardArrayList.size());
            }
        });


    }

    private void openAlertDialogBox(View view, int pos)
    {

        final View alertDialogBox = getLayoutInflater().inflate(R.layout.alert_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        AlertDialog dialog = builder.setTitle("New Application URL").setView(alertDialogBox).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                name = alertDialogBox.findViewById(R.id.name);
                                url = alertDialogBox.findViewById(R.id.url);
                                createItem(pos, name.getText().toString(), url.getText().toString());
                            }
                        }).create();
        dialog.show();
    }

    private void createItem(int pos, String name, String url)
    {
        if (name.isEmpty() || url.isEmpty()) {
            Snackbar.make(rView, "Please fill all the details", Snackbar.LENGTH_SHORT).show();
        }
        else {
            if (URLUtil.isValidUrl(url)){
                addItem(pos, name, url);
            }
            else {
                Snackbar.make(rView, "Please enter valid URL", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private void createRecyclerView() {

        recyclerLayoutManager = new LinearLayoutManager(this);

        rView = findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);

        rAdapter = new RviewAdapter(itemCardArrayList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Uri webLink = Uri.parse(itemCardArrayList.get(position).getItemLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, webLink);
                startActivity(intent);
            }
        };
        rAdapter.setOnItemClickListener(itemClickListener);

        rView.setAdapter(rAdapter);
        rView.setLayoutManager(recyclerLayoutManager);


    }

    private void addItem(int position, String name, String url) {
        itemCardArrayList.add(position, new ItemCard(R.drawable.ic_launcher_background, name, url));
        Snackbar.make(rView, "Added New Item Name and URL", Snackbar.LENGTH_LONG).show();
        rAdapter.notifyItemInserted(position);
    }
}