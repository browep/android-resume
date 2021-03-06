package com.github.browep.resume;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import static com.github.browep.resume.Constants.CONTACT_PAUL;
import static com.github.browep.resume.Constants.SCREEN_ID;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 6/3/11
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResumeApplication extends Application{
  private static ResumeApplication application;
  Map<String,Map> screens;

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;

    InputStream is = getApplicationContext().getResources().openRawResource(R.raw.data);
    try {
      screens = (Map<String, Map>) (new ObjectMapper()).readValue(is, TreeMap.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static ResumeApplication getApplication(){
    return  application;
  }

  public Map<String,Map> getScreens() {
    return screens;
  }

  public static boolean onCreateOptionsMenu(Menu menu) {
    menu.add(CONTACT_PAUL);
    return true;
  }

  public static boolean onMenuItemSelected(Context context ,int featureId, MenuItem item){
    Intent intent = new Intent(context, HtmlViewer.class);
    intent.putExtra(SCREEN_ID, "contact");
    context.startActivity(intent);
    return true;
  }
}
