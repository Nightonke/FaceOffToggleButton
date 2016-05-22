package com.nightonke.faceofftogglebuttonexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.faceofftogglebutton.ColorChangeType;
import com.nightonke.faceofftogglebutton.FaceOffToggleButton;
import com.nightonke.faceofftogglebutton.State;

public class MainActivity extends AppCompatActivity
        implements
        ColorChooseDialog.OnColorChoseListener,
        View.OnClickListener {

    private FaceOffToggleButton fotb0;
    private FaceOffToggleButton fotb1;
    private FaceOffToggleButton fotb2;

    private ProgressBar progressBar;
    private SeekBar seekBar;
    private RadioGroup radioGroup;

    private Toast lastToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.app_title);

        fotb0 = findView(R.id.fotb_0);
        fotb1 = findView(R.id.fotb_1);
        fotb2 = findView(R.id.fotb_2);
        progressBar = findView(R.id.progress);
        findView(R.id.set_check_with_listener).setOnClickListener(this);
        findView(R.id.set_check_without_listener).setOnClickListener(this);
        findView(R.id.set_check_immediately_with_listener).setOnClickListener(this);
        findView(R.id.set_check_immediately_without_listener).setOnClickListener(this);
        findView(R.id.toggle_with_listener).setOnClickListener(this);
        findView(R.id.toggle_without_listener).setOnClickListener(this);
        findView(R.id.toggle_immediately_with_listener).setOnClickListener(this);
        findView(R.id.toggle_immediately_without_listener).setOnClickListener(this);
        findView(R.id.background_color).setOnClickListener(this);
        findView(R.id.left_background_color).setOnClickListener(this);
        findView(R.id.right_background_color).setOnClickListener(this);
        findView(R.id.left_face_color).setOnClickListener(this);
        findView(R.id.right_face_color).setOnClickListener(this);
        findView(R.id.left_eye_color).setOnClickListener(this);
        findView(R.id.right_eye_color).setOnClickListener(this);
        findView(R.id.left_mouth_color).setOnClickListener(this);
        findView(R.id.right_mouth_color).setOnClickListener(this);
        findView(R.id.draggable).setOnClickListener(this);

        seekBar = findView(R.id.duration);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int duration = 500 + progress * 500;
                    ((TextView)findView(R.id.duration_text)).setText(duration + "ms");
                    fotb0.setDuration(duration);
                    fotb1.setDuration(duration);
                    fotb2.setDuration(duration);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        radioGroup = findView(R.id.color_change_type);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.color_change_type_hsv:
                        fotb0.setColorChangeType(ColorChangeType.HSV);
                        fotb1.setColorChangeType(ColorChangeType.HSV);
                        fotb2.setColorChangeType(ColorChangeType.HSV);
                        break;
                    case R.id.color_change_type_rgb:
                        fotb0.setColorChangeType(ColorChangeType.RGB);
                        fotb1.setColorChangeType(ColorChangeType.RGB);
                        fotb2.setColorChangeType(ColorChangeType.RGB);
                        break;
                }
            }
        });

        fotb0.setOnStateChangeListener(new FaceOffToggleButton.OnStateChangeListener() {
            @Override
            public void onStateChange(float process, State state, FaceOffToggleButton fotb) {
                progressBar.setProgress((int) (100 * process));
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
        switch (tag) {
            case R.id.background_color:
                fotb0.setBackgroundColor(color);
                break;
            case R.id.left_background_color:
                fotb0.setLeftBackgroundColor(color);
                break;
            case R.id.right_background_color:
                fotb0.setRightBackgroundColor(color);
                break;
            case R.id.left_face_color:
                fotb0.setLeftFaceColor(color);
                break;
            case R.id.right_face_color:
                fotb0.setRightFaceColor(color);
                break;
            case R.id.left_eye_color:
                fotb0.setLeftEyeColor(color);
                break;
            case R.id.right_eye_color:
                fotb0.setRightEyeColor(color);
                break;
            case R.id.left_mouth_color:
                fotb0.setLeftMouthColor(color);
                break;
            case R.id.right_mouth_color:
                fotb0.setRightMouthColor(color);
                break;
        }
    }

    @Override
    public void cancel() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_check_with_listener:
                fotb0.setChecked(!fotb0.isChecked());
                break;
            case R.id.set_check_without_listener:
                fotb0.setChecked(!fotb0.isChecked(), false);
                break;
            case R.id.set_check_immediately_with_listener:
                fotb0.setCheckedImmediately(!fotb0.isChecked());
                break;
            case R.id.set_check_immediately_without_listener:
                fotb0.setCheckedImmediately(!fotb0.isChecked(), false);
                break;
            case R.id.toggle_with_listener:
                fotb0.toggle();
                break;
            case R.id.toggle_without_listener:
                fotb0.toggle(false);
                break;
            case R.id.toggle_immediately_with_listener:
                fotb0.toggleImmediately();
                break;
            case R.id.toggle_immediately_without_listener:
                fotb0.toggleImmediately(false);
                break;
            case R.id.background_color:
                setColor("Background", fotb0.getBackgroundColor(), R.id.background_color);
                break;
            case R.id.left_background_color:
                setColor("Left Background", fotb0.getLeftBackgroundColor(), R.id.left_background_color);
                break;
            case R.id.right_background_color:
                setColor("Right Background", fotb0.getRightBackgroundColor(), R.id.right_background_color);
                break;
            case R.id.left_face_color:
                setColor("Left Face", fotb0.getLeftFaceColor(), R.id.left_face_color);
                break;
            case R.id.right_face_color:
                setColor("Right Face", fotb0.getRightFaceColor(), R.id.right_face_color);
                break;
            case R.id.left_eye_color:
                setColor("Left Eye", fotb0.getLeftEyeColor(), R.id.left_eye_color);
                break;
            case R.id.right_eye_color:
                setColor("Right Eye", fotb0.getRightEyeColor(), R.id.right_eye_color);
                break;
            case R.id.left_mouth_color:
                setColor("Left Mouth", fotb0.getLeftMouthColor(), R.id.left_mouth_color);
                break;
            case R.id.right_mouth_color:
                setColor("Right Mouth", fotb0.getRightMouthColor(), R.id.right_mouth_color);
                break;
            case R.id.draggable:
                fotb0.setDraggable(!fotb0.isDraggable());
                fotb1.setDraggable(!fotb1.isDraggable());
                fotb2.setDraggable(!fotb2.isDraggable());
                break;
        }
    }

    private void setColor(String title, int color, int tag) {
        new ColorChooseDialog.Builder(this)
                .title(title)
                .color(color)
                .tag(tag)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_github:
                goWeb("https://github.com/Nightonke/FaceOffToggleButton");
                return true;
            case R.id.action_developer:
                goWeb("https://github.com/Nightonke");
                return true;
            case R.id.action_wowo:
                goWeb("https://github.com/Nightonke/WoWoViewPager");
                return true;
            case R.id.action_boom:
                goWeb("https://github.com/Nightonke/BoomMenu");
                return true;
            case R.id.action_cocoin:
                goWeb("https://github.com/Nightonke/CoCoin");
                return true;
            case R.id.action_blur:
                goWeb("https://github.com/Nightonke/BlurLockView");
                return true;
            case R.id.action_leeco:
                goWeb("https://github.com/Nightonke/LeeCo");
                return true;
            case R.id.action_gw:
                goWeb("https://github.com/Nightonke/GithubWidget");
                return true;
            case R.id.action_jelly:
                goWeb("https://github.com/Nightonke/JellyToggleButton");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goWeb(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
