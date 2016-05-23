# FaceOffToggleButton

[![WoWoViewPager](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/WoWoViewPager)
[![BoomMenu](https://github.com/Nightonke/BoomMenu/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/BoomMenu)
[![CoCoin](https://github.com/Nightonke/CoCoin/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/CoCoin)
[![BlurLockView](https://github.com/Nightonke/BlurLockView/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/BlurLockView)
[![LeeCo](https://github.com/Nightonke/LeeCo/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/LeeCo)
[![GithubWidget](https://github.com/Nightonke/GithubWidget/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/GithubWidget)
[![JellyToggleButton](https://github.com/Nightonke/JellyToggleButton/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/JellyToggleButton)
[![FaceOffToggleButton](https://github.com/Nightonke/FaceOffToggleButton/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/FaceOffToggleButton)

笑脸苦脸表示开关状态的开关按钮。

![FaceOffToggleButton](https://github.com/Nightonke/FaceOffToggleButton/blob/master/img/Animation.gif?raw=true)

最近在写一个[可爱的果冻动效开关按钮](https://github.com/Nightonke/JellyToggleButton)。之后无意中看到一个用笑脸和苦脸表示开关状态的按钮（[IOS](https://github.com/lilei644/LLSwitch)和[Dribbble](https://dribbble.com/shots/2706143-Dribbble-Debut-Boring-Funny-Slider-Animation)）。因为都是开关按钮，所以顺手写了一个。这篇文档许多相关功能和之前提到的开关按钮类似，更多信息可以去[JellyToggleButton](https://github.com/Nightonke/JellyToggleButton)看看。

# Guide
1. [English Version](https://github.com/Nightonke/FaceOffToggleButton)
2. [Gradle](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#guide)
3. [Demo](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#demo)
4. [Usage Guide](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#usage-guide)
    1. [Background Color](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#background-color)
    2. [Left & Right Background Color](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#left--right-background-color)
    3. [Left & Right Face Color](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#left--right-face-color)
    4. [Left & Right Eye Color](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#left--right-eye-color)
    5. [Left & Right Mouth Color](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#left--right-mouth-color)
    6. [Set Checked and Toggle](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#set-checked-and-toggle)
    7. [Listener](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#listener)
    8. [Duration](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#duration)
    9. [Draggable](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#draggable)
    10. [Color Change Type](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#color-change-type)
    11. [Dimension](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#dimension)
    12. [Other Method](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#other-method)
5. [Todo](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#todo)
6. [Versions](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#version)
7. [License](https://github.com/Nightonke/FaceOffToggleButton/blob/master/README-ZH.md#license)

# Gradle
```
repositories {
    maven {
        url 'https://dl.bintray.com/nightonke/maven/'
    }
}
...
dependencies {
    compile 'com.nightonke:faceofftogglebutton:1.0.0'
}
```

# Demo
Demo在此：    
1. [On Github](https://github.com/Nightonke/FaceOffToggleButton/blob/master/apk/FaceOffToggleButton1.0.0.apk?raw=true)  
2. [On Fir](http://fir.im/faceofftoggle)  
3.   
![QR Code](https://github.com/Nightonke/FaceOffToggleButton/blob/master/apk/FaceOffToggleButton1.0.0.png?raw=true)

# Usage Guide

### Background Color
这里的背景颜色和View的背景颜色略有不同，可以试着定义背景颜色或者阅读源码来查看其不同之处。

```
setBackgroundColor(int mBackgroundColor)
```

```
app:foBackgroundColor="@color/white"
```

### Left & Right Background Color
脸运动到左边的背景颜色和运动到右边的背景颜色。
```
setLeftBackgroundColor(int mLeftBackgroundColor)
setRightBackgroundColor(int mRightBackgroundColor)
```
```
app:foLeftBackgroundColor="@color/gray"
app:foRightBackgroundColor="@color/blue"
```

### Left & Right Face Color
左脸颜色和右脸颜色。
```
setLeftFaceColor(int mLeftFaceColor)
setRightFaceColor(int mRightFaceColor)
```
```
app:foLeftFaceColor="@color/white"
app:foRightFaceColor="@color/white"
```

### Left & Right Eye Color
左脸的眼睛颜色和右脸的眼睛颜色。 
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
| 方法 | 是否有动画 | 是否有监听器 |
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
更多相关信息，请查阅[这](https://github.com/Nightonke/WoWoViewPager#rgb-or-hsv)。  
```
setColorChangeType(ColorChangeType mColorChangeType)
```
```
app:foColorChangeType="hsv"
```

### Dimension

```
// 脸的半径，单位像素
setFaceRadius(float mFaceRadius)

// 控件宽度与脸半径的比值，单位浮点数
setWidthRadiusRatio(float mWidthRadiusRatio)

// 脸和背景的间距，单位像素
setFaceMargin(float mFaceMargin)
```
```
app:foFaceRadius="15dp"
app:foWidthRadiusRatio="2.5"
app:foFaceMargin="1dp"
```

### Other Method
手指拖动的距离和脸移动的距离。  
```
setTouchMoveRatioValue(float ratio)
```
```
app:foTouchMoveRatioValue="2"
```
当脸运动到与上次相同的状态是否调用监听器。
```
setMoveToSameStateCallListener(boolean callListener)
```
```
app:foMoveToSameStateCallListener="false"
```

# Todo
1. 眼睛和嘴巴运动到终点的缓冲动效。
2. 更多表情。

# Version
#### [1.0.0](https://github.com/Nightonke/FaceOffToggleButton/blob/master/apk/FaceOffToggleButton1.0.0.apk?raw=true)
第一版本。

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
    
