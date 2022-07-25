package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URL;

public class Main {
    private final static String[] getListCmd = new String[]{"multimonitortool-x64/MultiMonitorTool.exe",  "/stabular", "output.txt"};
    private final static String[] toggleDisplayCmd = new String[]{"multimonitortool-x64/MultiMonitorTool.exe",  "/switch", ""};
    public static void main(String[] args)
    {
        try {
            Runtime.getRuntime().exec(getListCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Monitor[] monitors = ListReader.readMonitorList("output.txt");
        createSysTray(monitors);
    }

    private static void createSysTray(Monitor[] monitors)
    {
        //Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("bulb.gif", "tray icon"));
        trayIcon.setToolTip("Monitor Control");
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a pop-up menu components
        MenuItem configItem = new MenuItem("Configure");

        Menu toggleMenu = new Menu("Toggle Display");
        for (int i = 0; i < monitors.length; i++)
        {
            CheckboxMenuItem item = new CheckboxMenuItem(monitors[i].getName());
            if(monitors[i].isEnabled()) item.setState(true);
            int finalI = i;
            item.addItemListener(e -> {
                String[] cmd = toggleDisplayCmd.clone();
                cmd[2] = monitors[finalI].getId();
                System.out.println("Attempting toggle on: " + monitors[finalI]);
                try {
                    Runtime.getRuntime().exec(cmd);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            toggleMenu.add(item);
        }

        Menu setMainMenu = new Menu("Set Main Display");
        for (Monitor monitor : monitors) {
            CheckboxMenuItem item = new CheckboxMenuItem(monitor.getName());
            if (monitor.isMain()) item.setState(true);
            setMainMenu.add(item);
        }
        MenuItem exitItem = new MenuItem("Exit");

        //Add components to pop-up menu
        popup.add(toggleMenu);

        popup.add(setMainMenu);

        popup.addSeparator();

        popup.add(configItem);
        popup.add(exitItem);
        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }

    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = Main.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
