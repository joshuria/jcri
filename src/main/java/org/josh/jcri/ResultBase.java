package org.josh.jcri;

import javax.annotation.ParametersAreNonnullByDefault;

/**Base class contains method's result replied by browser.
 @see CommandBase
 @author Joshua */
@ParametersAreNonnullByDefault
public abstract class ResultBase implements CommonDomainType {
    /**Method Id.*/
    private long _id;
    /**Error code returned by browser.*/
    private final long _errorCode;
    /**Error message of method is failed.*/
    private final String _errorMessage;

    /**Construct a failed result instance.*/
    public static FailedResult ofError(long code, String msg) {
        return new FailedResult(code ,msg);
    }

    /**Error message holder internal class.
     This class is for de-ambiguous from success case and failed case. */
    protected static class FailedResult {
        private final long code;
        private final String message;
        private FailedResult(long code, String msg) { this.code = code; this.message = msg; }
    }

    /**Create a success method result instance.*/
    protected ResultBase() { _errorMessage = ""; _errorCode = 0; }
    /**Create a failed method result instance.*/
    protected ResultBase(FailedResult failedResult) {
        _errorCode = failedResult.code;
        _errorMessage = failedResult.message;
    }

    /**Set method's Id.*/
    final void setId(long id) { _id = id; }
    /**Get method's Id.*/
    public final long getId() { return _id; }
    /**Get error code returned by browser.*/
    public final long getCode() { return _errorCode; }
    /**Get if this result is success or not.*/
    public final boolean isSuccess() { return _errorMessage.isEmpty(); }
    /**Get error message.*/
    public final String getError() { return _errorMessage; }
}
