package org.fossify.calendar.dialogs

import android.app.Activity
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.fossify.calendar.R
import org.fossify.calendar.adapters.EmojiAdapter
import org.fossify.calendar.databinding.DialogSelectEmojiBinding
import org.fossify.commons.extensions.getAlertDialogBuilder
import org.fossify.commons.extensions.setupDialogStuff
import org.fossify.commons.extensions.viewBinding

class SelectEventEmojiDialog(val activity: Activity, var currentEmoji: String, val callback: (emoji: String) -> Unit) {
    private var dialog: AlertDialog? = null
    private val binding by activity.viewBinding(DialogSelectEmojiBinding::inflate)

    init {
        val emojis = arrayOf(
            "😀", "😃", "😄", "😁", "😆", "😅", "🤣", "😂",
            "🙂", "🙃", "😉", "😊", "😇", "🥰", "😍", "🤩",
            "😘", "😗", "😚", "😙", "🥲", "😋", "😛", "😜",
            "🤪", "😝", "🤑", "🤗", "🤭", "🤫", "🤔", "🤐",
            "🤨", "😐", "😑", "😶", "😏", "😒", "🙄", "😬",
            "🤥", "😌", "😔", "😪", "🤤", "😴", "😷", "🤒",
            "🤕", "🤢", "🤮", "🤧", "🥵", "🥶", "🥴", "😵",
            "🤯", "🤠", "🥳", "🥸", "😎", "🤓", "🧐", "😕",
            "😟", "🙁", "☹️", "😮", "😯", "😲", "😳", "🥺",
            "😦", "😧", "😨", "😰", "😥", "😢", "😭", "😱",
            "😖", "😣", "😞", "😓", "😩", "😫", "🥱", "😤",
            "😡", "😠", "🤬", "😈", "👿", "💀", "☠️", "💩",
            "🤡", "👹", "👺", "👻", "👽", "👾", "🤖", "🎃",
            "😺", "😸", "😹", "😻", "😼", "😽", "🙀", "😿",
            "😾", "❤️", "🧡", "💛", "💚", "💙", "💜", "🖤",
            "🤍", "🤎", "💔", "❣️", "💕", "💞", "💓", "💗",
            "💖", "💘", "💝", "💟", "☮️", "✝️", "☪️", "🕉️",
            "☸️", "✡️", "🔯", "🕎", "☯️", "☦️", "🛐", "⛎",
            "♈", "♉", "♊", "♋", "♌", "♍", "♎", "♏",
            "♐", "♑", "♒", "♓", "🆔", "⚛️", "🉑", "☢️",
            "☣️", "📴", "📳", "🈶", "🈚", "🈸", "🈺", "🈷️",
            "✴️", "🆚", "💮", "🉐", "㊙️", "㊗️", "🈴", "🈵",
            "🈹", "🈲", "🅰️", "🅱️", "🆎", "🆑", "🅾️", "🆘",
            "🎂", "🎉", "🎊", "🎁", "🎈", "🎀", "🏆", "🏅",
            "🥇", "🥈", "🥉", "⚽", "⚾", "🥎", "🏀", "🏐",
            "🏈", "🏉", "🎾", "🥏", "🎳", "🏏", "🏑", "🏒",
            "🥍", "🏓", "🏸", "🥊", "🥋", "🥅", "⛳", "⛸️",
            "🎣", "🤿", "🎽", "🎿", "🛷", "🥌", "🎯", "🪀",
            "🪁", "🎱", "🔮", "🪄", "🧿", "🎮", "🕹️", "🎰",
            "🎲", "🧩", "🧸", "🪅", "🪆", "♠️", "♥️", "♦️",
            "♣️", "♟️", "🃏", "🀄", "🎴", "🎭", "🖼️", "🎨",
            "⭐", "🌟", "✨", "⚡", "☄️", "💥", "🔥", "🌈",
            "☀️", "🌤️", "⛅", "🌥️", "☁️", "🌦️", "🌧️", "⛈️",
            "🌩️", "🌨️", "❄️", "☃️", "⛄", "🌬️", "💨", "💧",
            "💦", "☔", "🌊", "🌍", "🌎", "🌏", "🌐", "🌑"
        )

        val emojiAdapter = EmojiAdapter(activity, emojis, currentEmoji) { emoji ->
            callback(emoji)
            dialog?.dismiss()
        }

        binding.emojiGrid.apply {
            layoutManager = GridLayoutManager(activity, 8)
            adapter = emojiAdapter
        }

        activity.getAlertDialogBuilder()
            .apply {
                setNeutralButton(R.string.remove_emoji) { dialog, _ ->
                    callback("")
                    dialog?.dismiss()
                }

                activity.setupDialogStuff(binding.root, this, R.string.event_emoji) {
                    dialog = it
                }
            }
    }
}
