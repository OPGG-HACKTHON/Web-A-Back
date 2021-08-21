package opgg.weba.JamPick.util;

import opgg.weba.JamPick.domain.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class Util {
    public static final String KR_LANG = "kr_KR";
    public static final String EN_LANG = "en_US";

    public static final Long[] GENRE_IDS = {2L, 37L, 70L, 28L, 25L, 18L, 51L, 51L, 9L, 74L, 53L, 80L, 29L, 82L, 52L, 85L, 4L, 1L, 57L, 3L};
    public static final String[] KR_GENRE_DESCRIPTION = {"전략", "무료", "앞서 해보기", "시뮬레이션", "어드벤처", "스포츠", "애니메이션", "모델링", "레이싱", "고어", "일러스트레이션", "영화", "멀티플레이어", "에피소드", "오디오 제작", "360동영상", "캐주얼", "액션", "유틸리티", "RPG"};
    public static final String[] EN_GENRE_DESCRIPTION = {"Tactics", "Free", "Beta", "Simulation", "Adventure", "Sports", "Animation", "Modeling", "Racing", "Gore", "Illustration", "Movie", "Multiplayer", "Episode", "Audio", "360 Movie", "Casual", "Action", "Utility", "RPG"};

    public static String getRandomText(int targetStringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String getRandomUrl() {
        return "https://" + getRandomText(20);
    }

    public static List<Genre> createGenres(IndieApp indieApp, Integer count, String lang) {
        ArrayList<Genre> genres = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int val = random.nextInt(GENRE_IDS.length);

            Genre genre = new Genre();
            genre.setGenreId(GENRE_IDS[val]);
            genre.setDescription(lang.equals(KR_LANG) ? KR_GENRE_DESCRIPTION[val] : EN_GENRE_DESCRIPTION[val]);
            genre.setLanguage(lang);
            genre.setIndieApp(indieApp);
            genres.add(genre);
        }

        return genres;
    }

    public static IndieApp createIndieApp(Long id, Boolean isCreateChildData) {
        IndieApp indieApp = new IndieApp();

        indieApp.setId(id);
        indieApp.setCcu(0);
        indieApp.setAverageForever(0);
        indieApp.setName(getRandomText(10));
        indieApp.setHeaderImage(getRandomUrl());

        if (isCreateChildData) {
            ArrayList<Genre> genres = new ArrayList<>();
            ArrayList<IndieAppDetail> indieAppDetails = new ArrayList<>();
            ArrayList<Movie> movies = createMovies(indieApp, 5);
            ArrayList<Screenshot> screenshots = createScreenshots(indieApp, 4);

            for (String lang : new String[] {KR_LANG, EN_LANG}) {
                genres.addAll(createGenres(indieApp, 6, lang));
                indieAppDetails.add(createIndieAppDetail(indieApp, lang));
            }

            indieApp.setGenres(genres);
            indieApp.setIndieAppDetails(indieAppDetails);
        }

        return indieApp;
    }

    public static ArrayList<Screenshot> createScreenshots(IndieApp indieApp, int count) {
        ArrayList<Screenshot> screenshots = new ArrayList<>();

        for (int i = 1; i < count + 1; i++) {
            Screenshot screenshot = new Screenshot();
            screenshot.setScreenshotId((long) i);
            screenshot.setPathFull(getRandomUrl());
            screenshot.setPathThumbnail(getRandomUrl());
            screenshot.setIndieApp(indieApp);
            screenshots.add(screenshot);
        }

        return screenshots;
    }

    public static ArrayList<Movie> createMovies(IndieApp indieApp, int count) {
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 1; i < count + 1; i++) {
            Movie movie = new Movie();
            movie.setMovieId((long) i);
            movie.setName(getRandomText(4));
            movie.setMp4(getRandomUrl());
            movie.setIndieApp(indieApp);
            movies.add(movie);
        }

        return movies;
    }

    public static IndieAppDetail createIndieAppDetail(IndieApp indieApp, String lang) {
        IndieAppDetail indieAppDetail = new IndieAppDetail();
//        indieAppDetail.setId(indieApp.getId());
        indieAppDetail.setLanguage(lang);
        indieAppDetail.setShortDescription(getRandomText(50));
        indieAppDetail.setReleaseDate(getRandomDateString(lang));
        indieAppDetail.setIndieApp(indieApp);

        return indieAppDetail;
    }

    public static String getRandomDateString(String lang) {
        Date date = new Date();

        String pattern = lang.equals(KR_LANG) ? "yyyy년 MM월 dd일" : "dd MMM, yyyy";
        Locale locale = lang.equals(KR_LANG) ? Locale.KOREAN : Locale.ENGLISH;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);

        return simpleDateFormat.format(date);
    }
}
