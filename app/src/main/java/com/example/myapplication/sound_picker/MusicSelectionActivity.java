package com.example.myapplication.sound_picker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MusicSelectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private List<Music> musicList;
    private ImageButton backButton;
    private Music selectedMusic;
    private MediaPlayer mediaPlayer;

    private static final String PREFS_NAME = "MusicPrefs";
    private static final String PREF_SELECTED_MUSIC_ID = "selected_music_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_music);

        recyclerView = findViewById(R.id.list_view_music);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data, replace with actual music data
        musicList = new ArrayList<>();
        musicList.add(new Music(1, "Arpeggio", "", R.raw.country));
        musicList.add(new Music(2, "Breaking", "", R.raw.scratch_ringtone));
        musicList.add(new Music(3, "Canopy", "", R.raw.ring));
        musicList.add(new Music(4, "Chalet", "", R.raw.country));
        musicList.add(new Music(5, "Chirp", "", R.raw.country));
        musicList.add(new Music(6, "Daybreak", "", R.raw.country));


        musicAdapter = new MusicAdapter(musicList, music -> selectedMusic = music);
        recyclerView.setAdapter(musicAdapter);

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            if (selectedMusic != null) {
                resultIntent.putExtra("selected_music_resource", selectedMusic.getPreviewResourceId());
                resultIntent.putExtra("selected_music_title", selectedMusic.getTitle());
                resultIntent.putExtra("selected_music_artist", selectedMusic.getArtist());
                setResult(RESULT_OK, resultIntent);
            }
            // Save selected music id
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            if (selectedMusic != null) {
                editor.putInt(PREF_SELECTED_MUSIC_ID, selectedMusic.getId());
            } else {
                editor.remove(PREF_SELECTED_MUSIC_ID);
            }
            editor.apply();

            if (mediaPlayer != null) {
                Log.d("MusicSelectionActivity", "Releasing MediaPlayer");
                mediaPlayer.release();
                mediaPlayer = null;
            }
            finish();
        });

        // Restore selected music id
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int selectedMusicId = preferences.getInt(PREF_SELECTED_MUSIC_ID, -1);
        if (selectedMusicId != -1) {
            for (Music music : musicList) {
                if (music.getId() == selectedMusicId) {
                    selectedMusic = music;
                    break;
                }
            }
        }
    }
}
