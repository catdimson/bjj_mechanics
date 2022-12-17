package ru.catdimson.bjjmechanics.domain.services.actions

import android.content.Context
import com.google.gson.Gson
import ru.catdimson.bjjmechanics.domain.entities.actions.chain.ActionsStack

class ActionsServiceImpl : ActionsService {

    companion object {
        private const val ACTIONS_DATAS = "ACTIONS_DATAS"

        private const val ACTIONS_CHAIN = "ACTIONS_CHAIN"
    }

    override fun <T> getActionsChainFromSharedPref(context: Context): ActionsStack<T>? {
        val spEditor = context.getSharedPreferences(ACTIONS_DATAS, Context.MODE_PRIVATE)

        val gson = Gson()
        val json = spEditor.getString(ACTIONS_CHAIN, "")

        var result = gson.fromJson(json, ActionsStack::class.java)

        return if (result == null) {
            null
        } else {
            gson.fromJson(json, ActionsStack::class.java) as ActionsStack<T>
        }
    }

    override fun <T> saveActionsChainToSharedPref(actionsChain: ActionsStack<T>, context: Context) {
        val spEditor = context.getSharedPreferences(ACTIONS_DATAS, Context.MODE_PRIVATE).edit()

        val gson = Gson()
        val json = gson.toJson(actionsChain)

        spEditor.putString(ACTIONS_CHAIN, json)
        spEditor.apply()
    }

    override fun clearSharedPreferences(context: Context) {
        val sp = context.getSharedPreferences(ACTIONS_DATAS, Context.MODE_PRIVATE)
        sp.edit().clear().apply()
    }
}