# FaceOffToggleButton

[![WoWoViewPager](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/WoWoViewPager)
[![BoomMenu](https://github.com/Nightonke/BoomMenu/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/BoomMenu)
[![CoCoin](https://github.com/Nightonke/CoCoin/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/CoCoin)
[![BlurLockView](https://github.com/Nightonke/BlurLockView/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/BlurLockView)
[![LeeCo](https://github.com/Nightonke/LeeCo/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/LeeCo)
[![GithubWidget](https://github.com/Nightonke/GithubWidget/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/GithubWidget)
[![JellyToggleButton](https://github.com/Nightonke/JellyToggleButton/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/JellyToggleButton)
[![FaceOffToggleButton](https://github.com/Nightonke/FaceOffToggleButton/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/FaceOffToggleButton)

Toggle button which shows a happy face for checked or unhappy for unchecked.

![FaceOffToggleButton](https://github.com/Nightonke/FaceOffToggleButton/blob/master/img/Animation.gif?raw=true)

Recently, I spent some time to finish a [cute toggle button](https://github.com/Nightonke/JellyToggleButton) and after that, I found a toggle button design with a happy or unhappy face to show the checked status. Check [here](https://github.com/lilei644/LLSwitch) for the IOS version and [here](https://dribbble.com/shots/2706143-Dribbble-Debut-Boring-Funny-Slider-Animation) for the design in Dribbble. Since I have made a similar one, so I just change some codes to create the new FaceOffToggleButton(FOTB). This document is a quite simple one and for more information, please check [JellyToggleButton](https://github.com/Nightonke/JellyToggleButton).

# Guide
1. [中文文档](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md)
2. [Gradle](https://github.com/Nightonke/FaceOffToggleButton#guide)
3. [Demo](https://github.com/Nightonke/FaceOffToggleButton#demo)
4. [Usage Guide](https://github.com/Nightonke/FaceOffToggleButton#usage-guide)
    1. [Background Color](https://github.com/Nightonke/FaceOffToggleButton#background-color)
    2. [Left & Right Background Color](https://github.com/Nightonke/FaceOffToggleButton#left--right-background-color)
    3. [Left & Right Face Color](https://github.com/Nightonke/FaceOffToggleButton#left--right-face-color)
    4. [Left & Right Eye Color](https://github.com/Nightonke/FaceOffToggleButton#left--right-eye-color)
    5. [Left & Right Mouth Color](https://github.com/Nightonke/FaceOffToggleButton#left--right-mouth-color)
    6. [Set Checked and Toggle](https://github.com/Nightonke/FaceOffToggleButton#set-checked-and-toggle)
    7. [Listener](https://github.com/Nightonke/FaceOffToggleButton#listener)
    8. [Duration](https://github.com/Nightonke/FaceOffToggleButton#duration)
    9. [Draggable](https://github.com/Nightonke/FaceOffToggleButton#draggable)
    10. [Color Change Type](https://github.com/Nightonke/FaceOffToggleButton#color-change-type)
    11. [Dimension](https://github.com/Nightonke/FaceOffToggleButton#dimension)
    12. [Other Method](https://github.com/Nightonke/FaceOffToggleButton#other-method)
5. [Todo](https://github.com/Nightonke/FaceOffToggleButton#todo)
6. [Versions](https://github.com/Nightonke/FaceOffToggleButton#version)
7. [License](https://github.com/Nightonke/FaceOffToggleButton#license)

# Gradle
```
compile 'com.nightonke:faceofftogglebutton:1.0.0'
```

# Demo
Try demo:  
1. [On Github](https://github.com/Nightonke/FaceOffToggleButton/blob/master/apk/FaceOffToggleButton1.0.0.apk?raw=true)  
2. [On Fir](http://fir.im/faceofftoggle)  
3.   
![QR Code](https://github.com/Nightonke/FaceOffToggleButton/blob/master/apk/FaceOffToggleButton1.0.0.png?raw=true)

# Usage Guide

### Background Color
Notice that the background color here is totally different from the background color of a veiw. Try to set background color with the following method or check the source code and you will figure out why.  
```
setBackgroundColor(int mBackgroundColor)
```

```
app:foBackgroundColor="@color/white"
```

### Left & Right Background Color
The color of background when the face is on the end-of-left and end-of-right.  
```
setLeftBackgroundColor(int mLeftBackgroundColor)
setRightBackgroundColor(int mRightBackgroundColor)
```
```
app:foLeftBackgroundColor="@color/gray"
app:foRightBackgroundColor="@color/blue"
```

### Left & Right Face Color
The color of face when it is on the end-of-left and end-of-right.  
```
setLeftFaceColor(int mLeftFaceColor)
setRightFaceColor(int mRightFaceColor)
```
```
app:foLeftFaceColor="@color/white"
app:foRightFaceColor="@color/white"
```

### Left & Right Eye Color
FOTB has beautiful eyes.  
```
setLeftEyeColor(int mLeftEyeColor)
setRightEyeColor(int mRightEyeColor)
```
```
app:foLeftEyeColor="@color/gray"
app:foRightEyeColor="@color/blue"
```

### Left & Right Mouth Color
```
setLeftMouthColor(int mLeftMouthColor)
setRightMouthColor(int mRightMouthColor)
```
```
app:foLeftMouthColor="@color/gray"
app:foRightMouthColor="@color/blue"
```

### Set Checked and Toggle
| Method | With Animation | With Listener |
|:-------|:---------------|:--------------|
|```setChecked(boolean checked)```| Y | Y |
|```setChecked(boolean checked, boolean callListener)```|Y|Y/N|
|```setCheckedImmediately(boolean checked)```|N|Y|
|```setCheckedImmediately(boolean checked, boolean callListener)```|N|Y/N|
|```toggle()```|Y|Y|
|```toggle(boolean callListener)```|Y|Y/N|
|```toggleImmediately()```|N|Y|
|```toggleImmediately(boolean callListener)```|N|Y/N|

### Listener
```
fotb0.setOnStateChangeListener(new FaceOffToggleButton.OnStateChangeListener() {
    @Override
    public void onStateChange(float process, State state, FaceOffToggleButton fotb) {
        // process - current process of JTB, between [0, 1]
        // state   - current state of JTB, it is one of State.LEFT, State.LEFT_TO_RIGHT, State.RIGHT and State.RIGHT_TO_LEFT
        // fotb     - the FOTB
    }
});
```

### Duration
```
setDuration(int duration)
```
```
app:foDuration="1000"
```

### Draggable
```
setDraggable(boolean draggable)
```
```
app:foDraggable="false"
```

### Color Change Type
For information, please check [here](https://github.com/Nightonke/WoWoViewPager#rgb-or-hsv).  
```
setColorChangeType(ColorChangeType mColorChangeType)
```
```
app:foColorChangeType="hsv"
```

### Dimension

```
// Radius of face, in pixel.
setFaceRadius(float mFaceRadius)

// The ratio for width to face radius, in float.
setWidthRadiusRatio(float mWidthRadiusRatio)

// The margin between face and background, in pixel.
setFaceMargin(float mFaceMargin)
```
```
app:foFaceRadius="15dp"
app:foWidthRadiusRatio="2.5"
app:foFaceMargin="1dp"
```

### Other Method
The ratio for distance of finger movement to distance of face movement.  
```
setTouchMoveRatioValue(float ratio)
```
```
app:foTouchMoveRatioValue="2"
```
Whether call listener when face moves to same state as last.
```
setMoveToSameStateCallListener(boolean callListener)
```
```
app:foMoveToSameStateCallListener="false"
```

# Todo
1. Ease out back effect of the eyes and mouth.
2. More emoji.

# Version
#### [1.0.0](https://github.com/Nightonke/FaceOffToggleButton/blob/master/apk/FaceOffToggleButton1.0.0.apk?raw=true)
The first version.

# License

    Copyright 2016 Nightonke

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
