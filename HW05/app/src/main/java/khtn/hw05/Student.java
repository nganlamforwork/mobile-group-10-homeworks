package khtn.hw05;

public class Student {
    private String id;
    private String fullNames;
    private String className;
    private double gpa;
    private int avatar;
    private int position;


    // Constructor
    public Student(String id, String fullNames, String className, double gpa, int avatar, int position) {
        this.id = id;
        this.fullNames = fullNames;
        this.className = className;
        this.gpa = gpa;
        this.avatar = avatar;
        this.position = position;
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullNames;
    }

    public String getClassName() {
        return className;
    }

    public double getGpa() {
        return gpa;
    }

    public int getAvatar() {
        return avatar;
    }

    public int getPosition(){return position;}

    // Setter methods (if needed)
    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullNames) {
        this.fullNames = fullNames;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setPosition(int position){this.position = position;}
}
