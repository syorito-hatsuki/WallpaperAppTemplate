package space.fstudio.wallpaperapptemplate;

import static space.fstudio.wallpaperapptemplate.Cards.WallpaperCard.bundle;
import static space.fstudio.wallpaperapptemplate.Cards.WallpaperCard.file;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import space.fstudio.wallpaperapptemplate.Cards.WallpaperCard;
import space.fstudio.wallpaperapptemplate.Fragments.HomeFragment;
import space.fstudio.wallpaperapptemplate.Fragments.WallpaperListFragment;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  DrawerLayout drawer;
  /* File array (To this array registering all files what need to load as wallpaper) */

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //Load main fragment
    if (savedInstanceState == null) {
      FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
      fragmentManager.replace(R.id.conteiner, new HomeFragment());
      fragmentManager.commit();
    }

    //Setup drawer
    drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, 0, 0);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    //Setup NavigationBar
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override
  public void onBackPressed() {
    /* If drawer is opened, close drawer */
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
      /* Else close app */
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {

    Fragment fragment = new WallpaperListFragment();
    WallpaperCard wallpaperCard = new WallpaperCard(MainActivity.this, null);

    /* Clear list if is not empty (For fix duplication bug) */
    if (!file.isEmpty()){file.clear();}

    /* Category selection switch */
    try {
      switch (item.getItemId()) {
        case R.id.nav_category_one:
          /* setCategory("Your path in assets") */
          wallpaperCard.setCategory("c1");
          fragment.setArguments(bundle);
          break;
        case R.id.nav_category_two:
          wallpaperCard.setCategory("c2");
          fragment.setArguments(bundle);
          break;
        case R.id.nav_category_three:
          wallpaperCard.setCategory("c3");
          fragment.setArguments(bundle);
          break;
      }
    }catch (Exception ignored){}

    /* Change fragment when it selected */
    FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
    try {
      fragmentManager.replace(R.id.conteiner, fragment).commit();
    } catch (Exception ignored) {}

    /* Close drawer */
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

}
