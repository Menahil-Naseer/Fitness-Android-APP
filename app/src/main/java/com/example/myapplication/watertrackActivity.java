package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class watertrackActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, Dialog.DialogListener, NumberPickerDialog.DialogListener  {

    private int sum = 0;
    private int defaultGoal = 3700;
    private int defaultPick = 250;

    private static TextView mainText;
    private static TextView mainPercentage;
    private static TextView mainGoal;
    private static ImageView imgView;
    int[] images = {R.drawable.drop0, R.drawable.drop1, R.drawable.drop2, R.drawable.drop3, R.drawable.drop4, R.drawable.drop5, R.drawable.drop6, R.drawable.drop7, R.drawable.drop8, R.drawable.drop9, R.drawable.drop10};

    public static final String sharedPref = "sharedPref";
    private String date;
    private String dateToCompare;
    private String text;
    private String percentageText;
    private String lastColor = "#FFFFFF";

    private Animation animation;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
    private AlarmManager alarmManager;
    private PendingIntent pi;
    private Button mainButton;

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getDefaultGoal() {
        return defaultGoal;
    }

    public int getSum() {
        return sum;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watertrack);
        variableEq();
        initializeAlarmManager();
        loadData();
        updateViews();
        checkForNextDay();
    }
    public void variableEq() {
        mainText = findViewById(R.id.sum_goal);
        mainPercentage = findViewById(R.id.sum_percentage);
        mainGoal = findViewById(R.id.textBox2);
        imgView = findViewById(R.id.img_drop);
        animation = AnimationUtils.loadAnimation(watertrackActivity.this, R.anim.animation_drop);
        mainButton = findViewById(R.id.add);
        mainButton.setOnClickListener(Global_OnClickListener);
        mainButton.setText(String.valueOf(defaultPick));
        findViewById(R.id.select).setOnClickListener(Global_OnClickListener);
    }

    public void initializeAlarmManager() {
        Intent intent = new Intent(this, Receiver.class);
        pi = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + AlarmManager.INTERVAL_HOUR, pi);
    }

    public String goalReached() {
        return "Today's goal has been reached!" + " You drank " + getSum() + " ml!";
    }

    public void over() {
        if (getSum() > getDefaultGoal()) {
            lastColor = "#B82441";
            mainText.setTextColor(Color.parseColor(lastColor));
            mainPercentage.setTextColor(Color.parseColor(lastColor));
        }
    }

    public void refresh() {
        mainText.setText(getSum() + "/" + getDefaultGoal() + "ml");
    }

    public void reset() {
        setSum(sum = 0);
        lastColor = "#FFFFFF";
        mainText.setTextColor(Color.parseColor(lastColor));
        mainPercentage.setTextColor(Color.parseColor(lastColor));
        mainText.setText(getSum() + "/" + getDefaultGoal() + "ml");
        mainPercentage.setText((getSum() * 100) / getDefaultGoal() + "%");
        mainGoal.setText("");
        imgView.setImageResource(setImg(sum, false));
    }

    public int setImg(int sum, boolean reached) {
        if (!reached) {
            if (sum == 0)
                return images[0];
            if (sum <= (defaultGoal * 0.1))
                return images[1];
            if (sum <= (defaultGoal * 0.2))
                return images[2];
            if (sum <= (defaultGoal * 0.3))
                return images[3];
            if (sum <= (defaultGoal * 0.4))
                return images[4];
            if (sum <= (defaultGoal * 0.5))
                return images[5];
            if (sum <= (defaultGoal * 0.6))
                return images[6];
            if (sum <= (defaultGoal * 0.7))
                return images[7];
            if (sum <= (defaultGoal * 0.8))
                return images[8];
            if (sum <= (defaultGoal * 0.9))
                return images[9];
            if (sum == defaultGoal)
                return images[10];
        }
        return images[10];
    }

    public void setTxt() {
        over();
        mainText.setText(getSum() + "/" + getDefaultGoal() + "ml");
        mainPercentage.setText((getSum() * 100) / getDefaultGoal() + "%");
    }

    public void setImage(boolean reached) {
        imgView.setImageResource(setImg(sum, reached));
    }

    public void goalReach() {
        mainGoal.setText(goalReached());
    }

    public void buttonAction(int amount) {
        setSum(sum += amount);
        setTxt();
        setImage(false);
        saveData();
        if (sum >= defaultGoal) {
            goalReach();
            setImage(true);
            saveData();
        }
        imgView.startAnimation(animation);
    }

    final View.OnClickListener Global_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.add:
                    buttonAction(defaultPick);
                    break;
                case R.id.select:
                    openDialogNumberPicker();
                    break;
            }
        }
    };

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                openDialogSettings();
                return true;
            case R.id.item2:
                reset();
                return true;
            default:
                return false;
        }
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        date = dateFormat.format(Calendar.getInstance().getTime());

        editor.putString("color", lastColor);
        editor.putString("text", mainText.getText().toString());
        editor.putString("percentage", mainPercentage.getText().toString());
        editor.putInt("sum", getSum());
        editor.putInt("defaultGoal", getDefaultGoal());
        editor.putString("time", date);
        editor.putInt("pick", defaultPick);

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPref, MODE_PRIVATE);

        lastColor = sharedPreferences.getString("color", lastColor);
        percentageText = sharedPreferences.getString("percentage", "");
        text = sharedPreferences.getString("text", "");
        date = sharedPreferences.getString("time", "");
        sum = sharedPreferences.getInt("sum", sum);
        defaultGoal = sharedPreferences.getInt("defaultGoal", defaultGoal);
        defaultPick = sharedPreferences.getInt("pick", defaultPick);
        imgView.setImageResource(setImg(sum, false));
        dateToCompare = dateFormat.format(Calendar.getInstance().getTime());
        mainButton.setText(String.valueOf(defaultPick));
    }

    public void checkForNextDay() {
        if (date.compareTo(dateToCompare) < 0)
            reset();
    }

    public void updateViews() {
        mainText.setText(text);
        mainPercentage.setText(percentageText);
        mainText.setTextColor(Color.parseColor(lastColor));
        mainPercentage.setTextColor(Color.parseColor(lastColor));
    }

    public void showPopup(View v) {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.ItemTheme);
        PopupMenu popup = new PopupMenu(ctw, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.pop_menu);
        popup.show();
    }

    public void showInfo(View v) {
        openDialogInfo();
    }

    public void openDialogSettings() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    public void openDialogInfo() {
        InfoDialog dialog = new InfoDialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    public void openDialogNumberPicker() {
        NumberPickerDialog dialog = new NumberPickerDialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void applyText(String newGoal) {
        defaultGoal = Integer.parseInt(newGoal);
        reset();
        refresh();
        saveData();
    }

    @Override
    public void applySumButton(int newSelection) {
        defaultPick = newSelection;
        mainButton.setText(String.valueOf(defaultPick));
        refresh();
        saveData();
    }
}