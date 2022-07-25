package main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Main {

    public static void main(String[] args)
    {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Monitor[] monitors = ListReader.readMonitorList("multimonitortool-x64/output.txt");
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
//        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
//        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
        Menu toggleMenu = new Menu("Toggle Display");
        for (int i = 0; i < monitors.length; i++)
        {
            CheckboxMenuItem item = new CheckboxMenuItem(monitors[i].getName());
            if(monitors[i].isEnabled()) item.setState(true);
            toggleMenu.add(item);
        }
//        MenuItem item1 = new MenuItem("Monitor");
//        MenuItem item2 = new MenuItem("Monitor");
//        MenuItem item3 = new MenuItem("Monitor");
//        MenuItem item4 = new MenuItem("Monitor");
        Menu setMainMenu = new Menu("Set Main Display");
        for (int i = 0; i < monitors.length; i++)
        {
            CheckboxMenuItem item = new CheckboxMenuItem(monitors[i].getName());
            if (monitors[i].isMain()) item.setState(true);
            setMainMenu.add(item);
        }
        MenuItem exitItem = new MenuItem("Exit");

        //Add components to pop-up menu
//        popup.addSeparator();
//        popup.add(cb1);
//        popup.add(cb2);
        popup.add(toggleMenu);
//        toggleMenu.add(item1);
//        toggleMenu.add(item2);
        popup.add(setMainMenu);
//        setMainMenu.add(item3);
//        setMainMenu.add(item4);
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
