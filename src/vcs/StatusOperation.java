package vcs;

import filesystem.FileSystemOperation;
import utils.AbstractOperation;
import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which handle the execution of the status operation.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class StatusOperation extends VcsOperation {
    /**
     * Status operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the status operation.
     *
     * @param vcs the vcs
     * @return the return code;
     */
    @Override
    public int execute(Vcs vcs) {
        List<FileSystemOperation> stagedChanges = vcs.getStagedChanges();

        vcs.getOutputWriter().write("On branch: " + vcs.getCurrentBranch().getName()
                + "\nStaged changes:\n");

        for (AbstractOperation op : stagedChanges) {
            vcs.getOutputWriter().write("\t");

            switch (op.getType()) {
                case MAKEDIR:
                    // The directory's name is the operation's second argument.
                    vcs.getOutputWriter().write("Created directory "
                            + op.getOperationArgs().get(1) + "\n");
                    break;
                case TOUCH:
                    // The file's name is the operation's second argument.
                    vcs.getOutputWriter().write("Created file "
                            + op.getOperationArgs().get(1) + "\n");
                    break;
                case WRITETOFILE:
                    // The file's path is the operation's first argument.
                    String pathWf = op.getOperationArgs().get(0);
                    String file;

                    // Extracting the file's name from the path.
                    if (pathWf.contains("/")) {
                        file = pathWf.substring(pathWf.lastIndexOf("/") + 1);
                    } else {
                        file = pathWf;
                    }

                    vcs.getOutputWriter().write("Added \"" + op.getOperationArgs().get(1)
                            + "\" " + "to file " + file + "\n");
                    break;
                case REMOVE:
                    // The path of the file/directory is operation's last argument.
                    String pathRm = op.getOperationArgs().get(op.getOperationArgs().size() - 1);
                    String entityNameRm;

                    // Extracting the name of the file/directory from the path.
                    if (pathRm.contains("/")) {
                        entityNameRm = pathRm.substring(pathRm.lastIndexOf("/") + 1);
                    } else {
                        entityNameRm = pathRm;
                    }

                    vcs.getOutputWriter().write("Removed " + entityNameRm + "\n");
                    break;
                case CHANGEDIR:
                    // The directory's path is the operation's first argument.
                    String pathCd = op.getOperationArgs().get(0);
                    String directory;

                    // Extracting the directory's name from the path.
                    if (pathCd.contains("/")) {
                        directory = pathCd.substring(pathCd.lastIndexOf("/") + 1);
                    } else {
                        directory = pathCd;
                    }
                    vcs.getOutputWriter().write("Changed directory to " + directory
                            + "\n");
                    break;
                default:
                    return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }

        return ErrorCodeManager.OK;
    }
}
