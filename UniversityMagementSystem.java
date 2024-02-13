import java.util.*;

//person class
abstract class Person {
    int id;
    String name;
    int age;

    // contructor
    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    abstract public void getDetails();
}

// course class
class Course {
    int courseCode;
    String courseName;
    int credits;

    // contructor
    public Course(int courseCode, String courseName, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
    }

    // getters
    public int getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }
}

// student class extending person class
class Student extends Person {
    int studentid;
    ArrayList<Course> enrolled = new ArrayList<>();
    HashMap<Integer, ArrayList<Course>> mp = new HashMap<>();

    // contructor
    public Student(int id, String name, int age, int studentid) {
        super(id, name, age);
        this.studentid = this.id;
    }

    // getters
    public int getStudentid() {
        return studentid;
    }

    public ArrayList<Course> getEnrolled() {
        return enrolled;
    }

    // function to enroll into the course
    public void enrollCourse(Course c, int stuid) {
        enrolled.add(c);
        mp.put(stuid, enrolled);
    }

    // function to print details of each student
    public void getDetails() {
        System.out.println("Student id: " + getStudentid() + ", Student name: " + getName());
    }

    // displaying enrollled courses corresponding to their student id
    public void displayEnroll(int stuid) {
        System.out.println("Student id: " + stuid + ", Enrolled courses: ");
        ArrayList<Course> arr = mp.get(stuid);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).getCourseName() + " ");
        }
    }
}

// faculty class extending person class
class Faculty extends Person {
    int employeeID;
    String department;
    ArrayList<Course> teach = new ArrayList<>();
    HashMap<Integer, ArrayList<Course>> mp = new HashMap<>();

    // constructor
    public Faculty(int id, String name, int age, int employeeID, String department) {
        super(id, name, age);
        this.employeeID = this.id;
        this.department = department;
    }

    // getters
    public int getemployeeID() {
        return employeeID;
    }

    public String getDepartment() {
        return department;
    }

    // enrolling faculty into the courses
    public void teachCourse(Course c, int empID) {
        teach.add(c);
        mp.put(empID, teach);
    }

    // getting details of the faculty
    public void getDetails() {
        System.out.println("Employee id: " + getemployeeID() + ", Employee name: " + getName()
                + ", Employee department: " + getDepartment());
    }

    // displaying faculty courses corresponding to their id
    public void displayTeacherEnroll(int empID) {
        System.out.println("FAculty id: " + empID + ", Teaching courses: ");
        ArrayList<Course> arr = mp.get(empID);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).getCourseName() + " ");
        }
    }

}

class University {
    ArrayList<Student> st = new ArrayList<>();
    ArrayList<Faculty> fac = new ArrayList<>();
    ArrayList<Course> cou = new ArrayList<>();

    // add student
    public void addStudent(Student s) {
        for(int i=0;i<st.size();i++){
            if (st.get(i).getStudentid() == s.getStudentid()) {
                System.out.println("Student with this id already exist!!");
                return;
            }
        }
        st.add(s);
        System.out.println("Student added successfully..");
    }

    // remove student
    public void removeStudent(int stuID) {
        for (int i = 0; i <st.size(); i++) {
            if(st.get(i).getStudentid() == stuID){
                st.remove(st.get(i));
                System.out.println("Student removed successfully..");
                return;
            }
        }
        System.out.println("ID doesn't exist!!");

    }

    // add faculty
    public void addFaculty(Faculty f) {
        for(int i=0;i<fac.size();i++){
            if (fac.get(i).getemployeeID() == f.getemployeeID()) {
                System.out.println("Faculty with this id already exist!!");
                return;
            }
        }
        fac.add(f);
        System.out.println("Faculty added successfully..");
    }

    // remove faculty
    public void removeFaculty(int facID) {
        for (int i=0;i<fac.size();i++) {
            if (fac.get(i).getemployeeID() == facID) {
                fac.remove(fac.get(i));
                System.out.println("Faculty removed successfully..");
                break;
            }
        }
        System.out.println("ID doesn't exist!!");
    }

    // add course
    public void addCourse(Course c) {
        for(int i=0;i<cou.size();i++){
            if (cou.get(i).getCourseCode() == c.getCourseCode()) {
                System.out.println("Course with this id already exist!!");
                return;
            }
        }
        cou.add(c);
        System.out.println("Course added successfully..");
    }

    // remove course
    public void removeCourse(int cID) {
        for(int i=0;i<cou.size();i++){
            if (cou.get(i).getCourseCode() == cID) {
                cou.remove(cou.get(i));
                System.out.println("Course removed successfully..");
                return;
            }
        }
        System.out.println("ID doesn't exist..");
    }

    // displays available courses
    public boolean coursesAvailabale() {
        boolean f= true;
        if(!cou.isEmpty())
        {
            for (Course c : cou) {
                System.out.println("Course Code: " + c.courseCode + ", Course Name: " + c.courseName + ", Credit hours: "
                        + c.getCredits());
            }
        }
        else{
            System.out.println("Please add some courses!!");
            f=false;
        }

        return f;
       
    }

    // student enrollment into the courses
    public void studentEnroll(int code, int stu) throws IllegalArgumentException {
       try{
        for (Student s : st) {
            if (stu == s.getStudentid()) {
                Student stuEn = s;
                for (Course c : cou) {
                    if (c.getCourseCode() == code) {
                        stuEn.enrollCourse(c, stu);
                        break;
                    }
                }
            }
        }
       }
       catch(IllegalArgumentException e){
         System.out.println("Caught IllegalArgumentException: " + e.getMessage());
       }
       
    }

    // teacher enrollment into courses
    public void teacherEnroll(int code, int emp) throws IllegalArgumentException{
        try{
            for (Faculty f : fac) {
                if (emp == f.getemployeeID()) {
                    Faculty fEn = f;
                    for (Course c : cou) {
                        if (c.getCourseCode() == code) {
                            fEn.teachCourse(c, emp);
                            break;
                        }
                    }
                }
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("Caught IllegalArgumentException: " + e.getMessage());
          }
    }
   

    // display Details of the university
    public void displayUniDetails() {
        System.out.println("ENROLLED STUDENTS: ");
        for (Student s : st) {
            s.getDetails();
        }
        System.out.println("TEACHING FACULTY: ");
        for (Faculty f : fac) {
            f.getDetails();
        }
        System.out.println("COURSES AVAILABlE: ");
        for (Course c : cou) {
            System.out.println("Course Code: " + c.courseCode + ", Course Name: " + c.courseName + ", Credit hours: "
                    + c.getCredits());
        }
    }

    // function to show student with their courses
    public void displayEnrollment(int stuID) {
        boolean f=false;
        for (Student s : st) {
            if (s.getStudentid() == stuID) {
                s.displayEnroll(stuID);
                f=true;
                break;
            }
        }
        if(!f){
            System.out.println("Enter the valid student id!!!");
        }
    }

    // function to show teacher with their courses
    public void displayTeacherEnrollment(int empID) {
        boolean flag=false;
        for (Faculty f : fac) {
            if (f.getemployeeID() == empID) {
                f.displayTeacherEnroll(empID);
                flag=true;
                break;
            }
        }
        if(!flag){
            System.out.println("Enter the valid employee id!!!");
        }
    }

}

/**
 * UniversityMagementSystem
 */
public class UniversityMagementSystem {

    public static void main(String[] args) {
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        University u = new University();
        do {
            System.out.println("*************UNIVERSITY MAGEMENT SYSTEM********************");
            System.out.println(
                    "1.Add student\n2.Remove Student\n3.Add faculty\n4.Remove Faculty\n5.Add Course\n6.Remove Course\n7.Display University Details\n8.Enroll Students into course\n9.Display Enrollment\n10.Enrolling teachers to teach Courses\n11.Displaying teacher enrolled\n12.Exit");
            System.out.print("Enter the choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("Enter the student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the student name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter the student age: ");
                    int age = sc.nextInt();
                    Student s = new Student(id, name, age, id);
                    u.addStudent(s);
                    break;
                case 2:
                    System.out.println("Enter the student ID: ");
                    int stuid = sc.nextInt();
                    u.removeStudent(stuid);
                    break;
                case 3:
                    System.out.println("Enter the employee ID: ");
                    int i = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the employee name: ");
                    String n = sc.nextLine();
                    System.out.println("Enter the employee age: ");
                    int a = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the department: ");
                    String d = sc.nextLine();
                    Faculty f = new Faculty(i, n, a, i, d);
                    u.addFaculty(f);
                    break;
                case 4:
                    System.out.println("Enter the employee ID: ");
                    int empid = sc.nextInt();
                    u.removeFaculty(empid);
                    break;
                case 5:
                    System.out.println("Enter the Course code: ");
                    int code = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the course name");
                    String cName = sc.nextLine();
                    System.out.println("Enter the course credit: ");
                    int credit = sc.nextInt();
                    Course c = new Course(code, cName, credit);
                    u.addCourse(c);
                    break;
                case 6:
                    System.out.println("Enter the course code: ");
                    int cID = sc.nextInt();
                    u.removeCourse(cID);
                    break;
                case 7:
                    u.displayUniDetails();
                    break;
                case 8:
                    System.out.println("Enter your student ID: ");
                    int stu = sc.nextInt();
                    System.out.println("Following are the courses available: ");
                    u.coursesAvailabale();
                    System.out.println("Enter the number of courses you want to enroll: ");
                    int num = sc.nextInt();
                    for (int j = 0; j < num; j++) {
                        System.out.println((j + 1) + ". Enter the course code: ");
                        int cde = sc.nextInt();
                        u.studentEnroll(cde, stu);
                    }
                    break;
                case 9:
                    System.out.println("Enter student id: ");
                    int st = sc.nextInt();
                    u.displayEnrollment(st);
                    break;

                case 10:
                    System.out.println("Enter your employee ID: ");
                    int emp = sc.nextInt();
                    System.out.println("Following are the courses available: ");
                    if(u.coursesAvailabale()){
                        System.out.println("Enter the number of courses you want to enroll: ");
                        int number = sc.nextInt();
                        for (int j = 0; j < number; j++) {
                            System.out.print((j + 1) + ". Enter the course code: ");
                            int cde = sc.nextInt();
                            u.teacherEnroll(cde, emp);
                        }
                    }
                     break;

                case 11:
                    System.out.println("Enter employee id: ");
                    int e = sc.nextInt();
                    u.displayTeacherEnrollment(e);
                    break;

                case 12:
                    flag = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    break;
            }
        } while (flag);
        sc.close();
    }
}
