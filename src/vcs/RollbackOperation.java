package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

/**
 * Class which handles the execution of the rollback operation.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class RollbackOperation extends VcsOperation {
    /**
     * Vcs operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the rollback operation.
     *
     * @param vcs the vcs
     * @return the return code;
     */
    @Override
    public int execute(Vcs vcs) {
        vcs.clearStagedChanges();
        vcs.changeHead(vcs.getHead());

        return ErrorCodeManager.OK;
    }
}
