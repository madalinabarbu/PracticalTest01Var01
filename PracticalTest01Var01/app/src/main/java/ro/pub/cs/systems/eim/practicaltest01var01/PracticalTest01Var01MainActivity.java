package ro.pub.cs.systems.eim.practicaltest01var01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var01MainActivity extends AppCompatActivity {
    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    private Button north = null;
    private Button south = null;
    private Button east = null;
    private Button west = null;

    private Button navigateToSecondaryActivityButton = null;

    private Integer count = 0;

    private EditText editText = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String initText = editText.getText().toString();
            if (!initText.equals(""))
                initText=initText+",";
            switch(view.getId()) {
                case R.id.north:
                    editText.setText(initText+"North");
                    count++;
                    break;
                case R.id.south:
                    editText.setText(initText+"South");
                    count++;
                    break;
                case R.id.west:
                    editText.setText(initText+"West");
                    count++;
                    break;
                case R.id.east:
                    editText.setText(initText+"East");
                    count++;
                    break;
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01SecondaryActivity.class);
                    int crtCount = count;
                    String crtText = editText.getText().toString();
                    intent.putExtra("count", crtCount);
                    intent.putExtra("text", crtText);

                    count = 0;
                    editText.setText("");

                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_main);

        north = (Button)findViewById(R.id.north);
        east = (Button)findViewById(R.id.east);
        west = (Button)findViewById(R.id.west);
        south = (Button)findViewById(R.id.south);

        editText = (EditText)findViewById(R.id.edit_text);
        editText.setText("");

        north.setOnClickListener(buttonClickListener);
        south.setOnClickListener(buttonClickListener);
        east.setOnClickListener(buttonClickListener);
        west.setOnClickListener(buttonClickListener);

        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("count", Integer.toString(count));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("count")) {
            System.out.print(savedInstanceState.get("count"));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            String text = "Cancel";

            if (resultCode != 0 )
                text = "Register";

            Toast.makeText(this, text + " was pushed.", Toast.LENGTH_LONG).show();
        }
    }
}
