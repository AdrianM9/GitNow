package filesystem;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

/**
 * Class which handles the execution of an invalid file system operation.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class InvalidFileSystemOperation extends FileSystemOperation {
    /**
     * Invalid file system operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public InvalidFileSystemOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute the invalid file system operation operation.
     *
     * @param fileSystemSnapshot the active file system snapshot
     * @return return code
     */
    @Override
    public int execute(FileSystemSnapshot fileSystemSnapshot) {
        return ErrorCodeManager.SYS_BAD_CMD_CODE;
    }
}
