package org.josh.jcri;

import javax.annotation.ParametersAreNonnullByDefault;

/**Base class contains method's result replied by browser.
 @see CommandBase
 @author Joshua */
@ParametersAreNonnullByDefault
public abstract class ResultBase implements CommonDomainType {
    /**Method Id.*/
    private long _id;
    /**Error message of method is failed.*/
    private final String _errorMessage;

    /**Construct a failed result instance.*/
    public static FailedResult ofError(String errorMessage) { return new FailedResult(errorMessage); }

    /**Error message holder internal class.
     This class is for de-ambiguous from success case and failed case. */
    protected static class FailedResult {
        private final String message;
        private FailedResult(String msg) { message = msg; }
    }

    /**Create a success method result instance.*/
    protected ResultBase() { _errorMessage = ""; }
    /**Create a failed method result instance.*/
    protected ResultBase(FailedResult failedResult) { _errorMessage = failedResult.message; }

    /**Set method's Id.*/
    final void setId(long id) { _id = id; }
    /**Get method's Id.*/
    public final long getId() { return _id; }
    /**Get if this result is success or not.*/
    public final boolean isSuccess() { return _errorMessage.isEmpty(); }
    /**Get error message.*/
    public final String getError() { return _errorMessage; }
}
