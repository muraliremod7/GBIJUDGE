package gbijudge.murali.gbijudge.ideas;

/**
 * Created by Hari Prahlad on 15-05-2016.
 */
public class IdeasCommonClass {
    String ideaTitle;
    String eventName;
    String status;
    String id;

    public IdeasCommonClass(){

    }
    public IdeasCommonClass(String ideaTitle, String eventName, String status, String id) {
        this.ideaTitle = ideaTitle;
        this.eventName = eventName;
        this.status = status;
        this.id = id;
    }
    public String getIdeaTitle() {
        return ideaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        this.ideaTitle = ideaTitle;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
