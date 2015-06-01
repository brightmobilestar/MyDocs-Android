package jane.data;

import com.example.aaa.MainActivity;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PharmacyModel {
	public ArrayList<PharmacyRecord> list_appts = new ArrayList<PharmacyRecord>();
	public int maxIdx;
	
	public void init()
	{		
		SQLiteDatabase db = MainActivity.sqlHelper.getReadableDatabase();
		
		list_appts.clear();
		
	    Cursor cursor = db.query(SQLHelper.PHA_TABLE, null, null, null, null, null, null);
	    
	    maxIdx = -1;
	    
	    while (cursor.moveToNext()) {
	    	PharmacyRecord aptRecord = new PharmacyRecord();
	    	
	    	aptRecord.idex = cursor.getString(0);
	    	aptRecord.Pharmacy = cursor.getString(1);
	        aptRecord.address = cursor.getString(2);
	        aptRecord.city = cursor.getString(3);
	        aptRecord.state = cursor.getString(4);
	        aptRecord.zip = cursor.getString(5);
	        aptRecord.phone = cursor.getString(6);
	        
	        int curIdx = Integer.valueOf(aptRecord.idex);
	        
	        if(curIdx > maxIdx) maxIdx = curIdx;
	        
	        list_appts.add(aptRecord);
	    }
	    
	    cursor.close();
	    db.close();
	}
	
	public void updateDB(){
		
		SQLiteDatabase db = MainActivity.sqlHelper.getWritableDatabase();
		String strQuery = String.format("DELETE FROM %s", SQLHelper.PHA_TABLE);
		
		db.execSQL(strQuery);
		
		for(int i = 0; i < list_appts.size(); i ++)
		{
			PharmacyRecord aptRecord = list_appts.get(i);
			
			ContentValues values = new ContentValues();
			
		    values.put(SQLHelper.PHA_FIELD_INDEX, aptRecord.idex);
		    values.put(SQLHelper.PHA_FIELD_PHARMACY, aptRecord.Pharmacy);
		    values.put(SQLHelper.PHA_FIELD_ADDRESS, aptRecord.address);
		    values.put(SQLHelper.PHA_FIELD_CITY, aptRecord.city);
		    values.put(SQLHelper.PHA_FIELD_STATE, aptRecord.state);
		    values.put(SQLHelper.PHA_FIELD_ZIP, aptRecord.zip);
		    values.put(SQLHelper.PHA_FIELD_PHONE, aptRecord.phone);
		    
		    db.insert(SQLHelper.PHA_TABLE, null, values);	
		}
		
		db.close();
	}
	
	public void updateArray(PharmacyRecord newRecord)
	{
		for(int i = 0; i < list_appts.size(); i ++)
		{
			PharmacyRecord eleRecord = list_appts.get(i);
			
			if(eleRecord.idex.equals(newRecord.idex))
			{
				list_appts.remove(eleRecord);
				
				eleRecord.Pharmacy = newRecord.Pharmacy;
				eleRecord.address = newRecord.address;
				eleRecord.city = newRecord.city;
				eleRecord.state = newRecord.state;
				eleRecord.zip = newRecord.zip;
				eleRecord.phone = newRecord.phone;
				
				list_appts.add(i, eleRecord);
				
				updateDB();
				break;
			}
		}
	}
}
