package hello.itay.com.test2503;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameET, passwordET;

    public static final String USER_DETAILS = "profile";
    public static final String USER_DETAILS_NAME = "USER_DETAILS_NAME";
    public static final String USER_DETAILS_PWD = "USER_DETAILS_PWD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Float button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // snack bat
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        // action from snack bar
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getBaseContext(), "aaaaaaaaa", Toast.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });

        // Read the preferences

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = prefs.getString("username", "Default NickName");
        boolean updates = prefs.getBoolean("applicationUpdates", false);
        String st = prefs.getString("downloadDetials", "1");
        int downloadDetialsNumber = 1;
        try {
            //int downloadDetialsNumber = prefs.getInt("downloadDetials", 1);
            downloadDetialsNumber = Integer.parseInt(st);
        }
        catch (Exception ex)
        {
            Toast.makeText(getBaseContext(), "Cannot convert " + st + " to int", Toast.LENGTH_SHORT).show();
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Username: " + username + "\n");
        builder.append("Updates: " + updates+ "\n");
        builder.append("Download details: " + getResources().getStringArray(R.array.listArray)
                [downloadDetialsNumber - 1]);
        Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();

        nameET = findViewById(R.id.nameET);
        passwordET = findViewById(R.id.passwordET);

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Editor - to update the data in the shared pref.
                SharedPreferences.Editor editor = getSharedPreferences(USER_DETAILS,
                        MODE_PRIVATE).edit();
                String name = nameET.getText().toString();
                String pwd = passwordET.getText().toString();
                editor.putString(USER_DETAILS_NAME, name);
                editor.putString(USER_DETAILS_PWD, pwd);
                editor.apply();
                Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.loadBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = getSharedPreferences(USER_DETAILS,
                        MODE_PRIVATE);
                String name = prefs.getString(USER_DETAILS_NAME, "");
                String pwd = prefs.getString(USER_DETAILS_PWD, "");
                if (name.equals("") && pwd.equals(""))
                {
                    Toast.makeText(getBaseContext(), "Nothing to load...", Toast.LENGTH_SHORT).show();
                    return;
                }
                nameET.setText(name);
                passwordET.setText(pwd);
                Toast.makeText(getBaseContext(), "Loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // drae the menu ... dots
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // handle menu item clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            // (1) click setting - toast

            //Toast.makeText(getBaseContext(), "clicked settings",
            //   Toast.LENGTH_SHORT).show();

            // (2) click settings - open pref activity

            Intent gotoSettings = new Intent(this, MyPreferencesActivity.class);
            startActivity(gotoSettings);

            return true;
        }
        if (id == R.id.action_settings_login) {
            Toast.makeText(getBaseContext(), "clicked login",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
