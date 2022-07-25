package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ListReader {
    final static private int ACTIVE_INDEX = 3;
    final static private int NAME_INDEX = 12;
    final static private int MAIN_INDEX = 5;
    final static private int ID_INDEX = 10;

    public static Monitor[] readMonitorList(String filename)
    {
        Monitor[] monitors;
        Path file = new File(filename).toPath();
        List<String> stringList;
        String[] stringArray;
        try {
            stringList = Files.readAllLines(file, StandardCharsets.UTF_16);
            stringArray = stringList.toArray(new String[]{});
            monitors = new Monitor[stringArray.length];

            for (int i = 0; i < monitors.length; i++)
            {
                monitors[i] = createMonitor(stringArray[i]);
            }

            return monitors;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static Monitor createMonitor(String info) {
        String[] listArray;
        listArray = info.split("[ \t]{2,}");

        String name = listArray[NAME_INDEX];
        String id = listArray[ID_INDEX];
        boolean isEnabled;
        if (listArray[ACTIVE_INDEX].equalsIgnoreCase("Yes")) isEnabled = true;
        else isEnabled = false;
        boolean isMain;
        if (listArray[MAIN_INDEX].equalsIgnoreCase("Yes")) isMain = true;
        else isMain = false;

        return new Monitor(name, id, isEnabled, isMain);
    }

}
