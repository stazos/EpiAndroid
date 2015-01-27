package root.epiandroid.model.object;

/**
 * Created by vesy_m on 17/01/15.
 */
public class Project {
    private String scolarYear;
    private String codeModule;
    private String codeInstance;
    private String codeActi;
    private String actiTitle;
    private String titleModule;
    private String registered;
    private String start;
    private String end;

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getTitleModule() {
        return titleModule;
    }

    public void setTitleModule(String titleModule) {
        this.titleModule = titleModule;
    }

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
}
