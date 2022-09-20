package is.hi.hbv501g.h6.hugboverkefni.post;

public class Content {
    private String text;
    private String image;
    private String audio;

    public Content() {
    }

    public Content(String text, String image, String audio) {
        this.text = text;
        this.image = image;
        this.audio = audio;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
        return "Content{" +
                "text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", audio='" + audio + '\'' +
                '}';
    }
}
