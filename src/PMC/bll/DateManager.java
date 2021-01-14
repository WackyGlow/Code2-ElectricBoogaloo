package PMC.bll;

import PMC.be.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager {
    private String movieDate;
    private int moviesOverSixMonths;

    public DateManager() {
        moviesOverSixMonths = 0;
    }

    /**
     * this method is used to compare the lastWatched date with the date six months ago.
     * if a movie was watched over six months ago adds 1 to moviesOverSixMonths.
     * @param movie
     * @return
     * @throws ParseException
     */
    public void checkDate(Movie movie) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        movieDate = movie.getLastWatched();
        Date watchDate = sdf.parse(movieDate);
        Date dato = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dato);
        calendar.add(Calendar.MONTH, -6);

        Date sixMonthsAgo = calendar.getTime();
        if (watchDate.after(sixMonthsAgo)) {
            moviesOverSixMonths++;
        }
        System.out.println(sdf.format(sixMonthsAgo));
        System.out.println(sdf.format(watchDate));
    }

    public int getMoviesOverSixMonths() {
        return moviesOverSixMonths;
    }
}
