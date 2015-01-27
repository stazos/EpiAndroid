package root.epiandroid.model.object;

/**
 * Created by vesy_m on 26/01/15.
 */
public class Note {

    String titlemodule = null;
    String title = null;
    String date = null;
    String correcteur = null;
    String final_note = null;
    String comment = null;

    public String getTitlemodule() {
        return titlemodule;
    }

    public void setTitlemodule(String titlemodule) {
        this.titlemodule = titlemodule;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCorrecteur() {
        return correcteur;
    }

    public void setCorrecteur(String correcteur) {
        this.correcteur = correcteur;
    }

    public String getFinal_note() {
        return final_note;
    }

    public void setFinal_note(String final_note) {
        this.final_note = final_note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
