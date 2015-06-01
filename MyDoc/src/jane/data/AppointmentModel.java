package jane.data;

import com.example.aaa.MainActivity;
import java.util.ArrayList;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import jane.data.AppointmentRecord;

public class AppointmentModel{
	public ArrayList<AppointmentRecord> list_appts = new ArrayList<AppointmentRecord>();
	public int maxIdx;
	public void init(SQLHelper sqlHelper)
	{		
		SQLiteDatabase db = sqlHelper.getReadableDatabase();
		
		list_appts.clear();
		
		
		
	    Cursor cursor = db.query(SQLHelper.APT_TABLE, null, SQLHelper.APT_FIELD_REMINDER_CHK+"=?", new String[]{"1"}, null, null, null);
	    
	    maxIdx = -1;
	    
	    while (cursor.moveToNext()) {
	    	AppointmentRecord aptRecord = new AppointmentRecord();
	    	
	    	aptRecord.idex = cursor.getString(0);
	    	aptRecord.doctor = cursor.getString(1);
	    	aptRecord.date = cursor.getString(2);
	        aptRecord.time = cursor.getString(3);
	        aptRecord.reminder_check = cursor.getString(4);
	        aptRecord.reminder_time = cursor.getString(5);
	        aptRecord.address = cursor.getString(6);
	        aptRecord.city = cursor.getString(7);
	        aptRecord.state = cursor.getString(8);
	        aptRecord.zip = cursor.getString(9);
	        aptRecord.phone = cursor.getString(10);
	        aptRecord.mobile = cursor.getString(11);
	        aptRecord.email = cursor.getString(12);
	        
	        int curIdx = Integer.valueOf(aptRecord.idex);
	        
	        if(curIdx > maxIdx) maxIdx = curIdx;
	        
	        list_appts.add(aptRecord);
	    }
	    
	    cursor.close();
	    db.close();
	}
	
	public void init()
	{		
		SQLiteDatabase db = MainActivity.sqlHelper.getReadableDatabase();
		
		list_appts.clear();
		
		
		
	    Cursor cursor = db.query(SQLHelper.APT_TABLE, null, null, null, null, null, null);
	    
	    maxIdx = -1;
	    
	    while (cursor.moveToNext()) {
	    	AppointmentRecord aptRecord = new AppointmentRecord();
	    	
	    	aptRecord.idex = cursor.getString(0);
	    	aptRecord.doctor = cursor.getString(1);
	    	aptRecord.date = cursor.getString(2);
	        aptRecord.time = cursor.getString(3);
	        aptRecord.reminder_check = cursor.getString(4);
	        aptRecord.reminder_time = cursor.getString(5);
	        aptRecord.address = cursor.getString(6);
	        aptRecord.city = cursor.getString(7);
	        aptRecord.state = cursor.getString(8);
	        aptRecord.zip = cursor.getString(9);
	        aptRecord.phone = cursor.getString(10);
	        aptRecord.mobile = cursor.getString(11);
	        aptRecord.email = cursor.getString(12);
	        
	        int curIdx = Integer.valueOf(aptRecord.idex);
	        
	        if(curIdx > maxIdx) maxIdx = curIdx;
	        
	        list_appts.add(aptRecord);
	    }
	    
	    cursor.close();
	    db.close();
	}
	
	public void updateDB(){
		
		SQLiteDatabase db = MainActivity.sqlHelper.getWritableDatabase();
		String strQuery = String.format("DELETE FROM %s", SQLHelper.APT_TABLE);
		
		db.execSQL(strQuery);
		
		for(int i = 0; i < list_appts.size(); i ++)
		{
			AppointmentRecord aptRecord = list_appts.get(i);
			
			ContentValues values = new ContentValues();
			
		    values.put(SQLHelper.APT_FIELD_INDEX, aptRecord.idex);
		    values.put(SQLHelper.APT_FIELD_DOCTOR, aptRecord.doctor);
		    values.put(SQLHelper.APT_FIELD_DATE, aptRecord.date);
		    values.put(SQLHelper.APT_FIELD_TIME, aptRecord.time);
		    values.put(SQLHelper.APT_FIELD_REMINDER_CHK, aptRecord.reminder_check);
		    values.put(SQLHelper.APT_FIELD_REMINDER_TIME, aptRecord.reminder_time);
		    values.put(SQLHelper.APT_FIELD_ADDRESS, aptRecord.address);
		    values.put(SQLHelper.APT_FIELD_CITY, aptRecord.city);
		    values.put(SQLHelper.APT_FIELD_STATE, aptRecord.state);
		    values.put(SQLHelper.APT_FIELD_ZIP, aptRecord.zip);
		    values.put(SQLHelper.APT_FIELD_PHONE, aptRecord.phone);
		    values.put(SQLHelper.APT_FIELD_MOBILE, aptRecord.mobile);
		    values.put(SQLHelper.APT_FIELD_EMAIL, aptRecord.email);
		    
		    db.insert(SQLHelper.APT_TABLE, null, values);	
		}
		
		db.close();
	}
	
	public void updateArray(AppointmentRecord newRecord)
	{
		for(int i = 0; i < list_appts.size(); i ++)
		{
			AppointmentRecord eleRecord = list_appts.get(i);
			
			if(eleRecord.idex.equals(newRecord.idex))
			{
				list_appts.remove(eleRecord);
				
				eleRecord.doctor = newRecord.doctor;
				eleRecord.date = newRecord.date;
				eleRecord.time = newRecord.time;
				eleRecord.reminder_check = newRecord.reminder_check;
				eleRecord.reminder_time = newRecord.reminder_time;
				eleRecord.address = newRecord.address;
				eleRecord.city = newRecord.city;
				eleRecord.state = newRecord.state;
				eleRecord.zip = newRecord.zip;
				eleRecord.phone = newRecord.phone;
				eleRecord.mobile = newRecord.mobile;
				eleRecord.email = newRecord.email;
				
				list_appts.add(i, eleRecord);
				
				updateDB();
				break;
			}
		}
	}

}


