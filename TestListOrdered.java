package Project2;
import java.util.ArrayList;
import java.util.Comparator;

/**Test/main methods
 *
 * @author mitchelldoruff
 */
public class TestListOrdered {

  static ListOrdered<Student> students = new ListOrdered<>();

  static Student s1 = new Student("Amy","Larson",Major.CSC);
  static Student s2 = new Student("Mari","Jones");
  static Student s3 = new Student("Jai","Abid",Major.ART);
  static Student s4 = new Student("Inez","Zeus",Major.CSC);
  static Student s5 = new Student("Su","Quin",Major.MUS);
  static Student s6 = new Student("Nina","Martin");
  static Student keyOnly = new Student("key","only");
  
  
   //key comparators
  static Comparator<Object> compKeyFirst = new Comparator<Object>() { 
        public int compare(Object a, Object b){
            Student c2 = (Student)b;
            String c1 = (String)a;
            return c1.toLowerCase().compareTo(c2.first().toLowerCase());
        }
    };
    static Comparator<Object> compKeyLast = new Comparator<Object>() { 
        public int compare(Object a, Object b){
            Student c2 = (Student)b;
            String c1 = (String)a;
            return c1.toLowerCase().compareTo(c2.last().toLowerCase());
        }
    };
    static Comparator<Object> compKeyGpa = new Comparator<Object>() {
        public int compare(Object a, Object b) {
            Student c2 = (Student)b;
            double c1 = (Double)a;
            if (Math.abs((c1 - c2.gpa())) < .009) { return 0; }
            if ((c1 < c2.gpa())) { return -1; }
            if ((c1 > c2.gpa())) { return 1; }
            return 0;
        }
    };
    static Comparator<Object> compKeyId = new Comparator<Object>() {
        public int compare(Object a, Object b) {
            int c1 = (int)a;
            int c2 = (int)b;
            return c1 - c2;
        }
    };
     static Comparator<Object> compKeyMaj = new Comparator<Object>() {
        public int compare(Object a, Object b) {
            Major c1 = (Major)a;
            Student c2 = (Student)b;
            return c1.compareTo(c2.major());
        }
    };
    //Comparators
  static Comparator<Student> compFirst = new Comparator<Student>() { 
        @Override
        public int compare(Student s1, Student s2){
            return s1.first().toLowerCase().compareTo(s2.first().toLowerCase());
        }
    };
    static Comparator<Student> compLast = new Comparator<Student>() { 
        @Override
        public int compare(Student s1, Student s2){
            return s1.last().toLowerCase().compareTo(s2.last().toLowerCase());
        }
    };
    static Comparator<Student> compId = new Comparator<>() {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.id() - s2.id();
        }
    };
    static Comparator<Student> compMajor = new Comparator<>() {
        //Major
        @Override
        public int compare(Student s1, Student s2){
            return s1.major().compareTo(s2.major());
        }
    };
    static Comparator<Student> compGpa = new Comparator<>() {
        @Override
        public int compare(Student s1, Student s2) {
            if (Math.abs((s1.gpa() - s2.gpa())) < .009) { return 0; }
            if ((s1.gpa() < s2.gpa())) { return -1; }
            if ((s1.gpa() > s2.gpa())) { return 1; }
            return 0;
        }
    };

    
    
	public static void main(String[] args) throws InvalidIndexException, NullObjectException {
		System.out.println("\n*******    TestListOrdered.test     ************");
    testLength();
    testAdd();
    testNoDuplicates();
    testCapacity();
    testRemove();
    testFindBinary();
    testFindLinear();
    testReorder();
    testSort();
    testArrayListSort();
    testGetAll();
    testGetFirst();
    testGetRange();
    testRetainRange();
    testMaxMin();
    
            System.out.println("\n\nTesting exceptions\n\n");
            try {
            System.out.println(students.get(100));
            } catch (InvalidIndexException e) {
                System.out.println(e);
            }
            try {
            students.add(null);
            } catch (NullObjectException e) {
                System.out.println(e);
            }
    
    
    }

    public static void testReorder() throws InvalidIndexException, NullObjectException { //and sor(listOrdered)
        students.clear();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);

        students.reorder(compFirst);
        
    System.out.println("-----------------------------Testing reorder");
    
    if (students.get(0) != s1) {  // this is looking for object at same address
      System.out.println("Reorder test 1: "+students.get(0)+" expected "+s1.toString());
    }
    if (students.get(1) != s4) {  // this is looking for object at same address
      System.out.println("Reorder test 2: "+students.get(1)+" expected "+s4.toString());
    }
    if (students.get(2) != s3) {  // this is looking for object at same address
      System.out.println("Reorder test 3: "+students.get(2)+" expected "+s3.toString());
    }
    if (students.get(3) != s2) {  // this is looking for object at same address
      System.out.println("Reorder test 4: "+students.get(3)+" expected "+s2.toString());
    }
    if (students.get(4) != s5) {  // this is looking for object at same address
      System.out.println("Reorder test 5: "+students.get(4)+" expected "+s5.toString());
    }
        
    }
    
    public static void testSort() throws InvalidIndexException, NullObjectException { //sort(listordered, Comparator)
        students.clear();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        ListOrdered.sort(students, compLast);
        
        System.out.println("-----------------------------Testing SORT");
    
        if (students.get(0) != s3) {  // this is looking for object at same address
        System.out.println("Sort test 1: "+students.get(0)+" expected "+s3.toString());
        }
        if (students.get(1) != s2) {  // this is looking for object at same address
         System.out.println("Sort test 2: "+students.get(1)+" expected "+s2.toString());
        }
        if (students.get(2) != s1) {  // this is looking for object at same address
         System.out.println("Sort test 3: "+students.get(2)+" expected "+s1.toString());
        }
        if (students.get(3) != s5) {  // this is looking for object at same address
        System.out.println("Sort test 4: "+students.get(3)+" expected "+s5.toString());
        }
        if (students.get(4) != s4) {  // this is looking for object at same address
        System.out.println("Sort test 5: "+students.get(4)+" expected "+s4.toString());
        }
    }
    
    public static void testArrayListSort() throws InvalidIndexException, NullObjectException{
        students.clear();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        ArrayList<Student> temp = new ArrayList<>();
        temp = students.sort(compGpa);
        
        System.out.println("-----------------------------Testing testArrayListSort//visual inspection, ensure gpa is ");
        if (temp.size() != 5) {  // this is looking for object at same address
         System.out.println("List Sort test total: "+temp.size()+" expected "+5);
        }
        System.out.println(temp);
    }
        
    
    public static void testGetAll() throws InvalidIndexException, NullObjectException{
        students.clear();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        ArrayList<Student> temp = new ArrayList<>();
        temp = students.getAll((Object)Major.CSC, (Comparator)compKeyMaj);
        
        System.out.println("-----------------------------Testing testGetAll");
        if (temp.size() != 2) {  // this is looking for object at same address
         System.out.println("Get all test total: "+temp.size()+" expected "+2);
        }
        if (temp.get(0) != s1) {  // this is looking for object at same address
        System.out.println("Get all test 1: "+temp.get(0)+" expected "+s1.toString());
        }
        if (temp.get(1) != s4) {  // this is looking for object at same address
         System.out.println("get all test 2: "+temp.get(1)+" expected "+s4.toString());
        }
    }
    public static void testGetFirst() throws InvalidIndexException, NullObjectException{
        students.clear();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        Student temp = students.getFirst((Object)Major.CSC, (Comparator)compKeyMaj);
        
        System.out.println("-----------------------------Testing testGetFirst()");
        
        if (temp != s1) {  // this is looking for object at same address
        System.out.println("Get First test 1: "+temp.toString()+" expected "+s1.toString());
        }
    }
    public static void testGetRange() throws InvalidIndexException, NullObjectException{
        students.clear();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        ArrayList<Object> temp = students.getRange("Amy", "Jai", (Comparator)compKeyFirst);
        System.out.println("-----------------------------Testing GetRange");
        if (temp.size() != 3) {  // this is looking for object at same address
         System.out.println("Get Range test total: "+temp.size()+" expected "+3);
        }
        
        if (temp.get(0) != s1) {  // this is looking for object at same address
        System.out.println("Get all test 1: "+temp.get(0)+" expected "+s1.toString());
        }
        if (temp.get(1) != s4) {  // this is looking for object at same address
         System.out.println("Get Range test 2: "+temp.get(1)+" expected "+s4.toString());
        }
        if (temp.get(2) != s3) {  // this is looking for object at same address
         System.out.println("Get Range test 3: "+temp.get(2)+" expected "+s3.toString());
        }
        
    }
    public static void testRetainRange() throws InvalidIndexException, NullObjectException{
        students.clear();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        
        ArrayList<Student> temp = new ArrayList<>();
        students.retainRange("Amy", "Jai", (Comparator)compKeyFirst);
        
        System.out.println("-----------------------------Testing retain range");
        if (students.length() != 3) {  // this is looking for object at same address
         System.out.println("Retain test total: "+students.length()+" expected "+3);
        }
   
        if (students.get(0) != s1) {  // this is looking for object at same address
        System.out.println("Retain test 1: "+students.get(0)+" expected "+s1.toString());
        }
        if (students.get(1) != s4) {  // this is looking for object at same address
         System.out.println("Retain test 2: "+students.get(1)+" expected "+s4.toString());
        }
        if (students.get(2) != s3) {  // this is looking for object at same address
         System.out.println("Retain test 3: "+students.get(2)+" expected "+s3.toString());
        }
    }
    public static void testToArray() throws InvalidIndexException, NullObjectException{
        students.clear();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        Student[] temp = students.toArray();
        
        System.out.println("-----------------------------Testing toarray");
        if (temp.length != 3) {  // this is looking for object at same address
         System.out.println("To Arr test total: "+students.length()+" expected "+3);
        }
        
        if (students.get(0) != s1) {  // this is looking for object at same address
        System.out.println("To Arr test 1: "+students.get(0)+" expected "+s1.toString());
        }
        if (students.get(1) != s2) {  // this is looking for object at same address
         System.out.println("To Arr test 2: "+students.get(1)+" expected "+s2.toString());
        }
        if (students.get(2) != s3) {  // this is looking for object at same address
         System.out.println("To Arr test 3: "+students.get(2)+" expected "+s3.toString());
        }
    }
    public static void testMaxMin() throws InvalidIndexException, NullObjectException{
        students.clear();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        
        System.out.println("-----------------------------Testing min max");
        Student x = students.max(compLast);
        Student y = students.min(compFirst);
        if (!x.equals(s4)) {  // this is looking for object at same address
         System.out.println("To MAX 1: "+x.toString()+" expected "+s4.toString());
        }
        if (!y.equals(s1)) {  // this is looking for object at same address
         System.out.println("To MIN test 2: "+y.toString()+" expected "+s1.toString());
        }
        
    }
    
   

    
    public static void testRemoveRange() throws InvalidIndexException, NullObjectException {
    students.clear();
    students.add(s1);
    students.add(s2);
    students.add(s3);
    students.add(s4);
    students.add(s5);
    
    System.out.println("-----------------------------Testing removeRange");
    students.removeRange(s3, s5, compLast);
    if (1 != students.length()) {
      System.out.println("removeRange Fail: "+students.length()+" expected 1");
    }
    if (students.get(0) != s4) {  // this is looking for object at same address
      System.out.println("removeRange Fail: "+students.toString()+" expected "+s4.toString());
    }
    System.out.println();
    } // end test()

        
        
	public static void testLength() throws InvalidIndexException, NullObjectException {
    System.out.println("\n----------------------------- testLength() ---");
    students.clear();
    if (0 != students.length()) {
      System.out.println("Length clear: "+students.length()+" expected 0");
    }
    students.add(s1);
    if (1 != students.length()) {
      System.out.println("Length add at [0]: "+students.length()+" expected 1");
    }
    students.add(s2);
    if (2 != students.length()) {
      System.out.println("Length add at [1]: "+students.length()+" expected 2");
    }
    students.remove(s1);
    if (1 != students.length()) {
      System.out.println("Length remove at [0]: "+students.length()+" expected 1");
    }
    students.remove(s2);
    if (0 != students.length()) {
      System.out.println("Length remove at [1]: "+students.length()+" expected 0");
    }
  } // end testLength()

  public static void testNoDuplicates() throws InvalidIndexException, NullObjectException {
    System.out.println("\n------------------------- testNoDuplicates() ---");
    students.clear();
    if (0 != students.length()) {
      System.out.println("Length clear: "+students.length()+" expected 0");
    }
    students.add(s1);
    keyOnly.id(s1.id());
    students.add(keyOnly);
    if (1 != students.length()) {
      System.out.println("No Dups no add: "+students.length()+" expected 1");
    }
    // test that a student with the same name but different id does not match
    int length = students.length();
    students.add(s2);
    students.add(new Student("Mari","Jones"));
    if (length+2 != students.length()) {
      System.out.println("No Dups add: "+students.length()+" expected "+(length+2));
    }
  } // end testNoDuplicates()

	public static void testAdd() throws InvalidIndexException, NullObjectException {
    /** Testing add to list at ends and middle, maintaining order */
		System.out.println("\n---------------------------------- testAdd() ---");
    students.clear();

    students.add(s2);
    if (students.get(0) != s2) {  // this is looking for object at same address
      System.out.println("Add to empty at [0]: "+students.get(0)+" expected "+s2.toString());
    }
    students.add(s1);
    if (students.get(0) != s1) {  // this is looking for object at same address
      System.out.println("Add at [0] with 1 element: "+students.get(0)+" expected "+s1.toString());
    }
    students.add(s5);
    if (students.get(2) != s5) {  // this is looking for object at same address
      System.out.println("Add to last at [2]: "+students.get(2)+" expected "+s5.toString());
    }
    students.add(s3);
    if (students.get(2) != s3) {  // this is looking for object at same address
      System.out.println("Add middle at [2]: "+students.get(2)+" expected "+s3.toString());
    }
	}

	public static void testCapacity() throws InvalidIndexException, NullObjectException {
		System.out.println("\n------------------------------ testCapacity() ---");
    students.clear();
    students.add(s1);
    students.add(s2);
    students.add(s3);
    students.add(s4);
    students.add(s5);
    if (5 != students.length()) {
	System.out.println("Capacity Full length: "+students.length()+" expected 5");
    }
    students.add(new Student("asdf","asdf"));
    students.add(new Student("bqer","bsdf"));
    students.add(new Student("cwer","csdf"));
    students.add(new Student("excv","esdf"));
    students.add(new Student("wsdf","wxcv"));
    students.add(new Student("xsdf","xwer"));
    if (11 != students.length()) {
			System.out.println("Capacity  be: "+students.length()+" expected 11");
		}
    if (students.get(4) != s5) {
      System.out.println("Capacity Over element: "+students.get(4)+" expected "+s5);
    }

	} // end testAddUpToLimit

	public static void testRemove() throws InvalidIndexException, NullObjectException {
		System.out.println("\n ------------------------------ testRemove() --- ");
    students.clear();
    students.add(s1);
    students.add(s2);
    students.add(s3);
    students.add(s4);

    if (students.get(0) != s1) {
      System.out.println("Remove Confirm init [0]: "+students.get(0)+" expected "+s1);
    }
    if (students.get(1) != s2) {
      System.out.println("Remove Confirm init [1]: "+students.get(1)+" expected "+s2);
    }
    if (students.get(2) != s3) {
      System.out.println("Remove Confirm init [2]: "+students.get(2)+" expected "+s3);
    }
    if (students.get(3) != s4) {
      System.out.println("Remove Confirm init [3]: "+students.get(3)+" expected "+s4);
    }
    // Order of list confirmed to be s1, s2, s3, s4

    keyOnly.id(s2.id());
    students.remove(keyOnly);
    // Now list should be s1 s3 s4
    if (students.get(1) != s3) {
      System.out.println("Remove Middle: "+students.get(1)+" expected "+s3);
    }

    keyOnly.id(s1.id());
    students.remove(keyOnly);
    // Now list should be s3 s4
    if (students.get(0) != s3) {
      System.out.println("Remove First "+students.get(0)+". Expected "+s3);
    }
    keyOnly.id(s4.id());
    students.remove(keyOnly);
    // Now list should be s3
    if (students.get(0) != s3) {
      System.out.println("Remove Last "+students.get(0)+". Expected "+s3);
    }
    keyOnly.id(s3.id());
    students.remove(s3);
    // Now list should be empty
    if (!students.isEmpty()) {
      System.out.println("Remove Only Element, but not empty");
    }
  } // end testRemove

  public static void testFindBinary() throws InvalidIndexException, NullObjectException {
    System.out.println("\n --------------------------- testFindBinary() --- ");
    students.clear();
    students.add(s1);
    students.add(s2);
    students.add(s3);
    students.add(s4);
    students.add(s5);

    int index;
    index = students.find(s6);
    if (index != -1) {
      System.out.println("Find Binary not in list "+index+". Expected -1");
    }
    keyOnly.id(s1.id());
    index = students.find(keyOnly);
    if (index != 0) {
      System.out.println("Find Binary First. "+index+" expected 0");
    }
    keyOnly.id(s5.id());
    index = students.find(keyOnly);
    if (index != 4) {
      System.out.println("Find Binary Last. "+index+" expected 4");
    }
    keyOnly.id(s3.id());
    index = students.find(keyOnly);
    if (index != 2) {
      System.out.println("Find Binary Middle. "+index+" expected 2");
    }
  } // end testFindBinary()

  public static void testFindLinear() throws InvalidIndexException, NullObjectException {
    System.out.println("\n --------------------------- testFindLinear() --- ");
    students.clear();
    students.add(s1);
    students.add(s2);
    students.add(s3);
    students.add(s4);

    int index;
  
    
    index = students.find(keyOnly,compLast);
    if (index != -1) {
      System.out.println("Find Linear name not in list. "+index+" expected -1");
    }
    keyOnly.last(s1.last().toLowerCase()); keyOnly.first(s1.first().toUpperCase());
    index = students.find(keyOnly,compLast);
    if (index != 0) {
      System.out.println("Find Linear name. "+index+" expected 0");
    }
    keyOnly.last(s3.last()); keyOnly.first(s3.first());
    index = students.find(keyOnly,compLast);
    if (index != 2) {
      System.out.println("Find Linear name. "+index+" expected 2");
    }
    keyOnly.last(s4.last()); keyOnly.first(s4.first());
    index = students.find(keyOnly,compLast);
    if (index != 3) {
      System.out.println("Find Linear name. "+index+" expected 3");
    }


    
    keyOnly.major(Major.CHE);
    index = students.find(keyOnly,compMajor);
    if (index != -1) {
      System.out.println("Find Linear major in list. "+index+" expected -1");
    }
    keyOnly.major(s1.major());
    index = students.find(keyOnly,compMajor);
    if (index != 0) {
      System.out.println("Find Linear major. "+index+" expected 0");
    }
    keyOnly.major(s2.major());
    index = students.find(keyOnly,compMajor);
    if (index != 1) {
      System.out.println("Find Linear major. "+index+" expected 1");
    }


    index = students.find(s6,compGpa);
    if (index != -1) {
      System.out.println("Find Linear gpa not in list. "+index+" expected -1");
    }
    keyOnly.gpa(s1.gpa()+.0001);
    index = students.find(keyOnly,compGpa);
    if (index != 0) {
      System.out.println("Find Linear gpa. "+index+" expected 0");
    }
    keyOnly.gpa(s2.gpa()+.0001);
    index = students.find(keyOnly,compGpa);
    if (index != 1) {
      System.out.println("Find Linear gpa. "+index+" expected 1");
    }
    keyOnly.gpa(s4.gpa()+.0001);
    index = students.find(keyOnly,compGpa);
    if (index != 3) {
      System.out.println("Find Linear gpa. "+index+" expected 3");
    }
    
  } // end testFindLinear()

} // end class Teststudents
