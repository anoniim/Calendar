package org.fossify.calendar.activities

import android.os.Bundle
import org.fossify.calendar.databinding.ActivityAboutBinding
import org.fossify.commons.extensions.getProperTextColor
import org.fossify.commons.extensions.viewBinding

class AboutActivity : SimpleActivity() {
    private val binding by viewBinding(ActivityAboutBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val textColor = getProperTextColor()
        binding.apply {
            aboutLine1.setTextColor(textColor)
            aboutLine2.setTextColor(textColor)
            aboutLine3.setTextColor(textColor)
            aboutHeart.setTextColor(textColor)
        }
    }
}
