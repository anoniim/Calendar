package org.fossify.calendar.activities

import android.os.Bundle
import org.fossify.calendar.databinding.ActivityAboutBinding
import org.fossify.commons.extensions.viewBinding

class AboutActivity : SimpleActivity() {
    private val binding by viewBinding(ActivityAboutBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
