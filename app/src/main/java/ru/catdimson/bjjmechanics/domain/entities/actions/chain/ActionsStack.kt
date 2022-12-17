package ru.catdimson.bjjmechanics.domain.entities.actions.chain

/**
 * Класс нужен для хранения текущего состояния
 * */

class ActionsStack<T> {

    private val stackActions = ArrayDeque<T>()

    fun pop(): T? {
        return if (stackActions.size > 0) {
            stackActions.removeLast()
        } else {
            null
        }
    }

    fun push(actionId: T) {
        stackActions.add(actionId)
    }

    fun peek(): T? {
        return if (stackActions.size > 0) {
            stackActions[stackActions.size - 1]
        } else {
            null
        }
    }

    fun hasMore(): Boolean {
        return stackActions.size > 0
    }

    fun size(): Int {
        return stackActions.size
    }

}