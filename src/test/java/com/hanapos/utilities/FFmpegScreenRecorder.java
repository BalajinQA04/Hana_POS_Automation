package com.hanapos.utilities;

import io.qameta.allure.Allure;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FFmpegScreenRecorder {

    private static ThreadLocal<Process> ffmpegProcess = new ThreadLocal<>();
    private static ThreadLocal<String> videoPath = new ThreadLocal<>();

    public static void startRecording(String testName) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            String timestamp = java.time.LocalDateTime.now().toString().replaceAll("[:.]", "_");
            String fileName = testName + "_" + timestamp + ".mp4";
            String folderPath = "test-recordings";

            Files.createDirectories(Paths.get(folderPath));
            String fullPath = folderPath + File.separator + fileName;
            videoPath.set(fullPath);

            ProcessBuilder pb;

            if (os.contains("win")) {
                pb = new ProcessBuilder(
                        "ffmpeg", "-y",
                        "-f", "gdigrab",
                        "-framerate", "15",
                        "-i", "desktop",
                        "-preset", "ultrafast",
                        fullPath
                );
            } else if (os.contains("mac")) {
                pb = new ProcessBuilder(
                        "ffmpeg", "-y",
                        "-f", "avfoundation",
                        "-framerate", "15",
                        "-i", "1:none",
                        "-preset", "ultrafast",
                        fullPath
                );
            } else {
                pb = new ProcessBuilder(
                        "ffmpeg", "-y",
                        "-f", "x11grab",
                        "-framerate", "15",
                        "-i", ":0.0",
                        "-preset", "ultrafast",
                        fullPath
                );
            }

            pb.redirectErrorStream(true);
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);

            Process process = pb.start();
            ffmpegProcess.set(process);

            System.out.println("🎥 FFmpeg recording started: " + fullPath);

        } catch (Exception e) {
            System.err.println("❌ Failed to start FFmpeg recording: " + e.getMessage());
        }
    }

    public static void stopRecordingAndAttachToAllure() {
        try {
            Process process = ffmpegProcess.get();
            String path = videoPath.get();

            if (process != null) {
                process.destroy();
                process.waitFor();

                if (Files.exists(Paths.get(path))) {
                    System.out.println("✅ FFmpeg recording saved: " + path);
                    attachToAllure(path);
                } else {
                    System.err.println("❌ Video file does not exist or was not recorded: " + path);
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error stopping FFmpeg recording: " + e.getMessage());
        } finally {
            ffmpegProcess.remove();
            videoPath.remove();
        }
    }

    private static void attachToAllure(String videoFilePath) {
        try (InputStream is = Files.newInputStream(Paths.get(videoFilePath))) {
            Allure.addAttachment("Test Recording", "video/mp4", is, ".mp4");
        } catch (IOException e) {
            System.err.println("❌ Failed to attach video to Allure: " + e.getMessage());
        }
    }
}
