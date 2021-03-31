package com.example.uberbook.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uberbook.MainActivity;
import com.example.uberbook.R;
import com.example.uberbook.activities.booksDisponibilityListPage;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class Navigation {
    public static void init(BoomMenuButton currentBoomMenuButton, AppCompatActivity context) {
        currentBoomMenuButton.setButtonEnum(ButtonEnum.SimpleCircle);

        currentBoomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);
        currentBoomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_3_1);

        currentBoomMenuButton.setDotRadius(14);

        currentBoomMenuButton.addBuilder(new SimpleCircleButton.Builder()
                .normalImageRes(R.drawable.ic_icone_scan)
                .normalColor(Color.rgb(255, 255, 255))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Log.d("Debug", "Clicked on button");
                        //TODO: Action when calling
                    }
                }));

        currentBoomMenuButton.addBuilder(new SimpleCircleButton.Builder()
                .normalImageRes(R.drawable.ic_icone_home)
                .normalColor(Color.rgb(255, 255, 255))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent intent = new Intent(context, booksDisponibilityListPage.class);
                        intent.putExtra("User", SharedPreference.getUser());
                        context.startActivity(intent);
                    }
                }));

        currentBoomMenuButton.addBuilder(new SimpleCircleButton.Builder()
                .normalImageRes(R.drawable.ic_icone_profil)
                .normalColor(Color.rgb(255, 255, 255))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                            SharedPreference.removeAll();
                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                    }
                }));
    }
}
