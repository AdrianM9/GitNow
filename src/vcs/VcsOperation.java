package vcs;

import utils.AbstractOperation;
import utils.OperationType;

import java.util.ArrayList;

/**
 * Abstract class which handle the execution of the vcs operations.
 * @author Teodor-Adrian Mirea, 323CA
 */
public abstract class VcsOperation extends AbstractOperation {
    /**
     * Vcs operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public VcsOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the operation.
     *
     * @param vcs the vcs
     * @return the return code
     */
    public abstract int execute(Vcs vcs);

    /**
     * Accepts the vcs visitor.
     * @param vcs the vcs visitor
     * @return the return code
     */
    @Override
    public final int accept(Vcs vcs) {
        return vcs.visit(this);
    }
}
