package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

/**
 * Class which handles the execution of the commit operation.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class CommitOperation extends VcsOperation {
    /**
     * Vcs operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the commit operation.
     *
     * @param vcs the vcs
     * @return the return code;
     */
    @Override
    public int execute(Vcs vcs) {
        // The list of staged changes should not be empty.
        if (vcs.getStagedChanges().isEmpty()) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        // The first argument must be "-m".
        if (!operationArgs.get(0).equals("-m")) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        Commit c = new Commit(vcs.getHead(), createDescription());

        vcs.addCommit(c);
        vcs.clearStagedChanges();

        return ErrorCodeManager.OK;
    }

    /*
     * Creates the description for the commit based on the arguments of the command.
     * @return the description of the commit
     */
    private String createDescription() {
        StringBuffer description = new StringBuffer();

        // Removing the first argument: -m.
        operationArgs.remove(0);

        for (String word : operationArgs) {
            description.append(word + " ");
        }

        // Delete the last blank space added.
        description.deleteCharAt(description.length() - 1);

        return description.toString();
    }
}
