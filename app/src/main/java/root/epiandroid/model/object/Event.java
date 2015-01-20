package root.epiandroid.model.object;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vesy_m on 17/01/15.
 */
public class Event implements Serializable {
    private String scolarYear;
    private String codeModule;
    private String codeInstance;
    private String codeEvent;
    private String codeActi;
    private String actiTitle;
    private String titleModule;
    private String duree;
    private String registered;
    private String allowToken;
    private Date start;
    private Date end;
    private String salle;

    public String getTitleModule() {
        return titleModule;
    }

    public void setTitleModule(String titleModule) {
        this.titleModule = titleModule;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getAllowToken() {
        return allowToken;
    }

    public void setAllowToken(String allowToken) {
        this.allowToken = allowToken;
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

    public Date getStart() {
        return start;
    }

    public void setStart(String start) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dStart = null;
        try {
            dStart = format.parse(start);
        } catch (Exception e) {
            dStart = null;
        }
        this.start = dStart;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(String end) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dEnd = null;
        try {
            dEnd = format.parse(end);
        } catch (Exception e) {
            dEnd = null;
        }
        this.end = dEnd;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        if (salle == null)
            this.salle = null;
        else {
            int index = salle.lastIndexOf("/") + 1;
            if (index > salle.length())
                this.salle = null;
            else
                this.salle = salle.substring(index, salle.length());
        }
    }
}
