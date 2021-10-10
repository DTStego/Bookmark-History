package com.bookmarks;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BookmarkHistory
{
    public static void bookmarkChecker() throws IOException
    {
        if (new File(Bookmark.BOOKMARKFOLDER.getLocation()).mkdirs())
            System.out.println("Made new bookmark folder!");

        File bookmark = new File(Bookmark.OLDEST.getLocation());

        // If an "Oldest" bookmark doesn't exist
        if (!bookmark.exists())
        {
            bookmark = new File(Bookmark.OLD.getLocation());

            // If an "Old" bookmark doesn't exist
            if (!bookmark.exists())
            {
                bookmark = new File(Bookmark.NEW.getLocation());

                // If a "New" bookmark doesn't exist
                if (!bookmark.exists())
                {
                    // Copies bookmark from google folder to bookmark backups folder
                    Files.copy(Paths.get(Bookmark.GOOGLE.getLocation()), Paths.get(Bookmark.NEW.getLocation()));
                }
                // In here, only the "New" bookmark exists
                else
                {
                    // Checks if the "New" bookmark is within 5 kilobytes of the Google bookmark
                    if (compareBookmarks())
                    {
                        backupBookmark(bookmark);
                    }
                }
            }
            else
            {
                if (compareBookmarks())
                {
                    backupBookmark(bookmark);
                }
            }
        }
        else
        {
            if (compareBookmarks())
            {
                backupBookmark(bookmark);
            }
        }
    }

    /**
     * Compares "New" bookmark file with Google bookmark.
     * Opens "User/Desktop/Bookmark Backups" folder if there's a 5 KB difference or greater to alert user of mismatch.
     *
     * @return true if both are within 5 kilobytes in size and false otherwise or if both bookmarks are the same size.
     * **/
    public static boolean compareBookmarks() throws IOException
    {
        long parameterBookmark = Files.size(Paths.get(Bookmark.NEW.getLocation()));
        long googleBookmarkSize = Files.size(Paths.get(Bookmark.GOOGLE.getLocation()));

        // If both bookmarks are the same size
        if (parameterBookmark == googleBookmarkSize)
            return false;

        // If both bookmarks are within 5 kilobytes of each other
        if (parameterBookmark >= googleBookmarkSize - 5120 && parameterBookmark <= googleBookmarkSize + 5120)
        {
            return true;
        }
        else
        {
            try
            {
                Desktop.getDesktop().open(new File(Bookmark.BOOKMARKFOLDER.getLocation()));
                System.exit(0);
            } catch (IOException ioException)
            {
                System.out.println("Bookmark Backups folder does not exist. Fatal error!");
                ioException.printStackTrace();
            }

            return false;
        }
    }

    /**
     * Changes parameter bookmark name to whichever bookmark name is next in line (New -> Old -> Oldest -> gets deleted).
     * Assume there are no other bookmarks below parameter bookmark. It means you should only care about bookmarks above.
     * If the parameter file is the oldest, delete file and move the next two in line to their next name.
     * Copies the Google bookmark to the "Bookmark Backups" folder and gives it the "New" suffix.
     *
     * @param bookmark Existing bookmark for reference in method.
     */
    public static void backupBookmark(File bookmark) throws IOException
    {
        if (bookmark.getPath().equals(Bookmark.OLDEST.getLocation()))
        {
            // delete() returns a confirmation boolean that can be ignored
            bookmark.delete();
            bookmark = new File(Bookmark.OLD.getLocation());
        }

        if (bookmark.getPath().equals(Bookmark.OLD.getLocation()))
        {
            Files.copy(Paths.get(Bookmark.OLD.getLocation()), Paths.get(Bookmark.OLDEST.getLocation()), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
            bookmark = new File(Bookmark.NEW.getLocation());
        }

        if (bookmark.getPath().equals(Bookmark.NEW.getLocation()))
        {
            Files.copy(Paths.get(Bookmark.NEW.getLocation()), Paths.get(Bookmark.OLD.getLocation()), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
        }
        else
        {
            System.out.println("Error! File doesn't match any of the three defined bookmark locations");
            return;
        }

        Files.copy(Paths.get(Bookmark.GOOGLE.getLocation()), Paths.get(Bookmark.NEW.getLocation()), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
    }
}
