package ru.netology;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;

    /**
     * информация о том, в какую игру сколько часов было сыграно
     * ключ - игра
     * значение - суммарное количество часов игры в эту игру
     */
    private Map<Game, Integer> playedTime = new HashMap<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * добавление игры игроку
     * если игра уже была, никаких изменений происходить не должно
     */
//    public void installGame(Game game) {
//        playedTime.put(game, 0);
//    }
    private HashMap<String, String> addGame = new HashMap<>();

    public void installGame(Game game) {

        addGame.put(game.getTitle(), game.getGenre());
    }


    /**
     * игрок играет в игру game на протяжении hours часов
     * об этом нужно сообщить объекту-каталогу игр, откуда была установлена игра
     * также надо обновить значения в мапе игрока, добавив проигранное количество часов
     * возвращает суммарное количество часов, проигранное в эту игру.
     * если игра не была установлена, то надо выкидывать RuntimeException
     */
//    public int play(Game game, int hours) {
//        game.getStore().addPlayTime(name, hours);
//        if (playedTime.containsKey(game)) {
//            playedTime.put(game, playedTime.get(game));
//        } else {
//            playedTime.put(game, hours);
//        }
//        return playedTime.get(game);
//    }
    public int play(Game game, int hours) {
        game.getStore().addPlayTime(name, hours);
        if (addGame.isEmpty()) {
            throw new RuntimeException();
        }
        if (playedTime.containsKey(game)) {
            playedTime.put(game, playedTime.get(game) + hours);
        } else {
            playedTime.put(game, hours);
        }
        if (playedTime.get(game) < 0) {
            throw new RuntimeException();
        }
        return playedTime.get(game);
    }


    /**
     * Метод принимает жанр игры (одно из полей объекта игры) и
     * суммирует время, проигранное во все игры этого жанра этим игроком
     */
    //старый код
//    public int sumGenre(String genre) {
//        int sum = 0;
//        for (Game game : playedTime.keySet()) {
//            if (game.getGenre().equals(genre)) {
//                sum += playedTime.get(game);
//            } else {
//                sum = 0;
//            }
//        }
//        return sum;
//    }
    public int sumGenre(String genre) {
        int sum = 0;
        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                sum += playedTime.get(game);
            }
        }
        return sum;
    }

    /**
     * Метод принимает жанр и возвращает игру этого жанра, в которую играли больше всего
     * Если в игры этого жанра не играли, возвращается null
     */
    //старый код
//    public Game mostPlayerByGenre(String genre) {
//        return null;
//    }
    public Game mostPlayerByGenre(String genre) {
        int maxTime = 0;
        Game maxGame = null;

        for (Game title : playedTime.keySet()) {
            if (sumGenre(genre) == 0) {
                return null;
            }
            if (playedTime.get(title) > maxTime) {
                maxTime = playedTime.get(title);
                maxGame = title;
            }
        }
        return maxGame;
    }
}