package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

/**
 * Class which handles the execution of the log operation.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class LogOperation extends VcsOperation {
    /**
     * Vcs operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the log operation.
     *
     * @param vcs the vcs
     * @return the return code;
     */
    @Override
    public int execute(Vcs vcs) {
        for (Commit c : vcs.getCurrentBranch().getChildrenCommits()) {
            vcs.getOutputWriter().write("Commit id: " + c.getId() + "\nMessage: "
                    + c.getDescription() + "\n");
            // If the commit is not the last one, print another new line.
            if (!c.equals(vcs.getHead())) {
                vcs.getOutputWriter().write("\n");
            }
        }

        return ErrorCodeManager.OK;
    }
}
