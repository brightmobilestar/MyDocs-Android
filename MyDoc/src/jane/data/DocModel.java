package jane.data;

import com.example.aaa.MainActivity;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DocModel {
	public ArrayList<DocRecord> list_appts = new ArrayList<DocRecord>();
	public int maxIdx;
	
	public void init()
	{		
		SQLiteDatabase db = MainActivity.sqlHelper.getReadableDatabase();
		
		list_appts.clear();
		
	    Cursor cursor = db.query(SQLHelper.DOC_TABLE, null, null, null, null, null, null);
	    
	    maxIdx = -1;
	    
	    while (cursor.moveToNext()) {
	    	DocRecord aptRecord = new DocRecord();
	    	
	    	aptRecord.idx = cursor.getString(0);
	    	aptRecord.doctor = cursor.getString(1);
	        aptRecord.address = cursor.getString(2);
	        aptRecord.city = cursor.getString(3);
	        aptRecord.state = cursor.getString(4);
	        aptRecord.zip = cursor.getString(5);
	        aptRecord.phone = cursor.getString(6);
	        aptRecord.mobile = cursor.getString(7);
	        aptRecord.email = cursor.getString(8);
	        
	        int curIdx = Integer.valueOf(aptRecord.idx);
	        
	        if(curIdx > maxIdx) maxIdx = curIdx;
	        
	        list_appts.add(aptRecord);
	    }
	    
	    cursor.close();
	    db.close();
	}
	
	public void updateDB(){
		
		SQLiteDatabase db = MainActivity.sqlHelper.getWritableDatabase();
		String strQuery = String.format("DELETE FROM %s", SQLHelper.DOC_TABLE);
		
		db.execSQL(strQuery);
		
		for(int i = 0; i < list_appts.size(); i ++)
		{
			DocRecord aptRecord = list_appts.get(i);
			
			ContentValues values = new ContentValues();
			
		    values.put(SQLHelper.DOC_FIELD_INDEX, aptRecord.idx);
		    values.put(SQLHelper.DOC_FIELD_DOCTOR, aptRecord.doctor);
		    values.put(SQLHelper.DOC_FIELD_ADDRESS, aptRecord.address);
		    values.put(SQLHelper.DOC_FIELD_CITY, aptRecord.city);
		    values.put(SQLHelper.DOC_FIELD_STATE, aptRecord.state);
		    values.put(SQLHelper.DOC_FIELD_ZIP, aptRecord.zip);
		    values.put(SQLHelper.DOC_FIELD_PHONE, aptRecord.phone);
		    values.put(SQLHelper.DOC_FIELD_MOBILE, aptRecord.mobile);
		    values.put(SQLHelper.DOC_FIELD_EMAIL, aptRecord.email);
		    
		    db.insert(SQLHelper.DOC_TABLE, null, values);	
		}
		
		db.close();
	}
	
	public void updateArray(DocRecord newRecord)
	{
		for(int i = 0; i < list_appts.size(); i ++)
		{
			DocRecord eleRecord = list_appts.get(i);
			
			if(eleRecord.idx.equals(newRecord.idx))
			{
				list_appts.remove(eleRecord);
				
				eleRecord.doctor = newRecord.doctor;
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
