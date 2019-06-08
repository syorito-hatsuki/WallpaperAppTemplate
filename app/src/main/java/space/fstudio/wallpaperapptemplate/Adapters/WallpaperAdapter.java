package space.fstudio.wallpaperapptemplate.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.List;
import space.fstudio.wallpaperapptemplate.Cards.WallpaperCard;
import space.fstudio.wallpaperapptemplate.R;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.ViewHolder> {

  private List<WallpaperCard> wallpaperCardList;

  class ViewHolder extends RecyclerView.ViewHolder {

    ImageView img;

    ViewHolder(@NonNull View itemView) {
      super(itemView);
      img = itemView.findViewById(R.id.img_wallpaper);
    }
  }

  public WallpaperAdapter(List<WallpaperCard> wallpaperCardList){
    this.wallpaperCardList = wallpaperCardList;
  }


  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    /* Inflate the layout for this ViewHolder */
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.card_wallpaper, viewGroup,false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull WallpaperAdapter.ViewHolder viewHolder, int i) {
    WallpaperCard wallpaperCard = wallpaperCardList.get(i);
    /* Loading image to ImageView using Picasso for remove lags */
    Picasso.get().load("file:///android_asset/" + wallpaperCard.getCategory()).fit()
        .into(viewHolder.img);
  }

  @Override
  public int getItemCount() {
    return wallpaperCardList.size();
  }
}
