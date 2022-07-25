package main;

public class Monitor {
    private String name;
    private int id;
    private boolean isEnabled;
    private boolean isMain;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return isEnabled;
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

    public Monitor(String name, int id, boolean isEnabled, boolean isMain)
    {
        this.name = name;
        this.id = id;
        this.isEnabled = isEnabled;
        this.isMain = isMain;
    }
}
