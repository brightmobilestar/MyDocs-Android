package jane.data;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UIUtils {
	
	public static Typeface font, fontBold;
	
	public static void setFontForAllTextViewsInHierarchy(ViewGroup aViewGroup, Typeface aFont) {
		    for (int i=0; i<aViewGroup.getChildCount(); i++) {
		        View _v = aViewGroup.getChildAt(i);
		        if (_v instanceof TextView) {
		            ((TextView) _v).setTypeface(aFont);
		        } else if (_v instanceof ViewGroup) {
		            setFontForAllTextViewsInHierarchy((ViewGroup) _v, aFont);
		        }
		    }
		}
	
	public static void setFontForAllEditTextsInHierarchy(ViewGroup aViewGroup, Typeface aFont) {
	    for (int i=0; i<aViewGroup.getChildCount(); i++) {
	        View _v = aViewGroup.getChildAt(i);
	        if (_v instanceof EditText) {
	            ((TextView) _v).setTypeface(aFont);
	        } else if (_v instanceof ViewGroup) {
	            setFontForAllTextViewsInHierarchy((ViewGroup) _v, aFont);
	        }
	    }
	}
	
	public static void setFontForAllButtonsInHierarchy(ViewGroup aViewGroup, Typeface aFont) {
	    for (int i=0; i<aViewGroup.getChildCount(); i++) {
	        View _v = aViewGroup.getChildAt(i);
	        if (_v instanceof Button) {
	            ((TextView) _v).setTypeface(aFont);
	        } else if (_v instanceof ViewGroup) {
	            setFontForAllTextViewsInHierarchy((ViewGroup) _v, aFont);
	        }
	    }
	}
	
	public static void setFontForAll(ViewGroup avg)
	{
		setFontForAllButtonsInHierarchy(avg, fontBold);
		setFontForAllEditTextsInHierarchy(avg, font);
		setFontForAllTextViewsInHierarchy(avg, fontBold);
	}	
	
}
