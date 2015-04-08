package com.offact.common;

public class StringUtil {
    public static String nvl(String paramString1, String paramString2) {
        if ((paramString1 == null) || (paramString1.length() == 0))
            return paramString2;
        return paramString1;
    }

    public static String nvl(String paramString) {
        return nvl(paramString, "");
    }

    public static boolean isNull(String paramString) {
        return ((paramString == null) || (paramString.length() == 0));
    }

    public static String repeat(String paramString, int paramInt) {
        StringBuffer localStringBuffer = new StringBuffer();
        for (paramInt = paramInt; paramInt > 0; --paramInt)
            localStringBuffer.append(paramString);
        return localStringBuffer.toString();
    }

    public static String lpad(String paramString, int paramInt, char paramChar) {
        paramString = nvl(paramString);
        paramInt -= paramString.length();
        return repeat(String.valueOf(paramChar), paramInt) + paramString;
    }

    public static String rpad(String paramString, int paramInt, char paramChar) {
        paramString = nvl(paramString);
        paramInt -= paramString.length();
        return paramString + repeat(String.valueOf(paramChar), paramInt);
    }

    public static String lpadb(String paramString, int paramInt, char paramChar) {
        paramInt -= paramString.getBytes().length;
        return repeat(String.valueOf(paramChar), paramInt) + paramString;
    }

    public static String rpadb(String paramString, int paramInt, char paramChar) {
        paramString = nvl(paramString);
        paramInt -= paramString.getBytes().length;
        return paramString + repeat(String.valueOf(paramChar), paramInt);
    }

    public static String join(String[] paramArrayOfString, String paramString1, String paramString2, String paramString3) {
        StringBuffer localStringBuffer = new StringBuffer();
        for (int i = 0; i < paramArrayOfString.length; ++i) {
            if (i != 0)
                localStringBuffer.append(paramString1);
            if (paramString2 != null)
                localStringBuffer.append(paramString2);
            localStringBuffer.append(paramArrayOfString[i]);
            if (paramString3 == null)
                continue;
            localStringBuffer.append(paramString3);
        }
        return localStringBuffer.toString();
    }

    public static boolean contains(String[] paramArrayOfString, String paramString) {
        for (int i = 0; i < paramArrayOfString.length; ++i)
            if (paramString.equals(paramArrayOfString[i]))
                return true;
        return false;
    }

    public static String substr(String paramString, int paramInt1, int paramInt2) {
        if (paramString == null)
            return paramString;
        int i = paramString.length();
        if (paramInt1 < 0)
            paramInt1 = 0;
        if (paramInt1 > i)
            paramInt1 = i;
        if (paramInt2 < 0)
            paramInt2 = 0;
        if ((paramInt2 = paramInt1 + paramInt2) > i)
            paramInt2 = i;
        return paramString.substring(paramInt1, paramInt2);
    }

    public static String isNull(String paramString1, String paramString2) {
        if ((paramString1 == null) || (paramString1.trim().equals("")) || (paramString1.trim().equals("null")))
            paramString1 = paramString2;
        else
            paramString1 = paramString1.trim();
        return paramString1;
    }

    public static int isNull(String paramString, int paramInt) {
        int i;
        try {
            if ((paramString == null) || (paramString.trim().equals(""))) {
                i = paramInt;
            } else {
                i = Integer.parseInt(paramString);
            }
        } catch (Exception localException) {
            i = paramInt;
        }
        return i;
    }

    public static long isNull(String paramString, long paramLong) {
        long l;
        try {
            if ((paramString == null) || (paramString.trim().equals(""))) {
                l = paramLong;
            } else {
                l = Long.parseLong(paramString);
            }
        } catch (Exception localException) {
            l = paramLong;
        }
        return l;
    }

    public static float isNull(String paramString, float paramFloat) {
        float f;
        try {
            if ((paramString == null) || (paramString.trim().equals(""))) {
                f = paramFloat;
            } else {
                f = Float.parseFloat(paramString);
            }
        } catch (Exception localException) {
            f = paramFloat;
        }
        return f;
    }

    public static double isNull(String paramString, double paramDouble) {
        double d;
        try {
            if ((paramString == null) || (paramString.trim().equals(""))) {
                d = paramDouble;
            } else {
                d = Double.parseDouble(paramString);
            }
        } catch (Exception localException) {
            d = paramDouble;
        }
        return d;
    }

    public static String[] split(String paramString1, String paramString2) {
        int i = 1;
        int j = paramString1.indexOf(paramString2);
        int k = 0;
        while (j >= 0) {
            ++i;
            j = paramString1.indexOf(paramString2, j + 1);
        }
        String[] arrayOfString = new String[i];
        i = 0;
        j = paramString1.indexOf(paramString2);
        while (j >= 0) {
            arrayOfString[i] = paramString1.substring(k, j);
            k = j + 1;
            j = paramString1.indexOf(paramString2, j + 1);
            ++i;
        }
        arrayOfString[i] = paramString1.substring(k);
        return arrayOfString;
    }

    public static String formatDate(String paramString) {
        if (paramString == null)
            return "";
        if ((paramString = paramString.trim()).length() == 8)
            return paramString.substring(0, 4) + "-" + paramString.substring(4, 6) + "-" + paramString.substring(6, 8);
        if ((paramString = paramString.trim()).length() == 6)
            return paramString.substring(0, 4) + "-" + paramString.substring(4, 6);
        return paramString;
    }

    public static String formatDate(String paramString1, String paramString2) {
        if (paramString1 == null)
            return "";
        paramString1 = paramString1.trim();
        if (paramString2 == null)
            return paramString1;
        if ((paramString2 != null) && (!("".equals(paramString2)))) {
            StringBuffer localStringBuffer = new StringBuffer();
            int i = 0;
            while (true) {
                if (!(String.valueOf(paramString1.charAt(i)).trim().equals(paramString2)))
                    localStringBuffer.append(paramString1.charAt(i));
                ++i;
                if (i >= paramString1.length())
                    return localStringBuffer.toString();
            }
        }
        return paramString1;
    }

    public static String removeFormat(String paramString1) {
        String mask = "!@#$%^&*-+=(){}[]/\\|_,.<>?`~";
        if (paramString1 == null || paramString1.equals(""))
            return "";
        paramString1 = paramString1.trim();
        StringBuffer localStringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            if (!(mask.indexOf(String.valueOf(paramString1.charAt(i)).trim()) >= 0))
                localStringBuffer.append(paramString1.charAt(i));
            ++i;
            if (i >= paramString1.length())
                return localStringBuffer.toString();
        }
    }

    // Limit 방식페이징 시작인덱스 구하기
    public static String getCalcLimitStart(String curPage, String rowCount) {
        curPage = nvl(curPage, "1");
        rowCount = nvl(rowCount, "10");
        int i_start = (Integer.parseInt(curPage) - 1) * Integer.parseInt(rowCount);
        return String.valueOf(i_start);
    }

    // public static void main(String[] args) {
    // String test = "123!@#$%^&*()_+-=[]{}|/<>,.?`~\\";
    // System.out.println("/" + formatDate("20131") + "/");
    // System.out.println("#####################:" + test);
    // System.out.println("#####################:" + removeFormat(test));
    // }

}