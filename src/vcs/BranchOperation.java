package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

/**
 * Class which handles the execution of the branch operation.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class BranchOperation extends VcsOperation {
    /**
     * Branch operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public BranchOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the branch operation.
     *
     * @param vcs the vcs
     * @return the return code;
     */
    @Override
    public int execute(Vcs vcs) {
        // Find if there is another branch with the same name;
        for (Branch b : vcs.getChildrenBranches()) {
            if (b.getName().equals(operationArgs.get(0))) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }

        vcs.addBranch(new Branch(operationArgs.get(0), vcs.getCurrentBranch()));

        return ErrorCodeManager.OK;
    }
}
