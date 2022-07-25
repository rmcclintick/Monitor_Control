package main;

public class Monitor {
    private String name;
    private String id;
    private boolean isEnabled;
    private boolean isMain;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public Monitor(String name, String id, boolean isEnabled, boolean isMain)
    {
        this.name = name;
        this.id = id;
        this.isEnabled = isEnabled;
        this.isMain = isMain;
    }

    public String toString()
    {
        return String.format("Index: %s, Name: %s, Active: %s, Main: %s\n", this.id, this.name, this.isEnabled, this.isMain);
    }
}
