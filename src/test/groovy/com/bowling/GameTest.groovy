package com.bowling

import spock.lang.Specification

class GameTest extends Specification {
    def "A game starts with 0 points"() {
        expect:
        new Game().score() == 0
    }

    def "When a a player rolls, the number of pins knocked down is added to the score"() {
        given: "a newly started game"
        Game game = new Game()

        when: "the player knocks pins down"
        game.roll(5)

        then: "the score is updated with the number of pins knocked down"
        game.score() == 5
    }
}
