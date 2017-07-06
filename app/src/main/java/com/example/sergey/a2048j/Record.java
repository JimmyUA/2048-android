package com.example.sergey.a2048j;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Sergey on 22.05.2017.
 */

public class Record implements Parcelable {

    private int record;
    final static String LOG_TAG = "myLogs";

    {
        record = 0;
    }
    public Record(){
        Log.d(LOG_TAG, "Score(int record)");
    }

    protected Record(Parcel in) {
        record = in.readInt();
    }

    public static final Creator<Record> CREATOR = new Creator<Record>() {
        @Override
        public Record createFromParcel(Parcel in) {
            Log.d(LOG_TAG, "createFromParcel");
            return new Record(in);
        }

        @Override
        public Record[] newArray(int size) {
            return new Record[size];
        }
    };

    /**
     * Method sets user record from DataBase
     * @param
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    /*public void setRecord(User2048 user) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException{

        record = new DBWorker().getRecord(user);
    }

    public int getRecord(){
        return record;
    }*/

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "" + record;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + record;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Record other = (Record) obj;
        if (record != other.record)
            return false;
        return true;
    }

    public int getRecord(){
        return record;
    }

    public void setRecord(int record){
        this.record = record;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.d(LOG_TAG, "writeToParcel");
        parcel.writeInt(record);
    }
}
