package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    GameStore store = new GameStore();
    Player player = new Player("Petya");
    Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game1 = store.publishGame("Игра 1", "Аркады");
    Game game2 = store.publishGame("Игра 2", "Аркады");
    Game game3 = store.publishGame("Игра 3", "Аркады");
    Game game4 = store.publishGame("Игра 4", "ЭкшенРПГ");
    Game game5 = store.publishGame("Игра 5", "Стратегии");

    //Тест 1 - Не изменяет значение проигранных часов в одной игре одним игроком, при попытке повторной установки.
    @Test
    public void testShouldNotChangeParametersGameWhenInstallIfItAlreadyInstalled() {
        player.installGame(game);
        player.play(game, 3);
        player.installGame(game);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    //Тест 2 - Суммирует часы проведенные в играх одного жанра, если одна игра.
    @Test
    public void ShouldSumGenreIfOneGame() {

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        Assertions.assertEquals(expected, actual);
    }

    //Тест 3 - Суммирует часы проведенные в играх одного жанра, если 2 игры одного жанра и 2 игры других разных жанров.
    @Test
    public void testShouldSumGenreIfTwoGamesFromOneGenre () {
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game, 3);
        player.play(game1, 4);
        player.play(game4, 6);
        player.play(game5, 1);

        int expected =7;
        int actual = player.sumGenre("Аркады");
        Assertions.assertEquals(expected, actual);
    }

    //Тест 4 - Суммирует часы проведенные в играх одного жанра, если игр одного жанра несколько.
    @Test
    public void testShouldSumGenreIfSeveralGameFromOneGenre () {

        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game, 3);
        player.play(game1, 4);
        player.play(game2, 6);
        player.play(game3, 1);

        int expected = 14;
        int actual = player.sumGenre("Аркады");
        Assertions.assertEquals(expected, actual);
    }

    //Тест 5 - Выкидывает RunTimeException если Игрок пытается играть в неустановленную игру.
    @Test
    public void testShouldThrowRunTimeExceptionWhenTheGameNotInstalled () {
        assertThrows(RuntimeException.class, () -> player.play(game, 3));
    }

    //Тест 6 - Подсчитывает часы при игре одним игроком в одну игру
    @Test
    public void testShouldCountedHoursWhenTheGameInstalled () {
        player.installGame(game);

        int expected = 3;
        int actual = player.play(game, 3);

        Assertions.assertEquals(expected, actual);
    }

    //Тест 7 - Ищет и возвращает игру в которую Игрок играл большее количество часов из всех одного жанра.
    @Test
    public void testShouldSearchMostPlayedByGenreGameIfInstalled(){
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game, 3);
        player.play(game1, 4);
        player.play(game2, 6);
        player.play(game3, 1);

        Game expected = game2;
        Game actual = player.mostPlayerByGenre("Аркады");

        Assertions.assertEquals(expected,actual);
    }

    //Тест 8 - Выкидывает null при попытке поиска игры в которую игрок играл дольше всего, при неустановленных играх.
//    @Test
//    public void testShouldNotSearchMostPlayedByGenreGameIfNotInstalled() {
//        player.installGame(game);
//        player.installGame(game1);
//        player.installGame(game2);
//        player.installGame(game3);
//
//        player.play(game, 3);
//        player.play(game1, 4);
//        player.play(game2, 6);
//        player.play(game3, 1);
//
//        Game expected = null;
//        Game actual = player.mostPlayerByGenre("Аркады");
//
//        Assertions.assertEquals(expected, actual);
//    }

    // Тест 9. Выдает null если в запрашиваемый жанр не играли
    @Test
    public void shouldNotSearchMostPlayedByGenreIfNoOnePlayedThisGenre() {
        player.installGame(game1);
        player.installGame(game4);

        player.play(game1, 3);
        player.play(game4, 6);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Стратегии");

        Assertions.assertEquals(expected, actual);
    }

    //Тест 10 - Выкидывает RunTimeException при попытке установить отрицательное количество часов игры.
    @Test
    public void testShouldNotAddNegativeCountHoursPlayed () {
        player.installGame(game);


        assertThrows(RuntimeException.class,
                () -> player.play(game, -1));
    }

}