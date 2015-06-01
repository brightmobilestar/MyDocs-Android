package jane.data;

import com.example.aaa.MainActivity;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SpecialistModel {
	public ArrayList<SpecialistRecord> list_appts = new ArrayList<SpecialistRecord>();
	public int maxIdx;
	
	public void init()
	{		
		SQLiteDatabase db = MainActivity.sqlHelper.getReadableDatabase();
		
		list_appts.clear();
		
	    Cursor cursor = db.query(SQLHelper.SPEC_TABLE, null, null, null, null, null, null);
	    
	    maxIdx = -1;
	    
	    while (cursor.moveToNext()) {
	    	SpecialistRecord aptRecord = new SpecialistRecord();
	    	
	    	aptRecord.idex = cursor.getString(0);
	    	aptRecord.total_index = cursor.getString(1);
	    	aptRecord.Doctor = cursor.getString(2);
	    	aptRecord.Specialist = cursor.getString(3);
	        aptRecord.address = cursor.getString(4);
	        aptRecord.city = cursor.getString(5);
	        aptRecord.state = cursor.getString(6);
	        aptRecord.zip = cursor.getString(7);
	        aptRecord.phone = cursor.getString(8);
	        aptRecord.mobile = cursor.getString(9);
	        aptRecord.Email = cursor.getString(10);
	        
	        int curIdx = Integer.valueOf(aptRecord.idex);
	        
	        if(curIdx > maxIdx) maxIdx = curIdx;
	        
	        list_appts.add(aptRecord);
	    }
	    
	    cursor.close();
	    db.close();
	}
	
	public void updateDB(){
		
		SQLiteDatabase db = MainActivity.sqlHelper.getWritableDatabase();
		String strQuery = String.format("DELETE FROM %s", SQLHelper.SPEC_TABLE);
		
		db.execSQL(strQuery);
		
		for(int i = 0; i < list_appts.size(); i ++)
		{
			SpecialistRecord aptRecord = list_appts.get(i);
			
			ContentValues values = new ContentValues();
			
		    values.put(SQLHelper.SPEC_FIELD_INDEX, aptRecord.idex);
		    values.put(SQLHelper.SPEC_FIELD_TOTAL_INDEX, aptRecord.total_index);
		    values.put(SQLHelper.SPEC_FIELD_DOCTOR, aptRecord.Doctor);
		    values.put(SQLHelper.SPEC_FIELD_SPECIALIST, aptRecord.Specialist);
		    values.put(SQLHelper.SPEC_FIELD_ADDRESS, aptRecord.address);
		    values.put(SQLHelper.SPEC_FIELD_CITY, aptRecord.city);
		    values.put(SQLHelper.SPEC_FIELD_STATE, aptRecord.state);
		    values.put(SQLHelper.SPEC_FIELD_ZIP, aptRecord.zip);
		    values.put(SQLHelper.SPEC_FIELD_PHONE, aptRecord.phone);
		    values.put(SQLHelper.SPEC_FIELD_MOBILE, aptRecord.mobile);
		    values.put(SQLHelper.SPEC_FIELD_EMAIL, aptRecord.Email);
		    
		    db.insert(SQLHelper.SPEC_TABLE, null, values);	
		}
		
		db.close();
	}
	
	public void updateArray(SpecialistRecord newRecord)
	{
		for(int i = 0; i < list_appts.size(); i ++)
		{
			SpecialistRecord eleRecord = list_appts.get(i);
			
			if(eleRecord.idex.equals(newRecord.idex))
			{
				list_appts.remove(eleRecord);
				
				eleRecord.total_index = newRecord.total_index;
				eleRecord.Doctor = newRecord.Doctor;
				eleRecord.Specialist = newRecord.Specialist;
				eleRecord.address = newRecord.address;
				eleRecord.city = newRecord.city;
				eleRecord.state = newRecord.state;
				eleRecord.zip = newRecord.zip;
				eleRecord.phone = newRecord.phone;
				eleRecord.mobile = newRecord.mobile;
				eleRecord.Email = newRecord.Email;
				
				list_appts.add(i, eleRecord);
				
				updateDB();
				break;
			}
		}
	}

}
