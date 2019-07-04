package gc.assignment.firebaserealtimedatabase;

public class Artist {

    String artistId;
    String artistNme;
    String artistGenre;

    public Artist(){

    }

    public Artist(String artistId, String artistNme, String artistGenre) {
        this.artistId = artistId;
        this.artistNme = artistNme;
        this.artistGenre = artistGenre;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistNme() {
        return artistNme;
    }

    public String getArtistGenre() {
        return artistGenre;
    }
}

