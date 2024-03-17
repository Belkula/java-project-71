package hexlet.code;

public class Differs {

    private String key;
    private String status;
    private Object oldValue;
    private Object newValue;

    public Differs(Object oldValue, Object newValue, String key, String status ) {
        this.oldValue = oldValue;
        this.newValue = newValue;
		this.key = key;
        this.status = status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public String getKey() {
        return key;
    }

    public String getStatus() {
        return status;
    }

    public String toString() {
        //return "{key:" + key + "; status:" + status + "; oldValue:" + oldValue + "; newValue:" + newValue + "}";
        return ("\"" + key + "\", \"" + status + "\", \"" + oldValue + "\", \"" + newValue + "\"");
    }
}