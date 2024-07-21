package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.sound_picker.MusicSelectionActivity;
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SELECT_MUSIC = 1;
    private Button selectMusicButton;
    private TextView selectedMusicTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectMusicButton = findViewById(R.id.select_music_button);
        selectedMusicTextView = findViewById(R.id.select_music_text_view);

        selectMusicButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MusicSelectionActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT_MUSIC);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_MUSIC && resultCode == RESULT_OK) {
            String selectedMusicUri = data.getStringExtra("selected_music_uri");
            String selectedMusicTitle = data.getStringExtra("selected_music_title");
            String selectedMusicArtist = data.getStringExtra("selected_music_artist");

            // Hiển thị thông tin nhạc đã chọn
            selectedMusicTextView.setText("Sound: " + selectedMusicTitle);
        }
    }
}