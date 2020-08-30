package com.sanketbhat.cheatgtasa

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Sanket Bhat
 */
class GTASACheatService : InputMethodService(), CheatListAdapter.CheatListener,
    View.OnClickListener {
    lateinit var view: View
    lateinit var adapter: CheatListAdapter

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        service = this
        super.onStartInputView(info, restarting)
    }

    override fun onCreateInputView(): View {
        view = layoutInflater.inflate(R.layout.keyboard_view, null)
        val recyclerView = view.findViewById<RecyclerView>(R.id.cheatList)
        view.findViewById<Button>(R.id.btnPlayer).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnVehicle).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnWeapon).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnGame).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnWeather).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnOther).setOnClickListener(this)

        val list = getCheats()

        adapter = CheatListAdapter(list.filter { cheat -> cheat.type == Cheat.Type.Player }, this)
        val layoutManager = GridLayoutManager(this, 5, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        return view
    }

    override fun onCheatClick(cheat: Cheat) {
        //currentInputConnection.commitText(cheat.code, cheat.code.length - 1)
        currentInputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER))
        currentInputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER))
        for (c in cheat.code) {
            currentInputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, (c.toInt() - 36)))
            currentInputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, (c.toInt() - 36)))
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnPlayer -> {
                adapter.setList(getCheats().filter { cheat -> cheat.type == Cheat.Type.Player })
            }
            R.id.btnWeapon -> {
                adapter.setList(getCheats().filter { cheat -> cheat.type == Cheat.Type.Weapon })
            }
            R.id.btnVehicle -> {
                adapter.setList(getCheats().filter { cheat -> cheat.type == Cheat.Type.Vehicle })
            }
            R.id.btnGame -> {
                adapter.setList(getCheats().filter { cheat -> cheat.type == Cheat.Type.Game })
            }
            R.id.btnWeather -> {
                adapter.setList(getCheats().filter { cheat -> cheat.type == Cheat.Type.Weather })
            }
            R.id.btnOther -> {
                adapter.setList(getCheats().filter { cheat -> cheat.type == Cheat.Type.Other })
            }
        }
    }

    companion object {
        var service: GTASACheatService? = null
        fun getCheats(): ArrayList<Cheat> {
            return arrayListOf(
                Cheat("Full invincibility", Cheat.Type.Player, "GONPXWR"),
                Cheat("Not wanted", Cheat.Type.Player, "KDTZNHO"),
                Cheat("Most wanted", Cheat.Type.Player, "GWJZWC"),
                Cheat("Wanted + 2", Cheat.Type.Player, "NCBXXDX"),
                Cheat("Lock wanted", Cheat.Type.Player, "BYKGOAB"),
                Cheat("Complete mission", Cheat.Type.Player, "BYIXZIY"),
                Cheat("Health & wealth", Cheat.Type.Player, "PJYNQCQ"),
                Cheat("Max muscle", Cheat.Type.Player, "SGVDSQW"),
                Cheat("Max Respect", Cheat.Type.Player, "MTGIISR"),
                Cheat("Max Sexy", Cheat.Type.Player, "APGZLQR"),
                Cheat("Max Stamina", Cheat.Type.Player, "AEZLCKXU"),
                Cheat("Never Hungry", Cheat.Type.Player, "KBTMUVH"),
                Cheat("Super punch", Cheat.Type.Player, "LRMYOJM"),
                Cheat("Max driving skill", Cheat.Type.Player, "EHWBWDS"),
                Cheat("Suicide", Cheat.Type.Player, "SLOTSFK"),

                Cheat("All cars nitro", Cheat.Type.Vehicle, "WUSDOTO"),
                Cheat("Boats can fly", Cheat.Type.Vehicle, "PTHSEO"),
                Cheat("Cars can fly", Cheat.Type.Vehicle, "DOTBSFK"),
                Cheat("Invisible cars", Cheat.Type.Vehicle, "SDWBWHE"),
                Cheat("Perfect handling", Cheat.Type.Vehicle, "DLNNHZJ"),
                Cheat("Explode all", Cheat.Type.Vehicle, "BKFONFE"),
                Cheat("Caddy", Cheat.Type.Vehicle, "DAHESZY"),
                Cheat("Hearse", Cheat.Type.Vehicle, "PSPNATX"),
                Cheat("Hydra", Cheat.Type.Vehicle, "AWPTMIIQ"),
                Cheat("Race car 1", Cheat.Type.Vehicle, "HPGPIJZ"),
                Cheat("Race car 2", Cheat.Type.Vehicle, "BGJPSYC"),
                Cheat("Race car 3", Cheat.Type.Vehicle, "BIEAVBAY"),
                Cheat("Rhino", Cheat.Type.Vehicle, "AYNVQVK"),
                Cheat("Stretch", Cheat.Type.Vehicle, "IXSMWCQ"),
                Cheat("Trashmaster", Cheat.Type.Vehicle, "QPOLSVK"),
                Cheat("Vortex", Cheat.Type.Vehicle, "EHWBWDS"),
                Cheat("Jetpack", Cheat.Type.Vehicle, "CDGUDEP"),
                Cheat("Parachute", Cheat.Type.Vehicle, "GSUMLEG"),

                Cheat("All weapons", Cheat.Type.Weapon, "SDWBWHE"),
                Cheat("Weapons 1", Cheat.Type.Weapon, "BEFWKSBQ"),
                Cheat("Weapons 2", Cheat.Type.Weapon, "SHHIHJG"),
                Cheat("Weapons 3", Cheat.Type.Weapon, "GOIZSSX"),
                Cheat("Weapons 4", Cheat.Type.Weapon, "BIEUHQY"),
                Cheat("Infinite ammo", Cheat.Type.Weapon, "NECUMZ"),
                Cheat("Full aim vehicles", Cheat.Type.Weapon, "RYSMRM"),

                Cheat("+ 4 hours", Cheat.Type.Game, "YACKMWS"),
                Cheat("Speedup", Cheat.Type.Game, "EHWBWDS"),
                Cheat("Slowdown", Cheat.Type.Game, "FNJFCZC"),
                Cheat("Stop at 9PM", Cheat.Type.Game, "KTGDLXY"),
                Cheat("Stop at 12AM", Cheat.Type.Game, "AWUJNBB"),
                Cheat("Aggressive drivers", Cheat.Type.Game, "IOKXTFJ"),
                Cheat("All Green Lights", Cheat.Type.Game, "ENQCFMA"),
                Cheat("Black cars", Cheat.Type.Game, "GOYDVAO"),
                Cheat("All rural vehicles", Cheat.Type.Game, "JTBCSN"),
                Cheat("All sports cars", Cheat.Type.Game, "FRIUBIL"),
                Cheat("Low traffic", Cheat.Type.Game, "DEHDRX"),

                Cheat("Cloudy", Cheat.Type.Weather, "VBWEMQX"),
                Cheat("Fast Weather", Cheat.Type.Weather, "OWAKIJ"),
                Cheat("Foggy", Cheat.Type.Weather, "EGCEBVM"),
                Cheat("Rainy", Cheat.Type.Weather, "TAVPIER"),
                Cheat("Sandstorm", Cheat.Type.Weather, "JBWDWWO"),
                Cheat("Stormy", Cheat.Type.Weather, "EAKILHM"),
                Cheat("Sunny", Cheat.Type.Weather, "AAEXPPQC"),
                Cheat("Very Sunny", Cheat.Type.Weather, "HTRTTVJ")
            )
        }
    }
}