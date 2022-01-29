package com.example.android.votingiva;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class password_db extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "password";
    public static final String TABLE_NAME = "user";//1st table
    public static final String T_EMAIL = "EMAIL";
    public static final String T_PASS = "PASSWORD";
    public static final String T_NAME = "USERNAME";
    public static final String TABLE_NAME2="createPoll";//2nd table
    public static final String T2_PNAME = "PollName";
    public static final String T2_QUES="Question";
    public static final String T2_OPA="OptA";
    public static final String T2_OPB="OptB";
    public static final String T2_OPC="OptC";
    public static final String T2_OPD="OptD";
    public static final String T2_FUSERID="USERID";
    public static final String T2_FCIDF="CIDF";
    public static final String TABLE_NAME3="Category";//3rd table
    public static final String T3_CNAME="Cname";
    public static final String TABLE_NAME4="resultPoll";
    public static final String T4_PollIDF="POLLIDF";
    public static final String T4_DATE="date";
    public static final String T4_countA="countA";
    public static final String T4_countB="countB";
    public static final String T4_countC="countC";
    public static final String T4_countD="countD";

//    public static final String TABLE_NAME5="TextCP";//child of 2nd table
//    public static final String T5_PNAME = "PollName";
//    public static final String T5_QUES="Question";
//    public static final String T5_OPA="OptA";
//    public static final String T5_OPB="OptB";
//    public static final String T5_OPC="OptC";
//    public static final String T5_OPD="OptD";
//    public static final String T5_pollID_fk="CPID_fk";

    public static final String TABLE_NAME6 = "VotedOn"; // relationship table
    public static final String T6_OV = "OptionVoted";
    public static final String T6_USERID_fk="UserID_fk";
    public static final String T6_pollID_fk="CPID_fk";

    //SQLiteDatabase db;
    Context context;
    // Constructor
    public password_db(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1); // SuperClass (SQLite constructor)
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,EMAIL TEXT UNIQUE,PASSWORD TEXT,USERNAME TEXT) ");
        db.execSQL("create table " + TABLE_NAME3 + "(CID INTEGER PRIMARY KEY AUTOINCREMENT,Cname TEXT UNIQUE) ");
        db.execSQL("create table " + TABLE_NAME2 + " (POLLID INTEGER PRIMARY KEY AUTOINCREMENT,PollName TEXT,Question TEXT,OptA TEXT,OptB TEXT,OptC TEXT,OptD TEXT,USERID INTEGER,CIDF INTEGER,FOREIGN KEY (USERID) REFERENCES user (ID),FOREIGN KEY (CIDF) REFERENCES Category (CID))");
        db.execSQL("INSERT INTO  Category (Cname) VALUES ('Anime'),('Logos'),('Cars'),('Designs'),('Entertainment'),('Drawings'),('Games'),('Holidays')," +
                "('Music'),('Nature'),('Technology'),('Sports'),('Comics') ");
        db.execSQL("create table " + TABLE_NAME4 + " (POLLIDF INTEGER,date TEXT,countA INTEGER,countB INTEGER,countC INTEGER,countD INTEGER,FOREIGN KEY (POLLIDF) REFERENCES createPoll (POLLID),PRIMARY KEY (date,POLLIDF)) ");
       // db.execSQL("create table " + TABLE_NAME5 + " (PollName TEXT,Question TEXT,OptA TEXT,OptB TEXT,OptC TEXT,OptD TEXT, PollID_fk INTEGER,FOREIGN KEY (PollID_fk) REFERENCES createPoll (POLLID), PRIMARY KEY (PollID_fk))");
        db.execSQL("create table " + TABLE_NAME6 + " (OptionVoted Text, UserID_fk INTEGER, CPID_fk INTEGER, FOREIGN KEY (CPID_fk) REFERENCES resultPoll, FOREIGN KEY (UserID_fk) REFERENCES user, PRIMARY KEY (UserID_fk,CPID_fk))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME5);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME6);
        onCreate(db);
    }

    //registration handler which allows data insertion into the table
    public Boolean insertData(String email, String password, String name) { // SIGNUP
        SQLiteDatabase db = this.getWritableDatabase();//this tells us that we will be performing write operations on the database
        ContentValues contentValues = new ContentValues();//object of this class allows data manipulation
        contentValues.put(T_EMAIL, email);
        contentValues.put(T_PASS, password);
        contentValues.put(T_NAME, name);
        long result = db.insert(TABLE_NAME, null, contentValues);//the insertion returns boolean values
        // db.close();
        return (result != -1);
    }

    //login handler
    public Cursor login_user(String email, String password) { // LOGIN
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where EMAIL= ? AND PASSWORD= ? ", new String[]{email, password});
        return res;//we passed as a string because the use of '@' is a special character and displayed error in querying
    }

    public String fetch_about(String email) {//For about fragment
        SQLiteDatabase db = this.getReadableDatabase();
        String e="";
        //ArrayList<UsersData> usersData = new ArrayList<>();

        Cursor res = db.rawQuery("select USERNAME from " + TABLE_NAME + " where EMAIL like ? ",new String[]{email});
        res.moveToFirst();
        e = res.getString(0);
        //usersData.add(new UsersData(res.getString(0)));
        return e;
        // return usersData;
    }

    public int fetch_UserID(String email){//function to fetch userID
        SQLiteDatabase db = this.getReadableDatabase();
        int e=0;
        Cursor res = db.rawQuery("select ID from " + TABLE_NAME + " where EMAIL= ? ", new String[]{email});
        while(res.moveToNext()) {
            e= res.getInt(0);
        }
        return e;
    }
    public void insertDataTable6(int userID,int PollID,String opt){ // Insert into Voted On
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(T6_OV,opt);
        contentValues.put(T6_pollID_fk,PollID);
        contentValues.put(T6_USERID_fk,userID);
        db.insert(TABLE_NAME6,null,contentValues);
    }
    public int fetch_CatID(String cat){
        SQLiteDatabase db = this.getReadableDatabase();
        int e=0;
        Cursor res = db.rawQuery("select * from " + TABLE_NAME3 + " where Cname= ? ", new String[]{cat});
        while(res.moveToNext()) {
            e= res.getInt(0);
        }
        return e;
    }
    public int fetch_PollID(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select max(POLLID) from " +TABLE_NAME2 + " where USERID= (select USERID from user where email=?)",new String[]{email});
        int pollID=0;
        while(res.moveToNext()){
            pollID=res.getInt(0);
        }
        return pollID;
    }
    //when insertion is only done in createPoll but not in resultPoll
    public Boolean insertDataTable2(String pName,String pQues,String optA,String optB,String optC,String optD,String email,String cat){ // Create Poll
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(T2_PNAME,pName);
        contentValues.put(T2_QUES,pQues);
        contentValues.put(T2_OPA,optA);
        contentValues.put(T2_OPB,optB);
        contentValues.put(T2_OPC,optC);
        contentValues.put(T2_OPD,optD);
        contentValues.put(T2_FUSERID,fetch_UserID(email));//foreign key
        contentValues.put(T2_FCIDF,fetch_CatID(cat));//foreign key of category
        long result=db.insert(TABLE_NAME2,null,contentValues);//insertion in createPoll table is complete
        return result!=-1;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Boolean insertDataTable2_4(String pName, String pQues, String optA, String optB, String optC, String optD, String email, String cat){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(T2_PNAME,pName);
        contentValues.put(T2_QUES,pQues);
        contentValues.put(T2_OPA,optA);
        contentValues.put(T2_OPB,optB);
        contentValues.put(T2_OPC,optC);
        contentValues.put(T2_OPD,optD);
        contentValues.put(T2_FUSERID,fetch_UserID(email));//foreign key
        contentValues.put(T2_FCIDF,fetch_CatID(cat));//foreign key of category
        long result=db.insert(TABLE_NAME2,null,contentValues);//insertion in createPoll table is complete

        int PollID=fetch_PollID(email);//pollID for resultPoll table
        ContentValues contentValues2=new ContentValues();
        String dateVal=java.time.LocalDate.now().toString();//extracting date
        contentValues2.put(T4_PollIDF,PollID);
        contentValues2.put(T4_DATE,dateVal);
        contentValues2.put(T4_countA,0);
        contentValues2.put(T4_countB,0);
        contentValues2.put(T4_countC,0);
        contentValues2.put(T4_countD,0);
        long result2=db.insert(TABLE_NAME4,null,contentValues2);
        Log.d("SOS"," "+result+" ");

        return result != -1 && result2!=-1;
    }

    public ArrayList<Noti> getNotidata(int Uid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Noti> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select user.USERNAME, createPoll.PollName from VotedOn INNER JOIN user ON VotedOn.UserID_fk = user.ID INNER JOIN  createPoll ON VotedOn.CPID_fk = createPoll.POLLID where createPoll.USERID = ?", new String[]{String.valueOf(Uid)});
        while(cursor.moveToNext())
        {
            String Uname = cursor.getString(0);
            String Pname = cursor.getString(1);
            Noti noti = new Noti(Pname, Uname);
            arrayList.add(noti);
        }
        return arrayList;
    }

    public ArrayList<Poll> getAllData() // And store it in Class Poll
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Poll> arrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from createPoll where POLLID IN (select POLLIDF from resultPoll) order by pollID DESC"  , null);//change here is
        Cursor cursor2 = db.rawQuery("select * from resultPoll order by POLLIDF DESC", null);
        while(cursor.moveToNext() && cursor2.moveToNext())
        {
            int id = cursor.getInt(0);
            String Pollname = cursor.getString(1);
            String question = cursor.getString(2);
            String A = cursor.getString(3);
            String B = cursor.getString(4);
            String C = cursor.getString(5);
            String D = cursor.getString(6);
            int C1 = cursor2.getInt(2);
            int C2 = cursor2.getInt(3);
            int C3 = cursor2.getInt(4);
            int C4 = cursor2.getInt(5);
            Poll poll = new Poll(id, Pollname, question, A, B, C, D, C1, C2, C3, C4);
            arrayList.add(poll);
        }
        return arrayList;
    }
public Boolean checkVote(int PId,int UId){  // Check if Voted
        SQLiteDatabase db=getReadableDatabase();
        String option="";
        Cursor cursor=db.rawQuery("select OptionVoted from VotedOn where UserID_fk="+UId+" and  CPID_fk="+PId,null);
        while(cursor.moveToNext()){
            option=cursor.getString(0);
        }
        if (option.equals("")){
            return false;
        }
        else{
            return true;
        }
}

    public void updateItem(int PID, String Pdate, int C1, int C2, int C3, int C4) { // update result table
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(T4_countA, C1);
        contentValues.put(T4_countB, C2);
        contentValues.put(T4_countC, C3);
        contentValues.put(T4_countD, C4);
        db.update(TABLE_NAME4,contentValues,"POLLIDF="+PID,null);

        db.close();
    }

    public String  getDate(int id) // From Result poll
    {
        String date = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select date from resultPoll where POLLIDF=?",new String[]{String.valueOf(id)});
        while(cursor.moveToNext())
        {
            date= cursor.getString(0);
        }
        return date;
    }

    public String  getName(String email)
    {
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select USERNAME from user where EMAIL=?",new String[]{email});
        while(cursor.moveToNext())
        {
            name= cursor.getString(0);
        }
        return name;
    }

    public ArrayList<Poll> getSavedData(int UID){//function for about fragment
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Poll> arrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from createPoll where POLLID NOT IN (select POLLIDF from resultPoll) AND USERID = ? order by pollID DESC"  , new String[]{String.valueOf(UID)});//change here is

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String Pollname = cursor.getString(1);
            String question = cursor.getString(2);
            String A = cursor.getString(3);
            String B = cursor.getString(4);
            String C = cursor.getString(5);
            String D = cursor.getString(6);
            int C1=0, C2=0, C3=0, C4=0;
            Poll poll = new Poll(id, Pollname, question, A, B, C, D, C1, C2, C3, C4);
            arrayList.add(poll);
        }
        return arrayList;
    }

    public ArrayList<String> CategoryList(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from Category",null);
        while(cursor.moveToNext()){
            String cat=cursor.getString(1);
            arrayList.add(cat);
        }
        return arrayList;
    }

//    public ArrayList<String> queryXdata() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<String> xData = new ArrayList<String>();
//        String query = "select ";
//        return xData;
//    }
}
