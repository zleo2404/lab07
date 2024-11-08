package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

import javax.swing.JPopupMenu.Separator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    public enum Month{
        JANUARY("JANUARY"),
        FEBRUARY("FEBRUARY"),
        MARCH("MARCH"),
        APRIL("APRIL"),
        MAY("MAY"),
        JUNE("JUNE"),
        JULY("JULY"),
        AUGUST("AUGUST"),
        SEPTEMBER("SEPTEMBER"),
        OCTOBER("OCTOBER"),
        NOVEMBER("NOVEMBER"),
        DECEMBER("DECEMBER");
        
        private final String actualName;

        Month(String string) {
            this.actualName= string;
        }

        public static Month MonthFromString(String string){
                    int i=0;
                    Month value = null;
                    for(Month s : Month.values())
                    {
                        if(s.actualName.contains(string.toUpperCase())){
                            i++;
                            value = s;
                        }
                    }
                    if(i>1) throw new IllegalArgumentException("Nome ambiguo");
                    if(i==0) throw new IllegalArgumentException("Non esiste");
        
                    return value;
                    
                }
        
                private static int getDays(Month m){
        
                    if(m.actualName.equals(NOVEMBER.name()) || m.actualName.equals(APRIL.name()) || m.actualName.equals(JUNE.name()) || m.actualName.equals(SEPTEMBER.name())) return 30;
                    if(m.actualName.equals(FEBRUARY.name())) return 28;
        
                    return 31;
        
                }
        
            }
        
        
        
        
            @Override
            public Comparator<String> sortByDays() {
                return new Comparator<String>() {
        
                    @Override
                    public int compare(String o1, String o2) {
                       
                        return Integer.compare(Month.getDays(Month.MonthFromString(o1)), Month.getDays(Month.MonthFromString(o2)));
                                                
            }
            
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {

                return Integer.compare(Month.MonthFromString(o1).ordinal(), Month.MonthFromString(o2).ordinal());
                
            }
            
        };
    }
}
