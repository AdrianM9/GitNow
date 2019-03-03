package vcs;

import filesystem.FileSystemSnapshot;
import utils.IDGenerator;

/**
 * Class used for managing commits.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class Commit {
    private int id;
    private Commit parent;
    private FileSystemSnapshot snapshot;
    private String description;

    /**
     * Commit constructor for the first commit of the vcs.
     *
     * @param snapshot the file system that the commit is created on
     */
    public Commit(final FileSystemSnapshot snapshot) {
        this.id = IDGenerator.generateCommitID();
        this.parent = null;
        this.snapshot = snapshot.cloneFileSystem();
        this.description = "First commit";
    }

    /**
     * Commit constructor.
     *
     * @param parent      the parent commit of the new commit
     * @param description the description of the commit
     */
    public Commit(final Commit parent, final String description) {
        this.id = IDGenerator.generateCommitID();
        this.parent = parent;
        this.snapshot = parent.snapshot.cloneFileSystem();
        this.description = description;
    }

    /**
     * Returns the id of the commit as an Integer object.
     * @return the id of the commit
     */
    public Integer getId() {
        return new Integer(id);
    }

    /**
     * Returns the description of the commit.
     * @return the description of the commit.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the file system snapshot of the commit.
     * @return the file system snapshot of the commit
     */
    public FileSystemSnapshot getFileSystemSnapshot() {
        return snapshot;
    }

    /**
     * Sets the file system snapshot of the commit to the one given as parameter.
     * @param newSnapshot the new file system snapshot
     */
    public void setFileSystemSnapshot(final FileSystemSnapshot newSnapshot) {
        this.snapshot = newSnapshot;
    }
}
