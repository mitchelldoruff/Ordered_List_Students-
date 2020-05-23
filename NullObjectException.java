/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project2;

/**Throws exception if null pointer is passed as an arguement
 * @author mitchelldoruff
 */
public class NullObjectException extends Exception {
    private String method;
    private StackTraceElement[] trace;
    
    /**Constructor grabs method and specific location
     *
     * @param method method which the error occurred
     */
    public NullObjectException(String method) {
        this.method = method;
        this.trace = this.getStackTrace();
    }

    /**Grabs exact location
     *
     * @return traced stack
     */
    public StackTraceElement[] getTrace() {
        return trace;
    }

    /**Grabs method 
     *
     * @return method which the exception occurred
     */
    public String getMethod() {
        return method;
    }

    /** sends error, method, and exact location
     *
     * @return "NullObjectException in " + method + "\n"+ trace + "\n";
     */
    @Override
    public String toString() {
        return "NullObjectException in " + method + "\n"+ trace + "\n";
    }
}
