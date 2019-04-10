package com.github.ahmadaghazadeh.barcodescannerclient.utils.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PersianCalendar implements Serializable {

	private static final long serialVersionUID = 2578746994635796923L;

	public final static String DEFAULT_DATE_FORMAT = "YYYY-MM-DD";

	public final static String[] WEEKDAY_NAMES = new String[] { "يکشنبه",
			"دوشنبه", "سه شنبه", "چهارشنبه", "پنجشنبه", "جمعه", "شنبه" };

	public final static String[] MONTH_NAMES = new String[] { null, "فروردين",
			"ارديبهشت", "خرداد", "تير", "مرداد", "شهريور", "مهر", "آبان",
			"آذر", "دي", "بهمن", "اسفند" };

	private Date mDate;
	private int mDayOfMonth;
	private int mMonth;
	private int mYear;

	public PersianCalendar() {
		mDate = new Date();
		calcSolarCalendar();
	}

	public PersianCalendar(Date miladiDate) {
		mDate = miladiDate;
		calcSolarCalendar();
	}

	@SuppressWarnings("deprecation")
	private void calcSolarCalendar() {
		int ld;

		int miladiYear = mDate.getYear() + 1900;
		int miladiMonth = mDate.getMonth() + 1;
		int miladiDay = mDate.getDate();

		int[] buf1 = new int[12];
		int[] buf2 = new int[12];

		buf1[0] = 0;
		buf1[1] = 31;
		buf1[2] = 59;
		buf1[3] = 90;
		buf1[4] = 120;
		buf1[5] = 151;
		buf1[6] = 181;
		buf1[7] = 212;
		buf1[8] = 243;
		buf1[9] = 273;
		buf1[10] = 304;
		buf1[11] = 334;

		buf2[0] = 0;
		buf2[1] = 31;
		buf2[2] = 60;
		buf2[3] = 91;
		buf2[4] = 121;
		buf2[5] = 152;
		buf2[6] = 182;
		buf2[7] = 213;
		buf2[8] = 244;
		buf2[9] = 274;
		buf2[10] = 305;
		buf2[11] = 335;

		if ((miladiYear % 4) != 0) {
			mDayOfMonth = buf1[miladiMonth - 1] + miladiDay;

			if (mDayOfMonth > 79) {
				mDayOfMonth = mDayOfMonth - 79;
				if (mDayOfMonth <= 186) {
					switch (mDayOfMonth % 31) {
					case 0:
						mMonth = mDayOfMonth / 31;
						mDayOfMonth = 31;
						break;
					default:
						mMonth = (mDayOfMonth / 31) + 1;
						mDayOfMonth = (mDayOfMonth % 31);
						break;
					}
					mYear = miladiYear - 621;
				} else {
					mDayOfMonth = mDayOfMonth - 186;

					switch (mDayOfMonth % 30) {
					case 0:
						mMonth = (mDayOfMonth / 30) + 6;
						mDayOfMonth = 30;
						break;
					default:
						mMonth = (mDayOfMonth / 30) + 7;
						mDayOfMonth = (mDayOfMonth % 30);
						break;
					}
					mYear = miladiYear - 621;
				}
			} else {
				if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
					ld = 11;
				} else {
					ld = 10;
				}
				mDayOfMonth = mDayOfMonth + ld;

				switch (mDayOfMonth % 30) {
				case 0:
					mMonth = (mDayOfMonth / 30) + 9;
					mDayOfMonth = 30;
					break;
				default:
					mMonth = (mDayOfMonth / 30) + 10;
					mDayOfMonth = (mDayOfMonth % 30);
					break;
				}
				mYear = miladiYear - 622;
			}
		} else {
			mDayOfMonth = buf2[miladiMonth - 1] + miladiDay;

			if (miladiYear >= 1996) {
				ld = 79;
			} else {
				ld = 80;
			}
			if (mDayOfMonth > ld) {
				mDayOfMonth = mDayOfMonth - ld;

				if (mDayOfMonth <= 186) {
					switch (mDayOfMonth % 31) {
					case 0:
						mMonth = (mDayOfMonth / 31);
						mDayOfMonth = 31;
						break;
					default:
						mMonth = (mDayOfMonth / 31) + 1;
						mDayOfMonth = (mDayOfMonth % 31);
						break;
					}
					mYear = miladiYear - 621;
				} else {
					mDayOfMonth = mDayOfMonth - 186;

					switch (mDayOfMonth % 30) {
					case 0:
						mMonth = (mDayOfMonth / 30) + 6;
						mDayOfMonth = 30;
						break;
					default:
						mMonth = (mDayOfMonth / 30) + 7;
						mDayOfMonth = (mDayOfMonth % 30);
						break;
					}
					mYear = miladiYear - 621;
				}
			}

			else {
				mDayOfMonth = mDayOfMonth + 10;

				switch (mDayOfMonth % 30) {
				case 0:
					mMonth = (mDayOfMonth / 30) + 9;
					mDayOfMonth = 30;
					break;
				default:
					mMonth = (mDayOfMonth / 30) + 10;
					mDayOfMonth = (mDayOfMonth % 30);
					break;
				}
				mYear = miladiYear - 622;
			}
		}
	}

	public int getDayOfMonth() {
		return mDayOfMonth;
	}

	public String getGregorianDate(String pattern) {
		return new SimpleDateFormat(pattern).format(mDate);
	}

	public int getMonth() {
		return mMonth;
	}

	public String getMonthName() {
		return MONTH_NAMES[mMonth];
	}

	public String getPersianDate() {
		Locale loc = new Locale("en_US");
		return "" + mYear + "/" + String.format(loc, "%02d", mMonth) + "/"
				+ String.format(loc, "%02d", mDayOfMonth);
	}

	@SuppressWarnings("deprecation")
	public String getTime() {
		Locale loc = new Locale("en_US");
		return String.format(loc, "%02d", mDate.getHours()) + ":"
				+ String.format(loc, "%02d", mDate.getMinutes());
	}

	@SuppressWarnings("deprecation")
	public int getWeekDay() {
		return mDate.getDay();
	}

	@SuppressWarnings("deprecation")
	public String getWeekDayName() {
		return WEEKDAY_NAMES[mDate.getDay()];
	}

	public int getYear() {
		return mYear;
	}

	@Override
	public String toString() {
		return getWeekDayName() + " " + mDayOfMonth + " " + getMonthName()
				+ " " + mYear;
	}

	public static String getPersianDate(Date date){
		 PersianCalendar persianCalendar=new PersianCalendar(date);
		 return persianCalendar.getPersianDate();
	}
}
