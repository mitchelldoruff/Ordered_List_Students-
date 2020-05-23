package Project2;
import java.util.*;
import java.util.Random;

/** Basic Student class to hold relevant data for a college student.
Makes use of the enumerated type Major (in the file Major.java).
Must implement compareTo(), which defines the default key field (to be used with ListOrdered), and equals (to be used with ListOrdered).
*/
public class Student implements Comparable<Student>{
    
  /** Unique ID for each new Student */
  private static Integer nextId = 100100;

  /** Generate new unique id
  @return unique id
  */
  public static int getNewID() {
    ++nextId;
    return nextId;
  }

  /** last name */
  private String last;
  /** first name */
  private String first;
  /** major (enum type) */
  private Major major = Major.UNDECLARED;
  /** unique student id */
  private Integer id = null;
  /** grade point average */
  private Double gpa = 0.0;

  // Used to generate random gpa's for students for testing.
  static private final Random random = new Random();

  /** Constructor with student name and major. Creates unique id.
  @param inFirst first name
  @param inLast last name
  @param inMajor declared major (default enum UNDECLARED)
  */
  public Student(String inFirst, String inLast, Major inMajor ) {
    first = inFirst;
    last = inLast;
    major = inMajor;
    id = getNewID();

    // Generate random gpa for testing purposes.
    gpa = Double.valueOf(random.nextInt(4)+ random.nextDouble());
  }

  /** Constructor with student name only. "Major" default enum UNDECLARED.
  Calls other constructor to create unique id.
  */
  public Student(String inFirst, String inLast) {
    this(inFirst, inLast, Major.UNDECLARED);
  }

  /** println example: 1001001 Larson, Amy : CSC (3.821) */
  public String toString() {
    String gpa = String.format("%.3f", gpa());
    return id()+" "+last()+", "+first()+" : "+major+" ("+gpa+")";
  }

  /** Getter for both first and last name */
  public String fullName() { return first+" "+last; }
  /** Member variable getter */
  public String last() { return last; }
  /** Member variable getter */
  public String first() { return first; }
  /** Member variable getter */
  public Major major() { return major; }
  /** Member variable getter */
  public Integer id() { return id; }
  /** Member variable getter */
  public Double gpa() { return gpa; }

  /** Member variable setter */
  public void major(Major newMajor) { major = newMajor; }
  /** Member variable setter */
  public void gpa(Double newGPA) { gpa = newGPA; }
  /** Member variable setter */
  public void last(String newLast) { last = newLast; }
  /** Member variable setter */
  public void first(String newFirst) { first = newFirst; }
  /** Member variable setter. */
  public void id(Integer n) { id = n; }

    /**  Equals function overrides object and checks to make sure all member variables are the same
     *
     * @param s is the object to check if equaled
     * @return wether all member variables are the same true or false
     */
    @Override
  public boolean equals(Object s) {
      if (s == this) { return true; }
      if (!(s instanceof Student)) {return false;}
      Student a = (Student) s;
      return Double.compare(gpa, a.gpa) == 0 && 
              this.fullName().equalsIgnoreCase(a.fullName()) &&
              this.major.equals(a.major()) && 
              this.id == a.id();
      }

//    /**Compare to function compares Student's last names, if last names are the same 
//     * the function will compare the first names
//     * @param s is the object to be compared to by this
//     * @return value of (this object) - that object
//     */
//    @Override
//    public int compareTo(Student s) {
//        int x = this.last.toLowerCase().compareTo(s.last().toLowerCase());
//        if (x == 0) {
//            return this.first.toLowerCase().compareTo(s.first().toLowerCase());
//        }
//        return x;
//    }
    
    /**Compare to function compares Student's ID numbers
     * @param s is the object to be compared to by this
     * @return value of (this object) - that object
     */
    @Override
    public int compareTo(Student s) {
        return id.compareTo(s.id());
        //return this.id.compareTo(s.id());
    }
}
