package ru.catdimson.bjjmechanics.services.actions

import android.content.Context
import ru.catdimson.bjjmechanics.domain.entities.actions.chain.ActionsStack

interface ActionsService {

    fun <T> getActionsChainFromSharedPref(context: Context): ActionsStack<T>?

    fun <T> saveActionsChainToSharedPref(actionsChain: ActionsStack<T>, context: Context)

    fun clearSharedPreferences(context: Context)

}