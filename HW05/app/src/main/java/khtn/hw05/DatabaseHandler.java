package khtn.hw05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "HCM-US";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "STUDENTS";
    private static final String TABLE_CLASSNAME = "CLASS";

    private static final String KEY_MAHS = "MAHS";
    private static final String KEY_MALOP = "MALOP";
    private static final String KEY_TENHS = "TENHS";
    private static final String KEY_TENLOP = "TENLOP";
    private static final String KEY_AVATAR = "AVATAR";
    private static final String KEY_POSITION = "POSITION";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);
        String create_students_table = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER)", TABLE_NAME, KEY_MAHS, KEY_TENHS, KEY_MALOP, KEY_AVATAR, KEY_POSITION);
        String create_class_table = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY, %s TEXT)", TABLE_CLASSNAME, KEY_MALOP, KEY_TENLOP);
        db.execSQL(create_students_table);
        db.execSQL(create_class_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        String drop_class_table = String.format("DROP TABLE IF EXISTS %s", TABLE_CLASSNAME);
        db.execSQL(drop_students_table);
        db.execSQL(drop_class_table);
        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MAHS, student.getMaHS());
        values.put(KEY_TENHS, student.getTenHS());
        values.put(KEY_MALOP, student.getMaLop());
        values.put(KEY_AVATAR, student.getAvatar());
        values.put(KEY_POSITION, student.getPosition());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void addClass(Class classs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MALOP, classs.getMaLop());
        values.put(KEY_TENLOP, classs.getTenLop());
        db.insert(TABLE_CLASSNAME, null, values);
        db.close();
    }

    public Student getStudent(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_MAHS + " = ?", new String[] { String.valueOf(studentId) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        return new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4));
    }

    public Class getClass(int classId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLASSNAME, null, KEY_MALOP + " = ?", new String[] { String.valueOf(classId) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        return new Class(cursor.getString(0), cursor.getString(1));
    }


    public List<Student> getAllStudents() {
        List<Student>  studentList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Student student = new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4));
            studentList.add(student);
            cursor.moveToNext();
        }
        return studentList;
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MAHS, student.getMaHS());
        values.put(KEY_TENHS, student.getTenHS());
        values.put(KEY_MALOP, student.getMaLop());
        values.put(KEY_AVATAR, student.getAvatar());
        values.put(KEY_POSITION, student.getPosition());

        db.update(TABLE_NAME, values, KEY_MAHS + " = ?", new String[] { String.valueOf(student.getMaHS()) });
        db.close();
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_MAHS + " = ?", new String[] { String.valueOf(studentId) });
        db.close();
    }

    public void deleteClass(int classId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLASSNAME, KEY_MALOP + " = ?", new String[] { String.valueOf(classId) });
        db.close();
    }
}