package space.fstudio.wallpaperapptemplate.Fragments;


import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.jsibbold.zoomage.ZoomageView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import space.fstudio.wallpaperapptemplate.Adapters.WallpaperAdapter;
import space.fstudio.wallpaperapptemplate.Cards.WallpaperCard;
import space.fstudio.wallpaperapptemplate.Listeners.RecyclerTouchListener;
import space.fstudio.wallpaperapptemplate.R;

public class WallpaperListFragment extends Fragment {

  /* List of all wallpapers */
  private List<WallpaperCard> wallpaperCardList = new ArrayList<>();

  WallpaperAdapter adapter;
  RecyclerView recyclerView;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    /* Inflate the layout for this fragment */
    View view = inflater.inflate(R.layout.fragment_wallpaper_list, container, false);

    /* Find RecyclerView by ID */
    recyclerView = view.findViewById(R.id.recycler_wallpaper);

    /* Set list type to Linear */
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    /* Set default animation for items */
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    /* Set adapter to WallpaperAdapter */
    adapter = new WallpaperAdapter(wallpaperCardList);

    /* Set custom adapter for RecyclerView list */
    recyclerView.setAdapter(adapter);

    RecyclerItemClick();

    WallpapersLoader();

    return view;
  }

  public void RecyclerItemClick() {
    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView,
        new RecyclerTouchListener.ClickListener() {
          @Override
          public void onClick(View view, final int position) {

            /* Get wallpaper position */
            WallpaperCard wallpaperCard = wallpaperCardList.get(position);

            /* Call wallpaper manager */
            WallpaperManager wm = WallpaperManager.getInstance(getActivity());

            /* Get display density */
            DisplayMetrics metrics = new DisplayMetrics();
            Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);

            /* Set correct dimensions for wallpaper */
            wm.suggestDesiredDimensions(metrics.widthPixels, metrics.heightPixels);

            try {
              /* Set wallpaper */
              wm.setStream(
                  Objects.requireNonNull(getActivity()).getAssets().open(wallpaperCard.getImg()));
            } catch (IOException e) {
              e.printStackTrace();
            }
          }

          @Override
          public void onLongClick(View view, int position) {

            /* Get wallpaper position */
            WallpaperCard wallpaperCard = wallpaperCardList.get(position);

            /* Set layout file for dialogView */
            @SuppressLint("InflateParams") View dialogView = Objects.requireNonNull(getActivity())
                .getLayoutInflater().inflate(R.layout.dialog_zoom_wallpaper, null);

            /* Find ImageView by ID */
            ZoomageView imageView = dialogView.findViewById(R.id.dialog_image_zoom);
            try {
              /* Open image from position in dialog */
              imageView.setImageBitmap(BitmapFactory
                  .decodeStream((getActivity().getAssets().open(wallpaperCard.getImg()))));
            } catch (IOException ignored) {
            }

            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                /* Set custom view for dialog */
                .setView(dialogView).create();

            /* Set dialog window background to transparent */
            Objects.requireNonNull(alertDialog.getWindow())
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            /* Remove title from dialog */
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.show();
          }
        })
    );
  }

  public void WallpapersLoader() {

    assert getArguments() != null;

    /* Get category */
    String category = getArguments().getString("category");

    /* Get files */
    ArrayList file = getArguments().getStringArrayList("file");

    /* For looping unless all files is not added to wallpaperCardList */
    assert file != null;
    System.out.println("------------ LIST ------------");
    for (int i = 0; i < file.size(); i++) {

      /* Allow check files in console for check unloaded images
       * (Maybe you write not correct file or type) */
      System.out.println("Asset path: " + category + " / " + file.get(i));

      /* Adding files to list */
      wallpaperCardList.add(new WallpaperCard(category + "/" + file.get(i)));
    }
    System.out.println("------------------------------");

    /* Update adapter for load RecyclerView */
    adapter.notifyDataSetChanged();
  }

}
