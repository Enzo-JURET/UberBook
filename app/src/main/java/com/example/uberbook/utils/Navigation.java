package com.example.uberbook.utils;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uberbook.R;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class Navigation {
    public static void init(AppCompatActivity activity) {
        BoomMenuButton bmb = (BoomMenuButton) activity.findViewById(R.id.bmb);

        bmb.setButtonEnum(ButtonEnum.SimpleCircle);

        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);

        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_3_1);

        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
            bmb.addBuilder(new SimpleCircleButton.Builder()
                    .normalImageRes(R.drawable.icone_email));
        }
    }
}
