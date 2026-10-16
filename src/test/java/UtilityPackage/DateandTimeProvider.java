package UtilityPackage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateandTimeProvider 
{
	public static String getCurrentDatandTime()
	{
		LocalTime time = LocalTime.now();
		LocalDate date = LocalDate.now();
		String timeValue = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		String value=date + "_"+ timeValue;
		return value;
	}
}
