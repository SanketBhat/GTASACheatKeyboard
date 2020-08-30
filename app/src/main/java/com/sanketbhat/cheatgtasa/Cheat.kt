package com.sanketbhat.cheatgtasa

import java.io.Serializable

/**
 * @author Sanket Bhat
 */
data class Cheat(val name: String, val type: Type, val code: String) : Serializable {
    enum class Type {
        Weapon, Player, Game, Weather, Vehicle, Other
    }
}