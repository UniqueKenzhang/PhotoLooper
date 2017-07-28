# PhotoLooper
A view to display photo or some other things with a loop-card effect，Base on RecyclerView and almost the same way to use

## How to use
### 1.layout.xml
```xml
 <com.ftc.kenzhang.photolooper.PhotoRecyclerView
        android:id="@+id/content"
        android:layout_width="250dp"
        android:layout_height="250dp"
        android:layout_centerInParent="true" />
```
parent layout need two param:<br>
android:clipChildren="false"<br>
android:clipToPadding="false"<br>
### 2.java

```java
mPhotoPager = (PhotoRecyclerView) findViewById(R.id.content);
mPhotoPager.setAdapter(mPhotoPagerAdapter);// set a RecyclerView.Adapter
```
### 3.OK!
