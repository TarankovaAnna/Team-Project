package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStoreTest {
    GameStore store = new GameStore();

    //     тестируем containsGame
//     Тест1 - проверяет наличие игры в каталоге, если одна игра была добавлена
    @Test
    public void shouldAddGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");


        Assertions.assertTrue(store.containsGame(game));

    }



    //     Тест 2 - проверяет наличие последней игры в каталоге, если добавлено несколько
    @Test
    public void shouldAddSomeGames() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Игра 2", "Аркады");
        Game game3 = store.publishGame("Игра 3", "Головоломка");

        Assertions.assertTrue(store.containsGame(game3));

    }

    //   Тест 3 - проверяет отсутствие игр в каталоге, если ни одной игры не было добавлено
    @Test
    public void shouldAddNoGames() {
        GameStore store = new GameStore();


        Assertions.assertFalse(store.containsGame(null));
    }

//    тестируем addPlayTime
//    Тест 4 - проверяет количество времени, которое играл игрок за игрой этого каталога,
//    если ранее он уже играл в эту игру

    @Test
    public void shouldAddTimeIfAlreadyPlayed() {
        GameStore store = new GameStore();

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("1");
        player.installGame(game);

        store.addPlayTime("1", 1);

        Integer expected = 6;
        Integer actual = store.addPlayTime("1", 5);
        Assertions.assertEquals(expected, actual);
    }

    //    Тест 5 - проверяет количество времени, которое играл игрок за игрой этого каталога,
//     если ранее он не играл в эту игру
    @Test
    public void shouldAddTimeIfHaveNotPlayedYet() {
        GameStore store = new GameStore();

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("1");

        Integer expected = 5;
        Integer actual = store.addPlayTime("1", 5);
        Assertions.assertEquals(expected, actual);
    }

    //   Тест 6 - проверяет количество времени, если никто еще не играл в эту игру
    @Test
    public void shouldAddTimeIfNoPlayedYet() {
        GameStore store = new GameStore();

        Integer expected = 0;
        Integer actual = store.addPlayTime("null", 0);
        Assertions.assertEquals(expected, actual);
    }


    //   тестируем playMost
//   Тест 7 - находит игрока, который играл в игры этого каталога дольше других
    @Test
    public void shouldPlayMost() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("1");
        Player player1 = new Player("2");
        Player player2 = new Player("3");


        store.addPlayTime("1", 3);
        store.addPlayTime("2", 9);
        store.addPlayTime("3", 1);

        String expected = "2";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    //     Тест 8 - возвращает null, если никто еще не играл в игры этого каталога
    @Test
    public void shouldPlayMostIfNoOnePlay() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("1");
        Player player1 = new Player("2");
        Player player2 = new Player("3");


        String expected = null;
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    //    Тест 9 - возвращает список игроков, если есть несколько игроков, которые играли в игры этого каталога одинаковое время
    @Test
    public void shouldPlayMostIfSeveralPlaysSameTime() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("1");
        Player player1 = new Player("2");
        Player player2 = new Player("3");
        Player player3 = new Player("4");


        store.addPlayTime("1", 3);
        store.addPlayTime("2", 9);
        store.addPlayTime("3", 1);
        store.addPlayTime("4", 9);

        String expected = "2";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    //   Тест 10 - проверяет, кто из двух игроков играл в игру каталога дольше, если одни играл 1 час, а второй 0 часов
    @Test
    public void shouldPlayMostIfPlayOneHour() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("1");
        Player player1 = new Player("2");


        store.addPlayTime("1", 1);
        store.addPlayTime("2", 0);


        String expected = "1";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    //     тестируем getSumPlayedTime
//     Тест 11 - проверяет суммарное время, которое провели несколько игроков за играми этого каталога
    @Test
    public void shouldSumTimeIfSomePlayers() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("1");
        Player player1 = new Player("2");
        Player player2 = new Player("3");
        Player player3 = new Player("4");


        store.addPlayTime("1", 3);
        store.addPlayTime("2", 9);
        store.addPlayTime("3", 1);
        store.addPlayTime("4", 9);

        int expected = 22;
        int actual = store.getSumPlayedTime(store);

        Assertions.assertEquals(expected, actual);
    }

    //   Тест 12 - проверяет суммарное время, если в игры этого каталога играл только один игрок
    @Test
    public void shouldSumTimeIfOnePlayer() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("1");


        store.addPlayTime("1", 3);


        int expected = 3;
        int actual = store.getSumPlayedTime(store);

        Assertions.assertEquals(expected, actual);
    }

    //   Тест 13 - проверяет суммарное время, если в игры этого каталога никто не играл
    @Test
    public void shouldSumTimeIfNoOnePlayersGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");


        int expected = 0;
        int actual = store.getSumPlayedTime(store);

        Assertions.assertEquals(expected, actual);
    }


}
