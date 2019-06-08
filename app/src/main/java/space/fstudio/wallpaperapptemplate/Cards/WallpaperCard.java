package space.fstudio.wallpaperapptemplate.Cards;

import android.content.Context;
import android.os.Bundle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WallpaperCard {

  private String assetsPath;
  public static Bundle bundle = new Bundle();
  public static ArrayList<String> file = new ArrayList<>();
  private Context context;

  public WallpaperCard(Context context, String assetsPath) {
    this.assetsPath = assetsPath;
    this.context = context;
  }

  public String getCategory() {
    return assetsPath;
  }

  public void setCategory(String assetsPath) throws IOException {
    this.assetsPath = assetsPath;
    bundle.putString("category", assetsPath);
    String[] assetList = context.getAssets().list(assetsPath);
    file.addAll(Arrays.asList(assetList));
    bundle.putStringArrayList("file", file);
  }
}
