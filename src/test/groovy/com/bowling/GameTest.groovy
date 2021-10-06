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

    def "When a player throws a spare, the bonus for that frame is the number of points knocked down by the next roll"() {
        given: "a newly started game"
        Game game = new Game()

        when: "the player throws a spare"
        game.roll(5)
        game.roll(5)

        then:
        game.score() == 10

        when:
        game.roll(3)

        then: "the number of pins knocked down in the first roll of the second frame are added to the first"
        game.score() == 10+ 3 + 3
    }
}
