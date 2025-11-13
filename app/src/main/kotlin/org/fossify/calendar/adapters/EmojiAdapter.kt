package org.fossify.calendar.adapters

import android.app.Activity
import android.graphics.Color
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import org.fossify.calendar.databinding.EmojiButtonBinding
import org.fossify.commons.extensions.getProperBackgroundColor
import org.fossify.commons.extensions.getProperPrimaryColor

class EmojiAdapter(
    private val activity: Activity,
    private val emojis: Array<String>,
    var currentEmoji: String,
    val callback: (emoji: String) -> Unit,
    private val recentlyUsedCount: Int = 0
) : RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        return EmojiViewHolder(
            binding = EmojiButtonBinding.inflate(activity.layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        val emoji = emojis[position]
        val isFirstAfterRecent = recentlyUsedCount > 0 && position == recentlyUsedCount
        holder.bindView(emoji = emoji, selected = emoji == currentEmoji, showDivider = isFirstAfterRecent)
    }

    override fun getItemCount() = emojis.size

    private fun updateSelection(emoji: String) {
        currentEmoji = emoji
        notifyDataSetChanged()
        callback(emoji)
    }

    inner class EmojiViewHolder(val binding: EmojiButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(emoji: String, selected: Boolean, showDivider: Boolean) {
            binding.emojiButton.apply {
                // Handle empty emojis (padding spacers)
                if (emoji.isEmpty()) {
                    text = ""
                    isClickable = false
                    setBackgroundColor(Color.TRANSPARENT)
                    updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        topMargin = 0
                    }
                    return
                }

                text = emoji
                isClickable = true
                setOnClickListener {
                    updateSelection(emoji)
                }

                // Add top margin for separator
                updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    topMargin = if (showDivider) {
                        activity.resources.getDimensionPixelSize(org.fossify.commons.R.dimen.medium_margin)
                    } else {
                        0
                    }
                }

                if (selected) {
                    setBackgroundResource(org.fossify.commons.R.drawable.circle_background)
                    backgroundTintList = android.content.res.ColorStateList.valueOf(activity.getProperPrimaryColor())
                } else {
                    setBackgroundColor(Color.TRANSPARENT)
                }
            }
        }
    }
}
