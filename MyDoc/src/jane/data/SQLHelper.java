package jane.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "mydocs.db";
	private static final int 	DATABASE_VERSION = 1;
	
	// Appointment table
	public static final String APT_TABLE 					= "tblAppointment";
	public static final String APT_FIELD_INDEX 				= "id";
	public static final String APT_FIELD_DOCTOR				= "doctor";
	public static final String APT_FIELD_DATE				= "date";
	public static final String APT_FIELD_TIME				= "time";
	public static final String APT_FIELD_REMINDER_CHK 		= "reminder_chk";
	public static final String APT_FIELD_REMINDER_TIME 		= "reminder_time";
	public static final String APT_FIELD_ADDRESS			= "address";
	public static final String APT_FIELD_CITY				= "city";
	public static final String APT_FIELD_STATE				= "state";
	public static final String APT_FIELD_ZIP 				= "zipcode";
	public static final String APT_FIELD_PHONE				= "phone";
	public static final String APT_FIELD_MOBILE				= "mobile";
	public static final String APT_FIELD_EMAIL				= "email";
	
	
	// Insurance table
	public static final String INSR_TABLE 					= "tblInsurance";
	public static final String INSR_FIELD_INDEX 			= "id";
	public static final String INSR_FIELD_MEMBER			= "Member";
	public static final String INSR_FIELD_COMPANY			= "Company";
	public static final String INSR_FIELD_ADDRESS			= "address";
	public static final String INSR_FIELD_CITY				= "city";
	public static final String INSR_FIELD_STATE				= "state";
	public static final String INSR_FIELD_ZIP 				= "zipcode";
	public static final String INSR_FIELD_PHONE				= "phone";
	public static final String INSR_FIELD_POLICY_NUMBER		= "PolicyNubmer";
	public static final String INSR_FIELD_MEMBER_ID			= "MemberID";
	public static final String INSR_FIELD_GROUP				= "Group_INS";
	public static final String INSR_FIELD_BIN 				= "Bin_INS";
	
	// Specialist table
	public static final String SPEC_TABLE 					= "tblSpecialist";
	public static final String SPEC_FIELD_INDEX 			= "id";
	public static final String SPEC_FIELD_TOTAL_INDEX 		= "total_id";
	public static final String SPEC_FIELD_DOCTOR			= "Doctor";
	public static final String SPEC_FIELD_SPECIALIST		= "Specialist";
	public static final String SPEC_FIELD_ADDRESS			= "address";
	public static final String SPEC_FIELD_CITY				= "city";
	public static final String SPEC_FIELD_STATE				= "state";
	public static final String SPEC_FIELD_ZIP 				= "zipcode";
	public static final String SPEC_FIELD_PHONE				= "phone";
	public static final String SPEC_FIELD_MOBILE			= "mobile";
	public static final String SPEC_FIELD_EMAIL				= "Email";
	
	// Pharmacy table
	public static final String PHA_TABLE 					= "tblPharmacy";
	public static final String PHA_FIELD_INDEX 				= "id";
	public static final String PHA_FIELD_PHARMACY			= "Pharmacy";
	public static final String PHA_FIELD_ADDRESS			= "address";
	public static final String PHA_FIELD_CITY				= "city";
	public static final String PHA_FIELD_STATE				= "state";
	public static final String PHA_FIELD_ZIP 				= "zipcode";
	public static final String PHA_FIELD_PHONE				= "phone";

	// Doc table
	public static final String DOC_TABLE 					= "tblDoc";
	public static final String DOC_FIELD_INDEX 				= "id";
	public static final String DOC_FIELD_DOCTOR				= "Doctor";
	public static final String DOC_FIELD_ADDRESS			= "address";
	public static final String DOC_FIELD_CITY				= "city";
	public static final String DOC_FIELD_STATE				= "state";
	public static final String DOC_FIELD_ZIP 				= "zipcode";
	public static final String DOC_FIELD_PHONE				= "phone";
	public static final String DOC_FIELD_MOBILE				= "mobile";
	public static final String DOC_FIELD_EMAIL				= "Email";

	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		String strQuery = String.format("CREATE TABLE IF NOT EXISTS %s(%s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) " +
				"NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, " +
				"%s VARCHAR(%d) NOT NULL,%s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL)",
				APT_TABLE,
				APT_FIELD_INDEX, 5,
				APT_FIELD_DOCTOR, 50,
				APT_FIELD_DATE, 20,
				APT_FIELD_TIME, 20,
				APT_FIELD_REMINDER_CHK, 5,
				APT_FIELD_REMINDER_TIME, 5,
				APT_FIELD_ADDRESS, 100,
				APT_FIELD_CITY, 100,
				APT_FIELD_STATE, 50,
				APT_FIELD_ZIP, 20,
				APT_FIELD_PHONE, 20,
				APT_FIELD_MOBILE, 20,
				APT_FIELD_EMAIL, 30);

		db.execSQL(strQuery);
		
		String strQuery2 = String.format("CREATE TABLE IF NOT EXISTS %s(%s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) " +
				"NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, " +
				"%s VARCHAR(%d) NOT NULL,%s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL)",
				INSR_TABLE,
				INSR_FIELD_INDEX, 5,
				INSR_FIELD_MEMBER, 50,
				INSR_FIELD_COMPANY, 50,
				INSR_FIELD_ADDRESS, 100,
				INSR_FIELD_CITY, 50,
				INSR_FIELD_STATE, 50,
				INSR_FIELD_ZIP, 50,
				INSR_FIELD_PHONE, 50,
				INSR_FIELD_POLICY_NUMBER, 50,
				INSR_FIELD_MEMBER_ID, 20,
				INSR_FIELD_GROUP, 20,
				INSR_FIELD_BIN, 20);

		db.execSQL(strQuery2);
		
		String strQuery1 = String.format("CREATE TABLE IF NOT EXISTS %s(%s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) " +
				"NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, " +
				"%s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL)",
				SPEC_TABLE,
				SPEC_FIELD_INDEX, 5,
				SPEC_FIELD_TOTAL_INDEX, 5,
				SPEC_FIELD_DOCTOR, 50,
				SPEC_FIELD_SPECIALIST, 50,
				SPEC_FIELD_ADDRESS, 100,
				SPEC_FIELD_CITY, 50,
				SPEC_FIELD_STATE, 50,
				SPEC_FIELD_ZIP, 50,
				SPEC_FIELD_PHONE, 50,
				SPEC_FIELD_MOBILE, 50,
				SPEC_FIELD_EMAIL, 20);

		db.execSQL(strQuery1);
		
		String strQuery3 = String.format("CREATE TABLE IF NOT EXISTS %s(%s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) " +
				"NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL)",
				PHA_TABLE,
				PHA_FIELD_INDEX, 5,
				PHA_FIELD_PHARMACY, 50,
				PHA_FIELD_ADDRESS, 100,
				PHA_FIELD_CITY, 50,
				PHA_FIELD_STATE, 50,
				PHA_FIELD_ZIP, 50,
				PHA_FIELD_PHONE, 50);

		db.execSQL(strQuery3);
		
		String strQuery4 = String.format("CREATE TABLE IF NOT EXISTS %s(%s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) " +
				"NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL, %s VARCHAR(%d) NOT NULL)",
				DOC_TABLE,
				DOC_FIELD_INDEX, 5,
				DOC_FIELD_DOCTOR, 50,
				DOC_FIELD_ADDRESS, 100,
				DOC_FIELD_CITY, 50,
				DOC_FIELD_STATE, 50,
				DOC_FIELD_ZIP, 50,
				DOC_FIELD_PHONE, 50,
				DOC_FIELD_MOBILE, 50,
				DOC_FIELD_EMAIL, 20);
		db.execSQL(strQuery4);
	}
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion >= newVersion)
			return;

		String sql = null;
		if (oldVersion == 1) {
			
		}
		
		if (oldVersion == 2)
			sql = "";

		if (sql != null)
			db.execSQL(sql);
	}
}
