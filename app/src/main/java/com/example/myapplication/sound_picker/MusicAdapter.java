package com.example.myapplication.sound_picker;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private List<Music> musicList;
    private int selectedPosition = -1;
    private OnItemClickListener listener;
    private MediaPlayer mediaPlayer;

    // interface cho click 1 item
    public interface OnItemClickListener {
        void onItemClick(Music music);
    }

    public MusicAdapter(List<Music> musicList, OnItemClickListener listener) {
        this.musicList = musicList;
        this.listener = listener;
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_music, parent, false);
        return new MusicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MusicViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Music music = musicList.get(position);

        holder.title.setText(music.getTitle());
        holder.artist.setText(music.getArtist());

        holder.itemView.setOnClickListener(v -> {

            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            mediaPlayer = MediaPlayer.create(holder.itemView.getContext(), music.getPreviewResourceId());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(mp -> mp.release());

            selectedPosition = position;
            listener.onItemClick(music);
            notifyDataSetChanged();
        });

        holder.checkmark.setVisibility(position == selectedPosition ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public static class MusicViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView artist;
        public ImageView checkmark;

        public MusicViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.music_title_1);
            artist = view.findViewById(R.id.music_artist_1);
            checkmark = view.findViewById(R.id.checkmark);
        }
    }
}
