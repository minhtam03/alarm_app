package com.example.myapplication.sound_picker;

public class Music {
    private int id;
    private String title;
    private String artist;
    private int previewResourceId; // Resource ID for preview

    public Music(int id, String title, String artist, int previewResourceId) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.previewResourceId = previewResourceId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getPreviewResourceId() {
        return previewResourceId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Music music = (Music) obj;
        return id == music.id;
    }
}
