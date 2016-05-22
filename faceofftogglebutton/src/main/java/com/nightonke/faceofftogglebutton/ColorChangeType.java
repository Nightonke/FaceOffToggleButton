package com.nightonke.faceofftogglebutton;

/**
 * Created by Weiping on 2016/5/22.
 */

public enum ColorChangeType {

    RGB(0),
    HSV(1);

    int v;

    ColorChangeType(int v) {
        this.v = v;
    }

}
