# Material SearchView Toolbar
##### A circular reveal SearchView. Just like the one in Whatsapp or GooglePlay.

### Getting Started
Add the dependency (available from mavenCentral and jcenter) to your build.gradle:

    compile 'ayham.searchtoolbar.android:material_search_toolbar:0.2'
    
### Implement the Toolbar
All you have to do is adding this line above your main toolbar in your layout:

      <include layout="@layout/search_toolbar"/>

your code will look somehow like this

    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="dev.ayham.com.searchtoolbar.MainActivity">
        <FrameLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="@color/colorPrimary"/>
            <include layout="@layout/search_toolbar"/>
        </FrameLayout>
    </RelativeLayout>

#### In your MainActivity:

    SearchToolBar searchToolBar = new SearchToolBar(this);
And you're set to go!

### Methods:

##### setPosFromRight(int pos) -> void
Setting a margin to the reveal animation from the right, the default is 0
##### getPosFromRight() -> int
get the right margin of the animation
##### expand() -> void
expand the searchView.
##### collapse() -> void
collapse the searchView.
##### isPresent() -> boolean
check if the searchToolbar is visible. 
##### getQuery() -> String
returns the text in the searchView.
##### setSearchListener(SearchListener searchListener) -> void
The SearchListener interface has two methods.
###### submit()
triggered when the user hits the search button.
###### instantSearch()
triggered everytime the user change the text.
#### setStateListener(StateListener stateListener)
The StateListener interface has three methods.
##### onCollapse()
triggered when the Toolbar statrs collapsing
##### onExpand()
triggered when the Toolbar statrs expanding
##### onFocusLost()
triggered when the user click somewhere else other than the toolbar or the keyboard

### Example:
[MainActivity.java](https://github.com/ayham95/Material_Search_Toolbar/blob/master/app/src/main/java/dev/ayham/com/searchtoolbar/MainActivity.java)
[activity_main.xml](https://github.com/ayham95/Material_Search_Toolbar/blob/master/app/src/main/res/layout/activity_main.xml)
### License
This project is licensed under The [MIT](https://opensource.org/licenses/MIT) license.

### Acknowledgments
This Project is in alpha version [v0.3](https://github.com/ayham95/Material_Search_Toolbar/tree/v0.3).

