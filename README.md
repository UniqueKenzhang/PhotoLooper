# PhotoLooper
A view to display photo or some other things with a loop-card effect，Base on RecyclerView and almost the same way to use

## How to use
### 1.layout.xml
```xml
 <com.ftc.kenzhang.photolooper.PhotoLooperView
        android:id="@+id/content"
        android:layout_width="250dp"
        android:layout_height="250dp" 
        android:layout_centerInParent="true" />

// layout_width/height need a value
// can not larger than it's item's layout_width/height
```
your parent layout need two param:<br>
```xml
android:clipChildren="false"
android:clipToPadding="false"
 ```
### 2.java code

```java
PhotoAdapter adapter = new PhotoAdapter(data);// just a RecyclerView.Adapter
PhotoLooperView looper = (PhotoLooperView) findViewById(R.id.content);
looper.setAdapter(adapter);
```
### 3.OK!

![](https://github.com/UniqueKenzhang/PhotoLooper/blob/master/raw/photo_looper.gif)
<br><br>

## Detail Setting
```java
//call these method before you call setAdapter
looper.setThreshold(0.6f);//Persent of width.When over the threshold , the drag view will be recycled.
looper.setScaleGap(0.03f);//Scale rate between each item
looper.setTransYGAP(40);//TransY betweem each item.
looper.setShowCount(4);//display view count
```
<br>
<br>
## TODO
PhotoLooperView's layout_width/height can not larger than it's item's layout_width/height
