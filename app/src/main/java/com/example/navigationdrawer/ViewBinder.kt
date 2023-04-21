package com.example.navigationdrawer

import android.os.Bundle


import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet


class ViewBinderHelper {
    private var mapStates: MutableMap<String, Int> =
        Collections.synchronizedMap(HashMap<String, Int>())
    private val mapLayouts: MutableMap<String, SwipeRevealLayoutNew> =
        Collections.synchronizedMap(HashMap<String, SwipeRevealLayoutNew>())
    private val lockedSwipeSet: MutableSet<String> = Collections.synchronizedSet(HashSet<String>())

    @Volatile
    private var openOnlyOne = false
    private val stateChangeLock = Any()

    /**
     * Help to save and restore open/close state of the swipeLayout. Call this method
     * when you bind your view holder with the data object.
     *
     * @param swipeLayout swipeLayout of the current view.
     * @param id a string that uniquely defines the data object of the current view.
     */
    fun bind(swipeLayout: SwipeRevealLayoutNew, id: String) {
        if (swipeLayout.shouldRequestLayout()) {
            swipeLayout.requestLayout()
        }
        mapLayouts.values.remove(swipeLayout)
        mapLayouts[id] = swipeLayout
        swipeLayout.abort()
        swipeLayout.setDragStateChangeListener(object :
            SwipeRevealLayoutNew.DragStateChangeListener {
            override fun onDragStateChanged(state: Int) {
                mapStates[id] = state
                if (openOnlyOne) {
                    closeOthers(id, swipeLayout)
                }
            }
        })

        // first time binding.
        if (!mapStates.containsKey(id)) {
            mapStates[id] = SwipeRevealLayoutNew.STATE_CLOSE
            swipeLayout.close(false)
        } else {
            val state = mapStates[id]!!
            if (state == SwipeRevealLayoutNew.STATE_CLOSE || state == SwipeRevealLayoutNew.STATE_CLOSING || state == SwipeRevealLayoutNew.STATE_DRAGGING) {
                swipeLayout.close(false)
            } else {
                swipeLayout.open(false)
            }
        }

        // set lock swipe
        swipeLayout.setLockDrag(lockedSwipeSet.contains(id))
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in [android.app.Activity.onSaveInstanceState]
     */
  /*  fun saveStates(outState: Bundle?) {
        if (outState == null) return
        val statesBundle = Bundle()
        for ((key, value): Map.Entry<String, Int> in mapStates) {
            statesBundle.putInt(key, value)
        }
        outState.putBundle(BUNDLE_MAP_KEY, statesBundle)
    }*/

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in [android.app.Activity.onRestoreInstanceState]
     */
    fun restoreStates(inState: Bundle?) {
        if (inState == null) return
        if (inState.containsKey(BUNDLE_MAP_KEY)) {
            val restoredMap: HashMap<String, Int> = HashMap()
            val statesBundle = inState.getBundle(BUNDLE_MAP_KEY)
            val keySet = statesBundle!!.keySet()
            if (keySet != null) {
                for (key in keySet) {
                    restoredMap[key] = statesBundle.getInt(key)
                }
            }
            mapStates = restoredMap
        }
    }

    /**
     * Lock swipe for some layouts.
     * @param id a string that uniquely defines the data object.
     */
    fun lockSwipe(vararg id: String?) {
        setLockSwipe(true, *id as Array<out String>)
    }

    /**
     * Unlock swipe for some layouts.
     * @param id a string that uniquely defines the data object.
     */
    fun unlockSwipe(vararg id: String?) {
        setLockSwipe(false, *id as Array<out String>)
    }

    /**
     * @param openOnlyOne If set to true, then only one row can be opened at a time.
     */
    fun setOpenOnlyOne(openOnlyOne: Boolean) {
        this.openOnlyOne = openOnlyOne
    }

    /**
     * Open a specific layout.
     * @param id unique id which identifies the data object which is bind to the layout.
     */
    fun openLayout(id: String) {
        synchronized(stateChangeLock) {
            mapStates[id] = SwipeRevealLayoutNew.STATE_OPEN
            if (mapLayouts.containsKey(id)) {
                val layout: SwipeRevealLayoutNew? = mapLayouts[id]
                layout!!.open(true)
            } else if (openOnlyOne) {
                closeOthers(id, mapLayouts[id])
            }
        }
    }

    /**
     * Close a specific layout.
     * @param id unique id which identifies the data object which is bind to the layout.
     */
    fun closeLayout(SwipeRevealLayoutNew: SwipeRevealLayoutNew.Companion, id: String) {
        synchronized(stateChangeLock) {
            mapStates[id] = SwipeRevealLayoutNew.STATE_CLOSE
            if (mapLayouts.containsKey(id)) {
                val layout: SwipeRevealLayoutNew? = mapLayouts[id]
                layout!!.close(true)
            }
        }
    }

    /**
     * Close others swipe layout.
     * @param id layout which bind with this data object id will be excluded.
     * @param swipeLayout will be excluded.
     */
    private fun closeOthers(id: String, swipeLayout: SwipeRevealLayoutNew?) {
        synchronized(stateChangeLock) {
            // close other rows if openOnlyOne is true.
            if (openCount > 1) {
                for (entry in mapStates.entries) {
                    if (entry.key != id) {
                        entry.setValue(SwipeRevealLayoutNew.STATE_CLOSE)
                    }
                }
                for (layout in mapLayouts.values) {
                    if (layout !== swipeLayout) {
                        layout.close(true)
                    }
                }
            }
        }
    }

    private fun setLockSwipe(lock: Boolean, vararg id: String) {
        if (id.size == 0) return
        /*if (lock) lockedSwipeSet.addAll(listOf(Arrays.asList(id).toString())) else lockedSwipeSet!!.removeAll(
            Arrays.asList(
                id
            )
        )*/
        for (s in id) {
            val layout: SwipeRevealLayoutNew? = mapLayouts[s]
            if (layout != null) {
                layout.setLockDrag(lock)
            }
        }
    }

    private val openCount: Int
        private get() {
            var total = 0
            for (state in mapStates.values) {
                if (state == SwipeRevealLayoutNew.STATE_OPEN || state == SwipeRevealLayoutNew.STATE_OPENING) {
                    total++
                }
            }
            return total
        }

    companion object {
        private const val BUNDLE_MAP_KEY = "ViewBinderHelper_Bundle_Map_Key"
    }
}
