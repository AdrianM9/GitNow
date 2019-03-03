package utils;

/**
 * This enum contains enum constants used for associating every operation the corresponding type.
 * @author Teodor-Adrian Mirea, 323CA
 */
public enum OperationType {
    CAT,
    CHANGEDIR,
    LIST,
    MAKEDIR,
    REMOVE,
    TOUCH,
    WRITETOFILE,
    PRINT,
    FILESYSTEM_INVALID_OPERATION,

    STATUS,
    BRANCH,
    COMMIT,
    CHECKOUT,
    LOG,
    ROLLBACK,
    VCS_INVALID_OPERATION
}
