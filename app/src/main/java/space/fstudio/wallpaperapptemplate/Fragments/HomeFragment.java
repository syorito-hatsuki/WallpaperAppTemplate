package space.fstudio.wallpaperapptemplate.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import space.fstudio.wallpaperapptemplate.R;

public class HomeFragment extends Fragment {

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_home, container, false);

    WebView webView = view.findViewById(R.id.webView);
    WebSettings webSettings = webView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webView.loadUrl("https://github.com/syorito-hatsuki/WallpaperAppTemplate/blob/master/README.md#wallpaper-pack-application-template");

    return view;
  }

}
