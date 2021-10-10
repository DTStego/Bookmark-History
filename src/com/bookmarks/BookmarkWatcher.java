package com.bookmarks;

import java.io.IOException;
import java.nio.file.*;

public class BookmarkWatcher
{
    public static void watcher() throws IOException, InterruptedException
    {
        // Creates a WatchService and links it with Google Chrome's Bookmark Folder (Doesn't link to specific file)
        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default");

        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey watchKey;
        while ((watchKey = watchService.take()) != null) {
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                /* If the change was the bookmark file:
                        - Run Bookmark Checker
                        - Sync to Google Drive
                        - Replace or declare a mismatch on laptop
                 */
                if (event.context().toString().equals("Bookmarks"))
                    BookmarkHistory.bookmarkChecker();
            }
            watchKey.reset();
        }
    }
}
