package khtn.hw05;

public class Student {
    private String MaHS;
    private String TenHS;
    private String MaLop;
    private int avatar;
    private int position;


    // Constructor
    public Student(String MaHS, String TenHS, String MaLop, int avatar, int position) {
        this.MaHS = MaHS;
        this.TenHS = TenHS;
        this.MaLop = MaLop;
        this.avatar = avatar;
        this.position = position;
    }


    // Getter methods
    public String getMaHS() {
        return MaHS;
    }

    public String getTenHS() {
        return TenHS;
    }

    public String getMaLop() {
        return MaLop;
    }

    public int getAvatar() {
        return avatar;
    }

    public int getPosition(){return position;}

    // Setter methods (if needed)
    public void setMaHS(String MaHS) {
        this.MaHS = MaHS;
    }

    public void setTenHS(String TenHS) {
        this.TenHS = TenHS;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setPosition(int position){this.position = position;}


}
