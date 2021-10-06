package com.bowling

import spock.lang.Specification

class GameTest extends Specification {
    def "A game starts with 0 points" () {
        expect:
        new Game().score() == 0
    }
}
