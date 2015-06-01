package jane.data;

import com.example.aaa.MainActivity;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class InsuranceModel {
	public ArrayList<InsuranceRecord> list_appts = new ArrayList<InsuranceRecord>();
	public int maxIdx;
	
	public void init()
	{
		SQLiteDatabase db = MainActivity.sqlHelper.getReadableDatabase();
		
		list_appts.clear();
		
	    Cursor cursor = db.query(SQLHelper.INSR_TABLE, null, null, null, null, null, null);
	    
	    maxIdx = -1;
	    
	    while (cursor.moveToNext()) {
	    	InsuranceRecord aptRecord = new InsuranceRecord();
	    	
	    	aptRecord.idex = cursor.getString(0);
	    	aptRecord.Member = cursor.getString(1);
	    	aptRecord.Company = cursor.getString(2);
	        aptRecord.address = cursor.getString(3);
	        aptRecord.city = cursor.getString(4);
	        aptRecord.state = cursor.getString(5);
	        aptRecord.zip = cursor.getString(6);
	        aptRecord.phone = cursor.getString(7);
	        aptRecord.PolicyNumber = cursor.getString(8);
	        aptRecord.MemberID = cursor.getString(9);
	        aptRecord.Group = cursor.getString(10);
	        aptRecord.BIN = cursor.getString(11);
	        
	        int curIdx = Integer.valueOf(aptRecord.idex);
	        
	        if(curIdx > maxIdx) maxIdx = curIdx;
	        
	        list_appts.add(aptRecord);
	    }
	    
	    cursor.close();
	    db.close();
	}
	
	public void updateDB(){
		
		SQLiteDatabase db = MainActivity.sqlHelper.getWritableDatabase();
		String strQuery = String.format("DELETE FROM %s", SQLHelper.INSR_TABLE);
		
		db.execSQL(strQuery);
		
		for(int i = 0; i < list_appts.size(); i ++)
		{
			InsuranceRecord aptRecord = list_appts.get(i);
			
			ContentValues values = new ContentValues();
			
		    values.put(SQLHelper.INSR_FIELD_INDEX, aptRecord.idex);
		    values.put(SQLHelper.INSR_FIELD_MEMBER, aptRecord.Member);
		    values.put(SQLHelper.INSR_FIELD_COMPANY, aptRecord.Company);
		    values.put(SQLHelper.INSR_FIELD_ADDRESS, aptRecord.address);
		    values.put(SQLHelper.INSR_FIELD_CITY, aptRecord.city);
		    values.put(SQLHelper.INSR_FIELD_STATE, aptRecord.state);
		    values.put(SQLHelper.INSR_FIELD_ZIP, aptRecord.zip);
		    values.put(SQLHelper.INSR_FIELD_PHONE, aptRecord.phone);
		    values.put(SQLHelper.INSR_FIELD_POLICY_NUMBER, aptRecord.PolicyNumber);
		    values.put(SQLHelper.INSR_FIELD_MEMBER_ID, aptRecord.MemberID);
		    values.put(SQLHelper.INSR_FIELD_GROUP, aptRecord.Group);
		    values.put(SQLHelper.INSR_FIELD_BIN, aptRecord.BIN);
		    
		    db.insert(SQLHelper.INSR_TABLE, null, values);	
		}
		
		db.close();
	}
	
	public void updateArray(InsuranceRecord newRecord)
	{
		for(int i = 0; i < list_appts.size(); i ++)
		{
			InsuranceRecord eleRecord = list_appts.get(i);
			
			if(eleRecord.idex.equals(newRecord.idex))
			{
				list_appts.remove(eleRecord);
				
				eleRecord.Member = newRecord.Member;
				eleRecord.Company = newRecord.Company;
				eleRecord.address = newRecord.address;
				eleRecord.city = newRecord.city;
				eleRecord.state = newRecord.state;
				eleRecord.zip = newRecord.zip;
				eleRecord.phone = newRecord.phone;
				eleRecord.PolicyNumber = newRecord.PolicyNumber;
				eleRecord.MemberID = newRecord.MemberID;
				eleRecord.Group = newRecord.Group;
				eleRecord.BIN = newRecord.BIN;
				
				list_appts.add(i, eleRecord);
				
				updateDB();
				break;
			}
		}
	}

}
