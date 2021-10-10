# Bookmark-History
Due to a certain bug with Chrome, there exists an error with bookmarks where Chrome will only sync an old version of the bookmarks file, i.e., you effectively can't sync your new bookmarks file if an older one existed, even if you tried to import a new bookmark folder.

When you have an enormous amount of bookmarks and syncing doesn't work, you might want to have a local backup of your precious bookmarks!

## Installation

Download the zip file and extract into your project. Call BookmarkWatcher.watcher() which runs a for-loop, checking your Google Bookmark file (Checks the default location "\AppData\Local\Google\Chrome\User Data\Default\Bookmarks" [Note that the file doesn't have an extension]) with three backup bookmarks (Creates them if there are currently none on your desktop in a folder called Bookmark Backups). 

## Notes

This is meant to be a backup that adjusts for small changes, e.g., if you deleted a webpage from your bookmarks. Any major adjustments to your bookmarks file will incur a bookmark mismatch and the bookmarks folder will popup, indicating a dramatic change in your main bookmark file compared to your backups. This is useful if, for example, Chrome decides to sync your bookmarks to a different version and you lose your bookmark file. This program will ensure your bookmarks will be saved (Program defines a major change/mismatch as 5KB or more).
