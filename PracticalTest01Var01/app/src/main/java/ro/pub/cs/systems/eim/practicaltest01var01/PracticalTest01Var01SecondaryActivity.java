package ro.pub.cs.systems.eim.practicaltest01var01;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var01SecondaryActivity extends AppCompatActivity {

    private TextView count = null;
    private Button register = null;
    private Button cancel = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.register:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_secondary);

        count = (TextView)findViewById(R.id.secondaryText);
        Intent intent = getIntent();

        if (intent != null && intent.getExtras().containsKey("count")) {
            int numberOfClicks = intent.getIntExtra("count", -1);
            count.setText("Previous buttons were clicked " + String.valueOf(numberOfClicks) + " times.");
        }

        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(buttonClickListener);

        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(buttonClickListener);
    }

}
