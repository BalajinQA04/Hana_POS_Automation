package com.hanapos.utilities;

import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VideoRecorderUtil {

    public static ThreadLocal<ScreenRecorder> screenRecorder = new ThreadLocal<>();

    public static void startRecording(String fileName) {
        try {
            File folder = new File("test-recordings/");
            if (!folder.exists() && !folder.mkdirs()) {
                System.err.println("Could not create test-recordings directory");
                return;
            }

            GraphicsConfiguration gc = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

            Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            SpecializedScreenRecorder recorder = new SpecializedScreenRecorder(
                    gc,
                    captureSize,
                    folder,
                    fileName
            );

            screenRecorder.set(recorder);   // ⚠️ critical: bind to thread-local
            recorder.start();               // ✅ important: start recording

            System.out.println("Started recording: " + fileName);

        } catch (IOException | AWTException e) {
            e.printStackTrace();
            System.err.println("Failed to start recording for: " + fileName);
        }
    }

    public static void stopRecording() {
        try {
            ScreenRecorder recorder = screenRecorder.get();
            if (recorder != null) {
                recorder.stop();
                System.out.println("Stopped recording.");
                screenRecorder.remove();
            } else {
                System.err.println("No ScreenRecorder instance found in ThreadLocal for stopping.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error stopping video recording: " + e.getMessage());
        }
    }
}
