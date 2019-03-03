package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

/**
 * Class which handles the execution of the checkout operation.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class CheckoutOperation extends VcsOperation {
    /**
     * Checkout operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public CheckoutOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the checkout operation.
     *
     * @param vcs the vcs
     * @return the return code;
     */
    @Override
    public int execute(Vcs vcs) {
        // The list of staged changes should be empty.
        if (!vcs.getStagedChanges().isEmpty()) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }

        if (operationArgs.get(0).equals("-c")) {
            // Change head if the commit exists on the current branch.
            for (Commit c : vcs.getCurrentBranch().getChildrenCommits()) {
                if (c.getId().toString().equals(operationArgs.get(1))) {
                    vcs.changeHead(c);
                    vcs.getCurrentBranch().changeLastCommit(c);
                    return ErrorCodeManager.OK;
                }
            }

            return ErrorCodeManager.VCS_BAD_PATH_CODE;
        } else {
            // Change branch if it exists.
            for (Branch b : vcs.getChildrenBranches()) {
                if (b.getName().equals(operationArgs.get(0))) {
                    vcs.setCurrentBranch(b);
                    return ErrorCodeManager.OK;
                }
            }

            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }
    }
}
