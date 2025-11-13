package org.fossify.calendar.adapters

import android.app.Activity
import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.fossify.calendar.databinding.EmojiButtonBinding
import org.fossify.commons.extensions.getProperBackgroundColor
import org.fossify.commons.extensions.getProperPrimaryColor

class EmojiAdapter(
    private val activity: Activity,
    private val emojis: Array<String>,
    var currentEmoji: String,
    val callback: (emoji: String) -> Unit
) : RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        return EmojiViewHolder(
            binding = EmojiButtonBinding.inflate(activity.layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        val emoji = emojis[position]
        holder.bindView(emoji = emoji, selected = emoji == currentEmoji)
    }

    override fun getItemCount() = emojis.size

    private fun updateSelection(emoji: String) {
        currentEmoji = emoji
        notifyDataSetChanged()
        callback(emoji)
    }

    inner class EmojiViewHolder(val binding: EmojiButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(emoji: String, selected: Boolean) {
            binding.emojiButton.apply {
                text = emoji
                setOnClickListener {
                    updateSelection(emoji)
                }

                if (selected) {
                    setBackgroundColor(activity.getProperPrimaryColor())
                } else {
                    setBackgroundColor(Color.TRANSPARENT)
                }
            }
        }
    }
}
