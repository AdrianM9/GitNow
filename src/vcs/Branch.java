package vcs;

import filesystem.FileSystemSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for managing branches.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class Branch {
    private String name;
    private Branch parent;
    private List<Commit> childrenCommits;

    /**
     * Branch constructor for the master branch.
     *
     * @param name the name of the branch
     * @param snapshot  the file system that the branch is created on
     */
    public Branch(final String name, final FileSystemSnapshot snapshot) {
        this.name = name;
        this.parent = null;
        this.childrenCommits = new ArrayList<Commit>();
        this.childrenCommits.add(new Commit(snapshot));
    }

    /**
     * Branch constructor.
     *
     * @param name   the name of the branch
     * @param parent the parent branch of the new branch
     */
    public Branch(final String name, final Branch parent) {
        this.name = name;
        this.parent = parent;
        this.childrenCommits = new ArrayList<Commit>();
        this.childrenCommits.add(new Commit(parent.getHeadCommit(), ""));
    }

    /**
     * Returns the name of the branch.
     * @return the name of the branch
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list containing all the commits of the current branch.
     * @return a list containing all the commits of the branch
     */
    public List<Commit> getChildrenCommits() {
        return childrenCommits;
    }

    /**
     * Returns the head commit.
     * <p>
     *     The head commit is the commit that is currently opened.
     * </p>
     * @return the head commit
     */
    public Commit getHeadCommit() {
        return childrenCommits.get(childrenCommits.size() - 1);
    }

    /**
     * Adds a commit to the branch.
     * @param c the commit to be added
     */
    public void addCommit(final Commit c) {
        childrenCommits.add(c);
    }

    /**
     * Changes the list containing the commits to have the commit given as parameter and the ones
     * before it.
     * @param c the newer last commit of the branch
     */
    public void changeLastCommit(final Commit c) {
        childrenCommits = childrenCommits.subList(0, childrenCommits.indexOf(c) + 1);
    }
}
