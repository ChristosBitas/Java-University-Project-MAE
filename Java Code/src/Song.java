public class Song {

    private String songId,songTitle,artist,musicCateg,releaseDate,duration;

    public Song(String songId, String songTitle, String artist, String musicCateg, String releaseDate, String duration) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.artist = artist;
        this.musicCateg = musicCateg;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getMusicCateg() {
        return musicCateg;
    }

    public void setMusicCateg(String musicCateg) {
        this.musicCateg = musicCateg;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return songId + " " + songTitle + " " + releaseDate + " " + artist + " " + musicCateg + " " + duration;
    }
}
