package root.epiandroid.model;

/**
 * Created by vesy_m on 17/01/15.
 */
public class Event {
    private String scolarYear;
    private String codeModule;
    private String codeInstance;
    private String codeEvent;
    private String codeActi;
    private String actiTitle;
    private String start;
    private String end;
    private String salle;

    public String getCodeActi() {
        return codeActi;
    }

    public void setCodeActi(String codeActi) {
        this.codeActi = codeActi;
    }

    public String getScolarYear() {
        return scolarYear;
    }

    public void setScolarYear(String scolarYear) {
        this.scolarYear = scolarYear;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getCodeInstance() {
        return codeInstance;
    }

    public void setCodeInstance(String codeInstance) {
        this.codeInstance = codeInstance;
    }

    public String getCodeEvent() {
        return codeEvent;
    }

    public void setCodeEvent(String codeEvent) {
        this.codeEvent = codeEvent;
    }

    public String getActiTitle() {
        return actiTitle;
    }

    public void setActiTitle(String actiTitle) {
        this.actiTitle = actiTitle;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }
}
