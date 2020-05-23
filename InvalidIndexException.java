/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project2;

/** Exception for indices out of bounds
 *
 * @author mitchelldoruff
 */
public class InvalidIndexException extends Exception{
    private int index;
    private int capacity;
    private String method;
    private StackTraceElement[] trace;
    
    public InvalidIndexException(int index, int capacity, String method) {
        this.index = index;
        this.capacity = capacity;
        this.method = method;
        this.trace = this.getStackTrace();
    }
    /**Grabs the index passed
     *
     * @return index number
     */
    public int getIndex() {
        return index;
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

    /** to string sends error, method, exact location, index, and range
     *
     * @return "InvalidIndexException in " + method + "\n"+ trace + "\n"
                  + "index " + index + " not in range 0 to " + capacity;
     */
    @Override
    public String toString() {
        return "InvalidIndexException in " + method + "\n"+ trace + "\n"
                  + "index " + index + " not in range 0 to " + capacity;
    }
}
