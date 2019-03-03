package vcs;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.OutputWriter;
import utils.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * The class offers an interface to work with commits and branches and lets the user execute vcs
 * operations.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private FileSystemSnapshot initialSnapshot;
    private Branch currentBranch;
    private List<Branch> childrenBranches;
    private Commit head;
    private List<FileSystemOperation> stagedChanges;

    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.initialSnapshot = new FileSystemSnapshot(outputWriter);
        this.activeSnapshot = initialSnapshot.cloneFileSystem();
        this.currentBranch = new Branch("master", activeSnapshot);
        this.childrenBranches = new ArrayList<Branch>();
        childrenBranches.add(currentBranch);
        this.head = currentBranch.getHeadCommit();
        this.stagedChanges = new ArrayList<FileSystemOperation>();
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        return vcsOperation.execute(this);
    }

    /**
     * Returns the output writer of the vcs.
     * @return the output writer
     */
    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    /**
     * Returns the current branch.
     * @return the current branch.
     */
    public Branch getCurrentBranch() {
        return currentBranch;
    }

    /**
     * Sets the current branch of the vcs to the branch given as parameter.
     * @param b the new branch
     */
    public void setCurrentBranch(final Branch b) {
        currentBranch = b;
    }

    /**
     * Returns the list containing all the branches of the vcs.
     * @return a list containing all the branches
     */
    public List<Branch> getChildrenBranches() {
        return childrenBranches;
    }

    /**
     * Returns the head commit.
     * @return the head commit
     */
    public Commit getHead() {
        return head;
    }

    /**
     * Returns the list containing all the file system operations that should be saved in a new
     * commit.
     * @return a list containing the staged changes
     */
    public List<FileSystemOperation> getStagedChanges() {
        return stagedChanges;
    }

    /**
     * Adds a new branch.
     * @param branch the branch to be added
     */
    public void addBranch(final Branch branch) {
        childrenBranches.add(branch);
    }

    /**
     * Adds an operation to the list of staged changes.
     * @param operation the operation to be added
     */
    public void addStagedChange(final FileSystemOperation operation) {
        stagedChanges.add(operation);
    }

    /**
     * Does the required changes when a commit (the one given as parameter) is added.
     * @param c the commit to be added
     */
    public void addCommit(final Commit c) {
        head.setFileSystemSnapshot(initialSnapshot.cloneFileSystem());
        initialSnapshot = activeSnapshot.cloneFileSystem();
        c.setFileSystemSnapshot(initialSnapshot);
        currentBranch.addCommit(c);
        head = c;
    }

    /**
     * Changes the head commit of the vcs with the one given as parameter.
     * @param c the new head commit
     */
    public void changeHead(final Commit c) {
        head = c;
        initialSnapshot = c.getFileSystemSnapshot().cloneFileSystem();
        activeSnapshot = initialSnapshot;
    }

    /**
     * Clears the list containing the staged changes.
     */
    public void clearStagedChanges() {
        stagedChanges.clear();
    }
}
