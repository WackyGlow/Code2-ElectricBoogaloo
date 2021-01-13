package PMC.bll;

import PMC.be.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager {
    private String movieDate;

    /**
     * this method is used to compare the lastWatched date with the date six months ago.
     * if the movie was last watched over six months ago it will return true, if not the it returns false.
     * @param movie
     * @return
     * @throws ParseException
     */
    public boolean checkDate(Movie movie) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        movieDate = movie.getLastWatched();
        Date watchDate = sdf.parse(movieDate);
        Date dato= new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dato);
        calendar.add(Calendar.MONTH, -6);

        Date sixMonthsAgo = calendar.getTime();
        if (sixMonthsAgo.after(watchDate)) {
            return true;
        }
        else {
            return false;
        }
    }

}
