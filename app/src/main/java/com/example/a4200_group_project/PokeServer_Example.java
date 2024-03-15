package com.example.a4200_group_project;

public class PokeServer_Example {

    // example

    void example() {
        System.out.println("  ----------   test start   ----------\n");

        PokeServer.getPokemon(4, this::onSuccess, this::onFail);
        PokeServer.getPokemon(123, this::onSuccess, this::onFail); // not exist
        PokeServer.getPokemon("venusaur", this::onSuccess, this::onFail);
        PokeServer.getPokemon("bla bla bla", this::onSuccess, this::onFail); // not exist

    }

    void onSuccess(String content) {
        System.out.println("  ----------   success  ----------\n");
        System.out.println(content);
    }

    void onFail(Exception e) {
        System.out.println("  ----------   fail   ----------\n");
        e.printStackTrace();
    }


}
