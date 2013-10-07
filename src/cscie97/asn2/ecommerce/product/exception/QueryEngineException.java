package cscie97.asn2.ecommerce.product.exception;

/**
 * Exception for problems that the {@link cscie97.asn2.ecommerce.product.SearchEngine} may run into during typical
 * query execution.
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see cscie97.asn2.ecommerce.product.SearchEngine
 */
public class QueryEngineException extends Exception {

    /**
     * The original query string that triggered the exception
     */
    private String query;

    /**
     * The string line where the original exception originated in the import file
     */
    private String lineWhereFailed;

    /**
     * The line number of the import file that triggered the original exception
     */
    private int lineIndexWhereFailed;

    /**
     * Name of the imported file that triggered the original exception
     */
    private String filename;

    /**
     * The original exception that this class wraps with more specific information
     */
    private Throwable originalCause;

    /**
     * Wraps a more generic exception that may have been thrown in the
     * {@link cscie97.asn2.ecommerce.product.SearchEngine} class.  Arguments contain more specific details about
     * the exception to simplify debugging.
     *
     * @param msg       the exception message from the throwing cause
     * @param query     the string value of the line that caused the exception
     * @param lineNum   the line number in the file that caused the exception
     * @param filename  the filename that was the cause of the original exception
     * @param cause     the wrapped lower-level exception that triggered this exception's creation
     */
    public QueryEngineException (String msg, String query, int lineNum, String filename, Throwable cause) {
        super("QueryEngineException occurred on query [" + query + "] of query file " + filename + " in line number [" + lineNum + "]", cause);

        this.query = query;
        this.lineIndexWhereFailed = lineNum;
        this.filename = filename;
        this.originalCause = cause;
    }

    /**
     * Returns the original string query that triggered the exception
     *
     * @return   string query that triggered the exception
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the original string query that triggered the exception
     *
     * @param  query   string query that triggered the exception
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Returns the string value of the entire line in the original file that caused the wrapped exception
     *
     * @return  the string value of the line where the original exception occurred
     */
    public String getLineWhereFailed() {
        return lineWhereFailed;
    }

    /**
     * Sets the string value of the entire line in the original file that caused the wrapped exception
     *
     * @param lineWhereFailed  the string value of the line that caused the exception
     */
    public void setLineWhereFailed(String lineWhereFailed) {
        this.lineWhereFailed = lineWhereFailed;
    }

    /**
     * Returns the line number where the original exception occurred
     *
     * @return  the line number where the original exception occurred
     */
    public int getLineIndexWhereFailed() {
        return lineIndexWhereFailed;
    }

    /**
     * Sets the line number where the original exception occurred
     *
     * @param lineIndexWhereFailed  the line number where the original exception occurred
     */
    public void setLineIndexWhereFailed(int lineIndexWhereFailed) {
        this.lineIndexWhereFailed = lineIndexWhereFailed;
    }

    /**
     * Returns the original filename that triggered the exception
     *
     * @return  the original filename that triggered the exception
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the original filename that triggered the exception
     *
     * @param  filename   the original filename that triggered the exception
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Returns the original exception that the ImportException is wrapping with more specific details
     *
     * @return  the original wrapped exception
     */
    public Throwable getOriginalCause() {
        return originalCause;
    }

    /**
     * Sets the original exception that the ImportException is wrapping with more specific details
     *
     * @param originalCause   the original wrapped exception
     */
    public void setOriginalCause(Throwable originalCause) {
        this.originalCause = originalCause;
    }

}