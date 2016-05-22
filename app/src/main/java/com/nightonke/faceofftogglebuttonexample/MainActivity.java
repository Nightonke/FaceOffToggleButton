package com.nightonke.faceofftogglebuttonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nightonke.faceofftogglebutton.FaceOffToggleButton;
import com.nightonke.faceofftogglebutton.State;

public class MainActivity extends AppCompatActivity
        implements
        ColorChooseDialog.OnColorChoseListener,
        View.OnClickListener {

    private FaceOffToggleButton fotb0;
    private FaceOffToggleButton fotb1;
    private FaceOffToggleButton fotb2;

    private Toast lastToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.app_name);

        fotb0 = findView(R.id.fotb_0);
        fotb1 = findView(R.id.fotb_1);
        fotb2 = findView(R.id.fotb_2);

        fotb0.setOnStateChangeListener(new FaceOffToggleButton.OnStateChangeListener() {
            @Override
            public void onStateChange(float process, State state, FaceOffToggleButton fotb) {
                if (state.equals(State.LEFT)) {
                    if (lastToast != null) lastToast.cancel();
                    lastToast = Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT);
                    lastToast.show();
                }
                if (state.equals(State.RIGHT)) {
                    if (lastToast != null) lastToast.cancel();
                    lastToast = Toast.makeText(MainActivity.this, "Right!", Toast.LENGTH_SHORT);
                    lastToast.show();
                }
            }
        });


    }

    private <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void ok(int color, int tag) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void onClick(View v) {

    }
}
